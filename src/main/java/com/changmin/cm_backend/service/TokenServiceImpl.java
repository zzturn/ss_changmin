package com.changmin.cm_backend.service;

import cn.hutool.core.util.IdUtil;
import com.changmin.cm_backend.config.common.util.date.DateUtils;
import com.changmin.cm_backend.config.security.config.SecurityProperties;
import com.changmin.cm_backend.config.security.util.SecurityFrameworkUtils;
import com.changmin.cm_backend.exceptions.BusinessException;
import com.changmin.cm_backend.exceptions.ErrorCodeConstants;
import com.changmin.cm_backend.mapper.OAuth2AccessTokenMapper;
import com.changmin.cm_backend.model.pojo.AccessTokenDO;
import com.changmin.cm_backend.model.pojo.UserDO;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import javax.annotation.Resource;

import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * OAuth2.0 Token Service 实现类
 *
 * @author changmin源码
 */
@Slf4j
@Service
public class TokenServiceImpl implements TokenService {
  @Resource private OAuth2AccessTokenMapper oauth2AccessTokenMapper;

  @Resource private SecurityProperties securityProperties;

  public static final Long DEFAULT_EXPIRES_IN = 7L * 86400 * 1000;

  public static final Long MAX_EXPIRES_IN = 90L * 86400 * 1000;

  public static final String TOKEN_EXPIRE_UPDATE_LOCK_KEY_FORMAT = "lock:access_token:update:%s";

  public static final Long UPDATE_TIME_INTERVAL = 6 * 60 * 60 * 1000L;

  @Override
  @Transactional
  public AccessTokenDO createAccessToken(UserDO userDO) {
    // token失效时间设置7天
    long current = System.currentTimeMillis();
    Date expiresTime = new Date(current + DEFAULT_EXPIRES_IN);
    Date maxExpiresTime = new Date(current + MAX_EXPIRES_IN);
    AccessTokenDO accessTokenDO =
        AccessTokenDO.builder()
            .accessToken(generateAccessToken())
            .userId(userDO.getId())
            .userType(userDO.getUserType())
            .userContext(null)
            .clientId("miniapp")
            .scopes(null)
            .refreshToken(null)
            .expiresTimeUtc(expiresTime)
            .maxExpiresTimeUtc(maxExpiresTime)
            .build();

    oauth2AccessTokenMapper.insert(accessTokenDO);
    // 记录到 Redis 中

    return accessTokenDO;
  }

  @Override
  public AccessTokenDO getAccessToken(String accessToken) {

    // 获取不到，从 数据库 中获取
    AccessTokenDO accessTokenDO = oauth2AccessTokenMapper.selectByAccessToken(accessToken);

    return accessTokenDO;
  }

  @Override
  public AccessTokenDO checkAccessToken(String accessToken) {
    AccessTokenDO accessTokenDO = getAccessToken(accessToken);
    if (accessTokenDO == null) {
      throw new BusinessException(ErrorCodeConstants.TOKEN_NOT_EXIST);
    }
    if (DateUtils.isExpired(accessTokenDO.getExpiresTimeUtc())) {
      throw new BusinessException(ErrorCodeConstants.TOKEN_EXPIRED);
    }
    return accessTokenDO;
  }

  @Override
  public AccessTokenDO removeAccessToken(String accessToken) {
    // 删除访问令牌
    AccessTokenDO accessTokenDO = oauth2AccessTokenMapper.selectByAccessToken(accessToken);
    if (accessTokenDO == null) {
      return null;
    }
    oauth2AccessTokenMapper.deleteById(accessTokenDO.getId());
    return accessTokenDO;
  }

  /** 重置当前用户的token有效期 */
  @Override
  public void updateCurrentTokenExpire() {
    String token =
        SecurityFrameworkUtils.obtainAuthorization(
            ((ServletRequestAttributes)
                    Objects.requireNonNull(RequestContextHolder.getRequestAttributes()))
                .getRequest(),
            securityProperties.getTokenHeader());
    // 更新 token 有效期
    String tokenKey = String.format(TOKEN_EXPIRE_UPDATE_LOCK_KEY_FORMAT, token);
    updateTokenExpire(token);
  }

  @Override
  public void updateTokenExpire(@NotNull String token) {
    AccessTokenDO accessToken = getAccessToken(token);
    oauth2AccessTokenMapper.updateExpiresTimeUtcByAccessToken(
        token,
        DateUtils.min(
            accessToken.getMaxExpiresTimeUtc(),
            new Date(System.currentTimeMillis() + DEFAULT_EXPIRES_IN)));
  }

  private static String generateAccessToken() {
    return IdUtil.fastSimpleUUID();
  }
}

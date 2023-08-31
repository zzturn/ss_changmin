package com.changmin.cm_backend.service;

import com.changmin.cm_backend.model.pojo.AccessTokenDO;
import com.changmin.cm_backend.model.pojo.UserDO;
import org.springframework.transaction.annotation.Transactional;

/**
 * OAuth2.0 Token Service 接口
 *
 * <p>从功能上，和 Spring Security OAuth 的 DefaultTokenServices + JdbcTokenStore 的功能，提供访问令牌、刷新令牌的操作
 *
 * @author changmin源码
 */
public interface TokenService {
  @Transactional
  AccessTokenDO createAccessToken(UserDO userDO);

  AccessTokenDO getAccessToken(String accessToken);

  AccessTokenDO checkAccessToken(String accessToken);

  AccessTokenDO removeAccessToken(String accessToken);

  void updateCurrentTokenExpire();

  // 删除访问令牌
}

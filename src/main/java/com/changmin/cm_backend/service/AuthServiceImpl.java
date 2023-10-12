package com.changmin.cm_backend.service;

import static com.changmin.cm_backend.exceptions.ErrorCodeConstants.*;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaPhoneNumberInfo;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.changmin.cm_backend.config.common.enums.UserTypeEnum;
import com.changmin.cm_backend.exceptions.BusinessException;
import com.changmin.cm_backend.mapper.UserMapper;
import com.changmin.cm_backend.model.pojo.AccessTokenDO;
import com.changmin.cm_backend.model.pojo.UserDO;
import com.changmin.cm_backend.model.vo.AuthLoginRespVO;
import com.changmin.cm_backend.model.vo.AuthMiniappDataVO;
import com.changmin.cm_backend.model.vo.AuthMiniappLoginReqVO;
import com.changmin.cm_backend.model.vo.AuthRegisterReqVO;
import java.util.Objects;
import java.util.Optional;
import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AuthServiceImpl implements AuthService {

  @Autowired UserMapper userMapper;

  @Autowired TokenService tokenService;

  @Resource private WxMaService wxMaService;

  @Resource private PasswordEncoder passwordEncoder;

  @Override
  public AuthLoginRespVO miniappLogin(AuthMiniappLoginReqVO reqVO) {
    // 通过 code 获得 session 信息
    WxMaJscode2SessionResult sessionInfo;
    try {
      sessionInfo = wxMaService.getUserService().getSessionInfo(reqVO.getCode());
    } catch (WxErrorException e) {
      log.error("微信小程序 session 信息解析失败: {}", e.getError().toString());
      throw new BusinessException(AUTH_MINIAPP_SESSION_PARSE_ERROR);
    }
    // 解析手机号
    WxMaPhoneNumberInfo phoneNoInfo = null;
    if (StrUtil.isNotEmpty(reqVO.getMobileCode())) {

      try {
        phoneNoInfo = wxMaService.getUserService().getPhoneNoInfo(reqVO.getMobileCode());
        log.info("微信小程序手机号解析成功: {}", phoneNoInfo.toString());
      } catch (WxErrorException e) {
        log.error("微信小程序手机号解析失败: {}", e.getError().toString());
        throw new BusinessException(AUTH_MINIAPP_MOBILE_PARSE_ERROR);
      }
    }
    // 解析用户基本信息
    WxMaUserInfo userInfo =
        Optional.ofNullable(reqVO.getMiniappUserInfo())
            .map(x -> getMiniappUserInfo(sessionInfo.getSessionKey(), x))
            .orElse(null);
    String openid = sessionInfo.getOpenid();
    if (StrUtil.isEmpty(openid)) {
      log.error("微信小程序 openid 为空");
      throw new BusinessException(AUTH_MINIAPP_SESSION_PARSE_ERROR);
    }
    UserDO userDO = userMapper.selectByOpenid(openid);
    if (Objects.isNull(userDO)) {
      userDO = create("", "", "", UserTypeEnum.MEMBER, openid);
    }
    return createTokenAfterLoginSuccess(userDO);
  }

  @Override
  public AuthLoginRespVO register(AuthRegisterReqVO reqVO) {
    UserDO user = getUserByPhone(reqVO.getPhone());
    if (Objects.nonNull(user)) {
      throw new BusinessException(USER_MOBILE_EXISTS);
    }
    String password = reqVO.getPassword();
    String passwordEncoded = Objects.isNull(password) ? null : passwordEncoder.encode(password);

    UserDO userDO = this.create(reqVO.getPhone(), null, passwordEncoded, UserTypeEnum.MEMBER, "");

    return createTokenAfterLoginSuccess(userDO);
  }

  @Override
  public UserDO getUserByPhone(String phone) {
    return userMapper.selectOne(UserDO::getPhone, phone);
  }

  @Override
  public UserDO create(
      String phone, String email, String pwEncode, UserTypeEnum userType, String openid) {
    UserDO user =
        new UserDO(
            null,
            null,
            pwEncode,
            RandomUtil.randomString(8),
            null,
            email,
            phone,
            null,
            null,
            1,
            null,
            null,
            userType.getValue(),
            openid);
    int insert = userMapper.insert(user);
    return user;
  }

  @Override
  public UserDO login(
      String code, @NotNull WxMaJscode2SessionResult sessionInfo, WxMaUserInfo userInfo) {

    UserDO userDO = userMapper.selectByOpenid(sessionInfo.getOpenid());
    if (Objects.nonNull(userDO)) {
      return userDO;
    }
    userDO = UserDO.builder().id(null).openid(sessionInfo.getOpenid()).build();
    return userDO;
  }

  private WxMaUserInfo getMiniappUserInfo(String sessionKey, AuthMiniappDataVO vo) {
    if (Objects.isNull(vo)) {
      return null;
    }
    // 用户信息校验
    if (!wxMaService
        .getUserService()
        .checkUserInfo(sessionKey, vo.getRawData(), vo.getSignature())) {
      log.error("微信小程序用户信息校验失败: {}", vo);
      return null;
    }

    // 通过 miniappReqVO 解密出用户信息
    WxMaUserInfo userInfo =
        wxMaService.getUserService().getUserInfo(sessionKey, vo.getEncryptedData(), vo.getIv());
    return userInfo;
  }

  private AuthLoginRespVO createTokenAfterLoginSuccess(UserDO userDO) {

    // 创建访问令牌
    AccessTokenDO accessTokenDO = tokenService.createAccessToken(userDO);
    return new AuthLoginRespVO(
        accessTokenDO.getUserId(),
        accessTokenDO.getAccessToken(),
        accessTokenDO.getExpiresTimeUtc());
  }
}

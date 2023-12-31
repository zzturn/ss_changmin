package com.changmin.cm_backend.service;

import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import com.changmin.cm_backend.config.common.enums.UserTypeEnum;
import com.changmin.cm_backend.model.pojo.UserDO;
import com.changmin.cm_backend.model.vo.AuthLoginRespVO;
import com.changmin.cm_backend.model.vo.AuthMiniappLoginReqVO;
import com.changmin.cm_backend.model.vo.AuthRegisterReqVO;
import javax.validation.constraints.NotNull;

public interface AuthService {

  UserDO getUserByPhone(String mobile);

  UserDO create(String phone, String email, String pwEncode, UserTypeEnum userType, String openid);

  UserDO login(String code, @NotNull WxMaJscode2SessionResult sessionInfo, WxMaUserInfo userInfo);

  AuthLoginRespVO miniappLogin(AuthMiniappLoginReqVO reqVO);

  AuthLoginRespVO register(AuthRegisterReqVO reqVO);
}

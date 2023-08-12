package com.changmin.cm_backend.util;

import com.changmin.cm_backend.config.security.util.SecurityFrameworkUtils;
import com.changmin.cm_backend.exceptions.BusinessException;
import java.util.Objects;

/**
 * content
 *
 * @author ouzhenxiong
 * @since 2023/4/10
 */
public class LoginUserUtil {

  public static LoginUser getLoginUser() {
    LoginUser loginUser = SecurityFrameworkUtils.getLoginUser();
    if (Objects.isNull(loginUser)) {
      return null;
    }
    return loginUser;
  }

  public static String getLoginUserId() {

    LoginUser loginUser = getLoginUser();
    if (Objects.isNull(loginUser)) {
      return null;
    }
    return loginUser.getId();
  }

  public static String getLoginUserIdOrElseThrow() {
    LoginUser loginUser = getLoginUser();
    if (Objects.isNull(loginUser) || Objects.isNull(loginUser.getId())) {
      throw new BusinessException("账号未登录");
    }
    return loginUser.getId();
  }

  public static LoginUser getLoginUserOrElseThrow() {
    LoginUser loginUser = getLoginUser();
    if (Objects.isNull(loginUser) || Objects.isNull(loginUser.getId())) {
      throw new BusinessException("账号为登录");
    }
    return loginUser;
  }
}

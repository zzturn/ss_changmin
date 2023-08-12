package com.changmin.cm_backend.util;

import com.changmin.cm_backend.config.common.enums.UserTypeEnum;
import com.changmin.cm_backend.config.mybatis.core.query.LambdaQueryWrapperX;

import java.util.Date;
import java.util.Objects;
import java.util.Optional;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.BeansException;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 专属于 web 包的工具类
 *
 * @author changmin源码
 */
@Component
public class WebFrameworkUtils {

  private static final String REQUEST_ATTRIBUTE_LOGIN_USER_ID = "login_user_id";
  private static final String REQUEST_ATTRIBUTE_LOGIN_USER_TYPE = "login_user_type";

  private static final String REQUEST_ATTRIBUTE_COMMON_RESULT = "common_result";

  private static final String DEVICE_HEADER = "X-Device-ID";

  private static final String XCHANNEL_HEADER = "xchannel";

  private static WebProperties properties;

  public WebFrameworkUtils(WebProperties webProperties) {
    WebFrameworkUtils.properties = webProperties;
  }

  public static void setLoginUserId(ServletRequest request, String userId) {
    request.setAttribute(REQUEST_ATTRIBUTE_LOGIN_USER_ID, userId);
  }

  /**
   * 设置用户类型
   *
   * @param request 请求
   * @param userType 用户类型
   */
  public static void setLoginUserType(ServletRequest request, Integer userType) {
    request.setAttribute(REQUEST_ATTRIBUTE_LOGIN_USER_TYPE, userType);
  }

  /**
   * 获得当前用户的编号，从请求中 注意：该方法仅限于 framework 框架使用！！！
   *
   * @param request 请求
   * @return 用户编号
   */
  public static String getLoginUserId(HttpServletRequest request) {
    if (request == null) {
      return null;
    }
    return String.valueOf(request.getAttribute(REQUEST_ATTRIBUTE_LOGIN_USER_ID));
  }

  /**
   * 获得当前用户的类型 注意：该方法仅限于 web 相关的 framework 组件使用！！！
   *
   * @param request 请求
   * @return 用户编号
   */
  public static Integer getLoginUserType(HttpServletRequest request) {
    if (request == null) {
      return null;
    }
    // 基于 URL 前缀的约定
    if (request.getRequestURI().startsWith("/management")) {
      return UserTypeEnum.ADMIN.getValue();
    }
    if (request.getRequestURI().startsWith("/v1")) {
      return UserTypeEnum.MEMBER.getValue();
    }
    return null;
  }
  public static Integer getLoginUserType() {
    HttpServletRequest request = getRequest();
    return getLoginUserType(request);
  }

  public static String getLoginUserId() {
    HttpServletRequest request = getRequest();
    return getLoginUserId(request);
  }


  public static HttpServletRequest getRequest() {
    RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
    if (!(requestAttributes instanceof ServletRequestAttributes)) {
      return null;
    }
    ServletRequestAttributes servletRequestAttributes =
        (ServletRequestAttributes) requestAttributes;
    return servletRequestAttributes.getRequest();
  }
}

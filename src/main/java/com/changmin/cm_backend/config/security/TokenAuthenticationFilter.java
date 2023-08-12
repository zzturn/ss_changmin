package com.changmin.cm_backend.config.security;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.changmin.cm_backend.config.common.enums.UserTypeEnum;
import com.changmin.cm_backend.config.security.config.SecurityProperties;
import com.changmin.cm_backend.config.security.util.SecurityFrameworkUtils;
import com.changmin.cm_backend.exceptions.BusinessException;
import com.changmin.cm_backend.model.pojo.AccessTokenDO;
import com.changmin.cm_backend.service.TokenService;
import com.changmin.cm_backend.util.LoginUser;
import com.changmin.cm_backend.util.WebFrameworkUtils;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * Token 过滤器，验证 token 的有效性 验证通过后，获得 {@link LoginUser} 信息，并加入到 Spring Security 上下文
 *
 * @author changmin源码
 */
@RequiredArgsConstructor
public class TokenAuthenticationFilter extends OncePerRequestFilter {

  private final TokenService tokenService;

  private final SecurityProperties securityProperties;

  @Override
  @SuppressWarnings("NullableProblems")
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain chain)
      throws ServletException, IOException {
    String token =
        SecurityFrameworkUtils.obtainAuthorization(request, securityProperties.getTokenHeader());
    if (StrUtil.isNotEmpty(token)) {
      // 区分后台权限，前台用户不能使用后台功能
      Integer userType = WebFrameworkUtils.getLoginUserType(request);
      // 1 基于 token 构建登录用户
      LoginUser loginUser = buildLoginUserByToken(token, userType);
      // 1.2 模拟 Login 功能，方便日常开发调试
      if (loginUser == null) {
        loginUser = mockLoginUser(request, token, userType);
      }
      // 2. 设置当前用户
      if (loginUser != null) {
        SecurityFrameworkUtils.setLoginUser(loginUser, request);
      }
    }

    // 继续过滤链
    chain.doFilter(request, response);
  }

  private LoginUser buildLoginUserByToken(String token, Integer userType) {
    try {
      AccessTokenDO accessTokenDO = tokenService.checkAccessToken(token);
      if (accessTokenDO == null) {
        return null;
      }
      // 前台用户不能使用后台功能，无权限
      if (UserTypeEnum.ADMIN.getValue().equals(userType)
          && ObjectUtil.notEqual(accessTokenDO.getUserType(), userType)) {
        throw new AccessDeniedException("错误的用户类型");
      }
      // 构建登录用户
      return new LoginUser(
          accessTokenDO.getUserId(),
          accessTokenDO.getUserType(),
          accessTokenDO.getScopes(),
          accessTokenDO.getUserContext());
    } catch (BusinessException businessException) {
      // 校验 Token 不通过时，考虑到一些接口是无需登录的，所以直接返回 null 即可
      return null;
    }
  }

  /**
   * 模拟登录用户，方便日常开发调试
   *
   * <p>注意，在线上环境下，一定要关闭该功能！！！
   *
   * @param request 请求
   * @param token 模拟的 token，格式为 {@link SecurityProperties#getMockSecret()} + 用户编号
   * @param userType 用户类型
   * @return 模拟的 LoginUser
   */
  private LoginUser mockLoginUser(HttpServletRequest request, String token, Integer userType) {
    if (!securityProperties.getMockEnable()) {
      return null;
    }
    // 必须以 mockSecret 开头
    if (!token.startsWith(securityProperties.getMockSecret())) {
      return null;
    }
    // 构建模拟用户
    String userId = token.substring(securityProperties.getMockSecret().length());
    return new LoginUser(userId, userType, null, null);
  }
}

package com.changmin.cm_backend.config.security.core.service;

import cn.hutool.core.collection.CollUtil;
import com.changmin.cm_backend.config.security.util.SecurityFrameworkUtils;
import com.changmin.cm_backend.service.PermissionService;
import com.changmin.cm_backend.util.LoginUser;
import com.changmin.cm_backend.util.LoginUserUtil;
import java.util.Arrays;
import lombok.AllArgsConstructor;

/**
 * 默认的 {@link SecurityFrameworkService} 实现类
 *
 * @author changmin源码
 */
@AllArgsConstructor
public class SecurityFrameworkServiceImpl implements SecurityFrameworkService {

  private final PermissionService permissionService;

  @Override
  public boolean hasPermission(String permission) {
    return hasAnyPermissions(permission);
  }

  // todo ouzhenxiong 校验权限
  @Override
  public boolean hasAnyPermissions(String... permissions) {
    String userId = LoginUserUtil.getLoginUserIdOrElseThrow();
    return permissionService.hasAnyPermissions(userId, permissions);
  }

  @Override
  public boolean hasRole(String role) {
    return hasAnyRoles(role);
  }

  @Override
  public boolean hasAnyRoles(String... roles) {
    String userId = LoginUserUtil.getLoginUserIdOrElseThrow();
    return permissionService.hasAnyRoles(userId, roles);
  }

  @Override
  public boolean hasScope(String scope) {
    return hasAnyScopes(scope);
  }

  @Override
  public boolean hasAnyScopes(String... scope) {
    LoginUser user = SecurityFrameworkUtils.getLoginUser();
    if (user == null) {
      return false;
    }
    return CollUtil.containsAny(user.getScopes(), Arrays.asList(scope));
  }
}

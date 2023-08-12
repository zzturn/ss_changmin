package com.changmin.cm_backend.service;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import javax.annotation.PostConstruct;
import org.springframework.lang.Nullable;

/**
 * 权限 Service 接口
 *
 * <p>提供用户-角色、角色-菜单、角色-部门的关联权限处理
 *
 * @author 芋道源码
 */
public interface PermissionService {

    @PostConstruct
    void initLocalCache();

  /**
   * 设置角色菜单
   *
   * @param roleId 角色编号
   * @param menuIds 菜单编号集合
   */
  void assignRoleMenu(Long roleId, Set<String> menuIds);

  /**
   * 获得用户拥有的角色编号集合
   *
   * @param userId 用户编号
   * @return 角色编号集合
   */
  Set<Long> getUserRoleIdListByUserId(String userId);

  /**
   * 设置用户角色
   *
   * @param userId 角色编号
   * @param roleIds 角色编号集合
   */
  void assignUserRole(String userId, Set<Long> roleIds);

  /**
   * 设置角色的数据权限
   *
   * @param roleId 角色编号
   * @param dataScope 数据范围
   * @param dataScopeDeptIds 部门编号数组
   */
  void assignRoleDataScope(Long roleId, Integer dataScope, Set<Long> dataScopeDeptIds);

  /**
   * 处理角色删除时，删除关联授权数据
   *
   * @param roleId 角色编号
   */
  void processRoleDeleted(Long roleId);

  /**
   * 处理菜单删除时，删除关联授权数据
   *
   * @param menuId 菜单编号
   */
  void processMenuDeleted(String menuId);

  /**
   * 处理用户删除是，删除关联授权数据
   *
   * @param userId 用户编号
   */
  void processUserDeleted(String userId);

  /**
   * 判断是否有权限，任一一个即可
   *
   * @param userId 用户编号
   * @param permissions 权限
   * @return 是否
   */
  boolean hasAnyPermissions(String userId, String... permissions);

  /**
   * 判断是否有角色，任一一个即可
   *
   * @param roles 角色数组
   * @return 是否
   */
  boolean hasAnyRoles(String userId, String... roles);
}

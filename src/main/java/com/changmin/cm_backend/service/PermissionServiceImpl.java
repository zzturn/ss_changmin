package com.changmin.cm_backend.service;

import java.util.Set;
import org.springframework.stereotype.Service;

@Service
public class PermissionServiceImpl implements PermissionService{
    @Override
    public void initLocalCache() {

    }

    @Override
    public void assignRoleMenu(Long roleId, Set<String> menuIds) {

    }

    @Override
    public Set<Long> getUserRoleIdListByUserId(String userId) {
        return null;
    }

    @Override
    public void assignUserRole(String userId, Set<Long> roleIds) {

    }

    @Override
    public void assignRoleDataScope(Long roleId, Integer dataScope, Set<Long> dataScopeDeptIds) {

    }

    @Override
    public void processRoleDeleted(Long roleId) {

    }

    @Override
    public void processMenuDeleted(String menuId) {

    }

    @Override
    public void processUserDeleted(String userId) {

    }

    @Override
    public boolean hasAnyPermissions(String userId, String... permissions) {
        return false;
    }

    @Override
    public boolean hasAnyRoles(String userId, String... roles) {
        return false;
    }
}

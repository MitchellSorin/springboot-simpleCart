package com.sorin.simplecart.bean.rolepermission;

import java.io.Serializable;

/**
 * 角色权限关联主键
 *
 * @author LSD
 * @date 2019/06/20
 **/
public class RolePermissionKey implements Serializable {

    private String roleId;

    private String permissionId;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
    }
}

package com.sorin.simplecart.bean.rolepermission;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 角色权限关联
 *
 * @author LSD
 * @date 2019/06/20
 **/
@Entity
@Table(name = "role_permission")
@IdClass(RolePermissionKey.class)
public class RolePermission  implements Serializable {

    @Id
    @Column(length = 32)
    private String roleId;

    @Id
    @Column(length = 32)
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

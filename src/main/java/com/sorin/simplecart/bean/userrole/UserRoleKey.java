package com.sorin.simplecart.bean.userrole;

import java.io.Serializable;

/**
 * 用户角色关联表主键
 *
 * @author LSD
 * @date 2019/06/20
 **/
public class UserRoleKey implements Serializable {

    private String userId;

    private String roleId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}

package com.sorin.simplecart.bean.userrole;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 用户角色关联
 *
 * @author LSD
 * @date 2019/06/20
 **/
@Entity
@IdClass(UserRoleKey.class)
@Table(name = "user_role")
public class UserRole  implements Serializable {

    @Id
    @Column(length = 32)
    private String userId;

    @Id
    @Column(length = 32)
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

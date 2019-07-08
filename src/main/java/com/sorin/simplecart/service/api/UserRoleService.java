package com.sorin.simplecart.service.api;

import com.sorin.simplecart.bean.userrole.UserRole;

import java.util.List;

/**
 * 用户角色service
 *
 * @author LSD
 * @date 2019/06/20
 **/
public interface UserRoleService {

    /**
     * 查看用户的角色
     *
     * @param userId
     * @return java.util.List<com.sorin.simplecart.bean.userrole.UserRole>
     * @author LSD
     * @date 2019/7/5
     */
    List<UserRole> selectByUserId(String userId);

    /**
     * 查看角色给了哪些用户
     *
     * @param roleId
     * @return java.util.List<com.sorin.simplecart.bean.userrole.UserRole>
     * @author LSD
     * @date 2019/7/5
     */
    List<UserRole> selectByRoleId(String roleId);

    /**
     * 新增
     *
     * @param userRole
     * @return void
     * @author LSD
     * @date 2019/6/20
     */
    void add(UserRole userRole);

    /**
     * 删除
     *
     * @param userRole
     * @return void
     * @author LSD
     * @date 2019/6/20
     */
    void delete(UserRole userRole);
}

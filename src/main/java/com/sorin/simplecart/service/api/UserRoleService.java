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
     * 查询用户角色
     *
     * @param userId
     * @param roleId
     * @return java.util.List<com.sorin.simplecart.bean.userrole.UserRole>
     * @author LSD
     * @date 2019/6/20
     */
    List<UserRole> selectByUserOrRole(String userId, String roleId);

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

package com.sorin.simplecart.service.api;

import com.sorin.simplecart.bean.rolepermission.RolePermission;

import java.util.List;

/**
 * 角色权限service
 *
 * @author LSD
 * @date 2019/06/20
 **/
public interface RolePermissionService {

    /**
     * 查询角色权限
     *
     * @param roleId
     * @param permissonId
     * @return com.sorin.simplecart.bean.rolepermission.RolePermission
     * @author LSD
     * @date 2019/6/20
     */
    List<RolePermission> selectByRoleOrPermission(String roleId, String permissonId);

    /**
     * 新增
     *
     * @param rolePermission
     * @author LSD
     * @date 2019/6/20
     */
    void add(RolePermission rolePermission);

    /**
     * 删除
     *
     * @param rolePermission
     * @author LSD
     * @date 2019/6/20
     */
    void delete(RolePermission rolePermission);
}

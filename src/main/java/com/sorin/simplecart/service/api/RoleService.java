package com.sorin.simplecart.service.api;

import com.sorin.simplecart.bean.Role;

import java.util.List;

/**
 * 角色service
 *
 * @author LSD
 * @date 2019/06/20
 **/
public interface RoleService {

    /**
     * 查询全部角色
     *
     * @return java.util.List<com.sorin.simplecart.bean.Role>
     * @author LSD
     * @date 2019/6/20
     */
    List<Role> selectAll();

    /**
     * id获取角色
     *
     * @param id
     * @return com.sorin.simplecart.bean.Role
     * @author LSD
     * @date 2019/6/20
     */
    Role selectByPrimaryKey(String id);

    /**
     * 名称获取角色
     *
     * @param name
     * @return java.util.List<com.sorin.simplecart.bean.Role>
     * @author LSD
     * @date 2019/7/3
     */
    List<Role> selectByName(String name);

    /**
     * 新增或修改
     *
     * @param role
     * @author LSD
     * @date 2019/6/20
     */
    void add(Role role);

    /**
     * 删除
     *
     * @param role
     * @author LSD
     * @date 2019/6/20
     */
    void delete(Role role);
}

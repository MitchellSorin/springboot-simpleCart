package com.sorin.simplecart.service.api;

import com.sorin.simplecart.bean.Permission;

import java.util.List;

/**
 * 权限service
 *
 * @author LSD
 * @date 2019/06/20
 **/
public interface PermissionService {

    /**
     * 查询全部权限
     *
     * @return java.util.List<com.sorin.simplecart.bean.Permission>
     * @author LSD
     * @date 2019/6/20
     */
    List<Permission> selectAll();

    /**
     * id获取权限
     *
     * @param id
     * @return com.sorin.simplecart.bean.Permission
     * @author LSD
     * @date 2019/6/20
     */
    Permission selectByPrimaryKey(String id);

    /**
     * 名称获取权限
     *
     * @return java.util.List<com.sorin.simplecart.bean.Permission>
     * @author LSD
     * @date 2019/7/3
     */
    List<Permission> selectByName(String name);

    /**
     * 新增或修改权限
     *
     * @param permission
     * @author LSD
     * @date 2019/6/20
     */
    void add(Permission permission);

    /**
     * 删除权限
     *
     * @param permission
     * @author LSD
     * @date 2019/6/20
     */
    void delete(Permission permission);

    /**
     * 判断URI是否需要权限
     *
     * @param requestURI
     * @return boolean
     * @author LSD
     * @date 2019/7/2
     */
    boolean needInterceptor(String requestURI);

    /**
     * 获取用户权限内的URL
     *
     * @param userName
     * @return java.util.List<java.lang.String>
     * @author LSD
     * @date 2019/7/2
     */
    List<String> listPermissibleURLs(String userName);
}

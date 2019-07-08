package com.sorin.simplecart.controller;

import com.sorin.simplecart.baseresult.BaseResult;
import com.sorin.simplecart.baseresult.BaseResultConstant;
import com.sorin.simplecart.bean.rolepermission.RolePermission;
import com.sorin.simplecart.service.api.RolePermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 角色权限controller
 *
 * @author LSD
 * @date 2019/07/05
 **/
@RestController
@RequestMapping("/manage/rolePermission")
@Api(tags = "角色权限", description = "CRUD")
public class RolePermissionController {
    private static final Logger logger = LoggerFactory.getLogger(RolePermissionController.class);

    @Autowired
    private RolePermissionService rolePermissionService;

    @RequestMapping(value = "selectByRoleId", method = RequestMethod.GET)
    @ApiOperation(value = "查询角色的权限")
    public Object selectByRoleId(
            @RequestParam(value = "roleId") String roleId
    ) {
        try {
            return new BaseResult(BaseResultConstant.SUCCESS, rolePermissionService.selectByRoleOrPermission(roleId, null));
        } catch (Exception e) {
            logger.error("RolePermissionController.selectByRoleId--error:", e);
            return new BaseResult(BaseResultConstant.FAILED, null);
        }
    }

    @RequestMapping(value = "selectByPermissionId", method = RequestMethod.GET)
    @ApiOperation(value = "查询权限给力哪些角色")
    public Object selectByPermissionId(
            @RequestParam(value = "permissionId") String permissionId
    ) {
        try {
            return new BaseResult(BaseResultConstant.SUCCESS, rolePermissionService.selectByRoleOrPermission(null, permissionId));
        } catch (Exception e) {
            logger.error("RolePermissionController.selectByPermissionId--error:", e);
            return new BaseResult(BaseResultConstant.FAILED, null);
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation(value = "新增角色权限关系")
    public Object add(
            @RequestParam(value = "roleId") String roleId,
            @RequestParam(value = "permissionId") String permissionId
    ) {
        try {
            RolePermission rolePermission = new RolePermission();
            rolePermission.setPermissionId(permissionId);
            rolePermission.setRoleId(roleId);
            rolePermissionService.add(rolePermission);
            return new BaseResult(BaseResultConstant.SUCCESS, null);
        } catch (Exception e) {
            logger.error("RolePermissionController.add--error:", e);
            return new BaseResult(BaseResultConstant.FAILED, null);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @ApiOperation(value = "删除角色权限关系")
    public Object delete(
            @RequestParam(value = "roleId") String roleId,
            @RequestParam(value = "permissionId") String permissionId
    ) {
        try {
            RolePermission rolePermission = new RolePermission();
            rolePermission.setPermissionId(permissionId);
            rolePermission.setRoleId(roleId);
            rolePermissionService.delete(rolePermission);
            return new BaseResult(BaseResultConstant.SUCCESS, null);
        } catch (Exception e) {
            logger.error("RolePermissionController.delete--error:", e);
            return new BaseResult(BaseResultConstant.FAILED, null);
        }
    }
}

package com.sorin.simplecart.controller;

import com.sorin.simplecart.baseresult.BaseResult;
import com.sorin.simplecart.baseresult.BaseResultConstant;
import com.sorin.simplecart.bean.rolepermission.RolePermission;
import com.sorin.simplecart.service.api.RolePermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private RolePermissionService rolePermissionService;

    @GetMapping("selectByRoleId")
    @ApiOperation(value = "查询角色的权限")
    public Object selectByRoleId(
            @RequestParam(value = "roleId") String roleId
    ) {
        return new BaseResult<>(rolePermissionService.selectByRoleOrPermission(roleId, null));
    }

    @GetMapping("selectByPermissionId")
    @ApiOperation(value = "查询权限给力哪些角色")
    public Object selectByPermissionId(
            @RequestParam(value = "permissionId") String permissionId
    ) {
        return new BaseResult<>(rolePermissionService.selectByRoleOrPermission(null, permissionId));
    }

    @PostMapping
    @ApiOperation(value = "新增角色权限关系")
    public Object add(
            @RequestParam(value = "roleId") String roleId,
            @RequestParam(value = "permissionId") String permissionId
    ) {
        RolePermission rolePermission = new RolePermission();
        rolePermission.setPermissionId(permissionId);
        rolePermission.setRoleId(roleId);
        rolePermissionService.add(rolePermission);
        return new BaseResult<>(BaseResultConstant.SUCCESS);
    }

    @DeleteMapping
    @ApiOperation(value = "删除角色权限关系")
    public Object delete(
            @RequestParam(value = "roleId") String roleId,
            @RequestParam(value = "permissionId") String permissionId
    ) {
        RolePermission rolePermission = new RolePermission();
        rolePermission.setPermissionId(permissionId);
        rolePermission.setRoleId(roleId);
        rolePermissionService.delete(rolePermission);
        return new BaseResult<>(BaseResultConstant.SUCCESS);
    }
}

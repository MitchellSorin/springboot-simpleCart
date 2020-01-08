package com.sorin.simplecart.controller;

import com.sorin.simplecart.baseresult.BaseResult;
import com.sorin.simplecart.baseresult.BaseResultConstant;
import com.sorin.simplecart.bean.userrole.UserRole;
import com.sorin.simplecart.service.api.UserRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 用户角色controller
 *
 * @author LSD
 * @date 2019/07/05
 **/
@RestController
@RequestMapping("/manage/userRole")
@Api(tags = "用户角色", description = "CRUD")
public class UserRoleController {

    @Autowired
    private UserRoleService userRoleService;

    @GetMapping("selectByUserId")
    @ApiOperation(value = "查询用户的角色")
    public Object selectByUserId(
            @RequestParam(value = "userId") String userId
    ) {
        return new BaseResult<>(userRoleService.selectByUserId(userId));
    }

    @GetMapping("selectByRoleId")
    @ApiOperation(value = "查询角色给了哪些用户")
    public Object selectByRoleId(
            @RequestParam(value = "roleId") String roleId
    ) {
        return new BaseResult<>(userRoleService.selectByRoleId(roleId));
    }

    @PostMapping
    @ApiOperation(value = "新增用户角色关联")
    public Object add(
            @RequestParam(value = "userId") String userId,
            @RequestParam(value = "roleId") String roleId
    ) {
        UserRole userRole = new UserRole();
        userRole.setUserId(userId);
        userRole.setRoleId(roleId);
        userRoleService.add(userRole);
        return new BaseResult<>(BaseResultConstant.SUCCESS);
    }

    @DeleteMapping
    @ApiOperation(value = "删除用户角色关系")
    public Object delete(
            @RequestParam(value = "userId") String userId,
            @RequestParam(value = "roleId") String roleId
    ) {
        UserRole userRole = new UserRole();
        userRole.setUserId(userId);
        userRole.setRoleId(roleId);
        userRoleService.delete(userRole);
        return new BaseResult<>(BaseResultConstant.SUCCESS);
    }

}

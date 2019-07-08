package com.sorin.simplecart.controller;

import com.sorin.simplecart.baseresult.BaseResult;
import com.sorin.simplecart.baseresult.BaseResultConstant;
import com.sorin.simplecart.bean.userrole.UserRole;
import com.sorin.simplecart.service.api.UserRoleService;
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
 * 用户角色controller
 *
 * @author LSD
 * @date 2019/07/05
 **/
@RestController
@RequestMapping("/manage/userRole")
@Api(tags = "用户角色", description = "CRUD")
public class UserRoleController {
    private static final Logger logger = LoggerFactory.getLogger(UserRoleController.class);

    @Autowired
    private UserRoleService userRoleService;

    @RequestMapping(value = "selectByUserId", method = RequestMethod.GET)
    @ApiOperation(value = "查询用户的角色")
    public Object selectByUserId(
            @RequestParam(value = "userId") String userId
    ) {
        try {
            return new BaseResult(BaseResultConstant.SUCCESS, userRoleService.selectByUserId(userId));
        } catch (Exception e) {
            logger.error("UserRoleController.selectByUserId--error:", e);
            return new BaseResult(BaseResultConstant.FAILED, null);
        }
    }

    @RequestMapping(value = "selectByRoleId", method = RequestMethod.GET)
    @ApiOperation(value = "查询角色给了哪些用户")
    public Object selectByRoleId(
            @RequestParam(value = "roleId") String roleId
    ) {
        try {
            return new BaseResult(BaseResultConstant.SUCCESS, userRoleService.selectByRoleId(roleId));
        } catch (Exception e) {
            logger.error("UserRoleController.selectByRoleId--error:", e);
            return new BaseResult(BaseResultConstant.FAILED, null);
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation(value = "新增用户角色关联")
    public Object add(
            @RequestParam(value = "userId") String userId,
            @RequestParam(value = "roleId") String roleId
    ) {
        try {
            UserRole userRole = new UserRole();
            userRole.setUserId(userId);
            userRole.setRoleId(roleId);
            userRoleService.add(userRole);
            return new BaseResult(BaseResultConstant.SUCCESS, null);
        } catch (Exception e) {
            logger.error("UserRoleController.add--error:", e);
            return new BaseResult(BaseResultConstant.FAILED, null);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @ApiOperation(value = "删除用户角色关系")
    public Object delete(
            @RequestParam(value = "userId") String userId,
            @RequestParam(value = "roleId") String roleId
    ) {
        try {
            UserRole userRole = new UserRole();
            userRole.setUserId(userId);
            userRole.setRoleId(roleId);
            userRoleService.delete(userRole);
            return new BaseResult(BaseResultConstant.SUCCESS, null);
        } catch (Exception e) {
            logger.error("UserRoleController.delete--error:", e);
            return new BaseResult(BaseResultConstant.FAILED, null);
        }
    }

}

package com.sorin.simplecart.controller;

import com.sorin.simplecart.baseresult.BaseResult;
import com.sorin.simplecart.baseresult.BaseResultConstant;
import com.sorin.simplecart.bean.Role;
import com.sorin.simplecart.service.api.RoleService;
import com.sorin.simplecart.utils.StringUtils;
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
 * 角色管理
 *
 * @author LSD
 * @date 2019/07/03
 **/
@RestController
@RequestMapping("/manage/role")
@Api(tags = "角色", description = "CRUD")
public class RoleController {
    private static final Logger logger = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private RoleService roleService;

    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = "查询全部")
    public Object select(
    ) {
        try {
            return new BaseResult(BaseResultConstant.SUCCESS, roleService.selectAll());
        } catch (Exception e) {
            logger.error("RoleController.select--error:", e);
            return new BaseResult(BaseResultConstant.FAILED, null);
        }
    }

    @RequestMapping(value = "selectByName", method = RequestMethod.GET)
    @ApiOperation(value = "通过名称查询")
    public Object selectByName(
            @RequestParam(value = "name") String name
    ) {
        try {
            return new BaseResult(BaseResultConstant.SUCCESS, roleService.selectByName(name));
        } catch (Exception e) {
            logger.error("RoleController.selectByName--error:", e);
            return new BaseResult(BaseResultConstant.FAILED, null);
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation(value = "新增或修改")
    public Object add(
            @RequestParam(required = false, value = "id") String id,
            @RequestParam(required = false, value = "desperation") String desperation,
            @RequestParam(value = "name") String name
    ) {
        try {
            Role role = new Role();
            role.setDescription(desperation);
            role.setName(name);
            if (StringUtils.isBlank(id)) {
                id = StringUtils.random(32);
            }
            role.setId(id);
            roleService.add(role);
            return new BaseResult(BaseResultConstant.SUCCESS, null);
        } catch (Exception e) {
            logger.error("RoleController.add--error:", e);
            return new BaseResult(BaseResultConstant.FAILED, null);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @ApiOperation(value = "删除")
    public Object delete(
            @RequestParam(value = "id") String id
    ) {
        try {
            Role role = new Role();
            role.setId(id);
            roleService.delete(role);
            return new BaseResult(BaseResultConstant.SUCCESS, null);
        } catch (Exception e) {
            logger.error("RoleController.add--error:", e);
            return new BaseResult(BaseResultConstant.FAILED, null);
        }
    }

}

package com.sorin.simplecart.controller;

import com.sorin.simplecart.baseresult.BaseResult;
import com.sorin.simplecart.baseresult.BaseResultConstant;
import com.sorin.simplecart.bean.Permission;
import com.sorin.simplecart.service.api.PermissionService;
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
 * 权限管理
 *
 * @author LSD
 * @date 2019/07/03
 **/
@RestController
@RequestMapping("/manage/permission")
@Api(tags = "权限管理", description = "CRUD")
public class PermissionController {

    private static final Logger logger = LoggerFactory.getLogger(PermissionController.class);

    @Autowired
    private PermissionService permissionService;

    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = "查询全部")
    public Object select(
    ) {
        try {
            return new BaseResult(BaseResultConstant.SUCCESS, permissionService.selectAll());
        } catch (Exception e) {
            logger.error("PermissionController.select--error:", e);
            return new BaseResult(BaseResultConstant.FAILED, null);
        }
    }

    @RequestMapping(value = "selectByName", method = RequestMethod.GET)
    @ApiOperation(value = "通过名称查询")
    public Object selectByName(
            @RequestParam(value = "name") String name
    ) {
        try {
            return new BaseResult(BaseResultConstant.SUCCESS, permissionService.selectByName(name));
        } catch (Exception e) {
            logger.error("PermissionController.selectByName--error:", e);
            return new BaseResult(BaseResultConstant.FAILED, null);
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation(value = "新增或修改")
    public Object add(
            @RequestParam(required = false, value = "id") String id,
            @RequestParam(required = false, value = "desperation") String desperation,
            @RequestParam(value = "url") String url,
            @RequestParam(value = "name") String name
    ) {
        try {
            Permission permission = new Permission();
            permission.setDescription(desperation);
            permission.setId(id);
            permission.setName(name);
            permission.setUrl(url);
            return new BaseResult(BaseResultConstant.SUCCESS, null);
        } catch (Exception e) {
            logger.error("PermissionController.add--error:", e);
            return new BaseResult(BaseResultConstant.FAILED, null);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @ApiOperation(value = "删除")
    public Object delete(
            @RequestParam(value = "id") String id
    ) {
        try {
            Permission permission = new Permission();
            permission.setId(id);
            permissionService.delete(permission);
            return new BaseResult(BaseResultConstant.SUCCESS, null);
        } catch (Exception e) {
            logger.error("PermissionController.add--error:", e);
            return new BaseResult(BaseResultConstant.FAILED, null);
        }
    }

}

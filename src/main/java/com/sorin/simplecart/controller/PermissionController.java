package com.sorin.simplecart.controller;

import com.sorin.simplecart.baseresult.BaseResult;
import com.sorin.simplecart.baseresult.BaseResultConstant;
import com.sorin.simplecart.bean.Permission;
import com.sorin.simplecart.service.api.PermissionService;
import com.sorin.simplecart.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 权限管理
 *
 * @author LSD
 * @date 2019/07/03
 **/
@RestController
@RequestMapping("/manage/permission")
@Api(tags = "权限", description = "CRUD")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @GetMapping
    @ApiOperation(value = "查询全部")
    public Object select() {
        return new BaseResult<>(permissionService.selectAll());
    }

    @GetMapping("selectByName")
    @ApiOperation(value = "通过名称查询")
    public Object selectByName(
            @RequestParam(value = "name") String name
    ) {
        return new BaseResult<>(permissionService.selectByName(name));
    }

    @PostMapping
    @ApiOperation(value = "新增或修改")
    public Object add(
            @RequestParam(required = false, value = "id") String id,
            @RequestParam(required = false, value = "desperation") String desperation,
            @RequestParam(value = "url") String url,
            @RequestParam(value = "name") String name
    ) {
        Permission permission = new Permission();
        permission.setDescription(desperation);
        permission.setName(name);
        permission.setUrl(url);
        if (StringUtils.isBlank(id)) {
            id = StringUtils.random(32);
        }
        permission.setId(id);
        permissionService.add(permission);
        return new BaseResult<>(BaseResultConstant.SUCCESS);
    }

    @DeleteMapping
    @ApiOperation(value = "删除")
    public Object delete(
            @RequestParam(value = "id") String id
    ) {
        Permission permission = new Permission();
        permission.setId(id);
        permissionService.delete(permission);
        return new BaseResult<>(BaseResultConstant.SUCCESS);
    }

}

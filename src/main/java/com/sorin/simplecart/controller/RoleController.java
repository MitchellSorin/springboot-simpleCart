package com.sorin.simplecart.controller;

import com.sorin.simplecart.baseresult.BaseResult;
import com.sorin.simplecart.baseresult.BaseResultConstant;
import com.sorin.simplecart.bean.Role;
import com.sorin.simplecart.service.api.RoleService;
import com.sorin.simplecart.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private RoleService roleService;

    @GetMapping
    @ApiOperation(value = "查询全部")
    public Object select(
    ) {
        return new BaseResult<>(roleService.selectAll());
    }

    @GetMapping("selectByName")
    @ApiOperation(value = "通过名称查询")
    public Object selectByName(
            @RequestParam(value = "name") String name
    ) {
        return new BaseResult<>(roleService.selectByName(name));
    }

    @PostMapping
    @ApiOperation(value = "新增或修改")
    public Object add(
            @RequestParam(required = false, value = "id") String id,
            @RequestParam(required = false, value = "desperation") String desperation,
            @RequestParam(value = "name") String name
    ) {
        Role role = new Role();
        role.setDescription(desperation);
        role.setName(name);
        if (StringUtils.isBlank(id)) {
            id = StringUtils.random(32);
        }
        role.setId(id);
        roleService.add(role);
        return new BaseResult(BaseResultConstant.SUCCESS);
    }

    @DeleteMapping
    @ApiOperation(value = "删除")
    public Object delete(
            @RequestParam(value = "id") String id
    ) {
        Role role = new Role();
        role.setId(id);
        roleService.delete(role);
        return new BaseResult<>(BaseResultConstant.SUCCESS);
    }

}

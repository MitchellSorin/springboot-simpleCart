package com.sorin.simplecart.controller;

import com.sorin.simplecart.baseresult.BaseResult;
import com.sorin.simplecart.baseresult.BaseResultConstant;
import com.sorin.simplecart.bean.User;
import com.sorin.simplecart.service.api.UserServcie;
import com.sorin.simplecart.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * userController
 *
 * @author LSD
 * @date 2019/06/12
 **/
@RestController
@RequestMapping("/cart/user")
@Api(tags = "用户", description = "CRUD")
public class UserController {

    @Autowired
    private UserServcie userServcie;

    @GetMapping
    @ApiOperation(value = "查询")
    public Object select(
            @RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
            @RequestParam(required = false, defaultValue = "5", value = "limit") int limit,
            @RequestParam(required = false, value = "sort") String sort,
            @RequestParam(required = false, value = "order") String order,
            @RequestParam(required = false, value = "id") String id,
            @RequestParam(required = false, value = "name") String name
    ) {
        return new BaseResult<>(userServcie.select(offset, limit, sort, order, id, name));
    }

    @PostMapping
    @ApiOperation(value = "新增或修改")
    public Object add(
            @RequestParam(value = "name") String name,
            @RequestParam(value = "password") String password

    ) {
        User user = userServcie.selectByName(name);
        if (null != user) {
            String salt = user.getSalt();
            user.setPassword(new SimpleHash("md5", password, salt, 2).toString());
        } else {
            user = new User();
            user.setId(StringUtils.random(32));
            user.setName(name);
            String salt = StringUtils.random(16);
            user.setPassword(new SimpleHash("md5", password, salt, 2).toString());
            user.setSalt(salt);
        }
        userServcie.add(user);
        return new BaseResult<>(BaseResultConstant.SUCCESS);
    }

    @DeleteMapping
    @ApiOperation(value = "删除")
    public Object delete(
            @RequestParam(required = false, value = "name") String name
    ) {
        User user = userServcie.selectByName(name);
        if (null != user) {
            userServcie.delete(user);
        }
        return new BaseResult<>(BaseResultConstant.SUCCESS);
    }
}

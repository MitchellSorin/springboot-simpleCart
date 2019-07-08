package com.sorin.simplecart.controller;

import com.sorin.simplecart.baseresult.BaseResult;
import com.sorin.simplecart.baseresult.BaseResultConstant;
import com.sorin.simplecart.bean.User;
import com.sorin.simplecart.service.api.UserServcie;
import com.sorin.simplecart.utils.Page4Navigator;
import com.sorin.simplecart.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserServcie userServcie;

    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = "查询")
    public Object select(
            @RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
            @RequestParam(required = false, defaultValue = "5", value = "limit") int limit,
            @RequestParam(required = false, value = "sort") String sort,
            @RequestParam(required = false, value = "order") String order,
            @RequestParam(required = false, value = "id") String id,
            @RequestParam(required = false, value = "name") String name
    ) {
        try {
            Page4Navigator<User> users = userServcie.select(offset, limit, sort, order, id, name);
            return new BaseResult(BaseResultConstant.SUCCESS, users);
        } catch (Exception e) {
            logger.error("UserController.select--error:", e);
            return new BaseResult(BaseResultConstant.FAILED, null);
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation(value = "新增或修改")
    public Object add(
            @RequestParam(value = "name") String name,
            @RequestParam(value = "password") String password

    ) {
        try {
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
            return new BaseResult(BaseResultConstant.SUCCESS, null);
        } catch (Exception e) {
            logger.error("UserController.add--error:", e);
            return new BaseResult(BaseResultConstant.FAILED, null);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @ApiOperation(value = "删除")
    public Object delete(
            @RequestParam(required = false, value = "name") String name
    ) {
        try {
            User user = userServcie.selectByName(name);
            if (null != user) {
                userServcie.delete(user);
            }
            return new BaseResult(BaseResultConstant.SUCCESS, null);
        } catch (Exception e) {
            logger.error("UserController.delete--error:", e);
            return new BaseResult(BaseResultConstant.FAILED, null);
        }
    }
}

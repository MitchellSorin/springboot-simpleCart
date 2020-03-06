package com.sorin.simplecart.shiro;

import com.sorin.simplecart.baseresult.BaseResult;
import com.sorin.simplecart.baseresult.BaseResultConstant;
import com.sorin.simplecart.bean.User;
import com.sorin.simplecart.exception.CheckException;
import com.sorin.simplecart.service.api.UserServcie;
import com.sorin.simplecart.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 登录管理
 *
 * @author LSD
 * @date 2019/07/03
 **/
@RestController
@Api(tags = "登录", description = "登录系统")
public class AuthController {
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private UserServcie userServcie;

    @PostMapping(value = "/login")
    @ApiOperation(value = "登录")
    public Object login(
            @RequestParam(required = false, defaultValue = "superadmin", value = "userName") String userName,
            @RequestParam(required = false, defaultValue = "1", value = "password") String password
    ) {
        if (StringUtils.isBlank(userName) && StringUtils.isBlank(password)) {
            throw new CheckException("请先登录");
        }
        if (StringUtils.isBlank(userName) || StringUtils.isBlank(password)) {
            throw new CheckException("请输入正确的账户名密码");
        }
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            subject.logout();
        }
        User user = userServcie.selectByName(userName);
        if (null == user) {
            throw new CheckException("无此用户");
        }
        //登录
        UsernamePasswordToken token = new UsernamePasswordToken(user.getId(), password);
        subject.login(token);
        if (subject.isAuthenticated()) {
            return new BaseResult<>(BaseResultConstant.SUCCESS);
        } else {
            return new BaseResult<>(BaseResultConstant.FAILED);
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    @ApiOperation(value = "登出")
    public Object logout() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            subject.logout();
        }
        return new BaseResult<>(BaseResultConstant.SUCCESS);
    }

    @RequestMapping(value = "/unauthorized")
    public Object unauthorized() {
        return new BaseResult<>(BaseResultConstant.UN_AUTHORIZED);
    }
}

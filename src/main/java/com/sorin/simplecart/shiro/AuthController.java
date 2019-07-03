package com.sorin.simplecart.shiro;

import com.sorin.simplecart.baseresult.BaseResult;
import com.sorin.simplecart.baseresult.BaseResultConstant;
import com.sorin.simplecart.bean.User;
import com.sorin.simplecart.service.api.UserServcie;
import com.sorin.simplecart.utils.Page4Navigator;
import com.sorin.simplecart.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录管理
 *
 * @author LSD
 * @date 2019/07/03
 **/
@RestController
@Api(tags = "登录管理", description = "登录系统")
public class AuthController {
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private UserServcie userServcie;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @ApiOperation(value = "登录")
    public Object login(
            @RequestParam(required = false, value = "userName") String userName,
            @RequestParam(required = false, value = "password") String password
    ) {
        try {
            if (StringUtils.isBlank(userName) || StringUtils.isBlank(password)) {
                return new BaseResult(BaseResultConstant.UNLOGIN, null);
            }
            Subject subject = SecurityUtils.getSubject();
            if (subject.isAuthenticated()) {
                subject.logout();
            }
            //注册
            User user = userServcie.selectByName(userName);
            if (null == user) {
                user = new User();
                user.setName(userName);
                user.setId(StringUtils.random(32));
                String salt = StringUtils.random(16);
                user.setPassword(new SimpleHash("md5", password, salt, 2).toString());
                user.setSalt(salt);
                userServcie.add(user);
            }
            //登录
            UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
            subject.login(token);
            if (subject.isAuthenticated()) {
                return new BaseResult(BaseResultConstant.LOGINSUCCESS, null);
            } else {
                return new BaseResult(BaseResultConstant.LOGINFAIL, null);
            }
        } catch (Exception e) {
            logger.error("AuthController.login--error:", e);
            return new BaseResult(BaseResultConstant.LOGINFAIL, null);
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    @ApiOperation(value = "登出")
    public Object logout() {
        try {
            Subject subject = SecurityUtils.getSubject();
            if (subject.isAuthenticated()) {
                subject.logout();
            }
            return new BaseResult(BaseResultConstant.LOGOUTSUCCESS, null);
        } catch (Exception e) {
            return new BaseResult(BaseResultConstant.FAILED, null);
        }
    }

    @RequestMapping(value = "/unauthorized", method = RequestMethod.GET)
    public Object unauthorized() {
        return new BaseResult(BaseResultConstant.UNAUTHORIZED, null);
    }
}

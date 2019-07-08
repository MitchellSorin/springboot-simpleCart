package com.sorin.simplecart.shiro;

import com.sorin.simplecart.bean.Permission;
import com.sorin.simplecart.bean.User;
import com.sorin.simplecart.bean.userrole.UserRole;
import com.sorin.simplecart.service.api.UserRoleService;
import com.sorin.simplecart.service.api.UserServcie;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * shiro数据库配置
 *
 * @author LSD
 * @date 2019/06/24
 **/
public class DataBaseRealm extends AuthorizingRealm {
    @Autowired
    private UserServcie userServcie;
    @Autowired
    private UserRoleService userRoleService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String userId = (String) principalCollection.getPrimaryPrincipal();
        List<UserRole> userRoles = userRoleService.selectByUserId(userId);
        List<Permission> userPermissions = userServcie.getPermission(userId);
        Set<String> roles = new HashSet<>();
        Set<String> permissions = new HashSet<>();
        if (null != userRoles && userRoles.size() > 0) {
            for (UserRole userRole : userRoles) {
                roles.add(userRole.getRoleId());
            }
        }
        if (null != userPermissions && userPermissions.size() > 0) {
            for (Permission permission : userPermissions) {
                permissions.add(permission.getId());
            }
        }
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setRoles(roles);
        simpleAuthorizationInfo.setStringPermissions(permissions);
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获取账号密码
        UsernamePasswordToken t = (UsernamePasswordToken) token;
        String userId = t.getUsername();
        //获取数据库中密码
        User user = userServcie.selectById(userId);
        if (null == user) {
            throw new AuthenticationException();
        }
        String passwordInDB = user.getPassword();
        String salt = user.getSalt();
        return new SimpleAuthenticationInfo(userId, passwordInDB, ByteSource.Util.bytes(salt), super.getName());
    }
}

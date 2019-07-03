package com.sorin.simplecart.shiro;

import com.sorin.simplecart.service.api.PermissionService;
import com.sorin.simplecart.spring.SpringContextUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.util.List;

/**
 * url权限过滤
 *
 * @author LSD
 * @date 2019/07/02
 **/
public class URLPathMatchingFilter extends PathMatchingFilter {

    @Autowired
    PermissionService permissionService;

    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        if (null == permissionService) {
            permissionService = SpringContextUtils.getContext().getBean(PermissionService.class);
        }
        Subject subject = SecurityUtils.getSubject();
        //未登录，返回未登录提示
        if (!subject.isAuthenticated()) {
            WebUtils.issueRedirect(request, response, "/login");
            return false;
        }
        //判断这个路径是否需要权限
        String url = super.getPathWithinApplication(request);
        if (permissionService.needInterceptor(url)) {
            String userName = subject.getPrincipal().toString();
            List<String> list = permissionService.listPermissibleURLs(userName);
            if (null != list && list.size() > 0) {
                for (String myUrl : list) {
                    if (url.equals(myUrl)) {
                        return true;
                    }
                }
            }
            UnauthorizedException exception = new UnauthorizedException("用户" + userName + "没有访问" + url + "的权限");
            subject.getSession().setAttribute("exception", exception);
            WebUtils.issueRedirect(request, response, "/unauthorized");
            return false;
        } else {
            return true;
        }
    }
}

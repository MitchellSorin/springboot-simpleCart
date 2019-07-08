package com.sorin.simplecart.shiro;

import com.sorin.simplecart.service.api.PermissionService;
import com.sorin.simplecart.spring.SpringContextUtils;
import com.sorin.simplecart.utils.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import springfox.documentation.service.ApiListing;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        String method = ((HttpServletRequest) request).getMethod();
        String urlMethod = url + "==" + method;
        if (permissionService.needInterceptor(urlMethod)) {
            String userId = subject.getPrincipal().toString();
            List<String> list = permissionService.listPermissibleURLs(userId);
            if (null != list && list.size() > 0) {
                for (String myUrl : list) {
                    if (StringUtils.isNotBlank(myUrl)) {
                        String[] split = myUrl.split("==");
                        if (split.length > 1) {
                            //带请求方法
                            if (urlMethod.equals(myUrl)) {
                                return true;
                            }
                        } else {
                            //只有url
                            if (url.equals(myUrl)) {
                                return true;
                            }
                        }
                    }
                }
            }
            UnauthorizedException exception = new UnauthorizedException("用户：" + userId + "没有访问" + url + "的权限");
            subject.getSession().setAttribute("exception", exception);
            WebUtils.issueRedirect(request, response, "/unauthorized");
            return false;
        } else {
            return true;
        }
    }
}

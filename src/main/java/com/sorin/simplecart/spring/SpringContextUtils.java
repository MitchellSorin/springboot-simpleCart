package com.sorin.simplecart.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * spring工具
 *
 * @author LSD
 * @date 2019/07/03
 **/
@Component
public class SpringContextUtils implements ApplicationContextAware {
    @Autowired
    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextUtils.context = applicationContext;
    }

    public static ApplicationContext getContext() {
        return context;
    }
}

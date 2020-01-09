package com.sorin.simplecart.aop;

import com.sorin.simplecart.baseresult.BaseResult;
import com.sorin.simplecart.baseresult.BaseResultConstant;
import com.sorin.simplecart.exception.CheckException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

/**
 * controller的日志和异常处理
 *
 * @author LSD
 * @date 2020/01/08
 **/
@Aspect
@Configuration
public class ControllerAOP {
    private static final Logger log = LoggerFactory.getLogger(ControllerAOP.class);

    // 打印耗时日志
    @Around("execution(* com.sorin.simplecart.controller..*.*(..)) " +
            "|| execution(* com.sorin.simplecart.shiro.AuthController.*(..)) " +
            "|| execution(* com.sorin.simplecart.websocket.WsTestController.*(..))")
    public BaseResult<?> handlerControllerMethod(ProceedingJoinPoint joinPoint) {
        long startTime = System.currentTimeMillis();
        BaseResult<?> result;
        log.info(joinPoint.getSignature() + " begin");
        try {
            result = (BaseResult<?>) joinPoint.proceed();
        } catch (Throwable throwable) {
            result = handlerControllerException(joinPoint, throwable);
        }
        log.info(joinPoint.getSignature() + " use time " + (System.currentTimeMillis() - startTime) + "ms");
        log.info(joinPoint.getSignature() + " end");
        return result;
    }

    // 处理异常
    private BaseResult<?> handlerControllerException(ProceedingJoinPoint joinPoint, Throwable e) {
        BaseResult<?> result;

        if (e instanceof CheckException) {
            result = new BaseResult<>(BaseResultConstant.FAILED);
            result.setMessage(e.getLocalizedMessage());
        } else {
            result = new BaseResult<>(BaseResultConstant.FAILED);
            log.error(joinPoint.getSignature() + " error:" + e);
            // 处理未知异常
        }
        return result;
    }
}

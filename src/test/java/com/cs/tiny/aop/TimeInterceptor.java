package com.cs.tiny.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @author benjaminChan
 * @date 2018/10/16 0016 下午 2:06
 */
public class TimeInterceptor implements MethodInterceptor{

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        long startTime = System.nanoTime();
        System.out.println(methodInvocation.getMethod().getName() + " start at:" + startTime);
        Object proceed = methodInvocation.proceed();
        long endTime = System.nanoTime();
        System.out.println(methodInvocation.getMethod().getName() + " end at:" + endTime + " execute time:" + (endTime - startTime));
        return proceed;
    }
}

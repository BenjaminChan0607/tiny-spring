package com.cs.tiny.aop;

/**
 * @author benjaminChan
 * @date 2018/10/17 0017 上午 11:50
 */
public interface Pointcut {

    ClassFilter getClassFilter();

    MethodMatcher getMethodMatcher();
}

package com.cs.tiny.aop;

import java.lang.reflect.Method;

/**
 * @author benjaminChan
 * @date 2018/10/16 0016 上午 11:47
 */
public interface MethodMatcher {

    boolean matches(Method method, Class targetClass);
}

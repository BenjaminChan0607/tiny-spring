package com.cs.tiny.aop;

/**
 * @author benjaminChan
 * @date 2018/10/16 0016 上午 11:44
 * <p>
 * 被代理的对象
 */
public class TargetSource {
    private Object target;
    private Class<?> targetClass;
    private Class<?>[] interfaces;

    public Object getTarget() {
        return target;
    }

    public Class<?> getTargetClass() {
        return targetClass;
    }

    public Class<?>[] getInterfaces() {
        return interfaces;
    }

    public TargetSource(Object target, Class<?> targetClass, Class<?>... interfaces) {
        this.target = target;
        this.targetClass = targetClass;
        this.interfaces = interfaces;
    }
}

package com.cs.tiny.beans;

/**
 * @author benjaminChan
 * @date 2018/10/15 0015 下午 2:25
 */
public interface BeanPostProcessor {

    Object postProcessBeforeInitialization(Object bean, String beanName) throws Exception;

    Object postProcessAfterInitialization(Object bean, String beanName) throws Exception;
}

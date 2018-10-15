package com.cs.tiny.beans;

/**
 * @author benjaminChan
 * @date 2018/10/15 0015 上午 11:07
 */
public interface BeanDefinitionReader {
    void loadBeanDefinitions(String location) throws Exception;
}

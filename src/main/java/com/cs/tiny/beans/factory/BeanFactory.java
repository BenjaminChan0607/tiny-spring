package com.cs.tiny.beans.factory;

/**
 * @author benjaminChan
 * @date 2018/10/15 0015 下午 2:07
 */
public interface BeanFactory {

    Object getBean(String name) throws Exception;
}
package com.cs.tiny.beans;

import com.cs.tiny.beans.factory.BeanFactory;

/**
 * @author benjaminChan
 * @date 2018/10/15 0015 下午 4:45
 */
public interface BeanFactoryAware {
    void setBeanFactory(BeanFactory beanFactory) throws Exception;
}

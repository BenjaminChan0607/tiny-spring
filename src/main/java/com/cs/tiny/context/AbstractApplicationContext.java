package com.cs.tiny.context;

import com.cs.tiny.beans.BeanPostProcessor;
import com.cs.tiny.beans.factory.AbstractBeanFactory;

import java.util.List;

/**
 * @author benjaminChan
 * @date 2018/10/15 0015 下午 7:09
 */
public abstract class AbstractApplicationContext implements ApplicationContext {
    protected AbstractBeanFactory beanFactory;

    public AbstractApplicationContext(AbstractBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public void refresh() throws Exception {
        loadBeanDefinitions(beanFactory);
        registerBeanPostProcessors(beanFactory);
        onRefresh();
    }

    private void onRefresh() throws Exception {
        beanFactory.preInstantiateSingletons();
    }

    protected void registerBeanPostProcessors(AbstractBeanFactory beanFactory) throws Exception {
        List beanPostProcessors = beanFactory.getBeansForType(BeanPostProcessor.class);
        for (Object beanPostProcessor : beanPostProcessors) {
            beanFactory.addBeanPostProcessor((BeanPostProcessor) beanPostProcessor);
        }
    }

    protected abstract void loadBeanDefinitions(AbstractBeanFactory beanFactory) throws Exception;

    @Override
    public Object getBean(String name) throws Exception {
        return beanFactory.getBean(name);
    }
}



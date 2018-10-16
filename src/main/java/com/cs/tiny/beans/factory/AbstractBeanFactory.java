package com.cs.tiny.beans.factory;

import com.cs.tiny.beans.BeanDefinition;
import com.cs.tiny.beans.BeanPostProcessor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author benjaminChan
 * @date 2018/10/15 0015 下午 2:08
 */
public abstract class AbstractBeanFactory implements BeanFactory {

    private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String, BeanDefinition>();
    private List<String> beanDefinitionNames = new ArrayList<String>();
    private List<BeanPostProcessor> beanPostProcessors = new ArrayList<BeanPostProcessor>();

    @Override
    public Object getBean(String name) throws Exception {
        BeanDefinition beanDefinition = beanDefinitionMap.get(name);
        if (beanDefinition == null) {
            throw new IllegalArgumentException("No bean named" + name + "is defined");
        }
        Object bean = beanDefinition.getBean();
        if (bean == null) {
            bean = doCreateBean(beanDefinition);
            bean = initializeBean(bean, name);
            beanDefinition.setBean(bean);
        }
        return bean;
    }

    protected Object initializeBean(Object bean, String name) throws Exception {
        for (BeanPostProcessor beanPostProcessor : beanPostProcessors) {
            bean = beanPostProcessor.postProcessBeforeInitialization(bean, name);
        }
        for (BeanPostProcessor beanPostProcessor : beanPostProcessors) {
            bean = beanPostProcessor.postProcessAfterInitialization(bean, name);
        }
        return bean;
    }

    protected Object doCreateBean(BeanDefinition beanDefinition) throws Exception {
        Object bean = createBeanInstance(beanDefinition);
        beanDefinition.setBean(bean);
        applyPropertyValues(bean, beanDefinition);
        return bean;
    }

    protected void applyPropertyValues(Object bean, BeanDefinition beanDefinition) throws Exception {

    }

    private Object createBeanInstance(BeanDefinition beanDefinition) throws IllegalAccessException, InstantiationException {
        return beanDefinition.getBeanClass().newInstance();
    }

    public void registerBeanDefinition(String name, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(name, beanDefinition);
        beanDefinitionNames.add(name);
    }

    public List getBeansForType(Class type) throws Exception {
        List beans = new ArrayList<Object>();
        for (String beanDefinitionName : beanDefinitionNames) {
            if (type.isAssignableFrom(beanDefinitionMap.get(beanDefinitionName).getBeanClass())) {
                beans.add(getBean(beanDefinitionName));
            }
        }
        return beans;
    }

    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
        beanPostProcessors.add(beanPostProcessor);
    }

    public void preInstantiateSingletons() throws Exception {
        for (Iterator iterator = beanDefinitionNames.iterator();iterator.hasNext();) {
            String beanName = (String) iterator.next();
            getBean(beanName);
        }
    }
    /**
     public void preInstantiateSingletons() throws Exception {
     for (Iterator it = this.beanDefinitionNames.iterator(); it.hasNext();) {
     String beanName = (String) it.next();
     getBean(beanName);
     }
     }
     }
     *
     * private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String, BeanDefinition>();
     private final List<String> beanDefinitionNames = new ArrayList<String>();
     private List<BeanPostProcessor> beanPostProcessors = new ArrayList<BeanPostProcessor>();
     @Override public Object getBean(String name) throws Exception {
     BeanDefinition beanDefinition = beanDefinitionMap.get(name);
     if (beanDefinition == null) {
     throw new IllegalArgumentException("No bean named " + name + " is defined");
     }
     Object bean = beanDefinition.getBean();
     if (bean == null) {
     bean = doCreateBean(beanDefinition);
     bean = initializeBean(bean, name);
     beanDefinition.setBean(bean);
     }
     return bean;
     }

     protected Object initializeBean(Object bean, String name) throws Exception {
     for (BeanPostProcessor beanPostProcessor : beanPostProcessors) {
     bean = beanPostProcessor.postProcessBeforeInitialization(bean, name);
     }
     for (BeanPostProcessor beanPostProcessor : beanPostProcessors) {
     bean = beanPostProcessor.postProcessAfterInitialization(bean, name);
     }
     return bean;
     }

     protected Object createBeanInstance(BeanDefinition beanDefinition) throws Exception {
     return beanDefinition.getBeanClass().newInstance();
     }

     public void registerBeanDefinition(String name, BeanDefinition beanDefinition) throws Exception {
     beanDefinitionMap.put(name, beanDefinition);
     beanDefinitionNames.add(name);
     }

     public void preInstantiateSingletons() throws Exception {
     for (Iterator it = this.beanDefinitionNames.iterator(); it.hasNext();) {
     String beanName = (String) it.next();
     getBean(beanName);
     }
     }

     protected Object doCreateBean(BeanDefinition beanDefinition) throws Exception {
     Object bean = createBeanInstance(beanDefinition);
     beanDefinition.setBean(bean);
     applyPropertyValues(bean, beanDefinition);
     return bean;
     }

     protected void applyPropertyValues(Object bean, BeanDefinition beanDefinition) throws Exception {

     }

     public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) throws Exception {
     this.beanPostProcessors.add(beanPostProcessor);
     }

     public List getBeansForType(Class type) throws Exception {
     List beans = new ArrayList<Object>();
     for (String beanDefinitionName : beanDefinitionNames) {
     if (type.isAssignableFrom(beanDefinitionMap.get(beanDefinitionName).getBeanClass())) {
     beans.add(getBean(beanDefinitionName));
     }
     }
     return beans;
     }
     */
}

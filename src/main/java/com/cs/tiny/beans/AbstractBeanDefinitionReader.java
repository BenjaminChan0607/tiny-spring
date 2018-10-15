package com.cs.tiny.beans;

import com.cs.tiny.beans.io.ResourceLoader;

import java.util.HashMap;
import java.util.Map;

/**
 * @author benjaminChan
 * @date 2018/10/15 0015 上午 11:08
 */
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader{

    private Map<String,BeanDefinition> registry;

    private ResourceLoader resourceLoader;

    public AbstractBeanDefinitionReader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
        this.registry = new HashMap<String, BeanDefinition>();
    }

    public Map<String, BeanDefinition> getRegistry() {
        return registry;
    }

    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }
}

package com.cs.tiny.context;

import com.cs.tiny.beans.BeanDefinition;
import com.cs.tiny.beans.factory.AbstractBeanFactory;
import com.cs.tiny.beans.factory.AutowireCapableBeanFactory;
import com.cs.tiny.beans.io.ResourceLoader;
import com.cs.tiny.beans.xml.XmlBeanDefinitionReader;

import java.util.Map;

/**
 * @author benjaminChan
 * @date 2018/10/15 0015 下午 8:08
 */
public class ClassPathXmlApplicationContext extends AbstractApplicationContext {

    private String configLocation;

    public ClassPathXmlApplicationContext(String configLocation) throws Exception {
        this(configLocation,new AutowireCapableBeanFactory());
    }

    public ClassPathXmlApplicationContext(String configLocation, AbstractBeanFactory beanFactory) throws Exception {
        super(beanFactory);
        this.configLocation = configLocation;
        refresh();
    }

    @Override
    protected void loadBeanDefinitions(AbstractBeanFactory beanFactory) throws Exception {
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(new ResourceLoader());
        xmlBeanDefinitionReader.loadBeanDefinitions(configLocation);
        for (Map.Entry<String, BeanDefinition> beanDefinitionEntry : xmlBeanDefinitionReader.getRegistry().entrySet()) {
            beanFactory.registerBeanDefinition(beanDefinitionEntry.getKey(),beanDefinitionEntry.getValue());
        }
    }
}
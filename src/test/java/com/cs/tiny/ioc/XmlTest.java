package com.cs.tiny.ioc;

import com.cs.tiny.HelloService;
import com.cs.tiny.beans.BeanDefinition;
import com.cs.tiny.beans.factory.AbstractBeanFactory;
import com.cs.tiny.beans.factory.AutowireCapableBeanFactory;
import com.cs.tiny.beans.io.Resource;
import com.cs.tiny.beans.io.ResourceLoader;
import com.cs.tiny.beans.xml.XmlBeanDefinitionReader;
import org.junit.Assert;
import org.junit.Test;

import java.io.InputStream;
import java.util.Map;

/**
 * @author benjaminChan
 * @date 2018/10/15 0015 上午 11:33
 */
public class XmlTest {

    private final String CONF_FILE_NAME = "tiny-ioc.xml";

    @Test
    public void testResourceLoader() throws Exception {
        ResourceLoader resourceLoader = new ResourceLoader();
        Resource resource = resourceLoader.getResources("tiny-ioc.xml");
        InputStream inputStream = resource.getInputStream();
        Assert.assertNotNull(inputStream);
    }

    @Test
    public void testXmlBeanDefinitionReader() throws Exception {
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(new ResourceLoader());
        xmlBeanDefinitionReader.loadBeanDefinitions(CONF_FILE_NAME);
        Map<String, BeanDefinition> registry = xmlBeanDefinitionReader.getRegistry();
        Assert.assertTrue(registry.size() > 0);
    }

    @Test
    public void testLazy() {
        try {
            //1，读取配置
            XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(new ResourceLoader());
            xmlBeanDefinitionReader.loadBeanDefinitions(CONF_FILE_NAME);
            //2，初始化BeanFactory并注册Bean
            AbstractBeanFactory beanFactory = new AutowireCapableBeanFactory();
            for (Map.Entry<String, BeanDefinition> beanDefinitionEntry : xmlBeanDefinitionReader.getRegistry().entrySet()) {
                beanFactory.registerBeanDefinition(beanDefinitionEntry.getKey(), beanDefinitionEntry.getValue());
            }
            //3，获取Bean
            HelloService helloService = (HelloService) beanFactory.getBean("helloService");
            helloService.hello();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testBasic() throws Exception {
        // 1.初始化beanfactory
        AbstractBeanFactory beanFactory = new AutowireCapableBeanFactory();

// 2.注入bean
        BeanDefinition beanDefinition = new BeanDefinition();
        beanDefinition.setBeanClassName("com.cs.tiny.HelloServiceImpl");
        beanFactory.registerBeanDefinition("helloService", beanDefinition);

// 3.获取bean
        HelloService helloService = (HelloService) beanFactory.getBean("helloService");
        helloService.hello();
    }
}

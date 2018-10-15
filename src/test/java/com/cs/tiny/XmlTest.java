package com.cs.tiny;

import com.cs.tiny.beans.BeanDefinition;
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

    @Test
    public void resourceLoaderTest() throws Exception {
        ResourceLoader resourceLoader = new ResourceLoader();
        Resource resource = resourceLoader.getResources("tiny-ioc.xml");
        InputStream inputStream = resource.getInputStream();
        Assert.assertNotNull(inputStream);
    }

    @Test
    public void xmlBeanDefinitionReaderTest() throws Exception {
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(new ResourceLoader());
        xmlBeanDefinitionReader.loadBeanDefinitions("tiny-ioc.xml");
        Map<String,BeanDefinition> registry = xmlBeanDefinitionReader.getRegistry();
        Assert.assertTrue(registry.size() > 0);
    }

    @Test
    public void testLazy() {

    }
}

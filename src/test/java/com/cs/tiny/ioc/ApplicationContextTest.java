package com.cs.tiny.ioc;

import com.cs.tiny.HelloService;
import com.cs.tiny.context.ApplicationContext;
import com.cs.tiny.context.ClassPathXmlApplicationContext;
import org.junit.Test;

/**
 * @author benjaminChan
 * @date 2018/10/15 0015 下午 7:05
 */
public class ApplicationContextTest {

    private final String CONF_FILE_NAME = "tiny-ioc.xml";

    @Test
    public void testApplicationContext() throws Exception {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(CONF_FILE_NAME);
        HelloService helloService = (HelloService) applicationContext.getBean("helloService");
        helloService.hello();
    }
}

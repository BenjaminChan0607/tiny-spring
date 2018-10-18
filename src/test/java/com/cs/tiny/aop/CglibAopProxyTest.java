package com.cs.tiny.aop;

import com.cs.tiny.HelloService;
import com.cs.tiny.HelloServiceImpl;
import com.cs.tiny.constant.Constants;
import com.cs.tiny.context.ApplicationContext;
import com.cs.tiny.context.ClassPathXmlApplicationContext;
import org.junit.Test;

/**
 * @author benjaminChan
 * @date 2018/10/17 0017 上午 10:38
 */
public class CglibAopProxyTest {

    @Test
    public void testCglibAopProxy() throws Exception {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(Constants.CONF_FILE_NAME);
        HelloService helloService = (HelloService) applicationContext.getBean("helloService");
//        helloService.hello();
        //1.设置被代理的对象（JoinPoint）
        AdvisedSupport advisedSupport = new AdvisedSupport();
        advisedSupport.setTargetSource(new TargetSource(helloService, HelloServiceImpl.class, HelloService.class));
        //2.设置拦截器（Advice）
        TimeInterceptor timeInterceptor = new TimeInterceptor();
        advisedSupport.setMethodInterceptor(timeInterceptor);
        //3.创建代理（Proxy）
        CglibAopProxy cglibAopProxy = new CglibAopProxy(advisedSupport);
        HelloService helloServiceProxy = (HelloService) cglibAopProxy.getProxy();
        //4.基于Aop的调用
         helloServiceProxy.hello();
    }
}

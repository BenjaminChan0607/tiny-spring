package com.cs.tiny.aop;

import com.cs.tiny.HelloService;
import com.cs.tiny.HelloServiceImpl;
import com.cs.tiny.context.ApplicationContext;
import com.cs.tiny.context.ClassPathXmlApplicationContext;
import org.junit.Test;

/**
 * @author benjaminChan
 * @date 2018/10/16 0016 上午 11:36
 *
 * jdkDynamicAopProxy.getProxy() -> Proxy.newProxyInstance(ClassLoader classLoader,Class<?>[] interfaces,InvocationHandler invocationHandler)
 * helloServiceProxy.hello() -> JdkDynamicAopProxy invoke() ->  method.invoke(Object target,Object... args)
 */
public class JdkDynamicAopProxyTest {

    private final String CONF_FILE_NAME = "tiny-ioc.xml";

    @Test
    public void testJdkProxy() throws Exception {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(CONF_FILE_NAME);
        HelloService helloService = (HelloService) applicationContext.getBean("helloService");
//        helloService.hello();

        //1.设置被代理的对象（JoinPoint）
        AdvisedSupport advisedSupport = new AdvisedSupport();
        TargetSource targetSource = new TargetSource(helloService, HelloServiceImpl.class, HelloService.class);
        advisedSupport.setTargetSource(targetSource);
        //2.设置拦截器（Advice）
        TimeInterceptor timeInterceptor = new TimeInterceptor();
        advisedSupport.setMethodInterceptor(timeInterceptor);
        //3.创建代理（Proxy）
        JdkDynamicAopProxy jdkDynamicAopProxy = new JdkDynamicAopProxy(advisedSupport);
        HelloService helloServiceProxy = (HelloService) jdkDynamicAopProxy.getProxy();
        //4.基于Aop的调用
        helloServiceProxy.hello();
    }
}
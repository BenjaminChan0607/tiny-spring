package com.cs.tiny.aop;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author benjaminChan
 * @date 2018/10/17 0017 上午 10:48
 */
public class CglibAopProxy extends AbstractAopProxy {

    public CglibAopProxy(AdvisedSupport advisedSupport) {
        super(advisedSupport);
    }

    @Override
    public Object getProxy() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(advisedSupport.getTargetSource().getTargetClass());
        enhancer.setInterfaces(advisedSupport.getTargetSource().getInterfaces());
        enhancer.setCallback(new DynamicAdvisedInterceptor(advisedSupport));
        Object enhanced = enhancer.create();
        return enhanced;
    }

    private static class DynamicAdvisedInterceptor implements MethodInterceptor{
        private AdvisedSupport advisedSupport;
        private org.aopalliance.intercept.MethodInterceptor delegateMethodInterceptor;

        public DynamicAdvisedInterceptor(AdvisedSupport advisedSupport) {
            this.advisedSupport = advisedSupport;
            this.delegateMethodInterceptor = advisedSupport.getMethodInterceptor();
        }

        @Override
        public Object intercept(Object o, Method method, Object[] args, MethodProxy proxy) throws Throwable {
            if (advisedSupport.getMethodMatcher() == null || advisedSupport.getMethodMatcher().matches(method, advisedSupport.getTargetSource().getTargetClass())) {
                return delegateMethodInterceptor.invoke(new CglibMethodInvocation(advisedSupport.getTargetSource().getTarget(),method,args,proxy));
            }
            return new CglibMethodInvocation(advisedSupport.getTargetSource().getTarget(),method,args,proxy).proceed();
        }
    }

    private static class CglibMethodInvocation extends ReflectiveMethodInvocation {

        private MethodProxy methodProxy;

        public CglibMethodInvocation(Object target, Method method, Object[] arguments,MethodProxy methodProxy) {
            super(target, method, arguments);
            this.methodProxy = methodProxy;
        }

        @Override
        public Object proceed() throws Throwable {
            return this.methodProxy.invoke(target,arguments);
        }
    }

}
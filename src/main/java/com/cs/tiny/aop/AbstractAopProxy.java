package com.cs.tiny.aop;

/**
 * @author benjaminChan
 * @date 2018/10/16 0016 下午 2:13
 */
public abstract class AbstractAopProxy implements AopProxy{
    protected AdvisedSupport advisedSupport;

    public AbstractAopProxy(AdvisedSupport advisedSupport) {
        this.advisedSupport = advisedSupport;
    }
}

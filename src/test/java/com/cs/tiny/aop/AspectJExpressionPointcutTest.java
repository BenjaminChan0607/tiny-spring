package com.cs.tiny.aop;

import com.cs.tiny.HelloService;
import com.cs.tiny.HelloServiceImpl;
import org.junit.Test;

/**
 * @author benjaminChan
 * @date 2018/10/17 0017 上午 11:44
 */
public class AspectJExpressionPointcutTest {

    @Test
    public void testClassFilter() {
        String expression = "execution(* com.cs.tiny.*.*(..))";
        AspectJExpressionPointcut aspectJExpressionPointcut = new AspectJExpressionPointcut();
        aspectJExpressionPointcut.setExpression(expression);
        boolean matches = aspectJExpressionPointcut.getClassFilter().matches(HelloService.class);
        System.out.println(matches);

    }

    @Test
    public void testMethodMatcher() throws NoSuchMethodException {
        String expression = "execution(* com.cs.tiny.*.*(..))";
        AspectJExpressionPointcut aspectJExpressionPointcut = new AspectJExpressionPointcut();
        aspectJExpressionPointcut.setExpression(expression);
        boolean matches = aspectJExpressionPointcut.getMethodMatcher().matches(HelloServiceImpl.class.getDeclaredMethod("hello"), HelloServiceImpl.class);
        System.out.println(matches);
    }
}
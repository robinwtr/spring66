package com.atguigu.aop.xmlaop;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect //切面类
@Component //IOC容器
public class LogAspect {

    //设置切入点和通知类型

    /*
    前置 @before  返回@AfterReturning  异常@AfterThrowing  后置@After  环绕@Around
    * */

    //@Before(value = "execution(public int com.atguigu.aop.annoaop.CalculatorImpl.*(..))")
    //@Before(value = "com.atguigu.aop.xmlaop.LogAspect.pointCut()")
    public void beforeMethod(JoinPoint joinPoint) {
        String name = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        System.out.println("Before Method 方法名称："+name+" 参数列表："+Arrays.toString(args));
    }

    //@After(value = "execution(public int com.atguigu.aop.xmlaop.CalculatorImpl.*(..))")
    public void afterMethod(JoinPoint joinPoint) {
        String name = joinPoint.getSignature().getName();
        System.out.println("After Method 方法名称："+name);
    }
    //@AfterReturning(value = "execution(public int com.atguigu.aop.xmlaop.CalculatorImpl.*(..))",returning = "res")
    public void afterReturningMethod(JoinPoint joinPoint, Object res) {
        String name = joinPoint.getSignature().getName();
        System.out.println("返回通知，方法名称："+name+", 返回结果："+res);
    }

    //@AfterThrowing(value = "execution(public int com.atguigu.aop.xmlaop.CalculatorImpl.*(..))", throwing = "ex")
    public void afterThrowingMethod(JoinPoint joinPoint, Throwable ex) {
        String name = joinPoint.getSignature().getName();
        System.out.println("异常通知，方法名称："+name+", 异常信息："+ ex);
    }

    //@Around(value = "execution(public int com.atguigu.aop.xmlaop.CalculatorImpl.*(..))")
    public Object aroundMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        String name = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        String argString = Arrays.toString(args);
        Object result = null;
        try{
            System.out.println("环绕 目标方法之前");
            result = joinPoint.proceed(args);
            System.out.println("环绕 目标方法之后");
        }catch (Throwable e){
            System.out.println("环绕 目标方法异常");
            e.printStackTrace();
        } finally {
            System.out.println("环绕 目标方法完毕");
        }
        return result;
    }

    //重用切入点表达式
    @Pointcut(value = "execution(public int com.atguigu.aop.xmlaop.CalculatorImpl.*(..))")
    public void pointCut() {}
}

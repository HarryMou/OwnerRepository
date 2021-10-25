package com.usth.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;

import java.util.Date;

@Aspect
public class MyAspect {

    @After(value = "execution(* *..service.*..*(..))")
    public void outputRuntime(){
        System.out.println("方法在：" + new Date() + "时执行完毕");
    }
}

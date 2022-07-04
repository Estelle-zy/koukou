package com.example.koukou.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * @Author
 * @Date 2022/5/31 18:08
 * @Version 1.0
 * @Description
 **/
@Aspect
@Component
public class PermissionAspect {

    @Pointcut("@annotation(com.example.koukou.config.PermissionAnno)")
    public void permissionPoin(){}

    @Around("permissionPoin()")
    public Object hasPermission(ProceedingJoinPoint joinPoint) throws Throwable {
        // 方法签名
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        // 获取方法
        Method method = signature.getMethod();
        // 获取@Permission的value
        String permission = method.getAnnotation(PermissionAnno.class).value();
        // 自定义Permission列表中有哪些权限，不在list中的用户权限将不能访问
        ArrayList<String> permissionList = new ArrayList<>();
        permissionList.add("Agi");
        Object proceed = null;
        if(permissionList.contains(permission)){
            proceed = joinPoint.proceed();
        }else{
            proceed = "have no permission to visited";
        }
        return proceed;
    }
}

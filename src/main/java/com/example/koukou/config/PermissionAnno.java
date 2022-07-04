package com.example.koukou.config;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author
 * @Date 2022/5/31 17:19
 * @Version 1.0
 * @Description
 **/
@Target(ElementType.METHOD) // 加这个注解主要是为了方法上能用该注解
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface PermissionAnno {
    public String value();
}

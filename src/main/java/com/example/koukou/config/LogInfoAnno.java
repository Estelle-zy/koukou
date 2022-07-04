package com.example.koukou.config;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @Author
 * @Date 2022/5/30 16:13
 * @Version 1.0
 * @Description 自定义注解类
 **/
@Target({ ElementType.PARAMETER,ElementType.METHOD }) //注解放置的目标位置,METHOD是可注解在方法级别上
@Retention(RetentionPolicy.RUNTIME) //注解在哪个阶段执行
@Component
public @interface LogInfoAnno {

   String description() default "描述信息";

   String businessType() default "模块信息";

}
package com.example.koukou.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author
 * @Date 2022/7/15 14:52
 * @Version 1.0
 * @Description 前后端跨域配置
 **/
@Configuration
public class CorsConfiguration {
    //    我对@Bean的理解就是：如果想用某些属性和方法，必须要得到对象，所以@Bean其实是通过Java反射实现的
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
    //                  是否使用cookie、授权标头、TLS客户凭据进行跨站点访问请求
                        .allowCredentials(false)
//                        请求方式：get、post、或者直接配*所有的都可以
                        .allowedMethods("*")
                        .allowedOrigins("*");
            }
        };
    }
}

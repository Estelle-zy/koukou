package com.example.koukou;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@MapperScan(basePackages = "com.example.koukou.mapper")
@RestController
public class KoukouApplication {
    public static void main(String[] args) {
        SpringApplication.run(KoukouApplication.class, args);
    }

}

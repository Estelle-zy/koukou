package com.example.koukou.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @Author
 * @Date 2022/5/31 18:45
 * @Version 1.0
 * @Description
 **/
@Data
@Component
public class FileProperties {
    @Value("${file.path}")
    public  String filePath;
}

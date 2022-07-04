package com.example.koukou.basic;

import com.sun.tracing.dtrace.ArgsAttributes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author
 * @Date 2022/5/26 11:38
 * @Version 1.0
 * @Description
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseResult {
    private Integer status; // 响应状态码
    private String message; // 响应信息
    private Object data; // 响应数据
}

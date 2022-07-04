package com.example.koukou.controller;

import com.example.koukou.basic.ResponseResult;
import com.example.koukou.config.LogInfoAnno;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @Author
 * @Date 2022/5/30 18:06
 * @Version 1.0
 * @Description
 **/
@Controller
@RequestMapping("/api/logInfoController")
public class LogInfoController {
    /*测试get请求，应用切面类，即可保存日志*/
    @LogInfoAnno(description = "测试log 记录日志", businessType = "resume模块")
    @ResponseBody
    @GetMapping("/logTestGet")
    public ResponseResult test() {
        return new ResponseResult(200, "测试日志记录的get操作", null);
    }

    /*测试post请求 应用切面类，即可保存日志*/
    @LogInfoAnno(description = "测试log 记录日志", businessType = "resume模块")
    @ResponseBody
    @PostMapping("/logTestPost")
    public ResponseResult test(String name, String pwd) {
        return new ResponseResult(200, "测试日志记录的post操作", name+":"+pwd);
    }
}

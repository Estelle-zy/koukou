package com.example.koukou.controller;

import com.example.koukou.basic.ResponseResult;
import com.example.koukou.config.LogInfoAnno;
import com.example.koukou.entity.UserInfo;
import com.example.koukou.service.UserInfoService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @Author
 * @Date 2022/6/2 20:54
 * @Version 1.0
 * @Description
 **/
@Controller
@RequestMapping("api/redisController")
public class RedisController {
    @Autowired
    private UserInfoService service;

    @LogInfoAnno(description = "测试redis 存储对象", businessType = "resume模块")
    @ResponseBody
    @RequestMapping("/redis")
    public ResponseResult addUserInfo(String name, String pwd) {
        Object o = service.addUserInfo(name, pwd);
        return new ResponseResult(200, "添加成功", null);
    }
}


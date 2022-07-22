package com.example.koukou.service;

import com.example.koukou.entity.LogInfo;
import com.github.pagehelper.Page;

/**
 * @Author
 * @Date 2022/6/2 21:05
 * @Version 1.0
 * @Description
 **/
public interface UserInfo_RedisService {
    public Object addUserInfo(String name, String pwd);

}

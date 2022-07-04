package com.example.koukou.service.impl;

import com.alibaba.fastjson.JSON;
import com.example.koukou.entity.UserInfo;
import com.example.koukou.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

/**
 * @Author
 * @Date 2022/6/2 20:42
 * @Version 1.0
 * @Description
 **/
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public boolean login(String name, String password) {
        String redisKey="user:"+name;  //在redis中存储的key的格式
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        String value = ops.get(redisKey); //先从redis中根据key取出对应的密码
        //没有取到，说明该用户是第一次输错账号或者密码，将错误次数和用户信息记录到redis中
        if (value == null) {
            UserInfo user = new UserInfo();
            user.setUname(name);
            user.setPassword(password);
            user.setCount(1);
            user.setStatus(0);
            ops.set(redisKey, JSON.toJSONString(user));
        }else {//取到了，查看错误次数，
            UserInfo user = JSON.parseObject(value, UserInfo.class);
            if (user.getCount() >= 3) {
                //大于等于3次，封号(修改用户的状态)    user:admin   1  status 0
                user.setStatus(1);
                ops.set(redisKey,JSON.toJSONString(user));
            }else {
                //小于3次，在原来的错误次数基础上+1，重新存入redis中
                user.setCount(user.getCount()+1);
                ops.set(redisKey,JSON.toJSONString(user));
            }
        }
        return false;
    }

}

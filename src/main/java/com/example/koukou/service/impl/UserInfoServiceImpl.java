package com.example.koukou.service.impl;

import com.example.koukou.entity.UserInfo;
import com.example.koukou.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Service;

/**
 * @Author
 * @Date 2022/6/2 21:05
 * @Version 1.0
 * @Description
 **/
@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public Object addUserInfo(String name, String pwd) {
        UserInfo he = new UserInfo();
        he.setUname(name);
        he.setPassword(pwd);
        //操作字符类型的数据
        ValueOperations valueOperations = redisTemplate.opsForValue();
        //通过对象在redis对象进行存储
        valueOperations.set("USERINFO", he);
        //获取刚刚存储的对象
        Object value = valueOperations.get("USERINFO");
        System.out.println(value);
//        //操作hash类型的数据
//        HashOperations hashOperations = redisTemplate.opsForHash();
//        //操作列表的数据
//        ListOperations listOperations = redisTemplate.opsForList();
//        //操作有序集合类型的数据
//        ZSetOperations zSetOperations = redisTemplate.opsForZSet();
//        //操作无序集合类型的数据
//        SetOperations setOperations = redisTemplate.opsForSet();
        return value;
    }
}

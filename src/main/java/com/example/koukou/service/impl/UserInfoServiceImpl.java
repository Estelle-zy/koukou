package com.example.koukou.service.impl;

import com.alibaba.fastjson.JSON;
import com.example.koukou.entity.UserInfo;
import com.example.koukou.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jedis.JedisUtils;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

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
        UserInfo user = new UserInfo();
        user.setUname(name);
        user.setPassword(pwd);
//        使用jedis将对象转换成字符串存入redis
        Jedis jedis = new Jedis();
        jedis.set(name, JSON.toJSONString(user));
        //操作字符类型的数据
        ValueOperations valueOperations = redisTemplate.opsForValue();
        //获取刚刚存储的对象
        Object value = valueOperations.get(name);
//        System.out.println(value);
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

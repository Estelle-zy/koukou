package com.example.koukou.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @Author
 * @Date 2022/6/2 20:26
 * @Version 1.0
 * @Description
 **/
@Configuration
public class RedisConfig {
    /*
     * 这里直接使用StirngRedisTemplate序列化方式
     * */
    @Bean
    public StringRedisTemplate redisTemplate(RedisConnectionFactory redisConnectionFactory){
        StringRedisTemplate template=new StringRedisTemplate();
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }
    @Bean
    public RedisConnectionFactory redisConnectionFactory(){
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
        JedisConnectionFactory factory=new JedisConnectionFactory(configuration);
        return factory;
    }
}


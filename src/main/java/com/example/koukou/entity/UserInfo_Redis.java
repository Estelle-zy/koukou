package com.example.koukou.entity;

import lombok.Data;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * @Author
 * @Date 2022/6/2 20:31
 * @Version 1.0
 * @Description
 **/
@Repository
@Data
public class UserInfo_Redis implements Serializable {
    private String name;
    private String password;
    private Integer count; //统计用户登录失败的次数
    private Integer status; //标志用户状态
}

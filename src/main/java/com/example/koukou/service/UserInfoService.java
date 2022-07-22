package com.example.koukou.service;

import com.example.koukou.entity.UserInfo;

/**
 * @Author
 * @Date 2022/7/15 17:43
 * @Version 1.0
 * @Description
 **/
public interface UserInfoService {
    public boolean verifyPassword(String username, String password);

    public boolean resetPassword(String new_password, String username, String password);
}

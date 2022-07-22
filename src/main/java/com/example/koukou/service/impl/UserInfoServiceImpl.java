package com.example.koukou.service.impl;

import com.example.koukou.entity.UserInfo;
import com.example.koukou.mapper.UserInfoMapper;
import com.example.koukou.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author
 * @Date 2022/7/15 17:44
 * @Version 1.0
 * @Description
 **/
@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private UserInfoMapper mapper;


    @Override
    public boolean verifyPassword(String username, String password) {
        return mapper.verifyPassword(username, password) > 0 ? true : false;
    }

    @Override
    public boolean resetPassword(String new_password, String username, String password) {
        return mapper.updatePassword(new_password, username, password) > 0 ? true : false;
    }
}

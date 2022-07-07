package com.example.koukou.mapper;

import com.example.koukou.entity.UserInfo;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * @Author
 * @Date 2022/6/2 21:04
 * @Version 1.0
 * @Description
 **/
@Repository
public interface UserInfoMapper extends Mapper<UserInfo> {
}

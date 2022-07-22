package com.example.koukou.mapper;

import com.example.koukou.entity.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * @Author
 * @Date 2022/7/15 17:41
 * @Version 1.0
 * @Description
 **/
@Repository
public interface UserInfoMapper extends Mapper<UserInfo> {

    @Select("SELECT count(*) FROM user_info where username = #{username} and password = #{password}")
    public int verifyPassword(@Param("username") String username, @Param("password") String password);

    @Update("UPDATE user_info SET `password`=#{new_password} WHERE `username`=#{username} AND `password`=#{password}")
    public int updatePassword(@Param("new_password") String new_password,@Param("username") String username, @Param("password") String password);

}

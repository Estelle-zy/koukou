package com.example.koukou.controller;

import com.example.koukou.basic.ResponseResult;
import com.example.koukou.config.LogInfoAnno;
import com.example.koukou.service.impl.UserInfoServiceImpl;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @Author
 * @Date 2022/7/15 17:25
 * @Version 1.0
 * @Description
 **/
@Controller
@RequestMapping("/api/userInfoController")
public class UserInfoController {
    @Autowired
    private UserInfoServiceImpl service;

    @LogInfoAnno(description = "userinfo模块", businessType = "验证登录")
    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseResult login(HttpServletRequest request, HttpSession session) {
//        获取vue传递的参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println("参数：" + username + ": " + password);

        boolean selectRes = service.verifyPassword(username, password);

        if (selectRes) {
            session.setAttribute("username", username);
            return new ResponseResult(200, "登录成功", null);
        } else {
            return new ResponseResult(200, "用户名或密码错误", null);
        }
    }

    @LogInfoAnno(description = "userinfo模块", businessType = "重置密码")
    @ResponseBody
    @RequestMapping(value = "/resetPwd", method = RequestMethod.POST)
    public ResponseResult resetPwd(HttpServletRequest request) {
        String username = request.getParameter("username");
        String act_password = request.getParameter("act_password");
        String in_old_password = request.getParameter("in_old_password");
        String in_new_password = request.getParameter("in_new_password");
        String in_new_password_2 = request.getParameter("in_new_password_2");


        System.out.println("username：" + username + ",password:" + act_password);
        System.out.println("in_old_password:" + in_old_password);
        System.out.println("in_new_password:" + in_new_password);
        System.out.println("in_new_password2:" + in_new_password_2);

        if (in_old_password.equals(act_password) && in_new_password_2.equals(in_new_password)) {
            boolean updateRes = service.resetPassword(in_new_password, username, in_old_password);
            if (updateRes) {
                return new ResponseResult(200, "重置成功", null);
            } else {
                return new ResponseResult(200, "重置失败", null);
            }
        } else {
            return new ResponseResult(200, "新旧密码有误", null);
        }
    }
}

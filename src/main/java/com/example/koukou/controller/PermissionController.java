package com.example.koukou.controller;

import com.example.koukou.config.LogInfoAnno;
import com.example.koukou.config.PermissionAnno;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author
 * @Date 2022/5/31 18:04
 * @Version 1.0
 * @Description
 **/
@Controller
@RequestMapping("/api/permissionController")
public class PermissionController {

    @PermissionAnno(value = "User")
    @LogInfoAnno(description = "权限认证", businessType = "resume模块")
    @ResponseBody
    @GetMapping("/getUser")
    public String getUser(){
        return "User";
    }


    @PermissionAnno(value = "Agi")
    @LogInfoAnno(description = "权限认证", businessType = "resume模块")
    @ResponseBody
    @GetMapping("/getAGi")
    public String getAGi(){
        return "welecome AGi to visited";
    }
}

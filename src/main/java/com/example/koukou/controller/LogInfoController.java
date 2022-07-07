package com.example.koukou.controller;

import com.example.koukou.basic.ResponseResult;
import com.example.koukou.config.LogInfoAnno;
import com.example.koukou.entity.LogInfo;
import com.example.koukou.mapper.LogInfoMapper;
import com.example.koukou.service.LogInfoService;
import com.example.koukou.service.LoginService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @Author
 * @Date 2022/5/30 18:06
 * @Version 1.0
 * @Description
 **/
@Controller
@RequestMapping("/api/logInfoController")
public class LogInfoController {
    @Autowired
    private LogInfoMapper mapper;

    @Autowired
    private LogInfoService service;

    /*测试get请求，应用切面类，即可保存日志*/
    @LogInfoAnno(description = "测试log get操作", businessType = "LogInfo模块")
    @ResponseBody
    @GetMapping("/logTestGet")
    public ResponseResult test() {
        return new ResponseResult(200, "测试日志记录的get操作", null);
    }

    /*测试post请求 应用切面类，即可保存日志*/
    @LogInfoAnno(description = "测试log post操作", businessType = "LogInfo模块")
    @ResponseBody
    @PostMapping("/logTestPost")
    public ResponseResult test(String name, String pwd) {
        return new ResponseResult(200, "测试日志记录的post操作", name + ":" + pwd);
    }

    @LogInfoAnno(description = "测试查询所有log信息", businessType = "LogInfo模块")
    @ResponseBody
    @GetMapping("/queryAll")
    public ResponseResult queryAll() {
        List<LogInfo> logInfos = service.queryAll();
        return new ResponseResult(200, "查询所有LogInfo", logInfos);
    }

    @LogInfoAnno(description = "分页查询log数据并显示", businessType = "LogInfo模块")
    @ResponseBody
    @PostMapping("/queryByPage")
    public ResponseResult queryByPage(@RequestParam(defaultValue = "1") Integer pageNum,
                                      @RequestParam(defaultValue = "5") Integer pageSize) {
        PageInfo<LogInfo> pageInfo = service.findByPage(pageNum, pageSize);
        return new ResponseResult(200, "分页查询log操作", pageInfo);
    }
}

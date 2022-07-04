package com.example.koukou.controller;

import com.example.koukou.config.LogInfoAnno;
import com.example.koukou.entity.ResumeInfo;
import com.example.koukou.service.impl.ResumeInfoServiceImpl;
import com.github.pagehelper.PageInfo;
import com.sun.xml.bind.v2.TODO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @Author
 * @Date 2022/5/26 11:05
 * @Version 1.0
 * @Description
 **/
@Controller
@RequestMapping("/api/resumecontroller")
public class ResumeInfoController {
    @Autowired
    private ResumeInfoServiceImpl service;


    // 分页查询失败，但大致流程是这样，我这里页面跳转失败，
    // 首先需要跳转页面，不能用RestController，不然会返回return里的内容，需要用Controller
    @LogInfoAnno(description = "分页查询数据并显示", businessType = "resume模块")
    @RequestMapping("/queryByPage")
    public String queryByPage(Model model,
                              @RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "4") Integer pageSize){
        PageInfo<ResumeInfo> pageInfo = service.queryByPage(pageNum, pageSize);
        model.addAttribute("pageInfo", pageInfo);
        return "queryByPage"; // 将要跳转的页面
    }
}

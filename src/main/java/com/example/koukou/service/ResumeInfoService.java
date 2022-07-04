package com.example.koukou.service;

import com.example.koukou.entity.ResumeInfo;
import com.github.pagehelper.PageInfo;


/**
 * @Author
 * @Date 2022/5/26 12:00
 * @Version 1.0
 * @Description
 **/
public interface ResumeInfoService {
    public PageInfo<ResumeInfo> queryByPage(Integer pageNum, Integer pageSize);
}

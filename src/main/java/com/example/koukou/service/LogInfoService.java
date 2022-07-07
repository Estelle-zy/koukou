package com.example.koukou.service;

import com.example.koukou.entity.LogInfo;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @Author
 * @Date 2022/5/30 16:16
 * @Version 1.0
 * @Description
 **/
public interface LogInfoService {
    public int add(LogInfo log);  // 保存日志入库
//    查询所有
    public List<LogInfo> queryAll();
//    分页查询
    public PageInfo<LogInfo> findByPage(Integer pageNum, Integer pageSize);
}

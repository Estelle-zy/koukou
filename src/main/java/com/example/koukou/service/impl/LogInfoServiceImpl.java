package com.example.koukou.service.impl;

import com.example.koukou.entity.LogInfo;
import com.example.koukou.mapper.LogInfoMapper;
import com.example.koukou.service.LogInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author
 * @Date 2022/5/30 16:21
 * @Version 1.0
 * @Description
 * 业务逻辑的具体实现
 **/
@Service
public class LogInfoServiceImpl implements LogInfoService {
    @Autowired
    private LogInfoMapper mapper;

    @Override
    public int add(LogInfo log) {
        return mapper.insert(log);
    }

    @Override
    public List<LogInfo> queryAll() {
        return mapper.selectAll();
    }

    /**
     * 之前做分页查询的时候是一一次性把所有的数据都查询出来，然后分页展示
     * 这种做法对于大数据的情况非常不适应，那自然想到sql里面的limit关键字，
     * limit的两个参数：第一个是起始偏移量，第二个是查询的条数，那么java里面如何实现呢？
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<LogInfo> findByPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<LogInfo> pageInfo = new PageInfo<>(mapper.selectAll());
        return pageInfo;
    }




}

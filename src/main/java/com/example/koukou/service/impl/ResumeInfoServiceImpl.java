package com.example.koukou.service.impl;

import com.example.koukou.entity.ResumeInfo;
import com.example.koukou.mapper.ResumeInfoMapper;
import com.example.koukou.service.ResumeInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @Author
 * @Date 2022/5/26 12:01
 * @Version 1.0
 * @Description
 **/
@Service
public class ResumeInfoServiceImpl implements ResumeInfoService {
    @Autowired
    private ResumeInfoMapper mapper;


    @Override
    public PageInfo<ResumeInfo> queryByPage(Integer pageNum, Integer pageSize) {
        // 设置分页规则
        PageHelper.startPage(pageNum, pageSize);
        // 返回所有的分页信息参数为查询所有的数据记录
        PageInfo<ResumeInfo> pageInfo = new PageInfo<ResumeInfo>(mapper.selectAll()) ;
        return pageInfo;
    }
}

package com.example.koukou.service.impl;

import com.example.koukou.entity.LogInfo;
import com.example.koukou.mapper.LogInfoMapper;
import com.example.koukou.service.LogInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}

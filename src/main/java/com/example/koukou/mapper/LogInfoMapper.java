package com.example.koukou.mapper;

import com.example.koukou.entity.LogInfo;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * @Author
 * @Date 2022/5/30 16:20
 * @Version 1.0
 * @Description 使用通用mapper，使我们不再写复杂的mapper文件
 * 只需要在mapper接口上继承Mapper接口，指定mapper.xml的namespace  在入口类加上MapperScann扫描即可
 **/
@Repository
public interface LogInfoMapper extends Mapper<LogInfo> {
}

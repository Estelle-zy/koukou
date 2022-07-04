package com.example.koukou.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.stereotype.Repository;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author
 * @Date 2022/5/30 16:03
 * @Version 1.0
 * @Description 日志类，与数据库中日志表对应，将用户的操作入库如表
 **/
@Repository
@Data
@Table(name = "log_info")
public class LogInfo implements Serializable {


    @Id
    @Column(name = "id")
    private Integer id;                   //日志主键

    @Column(name = "url")
    private String url;                     //调用接口URL

    @Column(name = "method")
    private String method;                 //调用方法

    @Column(name = "type")
    private String type;                //调用类型

    @Column(name = "call_time")
    private String callTime;

    @Column(name = "param")
    private String param;                   //提交参数

    @Column(name = "ip")
    private String ip;                   //ip地址

    @Column(name = "msg")
    private String msg;            //信息

}

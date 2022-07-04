package com.example.koukou.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Author
 * @Date 2022/5/26 11:07
 * @Version 1.0
 * @Description  @Table 使得实体类和数据库表关联，@Data使用的前提是有Lombok插件，避免写很多的set get方法
 **/
@Data
@Table(name = "resume_info")
public class ResumeInfo implements Serializable {
    @Id
    @Column(name = "userid")
    private Integer id;

    @Column(name = "actual_name")
    private String name;

    @Column(name = "sex")
    private Integer sex;

    @Column(name = "age")
    private Integer age;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "graduate_school")
    private String school;

    @Column(name = "awards")
    private String awards;

    @Column(name = "introduce")
    private String introduce;

    @Column(name = "experience")
    private String experience;
}

package com.example.koukou.entity;

import lombok.Data;
import org.springframework.stereotype.Repository;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Author
 * @Date 2022/7/15 17:37
 * @Version 1.0
 * @Description
 **/
@Repository
@Data
@Table(name = "user_info")
public class UserInfo implements Serializable {
    @Id
    @Column
    private String name;        // username  unique索引

    @Column
    private String password;    // password

    @Column
    private String type;        // user_type(admin or normal)
}

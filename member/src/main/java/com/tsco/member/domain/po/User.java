package com.tsco.member.domain.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    //用户id
    private Integer id;
    //用户姓名
    private String userName;
    //邮箱
    private String email;
    //登录密码
    private String password;
    //性别
    private String sex;
    //生日
    private Date birthday;
    //地址
    private String address;
    //用户类型
    private String userRole;
    //创建时间
    private Date createdAt;
    //更新时间
    private Date updatedAt;
}

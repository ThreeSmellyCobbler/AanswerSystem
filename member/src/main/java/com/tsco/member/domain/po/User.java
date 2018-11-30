package com.tsco.member.domain.po;

import com.tsco.member.enums.SexEnum;
import com.tsco.member.enums.UserRoleEnum;
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
    private SexEnum sexEnum;
    //生日
    private Date birthday;
    //地址
    private String address;
    //创建时间
    private Date createdAt;
    //用户类型
    private UserRoleEnum userRoleEnum;
}

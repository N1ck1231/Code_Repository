package com.repositories.dao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    //账号
    private String account;
    //用户名
    private String username;
    //密码
    private String password;
    //用户权限
    private String name;
    //头像
    private String avatar;
    //描述
    private String mark;
    //唯一登录ID
    private String token;
    //路由
    private String routes;
    //状态
    private String status;
    //邮箱
    private String email;
    //电话
    private String phone;
    //地址
    private String address;
    //学校
    private String school;
}

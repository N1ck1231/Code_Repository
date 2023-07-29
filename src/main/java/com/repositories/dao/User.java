package com.repositories.dao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    //用户名
    private String username;
    //密码
    private String password;
    //用户权限
    private int user_identity;
}

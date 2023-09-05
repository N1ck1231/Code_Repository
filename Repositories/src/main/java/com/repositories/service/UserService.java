package com.repositories.service;

import com.repositories.dao.Code_Info;
import com.repositories.dao.User;
import com.repositories.dao.UserMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper user;
    public User login(String account){
        return user.findUserById(account);
    }

    public int signup(@Param("account")String account, @Param("password")String password){
        User user1=user.findUserById(account);
        System.out.println(user1);
        if(user1==null) {
            String token = UUID.randomUUID().toString().replaceAll("-","");
            user.insertUser(account, password,token);
            return 1;
        }
        else
            return 0;
    }

    public void setInfo(@Param("account")String account,@Param("username") String username,@Param("email") String email,@Param("phone") String phone,@Param("address") String address,@Param("school") String school){
        user.set_info(account,username,email,phone,address,school);
    }

    public List<User> getAll(){
        return user.getAll();
    }

}

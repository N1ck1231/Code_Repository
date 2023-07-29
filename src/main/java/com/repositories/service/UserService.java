package com.repositories.service;
import com.repositories.dao.Code_Info;
import com.repositories.dao.User;
import com.repositories.dao.UserMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserMapper user;
    public User login(String username){
        return user.findUserById(username);
    }

    public int signup(@Param("username")String username, @Param("password")String password){
        User user1=user.findUserById(username);
        System.out.println(user1);
        if(user1==null) {
            user.insertUser(username, password);
            return 1;
        }
        else
            return 0;
    }

}

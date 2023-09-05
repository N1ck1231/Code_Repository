package com.repositories.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

//注解表示设置该注解为mybatis的mapper类
@Mapper
@Repository
public interface UserMapper {

    User findUserById(String account);

    void insertUser(@Param("username")String username, @Param("password")String password, @Param("token")String token);

    void set_info(@Param("account") String account,@Param("username") String username,@Param("email") String email,@Param("phone") String phone,@Param("address") String address,@Param("school") String school);

    List<User> getAll();
}

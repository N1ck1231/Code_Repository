package com.repositories.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

//注解表示设置该注解为mybatis的mapper类
@Mapper
@Repository
public interface UserMapper {

    User findUserById(String username);

    void insertUser(@Param("username")String username, @Param("password")String password);

}

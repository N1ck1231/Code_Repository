package com.repositories.service;

import com.repositories.dao.Code_Info;
import com.repositories.dao.Code_InfoMapper;
import com.repositories.dao.User;
import com.repositories.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Code_InfoService {

    @Autowired
    private Code_InfoMapper user;
    public Code_Info select(String project_name){
        return user.find_Code_Info(project_name);
    }
}

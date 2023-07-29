package com.repositories.controller;

import com.repositories.dao.User;
import com.repositories.dao.Code_Info;
import com.repositories.service.Code_InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Code_Info")
public class Code_InfoController {
    @Autowired
    private Code_InfoService test;
    @PostMapping ("/select")
    public Code_Info select (Model model,String project_name){
        Code_Info code_info = test.select(project_name);
        System.out.println(project_name);
        System.out.println(code_info+"controller输出");
        return code_info;
    }
//更新用户信息表
    //更新api
    //条件查询api
}


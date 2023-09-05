package com.repositories.controller;

import com.repositories.dao.Code_Info;
import com.repositories.dao.Feed_Back;
import com.repositories.dao.Status_Code;
import com.repositories.service.Feed_BackService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/issue")
public class Feed_BackController {
    @Autowired
    private Feed_BackService user;

    @PostMapping("/submit_feedback")
    public void submit_feedback(@RequestBody Feed_Back backInfo){
        int project_id = backInfo.getProject_id();
        String account = backInfo.getAccount();
        String username = backInfo.getUsername();
        String project_name = backInfo.getProject_name();
        String project_url = backInfo.getProject_url();
        String issue = backInfo.getIssue();
        String  time = backInfo.getTime();
        System.out.println(account);
        if(project_name!=null)
        user.submit_feedback(project_id,account,username,project_name,project_url,issue,time);
    }

    @GetMapping("/get_feedback")
    public List<Feed_Back> get_feedback(){
        return user.get_feedback();
    }

    @GetMapping("/get_history")
    public List<Feed_Back> get_history(String account){
        return user.get_history(account);
    }


    @GetMapping("/pass_feedback")
    public void pass_feedback(@RequestBody Feed_Back feedback){
    int project_id = feedback.getProject_id();
    boolean red = feedback.isRed();
    boolean blue = feedback.isBlue();
    boolean green = feedback.isGreen();
    boolean err = feedback.isErr();
    user.pass_feedback(project_id,red,blue,green,err);
    }

    @GetMapping("/refuse_feedback")
    public void refuse_feedback(@RequestBody Feed_Back feedback){
        int project_id = feedback.getProject_id();
        boolean red = feedback.isRed();
        boolean blue = feedback.isBlue();
        boolean green = feedback.isGreen();
        boolean err = feedback.isErr();
        user.refuse_feedback(project_id,red,blue,green,err);
    }
    //设置拒绝、通过状态码并使用通过和拒绝接口传给前端、并保存到数据库，前端根据状态码进行判断
}

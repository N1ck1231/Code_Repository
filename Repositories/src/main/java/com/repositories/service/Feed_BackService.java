package com.repositories.service;


import com.repositories.dao.Feed_Back;
import com.repositories.dao.Feed_BackMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Feed_BackService {
    @Autowired
    private Feed_BackMapper user;

    public void submit_feedback(@Param("project_id")int project_id,@Param("account")String account,@Param("username") String username,@Param("project_name")String project_name, @Param("project_url")String project_url, @Param("issue")String issue, @Param("time")String time){
        user.submit_feedback(project_id,account,username,project_name,project_url,issue,time);
    }

    public List<Feed_Back>get_feedback(){
        return user.get_feedback();
    }

    public List<Feed_Back>get_history(String account){return user.get_history(account);}


    public void pass_feedback(@Param("project_id") int project_id,@Param("red") boolean red,@Param("blue") boolean blue,@Param("green") boolean green,@Param("err") boolean err){
        user.pass_feedback( project_id ,red,blue, green, err);
    }

    public void refuse_feedback(@Param("project_id") int project_id,@Param("red") boolean red,@Param("blue") boolean blue,@Param("green") boolean green,@Param("err") boolean err){
        user.refuse_feedback(project_id,red,blue,green,err);
    }
}

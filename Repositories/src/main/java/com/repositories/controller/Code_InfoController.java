package com.repositories.controller;

import com.repositories.dao.Code_Info;
import com.repositories.service.Code_InfoService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Code_Info")
public class Code_InfoController {
    @Autowired
    private Code_InfoService user;
    @PostMapping ("/select")
    public List<Code_Info> select ( @RequestBody Code_Info search_criteria){
        String key = search_criteria.getKey();
        String language = search_criteria.getLanguage();
        String time = search_criteria.getTime();
        String attention = search_criteria.getAttention();
//        String conditions = search_criteria.getCondition();
        String att,like;
        if(attention.contains("w")){
            att = "10000";
        }
        else if(attention.contains("k")){
            att = "1000";
        }
        else if(attention.contains("其它")){
            att = "-1";
        }
        else{
            att = "100";
        }
        String likes = search_criteria.getLikes();
        if(likes.contains("w")){
            like = "10000";
        }
        else if(likes.contains("k")){
            like = "1000";
        }
        else if(likes.contains("其它")){
            like = "-1";
        }
        else{
            like = "100";
        }
        System.out.println(key+language+time+att+like);
        List<Code_Info> code_info;
        code_info = user.select(key,language,time,att,like);
//        if(conditions==null){
//             code_info = user.select(key,language,time,att,like);
//        }
//        else{
//             code_info = user.select_condition(key,language,time,att,like,conditions);
//        }
        System.out.println(code_info);
        return code_info;
    }

    @GetMapping("/select_byId")
    public Code_Info select_byId(int project_id){
        System.out.println("project_id是："+project_id);
        Code_Info select_result;
        Integer id_project=new Integer(project_id);
        select_result = user.select_byId(id_project);
        return select_result;

    }

    @PostMapping("/alter")
    public void alter_CodeInfo(@RequestBody Code_Info alter_Info){
        String keyword = alter_Info.getKeyword();
        String project_name = alter_Info.getProject_name();
        String project_url = alter_Info.getProject_url();
        String download_url = alter_Info.getDownload_url();
        String watch_count = alter_Info.getWatch_count();
        String fork_count = alter_Info.getFork_count();
        String star_count = alter_Info.getStar_count();
        String update_time = alter_Info.getUpdate_time();
        String commit_count = alter_Info.getCommit_count();
        String about_text = alter_Info.getAbout_text();
        user.alter_CodeInfo(keyword,project_name,project_url,download_url,watch_count,fork_count,star_count,update_time,commit_count,about_text);
    }

}


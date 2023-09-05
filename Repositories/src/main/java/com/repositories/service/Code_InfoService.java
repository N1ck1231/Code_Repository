package com.repositories.service;

import com.repositories.dao.Code_Info;
import com.repositories.dao.Code_InfoMapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Code_InfoService {

    @Autowired
    private Code_InfoMapper user;

    public List<Code_Info> select(String key, String language,String time,String attention,String likes){
        return user.find_CodeInfo(key,language,time,attention,likes);
    }

    public Code_Info select_byId(int project_id){
        return user.select_byId(project_id);
    }
//    public List<Code_Info> select_condition(String key, String language,String time,String attention,String likes,String condition){
//        return user.find_CodeInfo_condition(key,language,time,attention,likes,condition);
//    }

    public void alter_CodeInfo(@Param("keyword")String keyword, @Param("project_name")String project_name, @Param("project_url")String project_url, @Param("download_url")String download_url, @Param("watch_count")String watch_count, @Param("fork_count")String fork_count, @Param("star_count")String star_count, @Param("update_time")String update_time, @Param("commit_count")String commit_count, @Param("about_text")String about_text){
        user.alter_CodeInfo(keyword,project_name,project_url,download_url,watch_count,fork_count,star_count,update_time,commit_count,about_text);
    }
}

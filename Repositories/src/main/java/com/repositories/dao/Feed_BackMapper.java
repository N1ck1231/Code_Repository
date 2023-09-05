package com.repositories.dao;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.beans.FeatureDescriptor;
import java.util.List;

@Mapper
@Repository
public interface Feed_BackMapper {

    void submit_feedback(@Param("project_id")int project_id,@Param("account")String account,@Param("username") String username,@Param("project_name")String project_name,@Param("project_url")String project_url,@Param("issue")String issue,@Param("time")String time);

    List<Feed_Back> get_feedback();

    List<Feed_Back> get_history(String account);

    public void pass_feedback(@Param("project_id") int project_id,@Param("red") boolean red,@Param("blue") boolean blue,@Param("green") boolean green,@Param("err") boolean err);

    public void refuse_feedback(@Param("project_id") int project_id,@Param("red") boolean red,@Param("blue") boolean blue,@Param("green") boolean green,@Param("err") boolean err);



}

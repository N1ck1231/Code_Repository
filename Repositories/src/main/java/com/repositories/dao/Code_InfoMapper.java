package com.repositories.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
@Mapper
@Repository
public interface Code_InfoMapper {

     List<Code_Info>  find_CodeInfo(@Param("key") String key, @Param("language") String language,@Param("time") String time,@Param("attention") String attention,@Param("likes") String likes);

     List<Code_Info>  find_CodeInfo_condition(@Param("key") String key, @Param("language") String language,@Param("time") String time,@Param("attention") String attention,@Param("likes") String likes,@Param("condition") String condition);

     void alter_CodeInfo(@Param("keyword")String keyword,@Param("project_name")String project_name,@Param("project_url")String project_url,@Param("download_url")String down_url,@Param("watch_count")String watch_count,@Param("fork_count")String fork_count,@Param("star_count")String star_count,@Param("update_time")String update_time,@Param("commit_count")String commit_count,@Param("about_text")String about_text);

     Code_Info select_byId(int project_id);
}

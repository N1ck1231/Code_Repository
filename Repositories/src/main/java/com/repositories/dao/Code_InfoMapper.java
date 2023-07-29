package com.repositories.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface Code_InfoMapper {

     Code_Info find_Code_Info(String watch_count);

}

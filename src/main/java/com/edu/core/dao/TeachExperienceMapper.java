package com.edu.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.edu.core.domain.TeachExperience;

public interface TeachExperienceMapper {
    int deleteByPrimaryKey(String id);

    int insert(TeachExperience record);

    int insertSelective(TeachExperience record);

    TeachExperience selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TeachExperience record);

    int updateByPrimaryKey(TeachExperience record);
    
    int deleteByTeacherid(String teacherid);
    
    List<TeachExperience> selectByTeacherid(String teacherid);
    
    List<TeachExperience> selectBySql(@Param("sqlwhere") String sqlwhere);
    
    int getCount(@Param("sqlwhere") String sqlwhere);
}
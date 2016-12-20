package com.edu.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.edu.core.domain.SubCourse;

public interface SubCourseMapper {
    int deleteByPrimaryKey(String id);

    int insert(SubCourse record);

    int insertSelective(SubCourse record);

    SubCourse selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SubCourse record);

    int updateByPrimaryKey(SubCourse record);
    
    int deleteByCourseKey(String courseid);
    
    List<SubCourse> selectBySql(@Param("sqlwhere") String sqlwhere);
    
    int getCount(@Param("sqlwhere") String sqlwhere);
      
}
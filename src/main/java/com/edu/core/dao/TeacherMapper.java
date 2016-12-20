package com.edu.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.edu.core.domain.Teacher;

public interface TeacherMapper {
    int deleteByPrimaryKey(String id);

    int insert(Teacher record);

    int insertSelective(Teacher record);

    Teacher selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Teacher record);

    int updateByPrimaryKey(Teacher record);
    
    Teacher selectByCode(String scode);
    
    List<Teacher> selectBySql(@Param("sqlwhere") String sqlwhere);
    
    int getCount(@Param("sqlwhere") String sqlwhere);
    
    List<Teacher> selectBylimit(@Param("sqlwhere") String sqlwhere, @Param("offset") int offset, @Param("limit") int limit);  
    
    List<Teacher> selectByCourseSql(@Param("sqlwhere") String sqlwhere);
}
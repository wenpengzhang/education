package com.edu.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.edu.core.domain.Student;

public interface StudentMapper {
    int deleteByPrimaryKey(String id);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);
    
    Student selectByCode(String scode);
    
    List<Student> selectBySql(@Param("sqlwhere") String sqlwhere);
    
    int getCount(@Param("sqlwhere") String sqlwhere);
    
    List<Student> selectBylimit(@Param("sqlwhere") String sqlwhere, @Param("offset") int offset, @Param("limit") int limit);  
}
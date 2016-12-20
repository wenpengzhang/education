package com.edu.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.edu.core.domain.Course;

public interface CourseMapper {
	
    int deleteByPrimaryKey(String id);

    int insert(Course record);

    int insertSelective(Course record);

    Course selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Course record);

    int updateByPrimaryKey(Course record);
    
    List<Course> selectBySql(@Param("sqlwhere") String sqlwhere);
    
    List<Course> selectBySqlAndTeacherid(@Param("sqlwhere") String sqlwhere,@Param("teacherid") String teacherid);
    
    List<Course> selectBySqlAndStudentid(@Param("sqlwhere") String sqlwhere,@Param("studentid") String studentid);
    
    int getCount(@Param("sqlwhere") String sqlwhere);
    
    List<Course> selectBylimit(@Param("sqlwhere") String sqlwhere, @Param("offset") int offset, @Param("limit") int limit);  
}
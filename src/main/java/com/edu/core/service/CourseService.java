package com.edu.core.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.edu.core.dao.CourseMapper;
import com.edu.core.domain.Course;


@Service("courseService")
public class CourseService {

	@Resource
	private CourseMapper courseMapper;
	
	public CourseService() {
		// TODO Auto-generated constructor stub
	}
	
	public int insert(Course record)
	{
		return this.courseMapper.insert(record); 
	}
	
	public int updateByPrimaryKey(Course record)
	{
		return this.courseMapper.updateByPrimaryKey(record); 
	}
	
	public int deleteByPrimaryKey(String id)
	{
		return this.courseMapper.deleteByPrimaryKey(id); 
	}
	
	public Course selectByPrimaryKey(String CourseId){
		return this.courseMapper.selectByPrimaryKey(CourseId);  
	}
	
	public List<Course> selectBySql(String sqlwhere){
		return this.courseMapper.selectBySql(sqlwhere);  
	}
	
	public List<Course> selectBySqlAndTeacherid(String sqlwhere,String teacherid){
		return this.courseMapper.selectBySqlAndTeacherid(sqlwhere,teacherid);  
	}
	
	public List<Course> selectBySqlAndStudentid(String sqlwhere,String studentid){
		return this.courseMapper.selectBySqlAndStudentid(sqlwhere,studentid);  
	}
	
	public int getCount(String sqlwhere)
	{
		return this.courseMapper.getCount(sqlwhere); 
	}
	
	public List<Course> selectBylimit(String sqlwhere,int offset,int limit)
	{
		return this.courseMapper.selectBylimit(sqlwhere,offset,limit); 
	}

}

package com.edu.core.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.edu.core.dao.SubCourseMapper;
import com.edu.core.domain.SubCourse;

@Service("subCourseService")
public class SubCourseService {

	@Resource
	private SubCourseMapper subCourseMapper;
	
	public SubCourseService() {
		// TODO Auto-generated constructor stub
	}
	
	public int insert(SubCourse record)
	{
		return this.subCourseMapper.insert(record); 
	}
	
	public int updateByPrimaryKey(SubCourse record)
	{
		return this.subCourseMapper.updateByPrimaryKey(record); 
	}
	
	public int deleteByPrimaryKey(String id)
	{
		return this.subCourseMapper.deleteByPrimaryKey(id); 
	}
	
	public int deleteByCourseKey(String courseid)
	{
		return this.subCourseMapper.deleteByCourseKey(courseid); 
	}
	
	public SubCourse selectByPrimaryKey(String CourseId){
		return this.subCourseMapper.selectByPrimaryKey(CourseId);  
	}
	
	public List<SubCourse> selectBySql(String sqlwhere){
		return this.subCourseMapper.selectBySql(sqlwhere);  
	}
	public int getCount(String sqlwhere)
	{
		return this.subCourseMapper.getCount(sqlwhere); 
	}
}

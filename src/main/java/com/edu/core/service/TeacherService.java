package com.edu.core.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.edu.core.dao.TeacherMapper;
import com.edu.core.domain.Teacher;


@Service("teacherService")
public class TeacherService {

	@Resource
	private TeacherMapper teacherMapper;
	
	public TeacherService() {
		// TODO Auto-generated constructor stub
	}
	
	public int insert(Teacher record)
	{
		return this.teacherMapper.insert(record); 
	}
	
	public int updateByPrimaryKey(Teacher record)
	{
		return this.teacherMapper.updateByPrimaryKey(record); 
	}
	
	public int deleteByPrimaryKey(String id)
	{
		return this.teacherMapper.deleteByPrimaryKey(id); 
	}
	
	public Teacher selectByPrimaryKey(String TeacherId){
		return this.teacherMapper.selectByPrimaryKey(TeacherId);  
	}
	
	public Teacher selectByCode(String scode){
		return this.teacherMapper.selectByCode(scode);  
	}
	
	public List<Teacher> selectBySql(String sqlwhere){
		return this.teacherMapper.selectBySql(sqlwhere);  
	}
	
	public int getCount(String sqlwhere)
	{
		return this.teacherMapper.getCount(sqlwhere); 
	}
	
	public List<Teacher> selectBylimit(String sqlwhere,int offset,int limit)
	{
		return this.teacherMapper.selectBylimit(sqlwhere,offset,limit); 
	}
	
	public List<Teacher> selectByCourseSql(String sqlwhere){
		return this.teacherMapper.selectByCourseSql(sqlwhere);  
	}
	
}

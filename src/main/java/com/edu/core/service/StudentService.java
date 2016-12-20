package com.edu.core.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.edu.core.dao.StudentMapper;
import com.edu.core.domain.Student;


@Service("studentService")
public class StudentService {

	@Resource
	private StudentMapper studentMapper;
	
	public StudentService() {
		// TODO Auto-generated constructor stub
	}
	
	public int insert(Student record)
	{
		return this.studentMapper.insert(record); 
	}
	
	public int updateByPrimaryKey(Student record)
	{
		return this.studentMapper.updateByPrimaryKey(record); 
	}
	
	public int deleteByPrimaryKey(String id)
	{
		return this.studentMapper.deleteByPrimaryKey(id); 
	}
	
	public Student selectByPrimaryKey(String StudentId){
		return this.studentMapper.selectByPrimaryKey(StudentId);  
	}
	
	public Student selectByCode(String scode){
		return this.studentMapper.selectByCode(scode);  
	}
	
	public List<Student> selectBySql(String sqlwhere){
		return this.studentMapper.selectBySql(sqlwhere);  
	}
	
	public int getCount(String sqlwhere)
	{
		return this.studentMapper.getCount(sqlwhere); 
	}
	
	public List<Student> selectBylimit(String sqlwhere,int offset,int limit)
	{
		return this.studentMapper.selectBylimit(sqlwhere,offset,limit); 
	}

}

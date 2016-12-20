package com.edu.core.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.edu.core.dao.TeachExperienceMapper;
import com.edu.core.domain.TeachExperience;

@Service("teachExperienceService")
public class TeachExperienceService {

	@Resource
	private TeachExperienceMapper teachExperienceMapper;
	
	public TeachExperienceService() {
		// TODO Auto-generated constructor stub
	}
	
	public int insert(TeachExperience record)
	{
		return this.teachExperienceMapper.insert(record); 
	}
	
	public int updateByPrimaryKey(TeachExperience record)
	{
		return this.teachExperienceMapper.updateByPrimaryKey(record); 
	}
	
	public int deleteByPrimaryKey(String id)
	{
		return this.teachExperienceMapper.deleteByPrimaryKey(id); 
	}
	
	public int deleteByTeacherid(String teacherid)
	{
		return this.teachExperienceMapper.deleteByTeacherid(teacherid); 
	}
	
	public TeachExperience selectByPrimaryKey(String TeachExperienceId){
		return this.teachExperienceMapper.selectByPrimaryKey(TeachExperienceId);  
	}
	
	public List<TeachExperience> selectByTeacherid(String teacherid){
		return this.teachExperienceMapper.selectByTeacherid(teacherid);  
	}
	
	public List<TeachExperience> selectBySql(String sqlwhere){
		return this.teachExperienceMapper.selectBySql(sqlwhere);  
	}

}

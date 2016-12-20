package com.edu.core.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.edu.core.dao.AppraiseMapper;
import com.edu.core.domain.Appraise;

@Service("appraiseService")
public class AppraiseService {

	@Resource
	private AppraiseMapper appraiseMapper;
	
	public AppraiseService() {
		// TODO Auto-generated constructor stub
	}
	
	public int insert(Appraise record)
	{
		return this.appraiseMapper.insert(record); 
	}
	
	public int updateByPrimaryKey(Appraise record)
	{
		return this.appraiseMapper.updateByPrimaryKey(record); 
	}
	
	public int deleteByPrimaryKey(String id)
	{
		return this.appraiseMapper.deleteByPrimaryKey(id); 
	}
	
	public Appraise selectByPrimaryKey(String AppraiseId){
		return this.appraiseMapper.selectByPrimaryKey(AppraiseId);  
	}
	
	public List<Appraise> selectBySql(String sqlwhere){
		return this.appraiseMapper.selectBySql(sqlwhere);  
	}
	
	public int getCount(String sqlwhere)
	{
		return this.appraiseMapper.getCount(sqlwhere); 
	}
	
	public List<Appraise> selectBylimit(String sqlwhere,int offset,int limit)
	{
		return this.appraiseMapper.selectBylimit(sqlwhere,offset,limit); 
	}

}

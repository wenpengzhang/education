package com.edu.core.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.edu.core.dao.AreaMapper;
import com.edu.core.domain.Area;


@Service("areaService")
public class AreaService {

	@Resource
	private AreaMapper areaMapper;
	
	public AreaService() {
		// TODO Auto-generated constructor stub
	}
	
	public int insert(Area record)
	{
		return this.areaMapper.insert(record); 
	}
	
	public int updateByPrimaryKey(Area record)
	{
		return this.areaMapper.updateByPrimaryKey(record); 
	}
	
	public int deleteByPrimaryKey(String id)
	{
		return this.areaMapper.deleteByPrimaryKey(id); 
	}
	
	public Area selectByPrimaryKey(String AreaId){
		return this.areaMapper.selectByPrimaryKey(AreaId);  
	}
	
	public Area selectByCode(String scode){
		return this.areaMapper.selectByCode(scode);  
	}
	
	public List<Area> selectByParentid(String parentid){
		return this.areaMapper.selectByParentid(parentid);  
	}
	public List<Area> selectBySql(String sqlwhere){
		return this.areaMapper.selectBySql(sqlwhere);  
	}
	
	public int getCount(String sqlwhere)
	{
		return this.areaMapper.getCount(sqlwhere); 
	}
}

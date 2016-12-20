package com.edu.core.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.edu.core.dao.ViewSuborderMapper;
import com.edu.core.domain.StatOrder;
import com.edu.core.domain.ViewSuborder;

@Service("iewSuborderService")
public class ViewSuborderService {

	@Resource
	private ViewSuborderMapper viewSuborderMapper;
	
	public ViewSuborderService() {
		// TODO Auto-generated constructor stub
	}
	
	public List<ViewSuborder> selectBySql(String sqlwhere){
		return this.viewSuborderMapper.selectBySql(sqlwhere);  
	}
	
	public int getCount(String sqlwhere)
	{
		return this.viewSuborderMapper.getCount(sqlwhere); 
	}
	
	public List<ViewSuborder> selectBylimit(String sqlwhere,int offset,int limit)
	{
		return this.viewSuborderMapper.selectBylimit(sqlwhere,offset,limit); 
	}
	
	public StatOrder statByTeacherid(String studentid)
	{
		return this.viewSuborderMapper.statByTeacherid(studentid); 
	}

}

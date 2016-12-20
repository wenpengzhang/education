package com.edu.core.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.edu.core.dao.ViewOrderMapper;
import com.edu.core.domain.StatOrder;
import com.edu.core.domain.ViewOrder;

@Service("viewOrderService")
public class ViewOrderService {

	@Resource
	private ViewOrderMapper viewOrderMapper;
	
	public ViewOrderService() {
		// TODO Auto-generated constructor stub
	}
	
	public ViewOrder selectByPrimaryKey(String ViewOrderId){
		return this.viewOrderMapper.selectByPrimaryKey(ViewOrderId);  
	}
	
	public List<ViewOrder> selectBySql(String sqlwhere){
		return this.viewOrderMapper.selectBySql(sqlwhere);  
	}
	
	public int getCount(String sqlwhere)
	{
		return this.viewOrderMapper.getCount(sqlwhere); 
	}
	
	public List<ViewOrder> selectBylimit(String sqlwhere,int offset,int limit)
	{
		return this.viewOrderMapper.selectBylimit(sqlwhere,offset,limit); 
	}
	
	public StatOrder statByStudentid(String studentid)
	{
		return this.viewOrderMapper.statByStudentid(studentid); 
	}
	
	public List<ViewOrder> selectBySqlAndTeacherid(String sqlwhere,String teacherid){
		return this.viewOrderMapper.selectBySqlAndTeacherid(sqlwhere,teacherid);  
	}

}

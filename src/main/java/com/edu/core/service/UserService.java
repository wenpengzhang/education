package com.edu.core.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.edu.core.dao.UserMapper;
import com.edu.core.domain.User;

@Service("userService")
public class UserService {

	@Resource
	private UserMapper userMapper;
	
	public UserService() {
		// TODO Auto-generated constructor stub
	}
	
	public int insert(User record)
	{
		return this.userMapper.insert(record); 
	}
	
	public int updateByPrimaryKey(User record)
	{
		return this.userMapper.updateByPrimaryKey(record); 
	}
	
	public int deleteByPrimaryKey(String id)
	{
		return this.userMapper.deleteByPrimaryKey(id); 
	}
	
	public User selectByPrimaryKey(String UserId){
		return this.userMapper.selectByPrimaryKey(UserId);  
	}
	
	public User selectByCode(String scode){
		return this.userMapper.selectByCode(scode);  
	}
	
	public List<User> selectBySql(String sqlwhere){
		return this.userMapper.selectBySql(sqlwhere);  
	}
	
	public int getCount(String sqlwhere)
	{
		return this.userMapper.getCount(sqlwhere); 
	}
	
	public List<User> selectBylimit(String sqlwhere,int offset,int limit)
	{
		return this.userMapper.selectBylimit(sqlwhere,offset,limit); 
	}


}

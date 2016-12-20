package com.edu.core.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.edu.core.dao.OrderingMapper;
import com.edu.core.domain.Ordering;


@Service("orderingService")
public class OrderingService {

	@Resource
	private OrderingMapper orderingMapper;
	
	public OrderingService() {
		// TODO Auto-generated constructor stub
	}
	
	public int insert(Ordering record)
	{
		return this.orderingMapper.insert(record); 
	}
	
	public int updateByPrimaryKey(Ordering record)
	{
		return this.orderingMapper.updateByPrimaryKey(record); 
	}
	
	public int deleteByPrimaryKey(String id)
	{
		return this.orderingMapper.deleteByPrimaryKey(id); 
	}
	
	public Ordering selectByPrimaryKey(String OrderingId){
		return this.orderingMapper.selectByPrimaryKey(OrderingId);  
	}
	
	public Ordering selectByOther(String studentid,String courseid)
	{
		return this.orderingMapper.selectByOther(studentid,courseid);  
	}
	
	public Ordering selectByCode(String code){
		return this.orderingMapper.selectByCode(code); 
	}
	
	
	public List<Ordering> selectBySql(String sqlwhere){
		return this.orderingMapper.selectBySql(sqlwhere);  
	}
	
	
	public int getCount(String sqlwhere)
	{
		return this.orderingMapper.getCount(sqlwhere); 
	}
	
	public Long getMoneyByDay(String days,String paystate)
    {
		return this.orderingMapper.getMoneyByDay(days,paystate); 
	}
	
	public Long getChageByDay(String days)
	{
		return this.orderingMapper.getChageByDay(days);
	}
	
	public Long getMoneyByWeak(String weaks,String paystate)
    {
		return this.orderingMapper.getMoneyByWeak(weaks,paystate); 
	}
	
	public Long getChageByWeak(String weaks)
	{
		return this.orderingMapper.getChageByWeak(weaks);
	}
	
	public Long getMoneyByMonth(String month,String paystate)
    {
		return this.orderingMapper.getMoneyByMonth(month,paystate); 
	}
	
	public Long getChageByMonth(String month)
	{
		return this.orderingMapper.getChageByMonth(month); 
	}
	
	public Long getMoneyByYear(String years,String paystate)
    {
		return this.orderingMapper.getMoneyByYear(years,paystate); 
	}
	
	public Long getChageByYear(String years)
	{
		return this.orderingMapper.getChageByYear(years); 
	}
}

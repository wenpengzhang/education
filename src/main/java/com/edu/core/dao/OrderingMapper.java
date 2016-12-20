package com.edu.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.edu.core.domain.Ordering;

public interface OrderingMapper {
    int deleteByPrimaryKey(String id);

    int insert(Ordering record);

    int insertSelective(Ordering record);

    Ordering selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Ordering record);

    int updateByPrimaryKey(Ordering record);
    
    Ordering selectByCode(String code);
    
    Ordering selectByOther(@Param("studentid") String studentid,@Param("courseid") String courseid);
    
    List<Ordering> selectBySql(@Param("sqlwhere") String sqlwhere);
    
    int getCount(@Param("sqlwhere") String sqlwhere);
    
    Long getMoneyByDay(@Param("days") String days,@Param("paystate") String paystate);
    
    Long getChageByDay(@Param("days") String days);
    
    Long getMoneyByWeak(@Param("weaks") String days,@Param("paystate") String paystate);
    
    Long getChageByWeak(@Param("weaks") String weaks);
    
  	Long getMoneyByMonth(@Param("month") String month,@Param("paystate") String paystate);
    
    Long getChageByMonth(@Param("month") String month);
    
    Long getMoneyByYear(@Param("years") String years,@Param("paystate") String paystate);
    
    Long getChageByYear(@Param("years") String years);
}
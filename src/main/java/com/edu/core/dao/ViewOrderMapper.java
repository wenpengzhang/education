package com.edu.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.edu.core.domain.StatOrder;
import com.edu.core.domain.ViewOrder;

public interface ViewOrderMapper {
	
	ViewOrder selectByPrimaryKey(String id);
    
    List<ViewOrder> selectBySql(@Param("sqlwhere") String sqlwhere);
    
    int getCount(@Param("sqlwhere") String sqlwhere);
    
    List<ViewOrder> selectBylimit(@Param("sqlwhere") String sqlwhere, @Param("offset") int offset, @Param("limit") int limit);  
    
    List<ViewOrder> selectBySqlAndTeacherid(@Param("sqlwhere") String sqlwhere,@Param("teacherid") String teacherid);
    
    StatOrder statByStudentid(String studentid);
}
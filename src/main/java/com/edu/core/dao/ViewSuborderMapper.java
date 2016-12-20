package com.edu.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.edu.core.domain.StatOrder;
import com.edu.core.domain.ViewSuborder;

public interface ViewSuborderMapper {
	
	List<ViewSuborder> selectBySql(@Param("sqlwhere") String sqlwhere);
	    
    int getCount(@Param("sqlwhere") String sqlwhere);
    
    List<ViewSuborder> selectBylimit(@Param("sqlwhere") String sqlwhere, @Param("offset") int offset, @Param("limit") int limit);  
    
    StatOrder statByTeacherid(String teacherid);
}
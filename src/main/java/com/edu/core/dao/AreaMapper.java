package com.edu.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.edu.core.domain.Area;

public interface AreaMapper {
    int deleteByPrimaryKey(String id);

    int insert(Area record);

    int insertSelective(Area record);

    Area selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Area record);

    int updateByPrimaryKey(Area record);
    
    Area selectByCode(String scode);
    
    List<Area> selectByParentid(String parentid);
    
    List<Area> selectBySql(@Param("sqlwhere") String sqlwhere);
    
    int getCount(@Param("sqlwhere") String sqlwhere);
}
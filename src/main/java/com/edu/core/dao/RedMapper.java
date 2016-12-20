package com.edu.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.edu.core.domain.Red;

public interface RedMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(Red record);

	int insertSelective(Red record);

	Red selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Red record);

	int updateByPrimaryKey(Red record);

	List<Red> getListByStudentID(@Param("sqlwhere") String sqlwhere);
}
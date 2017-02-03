package com.edu.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.edu.core.domain.Appraise;

public interface AppraiseMapper {
	int deleteByPrimaryKey(String id);

	int insert(Appraise record);

	int insertSelective(Appraise record);

	Appraise selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(Appraise record);

	int updateByPrimaryKey(Appraise record);

	List<Appraise> selectBySql(@Param("sqlwhere") String sqlwhere);

	int getCount(@Param("sqlwhere") String sqlwhere);

	List<Appraise> selectBylimit(@Param("sqlwhere") String sqlwhere, @Param("offset") int offset,
			@Param("limit") int limit);
}
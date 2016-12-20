package com.edu.core.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.edu.core.dao.RedMapper;
import com.edu.core.domain.Red;

@Service("redService")
public class RedService {

	@Resource
	private RedMapper redMapper;

	public RedService() {
		// TODO Auto-generated constructor stub
	}

	public List<Red> getListByStudentID(String sqlwhere) {
		return this.redMapper.getListByStudentID(sqlwhere);
	}

	public int insert(Red record) {
		return this.redMapper.insert(record);
	}

}

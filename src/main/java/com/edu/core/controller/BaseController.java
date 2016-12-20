package com.edu.core.controller;

public class BaseController {

	public int iLimit = 20;
	
	public BaseController() {
		// TODO Auto-generated constructor stub
	}
	
	public int GetPageCount(Integer recordCount, Integer pageLimit)
	{
		Double temp = recordCount.doubleValue()/ pageLimit.doubleValue();
		temp = Math.ceil(temp);
		int pageCount = Integer.parseInt(temp.toString());
		return pageCount;
    }

}

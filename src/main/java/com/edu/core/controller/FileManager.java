package com.edu.core.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

public class FileManager {

	public FileManager() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 生成随机文件名：当前年月日时分秒+五位随机数
	 * @param myFileName 
	 * 
	 * @return
	 */
	public static String getRandomFileName() {

		SimpleDateFormat simpleDateFormat;

		simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

		Date date = new Date();

		String strdate = simpleDateFormat.format(date);

		Random random = new Random();

		int rannum = (int) (random.nextDouble() * (99999 - 10000 + 1)) + 10000;// 获取5位随机数

		return strdate + rannum;// 当前时间
	}
	/*
	 * Java文件操作 获取文件扩展名
	 * 
	 * Created on: 2011-8-2 Author: blueeagle
	 */
	public static String getExtensionName(String filename) {
		if ((filename != null) && (filename.length() > 0)) {
			int dot = filename.lastIndexOf('.');
			if ((dot > -1) && (dot < (filename.length() - 1))) {
				return filename.substring(dot + 1);
			}
		}
		return filename;
	}

	/*
	 * Java文件操作 获取不带扩展名的文件名
	 * 
	 * Created on: 2011-8-2 Author: blueeagle
	 */
	public static String getFileNameNoEx(String filename) {
		if ((filename != null) && (filename.length() > 0)) {
			int dot = filename.lastIndexOf('.');
			if ((dot > -1) && (dot < (filename.length()))) {
				return filename.substring(0, dot);
			}
		}
		return filename;
	}

    public static String getServerUrl(HttpServletRequest request){
    	HttpServletRequest httpRequest=(HttpServletRequest)request;
    	String strBackUrl = "http://" + request.getServerName() //服务器地址
    	                    + ":" 
    	                    + request.getServerPort()           //端口号
    	                    + httpRequest.getContextPath() ;     //项目名称
    	return strBackUrl;

    }
}

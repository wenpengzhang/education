package com.edu.core.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edu.core.domain.Red;
import com.edu.core.service.RedService;

@Controller
@RequestMapping("/red")
public class RedController extends BaseController {

	@Resource
	private RedService redService;
	
	public RedController() {
		// TODO Auto-generated constructor stub
	}
	
	@ResponseBody
	@RequestMapping(value = "/getListByStudentID")
	public Map<String,Object> getListByStudentID(HttpServletRequest request,Model model){
		String skeyword=request.getParameter("studentid");
		String sqlwhere = "userid = '"+skeyword+"'";
		List<Red> list=this.redService.getListByStudentID(sqlwhere);
		Map<String,Object> map = new HashMap<String,Object>(); 
		map.put("total", list.size());
		map.put("records", list);
		return map;
	}
}

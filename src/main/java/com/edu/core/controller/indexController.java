package com.edu.core.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.edu.core.service.TeacherService;
import com.edu.core.service.UserService;

@Controller
@RequestMapping("/")
public class indexController extends BaseController {

	@Resource
	private UserService userService;

	@Resource
	private TeacherService teacherService;

	public indexController() {
		// TODO Auto-generated constructor stub
	}

	@ResponseBody
	@RequestMapping("/isshenhe")
	public Map<String, Object> isshenhe(HttpServletRequest request, Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("isshenhe", "1");
		map.put("data", map1);
		return map;
	}

}

package com.edu.core.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.edu.core.domain.Teacher;
import com.edu.core.domain.User;
import com.edu.core.service.TeacherService;
import com.edu.core.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

	@Resource
	private UserService userService;

	@Resource
	private TeacherService teacherService;

	public UserController() {
		// TODO Auto-generated constructor stub
	}

	@ResponseBody
	@RequestMapping(value = "/getlist")
	public Map<String, Object> getList(HttpServletRequest request, Model model) {
		String skeyword = request.getParameter("keyword");
		String sqlwhere = "(accounts like '%" + skeyword + "%'";
		sqlwhere += " or mobile like '%" + skeyword + "%'";
		sqlwhere += " or nickname like '%" + skeyword + "%')";
		List<User> list = this.userService.selectBySql(sqlwhere);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", list.size());
		map.put("records", list);
		return map;
	}

	@ResponseBody
	@RequestMapping(value = "/getAllList")
	public Map<String, Object> getAllList(HttpServletRequest request, Model model) {
		List<User> list = this.userService.selectBySql("1=1");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", list.size());
		map.put("records", list);
		return map;
	}

	@ResponseBody
	@RequestMapping(value = "/getListbyPage")
	public Map<String, Object> getListbyPage(HttpServletRequest request, Model model) {
		String skeyword = request.getParameter("keyword");
		int iPageIndex = Integer.parseInt(request.getParameter("pageindex"));
		String sqlwhere = "(accounts like '%" + skeyword + "%'";
		sqlwhere += " or mobile like '%" + skeyword + "%'";
		sqlwhere += " or nickname like '%" + skeyword + "%')";
		List<User> list = this.userService.selectBylimit(sqlwhere, (iPageIndex - 1) * iLimit, iPageIndex * iLimit);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", list.size());
		map.put("records", list);
		return map;
	}

	@ResponseBody
	@RequestMapping("/show")
	public Map<String, Object> show(HttpServletRequest request, Model model) {
		String id = request.getParameter("id");
		User user = this.userService.selectByPrimaryKey(id);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", user);
		return map;
	}
	@ResponseBody
	@RequestMapping("/login")
	public  Map<String, Object> login(HttpServletRequest request, Model model) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println("username=" + username);
		System.out.println("password=" + password);
		User user = this.userService.selectByCode(username);
		Teacher teacher = this.teacherService.selectByCode(username);
		//ModelAndView mv = new ModelAndView();
		Map<String, Object> map = new HashMap<String, Object>();
		if (user != null && user.getPassword().equals(password)) {
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			map.put("code", "1111");
			map.put("success", true);
			map.put("message", "登录成功！");

//			mv.addObject(map);
//			mv.setViewName("/index");
		} else if (teacher != null && teacher.getPassword().equals(password)) {
			HttpSession session = request.getSession();
			session.setAttribute("user", teacher);
			map.put("code", "1111");
			map.put("success", true);
			map.put("message", "登录成功！");
//			mv.addObject(map);
//			mv.setViewName("/index");
		} else {
			map.put("code", "0000");
			map.put("success", false);
			map.put("message", "帐号和密码不正确！");
//			mv.addObject(map);
//			mv.setViewName("/index");
		}

		return map;
	}

	@RequestMapping("/loginsu")
	public ModelAndView loginsu(HttpServletRequest request, Model model) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/index");
		return mv;
	}

	@ResponseBody
	@RequestMapping("/add")
	public Map<String, Object> insert(HttpServletRequest request, Model model) throws ParseException {
		Map<String, Object> map = new HashMap<String, Object>();
		if (!request.getParameter("mobile").equals(null)) {
			User user = new User();
			user.setAccounts(request.getParameter("accounts"));
			user.setPassword("111111");
			user.setNickname(request.getParameter("nickname"));
			user.setMobile(request.getParameter("mobile"));
			user.setRegistertime(new Date());
			String id = UUID.randomUUID().toString().replace("-", "");
			user.setId(id);
			// 创建用户暂未写，登陆后添加
			this.userService.insert(user);
			map.put("code", "1111");
			map.put("success", true);
			map.put("message", "添加成功！");
		} else {
			map.put("code", "0000");
			map.put("success", false);
			map.put("message", "该手机号已存在！");
		}
		return map;
	}

	@ResponseBody
	@RequestMapping("/edit")
	public Map<String, Object> edit(HttpServletRequest request, Model model) throws ParseException {
		String id = request.getParameter("id");
		User user = this.userService.selectByPrimaryKey(id);
		Map<String, Object> map = new HashMap<String, Object>();
		if (user != null) {
			user.setPassword(request.getParameter("password"));
			user.setNickname(request.getParameter("nickname"));
			user.setMobile(request.getParameter("mobile"));
			this.userService.updateByPrimaryKey(user);
			map.put("code", "1111");
			map.put("success", true);
			map.put("message", "修改成功！");
		} else {
			map.put("code", "0000");
			map.put("success", false);
			map.put("message", "编辑失败，记录不存在！");
		}

		return map;
	}

	@ResponseBody
	@RequestMapping("/delete")
	public Map<String, Object> delete(HttpServletRequest request, Model model) {

		Map<String, Object> map = new HashMap<String, Object>();
		String id = request.getParameter("id");
		int result = this.userService.deleteByPrimaryKey(id);
		if (result > 0) {
			map.put("code", "1111");
			map.put("success", true);
			map.put("message", "删除成功！");
		} else {
			map.put("code", "0000");
			map.put("success", false);
			map.put("message", "删除失败！");
		}
		return map;
	}
	
	@ResponseBody
	@RequestMapping("/live")
	public  Map<String, Object> live(HttpServletRequest request, Model model) {
		//0，是学生；1，是教师
		String type = request.getParameter("type");
//		String password = request.getParameter("password");
//		System.out.println("username=" + username);
//		System.out.println("password=" + password);
//		User user = this.userService.selectByCode(username);
//		Teacher teacher = this.teacherService.selectByCode(username);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		//ModelAndView mv = new ModelAndView();
		Map<String, Object> map = new HashMap<String, Object>();
		if ("0".equals(type)) {
			Map<String, Object> map1 = new HashMap<String, Object>();
			map1.put("image", "http://123.56.153.57:8080/education/images/students/student1.PNG");
			map1.put("url", "www.baidu.com");
			Map<String, Object> map2 = new HashMap<String, Object>();
			map2.put("image", "http://123.56.153.57:8080/education/images/students/student2.PNG");
			map2.put("url", "www.baidu.com");
			list.add(map1);
			list.add(map2);
			map.put("records", list);
		} 
		if ("1".equals(type)) {
			Map<String, Object> map1 = new HashMap<String, Object>();
			map1.put("image", "http://123.56.153.57:8080/education/images/teacher/teacher1.PNG");
			map1.put("url", "www.baidu.com");
			Map<String, Object> map2 = new HashMap<String, Object>();
			map2.put("image", "http://123.56.153.57:8080/education/images/teacher/teacher2.PNG");
			map2.put("url", "www.baidu.com");
			list.add(map1);
			list.add(map2);
			map.put("records", list);
		}

		return map;
	}
}

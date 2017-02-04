package com.edu.core.controller;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.edu.core.domain.Red;
import com.edu.core.domain.StatOrder;
import com.edu.core.domain.Student;
import com.edu.core.domain.ViewOrder;
import com.edu.core.service.OrderingService;
import com.edu.core.service.RedService;
import com.edu.core.service.StudentService;
import com.edu.core.service.ViewOrderService;
import com.edu.core.share.util.CommonUtil;

@Controller
@RequestMapping("/student")
public class StudentController extends BaseController {

	@Resource
	private StudentService studentService;

	@Resource
	private OrderingService orderingService;

	@Resource
	private ViewOrderService viewOrderService;

	@Resource
	private RedService redService;

	public StudentController() {
		// TODO Auto-generated constructor stub
	}

	@ResponseBody
	@RequestMapping(value = "/getAllList")
	public Map<String, Object> getAllList(HttpServletRequest request, Model model) {
		List<Student> list = this.studentService.selectBySql("1=1");
		for (Student item : list) {
			StatOrder statorder = viewOrderService.statByStudentid(item.getId());
			item.setStatOrder(statorder);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", list.size());
		map.put("records", list);
		return map;
	}

	@ResponseBody
	@RequestMapping(value = "/getListbyPage")
	public Map<String, Object> getListbyPage(HttpServletRequest request, Model model) {
		String skeyword = request.getParameter("keyword");
		String skeytjr = request.getParameter("skeytjr");
		System.out.println("skeytjr=" + skeytjr);
		int iPageIndex = Integer.parseInt(request.getParameter("pageindex"));
		String sqlwhere = null;
		if (!"".equals(skeytjr) && skeytjr != null) {
			sqlwhere = "(tjr like '%" + skeytjr + "%')";
		} else {
			sqlwhere = "(realname like '%" + skeyword + "%'";
			sqlwhere += " or accounts like '%" + skeyword + "%'";
			sqlwhere += " or mobile like '%" + skeyword + "%'";
			sqlwhere += " or nickname like '%" + skeyword + "%'";
			sqlwhere += " or mobileother like '%" + skeyword + "%'";
			sqlwhere += " or school like '%" + skeyword + "%'";
			sqlwhere += " or grade like '%" + skeyword + "%'";
			sqlwhere += " or address like '%" + skeyword + "%')";
		}
		List<Student> list = this.studentService.selectBylimit(sqlwhere, (iPageIndex - 1) * iLimit,
				iPageIndex * iLimit);
		for (Student item : list) {
			StatOrder statorder = viewOrderService.statByStudentid(item.getId());
			item.setStatOrder(statorder);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		// 计算总页数
		List<Student> list1 = this.studentService.selectBySql(sqlwhere);
		// 计算总页数
		int totalpage = 0;
		if (list1.size() > 0) {
			if (list1.size() % 20 > 0) {
				totalpage = (list1.size() / 20) + 1;
			} else {
				totalpage = list1.size() / 20;
			}

		}
		map.put("total", list.size());
		map.put("totalpage", totalpage);
		map.put("records", list);
		return map;
	}

	@ResponseBody
	@RequestMapping("/show")
	public Map<String, Object> show(HttpServletRequest request, Model model) {
		String id = request.getParameter("id");
		Student student = this.studentService.selectByPrimaryKey(id);
		String uploadpath = request.getContextPath() + "/upload/";
		if (student.getPichead() != null && !student.getPichead().equals("")) {
			student.setPichead(uploadpath + student.getPichead());
		}
		StatOrder statorder = viewOrderService.statByStudentid(id);
		student.setStatOrder(statorder);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", student);
		return map;
	}

	public Student upload(HttpServletRequest request, Student student) {
		String uploadpath = request.getSession().getServletContext().getRealPath("/") + "\\upload";
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		// 取得request中的所有文件名
		Iterator<String> iter = multipartRequest.getFileNames();
		while (iter.hasNext()) {
			// 记录上传过程起始时的时间，用来计算上传时间
			int pre = (int) System.currentTimeMillis();
			// 取得上传文件
			MultipartFile file = multipartRequest.getFile(iter.next());
			if (file != null) {
				// 取得当前上传文件的文件名称
				String myFileName = file.getOriginalFilename();
				System.out.println(myFileName);
				// 重命名上传后的文件名
				String newfileName = FileManager.getRandomFileName() + "." + FileManager.getExtensionName(myFileName);
				// 定义上传路径
				String savepath = uploadpath + "/" + newfileName;
				try {
					File localFile = new File(savepath);
					file.transferTo(localFile);
					student.setPichead(newfileName);

				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			// 记录上传该文件后的时间
			int finaltime = (int) System.currentTimeMillis();
			System.out.println(finaltime - pre);
		}
		return student;
	}

	@ResponseBody
	@RequestMapping("/regist")
	public Map<String, Object> regist(HttpServletRequest request, Model model) throws ParseException {
		String account = request.getParameter("accounts");
		Student student = this.studentService.selectByCode(account);
		Map<String, Object> map = new HashMap<String, Object>();
		if (student == null) {
			Student newstudent = new Student();
			newstudent.setAccounts(request.getParameter("accounts"));
			newstudent.setPassword(request.getParameter("password"));
			newstudent.setTjr(request.getParameter("tjr"));
			String id = UUID.randomUUID().toString().replace("-", "");
			newstudent.setId(id);
			// 创建用户暂未写，登陆后添加
			this.studentService.insert(newstudent);
			// 创建红包数据模型,并且插入数据
			Red red = new Red();
			red.setUserid(id);
			red.setPrice((float) 5);
			red.setRedname("注册红包");
			red.setContion("所有课程有效");
			red.setRedtype("通用红包");
			red.setEdate("永久有效");
			red.setRstatus(0);
			red.setSdate(CommonUtil.dateFormat(new Date(), null));
			redService.insert(red);

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
	@RequestMapping("/add")
	public Map<String, Object> insert(HttpServletRequest request, Model model) throws ParseException {
		Map<String, Object> map = new HashMap<String, Object>();
		if (!request.getParameter("accounts").equals(null)) {
			Student student = new Student();
			student.setAccounts(request.getParameter("accounts"));
			student.setPassword("111111");
			student.setRealname(request.getParameter("realname"));
			student.setNickname(request.getParameter("nickname"));
			student.setMobile(request.getParameter("mobile"));
			student.setMobileother(request.getParameter("mobileother"));
			student.setSchool(request.getParameter("school"));
			student.setGrade(request.getParameter("grade"));
			student.setAddress(request.getParameter("address"));
			String id = UUID.randomUUID().toString().replace("-", "");
			student = upload(request, student);
			student.setId(id);
			// 创建用户暂未写，登陆后添加
			this.studentService.insert(student);
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
		Student student = this.studentService.selectByPrimaryKey(id);
		Map<String, Object> map = new HashMap<String, Object>();
		if (student != null) {
			student.setAccounts(request.getParameter("mobile"));
			student.setRealname(request.getParameter("realname"));
			student.setNickname(request.getParameter("nickname"));
			// student.setMobile(request.getParameter("mobile"));
			student.setMobileother(request.getParameter("mobileother"));
			student.setSchool(request.getParameter("school"));
			student.setGrade(request.getParameter("grade"));
			student.setAddress(request.getParameter("address"));
			student = upload(request, student);
			this.studentService.updateByPrimaryKey(student);
			map.put("code", "1111");
			map.put("success", true);
			map.put("message", "修改成功！");
			// 修改成功以后根据信息完整度看是否发送红包,而且只送一次
			// 判断条件realname：grade：address：school：mobileother
			String sqlwhere = "userid = '" + id + "'";
			sqlwhere += " and redname = '完善信息'";
			List<Red> list = this.redService.getListByStudentID(sqlwhere);
			if (list.size() == 0) {
				if (!"".equals(request.getParameter("grade")) && !"".equals(request.getParameter("realname"))
						&& !"".equals(request.getParameter("address")) && !"".equals(request.getParameter("school"))
						&& !"".equals(request.getParameter("mobileother"))) {
					// 创建红包数据模型,并且插入数据
					Red red = new Red();
					red.setUserid(id);
					red.setPrice((float) 5);
					red.setRedname("完善信息");
					red.setContion("所有课程有效");
					red.setRedtype("通用红包");
					red.setEdate("永久有效");
					red.setRstatus(0);
					red.setSdate(CommonUtil.dateFormat(new Date(), null));
					redService.insert(red);
				}
			}
		} else {
			map.put("code", "0000");
			map.put("success", false);
			map.put("message", "编辑失败，记录不存在！");
		}

		return map;
	}

	@ResponseBody
	@RequestMapping("/androidedit")
	public Map<String, Object> androidedit(HttpServletRequest request, Model model) throws ParseException {
		String id = request.getParameter("id");
		Student student = this.studentService.selectByPrimaryKey(id);
		Map<String, Object> map = new HashMap<String, Object>();
		if (student != null) {
			// student.setAccounts(request.getParameter("mobile"));
			student.setRealname(request.getParameter("realname"));
			student.setNickname(request.getParameter("nickname"));
			// student.setMobile(request.getParameter("mobile"));
			student.setMobileother(request.getParameter("mobileother"));
			student.setSchool(request.getParameter("school"));
			student.setGrade(request.getParameter("grade"));
			student.setAddress(request.getParameter("address"));
			// student = upload(request, student);
			this.studentService.updateByPrimaryKey(student);
			map.put("code", "1111");
			map.put("success", true);
			map.put("message", "修改成功！");
			// 修改成功以后根据信息完整度看是否发送红包,而且只送一次
			// 判断条件realname：grade：address：school：mobileother
			String sqlwhere = "userid = '" + id + "'";
			sqlwhere += " and redname = '完善信息'";
			List<Red> list = this.redService.getListByStudentID(sqlwhere);
			if (list.size() == 0) {
				if (!"".equals(request.getParameter("grade")) && !"".equals(request.getParameter("realname"))
						&& !"".equals(request.getParameter("address")) && !"".equals(request.getParameter("school"))
						&& !"".equals(request.getParameter("mobileother"))) {
					// 创建红包数据模型,并且插入数据
					Red red = new Red();
					red.setUserid(id);
					red.setPrice((float) 5);
					red.setRedname("完善信息");
					red.setContion("所有课程有效");
					red.setRedtype("通用红包");
					red.setEdate("永久有效");
					red.setRstatus(0);
					red.setSdate(CommonUtil.dateFormat(new Date(), null));
					redService.insert(red);
				}
			}
		} else {
			map.put("code", "0000");
			map.put("success", false);
			map.put("message", "编辑失败，记录不存在！");
		}
		return map;
	}

	@ResponseBody
	@RequestMapping("/login")
	public Map<String, Object> login(HttpServletRequest request, Model model) {
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		Student student = this.studentService.selectByCode(account);
		Map<String, Object> map = new HashMap<String, Object>();
		if (student != null && student.getPassword().equals(password)) {
			map.put("code", "1111");
			map.put("success", true);
			map.put("message", "登录成功！");
			map.put("student", student);
		} else {
			map.put("code", "0000");
			map.put("success", false);
			map.put("message", "登录失败，帐号密码不一致！");
			map.put("student", null);
		}
		return map;
	}

	@ResponseBody
	@RequestMapping("/forgetThePassword")
	public Map<String, Object> forgetThePassword(HttpServletRequest request, Model model) throws ParseException {
		String accounts = request.getParameter("accounts");
		String newpassword = request.getParameter("newpassword");
		System.out.println("accounts=" + accounts);
		System.out.println("newpassword=" + newpassword);
		Student student = this.studentService.selectByCode(accounts);
		Map<String, Object> map = new HashMap<String, Object>();
		if (student != null) {
			student.setPassword(newpassword);
			this.studentService.updateByPrimaryKey(student);

			map.put("code", "1111");
			map.put("success", true);
			map.put("message", "修改密码成功！");
		} else {
			map.put("code", "0000");
			map.put("success", false);
			map.put("message", "修改密码失败，记录不存在或者密码不正确！");
		}
		return map;
	}

	@ResponseBody
	@RequestMapping("/delete")
	public Map<String, Object> delete(HttpServletRequest request, Model model) {

		Map<String, Object> map = new HashMap<String, Object>();
		String id = request.getParameter("id");
		if (orderingService.selectBySql("courseid='" + id + "'").size() > 0) {
			map.put("code", "0000");
			map.put("success", false);
			map.put("message", "删除失败，原因该课程已存在订单！");
		} else {
			int result = this.studentService.deleteByPrimaryKey(id);
			if (result > 0) {
				map.put("code", "1111");
				map.put("success", true);
				map.put("message", "删除成功！");
			} else {
				map.put("code", "0000");
				map.put("success", false);
				map.put("message", "删除失败！");
			}
		}
		return map;
	}

}

package com.edu.core.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edu.core.domain.Appraise;
import com.edu.core.domain.Course;
import com.edu.core.domain.Ordering;
import com.edu.core.domain.Student;
import com.edu.core.domain.SubCourse;
import com.edu.core.domain.Teacher;
import com.edu.core.service.AppraiseService;
import com.edu.core.service.OrderingService;
import com.edu.core.service.StudentService;
import com.edu.core.service.SubCourseService;
import com.edu.core.service.TeacherService;

@Controller
@RequestMapping("/appraise")
public class AppraiseController extends BaseController {

	@Resource
	private AppraiseService appraiseService;

	@Resource
	private SubCourseService subCourseService;

	@Resource
	private OrderingService orderingService;

	@Resource
	private TeacherService teacherService;

	@Resource
	private StudentService studentService;

	public AppraiseController() {
		// TODO Auto-generated constructor stub
	}

	@ResponseBody
	@RequestMapping(value = "/getAllList")
	public Map<String, Object> getAllList(HttpServletRequest request, Model model) {
		List<Appraise> list = this.appraiseService.selectBySql("1=1");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", list.size());
		map.put("records", list);
		return map;
	}

	@ResponseBody
	@RequestMapping(value = "/getRaiseListByTeacherid")
	public Map<String, Object> getRaiseListByTeacherid(HttpServletRequest request, Model model) {
		String teacherid = request.getParameter("teacherid");
		int iPageIndex = Integer.parseInt(request.getParameter("pageindex"));
		String sqlwhere = "teacherid = '" + teacherid + "'";
		System.out.println("============teacherid" + teacherid);
		System.out.println("============iPageIndex" + iPageIndex);
		List<Appraise> list = this.appraiseService.selectBylimit(sqlwhere, (iPageIndex - 1) * iLimit,
				iPageIndex * iLimit);
		for (Appraise item : list) {
			Student student = studentService.selectByPrimaryKey(item.getStudentid());
			Teacher teacher = teacherService.selectByPrimaryKey(item.getTeacherid());
			SubCourse subcourse = subCourseService.selectByPrimaryKey(item.getSubcourseid());
			if (subcourse != null) {
				item.setSubcourseid(subcourse.getSubname() == null ? "" : subcourse.getSubname());
			}
			item.setStudentid(student.getRealname() == null ? ""
					: (student.getAccounts().substring(0, 3) + "****"
							+ student.getAccounts().substring(7, student.getAccounts().length())));
			item.setTeacherid(teacher.getRealname() == null ? "" : teacher.getRealname());

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
		int iPageIndex = Integer.parseInt(request.getParameter("pageindex"));
		String sqlwhere = "(grade like '%" + skeyword + "%'";
		sqlwhere += " or advise like '%" + skeyword + "%')";
		List<Appraise> list = this.appraiseService.selectBylimit(sqlwhere, (iPageIndex - 1) * iLimit,
				iPageIndex * iLimit);
		for (Appraise item : list) {
			Student student = studentService.selectByPrimaryKey(item.getStudentid());
			Teacher teacher = teacherService.selectByPrimaryKey(item.getTeacherid());
			SubCourse subcourse = subCourseService.selectByPrimaryKey(item.getSubcourseid());
			item.setStudentid(student.getRealname());
			item.setTeacherid(teacher.getRealname());
			if (subcourse != null) {
				item.setSubcourseid(subcourse.getSubname());
			} else {
				item.setSubcourseid("");
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		// 计算总页数
		List<Appraise> list1 = this.appraiseService.selectBySql(sqlwhere);
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
		Appraise appraise = this.appraiseService.selectByPrimaryKey(id);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", appraise);
		return map;
	}

	@ResponseBody
	@RequestMapping("/add")
	public Map<String, Object> insert(HttpServletRequest request, Model model) throws ParseException {
		Map<String, Object> map = new HashMap<String, Object>();
		String studentid = request.getParameter("studentid");
		String subcourseid = request.getParameter("subcourseid");
		SubCourse subcourse = subCourseService.selectByPrimaryKey(subcourseid);
		Ordering ordering = orderingService.selectByOther(studentid, subcourse.getCourseid());
		String strWhere = "studentid='" + studentid + "' and subcourseid='" + subcourseid + "'";
		List<Appraise> list = appraiseService.selectBySql(strWhere);
		if (ordering != null && list.isEmpty()) {
			Appraise appraise = new Appraise();
			appraise.setStudentid(request.getParameter("studentid"));
			appraise.setTeacherid(subcourse.getTeacherid());
			appraise.setSubcourseid(request.getParameter("subcourseid"));
			appraise.setCourseid(ordering.getCourseid());
			appraise.setOrderid(ordering.getId());
			appraise.setGrade(request.getParameter("grade"));
			appraise.setAdvise(request.getParameter("advise"));
			appraise.setAvgscore(Integer.parseInt(request.getParameter("avgscore")));
			appraise.setFactscore(Integer.parseInt(request.getParameter("factscore")));
			appraise.setQualityscore(Integer.parseInt(request.getParameter("qualityscore")));
			appraise.setAttitudescore(Integer.parseInt(request.getParameter("attitudescore")));
			appraise.setTotalscore(Integer.parseInt(request.getParameter("totalscore")));
			appraise.setFiretime(new Date());
			String id = UUID.randomUUID().toString().replace("-", "");
			appraise.setId(id);

			// 创建用户暂未写，登陆后添加
			this.appraiseService.insert(appraise);
			map.put("code", "1111");
			map.put("success", true);
			map.put("message", "添加成功！");
		} else {
			map.put("code", "0000");
			map.put("success", false);
			map.put("message", "评论失败，因为无订单或者已评论。");
		}
		return map;
	}

	@ResponseBody
	@RequestMapping("/androidadd")
	public Map<String, Object> androidadd(HttpServletRequest request, Model model) throws ParseException {
		Map<String, Object> map = new HashMap<String, Object>();
		String studentid = request.getParameter("studentid");
		String subcourseid = request.getParameter("subcourseid");
		SubCourse subcourse = subCourseService.selectByPrimaryKey(subcourseid);
		Ordering ordering = orderingService.selectByOther(studentid, subcourse.getCourseid());
		String strWhere = "studentid='" + studentid + "' and subcourseid='" + subcourseid + "'";
		List<Appraise> list = appraiseService.selectBySql(strWhere);
		if (ordering != null && list.isEmpty()) {
			Appraise appraise = new Appraise();
			appraise.setStudentid(request.getParameter("studentid"));
			appraise.setTeacherid(subcourse.getTeacherid());
			appraise.setSubcourseid(request.getParameter("subcourseid"));
			appraise.setCourseid(ordering.getCourseid());
			appraise.setOrderid(ordering.getId());
			appraise.setGrade(request.getParameter("grade"));
			appraise.setAdvise(request.getParameter("advise"));
			//appraise.setAvgscore(Integer.parseInt(request.getParameter("avgscore")));
			appraise.setFactscore(Integer.parseInt(request.getParameter("factscore")));
			appraise.setQualityscore(Integer.parseInt(request.getParameter("qualityscore")));
			appraise.setAttitudescore(Integer.parseInt(request.getParameter("attitudescore")));
			appraise.setTotalscore(Integer.parseInt(request.getParameter("totalscore")));
			appraise.setFiretime(new Date());
			String id = UUID.randomUUID().toString().replace("-", "");
			appraise.setId(id);

			// 创建用户暂未写，登陆后添加
			this.appraiseService.insert(appraise);
			map.put("code", "1111");
			map.put("success", true);
			map.put("message", "添加成功！");
		} else {
			map.put("code", "0000");
			map.put("success", false);
			map.put("message", "评论失败，因为无订单或者已评论。");
		}
		return map;
	}

	@ResponseBody
	@RequestMapping("/delete")
	public Map<String, Object> delete(HttpServletRequest request, Model model) {

		Map<String, Object> map = new HashMap<String, Object>();
		String id = request.getParameter("id");
		int result = this.appraiseService.deleteByPrimaryKey(id);
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

}

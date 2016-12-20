package com.edu.core.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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

import com.edu.core.dao.OrderingMapper;
import com.edu.core.domain.Course;
import com.edu.core.domain.Ordering;
import com.edu.core.domain.SubCourse;
import com.edu.core.domain.Teacher;
import com.edu.core.domain.ViewOrder;
import com.edu.core.service.CourseService;
import com.edu.core.service.OrderingService;
import com.edu.core.service.SubCourseService;
import com.edu.core.service.TeacherService;
import com.edu.core.service.ViewOrderService;

@Controller
@RequestMapping("/ordering")
public class OrderingController extends BaseController {

	@Resource
	private CourseService courseService;
	
	@Resource
	private SubCourseService subCourseService;
	
	@Resource
	private TeacherService teacherService;
	
	@Resource
	private OrderingService orderingService;
	
	@Resource
	private ViewOrderService viewOrderService;

	public OrderingController() {
		// TODO Auto-generated constructor stub
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/getListbyPage")
	public Map<String,Object> getListbyPage(HttpServletRequest request,Model model){
		String skeyword = request.getParameter("keyword");
		int iPageIndex = Integer.parseInt(request.getParameter("pageindex")) ;
		String sqlwhere = "studentname like '%"+skeyword+"%' and coursename like '%"+skeyword+"%'";
		List<ViewOrder> list=this.viewOrderService.selectBylimit(sqlwhere, (iPageIndex - 1) * iLimit, iPageIndex * iLimit);
		for(ViewOrder item:list)
		{
			String teacherids="";
			String teachernames="";
			List<SubCourse> sublist = subCourseService.selectBySql("courseid='"+item.getCourseid()+"'");
			for(int i=0;i<sublist.size();i++)
			{
				Teacher teacher = teacherService.selectByPrimaryKey(sublist.get(i).getTeacherid());
				if(!teacherids.contains(teacher.getId()))
				{
					teachernames+=teacher.getRealname()+",";
					teacherids+=teacher.getId()+",";
				}
			}
			if(!teachernames.equals(""))
			{
				teachernames = teachernames.substring(0,teachernames.length()-1);
			}
			item.setTeacherid(teachernames);
		}
		Map<String,Object> map = new HashMap<String,Object>(); 
		map.put("total", list.size());
		map.put("records", list);
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value = "/getOrderListBystudentidAndConditions")
	public Map<String,Object> getOrderListBystudentidAndConditions(HttpServletRequest request,Model model){
		String studentid=request.getParameter("studentid");
		String pstate=request.getParameter("pstate");
		String sqlwhere = "studentid='"+studentid+"'";
		if(pstate!=null&&!pstate.equals(""))
		{
			sqlwhere = sqlwhere+ "and pstate = '"+pstate+"'"; 
		}
		List<ViewOrder> list=this.viewOrderService.selectBySql(sqlwhere);
		Map<String,Object> map = new HashMap<String,Object>(); 
		map.put("total", list.size());
		map.put("records", list);
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value = "/getOrderListByteacheridAndConditions")
	public Map<String,Object> getOrderListByteacheridAndConditions(HttpServletRequest request,Model model){
		String teacherid=request.getParameter("teacherid");
		String pstate=request.getParameter("pstate");
		String sqlwhere = "pstate = '"+pstate+"' and studentid='"+teacherid+"'";
		List<ViewOrder> list=this.viewOrderService.selectBySqlAndTeacherid(sqlwhere, teacherid);
		Map<String,Object> map = new HashMap<String,Object>(); 
		map.put("total", list.size());
		map.put("records", list);
		return map;
	}
	
	
	@ResponseBody
	@RequestMapping("/show")
	public Map<String,Object> show(HttpServletRequest request,Model model){
		String id=request.getParameter("id");
		Ordering ordering = this.orderingService.selectByPrimaryKey(id);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("data", ordering);
		return map;
	}
	/**
	 * 生成订单信息
	 * @param request
	 * @param model
	 * @return
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping("/setOrder")
	public Map<String,Object> setOrder(HttpServletRequest request,Model model) throws ParseException{
		String studentid = request.getParameter("studentid");
		String courseid = request.getParameter("courseid");
		/*String paymode = request.getParameter("paymode");*/
		String sqlwhere = "studentid = '"+studentid+"' and courseid ='"+courseid+"'";
		List<Ordering> list = this.orderingService.selectBySql(sqlwhere);
		Map<String,Object> map = new HashMap<String,Object>(); 
		if(list.size()<=0){
			Course course = courseService.selectByPrimaryKey(courseid);
			Ordering ordering=new Ordering();
			Date currentDate =new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");
			String formatStr =formatter.format(currentDate);
			ordering.setOrdercode(formatStr);
			ordering.setStudentid(studentid);
			ordering.setCourseid(courseid); 
			ordering.setSummoney(course.getPrice());
			/*ordering.setPaymode(paymode); */
			ordering.setOrdertime(currentDate);
			Long chage= Math.round(course.getPrice()*0.1);
			ordering.setChage(chage);
			ordering.setPaystate("未付款");
			ordering.setPstate("进行中"); 
			String id=UUID.randomUUID().toString().replace("-", "");
			ordering.setId(id);
		
			Integer Cnumber = course.getCnumber();
			if(Cnumber+1<=course.getCcount())
			{
				course.setCnumber(Cnumber+1);
				this.courseService.updateByPrimaryKey(course);
				this.orderingService.insert(ordering);
				map.put("code", "1111");
				map.put("success", true);
				map.put("message", "添加成功！");
				map.put("orderingid", id);
			}
			else
			{
				map.put("code", "1112");
				map.put("success", true);
				map.put("message", "下单失败，因为课程已经满员。");
				map.put("orderingid", "");
			}
			
			
		}else{
			map.put("code", "0000");
			map.put("success", false);
			map.put("message", "订单已经存在！");
		}
		return map;
	}
	@ResponseBody
	@RequestMapping("/payOrder")
	public Map<String,Object> payOrder(HttpServletRequest request,Model model) throws ParseException{
		String id = request.getParameter("id");
		String paymode = request.getParameter("paymode");
		Ordering ordering = this.orderingService.selectByPrimaryKey(id);
		Map<String,Object> map = new HashMap<String,Object>(); 
		if(ordering!=null&&ordering.getPaystate().equals("未付款")){
			ordering.setPaymode(paymode); 
			ordering.setPaystate("已付款");
			this.orderingService.updateByPrimaryKey(ordering);
			map.put("code", "1111");
			map.put("success", true);
			map.put("message", "添加成功！");
			
		}else{
			map.put("code", "0000");
			map.put("success", false);
			map.put("message", "订单不存在或者已经付款");
		}
		return map;
	}
	
	
	@RequestMapping("/cancePrelOrder")
	@ResponseBody
	public Map<String,Object> cancePrelOrder(HttpServletRequest request,Model model){
		
		Map<String,Object> map = new HashMap<String,Object>(); 
		String id=request.getParameter("id");
		int result=this.orderingService.deleteByPrimaryKey(id);
		if(result>0)
		{
			map.put("code", "1111");
			map.put("success", true);
			map.put("message", "删除成功！");
		}
		else
		{
			map.put("code", "0000");
			map.put("success", false);
			map.put("message", "删除失败！");
		}
		return map;
	}
	
	@ResponseBody
	@RequestMapping("/statistics")
	public Map<String,Object> statistics(HttpServletRequest request,Model model) throws ParseException{
		Date datetime = new Date();
		Calendar calendar = Calendar.getInstance(); 
		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		calendar.setTime(datetime); 
		Integer week = calendar.get(Calendar.WEEK_OF_YEAR); 
		week=week-1;
		Integer year = calendar.get(Calendar.YEAR); 
		Map<String,Object> map = new HashMap<String,Object>(); 
		SimpleDateFormat dayformat = new SimpleDateFormat("yyyyMMdd");
		String sday=dayformat.format(datetime);
		map.put("daysale", this.orderingService.getMoneyByDay(sday, "已付款"));
		map.put("dayrefund", this.orderingService.getMoneyByDay(sday, "退款"));
		map.put("dayfee", this.orderingService.getChageByDay(sday));
		String sweak=year.toString()+week.toString();
		map.put("weaksale", this.orderingService.getMoneyByWeak(sweak, "已付款"));
		map.put("weakrefund", this.orderingService.getMoneyByWeak(sweak, "退款"));
		map.put("weakfee", this.orderingService.getChageByWeak(sweak));
		SimpleDateFormat monthformat = new SimpleDateFormat("yyyyMM");
		String smonth=monthformat.format(datetime);
		map.put("monthsale", this.orderingService.getMoneyByMonth(smonth, "已付款"));
		SimpleDateFormat yearformat = new SimpleDateFormat("yyyy");
		String syear=yearformat.format(datetime);
		map.put("yearsale", this.orderingService.getMoneyByYear(syear, "已付款"));
		return map;
	}
}

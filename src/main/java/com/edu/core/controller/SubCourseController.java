package com.edu.core.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import com.edu.core.domain.Course;
import com.edu.core.domain.SubCourse;
import com.edu.core.service.CourseService;
import com.edu.core.service.SubCourseService;
import com.edu.core.share.util.*;

@Controller
@RequestMapping("/subcourse")
public class SubCourseController extends BaseController {

	@Resource
	private SubCourseService subCourseService;

	@Resource
	private CourseService courseService;

	@ResponseBody
	@RequestMapping(value = "/getAllList")
	public Map<String, Object> getAllList(HttpServletRequest request, Model model) {
		String sqlwhere = "1=1";
		List<SubCourse> list = this.subCourseService.selectBySql(sqlwhere);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", list.size());
		map.put("records", list);
		return map;
	}

	@ResponseBody
	@RequestMapping(value = "/CourseCalendar")
	public Map<String, Object> CourseCalendar(HttpServletRequest request, Model model) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-M-d");
		String teacherid = request.getParameter("teacherid");
		Date datetime = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(datetime);
		calendar.add(Calendar.DAY_OF_YEAR, -30);
		String startdate = dateFormat.format(calendar.getTime());
		String enddate = dateFormat.format(datetime);

		String sqlwhere = "teacherid='" + teacherid + "'";
		sqlwhere += "and datestart>='" + startdate + "' and datestart<='" + enddate + "'";
		Map<String, Object> map = new HashMap<String, Object>();
		if (sqlwhere != null && !sqlwhere.equals("")) {
			List<SubCourse> list = this.subCourseService.selectBySql(sqlwhere);
			map.put("total", list.size());
			map.put("records", list);
		} else {
			map.put("total", 0);
			map.put("records", null);
		}
		return map;
	}

	@ResponseBody
	@RequestMapping(value = "/theSelDayCourses")
	public Map<String, Object> theSelDayCourses(HttpServletRequest request, Model model) {
		String theDay = request.getParameter("theDay");
		String sqlwhere = "datestart='" + theDay + "'";
		Map<String, Object> map = new HashMap<String, Object>();
		if (sqlwhere != null && !sqlwhere.equals("")) {
			List<SubCourse> list = this.subCourseService.selectBySql(sqlwhere);
			map.put("total", list.size());
			map.put("records", list);
		} else {
			map.put("total", 0);
			map.put("records", null);
		}
		return map;
	}

	@ResponseBody
	@RequestMapping("/show")
	public Map<String, Object> show(HttpServletRequest request, Model model) {
		String id = request.getParameter("id");
		SubCourse subcourse = this.subCourseService.selectByPrimaryKey(id);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", subcourse);
		return map;
	}

	/*
	 * @ResponseBody
	 * 
	 * @RequestMapping("/insertTheliveURL") public Map<String,Object>
	 * insertTheliveURL(HttpServletRequest request,Model model){ String
	 * id=request.getParameter("subCourseid"); SubCourse subcourse =
	 * this.subCourseService.selectByPrimaryKey(id); Map<String,Object> map =
	 * new HashMap<String,Object>(); if(!subcourse.equals(null)) {
	 * subcourse.setSubstate("正在直播");
	 * subcourse.setLiveurl(request.getParameter("liveurl"));
	 * //subcourse.setChatname(request.getParameter("chatname"));
	 * subCourseService.updateByPrimaryKey(subcourse);
	 * 
	 * map.put("code", "1111"); map.put("success", true); map.put("message",
	 * "开始成功！"); } else { map.put("code", "0000"); map.put("success", false);
	 * map.put("message", "直播失败，记录不存在！"); } return map; }
	 */

	/*
	 * @ResponseBody
	 * 
	 * @RequestMapping("/getTheSubCourseEndNotice") public Map<String,Object>
	 * notice(HttpServletRequest request,Model model){ String
	 * spaceName=request.getParameter("spaceName"); String
	 * steamName=request.getParameter("steamName"); String sqlwhere =
	 * "liveurl like '%"+spaceName+"%' and liveurl like'%"+steamName+"%'";
	 * List<SubCourse> list = this.subCourseService.selectBySql(sqlwhere);
	 * Map<String,Object> map = new HashMap<String,Object>(); if(list.size()>0)
	 * { list.get(0).setSubstate("直播结束");
	 * list.get(0).setLiveurl(request.getParameter("liveurl"));
	 * list.get(0).setChatname(request.getParameter("chatname"));
	 * subCourseService.updateByPrimaryKey(list.get(0));
	 * 
	 * Course course =
	 * courseService.selectByPrimaryKey(list.get(0).getCourseid());
	 * course.setSubnumber(course.getSubnumber()+1);
	 * courseService.updateByPrimaryKey(course);
	 * 
	 * map.put("code", "1111"); map.put("success", true); map.put("message",
	 * "结束直播成功！"); } else { map.put("code", "0000"); map.put("success", false);
	 * map.put("message", "结束直播失败，记录不存在！"); } return map; }
	 */

	@ResponseBody
	@RequestMapping("/insertChatRoomId")
	public Map<String, Object> insertChatRoomId(HttpServletRequest request, Model model) {
		String id = request.getParameter("subCourseid");
		SubCourse subcourse = this.subCourseService.selectByPrimaryKey(id);
		Map<String, Object> map = new HashMap<String, Object>();
		if (!subcourse.equals(null)) {
			subcourse.setChatname(request.getParameter("chatname"));
			subCourseService.updateByPrimaryKey(subcourse);

			map.put("code", "1111");
			map.put("success", true);
			map.put("message", "开通聊天室！");
		} else {
			map.put("code", "0000");
			map.put("success", false);
			map.put("message", "直播失败，记录不存在！");
		}
		return map;
	}

	/**
	 * 根据子课程Id获取子课程数据
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getTheRefreshSubCourseDetailByid")
	public SubCourse getTheRefreshSubCourseDetailByid(HttpServletRequest request) {
		String id = request.getParameter("id");
		SubCourse subCourse = subCourseService.selectByPrimaryKey(id);
		return subCourse;
	}

	/**
	 * 根据教师id 获取教师当天的子课程
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/CourseCalendarToday")
	public Map<String, Object> CourseCalendarToday(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String teacherid = request.getParameter("teacherid");
		String sql = "teacherid = '" + teacherid + "' and stratdate LIKE '%"
				+ CommonUtil.dateFormat(new Date(), "yyyy-MM-dd") + "%'";
		List<SubCourse> list = subCourseService.selectBySql(sql);
		List<SubCourse> list2 = new ArrayList<SubCourse>();
		if(list.size()>0){
			for(int i=0;i<list.size();i++){
				String courseid = list.get(i).getCourseid();
				String sql1="cmode='视频课' and id ='"+courseid+"'";
				List<Course> list1 = this.courseService.selectBySql(sql1);
				if(list1.size()==0){
					list2.add(list.get(i));
				}
			}
		}
		map.put("records", list2);
		return map;
	}

	/**
	 * 子课程直播结束后得到通知的回调
	 * 
	 * @param request
	 */
	@ResponseBody
	@RequestMapping("/getTheSubCourseEndNotice")
	public Map<String, String> getTheSubCourseEndNotice(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		System.out.println("///////////////////////////////////////////////////////已访问");
		String spaceName = request.getParameter("spaceName");
		String streamName = request.getParameter("streamName");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String url = request.getParameter("url");
		System.out.println("================url=" + url);
		System.out.println("================streamName=" + streamName);
		String sql = "liveurl LIKE '%" + streamName + "%'";
		List<SubCourse> list = subCourseService.selectBySql(sql);
		System.out.println("list.size=" + list.size());
		if (list.size() > 0) {
			System.out.println("///////////////////////////////////////////////////////直播结束");
			SubCourse subCourse = list.get(0);
			System.out.println("================subCourse.id=" + subCourse.getId());
			subCourse.setRecordurl(url);
			subCourse.setSubstate("直播结束");
			System.out.println("================subCourse.url=" + subCourse.getRecordurl());
			subCourseService.updateByPrimaryKey(subCourse);
			// 计算已经结束直播
			// 首先获取大课程的id
			String courseid = subCourse.getCourseid();
			// 查询该大课程下的子课程的直播状态是结束直播的数量
			String sql1 = "substate='直播结束' and courseid='" + courseid + "'";
			List<SubCourse> list1 = subCourseService.selectBySql(sql1);
			System.out.println("直播结束的数量list1.size=" + list1.size());
			// 更新结束直播的子课程数量
			if (list1.size() > 0) {
				Course course = this.courseService.selectByPrimaryKey(courseid);
				course.setSubnumber(list1.size());
				this.courseService.updateByPrimaryKey(course);
				// 如果直播结束的子课程和总节数一样，就复制该课程数据并修改主课程的视频课程和购买量
				if (course.getSubcount() == course.getSubnumber()) {
					// 先根据课程名称查看是否存在该课程名称的视频课程了，如果有就不复制，如果没有就复制一条
					String sql2 = "cmode='视频课' and name='" + course.getName() + "'";
					List<Course> list2 = this.courseService.selectBySql(sql2);
					if (list2.size() == 0) {
						//在复制课程之前先查询此课程是否是试讲课，如果是试讲课就不复制
						String sql3 ="name='试讲课' and courseid='"+courseid+"'";
						List<Course> list3 = this.courseService.selectBySql(sql3);
						if(list3.size()==0){
							System.out.println("说明直播结束的子课程和子课程节数一致，开始复制课程");
							// 说明数量一样，开始进入复制数据，先复制主课程
							System.out.println("开始复制主课程");
							String id = UUID.randomUUID().toString().replace("-", "");
							course.setId(id);
							course.setCmode("视频课");
							course.setCnumber(0);
							this.courseService.insert(course);
							// 复制结束主课程开始复制子课程
							for (int i = 0; i < list1.size(); i++) {
								System.out.println("开始复制主课程子课程" + i);
								SubCourse subCourse2 = list1.get(i);
								String subid = UUID.randomUUID().toString().replace("-", "");
								subCourse2.setId(subid);
								subCourse2.setCourseid(id);
								subCourseService.insert(subCourse2);
							}
						}
					}
				}
			}
		} else {
			System.out.println("///////////////////////////////////////////////////////结束直播失败，记录不存在！");
			map.put("code", "0000");
			map.put("success", "false");
			map.put("message", "结束直播失败，记录不存在！");
		}
		return map;
	}

	/**
	 * 判断是否直播的回调
	 * 
	 * @param request
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping("/insertTheliveURL")
	public Map<String, String> insertTheliveURL(HttpServletRequest request) throws ParseException {
		Map<String, String> map = new HashMap<>();
		String id = request.getParameter("subCourseid");
		String liveurl = request.getParameter("liveurl");
		String chatname = request.getParameter("chatname");
		SubCourse subCourse = subCourseService.selectByPrimaryKey(id);

		if (subCourse == null) {
			map.put("code", "0000");
			map.put("success", "false");
			map.put("message", "直播失败，记录不存在！");
		} else {
			String starttime = subCourse.getStratdate();
			System.out.println("starttime=" + starttime);
			String endtime = subCourse.getEnddate();
			System.out.println("endtime=" + endtime);
			Date dateStarttime = CommonUtil.stringToDate(starttime, "yyyy-MM-dd HH:mm:ss");
			System.out.println("dateStarttime=" + dateStarttime);
			Date dateEndtime = CommonUtil.stringToDate(endtime, "yyyy-MM-dd HH:mm:ss");
			System.out.println("dateEndtime=" + dateEndtime);
			if ((new Date()).compareTo(dateStarttime) >= 0 && (dateEndtime).compareTo(new Date()) >= 0) {
				map.put("code", "1111");
				map.put("message", "成功开始直播");
				map.put("success", "1");
				System.out.println("成功=");

				subCourse.setChatname(chatname);
				subCourse.setLiveurl(liveurl);
				subCourse.setSubstate("正在直播");
				subCourseService.updateByPrimaryKey(subCourse);
			} else if ((new Date()).compareTo(dateStarttime) < 0) {
				map.put("code", "0000");
				map.put("message", "尚未开始直播");
				map.put("success", "0");
				System.out.println("尚未开始直播=");
			} else if ((new Date()).compareTo(dateEndtime) > 0) {
				map.put("code", "0001");
				map.put("message", "直播时间已过");
				map.put("success", "0");
				System.out.println("直播时间已过");
			}
		}
		return map;

	}
	
	@ResponseBody
	@RequestMapping("/edit")
	public Map<String, Object> edit(HttpServletRequest request, Model model) throws ParseException {
		String subcourseid = request.getParameter("subcourseid");
		String datestart = request.getParameter("datestart");
		String dateend = request.getParameter("dateend");
		String subname = request.getParameter("subname");
		String subtype = request.getParameter("subtype");
		String teacherid = request.getParameter("teacherid");
		Map<String, Object> map = new HashMap<String, Object>();
		SubCourse subCourse = new SubCourse();
		subCourse.setId(subcourseid);
		subCourse.setDatestart(CommonUtil.stringToDate(datestart, "yyyy-MM-dd HH:mm:ss"));
		subCourse.setDateend(CommonUtil.stringToDate(dateend, "yyyy-MM-dd HH:mm:ss"));
		subCourse.setSubname(subname);
		subCourse.setSubtype(subtype);
		subCourse.setTeacherid(teacherid);
		int i = this.subCourseService.updateByPrimaryKeySelective(subCourse);
		if (i>0) {
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
}

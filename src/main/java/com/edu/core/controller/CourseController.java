package com.edu.core.controller;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.edu.core.domain.Course;
import com.edu.core.domain.Ordering;
import com.edu.core.domain.SubCourse;
import com.edu.core.domain.Teacher;
import com.edu.core.domain.ViewOrder;
import com.edu.core.service.CourseService;
import com.edu.core.service.OrderingService;
import com.edu.core.service.SubCourseService;
import com.edu.core.service.TeacherService;

@Controller
@RequestMapping("/course")
public class CourseController extends BaseController {

	@Resource
	private SubCourseService subCourseService;
	
	@Resource
	private CourseService courseService;
	
	@Resource
	private TeacherService teacherService;
	
	@Resource
	private OrderingService orderingService;
	
	public CourseController() {
		// TODO Auto-generated constructor stub
	}
	
	@ResponseBody
	@RequestMapping(value = "/getAllList")
	public Map<String,Object> getAllList(HttpServletRequest request,Model model){
		String sqlwhere = "1=1";
		List<Course> list=this.courseService.selectBySql(sqlwhere);
		Map<String,Object> map = new HashMap<String,Object>(); 
		map.put("total", list.size());
		map.put("records", list);
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value = "/getCourseByConditions")
	public Map<String,Object> getCourseByConditions(HttpServletRequest request,Model model){
		String firsttype = request.getParameter("firsttype");
		String vsubject = request.getParameter("vsubject");
		String cmode = request.getParameter("cmode");
		String vgrade = request.getParameter("vgrade");
		String sqlwhere ="published ='发布'";
		if(firsttype!=null&&!firsttype.equals("")){
			sqlwhere+=" and firsttype like '%"+firsttype+"%'";
		}
		if(vsubject!=null&&!vsubject.equals("")){
			sqlwhere+="and vsubject like '%"+vsubject+"%'";
		}
		if(cmode!=null&&!cmode.equals("")){
			sqlwhere+="and cmode like '%"+cmode+"%'";
		}
		if(vgrade!=null&&!vgrade.equals("")){
			sqlwhere+="and vgrade like '%"+vgrade+"%'";
		}
		List<Course> list=this.courseService.selectBySql(sqlwhere);
 
		for(Course item:list)
		{
			String teacherids="";
			List<Teacher> teacherlist = new ArrayList<Teacher>();
			List<SubCourse> sublist = subCourseService.selectBySql("courseid='"+item.getId()+"'");
			for(int i=0;i<sublist.size();i++)
			{
				Teacher teacher = teacherService.selectByPrimaryKey(sublist.get(i).getTeacherid());
				if(!teacherids.contains(teacher.getId()))
				{
					teacherids=teacherids+","+teacher.getId();
					teacherlist.add(teacher);
				}
			}
			item.setTeachers(teacherlist);
		}
		Map<String,Object> map = new HashMap<String,Object>(); 
		map.put("total", list.size());
		map.put("records", list);
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value = "/getCourseByConditionsAndTeacherID")
	public Map<String,Object> getCourseByConditionsAndTeacherID(HttpServletRequest request,Model model){
		String teacherid = request.getParameter("teacherid");
		String firsttype = request.getParameter("firsttype");
		String vsubject = request.getParameter("vsubject");
		String cmode = request.getParameter("cmode");
		String vgrade = request.getParameter("vgrade");
		String sqlwhere ="published ='发布'";
		if(firsttype!=null&&!firsttype.equals("")){
			sqlwhere+=" and firsttype like '%"+firsttype+"%'";
		}
		if(vsubject!=null&&!vsubject.equals("")){
			sqlwhere+="and vsubject like '%"+vsubject+"%'";
		}
		if(cmode!=null&&!cmode.equals("")){
			sqlwhere+="and cmode like '%"+cmode+"%'";
		}
		if(vgrade!=null&&!vgrade.equals("")){
			sqlwhere+="and vgrade like '%"+vgrade+"%'";
		}
		List<Course> list=this.courseService.selectBySqlAndTeacherid(sqlwhere, teacherid);
		for(Course item:list)
		{
			String teacherids="";
			List<Teacher> teacherlist = new ArrayList<Teacher>();
			List<SubCourse> sublist = subCourseService.selectBySql("courseid='"+item.getId()+"'");
			for(int i=0;i<sublist.size();i++)
			{
				Teacher teacher = teacherService.selectByPrimaryKey(sublist.get(i).getTeacherid());
				if(!teacherids.contains(teacher.getId()))
				{
					teacherids=teacherids+","+teacher.getId();
					teacherlist.add(teacher);
				}
			}
			item.setTeachers(teacherlist);
		}
		Map<String,Object> map = new HashMap<String,Object>(); 
		map.put("total", list.size());
		map.put("records", list);
		return map;
	}
	
	/**
	 * 根据学生id得到所有发布状态的订单
	 * @param request
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getOrderedCourseByConditionsAndStudentID")
	public Map<String,Object> getCourseByConditionsAndStudentID(HttpServletRequest request,Model model){
		String studentid = request.getParameter("studentid");
		String firsttype = request.getParameter("firsttype");
		String vsubject = request.getParameter("vsubject");
		String cmode = request.getParameter("cmode");
		String vgrade = request.getParameter("vgrade");
		String sqlwhere ="published ='发布'";
		if(firsttype!=null&&!firsttype.equals("")){
			sqlwhere+=" and firsttype like '%"+firsttype+"%'";
		}
		if(vsubject!=null&&!vsubject.equals("")){
			sqlwhere+="and vsubject like '%"+vsubject+"%'";
		}
		if(cmode!=null&&!cmode.equals("")){
			sqlwhere+="and cmode like '%"+cmode+"%'";
		}
		if(vgrade!=null&&!vgrade.equals("")){
			sqlwhere+="and vgrade like '%"+vgrade+"%'";
		}
		List<Course> list=this.courseService.selectBySqlAndStudentid(sqlwhere, studentid);
		for(Course item:list)
		{
			String teacherids="";
			List<Teacher> teacherlist = new ArrayList<Teacher>();
			List<SubCourse> sublist = subCourseService.selectBySql("courseid='"+item.getId()+"'");
			for(int i=0;i<sublist.size();i++)
			{
				Teacher teacher = teacherService.selectByPrimaryKey(sublist.get(i).getTeacherid());
				if(!teacherids.contains(teacher.getId()))
				{
					teacherids=teacherids+","+teacher.getId();
					teacherlist.add(teacher);
				}
			}
			item.setTeachers(teacherlist);
		}
		Map<String,Object> map = new HashMap<String,Object>(); 
		map.put("total", list.size());
		map.put("records", list);
		return map;
	}
	
	/**
	 * 根据学生id得到所有发布状态的已支付订单
	 * @param request
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getPayOrderedCourseByConditionsAndStudentID")
	public Map<String,Object> getNotPayOrderedCourseByConditionsAndStudentID(HttpServletRequest request,Model model){
		String studentid = request.getParameter("studentid");
		String firsttype = request.getParameter("firsttype");
		String vsubject = request.getParameter("vsubject");
		String cmode = request.getParameter("cmode");
		String vgrade = request.getParameter("vgrade");
		String sqlwhere ="published ='发布'";
		
/*		 List<Ordering> orderlist = orderingService.selectBySql("paystate = '已付款'");
		 List<Course> courselist = new ArrayList<>();
		 for(int i = 0; i< orderlist.size(); i++ )
		 {
			 Ordering ordering = orderlist.get(i);
		 }*/
		
		if(firsttype!=null&&!firsttype.equals("")){
			sqlwhere+=" and firsttype like '%"+firsttype+"%'";
		}
		if(vsubject!=null&&!vsubject.equals("")){
			sqlwhere+="and vsubject like '%"+vsubject+"%'";
		}
		if(cmode!=null&&!cmode.equals("")){
			sqlwhere+="and cmode like '%"+cmode+"%'";
		}
		if(vgrade!=null&&!vgrade.equals("")){
			sqlwhere+="and vgrade like '%"+vgrade+"%'";
		}
		List<Course> list=this.courseService.selectBySqlAndStudentid(sqlwhere, studentid);
		List<Course> listResult = new ArrayList<>();
		for(Course item:list)
		{
			String teacherids="";
			List<Teacher> teacherlist = new ArrayList<Teacher>();
			List<SubCourse> sublist = subCourseService.selectBySql("courseid='"+item.getId()+"'");
			for(int i=0;i<sublist.size();i++)
			{
				Teacher teacher = teacherService.selectByPrimaryKey(sublist.get(i).getTeacherid());
				if(!teacherids.contains(teacher.getId()))
				{
					teacherids=teacherids+","+teacher.getId();
					teacherlist.add(teacher);
				}
			}
			item.setTeachers(teacherlist);
			List<Ordering> orderlist = orderingService.selectBySql("courseid='" + item.getId() +"' and studentid ='" +studentid  +"'" );
			if(orderlist.get(0).getPaystate().equals("已付款")){
				listResult.add(item);
			}
		}
		Map<String,Object> map = new HashMap<String,Object>(); 
		map.put("total", listResult.size());
		map.put("records", listResult);
		return map;
	}
	@ResponseBody
	@RequestMapping(value = "/getListbyPage")
	public Map<String,Object> getListbyPage(HttpServletRequest request,Model model){
		String skeyword = request.getParameter("keyword");
		int iPageIndex = Integer.parseInt(request.getParameter("pageindex")) ;
		String sqlwhere = "(name like '%"+skeyword+"%'";
		sqlwhere+= " or vstage like '%"+skeyword+"%'";
		sqlwhere+= " or vsubject like '%"+skeyword+"%'";
		sqlwhere+= " or vgrade like '%"+skeyword+"%'";
		sqlwhere+= " or cmode like '%"+skeyword+"%'";
		sqlwhere+= " or published like '%"+skeyword+"%'";
		sqlwhere+= " or showtop like '%"+skeyword+"%')";
		List<Course> list=this.courseService.selectBylimit(sqlwhere, (iPageIndex - 1) * iLimit, iPageIndex * iLimit);
		for(Course item:list)
		{
			String teacherids="";
			String teachernames="";
			List<SubCourse> sublist = subCourseService.selectBySql("courseid='"+item.getId()+"'");
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
	@RequestMapping("/show")
	public Map<String,Object> show(HttpServletRequest request,Model model){
		String id=request.getParameter("id");
		Course course = this.courseService.selectByPrimaryKey(id);
		List<SubCourse> list = subCourseService.selectBySql("courseid='"+id+"'");
		course.setSubCourse(list);
		String uploadpath = request.getContextPath()+"/upload/";
		if(course.getPichead() != null && !course.getPichead().equals("")){
			String[] piclist = course.getPichead().split(",");
			for(int i=0;i<piclist.length;i++){
				piclist[i]=uploadpath+piclist[i];
			}
			course.setPiclist(piclist);
		} 
		String teacherids="";
		List<Teacher> teacherlist = new ArrayList<Teacher>();
		List<SubCourse> sublist = subCourseService.selectBySql("courseid='"+course.getId()+"'");
		for(int i=0;i<sublist.size();i++)
		{
			Teacher teacher = teacherService.selectByPrimaryKey(sublist.get(i).getTeacherid());
			if(!teacherids.contains(teacher.getId()))
			{
				teacherids=teacherids+","+teacher.getId();
				teacherlist.add(teacher);
			}
		}
		course.setTeachers(teacherlist);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("data", course);
		return map;
	}
	
	public Course upload(HttpServletRequest request,Course course){
		String uploadpath = request.getSession().getServletContext().getRealPath("/")+ "\\upload";
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
				String newfileName = FileManager.getRandomFileName()+"."+ FileManager.getExtensionName(myFileName);
				// 定义上传路径
				String savepath = uploadpath + "/" + newfileName;
				try {
					File localFile = new File(savepath);
					file.transferTo(localFile);
					String pichhead =course.getPichead();
					if(pichhead!=null&&!pichhead.equals(""))
					{
						pichhead=pichhead+","+newfileName;
					}
					else
					{
						pichhead=newfileName;
					}
					course.setPichead(pichhead);
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
		return course;
    }
	
	@ResponseBody
	@RequestMapping("/add")
	public Map<String,Object> insert(HttpServletRequest request,Model model) throws ParseException{
		//Course renameProject = this.courseService.selectByCode(request.getParameter("id"));
		Map<String,Object> map = new HashMap<String,Object>(); 
		if(!request.getParameter("name").equals(null)){
			Course course=new Course();
			//course.setTeacherid(request.getParameter("teacherid"));
			course.setName(request.getParameter("name"));
			course.setVstage(request.getParameter("vstage"));
			course.setVsubject(request.getParameter("vsubject"));
			course.setVgrade(request.getParameter("vgrade"));
			course.setCmode(request.getParameter("cmode"));
			course.setFirsttype(request.getParameter("firsttype"));
			course.setPrice(Long.parseLong(request.getParameter("price")));
			course.setCnumber(0);
			course.setCcount(Integer.parseInt(request.getParameter("ccount")));
			course.setCversion(request.getParameter("cversion"));
			course.setArea(request.getParameter("area"));
			course.setTimezones(request.getParameter("timezones"));
			course.setDiscription(request.getParameter("discription"));
			course.setPublished("隐藏");
			course.setShowtop("");
			course = upload(request,course);
			String id=UUID.randomUUID().toString().replace("-", "");
			course.setId(id);
			Integer subindex = Integer.parseInt(request.getParameter("subindex"));
			Integer subcount=Integer.parseInt(request.getParameter("subcount"));
			
			course.setSubnumber(0);
			course.setSubcount(subcount);
			this.courseService.insert(course);
			
			for(int i=0;i<subindex;i++)
			{
				String temp = request.getParameter("teacherid"+i);
				if(temp!=null&&!temp.equals(""))
				{
					SubCourse subcourse=new SubCourse();
					subcourse.setCourseid(id);
					subcourse.setStratdate(request.getParameter("datestart"+i));
					subcourse.setEnddate(request.getParameter("dateend"+i));
					subcourse.setSubname(request.getParameter("subname"+i));
					subcourse.setSubtype(request.getParameter("subtype"+i));
					subcourse.setTeacherid(request.getParameter("teacherid"+i));
					subcourse.setSubprice(course.getPrice()/subcount);
					subcourse.setSubstate("未进行");
					String subid=UUID.randomUUID().toString().replace("-", "");
					subcourse.setId(subid);
					subCourseService.insert(subcourse);
				}
			}

			map.put("code", "1111");
			map.put("success", true);
			map.put("message", "添加成功！");
		}else{
			map.put("code", "0000");
			map.put("success", false);
			map.put("message", "添加失败，课程名称错误！");
		}
		return map;
	}
	
	@ResponseBody
	@RequestMapping("/edit")
	public Map<String,Object> edit(HttpServletRequest request,Model model) throws ParseException{
		String id=request.getParameter("id");
		Course course = this.courseService.selectByPrimaryKey(id);
		Map<String,Object> map = new HashMap<String,Object>(); 
		if(!course.equals(null))
		{
			course.setTeacherid(request.getParameter("teacherid"));
			course.setName(request.getParameter("name"));
			course.setVstage(request.getParameter("vstage"));
			course.setVsubject(request.getParameter("vsubject"));
			course.setVgrade(request.getParameter("vgrade"));
			course.setCmode(request.getParameter("cmode"));
			course.setFirsttype(request.getParameter("firsttype"));
			course.setPrice(Long.parseLong(request.getParameter("price")));
			course.setCnumber(0);
			course.setCcount(Integer.parseInt(request.getParameter("ccount")));
			course = upload(request,course);
			course.setCcount(Integer.parseInt(request.getParameter("ccount")));
			course.setCversion(request.getParameter("cversion"));
			course.setArea(request.getParameter("area"));
			course.setTimezones(request.getParameter("timezones"));
			course.setDiscription(request.getParameter("discription"));
			
			Integer subindex = Integer.parseInt(request.getParameter("subindex"));
			Integer subcount=Integer.parseInt(request.getParameter("subcount"));
			
			course.setSubnumber(0);
			course.setSubcount(subcount);
			this.courseService.updateByPrimaryKey(course);
			
			subCourseService.deleteByCourseKey(id);
			for(int i=0;i<subindex;i++)
			{
				String temp = request.getParameter("teacherid"+i);
				if(temp!=null&&!temp.equals(""))
				{
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
					SubCourse subcourse=new SubCourse();
					subcourse.setCourseid(id);
					subcourse.setStratdate(request.getParameter("datestart"+i));
					Date datestart = sdf.parse(request.getParameter("datestart"+i));
					subcourse.setDatestart(datestart);
					subcourse.setEnddate(request.getParameter("dateend"+i));
					Date dateend = sdf.parse(request.getParameter("dateend"+i));
					subcourse.setDateend(dateend);
					subcourse.setSubname(request.getParameter("subname"+i));
					subcourse.setSubtype(request.getParameter("subtype"+i));
					subcourse.setTeacherid(request.getParameter("teacherid"+i));
					subcourse.setSubprice(course.getPrice()/subcount);
					subcourse.setSubstate("未进行");
					String subid=UUID.randomUUID().toString().replace("-", "");
					subcourse.setId(subid);
					subCourseService.insert(subcourse);
				}
			}
			map.put("code", "1111");
			map.put("success", true);
			map.put("message", "修改成功！");
		}
		else
		{
			map.put("code", "0000");
			map.put("success", false);
			map.put("message", "编辑失败，记录不存在！");
		}
		return map;
	}

	@ResponseBody
	@RequestMapping("/delete")
	public Map<String,Object> delete(HttpServletRequest request,Model model){
		
		Map<String,Object> map = new HashMap<String,Object>();  
		String id=request.getParameter("id");
		if(orderingService.selectBySql("courseid='"+id+"'").size()>0)
		{
			map.put("code", "0000");
			map.put("success", false);
			map.put("message", "删除失败，原因该课程已存在订单！");
		}
		else
		{
			subCourseService.deleteByCourseKey(id);
			int result=this.courseService.deleteByPrimaryKey(id);
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
		}
		return map;
	}

	@ResponseBody
	@RequestMapping("/publish")
	public Map<String,Object> publish(HttpServletRequest request,Model model) throws ParseException{
		String id=request.getParameter("id");
		Course course = this.courseService.selectByPrimaryKey(id);
		Map<String,Object> map = new HashMap<String,Object>(); 
		if(course!=null)
		{
			if(course.getPublished()!=null&&course.getPublished().equals("发布")){
				course.setPublished("隐藏");
			}else {
				course.setPublished("发布");
			}
			this.courseService.updateByPrimaryKey(course);
			map.put("code", "1111");
			map.put("success", true);
			map.put("message", "发布隐藏成功！");
		}
		else
		{
			map.put("code", "0000");
			map.put("success", false);
			map.put("message", "发布隐藏失败，原因记录不存在");
		}
		return map;
	}
	
	@ResponseBody
	@RequestMapping("/top")
	public Map<String,Object> top(HttpServletRequest request,Model model) throws ParseException{
		String id=request.getParameter("id");
		
		Course course = this.courseService.selectByPrimaryKey(id);
		Map<String,Object> map = new HashMap<String,Object>(); 
		if(course!=null)
		{
			if(course.getShowtop()!=null&&course.getShowtop().equals("置顶")){
				course.setShowtop("");
			}else {
				course.setShowtop("置顶");
			}
			this.courseService.updateByPrimaryKey(course);
			map.put("code", "1111");
			map.put("success", true);
			map.put("message", "置顶成功！");
		}
		else
		{
			map.put("code", "0000");
			map.put("success", false);
			map.put("message", "置顶失败，原因记录不存在！");
		}
		return map;
	}
	
}

package com.edu.core.controller;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

import com.edu.core.domain.StatOrder;
import com.edu.core.domain.TeachExperience;
import com.edu.core.domain.Teacher;
import com.edu.core.service.SubCourseService;
import com.edu.core.service.TeachExperienceService;
import com.edu.core.service.TeacherService;
import com.edu.core.service.ViewSuborderService;


@Controller
@RequestMapping("/teacher")
public class TeacherController extends BaseController {
	
	@Resource
	private TeacherService teacherService;
	
	@Resource
	private TeachExperienceService teachExperienceService;
	
	@Resource
	private ViewSuborderService viewSuborderService;
	
	@Resource
	private SubCourseService subCourseService;

	public TeacherController() {
		// TODO Auto-generated constructor stub
	}
	
	@ResponseBody
	@RequestMapping(value = "/getAlllist")
	public Map<String,Object> getAlllist(HttpServletRequest request,Model model){
		List<Teacher> list=this.teacherService.selectBySql("1=1");
		for(Teacher item:list)
		{
			StatOrder statorder = viewSuborderService.statByTeacherid(item.getId());
			item.setStatOrder(statorder);
		}
		Map<String,Object> map = new HashMap<String,Object>(); 
		map.put("total", list.size());
		map.put("records", list);
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value = "/HotTeachers")
	public Map<String,Object> HotTeachers(HttpServletRequest request,Model model){
		String sqlwhere = "showtop='置顶'";
		Map<String,Object> map = new HashMap<String,Object>(); 
		if(sqlwhere!=null&&!sqlwhere.equals(""))
		{
			List<Teacher> list=this.teacherService.selectBySql(sqlwhere);
			map.put("total", list.size());
			map.put("records", list);
		}
		else 
		{
			map.put("total",0);
			map.put("records",null);
		}
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value = "/getTeacherByConditions")
	public Map<String,Object> getTeacherByConditions(HttpServletRequest request,Model model){
		String saddress = request.getParameter("address");
		String ssex = request.getParameter("sex");
		String ssubject = request.getParameter("subject");
		String sgrade = request.getParameter("grade");
		//String scondition = request.getParameter("condition");
		int iPageIndex = Integer.parseInt(request.getParameter("pageindex")) ;
		String sqlwhere ="published ='发布'";
		if(saddress!=null&&!saddress.equals("")){
			sqlwhere+=" and address like '%"+saddress+"%'";
		}
		if(ssex!=null&&!ssex.equals("")){
			sqlwhere+="and sex like '%"+ssex+"%'";
		}
		if(ssubject!=null&&!ssubject.equals("")){
			sqlwhere+="and subject like '%"+ssubject+"%'";
		}
		if(sgrade!=null&&!sgrade.equals("")){
			sqlwhere+="and subject like '%"+sgrade+"%'";
		}
		List<Teacher> list=this.teacherService.selectBylimit(sqlwhere, (iPageIndex - 1) * iLimit, iPageIndex * iLimit);
		for(Teacher item:list)
		{
			StatOrder statorder = viewSuborderService.statByTeacherid(item.getId());
			item.setStatOrder(statorder);
		}
		Map<String,Object> map = new HashMap<String,Object>(); 
		map.put("total", list.size());
		map.put("records", list);
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value = "/getTeacherForKeywordAndPage")
	public Map<String,Object> getTeacherForKeywordAndPage(HttpServletRequest request,Model model){
		String skeyword = request.getParameter("keyword");
		int iPageIndex = Integer.parseInt(request.getParameter("pageindex")) ;
		String sqlwhere ="(published ='发布'";
		sqlwhere+= " or realname like '%"+skeyword+"%'";
		sqlwhere+= " or mobile like '%"+skeyword+"%'";
		sqlwhere+= " or stype like '%"+skeyword+"%'";
		sqlwhere+= " or school like '%"+skeyword+"%'";
		sqlwhere+= " or subject like '%"+skeyword+"%'";
		sqlwhere+= " or showtop like '%"+skeyword+"%')";
		List<Teacher> list=this.teacherService.selectBylimit(sqlwhere, (iPageIndex - 1) * iLimit, iPageIndex * iLimit);
		for(Teacher item:list)
		{
			StatOrder statorder = viewSuborderService.statByTeacherid(item.getId());
			item.setStatOrder(statorder);
		}
		Map<String,Object> map = new HashMap<String,Object>(); 
		map.put("total", list.size());
		map.put("records", list);
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value = "/getListbyPage")
	public Map<String,Object> getListbyPage(HttpServletRequest request,Model model){
		String skeyword = request.getParameter("keyword");
		int iPageIndex = Integer.parseInt(request.getParameter("pageindex")) ;
		String sqlwhere = "(realname like '%"+skeyword+"%'";
		sqlwhere+= " or mobile like '%"+skeyword+"%'";
		sqlwhere+= " or stype like '%"+skeyword+"%'";
		sqlwhere+= " or school like '%"+skeyword+"%'";
		sqlwhere+= " or subject like '%"+skeyword+"%'";
		sqlwhere+= " or published like '%"+skeyword+"%'";
		sqlwhere+= " or showtop like '%"+skeyword+"%')";
		List<Teacher> list=this.teacherService.selectBylimit(sqlwhere, (iPageIndex - 1) * iLimit, iPageIndex * iLimit);
		for(Teacher item:list)
		{
			StatOrder statorder = viewSuborderService.statByTeacherid(item.getId());
			item.setStatOrder(statorder);
		}
		Map<String,Object> map = new HashMap<String,Object>(); 
		map.put("total", list.size());
		map.put("records", list);
		return map;
	}
	
	@ResponseBody
	@RequestMapping("/login")
	public Map<String,Object> login(HttpServletRequest request,Model model){
		String account=request.getParameter("account");
		String password=request.getParameter("password");
		Teacher teacher = this.teacherService.selectByCode(account);
		Map<String,Object> map = new HashMap<String,Object>();
		if(teacher!=null&&teacher.getPassword().equals(password))
		{
			map.put("code", "1111");
			map.put("success", true);
			map.put("message", "登录成功！");
			map.put("teacher", teacher);
		}else{
			map.put("code", "0000");
			map.put("success", false);
			map.put("message", "登录失败，帐号密码不一致！");
			map.put("teacher", null);
		}
		return map;
	}
	
	@ResponseBody
	@RequestMapping("/show")
	public Map<String,Object> show(HttpServletRequest request,Model model){
		String id=request.getParameter("id");
		Teacher teacher = this.teacherService.selectByPrimaryKey(id);
		Map<String,Object> map = new HashMap<String,Object>();
		String uploadpath = request.getContextPath()+"/upload/";
		if(teacher.getPichead() != null && !teacher.getPichead().equals("")){
			teacher.setPichead(uploadpath+teacher.getPichead());
		} 
		if(teacher.getPicidcard() != null && !teacher.getPicidcard().equals("")){
			teacher.setPicidcard(uploadpath+teacher.getPicidcard());
		}
		if(teacher.getPiceducation() != null && !teacher.getPiceducation().equals("")){
			teacher.setPiceducation(uploadpath+teacher.getPiceducation());
		}
		if(teacher.getPicteacher() != null && !teacher.getPicteacher().equals("")){
			teacher.setPicteacher(uploadpath+teacher.getPicteacher());
		}
		if(teacher.getPicother() != null && !teacher.getPicother().equals("")){
			teacher.setPicother(uploadpath+teacher.getPicother());
		}
		List<TeachExperience> list = teachExperienceService.selectByTeacherid(teacher.getId());
		teacher.setExperience(list);
		StatOrder statorder = viewSuborderService.statByTeacherid(id);
		teacher.setStatOrder(statorder);
		map.put("data", teacher);
		return map;
	}
	
    public Teacher upload(HttpServletRequest request,Teacher teacher){
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
					String sname =file.getName();
					if(sname.equals("filehead")){
						teacher.setPichead(newfileName);
					}else if(sname.equals("fileidcord")){
						teacher.setPicidcard(newfileName);
					}else if(sname.equals("fileedu")){
						teacher.setPiceducation(newfileName);
					}else if(sname.equals("fileteacher")){
						teacher.setPicteacher(newfileName);
					}else if(sname.equals("fileother")){
						teacher.setPicother(newfileName);
					}
					
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
		return teacher;
    }
    
    @ResponseBody
	@RequestMapping("/register")
	public Map<String,Object> register(HttpServletRequest request,Model model) throws ParseException{
		
		Teacher renameProject = this.teacherService.selectByCode(request.getParameter("accounts"));
		Map<String,Object> map = new HashMap<String,Object>(); 
		if(renameProject==null){
			Teacher teacher=new Teacher();
			teacher.setAccounts(request.getParameter("accounts"));
			teacher.setPassword(request.getParameter("password"));
			teacher.setRealname(request.getParameter("realname")); 
			teacher.setMobile(request.getParameter("mobile")); 
			teacher.setSex(request.getParameter("sex")); 
			teacher.setIdnumber(request.getParameter("idnumber"));
			if(request.getParameter("birthday")!=null&&!request.getParameter("birthday").equals("")){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
				Date birthday = sdf.parse(request.getParameter("birthday"));
				teacher.setBirthday(birthday);
			}
			teacher.setAddress(request.getParameter("address"));
			teacher.setSubject(request.getParameter("subject")); 
			teacher.setDegrees(request.getParameter("degrees")); 
			teacher.setGrade(request.getParameter("grade")); 
			teacher.setSchool(request.getParameter("school")); 
			if(request.getParameter("seniority")!=null&&!request.getParameter("seniority").equals("")){ 
				Integer seniority = Integer.parseInt(request.getParameter("seniority"));
				teacher.setSeniority(seniority);
			}
			teacher.setCv(request.getParameter("cv")); 
			teacher.setFeature(request.getParameter("feature")); 
			teacher.setStype(request.getParameter("stype")); 
			teacher.setPublished("隐藏");
			teacher.setShowtop("");
			String id=UUID.randomUUID().toString().replace("-", "");
			teacher.setId(id);
			this.teacherService.insert(teacher);
			
			map.put("code", "1111");
			map.put("success", true);
			map.put("message", "添加成功！");
		}else{
			map.put("code", "0000");
			map.put("success", false);
			map.put("message", "该手机号已存在！");
		}
		return map;
	}
	
	@ResponseBody
	@RequestMapping("/add")
	public Map<String,Object> insert(HttpServletRequest request,Model model) throws ParseException{
		
		Teacher renameProject = this.teacherService.selectByCode(request.getParameter("mobile"));
		Map<String,Object> map = new HashMap<String,Object>(); 
		if(renameProject==null){
			Teacher teacher=new Teacher();
			teacher.setAccounts(request.getParameter("mobile"));
			teacher.setPassword("111111"); 
			teacher.setRealname(request.getParameter("realname")); 
			teacher.setMobile(request.getParameter("mobile")); 
			teacher.setSex(request.getParameter("sex")); 
			teacher.setIdnumber(request.getParameter("idnumber"));
			if(request.getParameter("birthday")!=null&&!request.getParameter("birthday").equals("")){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
				Date birthday = sdf.parse(request.getParameter("birthday"));
				teacher.setBirthday(birthday);
			}
			teacher.setAddress(request.getParameter("address"));
			teacher.setSubject(request.getParameter("subject")); 
			teacher.setDegrees(request.getParameter("degrees")); 
			teacher.setGrade(request.getParameter("grade")); 
			teacher.setSchool(request.getParameter("school")); 
			if(request.getParameter("seniority")!=null&&!request.getParameter("seniority").equals("")){ 
				Integer seniority = Integer.parseInt(request.getParameter("seniority"));
				teacher.setSeniority(seniority);
			}
			teacher.setCv(request.getParameter("cv")); 
			teacher.setFeature(request.getParameter("feature")); 
			teacher.setStype(request.getParameter("stype")); 
			teacher.setPublished("隐藏");
			teacher.setShowtop("");
			String id=UUID.randomUUID().toString().replace("-", "");
			teacher.setId(id);
			teacher=upload(request,teacher);
			this.teacherService.insert(teacher);
			
			Integer subindex = Integer.parseInt(request.getParameter("subindex"));
			for(int i=0;i<subindex;i++)
			{
				String temp = request.getParameter("stage"+i);
				if(temp!=null&&!temp.equals(""))
				{
					TeachExperience eperience=new TeachExperience();
					eperience.setTeacherid(teacher.getId());
					eperience.setStage(request.getParameter("stage"+i));
					eperience.setSchool(request.getParameter("school"+i));
					String eperienceid=UUID.randomUUID().toString().replace("-", "");
					eperience.setId(eperienceid);
					teachExperienceService.insert(eperience);
				}
			}
			

			map.put("code", "1111");
			map.put("success", true);
			map.put("message", "添加成功！");
		}else{
			map.put("code", "0000");
			map.put("success", false);
			map.put("message", "该手机号已存在！");
		}
		return map;
	}
	
	@ResponseBody
	@RequestMapping("/edit")
	public Map<String,Object> edit(HttpServletRequest request,Model model) throws ParseException{
		String id=request.getParameter("id");
		Teacher teacher = this.teacherService.selectByPrimaryKey(id);
		Map<String,Object> map = new HashMap<String,Object>(); 
		if(teacher!=null)
		{
			teacher.setAccounts(request.getParameter("mobile"));
			teacher.setPassword("111111"); 
			teacher.setRealname(request.getParameter("realname")); 
			teacher.setMobile(request.getParameter("mobile")); 
			teacher.setSex(request.getParameter("sex")); 
			teacher.setIdnumber(request.getParameter("idnumber"));
			if(request.getParameter("birthday")!=null&&!request.getParameter("birthday").equals("")){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
				Date birthday = sdf.parse(request.getParameter("birthday"));
				teacher.setBirthday(birthday);
			}
			teacher.setAddress(request.getParameter("address"));
			teacher.setSubject(request.getParameter("subject")); 
			teacher.setDegrees(request.getParameter("degrees")); 
			teacher.setGrade(request.getParameter("grade")); 
			teacher.setSchool(request.getParameter("school")); 
			if(request.getParameter("seniority")!=null&&!request.getParameter("seniority").equals("")){ 
				Integer seniority = Integer.parseInt(request.getParameter("seniority"));
				teacher.setSeniority(seniority);
			}
			teacher.setCv(request.getParameter("cv")); 
			teacher.setFeature(request.getParameter("feature")); 
			teacher.setStype(request.getParameter("stype")); 
			teacher=upload(request,teacher);
			this.teacherService.updateByPrimaryKey(teacher);
			
			teachExperienceService.deleteByTeacherid(id);
			Integer subindex = Integer.parseInt(request.getParameter("subindex"));
			for(int i=0;i<subindex;i++)
			{
				String temp = request.getParameter("stage"+i);
				if(temp!=null&&!temp.equals(""))
				{
					TeachExperience eperience=new TeachExperience();
					eperience.setTeacherid(teacher.getId());
					eperience.setStage(request.getParameter("stage"+i));
					eperience.setSchool(request.getParameter("school"+i));
					String eperienceid=UUID.randomUUID().toString().replace("-", "");
					eperience.setId(eperienceid);
					teachExperienceService.insert(eperience);
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
	@RequestMapping("/forgetThePassword")
	public Map<String,Object> forgetThePassword(HttpServletRequest request,Model model) throws ParseException{
		String teacherid=request.getParameter("teacherid");
		String newpassword=request.getParameter("newpassword");
		Teacher teacher = this.teacherService.selectByPrimaryKey(teacherid);
		Map<String,Object> map = new HashMap<String,Object>(); 
		if(teacher!=null)
		{
			teacher.setPassword(newpassword); 
			this.teacherService.updateByPrimaryKey(teacher);
			
			map.put("code", "1111");
			map.put("success", true);
			map.put("message", "修改密码成功！");
		}
		else
		{
			map.put("code", "0000");
			map.put("success", false);
			map.put("message", "修改密码失败，记录不存在或者密码不正确！");
		}
		return map;
	}

	@ResponseBody
	@RequestMapping("/delete")
	public Map<String,Object> delete(HttpServletRequest request,Model model){
		
		Map<String,Object> map = new HashMap<String,Object>(); 
		String id=request.getParameter("id");
		if(subCourseService.selectBySql("teacherid='"+id+"'").size()>0)
		{
			map.put("code", "0000");
			map.put("success", false);
			map.put("message", "删除失败！原因该教师存在课程");
		}
		else
		{
			teachExperienceService.deleteByTeacherid(id);
			int result=this.teacherService.deleteByPrimaryKey(id);
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
		Teacher teacher = this.teacherService.selectByPrimaryKey(id);
		Map<String,Object> map = new HashMap<String,Object>(); 
		if(teacher!=null)
		{
			if(teacher.getPublished()!=null&&teacher.getPublished().equals("发布")){
				teacher.setPublished("隐藏");
			}else {
				teacher.setPublished("发布");
			}
			this.teacherService.updateByPrimaryKey(teacher);
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
		Teacher teacher = this.teacherService.selectByPrimaryKey(id);
		Map<String,Object> map = new HashMap<String,Object>(); 
		if(teacher!=null)
		{
			if(teacher.getShowtop()!=null&&teacher.getShowtop().equals("置顶")){
				teacher.setShowtop("");
			}else {
				teacher.setShowtop("置顶");
			}
			this.teacherService.updateByPrimaryKey(teacher);
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

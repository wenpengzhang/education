package com.edu.core.controller;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edu.core.domain.Area;
import com.edu.core.service.AreaService;

@Controller
@RequestMapping("/area")
public class AreaController {
	@Resource
	private AreaService areaService;
	
	public AreaController() {
		// TODO Auto-generated constructor stub
	}
	
	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public Map<String,Object> getList(HttpServletRequest request,Model model){
		String skeyword=request.getParameter("keyword");
		String sqlwhere = "code like '%"+skeyword+"%' and name like'%"+skeyword+"%'";
		List<Area> list=this.areaService.selectBySql(sqlwhere);
		Map<String,Object> map = new HashMap<String,Object>(); 
		map.put("total", list.size());
		map.put("records", list);
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value = "/getListByParentid", method = RequestMethod.POST)
	public Map<String,Object> getListByParentid(HttpServletRequest request,Model model){
		String parentId=request.getParameter("parentId");
		String sqlwhere="1=1";
		if(parentId.length()==0){
			sqlwhere="LENGTH(code)=2";
		}else if(parentId.length()==2){
			sqlwhere="code like '"+parentId+"%' and LENGTH(code)=4";
		}else if(parentId.length()==4){
			sqlwhere="code like '"+parentId+"%' and LENGTH(code)=6";
		}
		List<Area> list=this.areaService.selectBySql(sqlwhere);
		Map<String,Object> map = new HashMap<String,Object>(); 
		map.put("total", list.size());
		map.put("records", list);
		return map;
	}
	
	@ResponseBody
	@RequestMapping("/show")
	public Map<String,Object> show(HttpServletRequest request,Model model){
		String id=request.getParameter("id");
		Area area = this.areaService.selectByPrimaryKey(id);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("data", area);
		return map;
	}
	
	@ResponseBody
	@RequestMapping("/add")
	public Map<String,Object> insert(HttpServletRequest request,Model model) throws ParseException{
		Area renameProject = this.areaService.selectByCode(request.getParameter("name"));
		Map<String,Object> map = new HashMap<String,Object>(); 
		if(renameProject==null){
			Area baseArea=new Area();
			baseArea.setCode(request.getParameter("code"));
			baseArea.setName(request.getParameter("name")); 
			baseArea.setAlias(request.getParameter("alias"));
			baseArea.setPinyin(request.getParameter("pinyin"));
			//Date data = new Date();
			//SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String id=UUID.randomUUID().toString().replace("-", "");
			baseArea.setId(id);
			this.areaService.insert(baseArea);
			map.put("code", "1111");
			map.put("success", true);
			map.put("message", "添加成功！");
		}else{
			map.put("code", "0000");
			map.put("success", false);
			map.put("message", "该行政区划名称已存在！");
		}
		return map;
	}
	
	@ResponseBody
	@RequestMapping("/edit")
	public Map<String,Object> edit(HttpServletRequest request,Model model) throws ParseException{
		String id=request.getParameter("id");
		Area baseArea = this.areaService.selectByPrimaryKey(id);
		Map<String,Object> map = new HashMap<String,Object>(); 
		if(baseArea.equals(null))
		{
			baseArea.setCode(request.getParameter("code"));
			baseArea.setName(request.getParameter("name")); 
			baseArea.setAlias(request.getParameter("alias"));
			baseArea.setPinyin(request.getParameter("pinyin"));
			this.areaService.updateByPrimaryKey(baseArea);
			map.put("code", "1111");
			map.put("success", true);
			map.put("msg", "修改成功！");
		}
		else
		{
			map.put("code", "0000");
			map.put("success", false);
			map.put("message", "该行政区划已存在！");
		}
		
		return map;
	}

	@ResponseBody
	@RequestMapping("/delete")
	public Map<String,Object> delete(HttpServletRequest request,Model model){
		
		Map<String,Object> map = new HashMap<String,Object>(); 
		String id=request.getParameter("id");
		int result=this.areaService.deleteByPrimaryKey(id);
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
}

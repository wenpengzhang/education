<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>教师编辑</title>
    <script type="text/javascript">
        $(document).ready(function () {
        	student.bindForm();
        });
    </script>
</head>
<body>
    <div class="place">
        <span>位置：</span>
        <ul class="placeul">
            <li><a href="#">首页</a></li>
            <li><a href="#">学生管理</a></li>
            <li><a href="#">学生编辑</a></li>
        </ul>
    </div>
    <div class="formbody">
        <form id='form1' action="#" enctype="multipart/form-data" method="post">
        <div class="formtitle">
            <span>学生信息</span></div>
        <ul class="forminfo">
            <li>
                <label>用户名<b>*</b></label>
                <input name="accounts" type="text" class="dfinput" /></li> 
            <li>
                <label>昵称<b>*</b></label>
                <input name="nickname" type="text" class="dfinput" /></li> 
			<li>
                <input id="id" name="id" type="hidden" />
                <label>学生姓名<b>*</b></label>
                <input name="realname" type="text" class="dfinput" /></li>
            <li>
                <label>手机号码<b>*</b></label>
                <input name="mobile" type="text" class="dfinput" /></li> 
            <li>
                <label>父母电话<b>*</b></label>
                <input name="mobileother" type="text" class="dfinput" /></li> 
            <li>
                <label>所在学校<b>*</b></label>
                <input name="school" type="text" class="dfinput" /></li> 
            <li>
                <label>所在年级<b>*</b></label>
                <select name="grade" class="scselect">
                    <option value="初一">初一</option>
                    <option value="初二">初二</option>
                    <option value="初三">初三</option>
                    <option value="高一">高一</option>
                    <option value="高二">高二</option>
                    <option value="高三">高三</option>
                </select></li>
            <li id="liaddress">
                <label>所在地址</label><input id="txtaddress" name="address" type="text" class="dfinput" readonly="true"/></li>  
            <li>
                <label>所在地区<b>*</b></label>
                <select id="sltprovince" name="province" class="scselect" onchange="setCityList(this.value,null);"/>
                <select id="sltcity" name="city" class="scselect" onchange="setDistrictList(this.value,null);"/>
                <select id="sltcounty" name="county" class="scselect" onchange="showAddress();"/></li>
            <li id="lipic">
                <label>学生头像</label>
                <img id="imghead" name="pichead" src="#" height="200" width="200" />
           <li id="liupload">
                <label>图像上传</label>
                <input id="filehead" name="file" type="file" class="dfinput" ></li>
            <li>
                <label>
                    &nbsp;</label><input id='btnsubmit' name="" type="button" class="btn" value="确认保存" />&nbsp;&nbsp;
                    <input id='btnlist' name="" type="button" class="btn" value="返回列表" /></li>
        </ul>
        </form>
    </div>
</body>
</html>

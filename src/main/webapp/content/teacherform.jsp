<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>教师编辑</title>
    <script type="text/javascript">
        $(document).ready(function () {
        	teacher.bindForm();
        });
    </script>
</head>
<body>
    <div class="place">
        <span>位置：</span>
        <ul class="placeul">
            <li><a href="#">首页</a></li>
            <li><a href="#">教师管理</a></li>
            <li><a href="#">教师编辑</a></li>
        </ul>
    </div>
    <div class="formbody">
        <form id='form1' action="#" enctype="multipart/form-data" method="post">
        <div class="formtitle">
            <span>教师信息</span></div>
        <ul class="forminfo">
            <li>
            	<input id="id" name="id" type="hidden" />
                <label>教师姓名<b>*</b></label><input name="realname" type="text" class="dfinput" /></li>
            <li>
                <label>账号<b>*</b></label><input name="mobile" type="text" class="dfinput" /></li>
            <li>
                <label>身份证号<b>*</b></label><input name="idnumber" type="text" class="dfinput" /></li>
            <li>
             <li>
                <label>初始密码<b>*</b></label><input name="password" type="text" class="dfinput" /></li>
            <li>
                <label>性    别</label>
                 <select name="sex" class="scselect">
                    <option value="男">男</option>
                    <option value="女">女</option>
                </select></li> 
            <li>
                <label>出生年月</label><input id="datebirthday" name="birthday" type="text" class="dfinput" /></li>  
            <li id="liaddress">
                <label>所在地址</label><input id="txtaddress" name="address" type="text" class="dfinput" readonly="true"/></li>  
            <li id="lisetaddress">
                <label>地区选择<b>*</b></label>
                <select id="sltprovince" name="province" class="scselect" onchange="setCityList(this.value,null);"/>
                <select id="sltcity" name="city" class="scselect" onchange="setDistrictList(this.value,null);"/>
                <select id="sltcounty" name="county" class="scselect" onchange="showAddress();"/></li>
           <li>
                <label>教师类型</label>
                 <select name="stype" class="scselect">
                    <option value="在职教师">在职教师</option>
                    <option value="在校学生">在校学生</option>
                    <option value="全职教师">全职教师</option>
                </select></li> 
           <li>
                <label>所在学校</label><input name="school" type="text" class="dfinput" /></li>
           <li>
                <label>学    历</label><input name="degrees" type="text" class="dfinput" /></li>
           <li>
                <label>教     龄</label><input name="seniority" type="text" class="dfinput" /></li>
           <li id="lisubject">
                <label>可教科目</label><input id="txtsubject" name="subject" type="text" class="dfinput" readonly="true" style="width:600px" /></li>
           <li id="lisetsubject">
                <label>可教科目编辑</label>
                <div style="border:1px solid #b8dcff;float:left;">
                	年级
                 <select id="sltgrade" name="vgrade" class="scselect">
                    <option value="初一">初一</option>
                    <option value="初二">初二</option>
                    <option value="初三">初三</option>
                    <option value="中考">中考</option>
                    <option value="高一">高一</option>
                    <option value="高二">高二</option>
                    <option value="高三">高三</option>
                    <option value="高考">高考</option>
                </select>
                	科目
                 <select id="sltsubject" name="vsubject" class="scselect">
                    <option value="语">语</option>
                    <option value="数">数</option>
                    <option value="外">外</option>
                    <option value="理">理</option>
                    <option value="化">化</option>
                    <option value="生">生</option>
                    <option value="政">政</option>
                    <option value="政">史</option>
                    <option value="政">地</option>
                    <option value="政">科</option>
                    <option value="政">奥</option>
                </select>
               
                <input id='btnaddsub' name="" type="button" class="btn" value="添加可教科目" />
                <input id='btnclearsub' name="" type="button" class="btn" value="清除可教科目" />
                </div></li>
           <li>
                <label>教学特点</label><textarea name="feature" cols="" rows="" class="textinput"></textarea></li>
           <li>
                <label>简    历</label><textarea name="cv" cols="" rows="" class="textinput"></textarea></li>
           <li id="lipic">
                <label>教师图像</label>
                <img id="imghead" name="pichead" src="#" height="200" width="200" />
                <img id="imgidcard" name="picidcard" src="#" height="200" width="200" />
                <img id="imgeducation" name="piceducation" src="#" height="200" width="200" />
                <img id="imgteacher" name="picteacher" src="#" height="200" width="200" />
                <img id="imgother" name="picother" src="#" height="200" width="200" />
                </li>
           <li id="liupload">
                <label>图像上传</label>
                <div id="divupload" style="border:1px solid #b8dcff;float:left;">
                <div><label>教师头像</label><input id="filehead" name="filehead" type="file" class="dfinput" /></div>
                <div><label>身份证正反面</label><input id="fileidcord" name="fileidcord" type="file" class="dfinput" /></div>
                <div><label>学历认证</label><input id="fileedu" name="fileedu" type="file" class="dfinput" /></div>
                <div><label>教师资格证</label><input id="fileteacher" name="fileteacher" type="file" class="dfinput" /></div>
                <div><label>其他资质</label><input id="fileother" name="fileother" type="file" class="dfinput" /></div>
                </div></li>
           <li>
                <label>教学经历</label>
                <div id="divnew" style="border:1px solid #b8dcff;float:left;"></div>
                </li>
            <li>
                <label>
                    &nbsp;</label>
                    <input id='btnaddexp' name="" type="button" class="btn" value="添加教学经历" />&nbsp;&nbsp;
                    <input id='btnsubmit' name="" type="button" class="btn" value="确认保存" />&nbsp;&nbsp;
                    <input id='btnlist' name="" type="button" class="btn" value="返回列表" /></li>
        </ul>
        </form>
    </div>
</body>
</html>

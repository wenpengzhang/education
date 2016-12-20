<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>课程编辑</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script type="text/javascript">
        $(document).ready(function () {
        	course.bindForm();
        });
    </script>
</head>
<body>
    <div class="place">
        <span>位置：</span>
        <ul class="placeul">
            <li><a href="#">首页</a></li>
            <li><a href="#">课程管理</a></li>
            <li><a href="#">课程编辑</a></li>
        </ul>
    </div>
    <div class="formbody">
        <form id='form1' action="#" enctype="multipart/form-data" method="post">
        <div class="formtitle">
            <span>课程信息</span></div>
        <ul class="forminfo">
            <li>
            	<input id="id" name="id" type="hidden" />
                <label>课程名字<b>*</b></label>
                <input name="name" type="text" class="dfinput" /></li>
            <li>
                <label>年级科目<b>*</b></label>
                <select name="vstage" class="scselect">
                    <option value="初中">初中</option>
                    <option value="初中">高中</option>
                </select>
                <select name="vsubject" class="scselect">
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
                <select name="vgrade" class="scselect">
                    <option value="初一">初一</option>
                    <option value="初二">初二</option>
                    <option value="初三">初三</option>
                    <option value="中考">中考</option>
                    <option value="高一">高一</option>
                    <option value="高二">高二</option>
                    <option value="高三">高三</option>
                    <option value="高考">高考</option>
                </select>
                </li>
            <li>
                <label>课程模式<b>*</b></label>
                 <select name="cmode" class="scselect">
                    <option value="直播课">直播课</option>
                    <option value="视频课">视频课</option>
                    <option value="面授课">面授课</option>
                </select></li>
            <li>
                <label>课程类型<b>*</b></label>
                <select name="firsttype" class="scselect">
                    <option value="系统班">系统班</option>
                    <option value="精品课">精品课</option>
                    <option value="一对一">一对一</option>
                </select>
                <select name="sectype" class="scselect">
                    <option value="学生上门">学生上门</option>
                    <option value="老师上门">老师上门</option>
                </select></li>
            <li>
                <label>价  格<b>*</b></label>
                <input name="price" type="text" class="dfinput" /></li>
            <li>
                <label>课程名额<b>*</b></label>
                <input name="ccount" type="text" class="dfinput" /></li>
            <li>
                <label>教材版本</label>
                <input name="cversion" type="text" class="dfinput" /></li>
            <li>
                <label>适合地区</label>
                <input name="area" type="text" class="dfinput" /></li>
            <li>
                <label>起止时间</label>
                <input name="timezones" type="text" class="dfinput" /></li>
            <li>
                <label>课程大纲<b>*</b></label>
                 <div id="divnew" style="border:1px solid #b8dcff;float:left;"></div>
                 </li>
            <li>
                <label>课程描述</label>
                <textarea name="discription" cols="" rows="" class="textinput"></textarea></li>
            <li id="lipic">
                <label>教师图像</label>
                <div id="divimg"></div>
            <li id="liupload">
                <label>图片上传</label>
                <div id="divfile" style="border:1px solid #b8dcff;float:left;" ></div></li>
            <li>
                <label>
                    &nbsp;</label><input id='btnaddsub' name="" type="button" class="btn" value="添加课程大纲" />&nbsp;&nbsp;
                    <input id='btnaddfile' name="" type="button" class="btn" value="添加图片上传" />&nbsp;&nbsp;
                    <input id='btnsubmit' name="" type="button" class="btn" value="确认保存" />&nbsp;&nbsp;
                    <input id='btnlist' name="" type="button" class="btn" value="返回列表" /></li>
        </ul>
        </form>
    </div>
</body>
</html>
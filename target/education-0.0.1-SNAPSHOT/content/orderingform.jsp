<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>课程编辑</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script type="text/javascript">
        $(document).ready(function () {
        	ordering.bindForm();
        });
    </script>
</head>
<body>
    <div class="place">
        <span>位置：</span>
        <ul class="placeul">
            <li><a href="#">首页</a></li>
            <li><a href="#">订单管理</a></li>
            <li><a href="#">下单</a></li>
        </ul>
    </div>
    <div class="formbody">
        <form id='form1' action="#">
        <div class="formtitle">
            <span>订单信息</span></div>
        <ul class="forminfo">
            <li>
            	<input id="id" name="id" type="hidden" />
                <label>订单学生<b>*</b></label>
                <select id="sltstudent" name="studentid" class="scselect"></select></li>
            <li>
                <label>订单课程<b>*</b></label>
                <select id="sltcourse" name="courseid" class="scselect"></select></li>
            <li>
                <label>年级科目</label>
                <input name="vstage" type="text" class="dfinput" disabled="true"/>
                <input name="vsubject" type="text" class="dfinput" disabled="true"/>
                <input name="vgrade" type="text" class="dfinput" disabled="true"/></li>
            <li>
                <label>课程模式</label>
                <input name="cmode" type="text" class="dfinput" disabled="true"/>
                <input name="firsttype" type="text" class="dfinput" disabled="true"/>
                <input name="sectype" type="text" class="dfinput" disabled="true"/></li>
            <li>
                <label>价  格</label>
                <input name="price" type="text" class="dfinput" disabled="true"/></li>
            <li>
                <label>课程名额</label>
                <input name="ccount" type="text" class="dfinput" disabled="true"/></li>
            <li>
                <label>教材版本</label>
                <input name="cversion" type="text" class="dfinput" disabled="true"/></li>
            <li>
                <label>适合地区</label>
                <input name="area" type="text" class="dfinput" disabled="true"/></li>
            <li>
                <label>起止时间</label>
                <input name="timezones" type="text" class="dfinput" disabled="true"/></li>
            <li>
                <label>课程描述</label>
                <input name="discription" type="text" class="dfinput" disabled="true"/></li>
            <li>
                <label>
                    &nbsp;</label><input id='btnsubmit' name="" type="button" class="btn" value="确认保存" /></li>
        </ul>
        </form>
    </div>
</body>
</html>
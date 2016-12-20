<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>评价编辑</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script type="text/javascript">
        $(document).ready(function () {
        	appraise.bindForm();
        });
    </script>
</head>
<body>
    <div class="place">
        <span>位置：</span>
        <ul class="placeul">
            <li><a href="#">首页</a></li>
            <li><a href="#">评价管理</a></li>
            <li><a href="#">评价信息</a></li>
        </ul>
    </div>
    <div class="formbody">
        <form id='form1' action="#">
        <div class="formtitle">
            <span>评价信息</span></div>
        <ul class="forminfo">
            <li>
            	<input id="id" name="id" type="hidden" />
                <label>评价学生<b>*</b></label>
                <select id="sltstudent" name="studentid" class="scselect"></select></li>
            <li>
                <label>评价课程<b>*</b></label>
                <select id="sltcourse" name="subcourseid" class="scselect"></select></li>
            <li>
               <label>评价类型</label>
               <select name="grade" class="scselect">
                    <option value="好评">好评</option>
                    <option value="中评">中评</option>
                    <option value="差评">差评</option>
                </select></li>
           	<li>
               <label>描述相符评分</label>
               <select name="factscore" class="scselect">
                    <option value="5">5</option>
                    <option value="4">4</option>
                    <option value="3">3</option>
                    <option value="2">2</option>
                    <option value="1">1</option>
                </select></li>
           	<li>
               <label>教学态度评分</label>
               <select name="attitudescore" class="scselect">
                    <option value="5">5</option>
                    <option value="4">4</option>
                    <option value="3">3</option>
                    <option value="2">2</option>
                    <option value="1">1</option>
                </select></li>
            <li>
               <label>教学质量评分</label>
               <select name="qualityscore" class="scselect">
                    <option value="5">5</option>
                    <option value="4">4</option>
                    <option value="3">3</option>
                    <option value="2">2</option>
                    <option value="1">1</option>
                </select></li>
            <li>
                <label>评价详情</label>
                <textarea name="advise" cols="" rows="" class="textinput"></textarea></li>
            <li>
                <label>
                    &nbsp;</label><input id='btnsubmit' name="" type="button" class="btn" value="确认保存" /></li>
        </ul>
        </form>
    </div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>老师在哪儿</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=Edge">

<link type="text/css" rel="stylesheet" href="css/style.css" />
<link type="text/css" rel="stylesheet" href="css/select.css" />
<link type="text/css" rel="stylesheet" href="css/jqueryUI/jquery-ui.min.css" />
<link type="text/css" rel="stylesheet" href="css/alertify/alertify.core.css" />
<link type="text/css" rel="stylesheet" href="css/alertify/alertify.default.css" />
<link type="text/css" rel="stylesheet" href="css/jOrgChart/jquery.jOrgChart.css" />
<link type="text/css" rel="stylesheet" href="css/zTreeStyle/zTreeStyle.css" />
<link type="text/css" rel="stylesheet" href="css/flot/flot.css" />
<link type="text/css" rel="stylesheet" href="css/combo.select.css">

<script type="text/javascript" src="jquery/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="jquery/jquery-ui.min.js"></script>
<script type="text/javascript" src="jquery/jquery-ui.extend.js"></script>
<script type="text/javascript" src="jquery/jquery.validate.min.js"></script>
<script type="text/javascript" src="jquery/additional-methods.js"></script>
<script type="text/javascript" src="jquery/jquery.ztree.core-3.5.min.js"></script>
<script type="text/javascript" src="jquery/jquery.ztree.excheck-3.5.min.js"></script>
<script type="text/javascript" src="jquery/jquery.jOrgChart.js"></script>
<script type="text/javascript" src="jquery/jquery.mask.js"></script>
<script type="text/javascript" src="jquery/jquery.form.js"></script>
<script type="text/javascript" src="jquery/alertify.js"></script>
<script type="text/javascript" src="jquery/additional-methods.js"></script>
<script type="text/javascript" src="jquery/jquery.combo.select.js"></script>

<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/uploadpreview.js"></script>
<script type="text/javascript" src="js/districts.js"></script>
<script type="text/javascript" src="js/teachermanager.js"></script>
<script type="text/javascript" src="js/coursemanager.js"></script>
<script type="text/javascript" src="js/studentmanager.js"></script>
<script type="text/javascript" src="js/orderingmanager.js"></script>
<script type="text/javascript" src="js/appraisemanager.js"></script>
<script type="text/javascript" src="js/statistics.js"></script>
<script type="text/javascript" src="js/initialize.js"></script>
</head>
<body>
	<div id="header" style="background: url(images/topbg.gif) repeat-x; height: 88px">
        <div class="topleft">
            <a href="#" target="_parent">
                <img src="images/logo.png" alt="老师在哪儿" /></a>
        </div>
        <ul class="nav">
            <li><a href="index.jsp" class="selected" onclick="replace('content/statistics.jsp')">
                <img src="images/icon01.png" alt="财务统计" /><h2>财务统计</h2>
            </a></li>
            <li><a href="#" onclick="replace('content/orderinglist.jsp')">
                <img src="images/icon02.png" alt="订单管理" /><h2>订单管理</h2>
            </a></li>
            <li><a href="#" onclick="replace('content/teacherlist.jsp')">
                <img src="images/iconA.png" alt="教师管理" /><h2>教师管理</h2>
            </a></li>
            <li><a href="#" onclick="replace('content/courselist.jsp')">
                <img src="images/icon04.png" alt="课程管理" /><h2>课程管理</h2>
            </a></li>
            <li><a href="#" onclick="replace('content/studentlist.jsp')">
                <img src="images/icon06.png" alt="会员管理" /><h2>会员管理</h2>
            </a></li>
            <li><a href="#" onclick="replace('content/appraiselist.jsp')">
                <img src="images/icon07.png" alt="评价管理" /><h2>评价管理</h2>
            </a></li>
        </ul>
        <div class="topright">
            <ul>
                <li><a href="Logout.aspx" target="_parent">退出</a></li>
            </ul>
            <div class="user">
                <span></span> 
            </div>
        </div>
    </div>
	<div id="mainer">
	    <div id="content">
	    </div>
	</div>
</body>
</html>
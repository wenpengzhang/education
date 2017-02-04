<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" import="com.edu.core.domain.User"%>
<%@ page language="java" import="javax.servlet.http.HttpSession"%>
<%
	HttpSession session1 = request.getSession();
	User user = (User) session1.getAttribute("user");
	String username = null;
	if (user != null) {
		username = user.getAccounts();
	}
	System.out.println("username=" + username);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>老师在哪儿</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=Edge">

<link type="text/css" rel="stylesheet" href="css/style.css" />
<link type="text/css" rel="stylesheet" href="css/select.css" />
<link type="text/css" rel="stylesheet"
	href="css/jqueryUI/jquery-ui.min.css" />
<link type="text/css" rel="stylesheet"
	href="css/alertify/alertify.core.css" />
<link type="text/css" rel="stylesheet"
	href="css/alertify/alertify.default.css" />
<link type="text/css" rel="stylesheet"
	href="css/jOrgChart/jquery.jOrgChart.css" />
<link type="text/css" rel="stylesheet"
	href="css/zTreeStyle/zTreeStyle.css" />
<link type="text/css" rel="stylesheet" href="css/flot/flot.css" />
<link type="text/css" rel="stylesheet" href="css/combo.select.css">

<script type="text/javascript" src="jquery/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="jquery/jquery-ui.min.js"></script>
<script type="text/javascript" src="jquery/jquery-ui.extend.js"></script>
<script type="text/javascript" src="jquery/jquery.validate.min.js"></script>
<script type="text/javascript" src="jquery/additional-methods.js"></script>
<script type="text/javascript" src="jquery/jquery.ztree.core-3.5.min.js"></script>
<script type="text/javascript"
	src="jquery/jquery.ztree.excheck-3.5.min.js"></script>
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
<script type="text/javascript">
	function hreftest(i){
		//判断是否有登录用户
		var username = '<%=username%>';

		if (username == "null") {
			alert("请先登录！");
			window.location = "login.jsp";
		} else {
			var replace1 = "content/statistics.jsp";
			var replace2 = "content/orderinglist.jsp";
			var replace3 = "content/teacherlist.jsp";
			var replace4 = "content/courselist.jsp";
			var replace5 = "content/studentlist.jsp";
			var replace6 = "content/appraiselist.jsp";
			if (i == 1) {
				if (username == "LsznRootManger") {
					replace(replace1);
				}
				if (username == "LsznJiaowuManager") {
					alert("您没有该权限！");
				}
				if (username == "LsznyewuManager") {
					alert("您没有该权限！");
				}
			}
			if (i == 2) {
				if (username == "LsznRootManger") {
					replace(replace2);
				}
				if (username == "LsznJiaowuManager") {
					alert("您没有该权限！");
				}
				if (username == "LsznyewuManager") {
					replace(replace2);
				}
			}
			if (i == 3) {
				if (username == "LsznRootManger") {
					replace(replace3);
				}
				if (username == "LsznJiaowuManager") {
					alert("您没有该权限！");
				}
				if (username == "LsznyewuManager") {
					alert("您没有该权限！");
				}
			}
			if (i == 4) {
				if (username == "LsznRootManger") {
					replace(replace4);
				}
				if (username == "LsznJiaowuManager") {
					replace(replace4);
				}
				if (username == "LsznyewuManager") {
					alert("您没有该权限！");
				}
			}
			if (i == 5) {
				if (username == "LsznRootManger") {
					replace(replace5);
				}
				if (username == "LsznJiaowuManager") {
					alert("您没有该权限！");
				}
				if (username == "LsznyewuManager") {
					
					replace(replace5);
				}
			}
			if (i == 6) {
				if (username == "LsznRootManger") {
					replace(replace6);
				}
				if (username == "LsznJiaowuManager") {
					alert("您没有该权限！");
				}
				if (username == "LsznyewuManager") {
					alert("您没有该权限！");
				}
			}
		}
	}
</script>
</head>
<body>
	<div id="header"
		style="background: url(images/topbg.gif) repeat-x; height: 88px">
		<div class="topleft">
			<a href="#" target="_parent"> <img src="images/logo.png"
				alt="老师在哪儿" /></a>
		</div>
		<ul class="nav">
			<li><a href="#" class="selected" onclick="hreftest(1)"> <img
					src="images/icon01.png" alt="财务统计" />
					<h2>财务统计</h2>
			</a></li>
			<li><a href="#" onclick="hreftest(2)"> <img
					src="images/icon02.png" alt="订单管理" />
					<h2>订单管理</h2>
			</a></li>
			<li><a href="#" onclick="hreftest(3)"> <img
					src="images/iconA.png" alt="教师管理" />
					<h2>教师管理</h2>
			</a></li>
			<li><a href="#" onclick="hreftest(4)"> <img
					src="images/icon04.png" alt="课程管理" />
					<h2>课程管理</h2>
			</a></li>
			<li><a href="#" onclick="hreftest(5)"> <img
					src="images/icon06.png" alt="会员管理" />
					<h2>会员管理</h2>
			</a></li>
			<li><a href="#" onclick="hreftest(6)"> <img
					src="images/icon07.png" alt="评价管理" />
					<h2>评价管理</h2>
			</a></li>
		</ul>
		<div class="topright">
			<ul>
				<li><a href="login.jsp" target="_parent">退出</a></li>
			</ul>
			<div class="user">
				<span></span>
			</div>
		</div>
	</div>
	<div id="mainer">
		<div id="content"></div>
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>老师去哪儿登录界面</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="jquery/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="js/cloud.js"></script>

<script language="javascript">
	$(function() {
		$('.loginbox').css({
			'position' : 'absolute',
			'left' : ($(window).width() - 692) / 2
		});
		$(window).resize(function() {
			$('.loginbox').css({
				'position' : 'absolute',
				'left' : ($(window).width() - 692) / 2
			});
		})
	});

	function login() {
		var name = $("#user_name").val();
		var pass = $("#pass_word").val();
		if (name && pass) {
			$.ajax({
				cache : false,
				type : "POST",
				dataType : "json", //返回json格式的数据
				url : "user/login",
				data : {
					"username" : name,
					"password" : pass
				},
				success : function(response) {
					if (response.success == true) {
						window.location = "index.jsp";
					} else {
						alert("用户名或密码错误！");
					}
				},
				error : function(xhr, ajaxOptions, thrownError) {
				}
			});
		} else {
			alert("请输入相关用户名和密码！");
		}
	}
</script>

</head>
<body
	style="background-color: #1c77ac; background-image: url(images/light.png); background-repeat: no-repeat; background-position: center top; overflow: hidden;">



	<div id="mainBody">
		<div id="cloud1" class="cloud"></div>
		<div id="cloud2" class="cloud"></div>
	</div>


	<div class="logintop">
		<span>欢迎登录老师去哪儿后台管理界面</span>
		<ul>
			<li><a href="#">回首页</a></li>
		</ul>
	</div>

	<div class="loginbody">

		<span class="systemlogo"></span>

		<div class="loginbox">

			<ul>
				<li><input id="user_name" type="text" class="loginuser"
					value="admin" onclick="JavaScript:this.value=''" /></li>
				<li><input id="pass_word" type="password" class="loginpwd"
					value="" onclick="JavaScript:this.value=''" /></li>
				<li><input id="btnlogin" type="button" class="loginbtn"
					value="登录" onclick="login();" /></li>
			</ul>


		</div>

	</div>


</body>

</html>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'index.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<title>登录</title>
<script src="js/jquery-1.6.3.js"></script>
<script src="js/jquery-3.3.1.min.js"></script>
<script src="js/default.js"></script>
<script src="js/index.js"></script>
<script src="layer/layer.js"></script>
<link rel="stylesheet" href="css/index.css">
</head>

<body>
	<canvas id="circleCanvas" style="position:fixed;z-index: -1;"
		width="1000px" height="500px"></canvas>
	<div class="container">
		<div class="login-box">
			<div id="tipText">登录</div>
			<div class="input-box" id="input-box">
				<form id="loginForm">
					<div class="input-item">
						<img src="images/user_icon_copy.png"
							style="width:14px;height: 14px;"> <input name="nameInput"
							placeholder="请输入用户名" maxlength="16" type="text">
					</div>
					<div class="input-item">
						<img src="images/lock_icon_copy.png"
							style="width:12px;height: 16px;"> <input name="pwdInput"
							placeholder="请输入密码" maxlength="16" type="password"
							style="margin-left: 3px;">
					</div>
					<div class="input-item">
						<img src="images/key.png" style="width:17px;height: 15px;">
						<input name="keyInput" placeholder="验证码" maxlength="4" type="text"
							style="width: 120px;">
						<div id="keyValue" style="display: inline;">4564</div>
					</div>
					<div class='login-btn'>
						<input type='button' value='学生登录' id="sub_student"> <input
							type='button' value='教师登录' style="margin-left: 15px;"
							id="sub_teacher">
					</div>
					<div class='input-bottom'>
						<p style="font-size: 12px;color:#afb1be;">欢迎登录专业设计选课系统</p>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>

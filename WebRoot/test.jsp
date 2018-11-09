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

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<title>我的课程</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/bootstrap-table.min.css">

</head>

<body style="background-color: #F8F8F8;width: 70%;">

	<div class="container">
		<div class="row">
			<table id="tb_scores" class="col-lg-12"></table>
		</div>
	</div>

	<script src="js/jquery-3.3.1.min.js"></script>
	<script src="js/default.js"></script>
	<script src="js/main-student.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/bootstrap-table.js"></script>
	<script src="js/myCourse-teacher.js"></script>
</body>
</html>

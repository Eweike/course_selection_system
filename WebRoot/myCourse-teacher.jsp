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
<link rel="stylesheet" href="css/default.css">
<link rel="stylesheet" href="css/main-student.css">
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/myCourse-teacher.css">
<link rel="stylesheet" href="css/bootstrap-table.css">

</head>

<body style="background-color: #F8F8F8;width: 70%;">
	<div class="navbar navbar-default navbar-fixed-top" role="navigation"
		id="top-navbar">
		<!--<div class="container">-->
		<div class="navbar-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="#"
					style="letter-spacing: 1px;color: #fff"> <small> <span
						class="glyphicon glyphicon-send"></span> 华信软件学院2015级选课系统
				</small>
				</a>
			</div>
			<!--navbar-header-->
			<ul class="nav navbar-nav navbar-right" style="margin-right: 0px;">
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" style="padding: 7px;"> <img
						src="images/portrait.png" class="user-portrait" alt="暂无..">
						<span class="caret" style="color: #fff;"></span>
				</a>
					<ul class="dropdown-menu pull-right" id="user-menu">
						<li><a href="#"><span class="glyphicon glyphicon-user"></span>个人中心</a></li>
						<li><a href="#"><span class="glyphicon glyphicon-off"></span>退出登录</a></li>
					</ul></li>
			</ul>
			<ul class="nav navbar-nav navbar-right" id="menu-list">
				<li class="actived"><a href="javascript:goMain();"><span
						class="glyphicon glyphicon-home" id="go_main_page"></span> 首页</a></li>
				<li><a href="#"><span class="glyphicon glyphicon-tasks"></span>
						选课中心</a></li>
				<li><a href="#"><span class="glyphicon glyphicon-envelope"></span>
						我的消息</a></li>
				<li><a href="#"><span class="glyphicon glyphicon-bullhorn"></span>
						联系我们</a></li>
			</ul>

		</div>
		<!--navbar-fluid-->
	</div>
	<!--navbar-->
	<div style="margin-top: 50px;">
		<div class="container-fluid" style="padding: 0;">
			<div class="row"
				style="width: 100%;margin-left: 0px;margin-right: 0;border: 1px solid #ccc;margin-bottom:90px;">
				<nav class="col-md-2" style="padding-left: 0;padding-right: 0;">
				<ul class="nav nav-pills nav-stacked" style="text-align: center;">
					<li class="active" style="border-radius: 0;"><a href="#">功能列表</a></li>
					<li><a href="#">课程中心</a></li>
					<li class="dropdown"><a href="javascript:open1();"> 个人中心 <span
							class="caret"></span>
					</a>
						<ul class="nav nav-pills nav-stacked open-menu" id="menu_1"
							style="text-align: center;">
							<li><a href="#">基本资料</a></li>
							<li><a href="javascript:goMyCourse();"
								style="background-color: #eee;">我的课程</a></li>
						</ul></li>
					<li><a href="#">留言反馈</a></li>
					<li><a href="javascript:login_manager();">登录管理员</a></li>
				</ul>
				<div id="tips">
					<p>咨询QQ：2923471252</p>
					<p>
						&nbsp;&nbsp;友情提示： <font color="red">学生登录新教务选课系统的初始账号为个人学号，初始密码为身份证号后6位(若身份证号包含X请注意用大写，若系统中身份证号为空则密码为学号)，该信息同步于学校数字校园信息门户，</font>学生若需更改个人密码需登录【信息门户](http://my.tjut.edu.cn/)进行修改(修改后教务选课系统密码自动同步)。
					</p>
					<p>&nbsp;&nbsp;为了更好的保证选课操作的流畅性，请使用IE8及以上版本、Chrome(谷歌)、Firefox(火狐)等浏览器，不建议使用IE6、IE7等老版本浏览器。</p>
				</div>
				</nav>
				<div class="col-md-10 breadcrumbs"
					style="padding-right: 0;padding-left: 0;border-left: 1px solid #ccc;">
					<ul class="breadcrumb"
						style="height: 40px;line-height: 23.2px;background-color: #F5F5F5;border: 1px solid #ccc;">
						<li><span class="glyphicon glyphicon-home"></span> <a
							href="javascript:goMain();">首页</a></li>
						<li><a href="#">个人中心</a></li>
						<li class="active" id="bread-last">本学期课程</li>
					</ul>
					<!-- .breadcrumb -->
					<div style="width: 100%;margin-bottom:20px;">
						<div class="nav-btn-box">
							<a class="btn btn-info" id="goThisCourse" role="button" disabled>本学期课程</a>
							<a class="btn btn-info" id="goOtherCourse" role="button">我的历史课程</a>
						</div>
						<div class="table-box" id="thisCourse">
							<table id="course-tab">
								<caption>当前学期个人课程表</caption>
								<tr>
									<th style="width:80px;">
										<div class="out">
											<b></b> <em></em>
										</div>
									</th>
									<th>周一</th>
									<th>周二</th>
									<th>周三</th>
									<th>周四</th>
									<th>周五</th>
								</tr>
								<tr>
									<td class="t1">第一节</td>
									<td>毛泽东思想和中国特色社会主义理论概论体系</td>
									<td></td>
									<td>大学英语（三）</td>
									<td>JavaScript从入门到放弃</td>
									<td></td>
								</tr>
								<tr>
									<td class="t1">第二节</td>
									<td></td>
									<td>C语言从删库到跑路</td>
									<td></td>
									<td>PHP与颈椎病康复指南</td>
									<td></td>
								</tr>
								<tr>
									<td></td>
								</tr>
								<tr>
									<td class="t1">第三节</td>
									<td>Python高级程序设计</td>
									<td></td>
									<td></td>
									<td></td>
									<td>数据库原理及应用</td>
								</tr>
								<tr>
									<td class="t1">第四节</td>
									<td></td>
									<td>Java入门与实践</td>
									<td></td>
									<td></td>
									<td></td>
								</tr>
							</table>
						</div>
						<div class="panel-body" id="otherCourse"
							style="padding-bottom:0px;" hidden>
							<!-- <div class="panel panel-default">
								<div class="panel-heading">查询条件</div>
								<div class="panel-body">
									<form id="formSearch" class="form-horizontal">
										<div class="form-group" style="margin-top:15px">
											<label class="control-label col-sm-1"
												for="txt_search_departmentname">课程名称</label>
											<div class="col-sm-3">
												<input type="text" class="form-control"
													id="txt_search_departmentname">
											</div>
											<label class="control-label col-sm-1" for="txt_search_statu">排序方式</label>
											<div class="col-sm-3">
												<select class="form-control">
													<option>请选择</option>
													<option>课程编号</option>
													<option>单科成绩</option>
												</select>
											</div>
											<div class="col-sm-4" style="text-align:left;">
												<button type="button" style="margin-left:50px"
													id="btn_query" class="btn btn-primary">查询</button>
											</div>
										</div>
									</form>
								</div>
							</div> -->
							<!-- 							<div id="toolbar" class="btn-group">
								<button id="btn_add" type="button" class="btn btn-default">
									<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
								</button>
								<button id="btn_edit" type="button" class="btn btn-default">
									<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改
								</button>
								<button id="btn_delete" type="button" class="btn btn-default">
									<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
								</button>
							</div> -->
							<table id="tb_scores"></table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="navbar navbar-default navbar-fixed-bottom"
		role="navigation" id="top-navbar">
		<!--<div class="container">-->
		<div class="navbar-fluid">
			<div class="navbar-header"
				style="width: 100%;text-align: center;padding: 10px 0;color:#fff;letter-spacing: 1px;">
				<p>版权所有 © 天津理工大学 华信软件学院 2015级1班</p>
				<p>技术支持：天津市西青区 联系我们</p>
			</div>
		</div>
	</div>
	<div id="screen" hidden></div>
	<div class="login_manager" hidden>
		<form class="form-horizontal" role="form">
			<div class="form-group" style="margin-top: 20px;">
				<h4 style="text-align: center;margin-bottom: 30px;">管理员登录</h4>
				<label for="pwd" class="col-sm-5 control-label">管理员密码：</label>
				<div class="col-sm-5">
					<input type="password" class="form-control" id="pwd"
						placeholder="请输入密码">
				</div>
			</div>
			<div class="form-group" style="margin-top: 30px;">
				<div class="col-sm-5 col-sm-offset-4">
					<button type="submit" class="btn btn-info"
						style="padding-left: 20px;padding-right: 20px;">确认登录</button>
				</div>
			</div>
		</form>
	</div>

	<script src="js/jquery-3.3.1.min.js"></script>
	<script src="js/default.js"></script>
	<script src="js/main-student.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/bootstrap-table.js"></script>
	<script src="js/myCourse-teacher.js"></script>
	<script src="layer/layer.js"></script>
	<script>
		function login_manager() {
			$("#screen").show(500);
			$(".login_manager").show(500);
		};
		$("#screen").click(function() {
			$(".login_manager").hide(500);
			$("#screen").hide(500);
		});

		function open1() {
			if ($("#menu_1").is(":hidden")) {
				$("#menu_1").show(500);
			} else {
				$("#menu_1").hide(500);
			}
		};
		function goMain() {
			window.location.href = "main-teacher.jsp";
		};
		function goMyCourse() {
			window.location.href = "myCourse-teacher.jsp";
		}
	</script>
</body>
</html>

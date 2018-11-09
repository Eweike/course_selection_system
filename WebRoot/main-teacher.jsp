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
<title>教师首页</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="css/default.css">
<link rel="stylesheet" href="css/main-student.css">
<link rel="stylesheet" href="css/bootstrap.min.css">
<style type="text/css">
.login_manager {
	width: 400px;
	height: 200px;
	background-color: #fff;
	border-radius: 10px;
	position: absolute;
	top: 30%;
	left: 36%;
}

#screen {
	width: 100%;
	height: 100%;
	background: #000;
	opacity: 0.3;
	position: fixed;
	top: 0;
}

.open-menu a {
	font-size: 13px;
}
</style>

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
				<li class="actived"><a href="#"><span
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
				style="width: 100%;margin-left: 0px;margin-right: 0;">
				<nav class="col-md-2" style="padding-left: 0;padding-right: 0;">
				<ul class="nav nav-pills nav-stacked" style="text-align: center;">
					<li class="active"><a href="#">功能列表</a></li>
					<li><a href="#">课程中心</a></li>
					<li class="dropdown"><a href="javascript:open1();"> 个人中心 <span
							class="caret"></span>
					</a>
						<ul class="nav nav-pills nav-stacked open-menu" id="menu_1"
							style="text-align: center;" hidden>
							<li><a href="#">基本资料</a></li>
							<li><a href="javascript:goMyCourse();">我的课程</a></li>
						</ul></li>
					<li><a href="#">留言反馈</a></li>
					<li><a href="javascript:login_manager();">登录管理员</a></li>
				</ul>
				<div
					style="width: 95%;padding: 6px;border: 1px solid #ccc;text-align: center;margin-top: 40px;margin-left: 8px;">
					<p>咨询QQ：2923471252</p>
					<p>
						&nbsp;&nbsp;友情提示： <font color="red">学生登录新教务选课系统的初始账号为个人学号，初始密码为身份证号后6位(若身份证号包含X请注意用大写，若系统中身份证号为空则密码为学号)，该信息同步于学校数字校园信息门户，</font>学生若需更改个人密码需登录【信息门户](http://my.tjut.edu.cn/)进行修改(修改后教务选课系统密码自动同步)。
					</p>
					<p>&nbsp;&nbsp;为了更好的保证选课操作的流畅性，请使用IE8及以上版本、Chrome(谷歌)、Firefox(火狐)等浏览器，不建议使用IE6、IE7等老版本浏览器。</p>
				</div>
				</nav>
				<div class="col-md-10 breadcrumbs"
					style="padding-right: 0;padding-left: 0;height: 40px;border: 1px solid #ccc;">
					<ul class="breadcrumb"
						style="height: 100%;line-height: 23.2px;background-color: #F5F5F5;">
						<li><span class="glyphicon glyphicon-bell"></span> 重要通知</li>
					</ul>
					<!-- .breadcrumb -->
				</div>
				<div class="col-md-10"
					style="padding-right: 0;padding-left: 0;border-left: 1px solid #ccc;">
					<ul class="nav nav-pills nav-stacked">
						<li id="scroll" class="scroll clearfix">
							<ul>
								<li style="text-align: center;"><h3>2018-2019夏季学期选课注意事项</h3></li>
								<li>一、选课网址</li>
								<li>全校实行网上选课，请点击上海大学主页→学校概况→学校机构→教务处→选课管理。</li>
								<li>二、选课安排</li>
								<li>1、第一轮学生网上选课时间：4月20日20:30～25日8:00；</li>
								<li>2、第二轮学生网上选课时间：5月11日20:30～16日8:00；</li>
								<li>
									3、机房开放自由选课时间：4月21日、22日、5月12日、13日9:00～16:00；机房开放自由选课地点：宝山校区D楼5楼；嘉定校区计算中心(文达楼2楼)；延长校区四教2楼机房；
								</li>
								<li>4、学生务必于5月16日8:00之前选定夏季学期课程，过时无法解决。</li>
								<li>三、选课说明</li>
								<li>1、学生应按时进行每一轮选课。</li>
								<li>2、学生应本人登录选课系统进行选课，不得代选。</li>
								<li>3、选课最终结果在第二轮选课结束后才能确定，学生务必在第二轮选课结束后查询、确认自己的选课结果。</li>
								<li>四、特别提醒</li>
								<li>1、最终信息请以选课系统为准。</li>
								<li>2、选课如有问题，请于5月13日9:00～16:00凭本人学生证至选课机房咨询教务处值班老师。</li>
							</ul>
						</li>
					</ul>
				</div>
				<div class="col-md-10 breadcrumbs"
					style="padding-right: 0;padding-left: 0;height: 40px;border: 1px solid #ccc;">
					<ul class="breadcrumb"
						style="height: 100%;line-height: 23.2px;background-color: #F5F5F5;">
						<li><span class="glyphicon glyphicon-bullhorn"></span>通知公告</li>
					</ul>
					<!-- .breadcrumb -->
				</div>
				<div class="col-md-10"
					style="padding-right: 0;border: 1px solid #ccc;border-top: 0;">
					<ul class="nav nav-pills nav-stacked" style="margin-top: 10px;">
						<!--<li class="active"><a href="#">通知公告</a></li>-->
						<ul id="gonggao" style="width: 95%;padding-top: 10px;">
							<li><a href="#"> <text>华信软件学院2015级选课系统</text> <text>2018-4-10</text>
							</a></li>
							<li><a href="#"> <text>华信软件学院2015级选课系统</text> <text>2018-4-10</text>
							</a></li>
							<li><a href="#"> <text>华信软件学院2015级选课系统</text> <text>2018-4-10</text>
							</a></li>
							<li><a href="#"> <text>华信软件学院2015级选课系统</text> <text>2018-4-10</text>
							</a></li>
							<li><a href="#"> <text>华信软件学院2015级选课系统</text> <text>2018-4-10</text>
							</a></li>
							<li><a href="#"> <text>华信软件学院2015级选课系统</text> <text>2018-4-10</text>
							</a></li>
							<li><a href="#"> <text>华信软件学院2015级选课系统</text> <text>2018-4-10</text>
							</a></li>
							<li><a href="#"> <text>华信软件学院2015级选课系统</text> <text>2018-4-10</text>
							</a></li>
							<li><a href="#"> <text>华信软件学院2015级选课系统</text> <text>2018-4-10</text>
							</a></li>
							<li><a href="#"> <text>华信软件学院2015级选课系统</text> <text>2018-4-10</text>
							</a></li>
						</ul>
					</ul>
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
	<script src="js/jquery-1.6.3.js"></script>
	<script src="js/jquery-3.3.1.min.js"></script>
	<script src="js/default.js"></script>
	<script src="js/main-student.js"></script>
	<script src="js/bootstrap.min.js"></script>
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
		function goMyCourse() {
			window.location.href = "myCourse-teacher.jsp";
		}
	</script>
</body>
</html>

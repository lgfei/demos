<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<title>基本模版</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
body {
	text-decoration: none;
	width: 100%;
	height: 100%;
	text-align: center;
}

.head {
	width: 100%;
	height: 50px;
	background-color: #9985B0;
}

.left {
	width: 200px;
	height: 600px;
	background-color: #FAF7FD;
	clear: both;
	float: left;
}

.main {
	width: 600px;
	height: 600px;
	clear: right;
}

.bottom {
	width: 100%;
	height: 30px;
	background-color: #F5F6F7;
}
</style>
<script type="text/javascript" src="<%=basePath%>js/jquery/jquery-1.9.0.js"></script>
</head>
<body>
	<div class="head">
		<tiles:insertAttribute name="head" />
	</div>
	<div class="left">
		<tiles:insertAttribute name="left" />
	</div>
	<div class="main">
		<tiles:insertAttribute name="main" />
	</div>
	<div class="bottom">
		<tiles:insertAttribute name="bottom" />
	</div>
</body>
</html>
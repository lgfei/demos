<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<title>Welcome</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<div id="top" style="display: none;">
	<input type="hidden" id="loginMgName"/>
	<span><s:property value="#session.loginMgName"/>,欢迎你的到来!</span>
	<button onclick="javascript:logout();">退出</button>
</div>
<button id="loginBtn" onclick="javascript:login();">登录</button>
<button id="viewBtn" style="display: none;" onclick="javascript:viewStudentInfo();">查看学生信息</button>
</body>
<script type="text/javascript">
$(document).ready(function(){
	var loginMgName = '<%=session.getAttribute("loginMgName")%>';
	console.log(loginMgName);
	$("#loginMgName").val(loginMgName);
	if(loginMgName == "null" || loginMgName == ""){
		$("#top").hide();
		$("#viewBtn").hide();
	}else{
		$("#top").show();
		$("#viewBtn").show();
		$("#loginBtn").hide();
	}
});
</script>
<script type="text/javascript" src="<%=basePath%>js/tools.js"></script>
<script type="text/javascript" src="<%=basePath%>js/index.js"></script>
</html>
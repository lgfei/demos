<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
    String path = request.getContextPath();
    String basePath =
        request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
<title>Login</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<div>
		<p>
			<span id="errMsg" style="color: red;"> 
				<s:property value="errMsg" />
			</span>
		</p>
		<!--<s:fielderror></s:fielderror>-->
		<s:form action="/manager/manager_login" method="post" validate="true">
			<s:textfield name="manager.no" label="管理员"></s:textfield>
			<s:password name="manager.pwd" label="密码"></s:password>
			<s:textfield name="verificationCode" label="验证码" class="vf-code"></s:textfield>
			<s:submit label="登录" value="登录"></s:submit>
			<s:reset label="重置" value="重置"></s:reset>
			<s:token></s:token>
		</s:form>
		<div id="vfCodeDiv" class="vf-code">
			<img id="vfCodeImg" alt="看不清楚，点击换一张"
				src="<%=basePath%>/servlet/VerificationCodeServlet?timestamp=' + new Date().getTime();"
				onclick="changeVerificationCode();"> <a
				href="javascript:changeVerificationCode();">看不清楚?点击换一张</a>
		</div>
	</div>
</body>
<script type="text/javascript"
	src="<%=basePath%>js/jquery/jquery-1.9.0.js"></script>
<script type="text/javascript" src="<%=basePath%>js/login.js"></script>
<script type="text/javascript" src="<%=basePath%>js/tools.js"></script>
</html>
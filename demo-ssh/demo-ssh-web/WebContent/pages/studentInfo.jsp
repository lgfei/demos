<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Student Info</title>
<style type="text/css">
.clear{width: 100%;height: 30px;}
</style>
</head>
<body>
<div id="stuInfoDiv">
<div id="listTil"  style="border: 1px solid;width: 100px;float: left;margin-right: 10px;background-color: #F2F2F2;">
	<span onclick="show('listTab','editTab')">List</span>
</div>
<div id="editTil" style="border: 1px solid;width: 100px;float: left;display: none;">
	<span onclick="show('editTab','listTab')">Edit</span>
	&nbsp;&nbsp;
	<a href="javascript:closeTab()" >X</a>
</div>
<div class="clear"></div>
<div id="listTab" style="background-color: #DBEAF9">
	<button onclick="add()">新增</button>
	<button onclick="del()">删除</button>
	<table id="stuInfoTable" style="border: 1px solid">
		<thead>
			<tr>
			<th>序号</th>
			<th><input type='checkbox' id="head_box" onclick="checkAllOrNot()"/></th>
			<th>操作</th>
			<th>学号</th>
			<th>姓名</th>
			<th>性别</th>
			<th>身份证</th>
			</tr>
		</thead>
		<tbody></tbody>
		<tfoot>
			<tr>
			<td><a href='javascript:gotoPage("first")'>首页</a></td>
			<td><a href='javascript:gotoPage("before")'>上一页</a></td>
			<td colspan="2" style="width: 30%;">
				<span id="pages" style="text-align: center;"></span>
				<input type="hidden" name="currPage" id="curr_page" value="1"/>
				<input type="hidden" name="lastPage" id="last_page"/>
			</td>
			<td><a href='javascript:gotoPage("next")'>下一页</a></td>
			<td><a href='javascript:gotoPage("last")'>最后一页</a></td>
			<td>总页数：<span id="page_count"></span></td>
			</tr>
		</tfoot>
	</table>
</div>
<div id="editTab" style="background-color: #DBEAF9;display: none;">
    <form id="editFrm" action="../student/student_edit" method="post">
   	<ul>
	   	<li style="margin: 10px;">
	   	<label>学号：</label>
	   	<input type="text" name="stu.no"/>
	   	</li>
	   	<li style="margin: 10px;">
	   	<label>姓名：</label>
	   	<input type="text" name="stu.name"/>
	   	</li>
	   	<li style="margin: 10px;">
	   	<label>性别：</label>
	   	<input type="radio" name="stu.sex" value="1"/>男
	   	&nbsp;&nbsp;
	   	<input type="radio" name="stu.sex" value="0"/>女
	   	</li>
	   	<li style="margin: 10px;">
	   	<label>身份证：</label>
	   	<input type="text" name="stu.idcard"/>
	   	</li>
	   	<li style="margin: 10px;">
	   	<input type="hidden" id="act_flag" name="actFlag">
	   	<input type="submit" value="保存"/>
	   	</li>
   	</ul>
    </form> 
</div>
</div>
<script type="text/javascript" src="<%=basePath%>js/jquery/jquery-1.9.0.js"></script>
<script type="text/javascript" src="<%=basePath%>js/studentInfo.js"></script>
</body>
</html>
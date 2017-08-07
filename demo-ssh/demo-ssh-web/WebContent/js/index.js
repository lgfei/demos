/**
 * @author lgfei
 * @since 20160703
 */
var login = function(){
	console.log("Enter login...");
	//跳转至新页面
	window.location.href=getAppUrl()+"login.jsp";
	//打开新页面
	//window.open("login.jsp"); 
}

var logout = function(){
	console.log("Enter logout...");
	$.ajax({
		type:"POST",
		url:"/learn.web/manager/manager_logout",
		data:null,
		success:function(result){
			window.location.href=getAppUrl()+"login.jsp";
		}
	});
}

var viewStudentInfo = function(){
	console.log("Enter viewStudentInfo...");
	//跳转至新页面
	window.location.href=getAppUrl()+"pages/studentInfo.jsp";
}
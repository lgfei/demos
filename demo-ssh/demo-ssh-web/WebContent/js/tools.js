/**
 * @author lgfei
 * @since 20160706
 */
var getAppUrl = function(){
	console.log("Enter getAppUrl...");
	//当前地址栏的路径
	var href = window.location.href;
	//这里的pathName第一个字符是 "/",例如:myProject/xxx.html
	var pathName = window.document.location.pathname;
	var pos = href.indexOf(pathName);
	//取得服务器的路径包括端口号 ,例如：http://localhost:8080
	var serverPath = href.substr(0,pos);
	//取得项目名 ,例如 :/myProject
	var projectName = pathName.substr(0,pathName.substr(1).indexOf("/")+1);
	var appUrl = serverPath + projectName + "/";
	//最后得到项目的根路径,例如:http://localhost:8080/myProject/
	console.log("appUrl:"+appUrl);
	return appUrl;
}
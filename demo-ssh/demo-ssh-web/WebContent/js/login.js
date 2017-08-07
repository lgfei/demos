	/**
 * @author lgfei
 * @since 20160706
 */
var disappear = function(){
	console.log("Enter disappear...");
	var errMsgEle = $("#errMsg");
	errMsgEle.hide();
}
setTimeout("disappear()",2000);

var changeVerificationCode = function(){
	console.log("Enter changeVerificationCode...");
	var vfCodeSrc = getAppUrl() + "servlet/VerificationCodeServlet?timestamp=" + new Date().getTime();
	$("#vfCodeImg").attr("src",vfCodeSrc);
}
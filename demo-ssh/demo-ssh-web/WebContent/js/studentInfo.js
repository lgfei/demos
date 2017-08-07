/**
 * @author lgfei
 * @since 20160403
 */
$(document).ready(function(){
	//页面加载
	init();
	query();
});
var query=function(){
	//查询信息
	console.log("进入query");
	var currPage = $("#curr_page").val();
	$.ajax({
		type:"POST",
		url:"../student/student_findPageList",
		data: {"currPage":currPage},
        dataType: "json",
		success:function(map){
			var data = eval(map);
			
			var pagesView = "";
			var lastPage = data.lastPage;
			$("#last_page").val(lastPage);
			$("#page_count").html(lastPage);
			for(var page=1;page<=lastPage;page++){
				if(page == currPage){
					pagesView = pagesView + "<span>"+page+"</span>";
				}else{
					pagesView = pagesView + "<a href='javascript:gotoPage("+page+")'>"+page+"</a>";
				}
				pagesView = pagesView + "&nbsp;";
			}
			console.log("pagesView="+pagesView);
			$("#pages").html(pagesView);
			
			var dataView="";
			var list = data.list;
			$.each(list,function(j,item){
				var sexStr = item.sex == 1 ? '男' :'女';
				dataView = dataView + "<tr>";
				dataView = dataView + "<td>"+(j+1)+"</td>";
				dataView = dataView + "<td><input type='checkbox'/><input type='hidden' value='"+item.no+"'/></td>";
				dataView = dataView + "<td><a href='javascript:edit("+item.no+")'>编辑</a></td>";
				dataView = dataView + "<td>"+item.name+"</td>";
				dataView = dataView + "<td>"+sexStr+"</td>";
				dataView = dataView + "<td>"+item.idcard+"</td>";
				dataView = dataView + "</tr>";
			});
			console.log("dataView="+dataView);
			$("#stuInfoTable tbody").html(dataView);
		}
	});
}

var add=function(){
	//新增信息
	console.log("进入add");
	$("#listTab").hide();
	$("#editTil").show();
	$("#editTab").show();
	$("#editTil").css("background-color","#F2F2F2");
	$("#listTil").css("background-color","white");
	$("#act_flag").val('add');
	$("#editFrm input[name='stu.stuSex']").removeAttr("checked");
}

var edit=function(stuNo){
	//编辑信息
	console.log("进入edit,stuNo="+stuNo);
	$("#listTab").hide();
	$("#editTil").show();
	$("#editTab").show();
	$("#editTil").css("background-color","#F2F2F2");
	$("#listTil").css("background-color","white");
	$("#act_flag").val('update');
	$.ajax({
		type:"POST",
		url:"../student/student_findOne",
        data: {"stu.no":stuNo},
        dataType: "json",
		success:function(stu){
			$("#editFrm input[name='stu.no']").val(stu.no);
			$("#editFrm input[name='stu.name']").val(stu.name);
			if(stu.stuSex){
				//attr第二次失效，所以这里要用prop
				//$("#editFrm input[name='stu.stuSex'][value='"+stu.stuSex+"']").attr('checked',true);
				$("#editFrm input[name='stu.sex'][value='"+stu.sex+"']").prop('checked',true);
			}
			$("#editFrm input[name='stu.idcard']").val(stu.idcard);
		}
	});
}

var init=function(){
	//页面初始化
	console.log("进入init");
}

var show=function(showid,hideid){
	//点击页签显示页签内容
	console.log("进入show,showid="+showid+",hideid="+hideid);
	$("#"+showid).show();
	$("#"+hideid).hide();
	if(showid === "listTab"){
		$("#editTil").css("background-color","white");
		$("#listTil").css("background-color","#F2F2F2");
	}else{
		$("#editTil").css("background-color","#F2F2F2");
		$("#listTil").css("background-color","white");
	}
};

var closeTab=function(){
	//关闭页签
	console.log("进入closeTab");
	reset('editFrm');//清空原来填的数据
	$("#editTil").hide();
	$("#editTab").hide();
	$("#listTab").show();
	$("#editTil").css("background-color","white");
	$("#listTil").css("background-color","#F2F2F2");
}

var reset=function(frmId){
	//重置表单数据
	console.log("进入reset,frmId="+frmId);
    $(':input','#'+frmId).not(':button, :submit, :reset, :hidden, :radio')  
    .val('')  
    .removeAttr('checked')  
    .removeAttr('selected'); 
}

var gotoPage=function(type){
	//分页切换
	console.log("进入gotoPage,type="+type);
	var currPage = $('#curr_page').val();
	var lastPage = $('#last_page').val();
	if(type === 'first'){
		currPage = 1;
	}else if(type == 'last'){
		currPage = lastPage;
	}else if(type === 'before'){
		if(currPage > 1){
			currPage--;
		}
	}else if(type === 'next'){
		if(currPage < lastPage){
			currPage++;
		}
	}else{
		currPage = type;
	}
	$('#curr_page').val(currPage)
	query();
}

var checkAllOrNot=function(){
	//全选或者全不选
	console.log("进入checkAllOrNot");
	var boxs = $("#stuInfoTable tbody input[type='checkbox']");
	var check = $('#head_box').is(':checked');
	$.each(boxs,function(i,item){
		//attr第二次失效，所以这里要用prop
		//$(item).attr("checked",check);
		$(item).prop("checked",check);
	});
}

var del=function(){
	//批量删除
	console.log("进入del");
	var checkdBoxs = $("#stuInfoTable tbody input[type='checkbox']:checked");
	var ids = "";
	$.each(checkdBoxs,function(i,item){
		var id = $(item).next("input").eq(0).val();
		ids = ids + id + ","
	});
	if(ids.length <= 0){
		return;
	}else{
		ids = ids.substr(0,ids.length - 1);
	}
	$.ajax({
		type:"POST",
		url:"../student/student_del",
        data: {"stuIds":ids},
        dataType: "json",
        async:false,
		success:function(msg){
			window.location.reload();//刷新当前页面.
		}
	});
}
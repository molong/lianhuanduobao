var vid = "";
jQuery(document).ready(function() {
			App.init();
			search();
});
function search(){
	$("#table-data>tbody").html("");//清除旧表格数据
	showLoading("table-data");//显示进度条
	hideEmptyDesc();//删除没有查询结果的文字描述
	$.post("../stock/search",{
		},function(resultObject){
			if(resultObject.success) {
					hideLoading();//隐藏进度条
					if(resultObject.data.length==0){
						showEmptyDesc("table-data");//显示没有查询结果的文字描述
						return;
					}
					//循环生成表格数据
					var i = 0;
					$.each(resultObject.data, function(key, val) {
						var trObj = $("<tr/>");
						var name = "";
						if(i == 0){
							name = "大奖池";
						}else if(i == 1){
							name = "收入池";
						}else if(i == 2){
							name = "小奖池";
						}
						trObj.append($("<td>"+name+"</td>"));
						trObj.append($("<td>"+val.score+"</td>"));
						trObj.append($("<td>"+val.rate+"</td>"));
						trObj.append($("<td><buuton data-toggle=\"modal\" type=\"button\" data-target=\"#myModal\" class=\"btn userinputbtn\" onclick=\"update('"+i+"','"+val.score+"','"+val.rate+"')\">修改</buuton></td>"));
						trObj.appendTo("#table-data>tbody");
						i++;
					});
				}else{
					hideLoading();//隐藏进度条
					alert(resultObject.message);//弹出错误信息
				}
	},'json');
}
$("#btnMessage").click(function(){
	ustock();
});
function update(id,vscore,rate){
	vid = id;
	$("#score").val(vscore);
	$("#rate").val(rate);
}
//function check(){
//	if(isFloat($("#score").val()) && isFloat($("#rate").val())){
//		return true;
//	}else{
//		if(!isFloat($("#score").val())){
//			$("#score").focus();
//		}
//		if(!isFloat($("#rate").val())){
//			$("#rate").focus();
//		}
//		return false;
//	}
//}
function ustock(){
	$.post("../stock/update",{
		id:vid,
		score:$("#score").val(),
		rate:$("#rate").val()
		  },function(resultObject){
			if(resultObject.success) {
				$("#myModal").modal("hide");
				search();
			}else{
				alert(resultObject.message);
			}
	  });
} 

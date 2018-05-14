var vid = "";
jQuery(document).ready(function() {
			App.init();
			search();
});
function search(currentPage){
	$("#table-data>tbody").html("");//清除旧表格数据
	showLoading("table-data");//显示进度条
	hideEmptyDesc();//删除没有查询结果的文字描述
	$.post("../account/online",{
		currentPage:currentPage,
		  limit:30,
		  sort: TABLE_SORT_STR ? TABLE_SORT_STR : ""
		},function(resultObject){
			if(resultObject.success) {
					hideLoading();//隐藏进度条
					if(resultObject.data.length==0){
						showEmptyDesc("table-data");//显示没有查询结果的文字描述
						return;
					}
					//循环生成表格数据
					$.each(resultObject.data, function(key, val) {
						var trObj = $("<tr/>");
						trObj.append($("<td>"+val.userId+"</td>"));
						trObj.append($("<td>"+(val.userName)+"</td>"));
						trObj.append($("<td>"+(val.userBank.score)+"</td>"));
						trObj.append($("<td>"+(val.tel)+"</td>"));
						trObj.append($("<td>"+(val.registerIp)+"</td>"));
						trObj.append($("<td>"+val.introducer+"</td>"));
						trObj.append($("<td>"+(val.registerDate)+"</td>"));
						trObj.append($("<td>"+(val.updateDate)+"</td>"));
						trObj.appendTo("#table-data>tbody");
					});
					//创建分页
					var pager = resultObject.params.pager;
					myBootstrapPaginator("bootstrapPaginator",pager);
				}else{
					hideLoading();//隐藏进度条
					alert(resultObject.message);//弹出错误信息
				}
	},'json');
}
$("#btnMessage").click(function(){
	ustock();
});
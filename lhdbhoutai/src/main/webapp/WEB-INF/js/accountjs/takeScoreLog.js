var page = 1;
jQuery(document).ready(function() {  
	var hour = 24;
	//初始化两个日期
	$("#start").val(moment().subtract('hours', hour).format('YYYY-MM-DD HH:mm:ss'));
    $("#end").val(moment().format('YYYY-MM-DD HH:mm:ss'));
	$('#reservationtimeadd').val(
			 moment().subtract('hours', hour).format('YYYY-MM-DD HH:mm:ss') + '至'
			 + moment().format('YYYY-MM-DD HH:mm:ss')); 
	$('#reservationtimeadd').daterangepicker({
        timePicker: true,
        timePickerIncrement: 10,
        format: 'YYYY-MM-DD HH:mm:ss'
      }, function(start, end, label) {
   	   console.log(start.toISOString(), end.toISOString(), label);
   	   $("#start").val(start.toDate().format("yyyy-MM-dd hh:mm:ss"));
   	   $("#end").val(end.toDate().format("yyyy-MM-dd hh:mm:ss"));
      });
			App.init();
			search(1);
});
function search(currentPage){
	page = currentPage;
	$("#table-data>tbody").html("");//清除旧表格数据
	showLoading("table-data");//显示进度条
	hideEmptyDesc();//删除没有查询结果的文字描述
	$.post("../takeScoreLog/search",{
		currentPage:currentPage,
		userId:$("#userId").val(),
		masterId:$("#masterId").val(),
		type:$("#itype").val(),
		start:$("#start").val(),
		end:$("#end").val(),
		limit:30
		},function(resultObject){
			if(resultObject.success) {
					hideLoading();//隐藏进度条
					if(resultObject.data.length==0){
						showEmptyDesc("table-data");//显示没有查询结果的文字描述
						return;
					}
					if(resultObject.params != null){
						$("#sumScore").html(resultObject.params.score);
					}
					//循环生成表格数据
					$.each(resultObject.data, function(key, val) {
						var trObj = $("<tr/>");
						var typeValue = "";
						if(val.type == 0){
							typeValue = "充值";
						}else{
							typeValue = "转账";
						}
						trObj.append($("<td>"+typeValue+"</td>"));
						trObj.append($("<td>"+val.userId+"|"+val.name+"</td>"));
						trObj.append($("<td>"+val.masterId+"|"+val.transferName+"</td>"));
						trObj.append($("<td>"+val.score+"</td>"));
						trObj.append($("<td>"+(val.createTime)+"</td>"));
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
//判断是否为空
function IsNotNull(data){
	if(data==null)
		return "";
	else
		return data;
}

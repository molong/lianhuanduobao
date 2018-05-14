var currentPage = 1;
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
	Search.init();//时间插件的调取
	$("#userid").val(getQueryString("userid"));
	search(1);
});
		
function serachData(){
	$("#userid").val("");
	search(1);
}

function search(currentPage){
	currentPage = currentPage;
	$("#table-data>tbody").html("");//清除旧表格数据
	showLoading("table-data");//显示进度条
	hideEmptyDesc();//删除没有查询结果的文字描述
	$.post("../order/searchDf",{
		currentPage:currentPage,
		userId:$("#userId").val(),
		orderId:$("#orderId").val(),
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
					//循环生成表格数据
					$.each(resultObject.data, function(key, val) {
						var trObj = $("<tr/>");
						trObj.append($("<td>"+val.userId+"</td>"));
						trObj.append($("<td>"+val.orderId+"</td>"));
						trObj.append($("<td>"+(val.money)+"</td>"));
						trObj.append($("<td>"+(val.score)+"</td>"));
						trObj.append($("<td>"+(val.accNo)+"</td>"));
						trObj.append($("<td>"+(val.certifTp)+"</td>"));
						trObj.append($("<td>"+(val.certifId)+"</td>"));
						trObj.append($("<td>"+(val.customerNm)+"</td>"));
						var state = val.state;
						var stateName = "";
						if(state == 0){
							stateName = "未付款";
						}else if(state == 1){
							stateName = "已付款待处理";
						}else if(state == 2){
							stateName = "处理完成";
						}
						trObj.append($("<td>"+stateName+"</td>"));
						trObj.append($("<td>"+val.createTime+"</td>"));
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
function getParams(){
	var i = 0;
	var ids = '?';
	$("#itemContainer input:checked").each(function () {
		if(i>0){
			ids = ids+'&useridArray='+$(this).val();
		}else{
			ids = ids+'useridArray='+$(this).val();
		}
		i++;
	});
	return ids;
}
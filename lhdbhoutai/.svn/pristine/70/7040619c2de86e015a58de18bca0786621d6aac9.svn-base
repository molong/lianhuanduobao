var page = 1;
var saveOrUpdate = "/notice/save";
jQuery(document).ready(function() {       
			App.init();
			search(1);
			//初始化两个日期
			/*$("#start").val(moment().subtract('hours', 1).format('YYYY-MM-DD HH:mm:ss'));
            $("#end").val(moment().format('YYYY-MM-DD HH:mm:ss'));
			$('#reservationtimeadd').val(
					 moment().subtract('hours', 1).format('YYYY-MM-DD HH:mm:ss') + '至'
					 + moment().format('YYYY-MM-DD HH:mm:ss')); 
			$('#reservationtimeadd').daterangepicker({
                timePicker: true,
                timePickerIncrement: 10,
                format: 'YYYY-MM-DD HH:mm:ss'
              }, function(start, end, label) {
           	   console.log(start.toISOString(), end.toISOString(), label);
           	   $("#start").val(start.toDate().format("yyyy-MM-dd hh:mm:ss"));
           	   $("#end").val(end.toDate().format("yyyy-MM-dd hh:mm:ss"));
              });*/
});
$("#btnMessage").click(function(){
	check();
});
$("#addbtn").click(function(){
	$("#myModalLabel").html("添加广播");
	saveOrUpdate = "/notice/save";
});
function update(id){
	$("#myModalLabel").html("修改广播");
	saveOrUpdate = "/notice/update";
	$.post("../notice/find",{
		id:id
		},function(resultObject){
			if(resultObject.success) {
//				var start = resultObject.data.start;
//				var end = resultObject.data.end;
//				$('#reservationtimeadd').val(start + '至' + end);
				$("#title").val(resultObject.data.title);
				$("#status").val(resultObject.data.status);
//				$("#start").val(start);
//				$("#end").val(end);
				$("#id").val(id);
			}else{
				hideLoading();//隐藏进度条
				alert(resultObject.message);//弹出错误信息
			}
		},'json');
}
function search(currentPage){
	page = currentPage;
	$("#table-data>tbody").html("");//清除旧表格数据
	showLoading("table-data");//显示进度条
	hideEmptyDesc();//删除没有查询结果的文字描述
	$.post("../notice/search",{
		currentPage:currentPage,
		userId:$("#userid").val(),
		userName:$("#userName").val(),
		tel:$("#tel").val(),
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
						trObj.append($("<td>"+IsNotNull(val.title)+"</td>"));
						var statusValue = "";
						if(val.status == 0){
							statusValue = "正常";
						}else{
							statusValue = "停放";
						}
						trObj.append($("<td>"+statusValue+"</td>"));
//						trObj.append($("<td>"+(val.start)+"</td>"));
//						trObj.append($("<td>"+(val.end)+"</td>"));
						trObj.append($("<td><a href='javascript:void(0);' data-toggle=\"modal\" data-target=\"#myModal\" onclick=\"update('"+val.id+"')\">修改</a>" +
								"&nbsp;<a href='javascript:void(0);' onclick=\"del('"+val.id+"')\">删除</a></td>"));
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
function check(){
	if($("#title").val() == undefined || $("#title").val() == ''
//		|| $("#start").val() == undefined || $("#start").val() == ''
//		|| $("#end").val() == undefined || $("#end").val() == ''
			){
			$.messager.alert("操作提示", "请输入!");
		return;
	}
	$.post(".."+saveOrUpdate,{
		title:$("#title").val(),
		status:$("#status").val(),
		id:$("#id").val()
//		start:$("#start").val(),
//		end:$("#end").val()
		  },function(resultObject){
			if(resultObject.success) {
				$("#myModal").modal("hide");
				search(page);
			}else{
				alert(resultObject.message);
			}
		});
} 
function del(id){
	if(confirm("确定删除数据")){
		$.post("../notice/del",{
			id:id
			  },function(resultObject){
				if(resultObject.success) {
					$("#myModal").modal("hide");
					search(page);
				}else{
					alert(resultObject.message);
				}
			});
	}
}

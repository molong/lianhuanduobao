<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<meta content="width=device-width, initial-scale=1.0" name="viewport" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="renderer" content="webkit">
<meta content="" name="description" />
<jsp:include page="public/css.jsp"></jsp:include>
</head>
<body class="page-header-fixed">

<jsp:include page="public/menu.jsp"></jsp:include>
		<div class="page-content">
			<div>
				<div class="row-fluid">
					<div class="span12">
						<!--表格 begin-->
						<div class="portlet box blue">
							<!--显示内容信息的标题 begin-->
							<div class="portlet-title">
								<div class="caption">
									<i class="icon-globe"></i>模块管理
								</div>
							</div>
							<!--显示内容信息的标题 end-->
							<!--进行帅选信息的条件  begin-->
							<div class="booking-search">
									<div class="clearfix margin-bottom-20">
										<div class="pull-left" >
											<button data-toggle="modal" data-target="#module_modal" type="button" class="btn userinputbtn">添加</button> 
										</div>
									</div>
							</div>
							<!--进行帅选信息的条件  end-->

							<div class="portlet-body">
							
								<div class="table-responsive"> 
									<!--table 显示数据 begin-->  <!-- 	去掉table 里的	 table-responsive  样式 -->
									<table id="table_module" class="table table-striped  table-hover   table-full-width" >
										<!--表格的标题 begin-->
										<thead class="navbar-static-top">
											<tr>
												<th>模块ID</th>
												<th>上级模块</th>
												<th>模块名称</th>
												<th>链接地址</th>
												<th>排序</th>
												<th>启用</th>
												<th>菜单</th>
												<th width="10%;">说明</th>
												<th>ManagerPopedom</th>
												<th>link</th>
												<th>icon</th>
												<th style="text-align: center;">操作</th>
											</tr>
										</thead>
										<!--表格的标题 end-->
										<!--表格的内容 begin-->
										<tbody id="itemContainer">
										</tbody>
										
										<!--表格的内容 end-->
									</table>
								</div>
							</div>
						
						</div>
						<!--表格 end-->
					</div>
				</div>
			</div>
			</div>
			
			<!-- 模态框（Modal） -->
			<div style="width:400px;display: none;" class="modal fade" id="module_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
   				<div class="modal-dialog">
      				<div class="modal-content">
        				 <div class="modal-header">
            				<button type="button" class="close"	data-dismiss="modal" aria-hidden="true">&times;</button>
           	 				<h4 class="modal-title" id="myModalLabel">添加模块</h4>
         				</div>
         			<div class="modal-body">
         				<form action="">
         					<input type="hidden" id="module_form_moduleid">
         					<table>
         						<tr>
         							<td>标题：</td>
         							<td><input type="text" id="module_form_title" /></td>
         						</tr>
         						<tr>
         							<td>链接地址：</td>
         							<td><input type="text" id="module_form_link" /></td>
         						</tr>
         						<tr>
         							<td>排序：</td>
         							<td><input type="text" id="module_form_orderno" /></td>
         						</tr>
         						<tr>
         							<td>说明：</td>
         							<td><input type="text" id="module_form_description"/></td>
         						</tr>
         						<!-- <tr>
         							<td>link2：</td>
         							<td><input type="text" id="module_form_link2" value="0" /></td>
         						</tr> -->
         						<tr>
         							<td>icon：</td>
         							<td><input type="text" id="module_form_icon"/></td>
         						</tr>
         						<tr>
         							<td>父模块：</td>
         							<td><select id="module_form_parent_moduleid"><option value="0">无</option></select></td>
         						</tr>
         						<tr>
         							<td>&nbsp;</td>
         							<td>
									     <input type="checkbox" id="module_form_nullity"  value="0" checked="checked"/>&nbsp;&nbsp;启用
									     <input type="checkbox" id="module_form_ismenu"  value="0" checked="checked"/>&nbsp;&nbsp;菜单
         							</td>
         						</tr>
         					</table>
         				</form>
        			</div>
        	<div class="modal-footer">
				<button type="button"  class="btn btn-primary"  onclick="add_update_module()">确定</button>
				<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
			</div>
      </div><!-- /.modal-content -->
</div>
</div><!-- /.modal -->

		<!-- 把js放在后面这样可以节约界面加载的时间 -->
	<jsp:include page="public/js.jsp"></jsp:include>
	<script type="text/javascript">
		$(function(){
			App.init();
			search();
			$("#module_modal").bind('hidden.bs.modal', function () {
				$("#module_form_moduleid").val("");
				$("#module_form_parent_moduleid").val("0");
				$("#module_form_title").val("");
				$("#module_form_link").val("");
				$("#module_form_orderno").val("");
				$("#module_form_nullity").prop("checked",true);
				$("#module_form_ismenu").prop("checked",true);
				$("#module_form_icon").val("");
				$("#module_form_description").val("");
			});
		});
		
		function search(){
			$.post("../Modules/search", function(result) {
				
				var tbody = $("#table_module tbody");
				tbody.html("");
				var modules = module_arr(result.data);
				$.each(modules, function(key, val) {
					tbody.append("<tr class='info'><td>"+val.moduleid+"</td><td>"+val.parentid+"</td><td>"+val.title+"</td><td>"+val.link2+"</td>"
							+"<td>"+val.orderno+"</td><td>"+val.nullity+"</td><td>"+val.ismenu+"</td><td title='"+val.description+"'>"+(val.description.length > 10 ? val.description.substring(0, 9) + "..." : val.description)+"</td>"
							+"<td>"+val.managerpopedom+"</td><td>"+val.link+"</td><td>"+val.icon+"</td><td>"
							+"<a href='#' onclick='del_module("+val.moduleid+")'>删除</a>&nbsp;|&nbsp;"
							+"<a href='#' onclick='openNewWin(\"modulePersmission.jsp?moduleid="+val.moduleid+"\", \"modulepermission\", 1200, 800)'>权限管理</a>&nbsp;|&nbsp;<a href='#' data-toggle='modal' data-target='#module_modal' onclick='update_module("+JSON.stringify(val)+")'>修改</a>&nbsp;|&nbsp;"
							+"<a href='#' onclick='nullity_module("+val.moduleid+", "+ val.nullity+")'>"+(val.nullity == 0 ? "禁用":"启用")+"</a></td></tr>");
					$("#module_form_parent_moduleid").append("<option value='"+val.moduleid+"'>"+val.title+"</option>");
					if(val.children && val.children.length) {
						$.each(val.children, function(key, val) {
							tbody.append("<tr><td>"+val.moduleid+"</td><td>"+val.parentid+"</td><td>"+val.title+"</td><td>"+val.link2+"</td>"
									+"<td>"+val.orderno+"</td><td>"+val.nullity+"</td><td>"+val.ismenu+"</td><td title='"+val.description+"'>"+(val.description.length > 10 ? val.description.substring(0, 9) + "..." : val.description)+"</td>"
									+"<td>"+val.managerpopedom+"</td><td>"+val.link+"</td><td>"+val.icon+"</td><td>"
									+"<a href='#' onclick='del_module("+val.moduleid+")'>删除</a>"
									+"&nbsp;|&nbsp;<a href='#' onclick='openNewWin(\"modulePersmission.jsp?moduleid="+val.moduleid+"\", \"modulepermission\",  1200, 800)'>权限管理</a>&nbsp;|&nbsp;<a href='#' data-toggle='modal' data-target='#module_modal' onclick='update_module("+JSON.stringify(val)+")'>修改</a>&nbsp;|&nbsp;"
									+"<a href='#' onclick='nullity_module("+val.moduleid+", "+ val.nullity+")'>"+(val.nullity == 0 ? "禁用":"启用")+"</a></td></tr>");
						});
					}
				});
			});
		}
		function module_arr(modules) {
			var arr = new Array(); var key_pos_map = {};
			for(var i = 0; i<modules.length; i++) {
				if(modules[i].parentid == 0) {
					arr.push(modules[i]);
					key_pos_map[modules[i].moduleid] = arr.length - 1;
				}
			}
			
			for(var i = 0; i < modules.length; i++) {
				var pos = key_pos_map[modules[i].parentid];
				if(pos > -1 && arr[pos]) {
					var node = arr[pos];
				 	if(node.children) {node.children.push(modules[i]);}
					else {node.children = new Array(); node.children.push(modules[i]);} 
				} else if(modules[i].parentid != 0) {
					arr.push(modules[i]);
				}
			}
			return arr;
		}
		
		function add_update_module(){
			var params = {
				moduleid: $("#module_form_moduleid").val(),
				parentid: $("#module_form_parent_moduleid").val(),
				title: $("#module_form_title").val(),
				link2: $("#module_form_link").val(),
				orderno: $("#module_form_orderno").val(),
				nullity: $("#module_form_nullity").is(':checked') ? 0 : 1,//module_form_link2
				ismenu: $("#module_form_ismenu").is(':checked') ? 0 : 1,
				icon: $("#module_form_icon").val(),
				description: $("#module_form_description").val(),
				managerpopedom:0
			};
			var url = "../Modules/updateModule";
			if(!params.moduleid) {
				url = "../Modules/add";
				delete params.moduleid;
			}
			
			$("#module_modal").modal("hide");
			$.post(url,params, function(result) {
				if(result.success)
					search();
			});
		}
		
		function update_module(module) {
			$("#module_form_moduleid").val(module.moduleid);
			$("#module_form_parent_moduleid").val(module.parentid);
			$("#module_form_title").val(module.title);
			$("#module_form_link").val(module.link2);
			$("#module_form_orderno").val(module.orderno);
			$("#module_form_nullity").prop("checked",module.nullity == 0 ? true : false);
			$("#module_form_ismenu").prop("checked",module.ismenu == 0 ? true : false);
			$("#module_form_icon").val(module.icon);
			$("#module_form_description").val(module.description);
		}
		
		function del_module(moduleid){
			$.messager.confirm("删除", "确定删除模块：" + moduleid + "？ ", function() {
				$.post("../Modules/delModule", {
					moduleid: moduleid
				}, function(result) {
					if(result.success)
						$.messager.alert("删除成功","成功删除模块：" + moduleid);
					search();
				});
			});
		}
		
		function nullity_module(moduleid, nullity) {
			var isnullity = 0;
			if(nullity == 0) isnullity = 1; else isnullity = 0;
			$.messager.confirm("修改", "确定"+(isnullity == 0 ?"启用":"禁用")+"模块：" + moduleid + "？ ", function() {
				$.post("../Modules/updateModule", {
					moduleid: moduleid,
					nullity: isnullity
				}, function(result) {
					if(result.success)
						$.messager.alert("成功","成功"+(isnullity == 0 ?"启用":"禁用")+"模块：" + moduleid);
					search();
				});
			});
		}
	</script>
</body>
</html>
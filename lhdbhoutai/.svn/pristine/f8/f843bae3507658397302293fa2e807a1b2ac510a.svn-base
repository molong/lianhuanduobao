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
<body>

		<div style="padding: 15px;" >
			<div>
				<div class="row-fluid">
					<div class="span12">
						<!--表格 begin-->
						<div class="portlet box blue">
							<!--显示内容信息的标题 begin-->
							<div class="portlet-title">
								<div class="caption">
									<i class="icon-globe"></i>模块权限管理
								</div>
							</div>
							<!--显示内容信息的标题 end-->
							<!--进行帅选信息的条件  begin-->
							<div class="booking-search">
									<div class="clearfix margin-bottom-20">
										<div class="pull-left" >
											<button data-toggle="modal" data-target="#module_permission_modal" type="button" class="btn userinputbtn">添加</button> 
										</div>
									</div>
							</div>
							<!--进行帅选信息的条件  end-->

							<div class="portlet-body">
							
								<div class="table-responsive"> 
									<!--table 显示数据 begin-->  <!-- 	去掉table 里的	 table-responsive  样式 -->
									<table id="table_module_permission" class="table table-striped  table-hover   table-full-width" >
										<!--表格的标题 begin-->
										<thead class="navbar-static-top">
											<tr>
												<th>模块ID</th>
												<th>权限名称</th>
												<th>权限值</th>
												<th>禁用</th>
												<th>状态</th>
												<th>父模块标识</th>
												<th>URI</th>
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
			<div style="width:400px;display: none;" class="modal fade" id="module_permission_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
   				<div class="modal-dialog">
      				<div class="modal-content">
        				 <div class="modal-header">
            				<button type="button" class="close"	data-dismiss="modal" aria-hidden="true">&times;</button>
           	 				<h4 class="modal-title" id="myModalLabel">添加模块</h4>
         				</div>
         			<div class="modal-body">
         				<form action="">
         					<input type="hidden" id="module_permission_form_moduleid">
         					<table>
         						<tr>
         							<td>权限名称：</td>
         							<td><input type="text" id="module_permission_form_title" /></td>
         						</tr>
         						<tr>
         							<td>权限值：</td>
         							<td><input type="text" id="module_permission_form_value" /></td>
         						</tr>
         						<tr>
         							<td>URI：</td>
         							<td><input type="text" id="module_permission_form_uri" /></td>
         						</tr>
         						<tr>
         							<td>&nbsp;</td>
         							<td>
									     <input type="checkbox" id="module_permission_form_nullity"  value="0" checked="checked"/>&nbsp;&nbsp;启用
									     <input type="checkbox" id="module_permission_form_state"  value="0" checked="checked"/>&nbsp;&nbsp;状态
         							</td>
         						</tr>
         					</table>
         				</form>
        			</div>
        	<div class="modal-footer">
				<button type="button"  class="btn btn-primary"  onclick="add_update_modulePermission()">确定</button>
				<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
			</div>
      </div><!-- /.modal-content -->
</div>
</div><!-- /.modal -->

		<!-- 把js放在后面这样可以节约界面加载的时间 -->
	<jsp:include page="public/js.jsp"></jsp:include>
	<script type="text/javascript">
		var moduleid = <%=request.getParameter("moduleid")%>;
		var method_isupdateorupdate = false;
		$(function(){
			searchPermissions();
			$("#module_permission_modal").bind('hidden.bs.modal', function () {
				$("#module_permission_form_title").val("");
				$("#module_permission_form_value").val("");
				$("#module_permission_form_uri").val("");
				$("#module_permission_form_nullity").prop("checked",true);
				$("#module_permission_form_state").prop("checked",true);
				method_isupdateorupdate = false;
			});
		});
		
		function searchPermissions(){
			$.post("../Modules/getModulePermissions", {
				moduleid: moduleid
			}, function(result) {
				var tbody = $("#table_module_permission tbody");
				tbody.html("");
				$.each(result.data, function(key, val) {
					tbody.append("<tr><td>"+val.moduleid+"</td><td>"+val.permissiontitle+"</td><td>"+val.permissionvalue+"</td><td>"+val.nullity+"</td>"
							+"<td>"+val.stateflag+"</td><td>"+val.parentid+"</td><td>"+val.uri+"</td><td  style='text-align: center;'>"
							+"<a href='#' onclick='del_modulePermission("+val.permissionvalue+")'>删除</a>&nbsp;|&nbsp;"
							+"<a href='#' data-toggle='modal' data-target='#module_permission_modal' onclick='update_modulePermission("+JSON.stringify(val)+")'>修改</a>&nbsp;|&nbsp;"
							+"<a href='#' onclick='nullity_modulePermission("+val.permissionvalue+", "+ val.nullity+")'>"+(val.nullity == 0 ? "禁用":"启用")+"</a></td></tr>");
				});
			});
		}
		
		function add_update_modulePermission(){
			var params = {
				moduleid: moduleid,
				permissiontitle: $("#module_permission_form_title").val(),
				permissionvalue: $("#module_permission_form_value").val(),
				uri: $("#module_permission_form_uri").val(),
				nullity: $("#module_permission_form_nullity").is(':checked') ? 0 : 1,//module_form_link2
				stateflag: $("#module_permission_form_state").is(':checked') ? 0 : 1
			};
			var url = "../Modules/addModulePersmission";
			if(method_isupdateorupdate) {
				url = "../Modules/updateModulePermission";
			}
			
			$("#module_permission_modal").modal("hide");
			$.post(url,params, function(result) {
				if(result.success)
					searchPermissions();
			});
		}
		
		function update_modulePermission(permission) {
			$("#module_permission_form_title").val(permission.permissiontitle);
			$("#module_permission_form_value").val(permission.permissionvalue);
			$("#module_permission_form_uri").val(permission.uri);
			$("#module_permission_form_nullity").prop("checked",permission.nullity == 0 ? true : false);
			$("#module_permission_form_state").prop("checked",permission.stateflag == 0 ? true : false);
			method_isupdateorupdate = true;
		}
		
		function del_modulePermission(permissionvalue){
			$.messager.confirm("删除", "确定删除模块权限：" + permissionvalue + "？ ", function() {
				$.post("../Modules/delModulePermission", {
					moduleid: moduleid,
					permissionvalue: permissionvalue
				}, function(result) {
					if(result.success)
						$.messager.alert("删除成功","成功删除模块权限：" + permissionvalue);
					searchPermissions();
				});
			});
		}
		
		function nullity_modulePermission(permissionvalue, nullity) {
			var isnullity = 0;
			if(nullity == 0) isnullity = 1; else isnullity = 0;
			$.messager.confirm("修改", "确定"+(isnullity == 0 ?"启用":"禁用")+"模块权限：" + permissionvalue + "？ ", function() {
				$.post("../Modules/updateModulePermission", {
					moduleid: moduleid,
					permissionvalue: permissionvalue,
					nullity: isnullity
				}, function(result) {
					if(result.success)
						$.messager.alert("成功","成功"+(isnullity == 0 ?"启用":"禁用")+"模块权限：" + permissionvalue);
					searchPermissions();
				});
			});
		}
	</script>
</body>
</html>
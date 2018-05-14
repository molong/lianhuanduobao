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
									<i class="icon-globe"></i>角色管理
								</div>
							</div>
							<!--显示内容信息的标题 end-->
							<!--进行帅选信息的条件  begin-->
							<div class="booking-search">
									<div class="clearfix margin-bottom-20">
										<div class="pull-left" >
											<button data-toggle="modal" data-target="#myModal" type="button" class="btn userinputbtn">添加</button> 
										</div>
									</div>
							</div>
							<!--进行筛选信息的条件  end-->

							<div class="portlet-body">
							
								<div class="table-responsive"> 
									<!--table 显示数据 begin-->  <!-- 	去掉table 里的	 table-responsive  样式 -->
									<table id="table-rolesInfo" class="table table-striped  table-hover   table-full-width" >
										<!--表格的标题 begin-->
										<thead class="navbar-static-top">
											<tr>
												<th>序号</th>
												<th>角色ID</th>
												<th>角色名称</th>
												<th>角色描述</th>
												<th>操作</th>
											</tr>
										</thead>
										<!--表格的标题 end-->
										<!--表格的内容 begin-->
										<tbody id="itemContainer">
										</tbody>
										<!--表格的内容 end-->
									</table>
								</div>

								<!--分页 开始-->
								<div class="row-fluid pagelist" >
									<div id="bootstrapPaginator"></div>
								</div>
								<!--分页 结束-->
							</div>
						
						</div>
						<!--表格 end-->
					</div>
				</div>
			</div>
			</div>
			
			<!-- 模态框（Modal） -->
			<div style="width:400px;display: none;" class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
   				<div class="modal-dialog">
      				<div class="modal-content">
        				 <div class="modal-header">
            				<button type="button" class="close"	data-dismiss="modal" aria-hidden="true"></button>
           	 				<h4 class="modal-title" id="myModalLabel">创建模块</h4>
         				</div>
	         			<div class="modal-body">
	         				<form action="">
	         					<table>
	         						<tr>
	         							<td>角色名称：</td>
	         							<td><input type="text" id="from_rolename" name="" /></td>
	         						</tr>
	         						<tr>
	         							<td>角色描述：</td>
	         							<td>
	         								<textarea rows="5" cols="5" id="from_description"></textarea>
	         							</td>
	         						</tr>
	         					</table>
	         				</form>
	        			</div>
			        	<div class="modal-footer">
							<button type="button" onclick="add_rolesInfo()" class="btn btn-primary">确定</button>
							<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
						</div>
	      			</div><!-- /.modal-content -->
				</div>
			</div><!-- /.modal -->
			<!-- Modify 模态框（Modal） -->
			<!-- Modify begin -->
			<div style="width:800px;postion:absolute;left:40%" class="modal fade" id="Modify" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
      				<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close"	data-dismiss="modal" aria-hidden="true"></button>
							<h4 class="modal-title" >修改权限</h4>
							<h2 class="modal-title" id="currentRole"></h2>
						</div>
						<div class="modal-body">
							<form id="modify_form">
								<table id="modify_table" class="table table-bordered table-hover">
									<div style="float:right;margin-top:8px;margin-right:10px">
										<input type="checkbox" id="checkAll" onclick='_checkedAll(this)' style="margin-left:10px;position:relative;top:-3px;right:5px">全选</input>
									</div>
									<!-- 载入权限列表页面 -->
								</table>
							</form>
						</div>
						<div class="modal-footer">
							<button type="button" onclick="saveRP();" class="btn btn-primary">确定</button>
							<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
						</div>
					</div>
				</div>
			</div>
			<!-- Modify end -->
			
		<!-- 把js放在后面这样可以节约界面加载的时间 -->
	<jsp:include page="public/js.jsp"></jsp:include>
	<script src="../js/accountjs/rolesInfo.js"></script> 
</body>
</html>
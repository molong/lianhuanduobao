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
									<i class="icon-globe"></i>用户管理
								</div>
							</div>
							<!--显示内容信息的标题 end-->
							<!--进行帅选信息的条件  begin-->
							<div class="booking-search">
									<div class="clearfix margin-bottom-20">
										<div class="pull-left" >
											<button data-toggle="modal" data-target="#myModal" type="button" class="btn userinputbtn">添加</button> 
										</div>
										<div class="pull-left" >
											<button type="button" onclick="deleteAdmin()" class="btn userinputbtn">删除</button> 
										</div>
									</div>
							</div>
							<!--进行帅选信息的条件  end-->

							<div class="portlet-body">
							
								<div class="table-responsive"> 
									<!--table 显示数据 begin-->  <!-- 	去掉table 里的	 table-responsive  样式 -->
									<table id="table-adminInfo" class="table table-striped  table-hover   table-full-width" >
										<!--表格的标题 begin-->
										<thead class="navbar-static-top">
											<tr>
												<th><input id="all_choose" onclick="allselect()" type="checkbox" /></th>
<!-- 												<th>序号</th> -->
												<th>管理员ID</th>
												<th>管理员账号</th>
												<th>管理员角色</th>
												<th>秘钥</th>
												<th>上次登录时间</th>
												<th>上次登录IP</th>
												<th>最后登录时间</th>
												<th>最后登录IP</th>
												<th>登录次数</th>
												<th>是否绑定IP</th>
												<th>绑定IP</th>
												<th>禁用</th>
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
			<div style="width:400px;display: none;" class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
   				<div class="modal-dialog">
      				<div class="modal-content">
        				 <div class="modal-header">
            				<button type="button" class="close"	data-dismiss="modal" aria-hidden="true">&times;</button>
           	 				<h4 class="modal-title" id="myModalLabel">添加管理员</h4>
         				</div>
         			<div class="modal-body">
         				<form action="">
         					<table>
         						<tr>
         							<td>管理员账号：</td>
         							<td><input type="text" id="from_username" /></td>
         						</tr>
         						<tr>
         							<td>管理员密码：</td>
         							<td><input type="password" id="from_pwd" /></td>
         						</tr>
         						<tr>
         							<td>管理员密码确认：</td>
         							<td><input type="password" id="from_repwd" /></td>
         						</tr>
         						<tr>
         							<td>是否绑定IP：</td>
         							<td>
         							<input type="checkbox" id="from_isband" value="0" />
         							</td>
         						</tr>
         						<tr>
         							<td>是否禁用：</td>
         							<td><input type="checkbox" id="from_nullity" value="0" /></td>
         						</tr>
         						<tr>
         							<td>角色：</td>
         							<td>
         								<select id="from_roles">
         									
         								</select>
         						</tr>
         					</table>
         				</form>
        			</div>
        	<div class="modal-footer">
				<button type="button" onclick="add_adminInfo()" class="btn btn-primary">确定</button>
				
				
				<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
			</div>
      </div><!-- /.modal-content -->
</div>
</div><!-- /.modal -->


		<!-- 模态框（Modal） -->
			<div style="width:400px; display: none;" class="modal fade" id="editPwd" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
   				<div class="modal-dialog">
      				<div class="modal-content">
        				 <div class="modal-header">
            				<button type="button" class="close"	data-dismiss="modal" aria-hidden="true">&times;</button>
           	 				<h4 class="modal-title" id="myModalLabel">修改管理员密码</h4>
         				</div>
         			<div class="modal-body">
         					<input type="hidden" id="roleid_hidden" />
         					<input type="hidden" id="userid_hidden" />
         					<table>
         						<tr>
         							<td>管理员密码：</td>
         							<td><input type="password" id="edit_pwd" name="gameId" /></td>
         						</tr>
         						<tr>
         							<td>管理员密码确认：</td>
         							<td><input type="password" id="edit_repwd" name="gameName" /></td>
         						</tr>
         					</table>
         					<div class="modal-footer">
					            <button type="button" onclick="update_adminInfo()" id="edit_btn" class="btn btn-primary">
					               	确定
					            </button>
					            <button type="button" class="btn btn-default" data-dismiss="modal">取消 </button>
					        </div>
        			</div>
      </div><!-- /.modal-content -->
      
</div>
</div><!-- /.modal -->
			
		<!-- 把js放在后面这样可以节约界面加载的时间 -->
	<jsp:include page="public/js.jsp"></jsp:include>
	<script src="../js/accountjs/adminInfo.js"></script> 
</body>
</html>
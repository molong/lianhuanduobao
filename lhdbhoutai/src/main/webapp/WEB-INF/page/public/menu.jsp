<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Map" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"  %>
	<!-- BEGIN HEADER -->
	<div class="header navbar navbar-inverse navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container-fluid">
				<span class="titlename">后台管理系统</span>
				<a href="javascript:;" class="btn-navbar collapsed" data-toggle="collapse" data-target=".nav-collapse">
				<img src="../media/image/menu-toggler.png" alt="" />
				</a>
				<ul class="nav pull-right">
					<li>
						<span class="titlename" id="userloginedname">${sessionScope.loginFlag.username}</span>
					</li>
					<li class='hidden-480'><a href="#" onclick="getRoleId(${sessionScope.loginFlag.userid})" style='cursor:pointer;' data-toggle='modal' data-target='#commoneditPwd'>修改密码</a></li>
					<li class='hidden-480'><a href="../login/userExit" style="cursor:pointer;">退出</a></li>
				</ul>
			</div>
		</div>
	</div>
	<!--HEADER end-->

	<!-- BEGIN CONTAINER -->   
	<div class="page-container row-fluid">
		<!-- BEGIN 左侧 -->
		<div class="page-sidebar nav-collapse collapse">
			<ul class="page-sidebar-menu">
				<div class="sidebar-toggler hidden-phone"></div>
				
				<c:forEach var="pm" items="${sessionScope.userMenu}" varStatus="s">
				  	<c:if test="${ fn:length(pm.children)  > 0 }">
						<li class="" id="${pm.moduleid}">	
							<a href="#">
								<i class="icon-">
									<img src="../${pm.icon}"/>
								</i> 
			     				<span class="title">${ pm.title }</span>
								<span class="arrow"></span> 
							</a>
							<ul class="sub-menu">
								<c:forEach var="sm" items="${pm.children}" >
									<li id="${sm.moduleid}">
										<a href='javascript:show("${ sm.link }","${pm.moduleid}","${sm.moduleid}")'>${ sm.title }</a>
									</li>
								</c:forEach>
							</ul>
						</li>
					</c:if>
				</c:forEach>
			</ul>
		</div>
		<!-- BEGIN 右侧 -->
	</div><!-- END CONTAINER -->
	
			<!-- 模态框（Modal） -->
			<div style="width:400px; display: none;" class="modal fade" id="commoneditPwd" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
   				<div class="modal-dialog">
      				<div class="modal-content">
        				 	<div class="modal-header">
	            				<button type="button" class="close"	data-dismiss="modal" aria-hidden="true">&times;</button>
	           	 				<h4 class="modal-title" id="myModalLabel">修改密码</h4>
         				 	</div>
		         			<div class="modal-body">
		         					<input type="text" id="common_userid_hidden" />
		         					<table>
		         						<tr>
		         							<td>密码：</td>
		         							<td><input type="password" id="common_edit_pwd" name="gameId" /></td>
		         						</tr>
		         						<tr>
		         							<td>密码确认：</td>
		         							<td><input type="password" id="common_edit_repwd" name="gameName" /></td>
		         						</tr>
		         					</table>
		         					<div class="modal-footer">
							            <button type="button" onclick="update_adminInfo()" id="edit_btn" class="btn btn-primary">
							               	确定
							            </button>
							            <button type="button" class="btn btn-default" data-dismiss="modal">取消 </button>
							        </div>
		        			</div>
      				</div>
      			</div>
      </div><!-- /.modal-content -->
      
	<script src="../media/js/jquery-1.10.1.min.js"></script>
	
	
	<script type="text/javascript">
	
	
	function getRoleId(v){
		$("#common_userid_hidden").val(v);
	}
	
		function update_adminInfo(){
			var flag = true;
			if($("#common_edit_pwd").val()==null || $("#edit_pwd").val()==""){
				flag = false;
				$.messager.alert("修改提示", "请填写密码!");
			}else flag = true;
			if($("#common_edit_repwd").val()==null || $("#edit_repwd").val()==""){
				flag = false;
				$.messager.alert("修改提示", "请确认密码!");
			}else flag = true;
			if($("#common_edit_pwd").val() != $("#common_edit_repwd").val()){
				flag = false;
				$.messager.alert("修改提示", "请确认两次密码填写一致!");
			}else flag = true;
			if(flag){
				$.post("../adminUser/modifypass",{
// 					roleid:$("#roleid_hidden").val(),
					userid:$("#common_userid_hidden").val(),
					password:$("#common_edit_pwd").val()
	       		},function(v){
					if(v.success) {
						//点完确定按钮 隐藏 modal窗口
						$('#commoneditPwd').modal('hide');
						
						//刷新      
						//这里括号里面应该根据当前页面的页数  填写
						//待修改
						
						//提示信息显示
						$.messager.alert("修改提示", "修改成功!");
					}else{
						$.messager.alert("修改提示",v.message);
					}
				}
				);
			}
		}
	
	</script>

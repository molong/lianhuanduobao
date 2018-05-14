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
		<div>
			<div class="row-fluid">
				<div class="span12">
					<!--表格 begin-->
					<div class="portlet box blue">
						<!--显示内容信息的标题 begin-->
						<div class="portlet-title">
							<div class="caption">
								<i class="icon-globe"></i>充值/转账日志
							</div>
						</div>
						<!--显示内容信息的标题 begin-->
						<div class="portlet-title">
							<div class="caption">
								充值/转账总数<span id="sumScore"></span>(金币)
							</div>
						</div>
						<!--显示内容信息的标题 end-->
						<!--进行帅选信息的条件  begin-->
						<div class="booking-search">
							<form action="#" class="form-horizontal showallform">
								<div class="clearfix margin-bottom-20">
								<label class="pull-left" style="margin-top: 5px;text-align: right;">查询条件：</label>
								
								<div class="pull-left" >
									<input type="text" placeholder="用户id" id="userId" class="m-wrap medium userinput" >
									<input type="text" placeholder="充值/转账用户id" id="masterId" class="m-wrap medium userinput" >
									<select id="itype">
										<option value="">充值/转账</option>
				    					<option value="0">充值</option>
				    					<option value="1">转账</option>
			    					</select>
			    					<div class="input-prepend input-group">
				                       <span class="add-on input-group-addon"  style="padding: 6px 12px;"><i class="icon-calendar"></i></span>
				                       <input type="text" style="width:300px;height: 30px;" name="reservation" id="reservationtimeadd" class="form-control" 
				                       value=""  class="span3"/>
				                     </div>
				                     <input type="hidden"  id="start" name="start"/>
										<input type="hidden"  id="end" name="end"/>
								</div>
									<div class="pull-left">
									<button id="btn-rechargeSearch" type="button" class="btn userinputbtn" style="margin-left:2px;" onclick="search(1)">检索</button>
									</div>
								</div>
							</form>
						</div>
						<div class="portlet-body">
							<div class="table-responsive">
							<!--table 显示数据 begin-->
							<table id="table-data" class="table table-striped  table-hover   table-full-width" >
								<!--表格的标题 begin-->
								<thead class="navbar-static-top">
									<tr>
										<th>类型</th>
										<th>(id)用户名</th>
										<th>充值/转账(id)用户名</th>
										<th>充值/转账金额</th>
										<th>创建日期</th>
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
</div>
<!-- 把js放在后面这样可以节约界面加载的时间 -->
<jsp:include page="public/js.jsp"></jsp:include>
<script src="../js/accountjs/takeScoreLog.js"></script> 
</body>
</html>
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
								<i class="icon-globe"></i>反水记录
							</div>
						</div>
						<!--显示内容信息的标题 end-->
						<!--进行帅选信息的条件  begin-->
						<div class="booking-search">
							<form action="#" class="form-horizontal showallform">
								<div class="clearfix margin-bottom-20">
								<label class="pull-left" style="margin-top: 5px;text-align: right;">查询条件：</label>
								
								<div class="pull-left" >
									<input type="text" placeholder="用户ID" id="userId" class="m-wrap medium userinput" >
								</div>
									<div class="pull-left">
									<button id="btn-rechargeSearch" type="button" class="btn userinputbtn" style="margin-left:2px;" onclick="search()">检索</button>
									</div>
								</div>
							</form>
						</div>
						<!--进行帅选信息的条件  end-->
	
						<div class="portlet-body">
							<div class="table-responsive">
							<!--table 显示数据 begin-->
							<table id="table-data" class="table table-striped  table-hover   table-full-width" >
								<!--表格的标题 begin-->
								<thead class="navbar-static-top">
									<tr>
										<th>用户ID</th>
										<th>反水金额</th>
										<th>总投注</th>
										<th>反水时间</th>
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
<script src="../js/accountjs/rebateLog.js"></script> 
</body>
</html>
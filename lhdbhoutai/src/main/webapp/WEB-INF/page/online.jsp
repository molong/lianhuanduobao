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
								<i class="icon-globe"></i>在线用户
							</div>
						</div>
						<!--显示内容信息的标题 end-->
						<div class="portlet-body">
							<div class="table-responsive">
							<!--table 显示数据 begin-->
							<table id="table-data" class="table table-striped  table-hover   table-full-width" >
								<!--表格的标题 begin-->
								<thead class="navbar-static-top">
									<tr>
										<th>用户ID</th>
										<th>用户名</th>
										<th>金币</th>
										<th>手机</th>
										<th>注册ip</th>
										<th>介绍人id</th>
										<th>注册时间</th>
										<th>操作时间</th>
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
<script src="../js/accountjs/online.js"></script>
</body>
</html>
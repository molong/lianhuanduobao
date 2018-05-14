<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->
<!--[if !IE]><!--> <html lang="en"> <!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
	<meta charset="utf-8" />
	<title>登录界面</title>
	<meta content="width=device-width, initial-scale=1.0" name="viewport" />
	<meta content="" name="description" />
	<!-- BEGIN GLOBAL MANDATORY STYLES -->
	<link href="media/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
	<link href="media/css/bootstrap-responsive.min.css" rel="stylesheet" type="text/css"/>
	<link href="media/css/font-awesome.min.css" rel="stylesheet" type="text/css"/><!--字体和图标的样式-->
    <link href="media/css/style-metro.css" rel="stylesheet" type="text/css"/><!--表单的样式-->
	<link href="media/css/style.css" rel="stylesheet" type="text/css"/> 
	<link href="media/css/login.css" rel="stylesheet" type="text/css"/>
	<!-- END PAGE LEVEL STYLES -->
</head>
<body class="login">
	<div class="bgimg">
	<div class="content">
		<!-- BEGIN LOGIN FORM -->
		<form class="form-vertical login-form">
			<h3 class="form-title">连环夺宝后台管理</h3>
			<div class="alert alert-error hide">
				<button class="close" data-dismiss="alert"></button>
				<span></span>
			</div>
			<div class="control-group">
				<label class="control-label visible-ie8 visible-ie9">用户名</label>
				<div class="controls">
					<div class="input-icon left">
						<i class="icon-user"></i>
						<input class="m-wrap placeholder-no-fix" type="text" placeholder="请输入您的用户名" id="username" name="username"/>
					</div>
				</div>
			</div>

			<div class="control-group">
				<label class="control-label visible-ie8 visible-ie9">请输入您的密码</label>
				<div class="controls">
					<div class="input-icon left">
						<i class="icon-lock"></i>
						<input class="m-wrap placeholder-no-fix" type="password" placeholder="请输入您的密码" id="password" name="password"/>
					</div>
				</div>
			</div>

			<div class="form-actions">
				<label class="checkbox">
				<input type="checkbox" checked="checked" id="remember_id" name="remember" value="1" style="margin: 4px 0 0;"/> 记住账号和密码
				</label>
				<button type="submit" class="btn green pull-right" id="logBtn">
				登陆 <i class="m-icon-swapright m-icon-white"></i>
				</button>
				<input type="hidden" id="token" value="${CSRFToken }">
			</div>
		</form>
		<!-- END LOGIN FORM -->        
	</div>
    </div>
    </body>
	<!-- END LOGIN -->
	<script src="media/js/jquery-1.10.1.min.js" type="text/javascript"></script>
	<script src="media/js/jquery.cookie.js" type="text/javascript"></script>
	<script src="media/js/bootstrap.min.js" type="text/javascript"></script>
   <!--表单验证的js控件-->
	<script src="media/js/jquery.validate.min.js" type="text/javascript"></script>
	
	<!--让IE6-8支持CSS3 Media Query-->
	<!--[if lt IE 9]>
	<script src="media/js/excanvas.min.js"></script>
	<script src="media/js/respond.min.js"></script> 
	<![endif]-->  
    <!--核心脚本处理整个布局和基本功能-->
    
    <script src="media/js/app.js" type="text/javascript"></script>
	<!--登录的验证-->
	<script type="text/javascript">
	
	var Login = function () {
	    return {
	        init: function () {
	        	
	           $('.login-form').validate({
		            errorElement: 'label', //default input error message container
		            errorClass: 'help-inline', // default input error message class
		            focusInvalid: false, // do not focus the last invalid input
		            rules: {
		                username: {
		                    required: true
		                },
		                password: {
		                    required: true
		                },
		              /* code: {
		                    required: true
		                }, */
		                remember: {
		                    required: false
		                }
		            },
	
		            messages: {
		                username: {
		                    required: "请输入用户名"
		                },
		                password: {
		                    required: "请输入密码"
		                }/* ,
		                code: {
		                    required: "请输入手机安全码"
		                } */
		            },
	
		           /*  invalidHandler: function (event, validator) { //display error alert on form submit   
		            	//$('.alert-error').html("请输入您的用户名和账号");
		                $('.alert-error', $('.login-form')).show();
		            }, */
	
		            highlight: function (element) { // hightlight error inputs
		                $(element)
		                    .closest('.control-group').addClass('error'); // set error class to the control group
		            },
	
		            success: function (label) {
		                label.closest('.control-group').removeClass('error');
		                label.remove();
		            },
	
		            errorPlacement: function (error, element) {
		                error.addClass('help-small no-left-padding').insertAfter(element.closest('.input-icon'));
		            },
	
		            submitHandler: function (form) {
		            	doLogin();
		            }
		        });
	
		        $('.login-form input').keypress(function (e) {
		            if (e.which == 13) {
		                if ($('.login-form').validate().form()) {
		                	doLogin();
		                }
		                return false;
		            }
		        });
	        }
	    };
	}();
	
	function doLogin(){
		var headers = {};
	    headers['__RequestVerificationToken'] = $("#token").val();
	    headers['username'] = $("#username").val();
	    headers['password'] = $("#password").val();
		$.post(
				"login/userLogin",
				headers,
				function(resultObject){
    		    	if (resultObject.success) {
    		    		var username = $("#username").val(); 
    		    		var password = $("#password").val();
    		    		if($('#remember_id').is(':checked')){
	    		    		$.cookie("username", username, { expires: 30 }); 
	    		    		$.cookie("password", password, { expires: 30 }); 
    		    		}else{
    		    			$.cookie("username","");
    		    			$.cookie("password","");
    		    		}
    		    		window.location.href="page?__RequestVerificationToken="+$("#token").val(); 
					}else{
						$('.alert-error').html(resultObject.message);
		                $('.alert-error', $('.login-form')).show();
					}
				},
				'json');
	   }
	</script>	
	
	<script type="text/javascript">		
		$(function(){
			App.init();
			Login.init();
			if ($.cookie("username")!="") {
	        	$("#username").val($.cookie("username")); 
	        	$("#password").val($.cookie("password")); 
        	}
		});
	</script>
<!-- END BODY -->
</html>
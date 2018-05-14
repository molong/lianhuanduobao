jQuery(document).ready(function() {     
			App.init();
			selectRoles();
			Search.init();//时间插件的调取
			
			search(1);
			check();
		});
		
	$.messager.model = { 
        ok:{ text: "确定", classed: 'btn-default' },
        cancel: { text: "取消", classed: 'btn-error' }
      };
		var currentPageNo = 1;
		function search(currentPage){
			currentPageNo = currentPage;
			showLoading("table-adminInfo");
			hideEmptyDesc();
			
		  $.post("../adminUser/search",{
			  currentPage:currentPage,
			  limit:30
			  },function(resultObject){
				if(resultObject.success) {
					
					hideLoading();
					
					$("#table-adminInfo>tbody").html("");
					$.each(resultObject.data, function(key, val) {
						var trObj = $("<tr/>");
						trObj.append($("<td><input name='checkbox' type='checkbox' value='"+val.userid+"' class='allcheckbox' /></td>"));
						//trObj.append($("<td>"+ (key+1) + "</td>"));
						trObj.append($("<td>"+ val.userid  +"</td>"));
						trObj.append($("<td>"+ val.username  +"</td>"));
						trObj.append($("<td>"+ val.rolename  +"</td>"));
						trObj.append($("<td>"+ val.secret  +"</td>"));
						trObj.append($("<td>"+ val.prelogintime  +"</td>"));
						trObj.append($("<td>"+ val.preloginip  +"</td>"));
						trObj.append($("<td>"+ val.lastlogintime + "</td>"));
						trObj.append($("<td>"+ val.lastloginip  +"</td>"));
						trObj.append($("<td>"+ val.logintimes  +"</td>"));
						trObj.append($("<td class='click'>"+ changeShow(val.isband)  +"</td>"));
						trObj.append($("<td class='click'>"+ val.bandip  +"</td>"));
						trObj.append($("<td class='click'>"+ changeShow(val.nullity)  +"</td>"));
						trObj.append($("<td style='text-align: center;'><a style='cursor:pointer;' data-toggle='modal' data-target='#editPwd' onclick='getRoleId("+val.roleid+","+val.userid+")'>修改</a>" +
								"&nbsp;|<a style='cursor:pointer;' onclick='disabledUser("+val.userid+","+val.nullity+")'>"+(val.nullity == 0 ? "冻结" : "解冻")+"</a>" +
								"&nbsp;|<a style='cursor:pointer;' onclick='bandIP("+val.userid+",\""+val.lastloginip+"\","+val.isband+")'>"+(val.isband == 0 ? "绑定IP" : "取消绑定")+"</a>" + 
								"</td>"));
						trObj.appendTo("#table-adminInfo>tbody");
					});
					
				}else{
					hideLoading();
					alert(resultObject.message);
				}
			},
			'json');
		}
		
		function bandIP(userid, lastloginip, isband) {
			var banded = 1; ip = "000.000.000.000";
			if(isband == 1) {
				banded = 0; 
				ip = "000.000.000.000";
			}else{
				banded =  1;
				ip = lastloginip;
			} 
			$.post("../adminUser/modify", {
				userid:userid,
				isband: banded,
				bandip: ip
			}, function(result) {
				if(result.success) {
					search(currentPageNo);
				} else {
					alert(result.message);
				}
			});
		}
		
		function disabledUser(userid, nullity) {
			var status = 1;
			if(nullity == 1) status = 0; 
			$.post("../adminUser/modify", {
				disabled: status,
				userid: userid
			}, function(result) {
				if(result.success) {
					search(currentPageNo);
				} else {
					alert(result.message);
				}
			});
		}
		
		function deleteAdmin(){
			//$("#table-adminInfo :checkbox").attr("checked",true);
			var useridArray = new Array();
			var flag = false;
			$("#itemContainer input[name='checkbox']").each(function () {
				  if($(this).is(":checked")){
					  useridArray.push($(this).val());
					  flag = true;
				  }
			});
			var ids = '?';
			for(var i = 0;i< useridArray.length;i++){
				if(i>0){
					ids = ids+'&idArray='+useridArray[i];
				}else{
					ids = ids+'idArray='+useridArray[i];
				}
				
			}
			if(!flag){
				$.messager.alert("删除提示", "请至少选择一项再删除!");
			}else{
				$.messager.confirm("删除操作", "您确认要执行删除操作?", function() { 
					$.post("../adminUser/del"+ids,{},function(v){
						if(v.success) {
							//刷新      
							//这里括号里面应该根据当前页面的页数  填写
							//待修改
							search(1);
							
							//提示信息显示
							$.messager.alert("删除提示", "删除成功!");
						}else{
							alert(v.message);
						}
					}
				);
				});
			}
			
		}
		function add_adminInfo(){
				var flag = false;
				if($("#from_username").val()==null || $("#from_username").val()==""){
					$.messager.alert("提示", "管理员账号不能为空!");
					flag = false;
				}else{
					flag =true;
				}
				var flag_pwd = false;
				if($("#from_pwd").val()==null || $("#from_pwd").val()==""){
					$.messager.alert("提示", "管理员密码不能为空!");
					flag_pwd = false;
				}else{
					flag_pwd =true;
				}
				var flag_repwd = false;
				if($("#from_pwd").val()!=$("#from_repwd").val()){
					$.messager.alert("提示", "请确保两次密码一致!");
					flag_repwd = false;
				}else{
					flag_repwd =true;
				}
				if(flag && flag_pwd && flag_repwd){
				$.post("../adminUser/add",{
					username:$("#from_username").val(),
					password:$("#from_pwd").val(),
					isband:$("#from_isband").val(),
					nullity:$("#from_nullity").val(),
					roleid:$("#from_roles").val()
	       		},function(v){
					if(v.success) {
						//点完确定按钮 隐藏 modal窗口
						$('#myModal').modal('hide');
						
						//刷新      
						//这里括号里面应该根据当前页面的页数  填写
						//待修改
						search(1);
						
						//提示信息显示
						$.messager.alert("提示", "增加成功!");
					}else{
						alert(v.message);
					}
				}
	       		
				);
			}
		}
		
		function getRoleId(obj,v){
			$("#roleid_hidden").val(obj);
			$("#userid_hidden").val(v);
		}
		
		function update_adminInfo(){
			var flag = true;
			if($("#edit_pwd").val()==null || $("#edit_pwd").val()==""){
				flag = false;
				$.messager.alert("修改提示", "请填写密码!");
			}else flag = true;
			if($("#edit_repwd").val()==null || $("#edit_repwd").val()==""){
				flag = false;
				$.messager.alert("修改提示", "请确认密码!");
			}else flag = true;
			if($("#edit_pwd").val() != $("#edit_repwd").val()){
				flag = false;
				$.messager.alert("修改提示", "请确认两次密码填写一致!");
			}else flag = true;
			if(flag){
				$.post("../adminUser/modifypass",{
					roleid:$("#roleid_hidden").val(),
					userid:$("#userid_hidden").val(),
					password:$("#edit_pwd").val()
	       		},function(v){
					if(v.success) {
						//点完确定按钮 隐藏 modal窗口
						$('#editPwd').modal('hide');
						
						//刷新      
						//这里括号里面应该根据当前页面的页数  填写
						//待修改
						search(1);
						
						//提示信息显示
						$.messager.alert("修改提示", "修改成功!");
					}else{
						$.messager.alert("修改提示",v.message);
					}
				}
				);
			}
			
		}
		
		function check(){
			$("#from_isband").click(function(){
				if($("#from_isband").attr("value")==0){
					$("#from_isband").attr("value",1);
				}else{
					$("#from_isband").attr("value",0);
				}
			});
			$("#from_nullity").click(function(){
				if($("#from_nullity").attr("value")==0){
					$("#from_nullity").attr("value",1);
				}else{
					$("#from_nullity").attr("value",0);
				}
			});
		}
		
		function selectRoles(){
			
			$.ajax({  
                url: "../roles/search",    //后台webservice里的方法名称  
                type: "post",  
                dataType: "json",  
              //  contentType: "application/json",  
                traditional: true,  
                success: function (resultObject) {  
                	var optionstring = "";
                	 $.each(resultObject.data, function(key, val) {
                		optionstring += "<option value=\"" +val.roleid + "\" >" + val.rolename + "</option>";
                     });
                	 $("#from_roles").html(optionstring);
                }
               
            });
		}
		
		
		function allselect(){
				if($("#all_choose").is(":checked")){
					$("#itemContainer input[name='checkbox']").each(function () {
						  $(this).prop("checked","checked");
					});
				}else{
					$("#itemContainer input[name='checkbox']").each(function () {
							$(this).prop("checked",false);
					});
				}
		}
		
		//1为是，0为否
		function changeShow(obj){
			if(obj==0){
				return '否';
			}else
				return '<font color="red">是</font>';
			
		}
		

jQuery(document).ready(function() {       
			App.init();
			search(1);
			Search.init();//时间插件的调取
		});

$.messager.model = { 
        ok:{ text: "确定", classed: 'btn-default' },
        cancel: { text: "取消", classed: 'btn-error' }
      };
		
		function search(currentPage){
			
			showLoading("table-rolesInfo");
			hideEmptyDesc();
			
		  $.post("../roles/search",{
			  currentPage:currentPage,
			  limit:30
			  },function(resultObject){
				if(resultObject.success) {
					
					hideLoading();
					
					if(resultObject.data.length==0){
						showEmptyDesc("table-rolesInfo");//显示没有查询结果的文字描述
						return;
					}
					
					$("#table-rolesInfo>tbody").html("");
					$.each(resultObject.data, function(key, val) {
						
						var trObj = $("<tr/>");
						trObj.append($("<td>"+ key + "</td>"));
						trObj.append($("<td>"+ val.roleid  +"</td>"));
						trObj.append($("<td>"+ val.rolename  +"</td>"));
						trObj.append($("<td>"+ val.description  +"</td>"));
						trObj.append($("<td><a href='javascript:void(0);' onclick='delete_rolesInfo("+val.roleid+")'>删除</a>       <a style='cursor:pointer;' data-toggle='modal' data-target='#Modify' onclick='getModules("+val.roleid+")'>修改</a></td>"));
						trObj.appendTo("#table-rolesInfo>tbody");
					});
					
				}else{
					hideLoading();
					alert(resultObject.message);
				}
			},
			'json');
		}
		
		function add_rolesInfo(){
			$.post("../roles/addrole",{
				rolename:$("#from_rolename").val(),
				description:$("#from_description").val()
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
			}});
		}
		
		//
		function update_rolesInfo(obj){
			if (obj != null) {
				updateGameKindForm(obj);
				
				//点击确定按钮事件
				$("#edit_btn").click(function(){
				$.post("../gameGameItem/updateGameItemOne",{
	       		    gameId:$("#edit_from_gameid").val(),
	       		    gameName:$("#edit_from_gamename").val(),
	       		    dataBaseAddr:$("#edit_from_databaseaddr").val(),
	       		    dataBaseName:$("#edit_from_databasename").val(),
	       		    serverVersion:$("#edit_from_serverversion").val(),
	       		    clientVersion:$("#edit_from_clientversion").val()
	       		},function(v){
					if(v.success) {
						//点完确定按钮 隐藏 modal窗口
						$('#EditModal').modal('hide');
						
						//刷新      
						//这里括号里面应该根据当前页面的页数  填写
						//待修改
						search(1);
						
						//提示信息显示
						$.messager.alert("提示", "修改成功!");
				}});
				});
			}
		}
		
		//改变表单数据
		function updateGameKindForm(gameid){
			$.post("../gameGameItem/searchGameItemOne",{
       		    gameId:gameid
       		},function(v){
				if(v.success) {
					var obj = v.data;
					$("#edit_from_gameid").val(obj.gameId);
					$("#edit_from_gamename").val(obj.gameName);
					$("#edit_from_databaseaddr").val(obj.dataBaseAddr);
					$("#edit_from_databasename").val(obj.dataBaseName);
					$("#edit_from_serverversion").val(obj.serverVersion);
					$("#edit_from_clientversion").val(obj.clientVersion);
			}});
		}
		
		function delete_rolesInfo(gameid){
			$.messager.confirm("删除操作", "您确认要执行删除操作?", function() { 
	   	    		if (gameid != null) {
	   	    			$.post("../roles/delrole",{
	   	    				roleid:gameid
	   	        		},function(resultObject){
	   	 				if(resultObject.success) {
	   	 					search(1);
	   	 					$.messager.alert("提示", "删除成功!");
	   	 				}});
	   	    		}
		      });
			
   	    }
		
		/**
		 * 获取所有组件及权限ModulePermission
		 * 参数roleid允许为控制，在ModuleMaaper中对roleid进行了判断
		 * @param roleid
		 */
		function getModules(roleid){
			$("#modify_table").html("");  //查询前清空table
			$.post(
				"../Modules/getmodulebyrole",
				{
					roleid:roleid
				},
				function(data){
					var str = "";		   //定义一个tr标签对象
					var sinput = "";	   //定义一个input标签对象
					var valueStr = "";	   //声明 input中的value值
					var mybit = "";		   //用于接收二进制样式的字符串 
					var rowSpanNo = "";
					var tbTitle = "<tr style='background-color:#F5F5F5'><th>一级菜单</th><th>二级菜单</th><th>功能权限</th></tr>"+"<input type='hidden' name='roleid' value='"+roleid+"'/>";
					$(tbTitle).appendTo($("#modify_table"));
					$.each(data,function(k1,obj1){
						$.each(obj1,function(k2,obj2){                      //获取一级菜单title,obj2一级菜单对象
							$.each(obj2.children,function(k3,obj3){         //获取二级菜单title,obj3二级菜单对象
								rowSpanNo = k3+1;
								if(k3==0){
									str = "<tr><td id='title"+k2+k3+"'>"+obj2.title+"</td><td>"+obj3.title+"</td><td></td></tr>";
								}else{
									str = "<tr><td>"+obj3.title+"</td><td></td></tr>";
								}
								$.each(obj3.children,function(k4,obj4){     //获取权限对象,obj4权限对象
									valueStr = obj3.moduleid + "_" + obj4.permissionValue;    //将二级菜单id（moduleid）和权限（permissionValue）拼接成字符串，作为下面checkbox的value
									var op = obj4.operationPermission;		//获取当前权限module的权限值
									var p = obj4.permissionValue;			//获取当前二级菜单的权限值
									if(op == null || op == ""){				//如果op为空，则表示没有当前module的权限，checked为空
										sinput += "<input type='checkbox' value='" + valueStr + "' style='margin-left:20px;position:relative;top:-3px;right:5px' onclick='_changeValue(this)' >"+obj4.title+"</input>" +
													"<input type='hidden' id='" + valueStr + "' name='permission' value='" + obj3.moduleid + "_0" + "'>";
									}else{									//如果op不为空，再对角色的操作权限与当前module权限值进行对比，判断是否有当前module的权限
										mybit = _bitwise(op.toString(2),p.toString(2));		//mybit为二进制样式的字符串。模拟二进制数字比较，根据返回结果判断是否有权限。如果返回值为0，则表示无当前module权限；如果返回值大于0，则表示有当前模块权限
										if(mybit.indexOf(1) == -1){							//如果mybit中"1"的下标为负，则表示不包含"1"，即mybit表达的二进制数字为"0"，checked为空
											sinput += "<input type='checkbox' value='" + valueStr + "' style='margin-left:20px;position:relative;top:-3px;right:5px' onclick='_changeValue(this)'>"+obj4.title+"</input>" +
														"<input type='hidden' id='" + valueStr + "' name='permission' value='" + obj3.moduleid + "_0" + "'>";
										}else{												//如果mybit中"1"的下标不为负，则表示包含"1"，即mybit表达的二进制数字大于"0"，checked为"checked"
											sinput += "<input type='checkbox' value='" + valueStr + "' checked='checked' style='margin-left:20px;position:relative;top:-3px;right:5px' onclick='_changeValue(this)'>"+obj4.title+"</input>" +
														"<input type='hidden' id='" + valueStr + "' name='permission' value='" + valueStr + "'>";
										}
									}
								});
								$(str).appendTo($("#modify_table"));
								$(sinput).appendTo($("td:last"));
								sinput="";
								str="";
							});
							$("#title"+k2+"0").attr("rowspan",rowSpanNo);
						});
					});
				}
			);
		}
		
		//保存角色权限RolePermission
		function saveRP(){
			var dataForm = $("#modify_form").serialize();
			$.post(
				"../Modules/savePermission",
				dataForm,
				function(rs){
					$('#Modify').modal('hide');
					alert(rs.data);
				}
			);
		}
		
		/**
		 * 控制隐藏域的属性
		 * @param input
		 */
		function _changeValue(input){
			var ischecked = input.checked;            
			var inputId = input.value;
			var inputValue = input.value;
			var len = inputId.indexOf("_");
			var moduleid = inputId.substring(0,len);
			if(ischecked){
				$("#"+inputId).val(inputValue);
			}else{
				$("#"+inputId).val(moduleid + "_0");
			}
		}
		
		/**
		 * 全选、全不选
		 */
		function _checkedAll(input){
			var allCheckBox = $("#modify_table input[type='checkbox']");
			var isChecked = input.checked;
			for(var i=0; i<allCheckBox.length; i++){
				checkBox = allCheckBox[i];
				if(isChecked){
					checkBox.checked=true;
				}else{
					checkBox.checked=false;
				}
				_changeValue(checkBox);
			}
		}
		
		/**
		 * 模拟二进制的 按位与 运算,返回二进制样式的 字符串
		 * 按位与运算(str1、str2必须为二进制样式的字符串，即只包含0、1)
		 */
		function _bitwise(str1,str2){
			var longStr = "";
			var shortStr = "";
			if(str1.length >= str2.length){
				longStr = str1;
				shortStr = str2;
			}else if(str1.length < str2.length){
				longStr = str2;
				shortStr = str1;
			}
			var str = "";
			for(var i = 0; i<longStr.length; i++){
				var t1 = longStr.substr(longStr.length-(i+1),1);
				if(t1 != "0" && t1 != "1") return longStr + ":类型不正正确,只允许'二进制'样式的字符串！";
				if(i<shortStr.length){
					var t2 = shortStr.substr(shortStr.length-(i+1),1);
					if(t2 != "0" && t2 != "1") return shortStr + ":类型不正正确,只允许'二进制'样式的字符串！";
					if(t1 == t2 && t1 == 1){
						str += "1";
					}else{
						str += "0";
					}
				}else{
					str += "0";
				}
			}
			return str.split("").reverse().join("");
		}

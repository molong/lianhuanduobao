var currentPageNo = 1;
jQuery(document).ready(function() {       
			App.init();
			Search.init();//时间插件的调取
			search(currentPageNo);
			$.tableSort();
		});

		function search(currentPage){
			currentPageNo = currentPage;
			showLoading("table-onlineUser");
			hideEmptyDesc();
			
		  $("#bootstrapPaginator").html("");
		  $("#table-onlineUser>tbody").html("");
		  $.post("../user/search",{
			  currentPage:currentPage,
			  phone:$("#phone").val(),
			  username:$("#username").val(),
			  limit:30,
			  sort: TABLE_SORT_STR ? TABLE_SORT_STR : ""
			  },function(resultObject){
				if(resultObject.success) {
					hideLoading();
					if(resultObject.data.length==0){
						showEmptyDesc("table-onlineUser");//显示没有查询结果的文字描述
						return;
					}
					$.each(resultObject.data, function(key, val) {
						
						var trObj = $("<tr/>");
						trObj.append($("<td  title='" + val.id + "'><a href='javascript:void(0);' class='splitchars' title='" + val.id + "'  onclick=openNewWin('userInfomation.jsp?userid="+val.id+"','"+null+"',1250,600)>"+val.id+"</a><input type=\"button\" style=\"width:16px;margin-left:7px;\" class=\"thistdvalue\"  onclick=\"copyClick(this);\" /></td>"));
						trObj.append($("<td class='hidden-480'>"+ val.username  +"</td>"));
						trObj.append($("<td class='hidden-480'>"+ val.first_name  +"</td>"));
						trObj.append($("<td>"+ val.last_name +"</td>"));
						trObj.append($("<td>"+ val.phone  +"</td>"));
						trObj.append($("<td>"+ val.last_login  +"</td>"));
						trObj.append($("<td>"+ val.last_logout  +"</td>"));
						trObj.append($("<td>"+ val.insertDate  +"</td>"));
						trObj.append($("<td>"+ val.updateDate  +"</td>"));
						trObj.appendTo("#table-onlineUser>tbody");
					});
					myBootstrapPaginator('bootstrapPaginator',resultObject.params.pager);
					
				}else{
					alert(resultObject.message);
				}
			},
			'json');
		}
        
        /*复制粘贴*/
        function copyToClipboard(txt) {
			var sUserAgent = navigator.userAgent.toLowerCase();  
            var bIsIpad = sUserAgent.match(/ipad/i) == "ipad";  
            var bIsIphoneOs = sUserAgent.match(/iphone os/i) == "iphone os";  
            var bIsMidp = sUserAgent.match(/midp/i) == "midp";  
            var bIsUc7 = sUserAgent.match(/rv:1.2.3.4/i) == "rv:1.2.3.4";  
            var bIsUc = sUserAgent.match(/ucweb/i) == "ucweb";  
            var bIsAndroid = sUserAgent.match(/android/i) == "android";  
            var bIsCE = sUserAgent.match(/windows ce/i) == "windows ce";  
            var bIsWM = sUserAgent.match(/windows mobile/i) == "windows mobile"; 
		var	isMobile = bIsIpad || bIsIphoneOs || bIsMidp || bIsUc7 || bIsUc || bIsAndroid || bIsCE || bIsWM;
		if (window.clipboardData) {
			window.clipboardData.clearData();
			window.clipboardData.setData("Text", txt);
			alert("复制成功！");
		} else if (navigator.userAgent.indexOf("Opera") != -1) {
			window.location = txt;
			alert("复制成功！");
		} else if(navigator.userAgent.indexOf("Chrome") != -1){
			window.prompt ("粘贴板信息: 请使用快捷键Ctrl C, Enter", txt);
		}else if (window.netscape) {
			try {
				netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect");
			} catch (e) {
				alert("被浏览器拒绝！\n请在浏览器地址栏输入'about:config'并回车\n然后将 'signed.applets.codebase_principal_support'设置为'true'");
			}
			var clip = Components.classes['@mozilla.org/widget/clipboard;1'].createInstance(Components.interfaces.nsIClipboard);
			if (!clip)
				return;
			var trans = Components.classes['@mozilla.org/widget/transferable;1'].createInstance(Components.interfaces.nsITransferable);
			if (!trans)
				return;
			trans.addDataFlavor('text/unicode');
			var str = new Object();
			var str = Components.classes["@mozilla.org/supports-string;1"].createInstance(Components.interfaces.nsISupportsString);
			var copytext = txt;
			str.data = copytext;
			trans.setTransferData("text/unicode", str, copytext.length * 2);
			var clipid = Components.interfaces.nsIClipboard;
			if (!clip)
				return false;
			clip.setData(trans, null, clipid.kGlobalClipboard);
			alert("复制成功！");
		}else if(isMobile){
			window.prompt ("粘贴板信息: 请使用手机自带复制功能复制, Enter", txt);
		}else if(copy){
			copy(txt);
			alert("复制成功！");
		}
	}
     function copyClick(source){
    	// alert();
	     	copyToClipboard($(source).parent().attr("title"));
     }
     function monitor(userId, type) {
			$.post("../userAccountInfo/monitor", {
				userId: userId,
				type: type
			}, function(data) {
				alert(data.data);
				if(data.success) {
					search(currentPageNo);
				}
			});
		}
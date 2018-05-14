//表单排序（只用于表格内容为"数字"的列）
window["TABLE_SORT_STR"] = "";
(function($){  
    $.extend($,{
    	tableSort: function() {
    		var sortColumns = $(".sorting");
    		for(var i = 0; i<sortColumns.length; i++) {
    			var sc = $(sortColumns[i]);
    			if(sc.attr("index") && sc.attr("index")!="") {
    				sc.on("click", function(e) {
    					var target = $(e.target);
    					
    					var direction = "asc";
    					target.removeClass("sorting");
    					if(target.attr("class").indexOf("sorting_desc") < 0) {
    						sortColumns.attr("class", "sorting");
    						target.removeClass("sorting_asc");
    						target.addClass("sorting_desc");
    						direction = "desc";
    					} else {
    						sortColumns.attr("class", "sorting");
    						target.removeClass("sorting_desc");
    						target.addClass("sorting_asc");
    						direction = "asc";
    					}
    					TABLE_SORT_STR = "[{'property':'"+target.attr("index")+"','direction':'"+direction+"'}]";
    					search(currentPageNo);
    				});
    			}
    		}
    	},
        sortTable:{
        	//tableId需要进行排序的表格名称，Idx需要进行排序的列
            sort:function(tableId,Idx){
                var table = document.getElementById(tableId);  
                var tbody = table.tBodies[0];  
                var tr = tbody.rows;   
          
                var trValue = new Array();  
                for (var i=0; i<tr.length; i++ ) {  
                    trValue[i] = tr[i];  
                }  
          
                if (tbody.sortCol == Idx) {  
                    trValue.reverse(); 
                } else {  
                    //trValue.sort(compareTrs(Idx)); 
                    trValue.sort(function(tr1, tr2){  
                        var value1 = tr1.cells[Idx].innerHTML;  
                        var value2 = tr2.cells[Idx].innerHTML;
                        return value1 - value2;
                    });  
                }  
          
                var fragment = document.createDocumentFragment();
                for (var i=0; i<trValue.length; i++ ) {  
                    fragment.appendChild(trValue[i]);  
                }
                tbody.appendChild(fragment); 
                tbody.sortCol = Idx;  
            }
        }  
    });         
})(jQuery);

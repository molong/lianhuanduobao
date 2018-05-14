var Search = function () {
    return {
        //main function to initiate the module
        init: function () {
//            if (jQuery().datepicker) {
        		 $(".date-picker").val(moment().subtract('hours', 1).format('YYYY-MM-DD'));
            	 $('.date-picker').first().daterangepicker(
            		 null
                   , function(start, end, label) {
//                	   console.log(start.toISOString(), end.toISOString(), label);
                	   $(".date-picker").val(start.format('YYYY-MM-DD'));
                	   $(".date-picker").last().val(end.format('YYYY-MM-DD'));
//                     $("#concludeTime").val( end.format('YYYY-MM-DD'));
                   });
            	 
//                $('.date-picker').datepicker({
//                	format: 'yyyy-mm-dd',
//                	weekStart: 1,
//                    autoclose: true,
//                    todayBtn: 'linked',
//                    language: 'zh-CN'
//                });
                
//                var now = new Date();
//                var nowStr = now.format("yyyy-MM-dd"); 
//                $('.date-picker').val(nowStr);
         }
         //   App.initFancybox();
//        }

    };

}();
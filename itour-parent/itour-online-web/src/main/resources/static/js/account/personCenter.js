$(function(){
	
	;(function($,undefined){
		$.fn.uploadFile =function(options){
			var defaultOptions = {
				url:'/upload',
				width:'100',
				height:'100'
			}
			options = $.extend(defaultOptions, options);
			console.log(options);
			$('.vicp-drop-area input').on("change",upload);
			function upload(){
				fr = new FileReader();
				var file = $(".upload-dialog .vicp-drop-area input")[0].files[0];
				fr.readAsDataURL(file);
		    	fr.onload = function (e) {
		    		$(".vicp-img-container img").attr("src",e.target.result);
		    		$(".vicp-crop-right .vicp-preview-item-circle img").attr("src",e.target.result);
            		console.log(e.target.result)
           		};
           		$(".vicp-close").next().hide();
				$(".vicp-step1").prev().show();
				$(".vicp-step1").hide();
				$(".modi_dialog").find(".vicp-wrap").addClass("maxwrap");
				$(".vicp-step2").show();
			}
			
		}				 
	})(jQuery);
	
	
	$(document).on("click",".vicp-drop-area",function(e){
		document.querySelector('.vicp-drop-area input').click();
		$(".vicp-drop-area").uploadFile({url:'/upload',
				width:'100',
				height:'200'});
	});
	
	$(document).on("click",".modi_dialog .vicp-close,.vicp-step1 .vicp-operate a",function(){
		$(".modi_dialog").hide();
	});
	
	
/*	$(".userCenter .user-img").click(function(){
		$(".vicp-close").next().show();
		$(".vicp-step1").prev().hide();
		$(".vicp-step2").hide();
		$(".vicp-step1").show();
		$(".modi_dialog").find(".vicp-wrap").removeClass("maxwrap");
		$(".upload-dialog").show();
	});
	*/

	
});
function upload_file() {
    $('#form_upload').ajaxSubmit({            
        type: 'post',
        url :ctxPath+"/account/updateAvatar",
        dataType:"json",
        beforeSubmit:function(){
        	
        },
        success: function(data) {
        	var path = data.returnResult.result;
        	$(".userCenter .user-img img").attr("src",path);
        },
        error:function(data){
        	
        }
    });
 
}
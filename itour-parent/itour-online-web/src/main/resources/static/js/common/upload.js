	;(function($,undefined){
				$.fn.uploadFile =function(options){
					var defaultOptions = {
						url:'/upload',
						width:'100',
						height:'100'
					}
					options = $.extend(defaultOptions, options);
					console.log(options);
					//判断浏览器是否支持FileReader
					if(!window.FileReader){
						alert('您的浏览器不支持FileReader，请更换浏览器。');
						return;
					}
					$('.vicp-drop-area input').on("change",upload);
					function upload(){
						fr = new FileReader();
						var file = $(".upload-area .vicp-drop-area input")[0].files[0];
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
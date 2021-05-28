/**
 * 1.文件上传(拖拽|点击)--预览--裁剪--上传
 *  a.相关资料:
 *    https://www.jb51.net/article/88803.htm
 *    https://www.huanggr.cn/1666.html
 *    文件API
 *     a.HTML5还提供了FileReader接口：用于将文件读入内存，并读取文件中的数据;
 *     b.FileReader接口提供了四个方法和六个事件
 *       方法：
 *       1.readAsDataURL(file);读取文件DataURL
 *       事件：
 *       1.onload:读取文件成功时触发
 *    拖放API
 *     a.
 *      
 *       
 * @returns
 */
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
			var	fr = new FileReader();
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
	$(".userCenter .user-img").click(function(){
		$(".vicp-close").next().show();
		$(".vicp-step1").prev().hide();
		$(".vicp-step2").hide();
		$(".vicp-step1").show();
		$(".modi_dialog").find(".vicp-wrap").removeClass("maxwrap");
		$(".upload-dialog").show();
	});
	var dragArea = $(".vicp-drop-area").get(0);
	var options ={
	        imageBox: '.imageBox',
	        thumbBox: '.thumbBox',
	        spinner: '.spinner',
	        imgSrc: 'tx.png'
	}
	var cropper = new cropbox(options);
	dragArea.ondragstart = function(e,item){
          //判断当前浏览器是否为火狐浏览器
		 let userAgent = navigator.userAgent;
         let ifFirefox = userAgent.indexOf("Firefox");
         if(ifFirefox){
             e.dataTransfer.setData("text","Firefox");
             e.dataTransfer.setData("imgInfo", item);
         }

		 
		 console.log("ondragstart");
	}
	//1.进入目标元素触发
	dragArea.ondragenter = function(){
		console.log("dragenter");
	}
	//2.进入目标、离开目标之间，连续触发
	dragArea.ondragover = function(e){
		 e.preventDefault();
		console.log("dragover");
	}
	//3.离开目标元素触发
	dragArea.ondragleave = function(){
		console.log("dragleave");
	}
	//3.在目标元素上释放鼠标触发 
	dragArea.ondrop = function(e){
		e.preventDefault();
		var fr = new FileReader();
		var fs = e.dataTransfer.files;
		fr.readAsDataURL(fs[0]);
    	fr.onload = function (e) {
    		options.imgSrc = e.target.result;
            cropper = new cropbox(options);
	    $(".vicp-crop-right .vicp-preview-item-circle img").attr("src",e.target.result);
   		//显示图片预览页面
   		console.log(fs.length);
		$(".vicp-close").next().hide();
		$(".vicp-step1").prev().show();
		$(".vicp-step1").hide();
		$(".modi_dialog").find(".vicp-wrap").addClass("maxwrap");
		$(".vicp-step2").show();
		console.log("drop");
		console.log(e.dataTransfer.getData("text"));
	}
	}
	    
	   /* document.querySelector('#btnCrop').addEventListener('click', function(){
	        var img = cropper.getDataURL();
	        document.querySelector('.cropped').innerHTML += '<img src="'+img+'">';
	    })*/
	    $(document).on("click","#btnZoomIn",function(){
	    	 cropper.zoomIn();
	    	 var img = cropper.getDataURL();
	 	    $(".vicp-crop-right .vicp-preview-item-circle img").attr("src",img);
	    })
	    $(document).on("click","#btnZoomOut",function(){
	    	cropper.zoomOut();
	    	var img = cropper.getDataURL();
	 	    $(".vicp-crop-right .vicp-preview-item-circle img").attr("src",img);
	    })
	    /*document.querySelector('#btnZoomIn').addEventListener('click', function(){
	        cropper.zoomIn();
	    })
	    document.querySelector('#btnZoomOut').addEventListener('click', function(){
	        cropper.zoomOut();
	    })*/
	
});
function AutoResizeImage(maxWidth,maxHeight,objImg){
	//https://www.jb51.net/article/106016.htm
	var img = new Image();
	img.src = objImg.src;
	var hRatio;
	var wRatio;
	var ratio = 1;
	var w = img.width;
	var h = img.height;
	wRatio = maxWidth / w;
	hRatio = maxHeight / h;
	if (maxWidth ==0 && maxHeight==0){
	ratio = 1;
	}else if (maxWidth==0){//
	if (hRatio<1) ratio = hRatio;
	}else if (maxHeight==0){
	if (wRatio<1) ratio = wRatio;
	}else if (wRatio<1 || hRatio<1){
	ratio = (wRatio<=hRatio?wRatio:hRatio);
	}
	if (ratio<1){
	w = w * Ratio;
	h = h * Ratio;
	}
	objImg.height = h;
	objImg.width = w;
	}
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


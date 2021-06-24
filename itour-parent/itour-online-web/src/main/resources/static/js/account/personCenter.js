/**
 * 文件预览-缩放
 * @param $
 * @param window
 * @param document
 * @param undefined
 * @returns
 */
'use strict';
(function($, window, document, undefined) {
	$.fn.uploadFile = function(options) {
		var defaultOptions = {
			input_file_wrap: "#uploadFile", //文件上传按钮的标识 必须
			img_origin_wrapper: ".photo-orgin-wrap", //必须
			img_origin_wrap: ".orgin-photo", //原图 必须
			img_preview_wrap: ".pre-photo", //图片预览的标识 必须
			zoomInButton: "#btnZoomIn", //放大 必须
			zoomOutButton: "#btnZoomOut", //缩小 必须
			drag_wrapper:".vicp-drop-area",
			minheight: 184, //缩放时图片的最小高度
			iwidth:150,//原图片最小宽度
			iheight:150,//原图片最小高度
			ratio: 1, //图片缩放比例
			image: new Image(), //图片必须给一个图片路径
			state: {}
			
		}
		
		var currentOptions = $.extend(defaultOptions, options);
		var ele = document.querySelector(currentOptions.img_origin_wrap);
		var el = document.querySelector(currentOptions.img_origin_wrapper);
		var timer;
		$(currentOptions.drag_wrapper).on("click", dragPhoto);
		$(currentOptions.input_file_wrap).on("change", previewFile);
		$(currentOptions.zoomInButton).on("click", zoomIn);
		$(currentOptions.zoomOutButton).on("click", zoomOut);
		$(currentOptions.zoomInButton).on("mousedown", zoomIndown);
		$(currentOptions.zoomOutButton).on("mousedown", zoomOutdown);
		$(currentOptions.zoomInButton).on("mouseup", zoomInleave);
		$(currentOptions.zoomOutButton).on("mouseup", zoomOutleave);
		var obj = {
				position:{},
				getPosition:function(){
				var swidth = currentOptions.image.width,//原图w
				    sheight = currentOptions.image.height,//原图h			    
				    wRatio = ele.clientWidth / swidth,
				    hRatio = ele.clientHeight / sheight,
				    left = parseFloat(ele.style.left),//缩放后图片的向左的偏移量
				    top = parseFloat(ele.style.top),//缩放后图片的向上的偏移量
					dw = swidth * (currentOptions.minheight / sheight),//图片默认的宽
					dleft =((el.clientWidth - dw) / 2),//默认偏移量
				    sx = 0,//开始剪切的 x 坐标位置
				    sy = 0,//开始剪切的 y 坐标位置			    
				    width = swidth,
				    height = sheight;
				    var  sRatio = dw / swidth;
				    var  dRatio = currentOptions.minheight / sheight;
				   if(left<dleft &&left>0){
				   	    sx = (left -dleft) /wRatio;
				   	    sy = top / hRatio ;
				   	    width = swidth - sx;
				   	    height = sheight - sy;
				   }else if(left<0){
				   	    sx = left / wRatio;
				    	sy = top / hRatio;
				    	width = swidth - sx;
				    	height = sheight - sy;
				   }
				  this.position.x=sx;
				   this.position.y=sy;
				   this.position.width=width;
				   this.position.height=height;
				   return this.position;
				}
		}
		attachEvent(ele, "mousedown", mousedown);
		attachEvent(ele, "mouseup", mouseup);
		attachEvent(ele, "mouseleave", mouseup);
		/*$(currentOptions.img_origin_wrap).on("mousedown",mousedown);
		$(currentOptions.img_origin_wrap).on("mousemove",mousemove);
		$(document.body).on("mouseup",mouseup);*/
		attachEvent(ele, 'DOMNodeRemoved', function() {
			detachEvent(document.body, 'DOMNodeRemoved', mouseup)
		});
		//var mousewheel = (/Firefox/i.test(navigator.userAgent)) ? 'DOMMouseScroll' : 'mousewheel';
		//$(currentOptions.img_origin_wrap).on(mousewheel, zoomImage);
		function dragPhoto(){
			document.querySelector(currentOptions.input_file_wrap).click();
		}
		$(currentOptions.drag_wrapper).ondragstart = function(e,item){
	          //判断当前浏览器是否为火狐浏览器
			 let userAgent = navigator.userAgent;
	         let ifFirefox = userAgent.indexOf("Firefox");
	         if(ifFirefox){
	             e.dataTransfer.setData("text","Firefox");
	         }
		}
		//1.进入目标元素触发
		$(currentOptions.drag_wrapper).get(0).ondragenter = function(){
			//console.log("dragenter");
		}
		//2.进入目标、离开目标之间，连续触发
		$(currentOptions.drag_wrapper).get(0).ondragover = function(e){
			 e.preventDefault();
			//console.log("dragover");
		}
		//3.离开目标元素触发
		$(currentOptions.drag_wrapper).get(0).ondragleave = function(){
			//console.log("dragleave");
		}
		//4.在目标元素上释放鼠标触发 
		$(currentOptions.drag_wrapper).get(0).ondrop = function(e){
			e.preventDefault();
			var fr = new FileReader();
			//var fs = e.dataTransfer.files;
			var files = [];
			  [].forEach.call(e.dataTransfer.files, function(file) {
			    files.push(file);
			  },false);
			fr.readAsDataURL(files[0]);
	    	fr.onload = function (e) {
	    		currentOptions.image.src = e.target.result;
	    		var img = new Image();
	    		 img.src = e.target.result;
	    		 img.onload = function(){
	    			//显示图片预览页面
	 				$(".vicp-close").next().hide();
	 				$(".vicp-step1").prev().show();
	 				$(".vicp-step1").hide();
	 				$(".modi_dialog").find(".vicp-wrap").addClass("maxwrap");
	 				$(".vicp-step2").show();
	 		    	var width = currentOptions.image.width;
	 				var height = currentOptions.image.height;
	 				var hRatio = currentOptions.minheight / height;
	 				var w = width * hRatio; 
	 				var h = height * hRatio;
	 				var pw = (el.clientWidth - w) / 2;
	 				ele.setAttribute('style',
	 					'position: absolute; ' +
	 					'height: ' +h+ 'px; ' +
	 					'left: ' + pw + 'px; ' +
	 					'top: ' + 0 + 'px; '
	 				);
	 				$(currentOptions.img_origin_wrap).attr("src", e.target.result);
	 				$(currentOptions.img_preview_wrap).attr("src", e.target.result);
	    		 }
	    		
		   		
				//console.log("drop");
		}
		}
		function previewFile() {
			var fr = new FileReader();
			var files = $(currentOptions.input_file_wrap)[0].files;
			fr.readAsDataURL(files[0]);
			fr.onload = function(e) {
				currentOptions.image.src = e.target.result;
				var img = new Image();
				img.src = e.target.result;
				img.onload = function() {
					var width = currentOptions.image.width;
					var height = currentOptions.image.height;
					//图上距离/实际距离=r
					//实际图片的宽/实际图片的高=缩放后图片的宽/缩放后的图片的高
					var hRatio = currentOptions.minheight / height;
					var w = width * hRatio; 
					var h = height * hRatio;
					var pw = (el.clientWidth - w) / 2;
					ele.setAttribute('style',
						'position: absolute; ' +
						'height: ' +h+ 'px; ' +
						'left: ' + pw + 'px; ' +
						'top: ' + 0 + 'px; '
					);
					$(currentOptions.img_origin_wrap).attr("src", e.target.result);
					$(currentOptions.img_preview_wrap).attr("src", e.target.result);
				}
				$(".vicp-close").next().hide();
				$(".vicp-step1").prev().show();
				$(".vicp-step1").hide();
				$(".modi_dialog").find(".vicp-wrap").addClass("maxwrap");
				$(".vicp-step2").show();

			}
		}
        function zoomIndown(){
        	timer = setInterval(function(){
        		zoomIn();
        	},300)
        }
        function zoomInleave(){
        	clearInterval(timer);
        }
        function zoomOutdown(){
        	timer = setInterval(function(){
        		zoomOut();
        	},300)
        }
        function zoomOutleave(){
        	clearInterval(timer);
        }
		function zoomIn() {
			var zoom = isZoom("in");
			if(zoom){return;}
			currentOptions.ratio *= 1.002;
			if(currentOptions.ratio<1){
				currentOptions.ratio = 1.002;
			}
			setimg();
			var img = getDataURL();
			$(currentOptions.img_preview_wrap).attr("src", img);			
		}
        
		function zoomOut() {
			var zoom = isZoom("out");
			if(zoom){return;}
			currentOptions.ratio *= 0.998;
			if(currentOptions.ratio>1){
				currentOptions.ratio = 0.998;
			}
			setimg();
			var img = getDataURL();
			$(currentOptions.img_preview_wrap).attr("src", img);
		}
        function isZoom(fun){
          //上传图片的宽和高(图片的实际宽和高)
			var imgWidth = currentOptions.image.width;
			var imgHeight = currentOptions.image.height;
			//默认缩放后的宽和高(默认缩放指定高度(minheight))
			var w = imgWidth / imgHeight * currentOptions.minheight;
			var h = ele.clientHeight;
			//1.如果上传图片的实际尺寸为150*150不缩放
			var iszoom = (imgWidth == currentOptions.iwidth && imgHeight == currentOptions.iheight);
			if(iszoom){
				return true;
			}
			if(fun == "out"){//缩小
			  if(h <= currentOptions.minheight){
			  	return true;
			  }
			}
			if(fun =="in"){
				var maxheight = imgHeight * 1.618;
				if(h >= maxheight){
				  return true;
				}
			}
			return false;
        }
       
        
		function setimg() {
			//图上距离/实际距离=r
			var wRatio = ele.clientWidth / currentOptions.image.width,
			    hRatio = ele.clientHeight / currentOptions.image.height,
			    width = currentOptions.image.width * wRatio * currentOptions.ratio,
			    height = currentOptions.image.height * hRatio * currentOptions.ratio,
			    left = (el.clientWidth - width) / 2,
			    top = (currentOptions.minheight - height)/2;			
			//设置缩放后的图片的位置
			ele.setAttribute('style',
				'position: absolute; ' +
				'width: ' + width + 'px; ' +
				'height: ' + height+ 'px; ' +
				'left: ' + left + 'px; ' +
				'top: ' + top + 'px; ');
		}

		function getDataURL() {	
			//150*150不做缩放
			  if(currentOptions.image.width==currentOptions.iwidth &&currentOptions.image.height==currentOptions.iheight){
			  	return;
			  }
			var canvas = document.createElement("canvas");
			var context = canvas.getContext("2d");
			var img = currentOptions.image,			    
			    swidth = currentOptions.image.width,//原图w
			    sheight = currentOptions.image.height,//原图h			    
			    wRatio = ele.clientWidth / swidth,
			    hRatio = ele.clientHeight / sheight,
			    left = parseFloat(ele.style.left),//缩放后图片的向左的偏移量
			    top = parseFloat(ele.style.top),//缩放后图片的向上的偏移量
				dw = swidth * (currentOptions.minheight / sheight),//图片默认的宽
				dleft =((el.clientWidth - dw) / 2),//默认偏移量
			    sx = 0,//开始剪切的 x 坐标位置
			    sy = 0,//开始剪切的 y 坐标位置			    
			    width = swidth,
			    height = sheight;
			    var  sRatio = dw / swidth;
			    var  dRatio = currentOptions.minheight / sheight;
			   if(left<dleft &&left>0){
			   	    sx = (left -dleft) /wRatio;
			   	    sy = top / hRatio ;
			   	    width = swidth - sx;
			   	    height = sheight - sy;
			   }else if(left<0){
			   	    sx = left / wRatio;
			    	sy = top / hRatio;
			    	width = swidth - sx;
			    	height = sheight - sy;
			   }
			//设置画布的宽度
			canvas.width = swidth;
			canvas.height = sheight; 
			context.drawImage(img,0,0,swidth,sheight,sx,sy,width,height);
			var imageData = canvas.toDataURL('image/png');
			return imageData;
		}
		//onmousedown 事件会在鼠标按键被按下时发生
		function mousedown(e) {
			stopEvent(e);
			pauseEvent(e);
			var event = e || window.event;
			//1.记录下鼠标按下时的开始坐标
			currentOptions.state.mouseX = event.clientX;
			currentOptions.state.mouseY = event.clientY;
			//2.将标志位dragable设为true，标识鼠标开始移动
			currentOptions.state.dragable = true;
			attachEvent(ele, "mousemove", mousemove);
		} 
		//当鼠标指针在指定的元素中移动时，就会发生 mousemove 事件。
		function mousemove(e) {
			stopEvent(e);
			pauseEvent(e);
			var event = e || event;
			
			//1.判断startDrag为true（即鼠标开始移动）
			if(currentOptions.state.dragable) {
				//2.记录对应移动的距离
				var x = event.clientX - currentOptions.state.mouseX;
				var y = event.clientY - currentOptions.state.mouseY;
				
				//3.已偏移量
				var left = ele.style.left;
				var top = ele.style.top;
				
				//4.得到新的偏移量
				var offset_x = (parseInt(left) + x).toFixed(2),
				    offset_y = (parseInt(top) + y).toFixed(2);
				    
				//5.是否需要偏移   
				var zoom = isZoom("move");
				if(zoom){return;}
				
				//6.是否在运行拖拽的范围内
			    //6.1缩放图片的高度
				var h = ele.clientHeight;
				 //6.2图片的实际高度
				var height = currentOptions.image.height;
				var width = currentOptions.image.width;
				//6.3比例 = 图上距离 / 实际距离
				var hRatio = currentOptions.minheight / height;
				//6.4缩放的宽度
				var w = width * hRatio;
				//6.5 默认的偏移量
				 var  o_x = (el.clientWidth - w) / 2;
				//图片的宽度-(容器的宽度-默认偏移的宽度)=偏移量
				var img_w = ele.clientWidth;
				var wrap_w = el.clientWidth;
				var x_o = img_w - (wrap_w - o_x);
				//x>0 ：向右拖拽;x<0:向左拖拽;
				//向右拖拽的位移必须<=默认的偏移量;
				if((offset_x <= o_x && x>0)||(offset_x>= -x_o && x<0)){
					ele.style.left = offset_x + "px";
					var img = getDataURL();
					$(currentOptions.img_preview_wrap).attr("src", img);
				}
				//图片的高度-(容器的高度-偏移的高度)=偏移量
				//7.1图片的高度必须大于最小高度
				var mh = (h > currentOptions.minheight);
				//7.2 图片的高 -图片允许的最小高度 =图片向上拖的最大偏移量
				var top_y = ele.clientHeight - currentOptions.minheight;
				//y<0:往上拖;y>0:往下拖;				
				if(mh && ((offset_y >= -top_y && y < 0))||(offset_y <=0 && y > 0)){
					ele.style.top = offset_y + "px";
					var img = getDataURL();
					$(currentOptions.img_preview_wrap).attr("src", img);
				}
				//8.更新鼠标的位置
				currentOptions.state.mouseX = event.clientX;
				currentOptions.state.mouseY = event.clientY;
			}
		}

		function mouseup(e) {
			stopEvent(e);
			pauseEvent(e);
			var event = e || event;
			currentOptions.state.dragable = false;
			var img = getDataURL();
			$(currentOptions.img_preview_wrap).attr("src", img);
			detachEvent(ele, "mousemove", mousemove);
		}

		function zoomImage() {
			var evt = window.event || e;
			var delta = evt.detail ? evt.detail * (-120) : evt.wheelDelta;
			delta > -120 ? currentOptions.ratio *= 1.01 : currentOptions.ratio *= 0.999;
		}

		function stopEvent(e) {
			if(window.event) e.cancelBubble = true;
			else e.stopImmediatePropagation();
		}

		function attachEvent(node, event, cb) {
			if(node.attachEvent)
				node.attachEvent('on' + event, cb);
			else if(node.addEventListener)
				node.addEventListener(event, cb);
		}

		function detachEvent(node, event, cb) {
			if(node.detachEvent) {
				node.detachEvent('on' + event, cb);
			} else if(node.removeEventListener) {
				node.removeEventListener(event, cb);
			}
		}

		function pauseEvent(e) {
			if(e.stopPropagation) e.stopPropagation();
			if(e.preventDefault) e.preventDefault();
			e.cancelBubble = true;
			e.returnValue = false;
			return false;
		}
		
		return obj;
        
	}
})(jQuery, window, document);

$(function(){
	$(".userCenter .user-img").click(function(){
		$(".vicp-close").next().show();
		$(".vicp-step1").prev().hide();
		$(".vicp-step2").hide();
		$(".vicp-step1").show();
		$(".modi_dialog").find(".vicp-wrap").removeClass("maxwrap");
		$(".upload-dialog").show();
	});
	$(document).on("click",".modi_dialog .vicp-close,.vicp-step1 .vicp-operate a",function(){
		$(".modi_dialog").hide();
	});
	$(document).on("click",".vicp-operate a:first-child",function(){
    	$(".vicp-step2").hide();
    	$(".modi_dialog").find(".vicp-wrap").removeClass("maxwrap");
    	$(".vicp-close").next().hide();
		$(".vicp-step1").prev().show();
		$(".vicp-step1").show();
		
    });
	var options = {
			input_file_wrap: ".vicp-drop-area input", //文件上传file 必须
			img_origin_wrapper: ".photo-orgin-wrap", //必须
			img_origin_wrap: ".orgin-photo", //原图 必须
			img_preview_wrap: ".pre-photo", //图片预览的标识 必须
			zoomInButton: "#btnZoomIn", //放大 必须
			zoomOutButton: "#btnZoomOut", //缩小 必须
			drag_wrapper:".vicp-drop-area",
			minheight: 184, //缩放时图片的最小高度
		}
	 var upload = $(".vue-image-crop-upload").uploadFile(options);
	   
	 $(document).on("click",".vicp-operate .vicp-operate-btn",function(){
		var dataurl,filename;
		var pos = upload.getPosition();
		console.log(pos);
    	dataurl = $(".pre-photo").attr("src");
    	filename ="itour.png"
    	var file = dataURLtoFile (dataurl, filename);
    	console.log(file);
    	var form = new FormData();
    	form.append("file",file);
    	form.append("x",Math.abs(pos.x));
    	form.append("y",Math.abs(pos.y));
    	form.append("width",pos.width);
    	form.append("height",pos.height);
    	var url = "/account/updateAvatar";
    	postAjax(url, form, function (result) {
    		$(".modi_dialog").hide();
    		 window.location.reload();
	    }, {errorFunction:function(result){
	    	console.log(result);
	    },cache: false, async: false,processData:false,contentType:false});
	 });

	});
function dataURLtoFile (dataurl, filename) { 
    var arr = dataurl.split(','),
        mime = arr[0].match(/:(.*?);/)[1],
        bstr = atob(arr[1]),
        n = bstr.length,
        u8arr = new Uint8Array(n);
    while (n--) {
        u8arr[n] = bstr.charCodeAt(n);
    }
    return new File([u8arr], filename, { type: mime });
}




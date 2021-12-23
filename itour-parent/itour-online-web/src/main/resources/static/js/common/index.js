$(function(){
		$("#itour-banner .toolbar-subMenu-box").mouseenter(function(){
		$(this).find(".toolbar-subMenu").show();
	});
	$("#itour-banner .toolbar-subMenu-box").mouseleave(function(){
		$(this).find(".toolbar-subMenu").hide();
	});

	
	
	
	$(".tool_bar__fold-btn").click(function(){
		var fold = $(this).hasClass("fold");
		if(fold){
			 $(this).removeClass("fold");	
			 $(this).text("回到顶部");
			 $(this).attr("href","#article_setting_area");
		}else{
		  $(this).addClass("fold");	
		  $(this).text("文章设置");
		  $(this).attr("href","#top0");
		}
		
	});
	$(document).on("click",".item-input-wrap .i-ops .icon-yanjing",function(){
		$(this).addClass("icon-biyan");
		$(this).removeClass("icon-yanjing");
		$(this).closest(".item-input-wrap").find(".field").attr("type","text");
	});
	$(document).on("click",".item-input-wrap .i-ops .icon-biyan",function(){
		$(this).addClass("icon-yanjing");
		$(this).removeClass("icon-biyan");
		$(this).closest(".item-input-wrap").find(".field").attr("type","password");
	});
	
	$(".form-wrap input").focus(function(){
		$(this).closest(".form-item").addClass("z-focuse");
		var v = $(this).val();
		
	});
	$(".form-wrap input").blur(function(){
		$(this).closest(".form-item").removeClass("z-focuse");
	});
	
	$(".appConsole .el-submenu__title").click(function(){
		var hasclzz =$(this).closest("li").hasClass("is-opened");
		if(hasclzz){
			$(this).closest("li").removeClass("is-opened");
			$(this).closest("li").find(".el-menu.el-menu--inline").css("display","none");
		}else{
			$(this).closest("li").addClass("is-opened");
			$(this).closest("li").find(".el-menu.el-menu--inline").css("display","inline-block");
		}
		
	});
   $(document).on("click",".itour-favorites-btn,.back-btn-favoraties",function(){
	   var url="/travel/collectListPage?ajaxCmd=content";
	   var tid=$(this).attr("tid");
	   var data={"tid":tid};
	   postForm(url,  JSON.stringify(data), function (result) {
			$(".modal-wrapper.article_add_collect").html(result);
			$(".modal-wrapper.article_add_collect").show();
	      }, {"contentType":"application/json; charset=utf-8"}); 
   });
   $(document).on("click",".favlists-updateButton",function(){
	   var has = $(this).hasClass("button--primary");
	   if(has){
		   $(this).removeClass("button--primary");
	   }else{
		   $(this).addClass("button--primary");
	   }
   })
   //添加到收藏夹
   $(document).on("click",".favlists-addButton-sub",function(){
	  var arr = new Array();
	  var tid =$("#collect_tid").val();
	  $(".favlists-items .favlists-item button.button--primary").each(function(index,element){
		    var o ={};
			var fid = $(this).attr("fid");
			o.tid=tid;
			o.fid=fid;
			arr.push(o);
			});
	  var data={"arr":arr,"vo":{"tid":tid}};
	  postAjax("/travel/collectArticle", JSON.stringify(data), function (result) {
		  $(".article_add_collect").hide();
      }, {errorFunction:function(result){
      	alert(result.resultMessage);
      },cache: false, async: false});
	  
	  
   })
   $(document).on("click",".favlists-insertBtn-sub",function(){
	   var tid =$("#collect_tid").val();
	   var url=ctxPath+"/travel/favoratiesPage?tid="+tid;
	   $(".modal-wrapper.article_add_collect").load(url);
   });
   $(document).on("keyup",".favlists-descritionInput.input-wrapper input",function(){
	   var v = $(this).val();
	   var len = 18;
	   $("#input-tip-favor").removeClass("maxLength is-error");
	   if(v.length>0){
		   $(".modalButtonGroup .favlist-btn").prop("disabled",false);
		   $("#input-tip-favor").show();
		   if(v.length<=18){
			   var tip = "还可以输入"+ (len-v.length) +"个字"; 
			   $("#input-tip-favor").addClass("maxLength").text(tip);
		   }else{
			   var tip = "收藏标题已超过 "+(v.length-len)+"个字";
			   $("#input-tip-favor").addClass("maxLength is-error").text(tip);
			   $(".modalButtonGroup .favlist-btn").prop("disabled",true); 
		   }
	   }else{
		   $(".modalButtonGroup .favlist-btn").prop("disabled",true); 
		   $("#input-tip-favor").hide();
		   
	   }
   })
   $(document).on("keyup",".favlists-descritionInput.input-wrapper textarea",function(){
	   var v = $(this).val();
	   var len = 80;
	   $("#textarea-tip-favor").removeClass("maxLength is-error");
	   if(v.length>0){
		   $("#textarea-tip-favor").show();
		   if(v.length<=len){
			   var tip = "还可以输入"+ (len-v.length) +"个字"; 
			   $("#textarea-tip-favor").addClass("maxLength").text(tip);
		   }else{
			   var tip = "收藏描述已超过 "+(v.length-len)+"个字";
			   $("#textarea-tip-favor").addClass("maxLength is-error").text(tip);
		   }
	   }else{
		   $("#textarea-tip-favor").hide();
	   }
   })
   
   $(document).on("click",".modalButtonGroup .create-btn",function(){
	    // var formData = $(".favlists-content").serialize();
	     var formData = $.serializeObject($(".favlists-content"));
	     var data ={"vo":formData}
		  postAjax("/travel/insertFavorite", JSON.stringify(data), function (result) {
			  $(".back-btn-favoraties").trigger("click");
	      }, {errorFunction:function(result){
	      	alert(result.resultMessage);
	      },cache: false, async: false});
   });
   
})
	function showNav(){
	    		$(".richContent-inner h2").each(function(index,item){
	    		   $(this).prop("id","item-"+(index+1))
	    	    });
	    	     var i = 1;
	    	     var sm_parent="";
	    	    $(".richContent-inner h3").each(function(index,item){
		    		 var parent_id =  findParent($(this),"H2");
		    		 if(index==0){
		    		 	sm_parent = parent_id;
		    		 }
		    		 if(sm_parent!=parent_id){
	    		 		i=1;
	    		 		sm_parent =parent_id;
	    		 	 }
		    		 $(this).prop("id",parent_id+"-"+i);
		    		 i++;
	    	    });
	    	     sm_parent="";
	    	     i=1;
	    	     $(".richContent-inner h4").each(function(index,item){
		    		 var parent_id =  findParent($(this),"H3");
		    		 if(index==0){
		    		 	sm_parent = parent_id;
		    		 }
		    		 if(sm_parent!=parent_id){
	    		 		i=1;
	    		 		sm_parent =parent_id;
	    		 	 }
		    		 $(this).prop("id",parent_id+"-"+i);
		    		 i++;
	    	    });
	    	    sm_parent="";
	    	     i=1;
	    	     $(".richContent-inner h5").each(function(index,item){
		    		 var parent_id =  findParent($(this),"H4");
		    		 if(index==0){
		    		 	sm_parent = parent_id;
		    		 }
		    		 if(sm_parent!=parent_id){
	    		 		i=1;
	    		 		sm_parent =parent_id;
	    		 	 }
		    		 $(this).prop("id",parent_id+"-"+i);
		    		 i++;
	    	    });
	    	    sm_parent="";
	    	     i=1;
	    	     $(".richContent-inner h6").each(function(index,item){
		    		 var parent_id =  findParent($(this),"H5");
		    		 if(index==0){
		    		 	sm_parent = parent_id;
		    		 }
		    		 if(sm_parent!=parent_id){
	    		 		i=1;
	    		 		sm_parent =parent_id;
	    		 	 }
		    		 $(this).prop("id",parent_id+"-"+i);
		    		 i++;
	    	    });
	    	   
	    	   
	    	}
	    	function findParent(th,p_node){
	    		//获取当前元素的上一个兄弟节点
	    		var $this = $(th).prev();
	    		var parent_id = $($this).prop("id");
	    		//获取当前元素的上一个兄弟节点的标签名称
	    		var tName = $($this).prop("tagName");
	    		var reg = /^H\d{1}$/;
	    		if(tName!=p_node||!reg.test(tName)){
	    		  return findParent($this,p_node);
	    		}
	    		return parent_id;
	    		
	    	}
	    	function createNav(){
	    		var html = $(".article-nav-right_box");	    		 
	    	 $(".richContent-inner h2").each(function(){
	    	 	var target = $(this).prop("id");
	    	 	var title = $(this).text();
	    	 	$(".article-nav-list").append("<a class='list-group-item text-primary' href='#"+target+"' title='"+title.trim()+"'>"+title+"</a>");
	    	 	createChild("h3",target);
	    	 	
	    	 })
	    	}
	       function createChild(node,target){
	       	var html = $(".article-nav-list"); 
	       	var t = parseInt(node.substring(1))-1;//2
	       	//h3        item-1~item-N
	       	$(node+"[id^='"+target+"']").each(function(){
	       		var title = $(this).text();
	       		var target_ = $(this).prop("id");
	       		html.append("<a class='list-group-item nav"+t+"' href='#"+target_+"' title='"+title.trim()+"'>"+title+"</a>");
	       		var node_ ="h"+(t+2);
	       		if((t+1)<6){
	       		  return createChild(node_,target_);
	       		}
	       		
	       		
	       	});
	       	
	       
	       }

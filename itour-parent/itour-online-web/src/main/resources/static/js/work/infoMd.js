$(function(){
	var mdEditer;
		$(function(){
			 mdEditer=editormd("md-container",{
				autoHeight : false,
				path: ctxPath+"/md/lib/",
				watch:true//关闭实时预览
				
			});
			
		})
})
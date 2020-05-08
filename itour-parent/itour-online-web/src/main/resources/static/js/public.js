var postAjax = function(url,postData,successFunction,options){
			var returnFlag=false;
			var defaultOptions={
				type:"post",
				async:true,
				errorFunction:errorFunction,
				successArguments: "",
				errorArguments: "",
				contentType:"application/json; charset=utf-8",/*传入数据类型*/
				dataType:"json"/* 返回的数据类型*/
			};			
			var currentOptions = $.extend(defaultOptions, options);//https://www.runoob.com/jquery/misc-extend.html
			$.ajax({
				type:currentOptions.type,
				url:url,
				data:postData,
				async:currentOptions.async,
				contentType:currentOptions.contentType,
				dataTyp:currentOptions.dataType,
				beforeSend:function(){
					//loading();
				},
				success:function(resultData){
					if(resultData&&isSuccess(resultData.resultCode)){
						if(defaultOptions&&defaultOptions.successArguments){
							successFunction(resultData,defaultOptions.successArguments);
						}else{
							successFunction(resultData);
							
						}
						returnFlag = true;
					}else{
						defaultOptions.errorFunction(resultData);
						returnFlag = false;
					}
					
					
				},
				complete:function(){
					deleteLoading();
				},
				error:function(result){
					deleteLoading();
				},
				
				
			});
			return returnFlag;
		}





		function loading(){
			 $('body').append('<div id="aLoad"><div id="aLoad-overlay"></div><div class="cssload-loader"><div class="cssload-side"></div>' +
		                '<div class="cssload-side"></div><div class="cssload-side"></div>' +
		                '<div class="cssload-side"></div><div class="cssload-side"></div>' +
		                '<div class="cssload-side"></div><div class="cssload-side"></div>' +
		                '<div class="cssload-side"></div></div>');
		}
		function deleteLoading(){
			 $("#aLoad").remove();
		}
		function isSuccess(resultCode){
		return ("10"===resultCode)
		}
		function errorFunction(resultData) {
		showMessage(resultData);
		}
		
		function showMessage(resultData) {
		alert(resultData.description);
		}
		
		
		function filterSpecial(value) {
		    return value.replace(/[']/g, '')
		}
		$.serializeObject = function (form) {
		    var o = {};
		    $.each(form.serializeArray(), function (index) {
		        if (o[this['name']]) {
		            o[this['name']] = filterSpecial(o[this['name']] + "," + this['value'])
		        } else {
		            o[this['name']] = filterSpecial(this['value'])
		        }
		    });
		    return o
		};
		
/**
 * 正则表达式
 * 所有的通用的正则表达
 */		
var test_email = function(email){
	/**
	 * 邮箱格式:用户名称@域名
	 * 用户名由数字、字母、下划线、中划线组成至少两位;
	 * 域名：只能为.com ,.cn ,.net,.com.cn,.edu,.gov,.org
	 */
	var email_reg =/^[a-zA-Z0-9_\-\.]{2,}\@\w+\.(com|cn|net|com\.cn|edu|gov|org)$/;
	return email_reg.test(email);
}
var test_nickName = function(nickName){
	/**
	 * 用户名:仅支持数字、字母、下划线(3-18位)、中划线、不能以数字开头
	 */
	var nickName_reg=/^([a-zA-Z])([a-zA-Z0-9_\-]{2,18})$/;
	return nickName_reg.test(nickName);
}
var test_pwd = function(pwd){
/**
 * 密码：必须（6-20位）字符
 */	
	var pwd_reg = /^[a-zA-z0-9_\-\.\*\#\@\?\%\!]{6,20}$/;
	return pwd_reg.test(pwd);
}
var test_phone = function(phone){
	/**
	 * 电话号码:长度必须为11位,必须以1开头,第二位必须为3、4、5、6、7、8、9中的一位;
	 */
	var phone_reg = /^1[3456789]\d{9}$/;
	return phone_reg.test(phone);
}
var equalTopwd = function(pwd,topPwd){
	return pwd == topPwd;
}


function showCity(){
	var data={};
    postAjax("/travel/getCityList", JSON.stringify(data), function (result) {
    	console.log(result);
    	var str="<option value=''>请选择</option>";
    	$.each(result.returnResult.result,function(key,value){
        	$.each(value,function(k,v){
                console.info("key: " + k );
                str+='<optgroup label=\"'+k+'\">'
                $.each(v,function(kk,vv){
                	str+='<option value=\"'+vv.regionCode+'\">'+vv.regionName+'</option>';
                    console.info( "Value: " + vv.regionName);

            })
            str+="</optgroup>";
        })
    })
    $("#city").html(str);
    }, {errorFunction:function(result){},cache: false, async: false});
}
package com.itour.common.file;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.itour.common.vo.AccountVo;
import com.itour.util.SessionUtil;

import cn.hutool.core.lang.UUID;

public class MultipartFileUpload {
public static void main(String[] args) {
	//1.设置上传路径
	//2.管理文件
	//3.上传图片
}
@Value("${fileupload.path}") static String pathname;
public static JSONObject fileUpload(MultipartFile file)  {
	JSONObject res = new JSONObject();
	try {
		
		//1.设置上传路径
		//为每个用户分配一个文件夹
		AccountVo sessionUser = SessionUtil.getSessionUser();
		if(null!=sessionUser) {
			pathname+=File.separator+sessionUser.getuId();
		}else {
			pathname+=File.separator+"public";
		}
		//2.文件夹按年份创建
		Calendar instance = Calendar.getInstance();
		int y=instance.get(Calendar.YEAR);
		String year = "y"+y;
		DateFormat df = new SimpleDateFormat("MMM",Locale.ENGLISH);
		String month = df.format(new Date());
		String path = pathname+File.separator+year+File.separator+month;
	    File realPath = new File(path);    
	    boolean exists = realPath.exists();
	    if(!exists) {
	    	realPath.mkdirs();
	    }
	    String fileName = UUID.randomUUID().toString().replaceAll("-", "")+".jpg";
		File newFile = new File(realPath, fileName);
		file.transferTo(newFile);
		
	    res.put("url",newFile.getAbsolutePath());
	    res.put("success", 1);
	    res.put("message", "upload success!");
	} catch (Exception e) {
		// TODO: handle exception
		 e.printStackTrace();
		 res.put("url","");
		 res.put("success", 2);
		 res.put("message", "upload fail!");
	}
	return res;
	
}

}

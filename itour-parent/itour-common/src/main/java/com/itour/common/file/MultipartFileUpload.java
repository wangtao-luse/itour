package com.itour.common.file;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;

import cn.hutool.core.lang.UUID;

public class MultipartFileUpload {
public static void main(String[] args) {
	//1.设置上传路径
	//2.管理文件
	//3.上传图片
}
public static JSONObject fileUpload(MultipartFile file)  {
	JSONObject res = new JSONObject();
	try {
		//1.设置上传路径
		String pathname="D:\\temp\\upload";
		//2.文件夹按年份创建
		Calendar instance = Calendar.getInstance();
		int y=instance.get(Calendar.YEAR);
		String year = "y"+y;
		System.out.println(year);	
		DateFormat df = new SimpleDateFormat("MMM",Locale.ENGLISH);
		String month = df.format(new Date());
	    System.out.println(month);
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

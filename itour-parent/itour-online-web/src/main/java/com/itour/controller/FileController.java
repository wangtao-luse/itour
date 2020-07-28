package com.itour.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.itour.common.resp.ResponseMessage;
import com.itour.util.FileUtil;

@Controller
public class FileController {

public ResponseMessage uploadPhoto(MultipartFile file,HttpServletRequest request) {
	ResponseMessage responseMessage = ResponseMessage.getSucess();
	JSONObject jsonObject = new JSONObject();
	String compress = request.getParameter("c");
	String dict_prefix = request.getParameter("d");
	jsonObject.put("fileName", file.getOriginalFilename());
	//从配置中取或者走数据库
	jsonObject.put("savePath", "");
	//是否压缩
	jsonObject.put("compress", compress);
	jsonObject.put("dict_prefix","");
	jsonObject.put("resizeWidth", "1080");
	try {
		FileUtil.uploadPhoto(file.getInputStream(), jsonObject );
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return responseMessage;
}

}

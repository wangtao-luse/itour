package com.itour.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.itour.common.file.MultipartFileUpload;
import com.itour.common.resp.ResponseMessage;
import com.itour.util.FileUtil;

@Controller
@RequestMapping(value="/upload",produces = {"application/json;charset=UTF-8"})
public class FileController {
	@RequestMapping("/multipartFileUpload")
    @ResponseBody
public JSONObject multipartFileUpload(@RequestParam(value = "editormd-image-file", required = true) MultipartFile fileMultipartFile,HttpServletRequest request) {
	JSONObject fileUpload = MultipartFileUpload.fileUpload(fileMultipartFile);
	return fileUpload;
	
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
/**
   * 上传图片
 * @param file
 * @param request
 * @return
 */
@RequestMapping("/uploadPhoto")
public ResponseMessage uploadPhoto(MultipartFile file,HttpServletRequest request) {
	ResponseMessage responseMessage = ResponseMessage.getSucess();
	JSONObject jsonObject = new JSONObject();
	String compress = request.getParameter("c");
	String dict_prefix = request.getParameter("d");
	//文件名
	jsonObject.put("fileName", file.getOriginalFilename());
	//从配置中取或者走数据库
	jsonObject.put("savePath", "");
	//是否压缩
	jsonObject.put("compress", compress);
	//目录前缀
	jsonObject.put("dict_prefix",dict_prefix);
	//压缩参数
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

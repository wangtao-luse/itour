package com.itour.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
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
	@Value("${upload.resourceHandler}")
    private String resourceHandler;//请求 url 中的资源映射，不推荐写死在代码中，最好提供可配置，如 /uploadFiles/**
 
    @Value("${upload.location}")
    private String uploadFileLocation;//上传文件保存的本地目录，使用@Value获取全局配置文件中配置的属性值，如 E:/wmx/uploadFiles/	
	/**
	 * markdown图片上传实现
	 * @param fileMultipartFile
	 * @param request
	 * @return
	 */
	@RequestMapping("/multipartFileUpload")
    @ResponseBody
public JSONObject multipartFileUpload(@RequestParam(value = "editormd-image-file", required = true) MultipartFile fileMultipartFile,HttpServletRequest request) {
	JSONObject fileUpload = MultipartFileUpload.fileUpload(fileMultipartFile);
	return fileUpload;
	
}
	/**
	 * 单文件上传
	 * @param file
	 * @param request
	 * @return
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	@RequestMapping("/fileUpload")
    @ResponseBody
public String fileUpload(MultipartFile file,HttpServletRequest request) throws IllegalStateException, IOException {
		//https://www.cnblogs.com/zhaoyan001/p/10953711.html
		  String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
	        String fileName = file.getOriginalFilename();
	        String fileServerPath = basePath + resourceHandler.substring(0, resourceHandler.lastIndexOf("/") + 1) + fileName;
	        File realPath = new File(uploadFileLocation);
	        boolean exists = realPath.exists();
		    if(!exists) {
		    	realPath.mkdirs();
		    }
	        File saveFile = new File(uploadFileLocation, fileName);
	        file.transferTo(saveFile);
	        String absolutePath = saveFile.getAbsolutePath();
	    JSONObject fileUpload = MultipartFileUpload.fileUpload(file);
	return "<a href='" + fileServerPath + "'>" + fileServerPath + "</a>";
	
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

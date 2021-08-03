package com.itour.controller;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.itour.common.file.FileUploadHelper;
import com.itour.common.resp.ResponseMessage;
import com.itour.common.vo.AccountVo;
import com.itour.util.FileUtil;
import com.itour.util.SessionUtil;

import cn.hutool.core.lang.UUID;

@Controller
@RequestMapping(value="/upload",produces = {"application/json;charset=UTF-8"})
public class FileController {
	//请求 url 中的资源映射，不推荐写死在代码中，最好提供可配置，如 /uploadFiles/**
	@Value("${upload.resourceHandler}")
    private String resourceHandler;
   ////上传文件保存的本地目录，使用@Value获取全局配置文件中配置的属性值，如 E:/wmx/uploadFiles/	
    @Value("${upload.location}")
    private String uploadFileLocation;
	/**
	 * markdown图片上传实现
	 * @param fileMultipartFile
	 * @param request
	 * @return
	 */
	@RequestMapping("/multipartFileUpload")
    @ResponseBody
public JSONObject multipartFileUpload(@RequestParam(value = "editormd-image-file", required = true) MultipartFile file,HttpServletRequest request) {
		// https://www.cnblogs.com/zhaoyan001/p/10953711.html
		JSONObject result = new JSONObject();
		try {
			// http://localhost:9093/itour
			String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
					+ request.getContextPath();
			//获取上传源文件名称
			String originName = file.getOriginalFilename();// IMG_3903.JPG
			//获取文件后缀
			String suffix =  originName.substring(originName.lastIndexOf("."));
			boolean checkFileSuffix = FileUploadHelper.checkFileSuffix(suffix);
			if(!checkFileSuffix) {
				  result.put("url","");
				  result.put("success", 2);
				  result.put("message", "仅支持.jpg, .jpeg, .gif, .png, .bmp格式图片");
				  return result;
			}
			//重新生成文件名
			String fileName = UUID.randomUUID().toString().replaceAll("-", "")+suffix;
			// 文件访问路径
			// http://localhost:9093/itour/upload/189ec6990e554471b15631816d371066.JPG
			String fileServerPath = basePath + resourceHandler.substring(0, resourceHandler.lastIndexOf("/"))
					+ FileUploadHelper.getPath(FileUploadHelper.IMG_PREFIX_ONLINE) + File.separator + fileName;
			//文件保存的路径
			String savePath = uploadFileLocation + File.separator + FileUploadHelper.getPath(FileUploadHelper.IMG_PREFIX_ONLINE);
			//如果目录不能存在创建目录
			File realPath = new File(savePath);
			boolean exists = realPath.exists();
			if (!exists) {
				realPath.mkdirs();
			}
			//写入文件到服务器指定目录
			File saveFile = new File(savePath, fileName);
			file.transferTo(saveFile);
			  result.put("url",fileServerPath);
			  result.put("success", 1);
			  result.put("message", "upload success!");
			  return  result;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			  e.printStackTrace();
			  result.put("url","");
			  result.put("success", 2);
			  result.put("message", "upload fail!");
			return result;
		} 
	
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
	public ResponseMessage fileUpload(MultipartFile file, HttpServletRequest request) {
		// https://www.cnblogs.com/zhaoyan001/p/10953711.html
		ResponseMessage responseMessage = ResponseMessage.getSucess();
		try {
			responseMessage =FileUploadHelper.upload(file, uploadFileLocation, resourceHandler, request,FileUploadHelper.IMG_PREFIX_ONLINE);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseMessage.getFailed();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseMessage.getFailed();
		}

		return responseMessage;

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

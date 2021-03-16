package com.itour.controller;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

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
import com.itour.common.vo.AccountVo;
import com.itour.exception.BaseException;
import com.itour.util.FileUtil;
import com.itour.util.SessionUtil;

import cn.hutool.core.lang.UUID;

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
	public ResponseMessage fileUpload(MultipartFile file, HttpServletRequest request) {
		// https://www.cnblogs.com/zhaoyan001/p/10953711.html
		ResponseMessage responseMessage = ResponseMessage.getSucess();
		try {
			// http://localhost:9093/itour
			String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
					+ request.getContextPath();
			//获取上传源文件名称
			String originName = file.getOriginalFilename();// IMG_3903.JPG
			//获取文件后缀
			String suffix =  originName.substring(originName.lastIndexOf("."));
			boolean checkFileSuffix = checkFileSuffix(suffix);
			if(!checkFileSuffix) {
				return ResponseMessage.getFailed("仅支持.jpg, .jpeg, .gif, .png, .bmp格式图片");
			}
			//重新生成文件名
			String fileName = UUID.randomUUID().toString().replaceAll("-", "")+suffix;
			// 文件访问路径
			// http://localhost:9093/itour/upload/189ec6990e554471b15631816d371066.JPG
			String fileServerPath = basePath + resourceHandler.substring(0, resourceHandler.lastIndexOf("/"))
					+ getPath() + File.separator + fileName;
			//文件保存的路径
			String savePath = uploadFileLocation + File.separator + getPath();
			//如果目录不能存在创建目录
			File realPath = new File(savePath);
			boolean exists = realPath.exists();
			if (!exists) {
				realPath.mkdirs();
			}
			//写入文件到服务器指定目录
			File saveFile = new File(savePath, fileName);
			file.transferTo(saveFile);
			responseMessage.setReturnResult(fileServerPath);
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
	 * 文件上传文件管理(每个用户创建一个文件夹)
	 * 登录情况下的文件上传，返回 /用户唯一号/y+年份+月份英文缩写;
	 * 没有登录的情况下的文件上传 返回/public/y+年份+/月份英文缩写
	 * @return  /10000/y2021/Mar/ or /public/y2021/Mar/
	 */
private String getPath() {
	String path="";
	AccountVo sessionUser = SessionUtil.getSessionUser();
	if(null!=sessionUser) {
		path = File.separator+sessionUser.getuId();
	}else {
		path = File.separator+"public";
	}
	Calendar instance = Calendar.getInstance();
	int y=instance.get(Calendar.YEAR);
	String year = "y"+y;
	DateFormat df = new SimpleDateFormat("MMM",Locale.ENGLISH);
	String month = df.format(new Date());
	path+= File.separator+year+File.separator+month;
	return path;
}
/**
 * 文件后缀限制
 * @param suffix
 * @return
 */
private boolean checkFileSuffix(String suffix) {
   boolean b=true;
   String s = suffix.toLowerCase();
   if(".jpg.jpeg.gif.png.bmp".indexOf(s)==-1) {
	  b=false;
   }
   return b;
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

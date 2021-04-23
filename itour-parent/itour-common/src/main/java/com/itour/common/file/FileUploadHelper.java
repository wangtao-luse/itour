package com.itour.common.file;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.itour.common.resp.ResponseMessage;
import com.itour.common.vo.AccountVo;
import com.itour.constant.ConstantFile;
import com.itour.util.SessionUtil;


public class FileUploadHelper {
/**
 * 图片上传
 * 1.获取前台上传的文件
 * 2.保存上传的文件
 *   a.检查文件
 *   b.文件管理
 *   c.写文件
 *   d.返回访问路径(映射)
 * @throws IOException 
 * @throws IllegalStateException 
 */
public static ResponseMessage upload(MultipartFile file,String uploadFileLocation,String resourceHandler,HttpServletRequest request) throws IllegalStateException, IOException {
	ResponseMessage responseMessage = ResponseMessage.getSucess();
	//1.1获取上传源文件
	String originName = file.getOriginalFilename();// IMG_3903.JPG	
	//1.2获取上传文件的后缀
	String suffix =  originName.substring(originName.lastIndexOf("."));
	//2.1检查后缀
	boolean checkSuffix = checkFileSuffix(suffix);
	if(!checkSuffix) {
		return ResponseMessage.getFailed(ConstantFile.ERROR_SUFFIX_IMG);
	}
	//2.2文件管理
	String savePath =uploadFileLocation+File.separator+getPath();
	//如果目录不能存在创建目录
	File realPath = new File(savePath);
	boolean exists = realPath.exists();
	if (!exists) {
		realPath.mkdirs();
	}
	//写入文件到服务器指定目录
	String fileName = getFileName(suffix);
	File saveFile = new File(savePath,fileName);
	file.transferTo(saveFile);
	// http://localhost:9093/itour
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+ request.getContextPath();
	// http://localhost:9093/itour/upload/189ec6990e554471b15631816d371066.JPG
	String fileServerPath = basePath + resourceHandler.substring(0, resourceHandler.lastIndexOf("/"))+ getPath() + File.separator + fileName;
	responseMessage.setReturnResult(fileServerPath);
	return responseMessage;
}
/**
 * 文件后缀限制
 * @param suffix
 * @return
 */
public static boolean checkFileSuffix(String suffix) {
   boolean b=true;
   String s = suffix.toLowerCase();
   if(".jpg.jpeg.gif.png.bmp".indexOf(s)==-1) {
	  b=false;
   }
   return b;
}
/**
 * 文件上传文件管理(每个用户创建一个文件夹)
 * 登录情况下的文件上传，返回 /用户唯一号/y+年份+月份英文缩写;
 * 没有登录的情况下的文件上传 返回/public/y+年份+/月份英文缩写
 * @return  /10000/y2021/Mar or /public/y2021/Mar
 */
public  static String getPath() {
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
private static String getFileName(String suffix) {
	return  UUID.randomUUID().toString().replaceAll("-", "")+suffix;
}

}

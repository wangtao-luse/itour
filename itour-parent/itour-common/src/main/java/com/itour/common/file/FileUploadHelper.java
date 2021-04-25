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
//https://www.bejson.com/convert/filesize/
public static final long FILESIZE_DEFAULT = 2097152;//2MB
public static final long FILESIZE_3MB = 3145728;//3MB
public static final long FILESIZE_10MB = 10485760;//10MB
public static final long FILESIZE_100MB = 104857600;//100MB
public static final long FILESIZE_300MB = 314572800;//300MB
public static final long FILESIZE_500MB = 524288000;//500MB
public static final long FILESIZE_600MB = 629145600;//600MB
public static final long FILESIZE_1024MB = 1073741824;//1024MB
/**
 * 图片上传
 * 1.获取前台上传的文件
 * 2.保存上传的文件
 *   a.检查文件
 *   b.文件管理
 *   c.写文件
 *   d.返回访问路径(映射)
 */
	/**
	 * 文件上传
	 * @param file   长传的file
	 * @param uploadFileLocation 文件保存的目标地址
	 * @param resourceHandler   文件访问的地址映射目录
	 * @param request      请求对象
	 * @param fileSize      上传文件的最大大小
	 * @return  文件的访问地址 
	 * @throws IllegalStateException
	 * @throws IOException
	 */
public static ResponseMessage upload(MultipartFile file,String uploadFileLocation,String resourceHandler,HttpServletRequest request,long fileSize) throws IllegalStateException, IOException {
	ResponseMessage responseMessage = uploadFile(file, uploadFileLocation, resourceHandler, request, fileSize);
	return responseMessage;
}
/**
 * 文件上传  默认文件允许10MB
 * @param file   长传的file
 * @param uploadFileLocation 文件保存的目标地址
 * @param resourceHandler   文件访问的地址映射目录
 * @param request      请求对象
 * @return  文件的访问地址 
 * @throws IllegalStateException
 * @throws IOException
 */
public static ResponseMessage upload(MultipartFile file,String uploadFileLocation,String resourceHandler,HttpServletRequest request) throws IllegalStateException, IOException {
	ResponseMessage responseMessage = uploadFile(file, uploadFileLocation, resourceHandler, request,FileUploadHelper.FILESIZE_10MB );
	return responseMessage;
}
/**
 * 文件上传
 * @param file
 * @param uploadFileLocation
 * @param resourceHandler
 * @param request
 * @param fileSize
 * @return
 * @throws IOException
 */
private static ResponseMessage uploadFile(MultipartFile file, String uploadFileLocation, String resourceHandler,
		HttpServletRequest request, long fileSize) throws IOException {
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
	boolean checkLen = checkFileLen(file.getSize(), fileSize);
	if(checkLen) {
		return ResponseMessage.getFailed(ConstantFile.ERROR_FILE_LENGTH);
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
 * @return 合法的后缀返回 true; 非法的后缀返回false
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
 * 
 * @param file 文件
 * @param fileSize bytes 文件大小限制
 * @return 在限制以内 true,超出限制返回false
 */
public static boolean checkFileLen(long len,long  fileSize) {
	//https://www.bejson.com/convert/filesize/
	/**存储容量:bit Bytes KB MB GB TB
	 * 1Bytes = 8bit
	 * 1KB = 1024Bytes
	 * 1MB = 1024KB
	 * 1GB = 1024MB
	 * 1TB = 1024MB
	 *  */
	
	boolean b = false;
	if(len>fileSize) {
	  b=true;	
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
public static void main(String[] args) {
	//https://www.cnblogs.com/firstdream/p/8710762.html
	/**存储容量:bit Bytes KB MB GB TB
	 * 1Bytes = 8bit
	 * 1KB = 1024Bytes
	 * 1MB = 1024KB
	 * 1GB = 1024MB
	 * 1TB = 1024MB
	 *  */
	  
	String path = "D:\\temp\\test\\上海影视城.docx";
	File file = new File(path);
	long length = file.length();
	System.out.println(length);
	System.out.println("len:"+(length/1024)+"KB");
	
}
public void test(long ...value) {
	
}
}

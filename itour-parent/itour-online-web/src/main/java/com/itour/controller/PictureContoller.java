package com.itour.controller;

import java.io.File;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.itour.common.file.FileUploadHelper;
import com.itour.common.image.ThumbnailsHelper;
import com.itour.common.resp.ResponseMessage;
import com.itour.constant.Constant;
import com.itour.util.StringHelper;

@Controller
public class PictureContoller {
	@Value("${upload.resourceHandler}")
    private String resourceHandler;
	
	@Value("${upload.location}")
    private String uploadFileLocation;
@RequestMapping("/zoom")
public String zoomPage(ModelMap model) {
	return "/picture/picture1";
}
@RequestMapping("/uploadPicture")
@ResponseBody
public ResponseMessage uploadPicture(MultipartFile file,HttpServletRequest request) {
	ResponseMessage responseMessage = ResponseMessage.getSucess();
	try {
		String originName = file.getOriginalFilename();// IMG_3903.JPG	
	    //1.2获取上传文件的后缀
	    String suffix =  originName.substring(originName.lastIndexOf("."));
		responseMessage = FileUploadHelper.upload(file, uploadFileLocation, resourceHandler, request, FileUploadHelper.FILESIZE_1024MB,FileUploadHelper.IMG_PREFIX_ONLINE);
		Map<String, Object> returnResult = responseMessage.getReturnResult();
		String path =(String) returnResult.get(Constant.COMMON_KEY_RESULT);
		String fileName = FileUploadHelper.getFileName(suffix);
		String savePath =uploadFileLocation+File.separator+FileUploadHelper.getPath(FileUploadHelper.IMG_PREFIX_ONLINE)+File.separator+fileName;
		ThumbnailsHelper.thumbnail(path, savePath, ThumbnailsHelper.SCALE_DEFUALT);
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+ request.getContextPath();
		String fileServerPath = basePath + resourceHandler.substring(0, resourceHandler.lastIndexOf("/"))+ FileUploadHelper.getPath(FileUploadHelper.IMG_PREFIX_ONLINE) + File.separator + fileName;
		responseMessage.add("loc", fileServerPath);
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		responseMessage = ResponseMessage.getFailed();
	}
	return responseMessage;
}
}

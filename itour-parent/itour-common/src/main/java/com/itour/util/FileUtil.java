package com.itour.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import com.alibaba.fastjson.JSONObject;
import com.itour.constant.ConstantFile;
import com.itour.exception.BaseException;

public class FileUtil {
/**
 * 保存文件 
 * @param inputStream
 * @param fileName
 * @param savePath
 * @return
 */
	public static boolean saveFile(InputStream inputStream, String fileName, String savePath) {
		boolean b = true;
		FileOutputStream fos = null;
		try {
			// 1.写入文件的地址
			File file = new File(savePath + File.separator + fileName);
			//1.1如果目录不存在创建该目录
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			fos = new FileOutputStream(file);
			// 2.读取数据(通过字节数组来读取数据)
			byte[] buf = new byte[1024];// 准备一个容器来装数据
			int len = 0;// 表示每次接收读取的字节数据
			// 3.循环读取数据输出
			while ((len = inputStream.read(buf)) != -1) {
				fos.write(buf,0,len);
				
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			b = false;
		} finally {
			try {
				if (fos != null) {
					fos.close();
				}
				if (inputStream != null) {
					inputStream.close();
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				b = false;
			}

		}
		return b;

	}
/**
 * 上传图片
 * @param inputStream
 * @param jsonObject
 * @return
 */
public static boolean uploadPhoto(InputStream inputStream,JSONObject jsonObject) {
	//文件名称
	String fileName = jsonObject.getString("fileName");
	//检查图片后缀
	checkedSuffix(fileName, ConstantFile.FUNCTION_IMG);
	//保存路径
	String savePath = jsonObject.getString("savePath");
	//是否压缩
	boolean compress = jsonObject.getBoolean("compress");
	//文件目录前缀
	String dict_prefix = jsonObject.getString("dict_prefix");
	//
	Integer resizeWidth = jsonObject.getInteger("resizeWidth");
	//最终保存文件的目录
	String saveFileDictionary = getSaveFileDictionary(savePath, dict_prefix);
	//生成文件名
	String newfileName = getUUIDFileName(fileName);
	//压缩图片
	if(compress) {
	 ByteArrayOutputStream baos = (ByteArrayOutputStream) ImageUtils.resize(inputStream, resizeWidth);
     inputStream = new ByteArrayInputStream(baos.toByteArray());
	}
	boolean saveFile = saveFile(inputStream, newfileName, saveFileDictionary);
	
	return saveFile;
}
/**
 * 生成新的文件名
 * @param fileName
 * @return
 */
public static String getUUIDFileName(String fileName){
	// 将文件名的前面部分进行截取：xx.jpg   --->   .jpg
	int idx = fileName.lastIndexOf(".");
	String extention = fileName.substring(idx);
	String uuidFileName = UUID.randomUUID().toString().replace("-", "")+extention;
	return uuidFileName;
}
/**
 * 得到最终目录
 * @param savepath
 * @param prefix
 * @return
 */
public static String getSaveFileDictionary(String savepath,String prefix){
	
	String[] split = prefix.split(",");
	StringBuffer path = new StringBuffer(savepath);
	for (String p : split) {
		path.append(File.separator+p);
	}
	return path.toString();
}
/**
 * 校验文件后缀
 * @param fileName
 * @return
 */
public static boolean checkedSuffix(String fileName,String function){
	String suffix = fileName.toLowerCase();
	String regex="";
	boolean matches=false;
	if(ConstantFile.FUNCTION_IMG.equals(function)) {
		  regex ="(.)+(.png|.jpg|.jpeg)";
		  matches = suffix.matches(regex);
		  if(!matches) {
				throw new BaseException(ConstantFile.ERROR_SUFFIX_IMG);
		 }
	}else if(ConstantFile.FUNCTION_PDF.equals(function)) {
		  regex ="(.)+(.pdf)";
		  matches = suffix.matches(regex);
		  if(!matches) {
				throw new BaseException(ConstantFile.ERROR_SUFFIX_PDF);
		 }
	}else if(ConstantFile.FUNCTION_DOC.equals(function)) {
		  regex ="(.)+(.doc|docx)";
		  matches = suffix.matches(regex);
		 if(!matches) {
				throw new BaseException(ConstantFile.ERROR_SUFFIX_DOC);
		}
	}else if(ConstantFile.FUNCTION_PDF_DOC.equals(function)) {
		  regex ="(.)+(.pdf|.doc|docx)";
		  matches = suffix.matches(regex);
		 if(!matches) {
				throw new BaseException(ConstantFile.ERROR_SUFFIX_PDF_DOC);
		}
	}
	
	
	return matches;
}
/**
 * 上次pdf文件
 * @param inputStream
 * @param jsonObject
 * @return
 */
public static boolean uploadPdf(InputStream inputStream,JSONObject jsonObject) {
	//文件名称
		String fileName = jsonObject.getString("fileName");
		//检查图片后缀
		checkedSuffix(fileName, ConstantFile.FUNCTION_PDF);
		//保存路径
		String savePath = jsonObject.getString("savePath");
		//是否压缩
		boolean compress = jsonObject.getBoolean("compress");
		//文件目录前缀
		String dict_prefix = jsonObject.getString("dict_prefix");
		//
		Integer resizeWidth = jsonObject.getInteger("resizeWidth");
		//最终保存文件的目录
		String saveFileDictionary = getSaveFileDictionary(savePath, dict_prefix);
		//生成文件名
		String newfileName = getUUIDFileName(fileName);
		//压缩图片
		if(compress) {
		 ByteArrayOutputStream baos = (ByteArrayOutputStream) ImageUtils.resize(inputStream, resizeWidth);
	     inputStream = new ByteArrayInputStream(baos.toByteArray());
		}
	boolean saveFile = saveFile(inputStream, newfileName, saveFileDictionary);
	return saveFile;
}
/**
 * 上次文本文档
 * @param inputStream
 * @param jsonObject
 * @return
 */
public static boolean uploadDoc(InputStream inputStream,JSONObject jsonObject) {
	   //文件名称
		String fileName = jsonObject.getString("fileName");
		//检查图片后缀
		checkedSuffix(fileName, ConstantFile.FUNCTION_DOC);
		//保存路径
		String savePath = jsonObject.getString("savePath");
		//是否压缩
		boolean compress = jsonObject.getBoolean("compress");
		//文件目录前缀
		String dict_prefix = jsonObject.getString("dict_prefix");
		//
		Integer resizeWidth = jsonObject.getInteger("resizeWidth");
		//最终保存文件的目录
		String saveFileDictionary = getSaveFileDictionary(savePath, dict_prefix);
		//生成文件名
		String newfileName = getUUIDFileName(fileName);
		//压缩图片
		if(compress) {
		 ByteArrayOutputStream baos = (ByteArrayOutputStream) ImageUtils.resize(inputStream, resizeWidth);
	     inputStream = new ByteArrayInputStream(baos.toByteArray());
		}
	boolean saveFile = saveFile(inputStream, newfileName, saveFileDictionary);
	return saveFile;
}
/**
 * 上次pdf或文本文档
 * @param inputStream
 * @param jsonObject
 * @return
 */
public static boolean uploadPdfAndDoc(InputStream inputStream,JSONObject jsonObject) {
	//文件名称
		String fileName = jsonObject.getString("fileName");
		//检查图片后缀
		checkedSuffix(fileName, ConstantFile.FUNCTION_PDF_DOC);
		//保存路径
		String savePath = jsonObject.getString("savePath");
		//是否压缩
		boolean compress = jsonObject.getBoolean("compress");
		//文件目录前缀
		String dict_prefix = jsonObject.getString("dict_prefix");
		//
		Integer resizeWidth = jsonObject.getInteger("resizeWidth");
		//最终保存文件的目录
		String saveFileDictionary = getSaveFileDictionary(savePath, dict_prefix);
		//生成文件名
		String newfileName = getUUIDFileName(fileName);
		//压缩图片
		if(compress) {
		 ByteArrayOutputStream baos = (ByteArrayOutputStream) ImageUtils.resize(inputStream, resizeWidth);
	     inputStream = new ByteArrayInputStream(baos.toByteArray());
		}
	boolean saveFile = saveFile(inputStream, newfileName, saveFileDictionary);
	return saveFile;
}
public static void main(String[] args) throws IOException {
	//保存文件
	File file = new File("D:\\temp\\test\\icon-cn.png");
	InputStream inputStream =  new FileInputStream(file);
	String fileName = "sun-c11.png";
	String savePath = "D:\\temp\\test\\vo";
	//saveFile(inputStream, fileName, savePath);
	
	//保存图片
	File file1 = new File("D:\\temp\\test\\icon-cn.png");
	FileInputStream inputStream1 =  new FileInputStream(file);
	JSONObject jsonObject = new JSONObject();
	jsonObject.put("savePath", "D:\\temp\\test\\vo");
	jsonObject.put("resizeWidth", 1080);
	jsonObject.put("dict_prefix", "member,img");
	jsonObject.put("compress", true);
	jsonObject.put("fileName", "a.png");
	uploadPhoto(inputStream1, jsonObject);
	
	
}

}

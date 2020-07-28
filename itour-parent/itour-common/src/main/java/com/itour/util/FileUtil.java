package com.itour.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import com.alibaba.fastjson.JSONObject;
import com.itour.constant.FilePrefix;
import com.itour.exception.BaseException;

public class FileUtil {

private static boolean matches;

/**
 * 保存文件
 * @param inputStream
 * @param fileName
 * @param savePath
 * @return
 */
public static boolean saveFile(InputStream inputStream, String fileName,String savePath) {
	boolean b=true;
	FileOutputStream fos = null;
	  try {
		  //1.写入文件的地址
		  File file = new File(savePath+File.separator+fileName);
		  if(!file.getParentFile().exists()) {
			  file.getParentFile().mkdirs();
		  }
		  fos =  new FileOutputStream(file);
		//2.读取数据(通过字节数组来读取数据)
		 byte [] buf=new byte[1024];//准备一个容器来装数据
		int len =0;//表示每次接收读取的字节数据
		//3.循环读取数据输出
		while((len= inputStream.read(buf))!=-1) {
			fos.write(buf, 0, len);
		}
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		b=false;
	}finally {
		if(fos!=null) {
			try {
				fos.close();
				inputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				b=false;
			}
			
		}
	}
	return b;
	
}
public static boolean uploadPhoto(InputStream inputStream,JSONObject jsonObject) {
	//文件名称
	String fileName = jsonObject.getString("fileName");
	//检查图片后缀
	checkedSuffix(fileName, FilePrefix.FUNCTION_IMG);
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
	String path=savepath+File.separator+prefix;
	return path;
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
	if(FilePrefix.FUNCTION_IMG.equals(function)) {
		  regex ="(.)+(.png|.jpg|.jpeg)";
		  matches = suffix.matches(regex);
		  if(!matches) {
				throw new BaseException(FilePrefix.ERROR_SUFFIX_IMG);
		 }
	}else if(FilePrefix.FUNCTION_PDF.equals(function)) {
		  regex ="(.)+(.pdf)";
		  matches = suffix.matches(regex);
		  if(!matches) {
				throw new BaseException(FilePrefix.ERROR_SUFFIX_PDF);
		 }
	}else if(FilePrefix.FUNCTION_DOC.equals(function)) {
		  regex ="(.)+(.doc|docx)";
		  matches = suffix.matches(regex);
		 if(!matches) {
				throw new BaseException(FilePrefix.ERROR_SUFFIX_DOC);
		}
	}else if(FilePrefix.FUNCTION_PDF_DOC.equals(function)) {
		  regex ="(.)+(.pdf|.doc|docx)";
		  matches = suffix.matches(regex);
		 if(!matches) {
				throw new BaseException(FilePrefix.ERROR_SUFFIX_PDF_DOC);
		}
	}
	
	
	return matches;
}
public static boolean uploadPdf(InputStream inputStream,JSONObject jsonObject) {
	//文件名称
		String fileName = jsonObject.getString("fileName");
		//检查图片后缀
		checkedSuffix(fileName, FilePrefix.FUNCTION_PDF);
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
	boolean saveFile = saveFile(inputStream, fileName, savePath);
	return saveFile;
}
public static boolean uploadDoc(InputStream inputStream,JSONObject jsonObject) {
	//文件名称
		String fileName = jsonObject.getString("fileName");
		//检查图片后缀
		checkedSuffix(fileName, FilePrefix.FUNCTION_DOC);
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
	boolean saveFile = saveFile(inputStream, fileName, savePath);
	return saveFile;
}
public static boolean uploadPdfAndDoc(InputStream inputStream,JSONObject jsonObject) {
	//文件名称
		String fileName = jsonObject.getString("fileName");
		//检查图片后缀
		checkedSuffix(fileName, FilePrefix.FUNCTION_PDF_DOC);
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
	boolean saveFile = saveFile(inputStream, fileName, savePath);
	return saveFile;
}
public static void main(String[] args) throws FileNotFoundException {
	//保存文件
	File file = new File("D:\\temp\\test\\上海影视城.docx");
	InputStream inputStream =  new FileInputStream(file);
	String fileName = "上海影视城.docx";
	String savePath = "D:\\temp\\test\\vo";
	//saveFile(inputStream, fileName, savePath);
	System.out.println(getUUIDFileName("wangtao.jpg"));
	
	//保存图片
	File file1 = new File("D:\\temp\\test\\icon-xh.png");
	InputStream inputStream1 =  new FileInputStream(file);
	JSONObject jsonObject = new JSONObject();
	jsonObject.put("savePath", "D:\\temp\\test\\vo");
	jsonObject.put("fileName", "icon-xh.png");
	jsonObject.put("dict_prefix", "img");
	//uploadPhoto(inputStream1, jsonObject);
	boolean checkedSuffix = checkedSuffix("png.wwwangtao.png444",FilePrefix.FUNCTION_IMG);
	System.out.println(checkedSuffix);

}
}

package com.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

public class TestFile {


/**
 * File只操作文件本身相关的操作(创建、删除等),不涉及到文件内容
 *一、常用构造  
 *  1.设置完整路径(大部分情况下使用此方法)
 *  public File(String pathname){}
 *  2.设置父路径和子文件路径（Android上使用的比较多）
 *  public File(String parent, String child) {} 
 *  3.创建文件
 *  public boolean createNewFile() throws IOException {}
 *  a.如果目录不能访问 throws IOException 
 *  b.如果文件重名或文件名称错误 throws IOException
 *  4.删除文件 
 *  public boolean delete() {}
 *  5.判断文件是否存在 
 *  public boolean exists(){}
 *  6.判断是否是文件
 *  public boolean isFile() {} 
 *  7.获取父路径
 *  public File getParentFile(){} 
 *  8.创建多级目录
 *  public boolean mkdirs() {} 
 *  9.使用File类操作的时候路径分隔符使用File.separator，解决window和linux操作符的问题;
 *  
 */
	@Test
public void testFile() throws IOException {
	File file = new File("D:\\temp\\imgs\\test\\test.txt");
	File parentFile = file.getParentFile();
	if (file.exists()) {//如果文件存在删除
		file.delete();
	}
	if (!parentFile.exists()) {//如果父目录不存在
		parentFile.mkdirs();
	}
	file.createNewFile();
}
/**
 * 1.java中IO核心File,InputStream(输入字节流),OutputStream(输出字节流),Writer(输出字符流),Reader（出入字符流）五大类及Serializable接口;
 * 2.InputStream,OutputStream,Writer,Reader本身是一个抽象类,如果要实例化操作需要借助对应的子类来实例化;
 * 3.InputStream提供了三个输入的方法
 *   a.读取单个字节public abstract int read() throws IOException,返回读取的字节内容,如果已经没有内容了返回-1;
 *   b.将读取的数据保存在字节数组里：public int read(byte[] b) throws IOException,返回读取的数据长度,如果读取到了结尾返回-1;
 *   c.将读取的数据保存到部分字节数组里public int read(byte[] b, int off,  int len) throws IOException,返回读取的部分数据的长度,如果已经读取到结尾返回-1;
 * 4.OutputStream提供了三个输入的方法:
 *   a.输出单个字节 ： public abstract void write(int b) throws IOException;
 *   b.输出部分字节数组：public void write(byte[] b,int off,int len)throws IOException;
 *   c.输出全部字节数组public void write(byte[] b)throws IOException;
 * 
 * 5.Reader提供了一系列输入的方法：
 *   a.读取单个字符 public int read() throws IOException ,返回读取的字节内容,如果已经没有内容了返回-1;
 *   b.读取字符串到字符数组:public int read(char cbuf[]) throws IOException,返回数据读取的长度，如果已经读取到结尾了返回-1;
 * 6.Writer提供了一系列输出的方法：
 *   a.输出单个字符public void write(int c) throws IOException 
 *   b.输出全部的的字符数组：public void write(char cbuf[]) throws IOException
 *   c.输出字符串public void write(String str) throws IOException
 * 
 * 
 * 7.如何使用：
 * 1.在实际开发中使用字节流的情况比较多如图片，音乐,对于字符流最大的好处是可以进行中文的有效处理;
 * 2.如果要考虑中文的时候优先考虑字符流，如果没有中文问题建议使用字节流; 
 * 
 * 8.字节流与字符流转换(转换流)
 * InputStreamReader 、OutputStreamWriter
 * 文件保存在磁盘上，磁盘上能够保存的文件形式都是以字节的方式保存的,在使用字符流读取的时候， 实际上也是针对字节数组进行读取，
 * 只不过这个转换过程被我们的系统隐藏了,在缓冲区里完成了; 如果要进行转换唯一的可能是处理中文，两个转换类都是字符流的子类，
 * 属于字符流和字节流沟通的桥梁;
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * @throws IOException 
 */
	@Test
public void testInputStream() throws IOException {
	File file  = new File("d://temp//a");
	System.out.println(file.getName());
}
/**
 * 保存单个文件	
 * @param stream  输入流
 * @param savePath 文件完整路径
 * @return true:保存成功;false：保存失败
 */
public	boolean saveFile(InputStream stream,String savePath) {
	FileOutputStream fos = null;
	boolean b = false;
	try {
		File file = new File(savePath).getParentFile();
		if(!file.exists()) {
			file.mkdirs();
		}
		 fos = new FileOutputStream(savePath);
		byte [] buf = new byte[1024];
		int len;
		while((len=stream.read(buf)) != -1) {
			fos.write(buf, 0, len);
		}
		b = true;
	}catch (IOException e) {
		// TODO: handle exception
	  e.printStackTrace();
	  b = true;
	}finally {
		try {
			stream.close();
			fos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			b = true;
		}
	}
return b;	
	
}
/**
 * 保存单个文件	
 * @param stream  输入流
 * @param savePath 文件完整路径
 * @return true:保存成功;false：保存失败
 */
public static boolean saveFile(File file,String savePath) {
	FileOutputStream fos = null;
	FileInputStream fis = null;
	boolean b = false;
	try {
		File parentFile = new File(savePath).getParentFile();
		if(!parentFile.exists()) {
			parentFile.mkdirs();
		}
		 fis = new FileInputStream(file);
		fos = new FileOutputStream(savePath);
		byte [] buf = new byte[1024];
		int len;
		while((len=fis.read(buf)) != -1) {
			fos.write(buf, 0, len);
		}
		b = true;
	}catch (IOException e) {
		// TODO: handle exception
		e.printStackTrace();
		b = true;
	}finally {
		try {
			fis.close();
			fos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			b = true;
		}
	}
	return b;	
	
}

public void backupFile(String source,String target) {
	File sourceF = new File(source);
	File[] sourceFiles = sourceF.listFiles();
	for (File file : sourceFiles) {
	   boolean f = file.isFile();
	   if (f) {
		   //保存文件
		   saveFile(file, target+File.separator+file.getName());
	   }else {
		   String absolutePath = file.getAbsolutePath();
		   String name = file.getName();
		   backupFile(absolutePath,target+File.separator+name);  
	   }
	}
}

}

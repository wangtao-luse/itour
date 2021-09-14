package com.itour.util;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class PdfUtil {
public static void main(String[] args) throws Exception {
	//1.创建document对象
	Document document = new Document();	
	setAttr(document,"Docker的使用","wangtao","Docker在实际开发中的应用","Docker","wangtao.club");
	//2.建立一个书写器(Writer)与document对象关联，通过书写器(Writer)可以将文档写入到磁盘中
	FileOutputStream os = new FileOutputStream(new File("D:\\dev\\pdf\\test.pdf"));
	PdfWriter instance = PdfWriter.getInstance(document, os);    
	//3.打开文档
	document.open();
	//设置字体
	Font font = new Font(BaseFont.createFont("STSongStd-Light","UniGB-UCS2-H",BaseFont.NOT_EMBEDDED));
	//段落
	addParagraph(document,"1.你好;",font);
	addParagraph(document,"2.不能再好了",font);
	 URL url = new URL("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fcdn.duitang.com%2Fuploads%2Fitem%2F201202%2F18%2F20120218194349_ZHW5V.thumb.700_0.jpg&refer=http%3A%2F%2Fcdn.duitang.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1633680760&t=1b22e6fd447217883fb00dc361419d03");
	addImg(document, url, 0, 0, 200, 300);
    //1.创建PdfPTable对象
      
	//5.关闭文档
	document.close();
	
}
/**
 * 
 * @param header 表头和实体对应的关系
 * @param listRow 数据源
 * @param font    字体
 * @param clazz   对应的实体类
 * @return
 * @throws Exception
 */
public PdfPTable crateTab(String [][] header,List<?> listRow,Font font,Class clazz) throws Exception {
	//1.创建PdfPTable对象
		PdfPTable table = new PdfPTable(header.length);
		for (int i = 0; i < header.length; i++) {
			PdfPCell createCell = PdfUtil.createCell(header[i][0], font);
			table.addCell(createCell);
		}
		
		for (Object object : listRow) {
			for (int i = 0; i < header.length; i++) {
				Field field = clazz.getDeclaredField(header[i][1]);				
				Object v = field.get(field.getName());
				PdfPCell createCell = PdfUtil.createCell(v+"", font);
				table.addCell(createCell);
				
			}
		}
		
		return table;
}
/**
 * 创建单元格
 * @param value
 * @param font
 * @return
 */
public static PdfPCell createCell(String value,Font font) {
	PdfPCell  cell = new PdfPCell();
	cell.setPhrase(new Paragraph(value, font));
	return cell;
}
/**
 * 在pdf中添加图片
 * @param document
 * @param url  图片url
 * @param absoluteX  图片在pdf中x轴的位置
 * @param absoluteY 图片在pdf中y轴的位置
 * @param newWidth  图片的宽
 * @param newHeight  图片的高
 * @throws Exception
 */
public  static void addImg(Document document,URL url,float absoluteX,float absoluteY,float newWidth,float newHeight)
		throws Exception {
	Image img = Image.getInstance(url);
	//设置图片的位置
	img.setAbsolutePosition(absoluteX, absoluteY);
	//设置图片的宽和高
	img.scaleAbsolute(newWidth, newHeight);	
	document.add(img);
}
/**
 * 添加段落
 * @param document
 * @param string
 * @throws DocumentException
 */
public static void addParagraph(Document document,String string) throws DocumentException {
	Paragraph  p = new Paragraph(string);
	document.add(p);
}
/**
 * 添加段落添加段落（itext默认不支持中午,如果保护中文需要指定字体）
 * @param document
 * @param string
 * @param font 
 * @throws DocumentException
 */
public static void addParagraph(Document document,String string,Font font) throws DocumentException {
	Paragraph  p = new Paragraph(string, font);
	document.add(p);
}
/**
 * 设置pdf属性
 * @param document document对象
 * @param title  标题
 * @param author 作者
 * @param subject 主题
 * @param keyword 关键字
 * @param creator 应用程序
 */
public  static void setAttr(Document document,String title,String author,String subject,String keyword,String creator) {
	//a.标题
	document.addTitle(title);
	//b.作者
	document.addAuthor(author);
	//主题
	document.addSubject(subject);
	//关键字
	document.addKeywords(keyword);
	//创建日期
	document.addCreationDate();
	//应用程序
	document.addCreator(creator);
}
}

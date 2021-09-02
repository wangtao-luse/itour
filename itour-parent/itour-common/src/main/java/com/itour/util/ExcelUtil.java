package com.itour.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.extensions.XSSFCellBorder.BorderSide;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.util.StringUtils;

import com.itour.entity.Excel;
import com.itour.entity.Person;

public class ExcelUtil {
/**
 * java操作Excel方式
 *   1.poi
 *   Workbook接口的实现类:
 *    HSSFWorkbook
 *    XSSFWorkbook
 *    SXSSFWorkbook
 *            区别:
 *    HSSF:Excel97-2003版本，扩展名为.xls。一个sheet最大行数65536，最大列数256;
 *    XSSF:Excel2007版本开始，扩展名为.xlsx。一个sheet最大行数1048576，最大列数16384;
 *    SXSSF:是在XSSF基础上，POI3.8版本开始提供的支持低内存占用的操作方式，扩展名为.xlsx
 *   2.jxl
 *   3.EasyExcel
 * @throws IOException 
 * @throws FileNotFoundException 
 */
	
	public static void main(String[] args) throws Exception {
		//POI写实例
		//testPOI();
		List aList = new ArrayList<Person>();
		  aList.add(new Person(1L, "mike",new Date(),new Date()));
		  aList.add(new Person(2L, "amy",new Date(),new Date()));
		 for (int i = 0; i < aList.size(); i++) {
			  Object bean = aList.get(i);
			  BeanWrapper bw = new BeanWrapperImpl(bean);
			  Object value = bw.getPropertyValue("name");
			  System.out.println(value);
		}
		 String [][] headers = {
				 {"编号","id"},
				 {"姓名","name"},
				 {"生日","birthday"}
		 };
		ArrayList<Excel> list = new ArrayList<Excel>();
		list.add(new Excel("编号", "id"));
		list.add(new Excel("姓名", "name"));
		list.add(new Excel("生日", "birthday"));
		list.add(new Excel("最后修改日期", "updateDate",DateUtil.FMT_DATETIME_SHORT));
		 Workbook workbook = poiWriteExcel("用户表",list,aList);
		 FileOutputStream fileOutputStream = new FileOutputStream(new File("D:\\dev\\excel\\test.xlsx"));
			workbook.write(fileOutputStream);
			fileOutputStream.close();
			List<Excel> fList = new ArrayList<Excel>();
			fList.add(new Excel("用户数据", 0, 0, 0, 3));
			fList.add(new Excel("攻略数据", 0, 0, 4, 7));
			fList.add(new Excel("客户来源", 0, 0, 8, 10));
			
			List<Excel> slist = new ArrayList<Excel>();
			slist.add(new Excel("本周新增用户数","id"));
			slist.add(new Excel("本月新增用户数","id"));
			slist.add(new Excel("本月活跃用户数","id"));
			slist.add(new Excel("总用户数","id"));
			
			slist.add(new Excel("文章总数","id"));
			slist.add(new Excel("本月新增文章数","id"));
			slist.add(new Excel("本周浏览量","id"));
			slist.add(new Excel("当天浏览量","id"));
			
			slist.add(new Excel("微信","id"));
			slist.add(new Excel("QQ","id"));
			slist.add(new Excel("微博","id"));
			poiWriteExcel("用户统计表",fList,slist,aList);	
			
	}
private static void testPOI() throws FileNotFoundException, IOException {
	//1.创建一个工作簿
	Workbook workbook = new SXSSFWorkbook();
	//2.创建一个工作表
	Sheet sheet = workbook.createSheet("用户表");
	     
	//3.创建行  rownum从0开始
	Row row = sheet.createRow(0);
	//4.创建单元格
	Cell c1 = row.createCell(0);
	//设置值
	c1.setCellValue("编号");
	//写入
	FileOutputStream fileOutputStream = new FileOutputStream(new File("D:\\dev\\excel\\test1.xlsx"));
	workbook.write(fileOutputStream);
	fileOutputStream.close();
	
}

    public static Workbook poiWriteExcel(String sheetName,List<Excel>firstList,List<Excel> sList,List listRows) throws Exception {
    	int rownum = 0;
    	//1.创建一个工作簿
		SXSSFWorkbook workbook = new SXSSFWorkbook();
		//2.创建一个工作表
		SXSSFSheet sheet = workbook.createSheet(sheetName);
		
		//表头第一行
		for (int i = 0; i < firstList.size();i++) {
			Excel excel = firstList.get(i);
			int firstRow = excel.getFirstRow();//开始行号 从0开始
			int lastRow = excel.getLastRow();//终止行号 从0开始
			int firstCol = excel.getFirstCol();//开始列号 从0开始
			int lastCol = excel.getLastCol();//终止列号 从0开始
			String value= excel.getShowName();
			
			CellRangeAddress cra = new CellRangeAddress(firstRow, lastRow, firstCol, lastCol);
			sheet.addMergedRegion(cra);
			SXSSFRow row = sheet.getRow(rownum);
			if(null == row) {
				 row = sheet.createRow(rownum);
			}			
			SXSSFCell cell = row.getCell(firstCol );
			if(null == cell) {
				cell = row.createCell(firstCol);
			}
			cell.setCellValue(value);
			
		}
		
		
		rownum++;
		//第二行
		for (int i = 0; i < sList.size(); i++) {
			SXSSFRow row = sheet.getRow(rownum);
			if(null == row) {
				 row = sheet.createRow(rownum);
			}	
			SXSSFCell cell = row.createCell(i);
			cell.setCellValue(sList.get(i).getShowName());
			cell.setCellStyle(setBorderStyle(workbook, 0));
		}
		rownum++;
		//4.组装数据
				for (int i = 0; i < listRows.size(); i++) {
					//创建行
					Row sRow = sheet.createRow(rownum);
					Object bean = listRows.get(i);
					BeanWrapper bw = new BeanWrapperImpl(bean);
					//创建单元格
					for (int j = 0; j < sList.size(); j++) {
						Object value = bw.getPropertyValue(sList.get(j).getColName());
						if(null!=value) {
							if(value instanceof Number) {
								//设置单元格数据
								double v = Double.valueOf(value.toString());
								setCellValue(j,v , sRow,null);
							}else if(value instanceof Date) {
								setCellValue(j, value, sRow,null);
							}else if(value instanceof String) {
								setCellValue(j, String.valueOf(value), sRow);
							}
						}
						
						
					}
					rownum++;
				}
		setSizeColumn(sheet, sList.size());
		 FileOutputStream fileOutputStream = new FileOutputStream(new File("D:\\dev\\excel\\test2.xlsx"));
			workbook.write(fileOutputStream);
			fileOutputStream.close();
    	return null;
    }
    

    /**
     * Excel导出不支持格式化特定日期及数值格式化
     * @param sheetName 表名称
     * @param headers 表头
     * @param listRows 导出数据源
     * @return
     */
	public static Workbook poiWriteExcel(String sheetName,String[][] headers,List listRows) {
		int currentRow = 0;
		//1.创建一个工作簿
		SXSSFWorkbook workbook = new SXSSFWorkbook();
		//2.创建一个工作表
		SXSSFSheet sheet = workbook.createSheet(sheetName);
		//3.创建行第一行  rownum从0开始
		Row row = sheet.createRow(currentRow);
		for (int i = 0; i < headers.length; i++) {
			Cell cell = row.createCell(i);
			cell.setCellValue(headers[i][0]);
			
		}
		currentRow++;
		//4.组装数据
		for (int i = 0; i < listRows.size(); i++) {
			//创建行
			Row sRow = sheet.createRow(currentRow);
			Object bean = listRows.get(i);
			BeanWrapper bw = new BeanWrapperImpl(bean);
			//创建单元格
			for (int j = 0; j < headers.length; j++) {
				Object value = bw.getPropertyValue(headers[j][1]);
				if(null!=value) {
					if(value instanceof Number) {
						//设置单元格数据
						double v = Double.valueOf(value.toString());
						setCellValue(j,v , sRow,null);
					}else if(value instanceof Date) {
						setCellValue(j, value, sRow,null);
					}else if(value instanceof String) {
						setCellValue(j, String.valueOf(value), sRow);
					}
				}
				
				
			}
			currentRow++;
		}
		return workbook;
	}
	/**
	 * excel导出  支持格式化日期及数值格式化
	 * @param sheetName 表名称
	 * @param headers   表头
	 * @param listRows  导出数据源
	 * @return
	 */
	public static Workbook poiWriteExcel(String sheetName,List<Excel> headers,List listRows) {
		int currentRow = 0;
		//1.创建一个工作簿
		SXSSFWorkbook workbook = new SXSSFWorkbook();
		//2.创建一个工作表
		SXSSFSheet sheet = workbook.createSheet(sheetName);
		//3.创建行第一行  rownum从0开始
		Row row = sheet.createRow(currentRow);
		for (int i = 0; i < headers.size(); i++) {
			Cell cell = row.createCell(i);
			cell.setCellValue(headers.get(i).getShowName());
			
		}
		currentRow++;
		//4.组装数据
		for (int i = 0; i < listRows.size(); i++) {
			//创建行
			Row sRow = sheet.createRow(currentRow);
			Object bean = listRows.get(i);
			BeanWrapper bw = new BeanWrapperImpl(bean);
			//创建单元格
			for (int j = 0; j < headers.size(); j++) {
				Object value = bw.getPropertyValue(headers.get(j).getColName());
				if(null!=value) {
					if(value instanceof Number) {
						//设置单元格数据
						double valueOf = Double.valueOf(value.toString());
						setCellValue(j, valueOf, sRow,headers.get(j).getPattern());
					}else if(value instanceof Date) {
						setCellValue(j, value, sRow,headers.get(j).getPattern());
					}else if(value instanceof String) {
						setCellValue(j, String.valueOf(value), sRow);
					}
				}
				
				
			}
			currentRow++;
		}
		setSizeColumn(sheet, headers.size());
		return workbook;
	}
	/**
	 * 创建单元格
	 * @param column 单元格的下标从0开始
	 * @param value 单元格的值
	 * @param row  行对象
	 */
	public static void setCellValue(int column,double value,Row row,String pattern) {
		 Cell cell = row.createCell(column);
		  if(!StringUtils.isEmpty(pattern)) {
			String v = DecimalUtil.numberFormat(value, pattern);
			cell.setCellValue(v);
		  }else {
			  cell.setCellValue(value);
		  }
		 
		
		
		
	}
	/**
	 * 创建单元格
	 * @param column 单元格的下标从0开始
	 * @param value 单元格的值
	 * @param row  行对象
	 */
	public static void setCellValue(int column,String value,Row row) {
		Cell cell = row.createCell(column);
		cell.setCellValue(value);
	}
	/**
	 * 创建单元格
	 * @param column 单元格的下标从0开始
	 * @param value 单元格的值
	 * @param row  行对象
	 */
	public static void setCellValue(int column,Object value,Row row,String pattern) {
		Cell cell = row.createCell(column);
		if(StringUtils.isEmpty(value)) {
			cell.setCellValue("");
		}else {
			if(StringUtils.isEmpty(pattern)) {
				pattern=DateUtil.FMT_DATE;
			}
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			String v = sdf.format((Date)value);
			cell.setCellValue(v);
		}
	}
	/**
     * @Description:表格自适应宽度(中文支持)
     * @Author:
     * @param sheet sheet
     * @param columnLength 列数
     */
    private static void setSizeColumn(Sheet sheet, int columnLength) {
        for (int columnNum = 0; columnNum <= columnLength; columnNum++) {
            int columnWidth = sheet.getColumnWidth(columnNum) / 256;
            for (int rowNum = 0; rowNum < sheet.getLastRowNum(); rowNum++) {
                Row currentRow; // 当前行未被使用过
                if (sheet.getRow(rowNum) == null) {
                    currentRow = sheet.createRow(rowNum);
                } else {
                    currentRow = sheet.getRow(rowNum);
                }
                if (currentRow.getCell(columnNum) != null) {
                   Cell currentCell = currentRow.getCell(columnNum);
                    if (currentCell.getCellType() == CellType.STRING) {
                        int length = currentCell.getStringCellValue().getBytes().length;
                        if (columnWidth < length) {
                            columnWidth = length;
                        }
                    }
                }
            }
            sheet.setColumnWidth(columnNum, (columnWidth+1) * 256);
        }
    }
    /**
     * 
     * @param wb
     * @param cellIndex
     * @param backWidth
     * @return
     */
    private static  CellStyle setBorderStyle(SXSSFWorkbook wb,int cellIndex){
    	XSSFCellStyle cellStyle = (XSSFCellStyle)wb.createCellStyle();
        XSSFFont font = null;
        switch (cellIndex){
            case 0:
            	 font = (XSSFFont) wb.createFont(); // 创建字体对象
            	 font.setFontHeightInPoints((short) 10); // 设置字体大小
        		 font.setFontName("宋体");            	
        		 cellStyle.setFont(font);
        		 //垂直对齐位于单元高度的中心
        		 cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        		 //水平对齐居中，表示文本在单元格中居中。
        		 cellStyle.setAlignment(HorizontalAlignment.CENTER);
        		 cellStyle.setBorderTop(BorderStyle.THIN);//上边框
        		 cellStyle.setBorderRight(BorderStyle.THIN);//右边框
        		 cellStyle.setBorderBottom(BorderStyle.THIN); //下边框
        		 cellStyle.setBorderLeft(BorderStyle.THIN);//左边框
        		 
                break;
            case 1:
                cellStyle.setBorderBottom(BorderStyle.MEDIUM); //下边框
                cellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex()); //下边框颜色
                cellStyle.setBorderLeft(BorderStyle.MEDIUM_DASHED);//左边框
                cellStyle.setBorderTop(BorderStyle.MEDIUM);//上边框
                cellStyle.setTopBorderColor(IndexedColors.BLACK.getIndex()); //上边框颜色
                cellStyle.setBorderRight(BorderStyle.MEDIUM_DASHED);//右边框
                cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);//设置前景填充样式
                cellStyle.setFillForegroundColor(IndexedColors.BLACK.index);//前景填充色
                break;
            case 2:
                cellStyle.setBorderBottom(BorderStyle.MEDIUM); //下边框
                cellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex()); //下边框颜色
                cellStyle.setBorderLeft(BorderStyle.DASHED);//左边框
                cellStyle.setBorderTop(BorderStyle.MEDIUM);//上边框
                cellStyle.setTopBorderColor(IndexedColors.BLACK.getIndex()); //上边框颜色
                cellStyle.setBorderRight(BorderStyle.DASHED);//右边框
                break;
            case 3:
                cellStyle.setBorderBottom(BorderStyle.MEDIUM); //下边框
                cellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex()); //下边框颜色
                cellStyle.setBorderLeft(BorderStyle.DASHED);//左边框
                cellStyle.setBorderTop(BorderStyle.MEDIUM);//上边框
                cellStyle.setTopBorderColor(IndexedColors.BLACK.getIndex()); //上边框颜色
                cellStyle.setBorderRight(BorderStyle.DASHED);//右边框
                font = (XSSFFont) wb.createFont();
                font.setFontName("宋体");
			/*
			 * if("20".equals(backWidth)){ font.setFontHeightInPoints((short) 14);//设置字体大小
			 * }else if("30".equals(backWidth)){ font.setFontHeightInPoints((short)
			 * 16);//设置字体大小 }else if("40".equals(backWidth)){
			 * font.setFontHeightInPoints((short) 18);//设置字体大小 }else
			 * if("50".equals(backWidth)){ font.setFontHeightInPoints((short) 20);//设置字体大小
			 * }else if("60".equals(backWidth)){ font.setFontHeightInPoints((short)
			 * 22);//设置字体大小 }else{ font.setFontHeightInPoints((short) 14);//设置字体大小 }
			 */
                cellStyle.setFont(font);//选择需要用到的字体格式
                cellStyle.setAlignment(HorizontalAlignment.CENTER);//水平居中
                cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中
                break;
            case 4:
                cellStyle.setBorderBottom(BorderStyle.MEDIUM); //下边框
                cellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex()); //下边框颜色
                cellStyle.setBorderLeft(BorderStyle.DASHED);//左边框
                cellStyle.setBorderTop(BorderStyle.MEDIUM);//上边框
                cellStyle.setTopBorderColor(IndexedColors.BLACK.getIndex()); //上边框颜色
                cellStyle.setBorderRight(BorderStyle.DASHED);//右边框
                font = (XSSFFont) wb.createFont();
                font.setFontName("宋体");
                font.setFontHeightInPoints((short) 14);//设置字体大小
                cellStyle.setFont(font);//选择需要用到的字体格式
                cellStyle.setAlignment(HorizontalAlignment.CENTER);//水平居中
                cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中
                cellStyle.setWrapText(true);//设置自动换行
                break;
            case 5:
                cellStyle.setBorderBottom(BorderStyle.MEDIUM); //下边框
                cellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex()); //下边框颜色
                cellStyle.setBorderLeft(BorderStyle.DASHED);//左边框
                cellStyle.setBorderTop(BorderStyle.MEDIUM);//上边框
                cellStyle.setTopBorderColor(IndexedColors.BLACK.getIndex()); //上边框颜色
                cellStyle.setBorderRight(BorderStyle.DASHED);//右边框
                font = (XSSFFont) wb.createFont();
                font.setFontName("宋体");
			/*
			 * if("20".equals(backWidth)){ font.setFontHeightInPoints((short) 12);//设置字体大小
			 * }else if("30".equals(backWidth)){ font.setFontHeightInPoints((short)
			 * 14);//设置字体大小 }else if("40".equals(backWidth)){
			 * font.setFontHeightInPoints((short) 16);//设置字体大小 }else
			 * if("50".equals(backWidth)){ font.setFontHeightInPoints((short) 18);//设置字体大小
			 * }else if("60".equals(backWidth)){ font.setFontHeightInPoints((short)
			 * 20);//设置字体大小 }else { font.setFontHeightInPoints((short) 12);//设置字体大小 }
			 */
                cellStyle.setFont(font);//选择需要用到的字体格式
                cellStyle.setAlignment(HorizontalAlignment.CENTER);//水平居中
                cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中
                break;
            case 6:
                cellStyle.setBorderBottom(BorderStyle.MEDIUM); //下边框
                cellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex()); //下边框颜色
                cellStyle.setBorderLeft(BorderStyle.DASHED);//左边框
                cellStyle.setBorderTop(BorderStyle.MEDIUM);//上边框
                cellStyle.setTopBorderColor(IndexedColors.BLACK.getIndex()); //上边框颜色
                cellStyle.setBorderRight(BorderStyle.DASHED);//右边框
                font = (XSSFFont) wb.createFont();
                font.setFontName("宋体");
			/*
			 * if(StringUtils.isEmpty(backWidth) || "20".equals(backWidth)){
			 * font.setFontHeightInPoints((short) 14);//设置字体大小 }else{
			 * font.setFontHeightInPoints((short) 16);//设置字体大小 }
			 */
                cellStyle.setFont(font);//选择需要用到的字体格式
                cellStyle.setAlignment(HorizontalAlignment.CENTER);//水平居中
                cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中
                cellStyle.setWrapText(true);//设置自动换行
                cellStyle.setRotation((short)255); //设置文字竖向排列
                break;
            case 7:
                cellStyle.setBorderBottom(BorderStyle.MEDIUM); //下边框
                cellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex()); //下边框颜色
                cellStyle.setBorderLeft(BorderStyle.DASHED);//左边框
                cellStyle.setBorderTop(BorderStyle.MEDIUM);//上边框
                cellStyle.setTopBorderColor(IndexedColors.BLACK.getIndex()); //上边框颜色
                cellStyle.setBorderRight(BorderStyle.DASHED);//右边框
                break;
            case 8:
                cellStyle.setBorderBottom(BorderStyle.MEDIUM); //下边框
                cellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex()); //下边框颜色
                cellStyle.setBorderLeft(BorderStyle.DASHED);//左边框
                cellStyle.setBorderTop(BorderStyle.MEDIUM);//上边框
                cellStyle.setTopBorderColor(IndexedColors.BLACK.getIndex()); //上边框颜色
                cellStyle.setBorderRight(BorderStyle.DASHED);//右边框
                cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);//设置前景填充样式
                cellStyle.setFillForegroundColor(IndexedColors.BLACK.index);//前景填充色
                
                break;
            case 9:
                cellStyle.setBorderBottom(BorderStyle.DASHED); //下边框
                cellStyle.setBorderLeft(BorderStyle.DASHED);//左边框
                cellStyle.setBorderTop(BorderStyle.DASHED);//上边框
                cellStyle.setBorderRight(BorderStyle.DASHED);//右边框
                break;
            default:
                cellStyle.setBorderBottom(BorderStyle.DASHED); //下边框
                cellStyle.setBorderLeft(BorderStyle.DASHED);//左边框
                cellStyle.setBorderTop(BorderStyle.DASHED);//上边框
                cellStyle.setBorderRight(BorderStyle.DASHED);//右边框
                break;
  
        }
        return cellStyle;
    }
}

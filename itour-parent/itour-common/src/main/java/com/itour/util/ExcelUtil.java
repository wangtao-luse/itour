package com.itour.util;

import java.awt.Checkbox;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
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
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.util.StringUtils;

import com.itour.constant.ExceptionInfo;
import com.itour.entity.Excel;
import com.itour.entity.Person;
import com.itour.exception.BaseException;

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
 * 
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
			fList.add(new Excel("日期",0,1,0,0,IndexedColors.CORNFLOWER_BLUE.getIndex()));
			fList.add(new Excel("用户数据", 0, 0, 1, 4,IndexedColors.PALE_BLUE.getIndex()));
			fList.add(new Excel("攻略数据", 0, 0, 5, 8,IndexedColors.CORNFLOWER_BLUE.getIndex()));
			fList.add(new Excel("客户来源", 0, 0, 9, 11,IndexedColors.SKY_BLUE.getIndex()));
			
			List<Excel> slist = new ArrayList<Excel>();
			slist.add(new Excel("日期","birthday",IndexedColors.CORNFLOWER_BLUE.getIndex()));
			
			slist.add(new Excel("本周新增用户数","id",IndexedColors.PALE_BLUE.getIndex()));
			slist.add(new Excel("本月新增用户数","id",IndexedColors.PALE_BLUE.getIndex()));
			slist.add(new Excel("本月活跃用户数","id",IndexedColors.PALE_BLUE.getIndex()));
			slist.add(new Excel("总用户数","id",IndexedColors.PALE_BLUE.getIndex()));
			
			slist.add(new Excel("文章总数","id",IndexedColors.CORNFLOWER_BLUE.getIndex()));
			slist.add(new Excel("本月新增文章数","id",IndexedColors.CORNFLOWER_BLUE.getIndex()));
			slist.add(new Excel("本周浏览量","id",IndexedColors.CORNFLOWER_BLUE.getIndex()));
			slist.add(new Excel("当天浏览量","id",IndexedColors.CORNFLOWER_BLUE.getIndex()));
			
			slist.add(new Excel("微信","id",IndexedColors.SKY_BLUE.getIndex()));
			slist.add(new Excel("QQ","id",IndexedColors.SKY_BLUE.getIndex()));
			slist.add(new Excel("微博","id",IndexedColors.SKY_BLUE.getIndex()));
			Workbook poiWriteExcel = poiWriteExcel("用户统计表",fList,slist,aList);	
			FileOutputStream fileOutputStream1 = new FileOutputStream(new File("D:\\dev\\excel\\test2.xlsx"));
			poiWriteExcel.write(fileOutputStream1);
			fileOutputStream1.close();
			FileInputStream is = new FileInputStream(new File("D:\\dev\\excel\\test3.xlsx"));
			List<Person> poiReadExcel = poiReadExcel(is, "test3.xlsx", Person.class, list);
			System.out.println("Excel解析完成");
			for (Person person : poiReadExcel) {
				System.out.println(person);
			}
			
			
			
	}
private static void testPOI() throws FileNotFoundException, IOException {
	//1.创建一个工作簿
	Workbook workbook = new SXSSFWorkbook();
	//2.创建一个工作表
	SXSSFSheet sheet =(SXSSFSheet) workbook.createSheet("用户表");
	     
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
/**
 *    读取表头只有一行单个工作表  *    
 * @param <T>
 * @param is 输入流
 * @param fileName 文件名称
 * @param clazz  需要转换的类
 * @param collist 表头和实体类 的对应关系(collist中的对象的顺序必须和表头中的一致)
 * @return List<JavaBean> 
 * @throws Exception
 */
public static <T> List<T> poiReadExcel(InputStream is,String fileName,Class<T> clazz,List<Excel> collist) throws Exception {
	Workbook workbook = getWorkbook(is, fileName);
	//获取工作簿中的电子表格数
    int numberOfSheets = workbook.getNumberOfSheets();
    //获取工作表
    Sheet sheetAt = workbook.getSheetAt(0);
    int lastRowNum = sheetAt.getLastRowNum();
   
    
    //检查Excel是否有数据
    if(lastRowNum==0) {
    	throw new BaseException(ExceptionInfo.EXCEPTION_EXCEL_ROW_FAIL);
    }
    //检查模板是否正确
    if(collist.size()>0) {
    	for (int i = 0; i < collist.size(); i++) {
			Cell cell = getCell(sheetAt, 0, i);
			if(collist.get(i).getShowName() == cell.getStringCellValue()) {
				throw new BaseException(ExceptionInfo.EXCEPTION_EXCEL_TEMPLATE_FAIL);
			}
		}
    }
    //数据转换  
    //迭代行
    List<T> rList = new ArrayList<T>();
    for (int i = 1; i <= lastRowNum; i++) {
    	//获取行
    	 Row row = sheetAt.getRow(i);
    	 T newInstance = clazz.getDeclaredConstructor().newInstance();
    	 //迭代列
    	for (int j = 0; j < collist.size(); j++) {
    		//获取Excel工作标准单元格的值
			Cell cell = row.getCell(j);
			String regex = collist.get(j).getRegex();
			if(cell.getCellType() == CellType.STRING) {
				boolean check = Excel.check(regex, cell.getStringCellValue());
				if(!check) {
					String message = "请检查"+collist.get(j).getShowName()+"第"+i+"行的值,"+collist.get(j).getMsg();
				 throw new BaseException(message);
				}
			}else if(cell.getCellType() == CellType.NUMERIC) {
				String text = NumberToTextConverter.toText(cell.getNumericCellValue());
				boolean check = Excel.check(regex,text );
				if(!check) {
					String message = "请检查"+collist.get(j).getShowName()+"第"+i+"行的值,"+collist.get(j).getMsg();
				 throw new BaseException(message);
				}
			}
			
			//获取对应的字段名称
			String colName = collist.get(j).getColName();
			//获取指定类中的指定字段
			Field field = clazz.getDeclaredField(colName);
			//获取字段的类型
			String name = field.getType().getSimpleName();
			field.setAccessible(true);
			if("String".equals(name)) {
				field.set(newInstance, cell.getStringCellValue());
			}else if("Date".equals(name)) {
				String pattern = collist.get(i).getPattern();
				if(StringUtils.isEmpty(pattern)) {
					pattern=DateUtil.FMT_DATE;
				}
				SimpleDateFormat sdf = new SimpleDateFormat(pattern);
				String dateCellValue = cell.getStringCellValue();
				Date parse = sdf.parse(dateCellValue);
				field.set(newInstance, parse);	
			}else if("Double".equals(name)) {
				double numericCellValue = cell.getNumericCellValue();
				field.set(newInstance, numericCellValue);	
			}else if("Integer".equals(name)) {
				double value = cell.getNumericCellValue();
				String text = NumberToTextConverter.toText(value);
				field.set(newInstance, Integer.valueOf(text));	
			}else if("Long".equals(name)) {
				double value = cell.getNumericCellValue();
				String text = NumberToTextConverter.toText(value);
				field.set(newInstance, Long.valueOf(text));	
			}
		    
		}
    	rList.add(newInstance);
		
	}
   
    return rList;
	
}

/**
 * 根据不同的Excel版本初始化Workbook
 * @param is  输入流
 * @param fileName  文件名
 * @return   
 * @throws IOException
 */
private static Workbook getWorkbook(InputStream is, String fileName) throws IOException {
	Workbook workbook =null;
	String name = fileName.toLowerCase();
    if(name.endsWith(".xls")) {
    	 workbook = new HSSFWorkbook(is);
    }else if(name.endsWith(".xlsx")) {
    	workbook = new XSSFWorkbook(is);
    }else {
    	throw new BaseException(ExceptionInfo.EXCEPTION_EXCEL_SUFFIX_FAIL);
    }
	return workbook;
}
   /**
          * 创建两列表头的Excel
    * @param sheetName 表名
    * @param firstList  第一行数据
    * @param sList     第二行数据
    * @param listRows  数据源
    * @return 
    * @throws Exception
    */
    public static Workbook poiWriteExcel(String sheetName,List<Excel>firstList,List<Excel> sList,List listRows) throws Exception {
    	int rownum = 0;
    	//1.创建一个工作簿
		SXSSFWorkbook workbook = new SXSSFWorkbook();
		//2.创建一个工作表
		SXSSFSheet sheet = workbook.createSheet(sheetName);
		//表头第一行
		for (int i = 0; i < firstList.size();i++) {
			Excel excel = firstList.get(i);
			Integer firstRow = excel.getFirstRow();//开始行号 从0开始
			Integer lastRow = excel.getLastRow();//终止行号 从0开始
			Integer firstCol = excel.getFirstCol();//开始列号 从0开始
			Integer lastCol = excel.getLastCol();//终止列号 从0开始
			String value= excel.getShowName();
			Short foregroundColor = excel.getForegroundColor();
				CellRangeAddress cra = new CellRangeAddress(firstRow, lastRow, firstCol, lastCol);
				sheet.addMergedRegion(cra);
				//解决边框显示不全
				RegionUtil.setBorderBottom(BorderStyle.THIN, cra, sheet); // 下边框
			    RegionUtil.setBorderLeft(BorderStyle.THIN, cra, sheet); // 左边框
				RegionUtil.setBorderRight(BorderStyle.THIN, cra, sheet); // 有边框
				RegionUtil.setBorderTop(BorderStyle.THIN, cra, sheet); // 上边框
				SXSSFCell cell =getCell(sheet, rownum, firstCol);
				cell.setCellValue(value);
				cell.setCellStyle(setBorderStyle(workbook, foregroundColor,0));
		}
		rownum++;
		//第二行
		for (int i = 0; i < sList.size(); i++) {
			Excel excel = sList.get(i);
			Short foregroundColor = excel.getForegroundColor();
			SXSSFRow row = sheet.getRow(rownum);
			if(null == row) {
				 row = sheet.createRow(rownum);
			}	
			SXSSFCell cell = row.createCell(i);
			cell.setCellValue(sList.get(i).getShowName());
			cell.setCellStyle(setBorderStyle(workbook,foregroundColor, 0));
			
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
						SXSSFRow row = sheet.getRow(1);
						short foregroundColor = row.getCell(j).getCellStyle().getFillForegroundColor();
						if(null!=value) {
							if(value instanceof Number) {
								//设置单元格数据
								double v = Double.valueOf(value.toString());
								Cell cell = setCellValue(j,v , sRow,null);
								cell.setCellStyle(setBorderStyle(workbook,foregroundColor, 0));
							}else if(value instanceof Date) {
								Cell cell = setCellValue(j, value, sRow,null);
								cell.setCellStyle(setBorderStyle(workbook,foregroundColor, 0));
							}else if(value instanceof String) {
								Cell cell = setCellValue(j, String.valueOf(value), sRow);
								cell.setCellStyle(setBorderStyle(workbook,foregroundColor, 0));
							}
						}
						
						
					}
					rownum++;
				}
		setSizeColumn(sheet, sList.size());		 
    	return workbook;
    }
    /**
              * 获取指定行的指定的单元格
     * @param sheet  SXSSFSheet对象
     * @param rownum  行号
     * @param colnum  列号
     * @return  指定行的指定单元格对象
     */
	private static SXSSFCell getCell(SXSSFSheet sheet,int rownum,int colnum) {
		SXSSFRow row = sheet.getRow(rownum);
		if(null == row) {
			 row = sheet.createRow(rownum);
		}
		SXSSFCell cell = row.getCell(colnum );
		if(null == cell) {
			cell = row.createCell(colnum);
		}
		return cell;
	}
	private static Cell getCell(Sheet sheet,int rownum,int colnum) {
		Row row = sheet.getRow(rownum);
		if(null == row) {
			row = sheet.createRow(rownum);
		}
		Cell cell = row.getCell(colnum );
		if(null == cell) {
			cell = row.createCell(colnum);
		}
		return cell;
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
	public static Cell setCellValue(int column,double value,Row row,String pattern) {
		 Cell cell = row.createCell(column);
		  if(!StringUtils.isEmpty(pattern)) {
			String v = DecimalUtil.numberFormat(value, pattern);
			cell.setCellValue(v);
		  }else {
			  cell.setCellValue(value);
		  }
		 
		
		return cell;
		
	}
	/**
	 * 创建单元格
	 * @param column 单元格的下标从0开始
	 * @param value 单元格的值
	 * @param row  行对象
	 */
	public static Cell setCellValue(int column,String value,Row row) {
		Cell cell = row.createCell(column);
		cell.setCellValue(value);
		return cell;
	}
	/**
	 * 创建单元格
	 * @param column 单元格的下标从0开始
	 * @param value 单元格的值
	 * @param row  行对象
	 */
	public static Cell setCellValue(int column,Object value,Row row,String pattern) {
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
		return cell;
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
    private static  CellStyle setBorderStyle(SXSSFWorkbook wb,Short foregroundColor,int cellIndex){
    	XSSFCellStyle cellStyle = (XSSFCellStyle)wb.createCellStyle();
        XSSFFont font = null;
        switch (cellIndex){
            case 0:
            	 font = (XSSFFont) wb.createFont(); // 创建字体对象
            	 font.setFontHeightInPoints((short) 10); // 设置字体大小
        		 font.setFontName("宋体");
        		 font.setBold(true);
        		 cellStyle.setFont(font);
        		 //垂直对齐位于单元高度的中心
        		 cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        		 //水平对齐居中，表示文本在单元格中居中。
        		 cellStyle.setAlignment(HorizontalAlignment.CENTER);
        		 cellStyle.setBorderTop(BorderStyle.THIN);//上边框
        		 cellStyle.setBorderRight(BorderStyle.THIN);//右边框
        		 cellStyle.setBorderBottom(BorderStyle.THIN); //下边框
        		 cellStyle.setBorderLeft(BorderStyle.THIN);//左边框
        		 if(null!=foregroundColor) {
        		 cellStyle.setFillForegroundColor(foregroundColor);
        		 cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        		 } 
                break;
            case 1:
              
                break;            
            default:
               
                break;
  
        }
        return cellStyle;
    }
   

}

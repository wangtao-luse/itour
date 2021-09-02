package com.itour.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
/**
 * 2021-9-2
 * @author wangtao
 *
 */
public class DecimalUtil {
/**
 * 1.计算机是二进制的。浮点数没有办法是用二进制进行精确表示，可以使用BigDecimal解决;
 * 2.public BigDecimal(double val) {}
 * The results of this constructor can be somewhat unpredictable.（这个函数返回的结果有些不可预测）
   BigDecimal bd3=new BigDecimal(2.4);
       在进行浮点数操作是尽量使用public BigDecimal(String val) {}
   BigDecimal bd4=new BigDecimal(Double.toString(2.4));
   
   
   DecimalFormat 包含一个模式 和一组符号 
每个符号含义： 
0 一个数字 
# 一个数字，不包括 0 
. 小数的分隔符的占位符 

, 分组分隔符的占位符 

; 分隔格式。 

- 缺省负数前缀。 

% 乘以 100 和作为百分比显示 

? 乘以 1000 和作为千进制货币符显示；用货币符号代替；如果双写，用 

X 前缀或后缀中使用的任何其它字符，用来引用前缀或后缀中的特殊字符。 

相关资料：https://blog.csdn.net/xxdw1992/article/details/81357507
为了能更好理解，我们可以画一个XY轴

RoundingMode.CEILING：取右边最近的整数

RoundingMode.DOWN：去掉小数部分取整，也就是正数取左边，负数取右边，相当于向原点靠近的方向取整

RoundingMode.FLOOR：取左边最近的正数

RoundingMode.HALF_DOWN:五舍六入，负数先取绝对值再五舍六入再负数

RoundingMode.HALF_UP:四舍五入，负数原理同上

RoundingMode.HALF_EVEN:这个比较绕，整数位若是奇数则四舍五入，若是偶数则五舍六入
https://www.cnblogs.com/jpfss/p/9987319.html
 */
//重量 格式化 单位
public static final String FORMAT_WEIGHT = "##,###,###,##0.0#####";
//货币 格式化 单位
public static final String FORMAT_MONEY = "##,###,###,##0.00";
//货币 格式化 单位 科学计数法
public static final String FORMAT_MONEY_1 = "#########0.00";
/**
 * 格式化且四舍五入
 * @param value
 * @param pattern
 * @return
 */
public static String numberFormat(Double value,String pattern) {
	BigDecimal bg = new BigDecimal(value);
	DecimalFormat df = new DecimalFormat(pattern);
	df.setRoundingMode(RoundingMode.HALF_UP);
	String format = df.format(bg);
	return format;
	
	
	
}
}

package com.itour.help;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayHelper {
/**
 * 将String[]数组按逗号(,)分隔转为字符串
 * @param arr
 * @return 字符串
 */
public static String arrstrToStr(String [] arr) {
	
	return String.join(",", arr);
}
/**
 * 将List<String>的集合按逗号(,)分隔转为字符串
 * @param strList
 * @return
 */
public static String arrstrToStr(List<String> strList) {
	
	return String.join(",", strList);
}
/**
 * 将String[]数组按指定方式分隔转为字符串
 * @param arr
 * @param delimiter 指定分隔符
 * @return 字符串
 */
public static String arrstrToStr(CharSequence delimiter,String [] arr) {
	
	return String.join(delimiter, arr);
}
/**
 * 将List<String> 转换为以逗号(,)分隔的字符串
 * @param list
 * @return 字符串
 */
public static String listToStr(List<String> list) {
	String [] array = new String[list.size()];
	String[] arr = list.toArray(array);
	return String.join(",", arr);
}
/**
 * 将List<String> 转换为以指定分隔的字符串
 * @param list
 * @return 字符串
 */
public static String listToStr(CharSequence delimiter,List<String> list) {
	String [] array = new String[list.size()];
	String[] arr = list.toArray(array);
	return String.join(delimiter, arr);
}
/**
 * 将List<Integer> 转为以逗号为分隔符的字符串
 * @param list
 * @return
 */
public static String intListToStr(List<Integer> list) {
	List<String> strList = toStrList(list);
	String str = listToStr(strList);
	return str;
}
/**
 * 将List<Integer> 转为以指定分隔符分隔的字符串
 * @param list
 * @param delimiter 分隔符
 * @return
 */
public static String intListToStr(CharSequence delimiter,List<Integer> list) {
	List<String> strList = toStrList(list);
	String str = listToStr(delimiter,strList);
	return str;
}
/**
 * List<Integer> 转换为List<String>
 * @param list List<Integer>
 * @return List<String>
 */
public static List<String> toStrList(List<Integer> list) {
	List<String> strList = new ArrayList<String>();
	for (Integer integer : list) {
		strList.add(String.valueOf(integer));
	}
	return strList;
}
/**
 *  将数组转为List
 * @param <T>
 * @param a 数组
 * @return
 */
public static <T> List<T> arrToList(T... a) {
return 	Arrays.asList(a);
}
}

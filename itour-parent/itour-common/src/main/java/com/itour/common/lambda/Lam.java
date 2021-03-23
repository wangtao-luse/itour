package com.itour.common.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.alibaba.fastjson.JSONArray;

public class Lam {
public static void main(String[] args) {
	/**
	 * 1.语法：
	 * 2.使用场景：
	 *   lamda表达式配合Stream API 来操作集合;
	 * 3.优缺点：
	 * 4.如何使用
	 *   //1.获取数据源
	 */
	
	//获取数据源
	List<Student> stuList = getData();
	//--------------------------------------------------------一、转换部分---------------------------------------------------------------------------
	//1.将List<Student> 中的ID(Integer)属性转为List<String>|List<Integer>的集合;
	List<String> strList = listJavaBeantoListobj(stuList);
	//2.将List<String>转换为指定分隔符(,)的字符串
	String str = listStrToStr(strList);
	
	//3.将List<Student> 中的ID转换为指定的分隔符(,)的字符串
	String ids = listjavaBeanToStr(stuList);
	
	//4.将一个按指定分割符（,）隔开的字符串转换为List<String>
	List<String> strList1 = strToListobj(ids);
	
	//5.数组-->List<Obj> (String [] -->List<String>)
	String [] strArray = new String[] {"a", "b", "c"};
	List<String> strList2 = arrTolistobj(strArray);
	
	//6.数组|List<obj>---字符串
	  String arrOrlistObjeToStr = arrOrlistObjeToStr(strArray);
	 //7.JSONArray-->按指定分隔符分隔的字符串
	  JSONArray arr = new JSONArray();
	  arr.add("1");
	  arr.add("2");
	  arr.add("3");
	  String jsonArrToStr = jsonArrToStr(arr);
	 //-----------------------二、 计算部分---------------------------------------
	//1.集合计算某一列的和
	Double cSum = cSum(stuList);
	//2.计算某一列的平均值
	Double cavg = cavg(stuList);
	//------------------------三.过滤----------------------------------------------------
	
		 
     
}
private static Double cavg(List<Student> stuList) {
	double asDouble = stuList.stream().mapToDouble(Student::getMoney).average().getAsDouble();
	return asDouble;
}
private static Double cSum(List<Student> stuList) {
	Double collect2 = stuList.stream().collect(Collectors.summingDouble(Student::getMoney));
	 double sum = stuList.stream().mapToDouble(Student::getMoney).sum();
	 System.out.println("sum1:"+collect2);
	 System.out.println("sum2:"+sum);
	 return sum;
}
private static String jsonArrToStr(JSONArray arr) {
	  String join = String.join(",",arr.stream().map(String::valueOf).collect(Collectors.toList()));
	  System.out.println(join);
	  return join;
}
private static String arrOrlistObjeToStr(String[] strArray) {
	String str2 = String.join(",", strArray);
	  String join = String.join(",", Arrays.stream(strArray).collect(Collectors.toList()));
	  System.out.println(str2);
	  System.out.println(join);
	  return str2;
}
private static List<String> arrTolistobj(String[] strArray) {
	List<String> list = Arrays.asList(strArray);
	System.out.println("list:"+list);
	return list;
}
private static List<String> strToListobj(String ids) {
	List<String> strList1 = Arrays.asList(ids.split(",")).stream().map(t->t.trim()).collect(Collectors.toList());
	List<Integer> intList1 = Arrays.asList(ids.split(",")).stream().map(t->t.trim()).map(Integer::valueOf).collect(Collectors.toList());
	
	return strList1;
}
/**
 * 将List<Student> 中的ID转换为指定的分隔符的字符串
 * @param stuList
 */
private static String listjavaBeanToStr(List<Student> stuList) {
	//将List<Student> 中的ID转换为指定的分隔符的字符串
	String str1 = String.join(",", stuList.stream().map(Student::getId).map(String::valueOf).collect(Collectors.toList()));
	String str2 = stuList.stream().map(Student::getId).map(String::valueOf).collect(Collectors.joining(","));
	System.out.println("str1 :"+str1);
	System.out.println("str2 :"+str2);
	return str1;
}
/**
 * 将List<String>转换为指定分隔符的字符串
 * @param strList
 */
private static String listStrToStr(List<String> strList) {
	//将List<String>转换为指定分隔符的字符串
	String str = strList.stream().collect(Collectors.joining(","));
	System.out.println(str);
	return str;
}
/**
 * 将List<Student> 中的ID(Integer)属性转为List<String>|List<Integer>的集合;
 * @param stuList
 */
private static List<String> listJavaBeantoListobj(List<Student> stuList) {
	//将List<Student> 中的ID(Integer)属性转为List<String>|List<Integer>的集合;map(String::valueOf)强转
	List<String> strList = stuList.stream().map(Student::getId).map(String::valueOf).collect(Collectors.toList());	
	List<String> str1List = stuList.stream().map(t->String.valueOf(t.getId())).collect(Collectors.toList());
	List<Integer> intList = stuList.stream().map(Student::getId).collect(Collectors.toList());
	 System.out.println(intList);
	 return strList;
}
private static List<Student> getData() {
	List<Student> list = new ArrayList<Student>();
	Student stu1 = new Student(1, "mike", 18,100d);
	Student stu2 = new Student(2, "mike", 16,100.00);
	Student stu3 = new Student(3, "amy", 13,100d);
	Student stu4 = new Student(4, "tom", 12,50d);
	list.add(stu1);
	list.add(stu2);
	list.add(stu3);
	list.add(stu4);
	System.out.println("1.原始数据：getData()");
	for (Student student : list) {
		System.out.println(student);
	}
	return list;
}	
}

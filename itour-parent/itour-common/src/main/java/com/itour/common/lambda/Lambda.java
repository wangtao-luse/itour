package com.itour.common.lambda;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Lambda {
	/**
	 * .语法
		   (parameters) -> expression
		   或
		   (parameters) ->{ statements; }
	 * 
	 */
	/*
	 * 优点：1.简洁;
	 *   2.易并行计算。尤其适用于遍历结果，循环计算数值或者赋值的时候非常方便;
	 *   
	 * 缺点: 1.若不用并行计算，很多时候计算速度没有比传统的 for 循环快。
	 *   2.不容易使用debug模式调试。
	 *   3.在 lambda语句中直接强制类型转换不方便。
	 *   4.不可以在foreach中修改foreach外面的值。
	 */
	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();	
	 getFiles("D:\\mysql",list);
	System.out.println("size:  "+list.size());
	}
	public static void getFiles(String path,List<String> list) {
		
 	   File file=new File(path);
 	   File[] files = file.listFiles();
 	   for (int i = 0; i < files.length; i++) {
 		  if(files[i].isFile()) {
 			  System.out.println(files[i].getName());
 			  list.add(files[i].getName());
 		  }else {
 			  String absolutePath = files[i].getAbsolutePath();
 			  getFiles(absolutePath,list);
 		  }
 	   }
	}
    private static List<Student> getData(){
    	List<Student> list = new ArrayList<Student>();
    	Student stu1 = new Student(1, "mike", 18);
    	list.add(stu1);
    	Student stu2 = new Student(2, "amy", 18);
    	list.add(stu2);
    	Student stu3 = new Student(3, "cook", 18);
    	list.add(stu3);
    	Student stu4 = new Student(4, "avatar", 18);
    	list.add(stu4);
    	Student stu5 = new Student(5, "tom", 18);
    	list.add(stu5);
    	Student stu6 = new Student(6, "juila", 18);
    	list.add(stu6);
		return list;
    	
    }
}

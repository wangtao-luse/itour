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
		
	}
	/**
	 * 初始化数据
	 * @return
	 */
	private static List<Student> getData() {
		List<Student> list = new ArrayList<Student>();
		Student stu1 = new Student(1, "mike", 11);
		Student stu2 = new Student(2, "mike", 16);
		Student stu3 = new Student(3, "amy", 13);
		Student stu4 = new Student(4, "tom", 12);
		list.add(stu1);
		list.add(stu2);
		list.add(stu3);
		list.add(stu4);
		System.out.println("原始数据：");
		for (Student student : list) {
			System.out.println(student);
		}
		return list;
	}
	public static void filterAttr() {
		List<Student> list = getData();
		
		
	}
}

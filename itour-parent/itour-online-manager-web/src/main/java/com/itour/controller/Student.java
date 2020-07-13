package com.itour.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Student {
	private String id;
	private String name;
	private String age;
	private List<Student> records = new ArrayList<Student>();

	public Student() {

	}

	public Student(String id, String name, String age) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public List<Student> getRecords() {
		return records;
	}

	public void setRecords(List<Student> records) {
		this.records = records;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", age=" + age + ", records=" + records + "]";
	}

	public static void main(String[] args) {
		// 初始化数据
		List<Student> list = getData();
		// 过滤List<JavaBean>，根据JavaBean某一个属性相同的记录
		ArrayList<Student> collect = filterJavaBeanAttr(list);
		
		
		 

	}

	private static List<Student> getData() {
		List<Student> list = new ArrayList<Student>();
		Student stu1 = new Student("1", "mike", "11");
		Student stu2 = new Student("2", "mike", "16");
		Student stu3 = new Student("3", "amy", "13");
		Student stu4 = new Student("4", "tom", "12");
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


	/**
	 * 过滤List<JavaBean>:可以根据JavaBean中某一个属性来过滤;
	 * @param list
	 * @return 过滤名字相同的对象
	 */
	public static ArrayList<Student> filterJavaBeanAttr(List<Student> list) {
		// 过滤List<JavaBean>中JavaBean某一个属性相同的记录
		ArrayList<Student> collect = list.stream().collect(Collectors.collectingAndThen(
				Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(Student::getName))), ArrayList::new));
		System.out.println("过滤名字相同的记录：");
		for (Student student : collect) {
			System.out.println(student);
		}
		return collect;
	}
}

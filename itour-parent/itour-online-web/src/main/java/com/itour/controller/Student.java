package com.itour.controller;

import java.util.ArrayList;
import java.util.List;

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

}

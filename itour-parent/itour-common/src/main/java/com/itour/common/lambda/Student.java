package com.itour.common.lambda;

public class Student {
private Integer id;
private String name;
private Integer age;
private Double money;

public Student(Integer id, String name, Integer age) {
	super();
	this.id = id;
	this.name = name;
	this.age = age;
}
public Student(Integer id, String name, Integer age,Double money) {
	super();
	this.id = id;
	this.name = name;
	this.age = age;
	this.money=money;
}
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public Integer getAge() {
	return age;
}
public void setAge(Integer age) {
	this.age = age;
}
public Double getMoney() {
	return money;
}
public void setMoney(Double money) {
	this.money = money;
}
@Override
public String toString() {
	return "Student [id=" + id + ", name=" + name + ", age=" + age + "]";
}
}

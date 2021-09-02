package com.itour.entity;

import java.util.Date;

public class Person {
private Long id;
private String name;
private Date birthday;
private Date updateDate;
public Person() {
	super();
}
public Person(Long id, String name) {
	super();
	this.id = id;
	this.name = name;
}
public Person(Long id, String name, Date birthday) {
	super();
	this.id = id;
	this.name = name;
	this.birthday = birthday;
}
public Person(Long id, String name, Date birthday, Date updateDate) {
	super();
	this.id = id;
	this.name = name;
	this.birthday = birthday;
	this.updateDate = updateDate;
}
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public Date getBirthday() {
	return birthday;
}
public void setBirthday(Date birthday) {
	this.birthday = birthday;
}
public Date getUpdateDate() {
	return updateDate;
}
public void setUpdateDate(Date updateDate) {
	this.updateDate = updateDate;
}
}

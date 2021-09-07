package com.test.reflect;

public class Person {
private Long id;
private String name;
public String hobby;
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
public Person() {

}
public Person(Long id, String name) {
	super();
	this.id = id;
	this.name = name;
}
private Person(Long id) {
	super();
	this.id = id;
}


}

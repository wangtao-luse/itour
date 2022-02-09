package com.itour.entiy;

public class Group {
private Long id;
private String name;
private String rq;
public Group() {
	super();
}
public Group(Long id, String name, String rq) {
	super();
	this.id = id;
	this.name = name;
	this.rq = rq;
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
public String getRq() {
	return rq;
}
public void setRq(String rq) {
	this.rq = rq;
}

}

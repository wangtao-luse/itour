package com.itour.model.test;

import java.io.Serializable;
import java.util.Date;

public class Contract implements Serializable {
private Long id;
private String hthm;
private String hydm;
private String hymc;
private Date createRq;
private String hzmc;
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getHthm() {
	return hthm;
}
public void setHthm(String hthm) {
	this.hthm = hthm;
}
public String getHydm() {
	return hydm;
}
public void setHydm(String hydm) {
	this.hydm = hydm;
}
public String getHymc() {
	return hymc;
}
public void setHymc(String hymc) {
	this.hymc = hymc;
}
public Date getCreateRq() {
	return createRq;
}
public void setCreateRq(Date createRq) {
	this.createRq = createRq;
}
public String getHzmc() {
	return hzmc;
}
public void setHzmc(String hzmc) {
	this.hzmc = hzmc;
}

}

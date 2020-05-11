package com.itour.common.vo;

public class VerifyImage {
private String srcImage;
private	String cutImage;
private	Integer xPosition;
private	Integer yPosition;

public VerifyImage(String srcImage, String cutImage, Integer xPosition, Integer yPosition) {
	super();
	this.srcImage = srcImage;
	this.cutImage = cutImage;
	this.xPosition = xPosition;
	this.yPosition = yPosition;
}

public String getSrcImage() {
	return srcImage;
}

public void setSrcImage(String srcImage) {
	this.srcImage = srcImage;
}

public String getCutImage() {
	return cutImage;
}

public void setCutImage(String cutImage) {
	this.cutImage = cutImage;
}

public Integer getxPosition() {
	return xPosition;
}

public void setxPosition(Integer xPosition) {
	this.xPosition = xPosition;
}

public Integer getyPosition() {
	return yPosition;
}

public void setyPosition(Integer yPosition) {
	this.yPosition = yPosition;
}




}

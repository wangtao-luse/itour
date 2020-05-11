package com.itour.common.vo;

public class VerifyImage {
private String srcImage;
private	String cutImage;
private	Integer XPosition;
private	Integer YPosition;

public VerifyImage(String srcImage, String cutImage, Integer xPosition, Integer yPosition) {
	super();
	this.srcImage = srcImage;
	this.cutImage = cutImage;
	XPosition = xPosition;
	YPosition = yPosition;
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

public Integer getXPosition() {
	return XPosition;
}

public void setXPosition(Integer xPosition) {
	XPosition = xPosition;
}

public Integer getYPosition() {
	return YPosition;
}

public void setYPosition(Integer yPosition) {
	YPosition = yPosition;
}


}

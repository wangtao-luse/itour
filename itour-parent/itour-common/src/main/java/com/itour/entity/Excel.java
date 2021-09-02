package com.itour.entity;

public class Excel {
/**
 * 展示名称
 */
private String showName;
/**
 * 列名
 */
private String colName;
/**
 * 格式化
 */
private String pattern;
/**
 * 开始行号
 */
private int firstRow;
/**
 * 结束行号
 */
private int lastRow;
/**
 * 开始列号
 */
private int firstCol;
/**
 * 接收列号
 */
private int lastCol;

public int getFirstRow() {
	return firstRow;
}
public void setFirstRow(int firstRow) {
	this.firstRow = firstRow;
}
public int getLastRow() {
	return lastRow;
}
public void setLastRow(int lastRow) {
	this.lastRow = lastRow;
}
public int getFirstCol() {
	return firstCol;
}
public void setFirstCol(int firstCol) {
	this.firstCol = firstCol;
}
public int getLastCol() {
	return lastCol;
}
public void setLastCol(int lastCol) {
	this.lastCol = lastCol;
}
public Excel(String showName, String colName) {
	super();
	this.showName = showName;
	this.colName = colName;
}
public Excel(String showName, String colName, String pattern) {
	super();
	this.showName = showName;
	this.colName = colName;
	this.pattern = pattern;
}
public String getShowName() {
	return showName;
}
public Excel(String showName,int firstRow, int lastRow, int firstCol, int lastCol) {
	super();
	this.showName = showName;
	this.firstRow = firstRow;
	this.lastRow = lastRow;
	this.firstCol = firstCol;
	this.lastCol = lastCol;
}
public void setShowName(String showName) {
	this.showName = showName;
}
public String getColName() {
	return colName;
}
public void setColName(String colName) {
	this.colName = colName;
}
public String getPattern() {
	return pattern;
}
public void setPattern(String pattern) {
	this.pattern = pattern;
}


}

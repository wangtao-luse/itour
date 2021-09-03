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
private Integer firstRow;
/**
 * 结束行号
 */
private Integer lastRow;
/**
 * 开始列号
 */
private Integer firstCol;
/**
 * 接收列号
 */
private Integer lastCol;
/**
 * 单元格背景颜色
 */
private Short foregroundColor;
/**
 * 无跨列情况使用
 * @param showName 显示名称
 * @param colName  显示字段
 */
public Excel(String showName, String colName) {
	super();
	this.showName = showName;
	this.colName = colName;
}
/**
 * 需要设置背景色
 * @param showName
 * @param colName
 * @param foregroundColor
 */
public Excel(String showName, String colName, Short foregroundColor) {
	super();
	this.showName = showName;
	this.colName = colName;
	this.foregroundColor = foregroundColor;
}
/**
 * 需要格式化时使用
 * @param showName 显示名称
 * @param colName  显示字段
 * @param pattern  格式化
 */
public Excel(String showName, String colName, String pattern) {
	super();
	this.showName = showName;
	this.colName = colName;
	this.pattern = pattern;
}
/**
 * 需要合并单元格的时候
 * @param showName 显示的内容
 * @param firstRow  开始行号
 * @param lastRow   终止行号
 * @param firstCol  开始列号
 * @param lastCol   终止列号
 */
public Excel(String showName,Integer firstRow, Integer lastRow, Integer firstCol, Integer lastCol,Short foregroundColor) {
	super();
	this.showName = showName;
	this.firstRow = firstRow;
	this.lastRow = lastRow;
	this.firstCol = firstCol;
	this.lastCol = lastCol;
	this.foregroundColor = foregroundColor;
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
public Integer getFirstRow() {
	return firstRow;
}
public void setFirstRow(Integer firstRow) {
	this.firstRow = firstRow;
}
public Integer getLastRow() {
	return lastRow;
}
public void setLastRow(Integer lastRow) {
	this.lastRow = lastRow;
}
public Integer getFirstCol() {
	return firstCol;
}
public void setFirstCol(Integer firstCol) {
	this.firstCol = firstCol;
}
public Integer getLastCol() {
	return lastCol;
}
public void setLastCol(Integer lastCol) {
	this.lastCol = lastCol;
}
public void setShowName(String showName) {
	this.showName = showName;
}
public String getShowName() {
	return showName;
}
public Short getForegroundColor() {
	return foregroundColor;
}
public void setForegroundColor(Short foregroundColor) {
	this.foregroundColor = foregroundColor;
}
}

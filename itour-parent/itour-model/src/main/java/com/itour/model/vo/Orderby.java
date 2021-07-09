package com.itour.model.vo;

import java.io.Serializable;

import org.springframework.stereotype.Component;
@Component
public class Orderby implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** 排序字段名称: */
	private  String  sortType; 
	
	/** 排序规则 0：降序  1：升序*/
	private  String sortRule;

	public String getSortType() {
		return sortType;
	}

	public void setSortType(String sortType) {
		this.sortType = sortType;
	}

	public String getSortRule() {
		return sortRule;
	}

	public void setSortRule(String sortRule) {
		this.sortRule = sortRule;
	}
	
}

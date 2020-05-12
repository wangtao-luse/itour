package com.itour.model.vo;

import java.io.Serializable;

public class LongRange implements Serializable{
	
	/** 
	* <p>@Fields serialVersionUID : TODO </p>
	*/ 
	private static final long serialVersionUID = 1L;

	/** 下限*/
	private Long lowerLimit;
	
	/** 上限*/
	private Long upperLimit;

	public Long getLowerLimit() {
		return lowerLimit;
	}

	public void setLowerLimit(Long lowerLimit) {
		this.lowerLimit = lowerLimit;
	}

	public Long getUpperLimit() {
		return upperLimit;
	}

	public void setUpperLimit(Long upperLimit) {
		this.upperLimit = upperLimit;
	}

	@Override
	public String toString() {
		return "LongDateRange [lowerLimit=" + lowerLimit + ", upperLimit=" + upperLimit + "]";
	}
	
	

    
   
	

	
	
	
}

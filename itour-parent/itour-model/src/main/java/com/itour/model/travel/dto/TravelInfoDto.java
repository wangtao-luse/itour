package com.itour.model.travel.dto;

import com.itour.model.travel.TravelInfo;

public class TravelInfoDto extends TravelInfo {
 private String dynamic;
 private Long time;
public String getDynamic() {
	return dynamic;
}
public void setDynamic(String dynamic) {
	this.dynamic = dynamic;
}
public Long getTime() {
	return time;
}
public void setTime(Long time) {
	this.time = time;
}
}

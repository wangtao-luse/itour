package com.itour.model.travel.dto;

import com.itour.model.travel.TravelInfo;


public class TravelInfoDto extends TravelInfo {
private String dynamic;
private Long time;
private String mold;
private String createDateFmt;
private String avatar;
private String nickname;

public String getNickname() {
	return nickname;
}
public void setNickname(String nickname) {
	this.nickname = nickname;
}
public String getAvatar() {
	return avatar;
}
public void setAvatar(String avatar) {
	this.avatar = avatar;
}
public String getCreateDateFmt() {
	return createDateFmt;
}
public void setCreateDateFmt(String createDateFmt) {
	this.createDateFmt = createDateFmt;
}
public String getMold() {
	return mold;
}
public void setMold(String mold) {
	this.mold = mold;
}
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

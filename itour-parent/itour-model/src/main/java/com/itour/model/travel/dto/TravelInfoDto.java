package com.itour.model.travel.dto;

import java.util.ArrayList;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableField;
import com.itour.model.travel.TravelInfo;
import com.itour.model.vo.Orderby;


public class TravelInfoDto extends TravelInfo {
private String dynamic;
private Long time;
private String mold;
private String createDateFmt;
private String avatar;
private String nickname;
private String oauthId;
private Long infoCount;
private Long draffCount;
private List<Orderby> orderbyList = new ArrayList<Orderby>();
private String niceStatus;
private String niceUid;
private String loginUid;

public String getLoginUid() {
	return loginUid;
}
public void setLoginUid(String loginUid) {
	this.loginUid = loginUid;
}
public String getNiceStatus() {
	return niceStatus;
}
public void setNiceStatus(String niceStatus) {
	this.niceStatus = niceStatus;
}
public String getNiceUid() {
	return niceUid;
}
public void setNiceUid(String niceUid) {
	this.niceUid = niceUid;
}
public Long getInfoCount() {
	return infoCount;
}
public void setInfoCount(Long infoCount) {
	this.infoCount = infoCount;
}
public Long getDraffCount() {
	return draffCount;
}
public void setDraffCount(Long draffCount) {
	this.draffCount = draffCount;
}
public List<Orderby> getOrderbyList() {
	return orderbyList;
}
public void setOrderbyList(List<Orderby> orderbyList) {
	this.orderbyList = orderbyList;
}
public String getOauthId() {
	return oauthId;
}
public void setOauthId(String oauthId) {
	this.oauthId = oauthId;
}
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
@Override
public String toString() {
	return "TravelInfoDto {"+ 
			"dynamic=" + dynamic +
			", time=" + time + 
			", mold=" + mold +
			", createDateFmt="+ createDateFmt +
			", avatar="	+ avatar +
			", nickname=" + nickname + 
			", oauthId=" +oauthId + 
			", infoCount="+ infoCount + 
			", draffCount=" + draffCount + 
			", orderbyList=" + orderbyList + 
			", niceStatus=" + niceStatus+ 
			", niceUid=" + niceUid + 
			", loginUid=" + loginUid + 
			"}";
}

}

package com.itour.common.req;

import java.io.Serializable;

import com.alibaba.fastjson.JSONObject;

public class RequestBody implements Serializable {
private static final long serialVersionUID = 1L;
private JSONObject content;
/**
 * 用户唯一号
 */
private String uId;
/**
 * 第三方登录唯一ID
 */
private String oauthId;
/**
 * 第三方登录唯一标识(手机：phone;邮箱：email;QQ:qq;微信：wechat;用户名：uid;微博:weibo)
 */
private String oauthType;

/**
 * 昵称
 */
private String nickname;
/**
* 头像
*/
private String avatar;
public JSONObject getContent() {
	return content;
}
public void setContent(JSONObject content) {
	this.content = content;
}
public String getuId() {
	return uId;
}
public void setuId(String uId) {
	this.uId = uId;
}
public String getOauthId() {
	return oauthId;
}
public void setOauthId(String oauthId) {
	this.oauthId = oauthId;
}
public String getOauthType() {
	return oauthType;
}
public void setOauthType(String oauthType) {
	this.oauthType = oauthType;
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


}

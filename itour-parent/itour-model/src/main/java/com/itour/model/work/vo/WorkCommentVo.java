package com.itour.model.work.vo;

import java.util.List;

import com.itour.model.dto.Orderby;
import com.itour.model.work.WorkComment;

public class WorkCommentVo extends WorkComment {
private String avatar;
private String nickname;
private List<Orderby> orderbyList;
private List<WorkCommentReplyVo> commentReplyList;

public String getAvatar() {
	return avatar;
}
public void setAvatar(String avatar) {
	this.avatar = avatar;
}
public String getNickname() {
	return nickname;
}
public void setNickname(String nickname) {
	this.nickname = nickname;
}
public List<Orderby> getOrderbyList() {
	return orderbyList;
}
public void setOrderbyList(List<Orderby> orderbyList) {
	this.orderbyList = orderbyList;
}
public List<WorkCommentReplyVo> getCommentReplyList() {
	return commentReplyList;
}
public void setCommentReplyList(List<WorkCommentReplyVo> commentReplyList) {
	this.commentReplyList = commentReplyList;
}

}

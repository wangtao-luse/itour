package com.itour.model.work.dto;

import java.util.List;

import com.itour.model.vo.Orderby;
import com.itour.model.work.Comment;

public class CommentDto extends Comment {
private String avatar;
private String nickname;
private List<Orderby> orderbyList;
private List<CommentReplyDto> commentReplyList;
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
public List<CommentReplyDto> getCommentReplyList() {
	return commentReplyList;
}
public void setCommentReplyList(List<CommentReplyDto> commentReplyList) {
	this.commentReplyList = commentReplyList;
}

}

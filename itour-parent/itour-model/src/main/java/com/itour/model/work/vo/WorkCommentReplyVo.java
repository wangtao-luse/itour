package com.itour.model.work.vo;

import java.util.List;

import com.itour.model.work.WorkCommentReply;

public class WorkCommentReplyVo extends WorkCommentReply {
	private String fromAvatar;
	private String fromNickname;
	private String toAvatar;
	private String toNickname;
	private List<Long> idList;
	public String getFromAvatar() {
		return fromAvatar;
	}
	public void setFromAvatar(String fromAvatar) {
		this.fromAvatar = fromAvatar;
	}
	public String getFromNickname() {
		return fromNickname;
	}
	public void setFromNickname(String fromNickname) {
		this.fromNickname = fromNickname;
	}
	public String getToAvatar() {
		return toAvatar;
	}
	public void setToAvatar(String toAvatar) {
		this.toAvatar = toAvatar;
	}
	public String getToNickname() {
		return toNickname;
	}
	public void setToNickname(String toNickname) {
		this.toNickname = toNickname;
	}
	public List<Long> getIdList() {
		return idList;
	}
	public void setIdList(List<Long> idList) {
		this.idList = idList;
	}
	
}

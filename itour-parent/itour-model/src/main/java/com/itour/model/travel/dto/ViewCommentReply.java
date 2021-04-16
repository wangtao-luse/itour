package com.itour.model.travel.dto;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
/**
 * <p>
 * VIEW
 * </p>
 *
 * @author wangtao
 * @since 2021-04-12
 */
@TableName("view_comment_reply")
public class ViewCommentReply extends Model<ViewCommentReply> {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableField("ID")
    private Long id;

    /**
     * 评论编号
     */
    @TableField("COMMENT_ID")
    private Long commentId;

    /**
     * 回复内容
     */
    @TableField("REPLY")
    private String reply;

    /**
     * 回复时间
     */
    @TableField("RTIME")
    private Long rtime;

    /**
     * 回复人的ID
     */
    @TableField("FROM_UID")
    private String fromUid;

    /**
     * 回复给目标人的ID
     */
    @TableField("TO_UID")
    private String toUid;


    /**
     * 状态（1:正常;0:删除）
     */
    @TableField("STATUS")
    private String status;

    /**
     * 回复人图像
     */
    @TableField("FROM_AVATAR")
    private String fromAvatar;

    /**
     * 回复人昵称
     */
    @TableField("FROM_NICKNAME")
    private String fromNickname;
    /**
     * 回复目标者图像
     */
    @TableField("TO_AVATAR")
    private String toAvatar;
    
    /**
     * 回复目标者昵称
     */
    @TableField("TO_NICKNAME")
    private String toNickname;

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

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public Long getRtime() {
        return rtime;
    }

    public void setRtime(Long rtime) {
        this.rtime = rtime;
    }

    public String getFromUid() {
        return fromUid;
    }

    public void setFromUid(String fromUid) {
        this.fromUid = fromUid;
    }

    public String getToUid() {
        return toUid;
    }

    public void setToUid(String toUid) {
        this.toUid = toUid;
    }

  

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

   

    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "ViewCommentReply{" +
        ", id=" + id +
        ", commentId=" + commentId +
        ", reply=" + reply +
        ", rtime=" + rtime +
        ", fromUid=" + fromUid +
        ", toUid=" + toUid +
        ", status=" + status +
        ", fromAvatar=" + fromAvatar +
        ", fromNickname=" + fromNickname +
        ", toAvatar=" + toAvatar +
        ", toNickname=" + toNickname +
        
        "}";
    }
}

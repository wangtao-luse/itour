package com.itour.model.travel.vo;
import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.itour.model.dto.Orderby;
/**
 * <p>
 * VIEW
 * </p>
 *
 * @author wangtao
 * @since 2021-04-12
 */
@TableName("view_travel_comment")
public class ViewTravelComment extends Model<ViewTravelComment> {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableField("ID")
    private Long id;

    /**
     * 评论内容
     */
    @TableField("COMMENT")
    private String comment;

    /**
     * 评论时间
     */
    @TableField("CTIME")
    private Long ctime;

    /**
     * 评论用户编号
     */
    @TableField("UID")
    private String uid;

    /**
     * 旅行信息编号
     */
    @TableField("TID")
    private Long tid;

  

    /**
     * 状态（0:待审核状态;1:审核通过;2:审核不通过;3:已删除）
     */
    @TableField("STATUS")
    private String status;
    /**
          * 评论点赞数
     */
    @TableField("NICE_COUNT")
    private Integer niceCount;
    /**
     * 图像
     */
    @TableField("AVATAR")
    private String avatar;

    /**
     * 昵称
     */
    @TableField("NICKNAME")
    private String nickname;
    /**
              * 评论回复
     */
    @TableField(exist = false)
    private List<ViewCommentReply> vCommentReplyList;
    @TableField(exist = false)
    private List<Orderby> orderbyList;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getCtime() {
        return ctime;
    }

    public void setCtime(Long ctime) {
        this.ctime = ctime;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Long getTid() {
        return tid;
    }

    public void setTid(Long tid) {
        this.tid = tid;
    }

    

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getNiceCount() {
		return niceCount;
	}

	public void setNiceCount(Integer niceCount) {
		this.niceCount = niceCount;
	}

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

    @Override
    protected Serializable pkVal() {
        return null;
    }

    public List<ViewCommentReply> getvCommentReplyList() {
		return vCommentReplyList;
	}

	public void setvCommentReplyList(List<ViewCommentReply> vCommentReplyList) {
		this.vCommentReplyList = vCommentReplyList;
	}

	public List<Orderby> getOrderbyList() {
		return orderbyList;
	}

	public void setOrderbyList(List<Orderby> orderbyList) {
		this.orderbyList = orderbyList;
	}

	@Override
    public String toString() {
        return "ViewTravelComment{" +
        ", id=" + id +
        ", comment=" + comment +
        ", ctime=" + ctime +
        ", uid=" + uid +
        ", tid=" + tid +
        ", status=" + status +
        ", avatar=" + avatar +
        ", nickname=" + nickname +
        "}";
    }
}

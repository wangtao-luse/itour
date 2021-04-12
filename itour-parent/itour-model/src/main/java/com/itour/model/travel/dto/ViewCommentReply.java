package com.itour.model.travel.dto;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
/**
 * <p>
 * VIEW
 * </p>
 *
 * @author wangtao
 * @since 2021-04-12
 */
public class ViewCommentReply extends Model<ViewCommentReply> {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableField("ID")
    private Integer id;

    /**
     * 评论编号
     */
    @TableField("COMMENT_ID")
    private Integer commentId;

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
     * 点赞数
     */
    @TableField("THUM")
    private Integer thum;

    /**
     * 状态（1:正常;0:删除）
     */
    @TableField("STATUS")
    private String status;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
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

    public Integer getThum() {
        return thum;
    }

    public void setThum(Integer thum) {
        this.thum = thum;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    @Override
    public String toString() {
        return "ViewCommentReply{" +
        ", id=" + id +
        ", commentId=" + commentId +
        ", reply=" + reply +
        ", rtime=" + rtime +
        ", fromUid=" + fromUid +
        ", toUid=" + toUid +
        ", thum=" + thum +
        ", status=" + status +
        ", avatar=" + avatar +
        ", nickname=" + nickname +
        "}";
    }
}

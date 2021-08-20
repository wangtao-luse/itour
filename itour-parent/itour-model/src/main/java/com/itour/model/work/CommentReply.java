package com.itour.model.work;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
/**
 * <p>
 * 
 * </p>
 *
 * @author wangtao
 * @since 2021-07-13
 */
@TableName("t_w_comment_reply")
public class CommentReply extends Model<CommentReply> {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    /**
     * 评论的ID	
     */
    @TableField("COMMENT_ID")
    private Long commentId;

    /**
     * 回复的内容	
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
     * 评论点赞数	
     */
    @TableField("NICE_COUNT")
    private Integer niceCount;

    /**
     * 状态	20:待审核状态;30:审核通过;40:审核不通过; 50:已删除
     */
    @TableField("STATUS")
    private String status;

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

    public Integer getNiceCount() {
        return niceCount;
    }

    public void setNiceCount(Integer niceCount) {
        this.niceCount = niceCount;
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
        return "CommentReply{" +
        ", id=" + id +
        ", commentId=" + commentId +
        ", reply=" + reply +
        ", rtime=" + rtime +
        ", fromUid=" + fromUid +
        ", toUid=" + toUid +
        ", niceCount=" + niceCount +
        ", status=" + status +
        "}";
    }
}

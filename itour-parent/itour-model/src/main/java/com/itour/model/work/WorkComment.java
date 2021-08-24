package com.itour.model.work;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
/**
 * <p>
 * 
 * </p>
 *
 * @author wangtao
 * @since 2021-07-13
 */
@TableName("t_w_work_comment")
public class WorkComment extends Model<WorkComment> {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId(value = "ID", type = IdType.AUTO)
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
     * 用户编号	用户ID
     */
    @TableField("UID")
    private String uid;

    /**
     * 文章信息编号	博客的编号
     */
    @TableField("TID")
    private Long tid;

    /**
     * 评论点赞数
     */
    @TableField("NICE_COUNT")
    private Integer niceCount;

    /**
     * 状态(20:待审核状态;30:审核通过;40:审核不通过; 50:已删除)
     */
    @TableField("STATUS")
    private String status;
    
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
        return "Comment{" +
        ", id=" + id +
        ", comment=" + comment +
        ", ctime=" + ctime +
        ", uid=" + uid +
        ", tid=" + tid +
        ", niceCount=" + niceCount +
        ", status=" + status +
        "}";
    }
}

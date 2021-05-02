package com.itour.model.travel;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
/**
 * <p>
 * 旅行信息评论表
 * </p>
 *
 * @author wangtao
 * @since 2021-04-09
 */
@TableName("t_t_travel_comment")
public class TravelComment extends Model<TravelComment> {

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
     * 用户编号
     */
    @TableField("UID")
    private String uid;

    /**
     * 旅行信息编号
     */
    @TableField("TID")
    private Long tid;

    /**
     * 
    /**
     * 状态（20:待审核状态;30:审核通过;40:审核不通过;50:已删除）
     */
    @TableField("STATUS")
    private String status;
    /**
     	* 评论点赞数
     */
    @TableField("NICE_COUNT")
    private Integer niceCount;

    public Integer getNiceCount() {
		return niceCount;
	}

	public void setNiceCount(Integer niceCount) {
		this.niceCount = niceCount;
	}

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

    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "TravelComment{" +
        ", id=" + id +
        ", comment=" + comment +
        ", ctime=" + ctime +
        ", uid=" + uid +
        ", tid=" + tid +
        ", status=" + status +
        "}";
    }
}

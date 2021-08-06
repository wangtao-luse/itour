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
@TableName("t_w_reply_like")
public class ReplyLike extends Model<ReplyLike> {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    /**
     * 攻略评论回复表编号
     */
    @TableField("RID")
    private Long rid;

    /**
     * 用户编号
     */
    @TableField("UID")
    private Long uid;

    /**
     * 点赞时间
     */
    @TableField("CREATEDATE")
    private Long createdate;

    /**
     * 状态（0:取消;1:有效）
     */
    @TableField("STATUS")
    private String status;

    
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRid() {
		return rid;
	}

	public void setRid(Long rid) {
		this.rid = rid;
	}

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public Long getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Long createdate) {
        this.createdate = createdate;
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
        return "ReplyLike{" +
        ", id=" + id +
        ", rid=" + rid +
        ", uid=" + uid +
        ", createdate=" + createdate +
        ", status=" + status +
        "}";
    }
}

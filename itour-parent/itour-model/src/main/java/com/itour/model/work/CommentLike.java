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
 * @since 2021-07-09
 */
@TableName("t_w_comment_like")
public class CommentLike extends Model<CommentLike> {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    /**
     * 博文评论表编号
     */
    @TableField("CID")
    private Integer cid;

    /**
     * 用户编号
     */
    @TableField("UID")
    private String uid;

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

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
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
        return "CommentLike{" +
        ", id=" + id +
        ", cid=" + cid +
        ", uid=" + uid +
        ", createdate=" + createdate +
        ", status=" + status +
        "}";
    }
}

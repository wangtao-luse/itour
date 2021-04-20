package com.itour.model.travel;
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
 * @since 2021-04-20
 */
@TableName("t_t_travel_comment_nice")
public class CommentNice extends Model<CommentNice> {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    /**
     * 攻略评论表编号
     */
    @TableField("CID")
    private Long cid;

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

    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
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
        return "CommentNice{" +
        ", id=" + id +
        ", cid=" + cid +
        ", uid=" + uid +
        ", createdate=" + createdate +
        ", status=" + status +
        "}";
    }
}

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
 * @since 2020-07-29
 */
@TableName("t_t_travel_comment")
public class TravelComment extends Model<TravelComment> {

    private static final long serialVersionUID = 1L;

    /**
     * 编号	
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    /**
     * 评论内容
     */
    @TableField("COMMENT")
    private String comment;

    /**
     * 评论时间
     */
    @TableField("CTIME")
    private Integer ctime;

    /**
     * 用户编号
     */
    @TableField("UID")
    private Integer uid;

    /**
     * 旅行信息编号
     */
    @TableField("TID")
    private Integer tid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getCtime() {
        return ctime;
    }

    public void setCtime(Integer ctime) {
        this.ctime = ctime;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
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
        "}";
    }
}

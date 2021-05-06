package com.itour.model.travel.dto;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
/**
 * <p>
 * VIEW
 * </p>
 *
 * @author wangtao
 * @since 2021-04-12
 */
@TableName("view_travel_tag")
public class ViewTravelTag extends Model<ViewTravelTag> {

    private static final long serialVersionUID = 1L;

    /**
     * 编号(主键)
     */
    @TableField("ID")
    private Long id;

    /**
     * 攻略编号
     */
    @TableField("TID")
    private Long tid;

    /**
     * 标签编号
     */
    @TableField("TAG_ID")
    private Long tagId;

    /**
     * 标签名称
     */
    @TableField("TAG")
    private String tag;

    /**
     * 标签创建者
     */
    @TableField("UID")
    private String uid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTid() {
        return tid;
    }

    public void setTid(Long tid) {
        this.tid = tid;
    }

    public Long getTagId() {
        return tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "ViewTravelTag{" +
        ", id=" + id +
        ", tid=" + tid +
        ", tagId=" + tagId +
        ", tag=" + tag +
        ", uid=" + uid +
        "}";
    }
}

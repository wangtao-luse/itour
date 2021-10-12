package com.itour.model.travel.vo;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
/**
 * <p>
 * VIEW
 * </p>
 *
 * @author wangtao
 * @since 2021-05-06
 */
@TableName("view_travel_column")
public class ViewTravelColumn extends Model<ViewTravelColumn> {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableField("ID")
    private Long id;

    /**
     * 专栏名称
     */
    @TableField("`COLUMN`")
    private String column;

    /**
     * 用户ID
     */
    @TableField("UID")
    private String uid;

    /**
     * 创建日期
     */
    @TableField("CREATEDATE")
    private Long createdate;

    /**
     * 旅行信息编号
     */
    @TableField("TID")
    private Long tid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
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

    public Long getTid() {
        return tid;
    }

    public void setTid(Long tid) {
        this.tid = tid;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "ViewTravelColumn{" +
        ", id=" + id +
        ", column=" + column +
        ", uid=" + uid +
        ", createdate=" + createdate +
        ", tid=" + tid +
        "}";
    }
}

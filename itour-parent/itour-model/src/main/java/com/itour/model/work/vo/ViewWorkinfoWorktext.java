package com.itour.model.work.vo;
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
 * @since 2021-08-13
 */
@TableName("view_workInfo_worktext")
public class ViewWorkinfoWorktext extends Model<ViewWorkinfoWorktext> {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableField("ID")
    private Long id;

    /**
     * 状态(10：草稿；20：待审核;30:审核通过；40：审核不通过；50：已删除)
     */
    @TableField("STATUS")
    private String status;

    /**
     * 工作博客文本
     */
    @TableField("WCONTENT")
    private String wcontent;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getWcontent() {
        return wcontent;
    }

    public void setWcontent(String wcontent) {
        this.wcontent = wcontent;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "ViewWorkinfoWorktext{" +
        ", id=" + id +
        ", status=" + status +
        ", wcontent=" + wcontent +
        "}";
    }
}

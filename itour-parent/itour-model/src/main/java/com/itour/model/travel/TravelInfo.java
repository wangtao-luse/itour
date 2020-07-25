package com.itour.model.travel;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
/**
 * <p>
 * 旅行信息表
 * </p>
 *
 * @author wangtao
 * @since 2020-07-25
 */
@TableName("t_t_travel_info")
public class TravelInfo extends Model<TravelInfo> {

    private static final long serialVersionUID = 1L;

    /**
     * 编号(主键)
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    /**
     * 标题
     */
    @TableField("TITLE")
    private String title;

    /**
     * 简介
     */
    @TableField("BRIEF")
    private String brief;

    /**
     * 类型(1:旅行攻略；2.周末攻略3.景点介绍;(来源于博客类型表)
     */
    @TableField("TYPE")
    private String type;

    /**
     * 所属专栏	来源旅行博客专栏表的ID
     */
    @TableField("COLUMN")
    private Integer column;

    /**
     * 城市代码
     */
    @TableField("CODE")
    private String code;

    /**
     * 发布时间
     */
    @TableField("PUBLISHTIME")
    private Integer publishtime;

    /**
     * 最后发布时间
     */
    @TableField("UPDATETIME")
    private Integer updatetime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getColumn() {
        return column;
    }

    public void setColumn(Integer column) {
        this.column = column;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getPublishtime() {
        return publishtime;
    }

    public void setPublishtime(Integer publishtime) {
        this.publishtime = publishtime;
    }

    public Integer getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Integer updatetime) {
        this.updatetime = updatetime;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "TravelInfo{" +
        ", id=" + id +
        ", title=" + title +
        ", brief=" + brief +
        ", type=" + type +
        ", column=" + column +
        ", code=" + code +
        ", publishtime=" + publishtime +
        ", updatetime=" + updatetime +
        "}";
    }
}

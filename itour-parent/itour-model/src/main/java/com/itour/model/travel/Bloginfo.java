package com.itour.model.travel;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
/**
 * <p>
 * 旅行博客信息表
 * </p>
 *
 * @author wangtao
 * @since 2020-05-06
 */
@TableName("t_b_bloginfo")
public class Bloginfo extends Model<Bloginfo> {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId("ID")
    private Integer id;

    /**
     * 博客标题
     */
    @TableField("BLOGTITLE")
    private String blogtitle;

    /**
     * 博客简介
     */
    @TableField("BLOGBRIEF")
    private String blogbrief;

    /**
     * 旅行安排
     */
    @TableField("BLOGPLAN")
    private String blogplan;

    /**
     * 行程解剖
     */
    @TableField("BLOGEXPLAIN")
    private String blogexplain;

    @TableField("BLOGTYPE")
    private String blogtype;

    /**
     * 所属专栏
     */
    @TableField("BLOGCOLUMN")
    private Integer blogcolumn;

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

    public String getBlogtitle() {
        return blogtitle;
    }

    public void setBlogtitle(String blogtitle) {
        this.blogtitle = blogtitle;
    }

    public String getBlogbrief() {
        return blogbrief;
    }

    public void setBlogbrief(String blogbrief) {
        this.blogbrief = blogbrief;
    }

    public String getBlogplan() {
        return blogplan;
    }

    public void setBlogplan(String blogplan) {
        this.blogplan = blogplan;
    }

    public String getBlogexplain() {
        return blogexplain;
    }

    public void setBlogexplain(String blogexplain) {
        this.blogexplain = blogexplain;
    }

    public String getBlogtype() {
        return blogtype;
    }

    public void setBlogtype(String blogtype) {
        this.blogtype = blogtype;
    }

    public Integer getBlogcolumn() {
        return blogcolumn;
    }

    public void setBlogcolumn(Integer blogcolumn) {
        this.blogcolumn = blogcolumn;
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
        return "Bloginfo{" +
        ", id=" + id +
        ", blogtitle=" + blogtitle +
        ", blogbrief=" + blogbrief +
        ", blogplan=" + blogplan +
        ", blogexplain=" + blogexplain +
        ", blogtype=" + blogtype +
        ", blogcolumn=" + blogcolumn +
        ", code=" + code +
        ", publishtime=" + publishtime +
        ", updatetime=" + updatetime +
        "}";
    }
}

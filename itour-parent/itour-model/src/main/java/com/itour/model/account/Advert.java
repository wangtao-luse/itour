package com.itour.model.account;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
/**
 * <p>
 * 广告信息表
 * </p>
 *
 * @author wangtao
 * @since 2020-08-21
 */
@TableName("t_a_advert")
public class Advert extends Model<Advert> {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    /**
     * 广告的logo
     */
    @TableField("LOGO")
    private String logo;

    /**
     * 品牌名称
     */
    @TableField("BRAND")
    private String brand;

    /**
     * 标题
     */
    @TableField("TITLE")
    private String title;

    /**
     * 广告图片
     */
    @TableField("ADVERIMG")
    private String adverimg;

    /**
     * 广告简介
     */
    @TableField("SUMMARY")
    private String summary;

    /**
     * 广告详情地址
     */
    @TableField("URL")
    private String url;

    /**
     * 创建时间
     */
    @TableField("CREATEDATE")
    private Integer createdate;

    /**
     * 投放结束时间
     */
    @TableField("TERM")
    private Integer term;

    /**
     * 状态
     */
    @TableField("STATUS")
    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
    
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAdverimg() {
        return adverimg;
    }

    public void setAdverimg(String adverimg) {
        this.adverimg = adverimg;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Integer createdate) {
        this.createdate = createdate;
    }

    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "Advert{" +
        ", id=" + id +
        ", logo=" + logo +
        ", brand=" + brand +
        ", title=" + title +
        ", adverimg=" + adverimg +
        ", summary=" + summary +
        ", url=" + url +
        ", createdate=" + createdate +
        ", term=" + term +
        ", status=" + status +
        "}";
    }
}

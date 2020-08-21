package com.itour.model.dictionary;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
/**
 * <p>
 * 网站推荐表
 * </p>
 *
 * @author wangtao
 * @since 2020-08-21
 */
@TableName("t_r_website_recommend")
public class WebsiteRecommend extends Model<WebsiteRecommend> {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    /**
     * 网站名称
     */
    @TableField("NAME")
    private String name;

    /**
     * 网站中文名称
     */
    @TableField("NAME_EN")
    private String nameEn;

    /**
     * 网址
     */
    @TableField("URL")
    private String url;

    /**
     * 特色
     */
    @TableField("NICE")
    private String nice;

    /**
     * 优点
     */
    @TableField("ADVANTAGE")
    private String advantage;

    /**
     * 缺点
     */
    @TableField("SHORTCOMING")
    private String shortcoming;

    /**
     * 网站类型
     */
    @TableField("TYPE")
    private String type;

    /**
     * 是否收费
     */
    @TableField("FEE")
    private String fee;

    /**
     * 所属行业
     */
    @TableField("PROFESSION")
    private String profession;

    /**
     * 所属工作
     */
    @TableField("JOB")
    private String job;

    /**
     * 推荐指数
     */
    @TableField("INDEX")
    private Float index;

    /**
     * 录入时间
     */
    @TableField("CREATEDATE")
    private Integer createdate;

    /**
     * 录入人
     */
    @TableField("UID")
    private Integer uid;

    /**
     * 状态(0:已删除,1:正常)
     */
    @TableField("STATUS")
    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNice() {
        return nice;
    }

    public void setNice(String nice) {
        this.nice = nice;
    }

    public String getAdvantage() {
        return advantage;
    }

    public void setAdvantage(String advantage) {
        this.advantage = advantage;
    }

    public String getShortcoming() {
        return shortcoming;
    }

    public void setShortcoming(String shortcoming) {
        this.shortcoming = shortcoming;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Float getIndex() {
        return index;
    }

    public void setIndex(Float index) {
        this.index = index;
    }

    public Integer getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Integer createdate) {
        this.createdate = createdate;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
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
        return "WebsiteRecommend{" +
        ", id=" + id +
        ", name=" + name +
        ", nameEn=" + nameEn +
        ", url=" + url +
        ", nice=" + nice +
        ", advantage=" + advantage +
        ", shortcoming=" + shortcoming +
        ", type=" + type +
        ", fee=" + fee +
        ", profession=" + profession +
        ", job=" + job +
        ", index=" + index +
        ", createdate=" + createdate +
        ", uid=" + uid +
        ", status=" + status +
        "}";
    }
}

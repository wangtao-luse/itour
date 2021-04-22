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
 * @since 2021-02-12
 */
@TableName("t_t_travel_info")
public class TravelInfo extends Model<TravelInfo> {

    private static final long serialVersionUID = 1L;

    /**
     * 编号(主键)
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    /**
     * 标题
     */
    @TableField("TITLE")
    private String title;

    /**
     * 简介
     */
    @TableField("SUMMARY")
    private String summary;

    /**
     * 简介图片视频地址
     */
    @TableField("URL")
    private String url;

    /**
     * 类型(1:旅行攻略;2.周末攻略;3.景点介绍;4.vlog;5.广告)
     */
    @TableField("TYPE")
    private String type;

    /**
     * 文章类型（1：原创;2:非原创）
     */
    @TableField("ARTICLE_TYPE")
    private String articleType;

  
    /**
     * 用户唯一号
     */
    @TableField("UID")
    private String uid;

    /**
     * 城市代码
     */
    @TableField("CODE")
    private String code;

    /**
     * 阅读数
     */
    @TableField("READ_COUNT")
    private Integer readCount;

    /**
     * 评论数
     */
    @TableField("COMMENT_COUNT")
    private Integer commentCount;

    /**
     * 点赞数
     */
    @TableField("NICE_COUNT")
    private Integer niceCount;

    /**
     * 浏览量
     */
    @TableField("PV")
    private Integer pv;
    /**
     * 状态（0：待审核;1:审核通过;2:审核不通过;3:已删除）
     */
    @TableField("STATUS")
    private String status;
    /**
     * 发布时间
     */
    @TableField("PUBLISHTIME")
    private Long publishtime;

    /**
     * 最后发布时间
     */
    @TableField("UPDATETIME")
    private Long updatetime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getArticleType() {
        return articleType;
    }

    public void setArticleType(String articleType) {
        this.articleType = articleType;
    }

   

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getReadCount() {
        return readCount;
    }

    public void setReadCount(Integer readCount) {
        this.readCount = readCount;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public Integer getNiceCount() {
        return niceCount;
    }

    public void setNiceCount(Integer niceCount) {
        this.niceCount = niceCount;
    }

    public Integer getPv() {
        return pv;
    }

    public void setPv(Integer pv) {
        this.pv = pv;
    }
    
    public Long getPublishtime() {
        return publishtime;
    }

    public void setPublishtime(Long publishtime) {
        this.publishtime = publishtime;
    }

    public Long getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Long updatetime) {
        this.updatetime = updatetime;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }

    public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
    public String toString() {
        return "TravelInfo{" +
        ", id=" + id +
        ", title=" + title +
        ", summary=" + summary +
        ", url=" + url +
        ", type=" + type +
        ", articleType=" + articleType +
        ", uid=" + uid +
        ", code=" + code +
        ", readCount=" + readCount +
        ", commentCount=" + commentCount +
        ", niceCount=" + niceCount +
        ", pv=" + pv +
        ", status=" + status +        
        ", publishtime=" + publishtime +
        ", updatetime=" + updatetime +
        "}";
    }
}

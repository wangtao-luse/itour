package com.itour.model.work;
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
 * @since 2021-07-13
 */
@TableName("t_w_work_info")
public class WorkInfo extends Model<WorkInfo> {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    /**
     * 标题
     */
    @TableField("TITLE")
    private String title;

    /**
     * 博客封面	视频或图片地址
     */
    @TableField("URL")
    private String url;

    /**
     * 简介
     */
    @TableField("SUMMARY")
    private String summary;

    /**
     * 类型	(1：原创；0非原创)
     */
    @TableField("ARTICLE_TYPE")
    private String articleType;

    /**
     * 创建日期
     */
    @TableField("PUBLISHTIME")
    private Long publishtime;

    /**
     * 最后修改日期
     */
    @TableField("UPDATETIME")
    private Long updatetime;

   /**
     * 发布形式	(1:仅自己可见,2:公开)
     */
    @TableField("MODALITY")
    private String modality;

    /**
     * 用户唯一号
     */
    @TableField("UID")
    private String uid;

    /**
     * 阅读数	默认0 冗余字段
     */
    @TableField("READ_COUNT")
    private Integer readCount;

    /**
     * 评论数	默认0冗余字段
     */
    @TableField("COMMENT_COUNT")
    private Integer commentCount;

    /**
     * 点赞数	默认0冗余字段
     */
    @TableField("LIKE_COUNT")
    private Integer likeCount;

    /**
     * 浏览量 默认0冗余字段
     */
    @TableField("PV")
    private Integer pv;
    
    @TableField("COLLECT_COUNT")
    private Integer collectCount;
    

    /**
     * 状态(10：草稿；20：待审核;30:审核通过；40：审核不通过；50：已删除)
     */
    @TableField("STATUS")
    private String status;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getArticleType() {
        return articleType;
    }

    public void setArticleType(String articleType) {
        this.articleType = articleType;
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

	public String getModality() {
        return modality;
    }

    public void setModality(String modality) {
        this.modality = modality;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
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

  

    public Integer getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(Integer likeCount) {
		this.likeCount = likeCount;
	}

	public Integer getPv() {
        return pv;
    }

    public void setPv(Integer pv) {
        this.pv = pv;
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

    public Integer getCollectCount() {
		return collectCount;
	}

	public void setCollectCount(Integer collectCount) {
		this.collectCount = collectCount;
	}

	@Override
    public String toString() {
        return "WorkInfo{" +
        ", id=" + id +
        ", title=" + title +
        ", url=" + url +
        ", summary=" + summary +
        ", articleType=" + articleType +
        ", publishtime=" + publishtime +
        ", updatetime=" + updatetime +
        ", modality=" + modality +
        ", uid=" + uid +
        ", readCount=" + readCount +
        ", commentCount=" + commentCount +
        ", likeCount=" + likeCount +
        ", pv=" + pv +
        ", collectCount=" + collectCount +
        ", status=" + status +
        "}";
    }
}

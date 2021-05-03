package com.itour.model.travel.dto;
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
 * @since 2020-08-03
 */
@TableName("view_travelInfo_oauth")
public class ViewTravelinfoOauth extends Model<ViewTravelinfoOauth> {

    private static final long serialVersionUID = 1L;

    /**
     * 编号(主键)
     */
    @TableField("ID")
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
     * 状态（10：草稿；20：待审核;30:审核通过；40：审核不通过；50：已删除）
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

    /**
     * 昵称
     */
    @TableField("NICKNAME")
    private String nickname;
    /**
     * 作者图像
     */
    @TableField("AVATAR")
    private String avatar;

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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
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

    @Override
    public String toString() {
        return "ViewTravelinfoOauth{" +
        ", id=" + id +
        ", title=" + title +
        ", summary=" + summary +
        ", url=" + url +
        ", type=" + type +
        ", uid=" + uid +
        ", code=" + code +
        ", readCount=" + readCount +
        ", commentCount=" + commentCount +
        ", niceCount=" + niceCount +
        ", pv=" + pv +
        ", publishtime=" + publishtime +
        ", updatetime=" + updatetime +
        ", nickname=" + nickname +
        "}";
    }
}

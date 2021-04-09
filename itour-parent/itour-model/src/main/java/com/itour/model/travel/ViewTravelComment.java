package com.itour.model.travel;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
/**
 * <p>
 * VIEW
 * </p>
 *
 * @author wangtao
 * @since 2021-04-09
 */
public class ViewTravelComment extends Model<ViewTravelComment> {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableField("ID")
    private Integer id;

    /**
     * 评论内容
     */
    @TableField("COMMENT")
    private String comment;

    /**
     * 评论时间
     */
    @TableField("CTIME")
    private Long ctime;

    /**
     * 用户编号
     */
    @TableField("UID")
    private String uid;

    /**
     * 旅行信息编号
     */
    @TableField("TID")
    private Integer tid;

    /**
     * 点赞数
     */
    @TableField("THUM")
    private Integer thum;

    /**
     * 状态（1:正常;0:删除）
     */
    @TableField("STATUS")
    private String status;

    /**
     * 图像
     */
    @TableField("AVATAR")
    private String avatar;

    /**
     * 昵称
     */
    @TableField("NICKNAME")
    private String nickname;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getCtime() {
        return ctime;
    }

    public void setCtime(Long ctime) {
        this.ctime = ctime;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public Integer getThum() {
        return thum;
    }

    public void setThum(Integer thum) {
        this.thum = thum;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "ViewTravelComment{" +
        ", id=" + id +
        ", comment=" + comment +
        ", ctime=" + ctime +
        ", uid=" + uid +
        ", tid=" + tid +
        ", thum=" + thum +
        ", status=" + status +
        ", avatar=" + avatar +
        ", nickname=" + nickname +
        "}";
    }
}

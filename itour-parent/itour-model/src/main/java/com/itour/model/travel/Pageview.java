package com.itour.model.travel;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
/**
 * <p>
 * 旅行信息浏览数表
 * </p>
 *
 * @author wangtao
 * @since 2020-09-14
 */
@TableName("t_t_pageview")
public class Pageview extends Model<Pageview> {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableField("ID")
    private Integer id;

    /**
     * 旅行信息编号
     */
    @TableField("TID")
    private Integer tid;

    /**
     * 用户
     */
    @TableField("UID")
    private String uid;

    /**
     * 浏览量
     */
    @TableField("PAGE_VEW")
    private Integer pageVew;

    /**
     * 独立访客
     */
    @TableField("UNIQUE_ISITOR")
    private Integer uniqueIsitor;

    /**
     * 独立IP
     */
    @TableField("IP")
    private Integer ip;

    /**
     * 访问次数
     */
    @TableField("VISIT_IEW")
    private Integer visitIew;

    /**
     * 视频播放量
     */
    @TableField("VIDEO_VIEW")
    private Integer videoView;

    /**
     * 浏览日期
     */
    @TableField("CREATEDATE")
    private Long createdate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Integer getPageVew() {
        return pageVew;
    }

    public void setPageVew(Integer pageVew) {
        this.pageVew = pageVew;
    }

    public Integer getUniqueIsitor() {
        return uniqueIsitor;
    }

    public void setUniqueIsitor(Integer uniqueIsitor) {
        this.uniqueIsitor = uniqueIsitor;
    }

    public Integer getIp() {
        return ip;
    }

    public void setIp(Integer ip) {
        this.ip = ip;
    }

    public Integer getVisitIew() {
        return visitIew;
    }

    public void setVisitIew(Integer visitIew) {
        this.visitIew = visitIew;
    }

    public Integer getVideoView() {
        return videoView;
    }

    public void setVideoView(Integer videoView) {
        this.videoView = videoView;
    }

    public Long getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Long createdate) {
        this.createdate = createdate;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "Pageview{" +
        ", id=" + id +
        ", tid=" + tid +
        ", uid=" + uid +
        ", pageVew=" + pageVew +
        ", uniqueIsitor=" + uniqueIsitor +
        ", ip=" + ip +
        ", visitIew=" + visitIew +
        ", videoView=" + videoView +
        ", createdate=" + createdate +
        "}";
    }
}

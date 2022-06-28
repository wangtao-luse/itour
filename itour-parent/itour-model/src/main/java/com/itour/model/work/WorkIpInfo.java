package com.itour.model.work;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
/**
 * <p>
 * 日志独立IP访问记录
 * </p>
 *
 * @author wangtao
 * @since 2022-06-28
 */
@TableName("t_w_work_ip_info")
public class WorkIpInfo extends Model<WorkIpInfo> {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    /**
     * 访问的IP地址
     */
    @TableField("IP_ADDR")
    private String ipAddr;

    /**
     * IP访问时间
     */
    @TableField("IP_VISIT_TIME")
    private Long ipVisitTime;

    /**
     * 文章Id
     */
    @TableField("TID")
    private Long tid;

    /**
     * 创建时间
     */
    @TableField("CREATE_DATE")
    private Long createDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIpAddr() {
        return ipAddr;
    }

    public void setIpAddr(String ipAddr) {
        this.ipAddr = ipAddr;
    }

    public Long getIpVisitTime() {
        return ipVisitTime;
    }

    public void setIpVisitTime(Long ipVisitTime) {
        this.ipVisitTime = ipVisitTime;
    }

    public Long getTid() {
        return tid;
    }

    public void setTid(Long tid) {
        this.tid = tid;
    }

    public Long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Long createDate) {
        this.createDate = createDate;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "WorkIpInfo{" +
        ", id=" + id +
        ", ipAddr=" + ipAddr +
        ", ipVisitTime=" + ipVisitTime +
        ", tid=" + tid +
        ", createDate=" + createDate +
        "}";
    }
}

package com.itour.model.dictionary;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
/**
 * <p>
 * 网站访独立IP访问记录
 * </p>
 *
 * @author wangtao
 * @since 2022-06-24
 */
@TableName("t_d_website_ip_info")
public class WebsiteIpInfo extends Model<WebsiteIpInfo> {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    /**
     * 访问的IP地址
     */
    @TableField("IP_ADDR")
    private String ipAddr;

    /**
     * IP当天第一次访问的时间
     */
    @TableField("IP_VISIT_TIME")
    private Long ipVisitTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "WebsiteIpInfo{" +
        ", id=" + id +
        ", ipAddr=" + ipAddr +
        ", ipVisitTime=" + ipVisitTime +
        "}";
    }
}

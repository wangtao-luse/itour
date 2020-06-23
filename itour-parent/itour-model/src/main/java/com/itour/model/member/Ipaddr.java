package com.itour.model.member;
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
 * @since 2020-06-23
 */
@TableName("t_m_ipaddr")
public class Ipaddr extends Model<Ipaddr> {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    /**
     * IP地址
     */
    @TableField("IP")
    private String ip;

    /**
     * 网络服务供应商
     */
    @TableField("ISP")
    private String isp;

    /**
     * 国家
     */
    @TableField("COUNTRY")
    private String country;

    /**
     * 网络域名
     */
    @TableField("COUNTRYCODE")
    private String countrycode;

    /**
     * 地区（省）
     */
    @TableField("REGION")
    private String region;

    /**
     * 地区拼音缩写
     */
    @TableField("REGIONNAME")
    private String regionname;

    /**
     * 城市
     */
    @TableField("CITY")
    private String city;

    /**
     * 区
     */
    @TableField("AREA")
    private String area;

    /**
     * 纬度
     */
    @TableField("LATITUDE")
    private String latitude;

    /**
     * 经度
     */
    @TableField("LONGITUDE")
    private String longitude;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getIsp() {
        return isp;
    }

    public void setIsp(String isp) {
        this.isp = isp;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountrycode() {
        return countrycode;
    }

    public void setCountrycode(String countrycode) {
        this.countrycode = countrycode;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getRegionname() {
        return regionname;
    }

    public void setRegionname(String regionname) {
        this.regionname = regionname;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "Ipaddr{" +
        ", id=" + id +
        ", ip=" + ip +
        ", isp=" + isp +
        ", country=" + country +
        ", countrycode=" + countrycode +
        ", region=" + region +
        ", regionname=" + regionname +
        ", city=" + city +
        ", area=" + area +
        ", latitude=" + latitude +
        ", longitude=" + longitude +
        "}";
    }
}

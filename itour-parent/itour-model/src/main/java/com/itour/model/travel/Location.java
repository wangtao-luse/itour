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
 * @since 2020-08-04
 */
@TableName("t_t_location")
public class Location extends Model<Location> {

    private static final long serialVersionUID = 1L;

    /**
     * 编号	
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    /**
     * 国家代码
     */
    @TableField("COUNTRY_CODE")
    private String countryCode;

    /**
     * 国家名称
     */
    @TableField("COUNTRY")
    private String country;

    /**
     * 国家名称对应的英文
     */
    @TableField("COUNTRY_EN")
    private String countryEn;

    /**
     * 城市代码
     */
    @TableField("CODE")
    private String code;

    /**
     * 城市名称
     */
    @TableField("CITY_EN")
    private String cityEn;

    /**
     * 城市中文名称
     */
    @TableField("CITY")
    private String city;

    /**
     * 城市标志图片
     */
    @TableField("SIGNIMG")
    private String signimg;
    /**
     * 地区父id
     */
    @TableField("PID")
    private Long pid;
    /**
     * 地区级别
     */
    @TableField("LEVEL")
    private String level;
    /**
     * 位置图片
     */
    @TableField("LOCIMG")
    private String locimg;

    /**
     * 行政区域划分图片
     */
    @TableField("DISTRICTIMG")
    private String districtimg;

    /**
     * 行政区域划分介绍
     */
    @TableField("DISTRICTDESC")
    private String districtdesc;

    /**
     * 地理位置
     */
    @TableField("LOCATION")
    private String location;

    /**
     * 简短介绍
     */
    @TableField("INTROUDUCE")
    private String introuduce;

    /**
     * 天气简介
     */
    @TableField("WEATHER")
    private String weather;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getCountryEn() {
        return countryEn;
    }

    public void setCountryEn(String countryEn) {
        this.countryEn = countryEn;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCityEn() {
        return cityEn;
    }

    public void setCityEn(String cityEn) {
        this.cityEn = cityEn;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSignimg() {
        return signimg;
    }

    public void setSignimg(String signimg) {
        this.signimg = signimg;
    }

    public String getLocimg() {
        return locimg;
    }

    public void setLocimg(String locimg) {
        this.locimg = locimg;
    }

    public String getDistrictimg() {
        return districtimg;
    }

    public void setDistrictimg(String districtimg) {
        this.districtimg = districtimg;
    }

    public String getDistrictdesc() {
        return districtdesc;
    }

    public void setDistrictdesc(String districtdesc) {
        this.districtdesc = districtdesc;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getIntrouduce() {
        return introuduce;
    }

    public void setIntrouduce(String introuduce) {
        this.introuduce = introuduce;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	@Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "Location{" +
        ", id=" + id +
        ", countryCode=" + countryCode +
        ", country=" + country +
        ", countryEn=" + countryEn +
        ", code=" + code +
        ", cityEn=" + cityEn +
        ", city=" + city +
        ", signimg=" + signimg +
        ", locimg=" + locimg +
        ", districtimg=" + districtimg +
        ", districtdesc=" + districtdesc +
        ", location=" + location +
        ", introuduce=" + introuduce +
        ", weather=" + weather +
        "}";
    }
}

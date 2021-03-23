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
 * @since 2021-03-20
 */
@TableName("t_t_region")
public class Region extends Model<Region> {

    private static final long serialVersionUID = 1L;

    /**
     * 地区主键编号
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    /**
     * 地区名称
     */
    @TableField("REGION_NAME")
    private String regionName;

    /**
     * 行政地区编号
     */
    @TableField("REGION_CODE")
    private String regionCode;

    /**
     * 上级主键
     */
    @TableField("REGION_PARENT_ID")
    private String regionParentId;

    /**
     * 地区级别 1-省、自治区、直辖市 2-地级市、地区、自治州、盟 3-市辖区、县级市、县
     */
    @TableField("REGION_LEVEL")
    private String regionLevel;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public String getRegionParentId() {
        return regionParentId;
    }

    public void setRegionParentId(String regionParentId) {
        this.regionParentId = regionParentId;
    }

    public String getRegionLevel() {
        return regionLevel;
    }

    public void setRegionLevel(String regionLevel) {
        this.regionLevel = regionLevel;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "Region{" +
        ", id=" + id +
        ", regionName=" + regionName +
        ", regionCode=" + regionCode +
        ", regionParentId=" + regionParentId +
        ", regionLevel=" + regionLevel +
        "}";
    }
}

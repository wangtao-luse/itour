package com.itour.model.travel;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
/**
 * <p>
 * 交通信息表
 * </p>
 *
 * @author wangtao
 * @since 2020-07-25
 */
@TableName("t_t_transportation_info")
public class TransportationInfo extends Model<TransportationInfo> {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    /**
     * 城市代码
     */
    @TableField("CODE")
    private String code;

    /**
     * 城市名称
     */
    @TableField("CITY")
    private String city;

    /**
     * 交通类型
     */
    @TableField("TTYPE")
    private String ttype;

    /**
     * 交通状况
     */
    @TableField("TSITUATION")
    private String tsituation;

    /**
     * 推荐路线
     */
    @TableField("ROUTE")
    private String route;

    /**
     * 交通技巧
     */
    @TableField("SKILL")
    private String skill;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTtype() {
        return ttype;
    }

    public void setTtype(String ttype) {
        this.ttype = ttype;
    }

    public String getTsituation() {
        return tsituation;
    }

    public void setTsituation(String tsituation) {
        this.tsituation = tsituation;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "TransportationInfo{" +
        ", id=" + id +
        ", code=" + code +
        ", city=" + city +
        ", ttype=" + ttype +
        ", tsituation=" + tsituation +
        ", route=" + route +
        ", skill=" + skill +
        "}";
    }
}

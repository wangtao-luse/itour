package com.itour.model.travel;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
/**
 * <p>
 * 旅行行程安排表
 * </p>
 *
 * @author wangtao
 * @since 2020-07-25
 */
@TableName("t_t_travel_schedule")
public class TravelSchedule extends Model<TravelSchedule> {

    private static final long serialVersionUID = 1L;

    /**
     * 编号(主键)
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    /**
     * 城市代码
     */
    @TableField("CODE")
    private String code;

    /**
     * 地点
     */
    @TableField("LOCATION")
    private String location;

    /**
     * 类型
     */
    @TableField("TYPE")
    private String type;

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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "TravelSchedule{" +
        ", id=" + id +
        ", code=" + code +
        ", location=" + location +
        ", type=" + type +
        "}";
    }
}

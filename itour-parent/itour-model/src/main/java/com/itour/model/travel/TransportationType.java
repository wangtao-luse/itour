package com.itour.model.travel;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
/**
 * <p>
 * 交通类型表
 * </p>
 *
 * @author wangtao
 * @since 2020-07-25
 */
@TableName("t_t_transportation_type")
public class TransportationType extends Model<TransportationType> {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    /**
     * key
     */
    @TableField("TCODE")
    private Integer tcode;

    /**
     * 交通类型
     */
    @TableField("TRANSPORTATION")
    private String transportation;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTcode() {
        return tcode;
    }

    public void setTcode(Integer tcode) {
        this.tcode = tcode;
    }

    public String getTransportation() {
        return transportation;
    }

    public void setTransportation(String transportation) {
        this.transportation = transportation;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "TransportationType{" +
        ", id=" + id +
        ", tcode=" + tcode +
        ", transportation=" + transportation +
        "}";
    }
}

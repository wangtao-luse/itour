package com.itour.model.travel;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
/**
 * <p>
 * 景点类型表
 * </p>
 *
 * @author wangtao
 * @since 2020-07-25
 */
@TableName("t_t_stype")
public class Stype extends Model<Stype> {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    /**
     * Key
     */
    @TableField("TCODE")
    private Integer tcode;

    /**
     * 景点类型
     */
    @TableField("Type")
    private String Type;

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

    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "Stype{" +
        ", id=" + id +
        ", tcode=" + tcode +
        ", Type=" + Type +
        "}";
    }
}

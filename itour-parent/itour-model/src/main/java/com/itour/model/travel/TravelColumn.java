package com.itour.model.travel;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
/**
 * <p>
 * 旅行博客专栏表
 * </p>
 *
 * @author wangtao
 * @since 2020-07-25
 */
@TableName("t_t_travel_column")
public class TravelColumn extends Model<TravelColumn> {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    /**
     * 专栏名称
     */
    @TableField("BCOLUMN")
    private String bcolumn;

    /**
     * 用户ID
     */
    @TableField("UID")
    private Integer uid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBcolumn() {
        return bcolumn;
    }

    public void setBcolumn(String bcolumn) {
        this.bcolumn = bcolumn;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "TravelColumn{" +
        ", id=" + id +
        ", bcolumn=" + bcolumn +
        ", uid=" + uid +
        "}";
    }
}

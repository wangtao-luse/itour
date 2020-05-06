package com.itour.model.travel;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
/**
 * <p>
 * 旅行博客类型表
 * </p>
 *
 * @author wangtao
 * @since 2020-05-06
 */
@TableName("t_b_btype")
public class Btype extends Model<Btype> {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableField("ID")
    private Integer id;

    /**
     * 类型名称
     */
    @TableField("TNAME")
    private String tname;

    /**
     * 创建时间
     */
    @TableField("CREATEDATE")
    private Integer createdate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public Integer getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Integer createdate) {
        this.createdate = createdate;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "Btype{" +
        ", id=" + id +
        ", tname=" + tname +
        ", createdate=" + createdate +
        "}";
    }
}

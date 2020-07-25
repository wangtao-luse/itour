package com.itour.model.travel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
/**
 * <p>
 * 旅行博客-关键字表(中间表)
 * </p>
 *
 * @author wangtao
 * @since 2020-07-25
 */
public class TTravelKeyword extends Model<TTravelKeyword> {

    private static final long serialVersionUID = 1L;

    /**
     * 编号(主键)
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    /**
     * 博客编号(来源博客信息表的ID)
     */
    @TableField("BID")
    private Integer bid;

    /**
     * 关键字的编号(来源于关键字表的ID （一篇博客最多可设置3个关键字）)
     */
    @TableField("KID")
    private Integer kid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }

    public Integer getKid() {
        return kid;
    }

    public void setKid(Integer kid) {
        this.kid = kid;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "TTravelKeyword{" +
        ", id=" + id +
        ", bid=" + bid +
        ", kid=" + kid +
        "}";
    }
}

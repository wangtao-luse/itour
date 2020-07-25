package com.itour.model.travel;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
/**
 * <p>
 * 旅行博客关键字表
 * </p>
 *
 * @author wangtao
 * @since 2020-07-25
 */
@TableName("t_t_keyword")
public class Keyword extends Model<Keyword> {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    /**
     * 关键字	博客关键字
     */
    @TableField("Keyword")
    private String Keyword;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKeyword() {
        return Keyword;
    }

    public void setKeyword(String Keyword) {
        this.Keyword = Keyword;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "Keyword{" +
        ", id=" + id +
        ", Keyword=" + Keyword +
        "}";
    }
}

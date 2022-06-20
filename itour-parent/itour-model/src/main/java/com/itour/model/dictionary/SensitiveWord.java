package com.itour.model.dictionary;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
/**
 * <p>
 * 敏感字表
 * </p>
 *
 * @author wangtao
 * @since 2021-04-21
 */
@TableName("t_d_sensitive_word")
public class SensitiveWord extends Model<SensitiveWord> {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    @TableField("WORD_CONTENT")
    private String wordContent;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWordContent() {
        return wordContent;
    }

    public void setWordContent(String wordContent) {
        this.wordContent = wordContent;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "SensitiveWord{" +
        ", id=" + id +
        ", wordContent=" + wordContent +
        "}";
    }
}

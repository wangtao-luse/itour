package com.itour.model.dictionary;
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
 * @since 2020-07-08
 */
@TableName("t_d_dictionary")
public class Dictionary extends Model<Dictionary> {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    /**
     * 字典的目录
     */
    @TableField("CODE_SET")
    private String codeSet;

    /**
     * 字典代码
     */
    @TableField("CODE")
    private String code;

    /**
     * 字典值
     */
    @TableField("CNAME")
    private String cname;

    /**
     * 状态(1:正常;2:不可用)
     */
    @TableField("STATUS")
    private String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodeSet() {
        return codeSet;
    }

    public void setCodeSet(String codeSet) {
        this.codeSet = codeSet;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "Dictionary{" +
        ", id=" + id +
        ", codeSet=" + codeSet +
        ", code=" + code +
        ", cname=" + cname +
        ", status=" + status +
        "}";
    }
}

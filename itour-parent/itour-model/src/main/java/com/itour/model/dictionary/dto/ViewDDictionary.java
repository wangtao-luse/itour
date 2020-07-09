package com.itour.model.dictionary.dto;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
/**
 * <p>
 * VIEW
 * </p>
 *
 * @author wangtao
 * @since 2020-07-09
 */
public class ViewDDictionary extends Model<ViewDDictionary> {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableField("ID")
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

    /**
     * 字典值
     */
    @TableField("STATUS_STR")
    private String statusStr;

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

    public String getStatusStr() {
        return statusStr;
    }

    public void setStatusStr(String statusStr) {
        this.statusStr = statusStr;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "ViewDDictionary{" +
        ", id=" + id +
        ", codeSet=" + codeSet +
        ", code=" + code +
        ", cname=" + cname +
        ", status=" + status +
        ", statusStr=" + statusStr +
        "}";
    }
}

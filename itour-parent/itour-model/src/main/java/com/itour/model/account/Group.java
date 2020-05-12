package com.itour.model.account;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
/**
 * <p>
 * 用户组表
 * </p>
 *
 * @author wangtao
 * @since 2020-05-07
 */
@TableName("t_a_group")
public class Group extends Model<Group> {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId("ID")
    private Integer id;

    /**
     * 编号名称
     */
    @TableField("G_NAME")
    private String gName;

    /**
     * 所属组编号
     */
    @TableField("G_PARENT")
    private Integer gParent;

    /**
     * 组描述
     */
    @TableField("G_DESC")
    private String gDesc;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getgName() {
        return gName;
    }

    public void setgName(String gName) {
        this.gName = gName;
    }

    public Integer getgParent() {
        return gParent;
    }

    public void setgParent(Integer gParent) {
        this.gParent = gParent;
    }

    public String getgDesc() {
        return gDesc;
    }

    public void setgDesc(String gDesc) {
        this.gDesc = gDesc;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "Group{" +
        ", id=" + id +
        ", gName=" + gName +
        ", gParent=" + gParent +
        ", gDesc=" + gDesc +
        "}";
    }
}

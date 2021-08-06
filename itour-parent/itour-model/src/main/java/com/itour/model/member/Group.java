package com.itour.model.member;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
/**
 * <p>
 * 管理用户组表
 * </p>
 *
 * @author wangtao
 * @since 2020-06-23
 */
@TableName("t_m_group")
public class Group extends Model<Group> {

    private static final long serialVersionUID = 1L;

    /**
     * 组编号
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    /**
     * 组名称
     */
    @TableField("G_NAME")
    private String gName;

    /**
     * 所属组编号
     */
    @TableField("G_PARENT")
    private Long gParent;

    /**
     * 组描述
     */
    @TableField("G_DESC")
    private String gDesc;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getgName() {
        return gName;
    }

    public void setgName(String gName) {
        this.gName = gName;
    }

    public Long getgParent() {
        return gParent;
    }

    public void setgParent(Long gParent) {
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

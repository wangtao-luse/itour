package com.itour.model.member;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
/**
 * <p>
 * 用户-组表(中间表)
 * </p>
 *
 * @author wangtao
 * @since 2020-06-24
 */
@TableName("t_m_account_group")
public class AccountGroup extends Model<AccountGroup> {

    private static final long serialVersionUID = 1L;

    /**
     *  编号(主键,自动增长)
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户唯一号	来源于用户标准的UID
     */
    @TableField("U_ID")
    private String uId;

    /**
     * 组编号	来源于用户组表中的ID
     */
    @TableField("GROUP_ID")
    private Integer groupId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "AccountGroup{" +
        ", id=" + id +
        ", uId=" + uId +
        ", groupId=" + groupId +
        "}";
    }
}

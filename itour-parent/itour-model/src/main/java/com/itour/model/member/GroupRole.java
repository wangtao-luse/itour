package com.itour.model.member;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
/**
 * <p>
 * 角色-组表
 * </p>
 *
 * @author wangtao
 * @since 2020-05-28
 */
@TableName("t_m_group_role")
public class GroupRole extends Model<GroupRole> {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    /**
     * 角色编号
     */
    @TableField("ROLE_ID")
    private Long roleId;

    /**
     * 组编号
     */
    @TableField("GROUP_ID")
    private Long groupId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "RoleGroup{" +
        ", id=" + id +
        ", roleId=" + roleId +
        ", groupId=" + groupId +
        "}";
    }
}

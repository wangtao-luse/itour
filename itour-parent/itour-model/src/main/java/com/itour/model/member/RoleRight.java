package com.itour.model.member;
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
 * @since 2020-06-06
 */
@TableName("t_m_role_right")
public class RoleRight extends Model<RoleRight> {

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
     * 权限编号
     */
    @TableField("RIGHT_ID")
    private Long rightId;

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

    public Long getRightId() {
        return rightId;
    }

    public void setRightId(Long rightId) {
        this.rightId = rightId;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "RoleRight{" +
        ", id=" + id +
        ", roleId=" + roleId +
        ", rightId=" + rightId +
        "}";
    }
}

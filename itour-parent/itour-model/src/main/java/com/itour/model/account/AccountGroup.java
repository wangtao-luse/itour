package com.itour.model.account;
import com.baomidou.mybatisplus.annotation.TableName;
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
 * @since 2020-05-04
 */
@TableName("t_a_account_group")
public class AccountGroup extends Model<AccountGroup> {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId("ID")
    private Integer id;

    /**
     * 用户唯一号
     */
    @TableField("U_ID")
    private String uId;

    /**
     * 组编号
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

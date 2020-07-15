package com.itour.model.member.dto;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
/**
 * <p>
 * VIEW
 * </p>
 *
 * @author wangtao
 * @since 2020-07-15
 */
public class ViewMAccountGroup extends Model<ViewMAccountGroup> {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableField("ID")
    private Integer id;

    /**
     * 用户唯一号	唯一(从10000开始)
     */
    @TableField("UID")
    private String uid;

    /**
     * 性别(0：女；1：男)
     */
    @TableField("SEX")
    private String sex;

    /**
     * 注册时间
     */
    @TableField("CREATEDATE")
    private Integer createdate;

    /**
     * 注册IP	
     */
    @TableField("CREATEIP")
    private String createip;

    /**
     * 上次登录时间
     */
    @TableField("LASTTIME")
    private Integer lasttime;

    /**
     * 状态	0：禁用；1：正常
     */
    @TableField("STATUS")
    private String status;

    /**
     * 用户类型	0:普通用户;1:管理员
     */
    @TableField("UTYPE")
    private String utype;
    /**
     * 组编号
     */
    @TableField("GROUP_ID")
    private String groupId;
    /**
     * 字典值
     */
    @TableField("STATUS_STR")
    private String statusStr;

    /**
     * 字典值
     */
    @TableField("UTYPE_STR")
    private String utypeStr;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Integer createdate) {
        this.createdate = createdate;
    }

    public String getCreateip() {
        return createip;
    }

    public void setCreateip(String createip) {
        this.createip = createip;
    }

    public Integer getLasttime() {
        return lasttime;
    }

    public void setLasttime(Integer lasttime) {
        this.lasttime = lasttime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUtype() {
        return utype;
    }

    public void setUtype(String utype) {
        this.utype = utype;
    }

    public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getStatusStr() {
        return statusStr;
    }

    public void setStatusStr(String statusStr) {
        this.statusStr = statusStr;
    }

    public String getUtypeStr() {
        return utypeStr;
    }

    public void setUtypeStr(String utypeStr) {
        this.utypeStr = utypeStr;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "ViewMAccountGroup{" +
        ", id=" + id +
        ", uid=" + uid +
        ", sex=" + sex +
        ", createdate=" + createdate +
        ", createip=" + createip +
        ", lasttime=" + lasttime +
        ", status=" + status +
        ", utype=" + utype +
        ", groupId=" + groupId +
        ", statusStr=" + statusStr +
        ", utypeStr=" + utypeStr +
        "}";
    }
}

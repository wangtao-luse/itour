package com.itour.model.account.dto;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.itour.model.vo.LongRange;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
/**
 * <p>
 * VIEW
 * </p>
 *
 * @author wangtao
 * @since 2020-07-08
 */
public class ViewAAccount extends Model<ViewAAccount> {

    private static final long serialVersionUID = 1L;

    @TableField("ID")
    private Integer id;

    @TableField("UID")
    private String uid;

    @TableField("SEX")
    private String sex;

    @TableField("CREATEDATE")
    private Integer createdate;

    @TableField("CREATEIP")
    private String createip;

    @TableField("LASTTIME")
    private Integer lasttime;

    @TableField("STATUS")
    private String status;

    @TableField("UTYPE")
    private String utype;

    /**
     * 字典值
     */
    @TableField("SEX_STR")
    private String sexStr;

    /**
     * 字典值
     */
    @TableField("UTYPE_STR")
    private String utypeStr;

    /**
     * 字典值
     */
    @TableField("STATUS_STR")
    private String statusStr;
    /**注册日期***/
    @TableField(exist = false)
    private LongRange createdateRange = new LongRange();
    /**最近一次登录日期***/
    @TableField(exist = false)
    private LongRange lasttimeRange = new LongRange();
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

    public String getSexStr() {
        return sexStr;
    }

    public void setSexStr(String sexStr) {
        this.sexStr = sexStr;
    }

    public String getUtypeStr() {
        return utypeStr;
    }

    public void setUtypeStr(String utypeStr) {
        this.utypeStr = utypeStr;
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

    public LongRange getCreatedateRange() {
		return createdateRange;
	}

	public void setCreatedateRange(LongRange createdateRange) {
		this.createdateRange = createdateRange;
	}

	public LongRange getLasttimeRange() {
		return lasttimeRange;
	}

	public void setLasttimeRange(LongRange lasttimeRange) {
		this.lasttimeRange = lasttimeRange;
	}

	@Override
    public String toString() {
        return "ViewAAccount{" +
        ", id=" + id +
        ", uid=" + uid +
        ", sex=" + sex +
        ", createdate=" + createdate +
        ", createip=" + createip +
        ", lasttime=" + lasttime +
        ", status=" + status +
        ", utype=" + utype +
        ", sexStr=" + sexStr +
        ", utypeStr=" + utypeStr +
        ", statusStr=" + statusStr +
        "}";
    }
}

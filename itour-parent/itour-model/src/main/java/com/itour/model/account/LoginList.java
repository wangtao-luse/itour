package com.itour.model.account;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.itour.model.dto.LongRange;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
/**
 * <p>
 * 用户认证表
 * </p>
 *
 * @author wangtao
 * @since 2020-05-05
 */
@TableName("t_a_login_list")
public class LoginList extends Model<LoginList> {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId(value = "ID",type = IdType.AUTO)
    private Long id;

    /**
     * 用户UID
     */
    @TableField("U_ID")
    private String uId;
    /**
           * 帐号
     */
    @TableField("OAUTH_ID")
    private String oauthId;
    /**
     * 账号类型
     */
    @TableField("OAUTH_TYPE")
    private String oauthType;
    /**
     * 登录时间
     */
    @TableField("LOGIN_TIME")
    private Long loginTime;

    /**
     * 登录IP
     */
    @TableField("LOGIN_IP")
    private String loginIp;

    /**
     * IP反查结果
     */
    @TableField("LOGIN_IP_LOOKUP")
    private String loginIpLookup;
    /**
     * 登录日期取决查询
     */
    @TableField(exist = false)
    private LongRange loginDate;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public Long getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Long loginTime) {
        this.loginTime = loginTime;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public String getLoginIpLookup() {
        return loginIpLookup;
    }

    public void setLoginIpLookup(String loginIpLookup) {
        this.loginIpLookup = loginIpLookup;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }

    public String getOauthId() {
		return oauthId;
	}

	public void setOauthId(String oauthId) {
		this.oauthId = oauthId;
	}

	public String getOauthType() {
		return oauthType;
	}

	public void setOauthType(String oauthType) {
		this.oauthType = oauthType;
	}

	public LongRange getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(LongRange loginDate) {
		this.loginDate = loginDate;
	}

	@Override
    public String toString() {
        return "LoginList{" +
        ", id=" + id +
        ", uId=" + uId +
        ", oauthId="+oauthId+
        ", oauthType="+oauthType+
        ", loginTime=" + loginTime +
        ", loginIp=" + loginIp +
        ", loginIpLookup=" + loginIpLookup +
        "}";
    }
}

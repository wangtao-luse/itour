package com.itour.model.member;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
/**
 * <p>
 * 登录记录表
 * </p>
 *
 * @author wangtao
 * @since 2020-06-23
 */
@TableName("t_m_login_list")
public class LoginList extends Model<LoginList> {

    private static final long serialVersionUID = 1L;

    /**
     * 编号标识 主键,自动增长
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    @TableField("U_ID")
    private String uId;

    @TableField("OAUTH_ID")
    private String oauthId;

    @TableField("OAUTH_TYPE")
    private String oauthType;

    /**
     * 登录时间
     */
    @TableField("LOGIN_TIME")
    private Integer loginTime;

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

    public Integer getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Integer loginTime) {
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

    @Override
    public String toString() {
        return "LoginList{" +
        ", id=" + id +
        ", uId=" + uId +
        ", oauthId=" + oauthId +
        ", oauthType=" + oauthType +
        ", loginTime=" + loginTime +
        ", loginIp=" + loginIp +
        ", loginIpLookup=" + loginIpLookup +
        "}";
    }
}

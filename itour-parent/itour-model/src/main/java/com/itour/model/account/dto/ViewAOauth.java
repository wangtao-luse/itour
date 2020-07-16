package com.itour.model.account.dto;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
/**
 * <p>
 * VIEW
 * </p>
 *
 * @author wangtao
 * @since 2020-07-10
 */
public class ViewAOauth extends Model<ViewAOauth> {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableField("ID")
    private Integer id;

    /**
     * 用户唯一号
     */
    @TableField("U_ID")
    private String uId;

    /**
     * 第三方登录唯一ID
     */
    @TableField("OAUTH_ID")
    private String oauthId;

    /**
     * 第三方登录平台标识(手机号，邮箱，用户名，第三方应用名称（微信，QQ，微博…）)	手机：PHONE;邮箱：EMAIL;QQ:QQ;微信：WECHAT;用户名：UID;微博:WEIBO
     */
    @TableField("OAUTH_TYPE")
    private String oauthType;

    /**
     * 昵称
     */
    @TableField("NICKNAME")
    private String nickname;

    /**
     * 图像
     */
    @TableField("AVATAR")
    private String avatar;

    /**
     * 字典值
     */
    @TableField("OAUTH_TYPE_STR")
    private String oauthTypeStr;
    /**
     * 注册日期
     */
    @TableField("CREATEDATE")
    private Long createdate;
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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getOauthTypeStr() {
        return oauthTypeStr;
    }

    public void setOauthTypeStr(String oauthTypeStr) {
        this.oauthTypeStr = oauthTypeStr;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }

    public Long getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Long createdate) {
		this.createdate = createdate;
	}

	@Override
    public String toString() {
        return "ViewAOauth{" +
        ", id=" + id +
        ", uId=" + uId +
        ", oauthId=" + oauthId +
        ", oauthType=" + oauthType +
        ", nickname=" + nickname +
        ", avatar=" + avatar +
        ", oauthTypeStr=" + oauthTypeStr +
        ", createdate=" + createdate +
        "}";
    }
}

package com.itour.model.member.vo;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
/**
 * <p>
 * VIEW
 * </p>
 *
 * @author wangtao
 * @since 2020-07-10
 */
@TableName("view_m_oauth")
public class ViewMOauth extends Model<ViewMOauth> {

    private static final long serialVersionUID = 1L;

    /**
     * 编号标识	主键,自动增长
     */
    @TableField("ID")
    private Long id;

    /**
     * 用户唯一号	外键来源于用户表中的UID
     */
    @TableField("U_ID")
    private String uId;

    /**
     * 第三方登录唯一ID	站内保存手机号码;用户名，邮箱;
     */
    @TableField("OAUTH_ID")
    private String oauthId;

    /**
     * 第三方登录平台标识(手机号，邮箱，用户名，第三方应用名称（微信，QQ，微博…）)	手机：phone;邮箱：email;QQ:qq;微信：wechat;用户名：uid;微博:weibo
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
     * 注册时间
     */
    @TableField("CREATEDATE")
    private Long createdate;

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

    @Override
    public String toString() {
        return "ViewMOauth{" +
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

package com.itour.common.vo;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

public class AccountVo {
	 /**
     * 编号
     */
    private Integer id;

    /**
     * 用户唯一号
     */
    private String uId;

    /**
     * 第三方登录唯一ID
     */
    private String oauthId;

    /**
     * 第三方登录平台标识(手机号，邮箱，用户名，第三方应用名称（微信，QQ，微博…）)	手机：phone;邮箱：email;QQ:qq;微信：wechat;用户名：uid;微博:weibo
     */
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

  

   

    @Override
    public String toString() {
        return "Oauth{" +
        ", id=" + id +
        ", uId=" + uId +
        ", oauthId=" + oauthId +
        ", oauthType=" + oauthType +
        ", nickname=" + nickname +
        ", avatar=" + avatar +
        "}";
    }
}

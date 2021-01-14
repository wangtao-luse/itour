package com.itour.model.msg;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
/**
 * <p>
 * 消息发送记录表
 * </p>
 *
 * @author wangtao
 * @since 2020-05-14
 */
@TableName("t_a_messageinfo")
public class Messageinfo extends Model<Messageinfo> {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    /**
     * 主题
     */
    @TableField("SUBJECT")
    private String subject;

    /**
     * 消息内容
     */
    @TableField("TEXT")
    private String text;

    /**
     * 发送者
     */
    @TableField("`FROM`")
    private String from;

    /**
     * 接受者
     */
    @TableField("`TO`")
    private String to;

  

    /**
     * 消息类型(email:邮件;phone:手机)
     */
    @TableField("`TYPE`")
    private String type;

 

    /**
     * 消息发送时间
     */
    @TableField("SENDTIME")
    private Long sendtime;
    /**
     * 验证码的作用(1:注册验证码)
     */
    @TableField("AIM")
    private String aim;
    /**
          * 来源 (1:前台用户 ;2:后台用户)
     */
    @TableField("ORIGIN")
    private String origin;
    /**
     * IP地址
     */
    @TableField("IP")
    private String ip;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

  

    public String getType() {
        return type;
    }


    public void setType(String type) {
		this.type = type;
	}

	public Long getSendtime() {
        return sendtime;
    }

    public void setSendtime(Long sendtime) {
        this.sendtime = sendtime;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }

    public String getAim() {
		return aim;
	}

	public void setAim(String aim) {
		this.aim = aim;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@Override
    public String toString() {
        return "Messageinfo{" +
        ", id=" + id +
        ", subject=" + subject +
        ", text=" + text +
        ", from=" + from +
        ", to=" + to +
        ", type=" + type +
        ", sendtime=" + sendtime +
        ", aim=" + aim +
        ", origin=" + origin +
        "}";
    }
}

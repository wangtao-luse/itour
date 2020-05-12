package com.itour.model.msg;
import com.baomidou.mybatisplus.annotation.TableName;
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
 * @since 2020-05-12
 */
@TableName("t_a_messagetext")
public class MessageText extends Model<MessageText> {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId("ID")
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
    private String from;

    /**
     * 接受者
     */
    private String to;

    /**
     * 消息目的
     */
    private String aim;

    /**
     * 消息类型
     */
    private String type;

    /**
     * 备注
     */
    private String remark;

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

    public String getAim() {
        return aim;
    }

    public void setAim(String aim) {
        this.aim = aim;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "Messagelist{" +
        ", id=" + id +
        ", subject=" + subject +
        ", text=" + text +
        ", from=" + from +
        ", to=" + to +
        ", aim=" + aim +
        ", type=" + type +
        ", remark=" + remark +
        "}";
    }
}

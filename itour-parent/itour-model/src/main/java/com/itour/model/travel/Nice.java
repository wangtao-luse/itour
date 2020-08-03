package com.itour.model.travel;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
/**
 * <p>
 * 
 * </p>
 *
 * @author wangtao
 * @since 2020-08-03
 */
@TableName("t_t_nice")
public class Nice extends Model<Nice> {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    /**
     * 旅行信息编号
     */
    @TableField("TID")
    private Integer tid;

    /**
     * 点赞数
     */
    @TableField("NICE")
    private Integer nice;

    /**
     * 用户编号
     */
    @TableField("UID")
    private String uid;

    /**
     * 点赞时间
     */
    @TableField("TIME")
    private Integer time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public Integer getNice() {
        return nice;
    }

    public void setNice(Integer nice) {
        this.nice = nice;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "Nice{" +
        ", id=" + id +
        ", tid=" + tid +
        ", nice=" + nice +
        ", uid=" + uid +
        ", time=" + time +
        "}";
    }
}

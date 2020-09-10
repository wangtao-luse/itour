package com.itour.model.travel;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
/**
 * <p>
 * 旅行博客收藏表
 * </p>
 *
 * @author wangtao
 * @since 2020-09-10
 */
@TableName("t_b_collect")
public class Collect extends Model<Collect> {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    /**
     * 博客编号
     */
    @TableField("TID")
    private Integer tid;

    /**
     * 收藏时间
     */
    @TableField("CTIME")
    private Integer ctime;

    /**
     * 收藏用户编号
     */
    @TableField("UID")
    private String uid;

    /**
     * 所属收藏夹
     */
    @TableField("FID")
    private Integer fid;

    /**
     * 状态(1:收藏;2:取消收藏)
     */
    @TableField("STATUS")
    private String status;

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

    public Integer getCtime() {
        return ctime;
    }

    public void setCtime(Integer ctime) {
        this.ctime = ctime;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "Collect{" +
        ", id=" + id +
        ", tid=" + tid +
        ", ctime=" + ctime +
        ", uid=" + uid +
        ", fid=" + fid +
        ", status=" + status +
        "}";
    }
}

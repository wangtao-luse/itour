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
@TableName("t_t_collect")
public class Collect extends Model<Collect> {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    /**
     * 博客编号
     */
    @TableField("TID")
    private Long tid;

    /**
     * 收藏时间
     */
    @TableField("CTIME")
    private Long ctime;

    /**
     * 收藏用户编号
     */
    @TableField("UID")
    private String uid;

    /**
     * 所属收藏夹
     */
    @TableField("FID")
    private Long fid;

    /**
     * 状态(1:收藏;2:取消收藏)
     */
    @TableField("STATUS")
    private String status;



    

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTid() {
		return tid;
	}

	public void setTid(Long tid) {
		this.tid = tid;
	}

	public Long getCtime() {
		return ctime;
	}

	public void setCtime(Long ctime) {
		this.ctime = ctime;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public Long getFid() {
		return fid;
	}

	public void setFid(Long fid) {
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

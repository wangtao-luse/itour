package com.itour.model.travel;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
/**
 * <p>
 * 旅行信息浏览记录表
 * </p>
 *
 * @author wangtao
 * @since 2020-09-22
 */
@TableName("t_t_history")
public class History extends Model<History> {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户唯一号
     */
    @TableField("U_ID")
    private String uId;

    /**
     * 浏览时间
     */
    @TableField("CREATEDATE")
    private Long createdate;

    /**
     * 旅行信息编号
     */
    @TableField("TID")
    private Integer tid;

    /**
     * 旅行信息标题
     */
    @TableField("TITLE")
    private String title;

    /**
     * 文章地址
     */
    @TableField("LOC")
    private String loc;

    /**
     * 状态	1:正常;2:以删除
     */
    @TableField("STATUS")
    private String status;

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

    public Long getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Long createdate) {
        this.createdate = createdate;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
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
        return "History{" +
        ", id=" + id +
        ", uId=" + uId +
        ", createdate=" + createdate +
        ", tid=" + tid +
        ", title=" + title +
        ", loc=" + loc +
        ", status=" + status +
        "}";
    }
}

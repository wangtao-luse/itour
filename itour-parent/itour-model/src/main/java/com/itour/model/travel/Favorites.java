package com.itour.model.travel;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
/**
 * <p>
 * 旅行收藏夹表
 * </p>
 *
 * @author wangtao
 * @since 2020-09-10
 */
@TableName("t_b_favorites")
public class Favorites extends Model<Favorites> {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    /**
     * 收藏夹名称
     */
    @TableField("FAVORITE")
    private String favorite;

    /**
     * 收藏夹描述
     */
    @TableField("SUBTITLE")
    private String subtitle;

    /**
     * 所属用户编号
     */
    @TableField("UID")
    private String uid;

    /**
     * 创建时间
     */
    @TableField("CREATEDATE")
    private Integer createdate;

    /**
     * 状态(0:已删除;1:正常)
     */
    @TableField("STATUS")
    private String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFavorite() {
        return favorite;
    }

    public void setFavorite(String favorite) {
        this.favorite = favorite;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Integer getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Integer createdate) {
        this.createdate = createdate;
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
        return "Favorites{" +
        ", id=" + id +
        ", favorite=" + favorite +
        ", subtitle=" + subtitle +
        ", uid=" + uid +
        ", createdate=" + createdate +
        ", status=" + status +
        "}";
    }
}

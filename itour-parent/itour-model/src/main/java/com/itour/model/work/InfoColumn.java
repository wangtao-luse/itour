package com.itour.model.work;
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
 * @since 2021-07-13
 */
@TableName("t_w_info_column")
public class InfoColumn extends Model<InfoColumn> {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    /**
     * 博客编号
     */
    @TableField("WID")
    private Long wid;

    /**
     * 专栏ID
     */
    @TableField("CID")
    private Long cid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    
    public Long getWid() {
		return wid;
	}

	public void setWid(Long wid) {
		this.wid = wid;
	}

	public Long getCid() {
		return cid;
	}

	public void setCid(Long cid) {
		this.cid = cid;
	}

	@Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "InfoColumn{" +
        ", id=" + id +
        ", wid=" + wid +
        ", cid=" + cid +
        "}";
    }
}

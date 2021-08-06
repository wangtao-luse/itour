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
 * @since 2021-07-16
 */
@TableName("t_w_worktext")
public class Worktext extends Model<Worktext> {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    /**
     * 博文编号
     */
    @TableField("WID")
    private Long wid;

    /**
     * 工作博客文本
     */
    @TableField("WCONTENT")
    private String wcontent;

    

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

	public String getWcontent() {
        return wcontent;
    }

    public void setWcontent(String wcontent) {
        this.wcontent = wcontent;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "Worktext{" +
        ", id=" + id +
        ", wid=" + wid +
        ", wcontent=" + wcontent +
        "}";
    }
}

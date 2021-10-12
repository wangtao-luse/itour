package com.itour.model.travel;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.itour.model.dto.LongRange;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
/**
 * <p>
 * 
 * </p>
 *
 * @author wangtao
 * @since 2020-08-04
 */
@TableName("t_t_travel_column")
public class TravelColumn extends Model<TravelColumn> {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    /**
     * 专栏名称
     */
    @TableField("`COLUMN`")
    private String column;

    /**
     * 用户ID
     */
    @TableField("UID")
    private String uid;

    /**
     * 创建日期
     */
    @TableField("CREATEDATE")
    private Long createdate;
    /**创建日期***/
    @TableField(exist = false)
    private LongRange createdateRange;

    public LongRange getCreatedateRange() {
		return createdateRange;
	}

	public void setCreatedateRange(LongRange createdateRange) {
		this.createdateRange = createdateRange;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Long getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Long createdate) {
        this.createdate = createdate;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "TravelColumn{" +
        ", id=" + id +
        ", column=" + column +
        ", uid=" + uid +
        ", createdate=" + createdate +
        "}";
    }
}

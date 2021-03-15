package com.itour.model.travel;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
/**
 * <p>
 * 周末攻略内容表
 * </p>
 *
 * @author wangtao
 * @since 2021-02-12
 */
@TableName("t_t_week_info")
public class WeekInfo extends Model<WeekInfo> {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    /**
     * 周末攻略文章内容
     */
    @TableField("WEEK_CONTENT")
    private String weekContent;

    /**
     * 文章编号
     */
    @TableField("TID")
    private Long tid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWeekContent() {
        return weekContent;
    }

    public void setWeekContent(String weekContent) {
        this.weekContent = weekContent;
    }

    public Long getTid() {
        return tid;
    }

    public void setTid(Long tid) {
        this.tid = tid;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "WeekInfo{" +
        ", id=" + id +
        ", weekContent=" + weekContent +
        ", tid=" + tid +
        "}";
    }
}

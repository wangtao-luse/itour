package com.itour.model.travel.dto;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
/**
 * <p>
 * VIEW
 * </p>
 *
 * @author wangtao
 * @since 2021-05-02
 */
public class ViewTravelinfoWeekinfo extends Model<ViewTravelinfoWeekinfo> {

    private static final long serialVersionUID = 1L;

    /**
     * 编号(主键)
     */
    @TableField("ID")
    private Long id;

    @TableField("STATUS")
    private String status;

    /**
     * 周末攻略文章内容
     */
    @TableField("WEEK_CONTENT")
    private String weekContent;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getWeekContent() {
        return weekContent;
    }

    public void setWeekContent(String weekContent) {
        this.weekContent = weekContent;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "ViewTravelinfoWeekinfo{" +
        ", id=" + id +
        ", status=" + status +
        ", weekContent=" + weekContent +
        "}";
    }
}

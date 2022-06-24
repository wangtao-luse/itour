package com.itour.model.dictionary;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
/**
 * <p>
 * 网站访问信息统计表
 * </p>
 *
 * @author wangtao
 * @since 2022-06-23
 */
@TableName("t_d_website_visit_info")
public class WebsiteVisitInfo extends Model<WebsiteVisitInfo> {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    /**
     * 浏览量
     */
    @TableField("PV")
    private Long pv;

    /**
     * 访问次数
     */
    @TableField("VV")
    private Long vv;

    /**
     * 独立ip访问量
     */
    @TableField("IP")
    private Long ip;

    /**
     * 独立访客
     */
    @TableField("UV")
    private Long uv;

    /**
     * 統計数据日期
     */
    @TableField("COUNT_DATE")
    private Long countDate;



    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPv() {
		return pv;
	}

	public void setPv(Long pv) {
		this.pv = pv;
	}

	public Long getVv() {
		return vv;
	}

	public void setVv(Long vv) {
		this.vv = vv;
	}

	public Long getIp() {
		return ip;
	}

	public void setIp(Long ip) {
		this.ip = ip;
	}

	public Long getUv() {
		return uv;
	}

	public void setUv(Long uv) {
		this.uv = uv;
	}

	public Long getCountDate() {
		return countDate;
	}

	public void setCountDate(Long countDate) {
		this.countDate = countDate;
	}

	@Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "WebsiteVisitInfo{" +
        ", id=" + id +
        ", pv=" + pv +
        ", vv=" + vv +
        ", ip=" + ip +
        ", uv=" + uv +
        ", countDate=" + countDate +
        "}";
    }
}

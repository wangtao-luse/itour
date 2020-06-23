package com.itour.model.member;
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
 * @since 2020-06-23
 */
@TableName("t_m_right_detail")
public class RightDetail extends Model<RightDetail> {

    private static final long serialVersionUID = 1L;

    /**
     * 编号(主键,自动增长)
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    /**
     * 菜单URL
     */
    @TableField("URL")
    private String url;

    /**
     * 权限编号(来源于权限主表的菜单唯一号)
     */
    @TableField("RIGHT_NO")
    private String rightNo;

    /**
     * 是否登录(anon 不需要登录 authc 需要登录)
     */
    @TableField("ISLOGIN")
    private String islogin;

    /**
     * 描述
     */
    @TableField("DESC")
    private String desc;

    /**
     * 是否记日志(0:不需要;1:需要)
     */
    @TableField("ISLOG")
    private String islog;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRightNo() {
        return rightNo;
    }

    public void setRightNo(String rightNo) {
        this.rightNo = rightNo;
    }

    public String getIslogin() {
        return islogin;
    }

    public void setIslogin(String islogin) {
        this.islogin = islogin;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getIslog() {
        return islog;
    }

    public void setIslog(String islog) {
        this.islog = islog;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "RightDetail{" +
        ", id=" + id +
        ", url=" + url +
        ", rightNo=" + rightNo +
        ", islogin=" + islogin +
        ", desc=" + desc +
        ", islog=" + islog +
        "}";
    }
}

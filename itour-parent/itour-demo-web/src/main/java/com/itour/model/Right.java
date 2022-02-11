package com.itour.model;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
/**
 * <p>
 * 权限主表
 * </p>
 *
 * @author wangtao
 * @since 2020-06-23
 */
@TableName("t_m_right")
public class Right extends Model<Right> {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    /**
     * 菜单唯一号
     */
    @TableField("MENU_NO")
    private String menuNo;

    /**
     * 菜单名称
     */
    @TableField("MENU")
    private String menu;

    /**
     * 上级菜单编号(0:顶级菜单)
     */
    @TableField("PARENT_ID")
    private String parentId;

    /**
     * 菜单类型(1:菜单;2:按钮)
     */
    @TableField("MENU_TYPE")
    private String menuType;

    /**
     * 菜单url
     */
    @TableField("URL")
    private String url;
    /**
     * 一级菜单的顺序
     */
    @TableField("M_ORDER")
    private String mOrder;

    /**
     * 二级菜单的顺序
     */
    @TableField("S_ORDER")
    private String sOrder;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMenuNo() {
        return menuNo;
    }

    public void setMenuNo(String menuNo) {
        this.menuNo = menuNo;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getMenuType() {
        return menuType;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getmOrder() {
		return mOrder;
	}

	public void setmOrder(String mOrder) {
		this.mOrder = mOrder;
	}

	public String getsOrder() {
		return sOrder;
	}

	public void setsOrder(String sOrder) {
		this.sOrder = sOrder;
	}

	@Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "Right{" +
        ", id=" + id +
        ", menuNo=" + menuNo +
        ", menu=" + menu +
        ", parentId=" + parentId +
        ", menuType=" + menuType +
        ", url=" + url +
        ", mOrder=" + mOrder +
        ", sOrder=" + sOrder +
        "}";
    }
}

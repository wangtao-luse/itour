package com.itour.model.member.dto;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
/**
 * <p>
 * VIEW
 * </p>
 *
 * @author wangtao
 * @since 2020-07-09
 */
public class ViewMRight extends Model<ViewMRight> {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableField("ID")
    private Integer id;

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
    private Integer parentId;

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

    /**
     * 字典值
     */
    @TableField("MENU_TYPE_STR")
    private String menuTypeStr;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
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

    public String getMenuTypeStr() {
        return menuTypeStr;
    }

    public void setMenuTypeStr(String menuTypeStr) {
        this.menuTypeStr = menuTypeStr;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "ViewMRight{" +
        ", id=" + id +
        ", menuNo=" + menuNo +
        ", menu=" + menu +
        ", parentId=" + parentId +
        ", menuType=" + menuType +
        ", url=" + url +
        ", mOrder=" + mOrder +
        ", sOrder=" + sOrder +
        ", menuTypeStr=" + menuTypeStr +
        "}";
    }
}

package com.itour.model.travel;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.itour.model.dto.Orderby;
/**
 * <p>
 * 景点信息表
 * </p>
 *
 * @author wangtao
 * @since 2020-07-25
 */
@TableName("t_t_scenic_spot")
public class ScenicSpot extends Model<ScenicSpot> {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    /**
     * 景点图片
     */
    @TableField("SCENICIMG")
    private String scenicimg;

    /**
     * 景点名称
     */
    @TableField("SCENIC_SPOT")
    private String scenicSpot;

    /**
     * 景点类型
     */
    @TableField("TYPE")
    private String type;

    /**
     * 景点简介
     */
    @TableField("SINTROUDUCE")
    private String sintrouduce;

    /**
     * 景点介绍
     */
    @TableField("INTROUDUCE")
    private String introuduce;

    /**
     * 门票价格
     */
    @TableField("PRICE")
    private BigDecimal price;

    /**
     * 所在城市
     */
    @TableField("CITY")
    private String city;

    /**
     * 城市代码
     */
    @TableField("CCODE")
    private String ccode;

    /**
     * 具体地址
     */
    @TableField("ADDR")
    private String addr;

    /**
     * 交通
     */
    @TableField("STRANSPORTATION")
    private String stransportation;

    /**
     * 推荐指数
     */
    @TableField("RECOMMEND")
    private BigDecimal recommend;
    
    
    private List<Orderby> orderby;
    public List<Orderby> getOrderby() {
		return orderby;
	}

	public void setOrderby(List<Orderby> orderby) {
		this.orderby = orderby;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getScenicimg() {
        return scenicimg;
    }

    public void setScenicimg(String scenicimg) {
        this.scenicimg = scenicimg;
    }

    public String getScenicSpot() {
        return scenicSpot;
    }

    public void setScenicSpot(String scenicSpot) {
        this.scenicSpot = scenicSpot;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSintrouduce() {
        return sintrouduce;
    }

    public void setSintrouduce(String sintrouduce) {
        this.sintrouduce = sintrouduce;
    }

    public String getIntrouduce() {
        return introuduce;
    }

    public void setIntrouduce(String introuduce) {
        this.introuduce = introuduce;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCcode() {
        return ccode;
    }

    public void setCcode(String ccode) {
        this.ccode = ccode;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getStransportation() {
        return stransportation;
    }

    public void setStransportation(String stransportation) {
        this.stransportation = stransportation;
    }

    public BigDecimal getRecommend() {
        return recommend;
    }

    public void setRecommend(BigDecimal recommend) {
        this.recommend = recommend;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "ScenicSpot{" +
        ", id=" + id +
        ", scenicimg=" + scenicimg +
        ", scenicSpot=" + scenicSpot +
        ", type=" + type +
        ", sintrouduce=" + sintrouduce +
        ", introuduce=" + introuduce +
        ", price=" + price +
        ", city=" + city +
        ", ccode=" + ccode +
        ", addr=" + addr +
        ", stransportation=" + stransportation +
        ", recommend=" + recommend +
        "}";
    }
}

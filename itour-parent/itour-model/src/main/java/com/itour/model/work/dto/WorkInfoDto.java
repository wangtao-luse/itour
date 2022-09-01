package com.itour.model.work.dto;

import java.util.ArrayList;
import java.util.List;

import com.itour.model.dto.Orderby;
import com.itour.model.work.WorkInfo;

public class WorkInfoDto extends WorkInfo{
	private String dynamic;
	private Long time;
	private String mold;
	private String createDateFmt;
	private String avatar;
	private String nickname;
	private String oauthId;
	private Long articleCount;
	private Long draffCount;
	private List<Orderby> orderbyList = new ArrayList<Orderby>();
	private String niceStatus;
	private String niceUid;
	private String queryUid;
	/**
	 * 收藏夹相关
	 */
	private String visual;
	private String favorite;
	private String subtitle;
	private Long createdate;
	private Long latestDate;

	/**
	 * 
	 * 预览相关
	 */
	private String [] tag_arr;
	private String  [] col_arr;
	private String markdown;

	public String[] getTag_arr() {
		return tag_arr;
	}
	public void setTag_arr(String[] tag_arr) {
		this.tag_arr = tag_arr;
	}
	public String[] getCol_arr() {
		return col_arr;
	}
	public void setCol_arr(String[] col_arr) {
		this.col_arr = col_arr;
	}
	public String getMarkdown() {
		return markdown;
	}
	public void setMarkdown(String markdown) {
		this.markdown = markdown;
	}
	public Long getArticleCount() {
		return articleCount;
	}
	public void setArticleCount(Long articleCount) {
		this.articleCount = articleCount;
	}
	public Long getLatestDate() {
		return latestDate;
	}
	public void setLatestDate(Long latestDate) {
		this.latestDate = latestDate;
	}
	public String getVisual() {
		return visual;
	}
	public void setVisual(String visual) {
		this.visual = visual;
	}
	public String getFavorite() {
		return favorite;
	}
	public void setFavorite(String favorite) {
		this.favorite = favorite;
	}
	public String getSubtitle() {
		return subtitle;
	}
	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}
	public Long getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Long createdate) {
		this.createdate = createdate;
	}

	public String getQueryUid() {
		return queryUid;
	}
	public void setQueryUid(String queryUid) {
		this.queryUid = queryUid;
	}
	public String getNiceStatus() {
		return niceStatus;
	}
	public void setNiceStatus(String niceStatus) {
		this.niceStatus = niceStatus;
	}
	public String getNiceUid() {
		return niceUid;
	}
	public void setNiceUid(String niceUid) {
		this.niceUid = niceUid;
	}

	public Long getDraffCount() {
		return draffCount;
	}
	public void setDraffCount(Long draffCount) {
		this.draffCount = draffCount;
	}
	public List<Orderby> getOrderbyList() {
		return orderbyList;
	}
	public void setOrderbyList(List<Orderby> orderbyList) {
		this.orderbyList = orderbyList;
	}
	public String getOauthId() {
		return oauthId;
	}
	public void setOauthId(String oauthId) {
		this.oauthId = oauthId;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getCreateDateFmt() {
		return createDateFmt;
	}
	public void setCreateDateFmt(String createDateFmt) {
		this.createDateFmt = createDateFmt;
	}
	public String getMold() {
		return mold;
	}
	public void setMold(String mold) {
		this.mold = mold;
	}
	public String getDynamic() {
		return dynamic;
	}
	public void setDynamic(String dynamic) {
		this.dynamic = dynamic;
	}
	public Long getTime() {
		return time;
	}
	public void setTime(Long time) {
		this.time = time;
	}
	@Override
	public String toString() {
		return "TravelInfoDto {"+ 
				"dynamic=" + dynamic +
				", time=" + time + 
				", mold=" + mold +
				", createDateFmt="+ createDateFmt +
				", avatar="	+ avatar +
				", nickname=" + nickname + 
				", oauthId=" +oauthId + 
				", draffCount=" + draffCount + 
				", orderbyList=" + orderbyList + 
				", niceStatus=" + niceStatus+ 
				", niceUid=" + niceUid + 
				", queryUid=" + queryUid + 
				"}";
	}

}

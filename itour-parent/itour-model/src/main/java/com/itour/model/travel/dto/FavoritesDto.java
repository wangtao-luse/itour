package com.itour.model.travel.dto;

import com.itour.model.travel.Favorites;

public class FavoritesDto extends Favorites {
private static final long serialVersionUID = 1L;
private String checked;
private Long tid;
private Integer collectCount;
private Long latestDate;
private String queryUid;
private Integer articleCount;
public Integer getArticleCount() {
	return articleCount;
}

public void setArticleCount(Integer articleCount) {
	this.articleCount = articleCount;
}

public String getQueryUid() {
	return queryUid;
}

public void setQueryUid(String queryUid) {
	this.queryUid = queryUid;
}

public Long getLatestDate() {
	return latestDate;
}

public void setLatestDate(Long latestDate) {
	this.latestDate = latestDate;
}

public Integer getCollectCount() {
	return collectCount;
}

public void setCollectCount(Integer collectCount) {
	this.collectCount = collectCount;
}

public String getChecked() {
	return checked;
}

public void setChecked(String checked) {
	this.checked = checked;
}

public Long getTid() {
	return tid;
}

public void setTid(Long tid) {
	this.tid = tid;
}
}

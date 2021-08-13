package com.itour.model.vo;

import java.util.List;

import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
@Component
public class PageInfo<T> extends Page<T> {

	private static final long serialVersionUID = 1L;
	private long[] navigatepageNums;
	private long navSize = 5;
	private long ps;
	public PageInfo() {
		super();
	}
	public PageInfo(long navSize, long ps) {
		super();
		this.navSize = navSize;
		this.ps = ps;
	}
	public long getPs() {
		long total = this.getTotal();
		long size = this.getSize();
		long pages=total/size;
		if(total%size!=0) {
			pages+=1;
		}
		return pages;
	}
	public void setPs(long ps) {
		this.ps = ps;
	}
	public long[] getNavigatepageNums() {
		return navigatepageNums;
	}
	public void setNavigatepageNums(long[] navigatepageNums) {
		this.navigatepageNums = navigatepageNums;
	}
	public long getNavSize() {
		return navSize;
	}
	public void setNavSize(long navSize) {
		this.navSize = navSize;
	}
	
	
	
		public void pageNav(){
			//当前的页码
			long pageNum=this.getCurrent();
			// 一共多少页
			long pages = this.getPages();
	        //算出pageNum左边有多少个
	        long before = navSize/2;

	        //起始的页码，防止了起始为负数
	        long start = pageNum-before<1 ? 1 : pageNum-before;
	        //终止的页码
	        long end = start+navSize-1;
	        //如果终止页码大于等于总页码
	        if (end>=pages){
	            //总页码就是终止页码
	            end = pages;
	            //因为终止页码变动，起始页码也需要变动
	            start = end-navSize+1;
	            //如果总页码小于展示页码个数的话，起始页码可能是负数，将它变为1
	            if (start<1){
	                start = 1;
	            }
	        }


	        long[] navs = new long[(int) (pages<navSize?pages:navSize)];
	        for (int i = (int) start,j=0; i <= end; i++,j++) {
	            navs[j] = i;
	        }
	        this.navigatepageNums= navs;
	    }
		 public List<T> getRecords() {
			 List<T> records = super.getRecords();
			  this.getPs();
			  this.pageNav();
		        return records;
		    }
}

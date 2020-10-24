package com.ccb.portal.vo;

import java.io.Serializable;
import java.util.List;

import com.github.pagehelper.PageInfo;

public class Pager<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int pageNum; // 第几页
	private int pageSize; // 每页记录数
	private long total; // 总记录数
	private List<T> list; // 结果集

	public Pager(List<T> list) {
		PageInfo<T> page = new PageInfo<>(list);
		this.pageNum = page.getPageNum();
		this.pageSize = page.getPageSize();
		this.total = page.getTotal();
		this.list = page.getList();
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

}

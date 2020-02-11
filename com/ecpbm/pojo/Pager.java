package com.ecpbm.pojo;

public class Pager {
	private int curPage;
	private int perPageRows;
	private int rowCount;
	private int pageCount;
	public int getCurPage() {
		return curPage;
	}
	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}
	public int getPerPageRows() {
		return perPageRows;
	}
	public void setPerPageRows(int perPageRows) {
		this.perPageRows = perPageRows;
	}
	public int getRowCount() {
		return rowCount;
	}
	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}
	//计算总页数
	public int getPageCount() {
		return (rowCount+perPageRows-1)/perPageRows;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getFirstLimitParam() {
		return (this.curPage - 1)*this.perPageRows;
	};
}

package com.explorer.musicblog.pojo;

/**
 * 	分页类
 * @author 17879
 *
 */
public class PageBean {

	private Integer pageNo = 1;	   // 当前页
	
	private Integer rows = 10;	   // 每页显示条数
	
	private Integer total = 0;	   // 总条数
	
	private boolean paging = true; //是否分页

	public PageBean(Integer pageNo, Integer rows, Integer total, boolean paging) {
		super();
		this.pageNo = pageNo;
		this.rows = rows;
		this.total = total;
		this.paging = paging;
	}

	@Override
	public String toString() {
		return "PageBean [pageNo=" + pageNo + ", rows=" + rows + ", total=" + total + ", paging=" + paging + "]";
	}
	
	/**
	 * 	获取当前页
	 * @return
	 */
	public int getStartIndex() {
		return (this.pageNo - 1) * this.rows;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public boolean paging() {
		return paging;
	}

	public void setPaging(boolean paging) {
		this.paging = paging;
	}
}

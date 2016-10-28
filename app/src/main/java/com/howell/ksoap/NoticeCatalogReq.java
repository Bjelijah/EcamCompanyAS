package com.howell.ksoap;
/**
 * @author 霍之昊 
 *
 * 类说明：查询系统通知信息
 */
public class NoticeCatalogReq {
	private String session;
	private int pageIndex;
	private int pageSize;
	private String status;
	private String time;
	private String sender;
	public NoticeCatalogReq(String session, int pageIndex, int pageSize,
			String status, String time, String sender) {
		super();
		this.session = session;
		this.pageIndex = pageIndex;
		this.pageSize = pageSize;
		this.status = status;
		this.time = time;
		this.sender = sender;
	}
	
	public NoticeCatalogReq(String session, int pageIndex, int pageSize) {
		super();
		this.session = session;
		this.pageIndex = pageIndex;
		this.pageSize = pageSize;
	}

	public NoticeCatalogReq(String session) {
		super();
		this.session = session;
	}
	public NoticeCatalogReq() {
		super();
	}
	public String getSession() {
		return session;
	}
	public void setSession(String session) {
		this.session = session;
	}
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}

	
}

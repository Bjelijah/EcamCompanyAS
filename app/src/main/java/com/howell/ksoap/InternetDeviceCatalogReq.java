package com.howell.ksoap;
/**
 * @author 霍之昊 
 *
 * 类说明：获取网络接入设备列表，登陆平台后使用
 */
public class InternetDeviceCatalogReq {
	private String session;
    private int pageIndex;
    private int pageSize;
	public InternetDeviceCatalogReq(String session, int pageIndex, int pageSize) {
		super();
		this.session = session;
		this.pageIndex = pageIndex;
		this.pageSize = pageSize;
	}
	public InternetDeviceCatalogReq() {
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
    
    
}

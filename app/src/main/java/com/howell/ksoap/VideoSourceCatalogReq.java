package com.howell.ksoap;

public class VideoSourceCatalogReq {
	private String session;
	private String internetDeviceId;
	private String id;
	private int pageIndex;
	private int pageSize;
	public VideoSourceCatalogReq(String session, String internetDeviceId,
			String id, int pageIndex, int pageSize) {
		super();
		this.session = session;
		this.internetDeviceId = internetDeviceId;
		this.id = id;
		this.pageIndex = pageIndex;
		this.pageSize = pageSize;
	}
	public VideoSourceCatalogReq() {
		super();
	}
	public String getSession() {
		return session;
	}
	public void setSession(String session) {
		this.session = session;
	}
	public String getInternetDeviceId() {
		return internetDeviceId;
	}
	public void setInternetDeviceId(String internetDeviceId) {
		this.internetDeviceId = internetDeviceId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	@Override
	public String toString() {
		return "VideoSourceCatalogReq [session=" + session
				+ ", internetDeviceId=" + internetDeviceId + ", id=" + id
				+ ", pageIndex=" + pageIndex + ", pageSize=" + pageSize + "]";
	}
	
	
}

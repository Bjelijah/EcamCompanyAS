package com.howell.ksoap;
/**
 * @author 霍之昊 
 *
 * 类说明
 */
public class DeviceStorageMediumCatalogReq {
	private String session;
	private String internetDeviceId;
	private String id;
	private int pageIndex;
	private int pageSize;
	public DeviceStorageMediumCatalogReq(String session, String internetDeviceId, String id,
			int pageIndex, int pageSize) {
		super();
		this.session = session;
		this.internetDeviceId = internetDeviceId;
		this.id = id;
		this.pageIndex = pageIndex;
		this.pageSize = pageSize;
	}
	public DeviceStorageMediumCatalogReq(String session, String internetDeviceId,String id) {
		super();
		this.session = session;
		this.internetDeviceId = internetDeviceId;
		this.id = id;
	}
	public DeviceStorageMediumCatalogReq() {
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
}

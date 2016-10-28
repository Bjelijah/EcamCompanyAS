package com.howell.ksoap;
/**
 * @author 霍之昊 
 *
 * 类说明:查询设备下的报警输入通道目录 
 */
public class DeviceIOInputChannelCatalogReq {
	private String session;
	private String internetDeviceId;
	private String id;
	private int pageIndex;
	private int pageSize;
	public DeviceIOInputChannelCatalogReq(String session, String internetDeviceId, String id,
			int pageIndex, int pageSize) {
		super();
		this.session = session;
		this.internetDeviceId = internetDeviceId;
		this.id = id;
		this.pageIndex = pageIndex;
		this.pageSize = pageSize;
	}
	
	public DeviceIOInputChannelCatalogReq(String session,
			String internetDeviceId, String id) {
		super();
		this.session = session;
		this.internetDeviceId = internetDeviceId;
		this.id = id;
	}

	public DeviceIOInputChannelCatalogReq(String session, String internetDeviceId) {
		super();
		this.session = session;
		this.internetDeviceId = internetDeviceId;
	}
	
	public DeviceIOInputChannelCatalogReq() {
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

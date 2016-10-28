package com.howell.ksoap;
/**
 * @author 霍之昊 
 *
 * 类说明：查询系统故障报告request
 */
public class SystemFaultReportCatalogReq {
	private String session;
	private String internetDeviceId;
	private int pageIndex;
	private int pageSize;
	private String faultType;
	public SystemFaultReportCatalogReq(String session, String internetDeviceId,
			int pageIndex, int pageSize, String faultType) {
		super();
		this.session = session;
		this.internetDeviceId = internetDeviceId;
		this.pageIndex = pageIndex;
		this.pageSize = pageSize;
		this.faultType = faultType;
	}
	public SystemFaultReportCatalogReq() {
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
	public String getFaultType() {
		return faultType;
	}
	public void setFaultType(String faultType) {
		this.faultType = faultType;
	}
	
	
}

package com.howell.ksoap;
/**
 * @author 霍之昊 
 *
 * 类说明：系统警告报告request
 */
public class SystemWarningReportCatalogReq {
	private String session;
	private String internetDeviceId;
	private int pageIndex;
	private int pageSize;
	private String warningType;
	public SystemWarningReportCatalogReq(String session,
			String internetDeviceId, int pageIndex, int pageSize,
			String warningType) {
		super();
		this.session = session;
		this.internetDeviceId = internetDeviceId;
		this.pageIndex = pageIndex;
		this.pageSize = pageSize;
		this.warningType = warningType;
	}
	public SystemWarningReportCatalogReq() {
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
	public String getWarningType() {
		return warningType;
	}
	public void setWarningType(String warningType) {
		this.warningType = warningType;
	}
	
	

}

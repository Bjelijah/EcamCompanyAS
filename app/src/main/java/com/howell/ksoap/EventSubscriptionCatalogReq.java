package com.howell.ksoap;
/**
 * @author 霍之昊 
 *
 * 类说明
 */
public class EventSubscriptionCatalogReq {
	private String session;
	private String internetDeviceId;
	private String componentId;
	private String eventType;
	private int pageIndex;
	private int pageSize;
	public EventSubscriptionCatalogReq(String session, String internetDeviceId,
			String componentId, String eventType, int pageIndex, int pageSize) {
		super();
		this.session = session;
		this.internetDeviceId = internetDeviceId;
		this.componentId = componentId;
		this.eventType = eventType;
		this.pageIndex = pageIndex;
		this.pageSize = pageSize;
	}
	public EventSubscriptionCatalogReq(String session, String internetDeviceId,String eventType) {
		super();
		this.session = session;
		this.internetDeviceId = internetDeviceId;
		this.eventType = eventType;
	}
	
	public EventSubscriptionCatalogReq(String session, String internetDeviceId,
			String componentId, String eventType) {
		super();
		this.session = session;
		this.internetDeviceId = internetDeviceId;
		this.componentId = componentId;
		this.eventType = eventType;
	}
	public EventSubscriptionCatalogReq() {
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
	public String getComponentId() {
		return componentId;
	}
	public void setComponentId(String componentId) {
		this.componentId = componentId;
	}
	public String getEventType() {
		return eventType;
	}
	public void setEventType(String eventType) {
		this.eventType = eventType;
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
		return "EventSubscriptionCatalogReq [session=" + session
				+ ", internetDeviceId=" + internetDeviceId + ", componentId="
				+ componentId + ", eventType=" + eventType + ", pageIndex="
				+ pageIndex + ", pageSize=" + pageSize + "]";
	}
	

}

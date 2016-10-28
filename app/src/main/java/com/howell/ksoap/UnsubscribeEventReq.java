package com.howell.ksoap;
/**
 * @author 霍之昊 
 *
 * 类说明
 */
public class UnsubscribeEventReq {
	private String session;
	private String internetDeviceId;
	private String componentId;
	private String eventType;
	public UnsubscribeEventReq(String session, String internetDeviceId,
			String componentId, String eventType) {
		super();
		this.session = session;
		this.internetDeviceId = internetDeviceId;
		this.componentId = componentId;
		this.eventType = eventType;
	}
	public UnsubscribeEventReq() {
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
	
	
}

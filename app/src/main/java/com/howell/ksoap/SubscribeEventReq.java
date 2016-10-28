package com.howell.ksoap;
/**
 * @author 霍之昊 
 *
 * 类说明
 */
public class SubscribeEventReq {
	private String session;
	private String internetDeviceId;
	private String componentId;
	private String eventType;
	private String triggerState;
	private String notificationMethod;
	public SubscribeEventReq(String session, String internetDeviceId,
			String componentId, String eventType, String triggerState,
			String notificationMethod) {
		super();
		this.session = session;
		this.internetDeviceId = internetDeviceId;
		this.componentId = componentId;
		this.eventType = eventType;
		this.triggerState = triggerState;
		this.notificationMethod = notificationMethod;
	}
	public SubscribeEventReq() {
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
	public String getTriggerState() {
		return triggerState;
	}
	public void setTriggerState(String triggerState) {
		this.triggerState = triggerState;
	}
	public String getNotificationMethod() {
		return notificationMethod;
	}
	public void setNotificationMethod(String notificationMethod) {
		this.notificationMethod = notificationMethod;
	}
	
	

}

package com.howell.ksoap;
/**
 * @author 霍之昊 
 *
 * 类说明
 */
public class EventSubscription {
	private String internetDeviceId;
	private String componentId;
	private String eventType;
	private String triggerState;
	private String notificationMethod;
	private String lastModifiedTime;
	public EventSubscription(String internetDeviceId, String componentId,
			String eventType, String triggerState, String notificationMethod,
			String lastModifiedTime) {
		super();
		this.internetDeviceId = internetDeviceId;
		this.componentId = componentId;
		this.eventType = eventType;
		this.triggerState = triggerState;
		this.notificationMethod = notificationMethod;
		this.lastModifiedTime = lastModifiedTime;
	}
	public EventSubscription() {
		super();
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
	public String getLastModifiedTime() {
		return lastModifiedTime;
	}
	public void setLastModifiedTime(String lastModifiedTime) {
		this.lastModifiedTime = lastModifiedTime;
	}
	@Override
	public String toString() {
		return "EventSubscription [internetDeviceId=" + internetDeviceId
				+ ", componentId=" + componentId + ", eventType=" + eventType
				+ ", triggerState=" + triggerState + ", notificationMethod="
				+ notificationMethod + ", lastModifiedTime=" + lastModifiedTime
				+ "]";
	}
	
	

}

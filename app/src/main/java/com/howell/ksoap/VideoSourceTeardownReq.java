package com.howell.ksoap;

public class VideoSourceTeardownReq {
	private String session;
	private String internetDeviceId;
	private String Id;
	private String dialogId;
	public VideoSourceTeardownReq(String session, String internetDeviceId, String id,
			String dialogId) {
		super();
		this.session = session;
		this.internetDeviceId = internetDeviceId;
		this.Id = id;
		this.dialogId = dialogId;
	}
	public VideoSourceTeardownReq() {
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
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getDialogId() {
		return dialogId;
	}
	public void setDialogId(String dialogId) {
		this.dialogId = dialogId;
	}
	
}

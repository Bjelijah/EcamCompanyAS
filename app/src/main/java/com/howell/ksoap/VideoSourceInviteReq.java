package com.howell.ksoap;

public class VideoSourceInviteReq {
	private String session;
	private String internetDeviceId;
	private String id;
	private int streamingChannel;
	private String sDP;
	public VideoSourceInviteReq(String session, String internetDeviceId,
			String id, int streamingChannel, String sDP) {
		super();
		this.session = session;
		this.internetDeviceId = internetDeviceId;
		this.id = id;
		this.streamingChannel = streamingChannel;
		this.sDP = sDP;
	}
	public VideoSourceInviteReq() {
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
	public int getStreamingChannel() {
		return streamingChannel;
	}
	public void setStreamingChannel(int streamingChannel) {
		this.streamingChannel = streamingChannel;
	}
	public String getsDP() {
		return sDP;
	}
	public void setsDP(String sDP) {
		this.sDP = sDP;
	}
	
	

}

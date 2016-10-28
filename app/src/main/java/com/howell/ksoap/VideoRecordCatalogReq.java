package com.howell.ksoap;

public class VideoRecordCatalogReq {
	private String session;
	private String internetDeviceId;
	private String id;
	private int streamingChannel;
	private String beginTime;
	private String endTime;
	private int pageIndex;
	private int pageSize;
	public VideoRecordCatalogReq(String session, String internetDeviceId,
			String id, int streamingChannel, String beginTime, String endTime,
			int pageIndex, int pageSize) {
		super();
		this.session = session;
		this.internetDeviceId = internetDeviceId;
		this.id = id;
		this.streamingChannel = streamingChannel;
		this.beginTime = beginTime;
		this.endTime = endTime;
		this.pageIndex = pageIndex;
		this.pageSize = pageSize;
	}
	public VideoRecordCatalogReq() {
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
	public String getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
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

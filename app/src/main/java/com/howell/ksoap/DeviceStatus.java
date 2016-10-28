package com.howell.ksoap;

//设备状态信息
public class DeviceStatus {
	private String id;
	private boolean online;
	private String offlineReason;
	private String lastestOnlineTime;
	private String time;
	private long systemUpTime;
	private String iPAddress;
	public DeviceStatus(String id, boolean online, String offlineReason,
			String lastestOnlineTime, String time, long systemUpTime,
			String iPAddress) {
		super();
		this.id = id;
		this.online = online;
		this.offlineReason = offlineReason;
		this.lastestOnlineTime = lastestOnlineTime;
		this.time = time;
		this.systemUpTime = systemUpTime;
		this.iPAddress = iPAddress;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public boolean isOnline() {
		return online;
	}
	public void setOnline(boolean online) {
		this.online = online;
	}
	public String getOfflineReason() {
		return offlineReason;
	}
	public void setOfflineReason(String offlineReason) {
		this.offlineReason = offlineReason;
	}
	public String getLastestOnlineTime() {
		return lastestOnlineTime;
	}
	public void setLastestOnlineTime(String lastestOnlineTime) {
		this.lastestOnlineTime = lastestOnlineTime;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public long getSystemUpTime() {
		return systemUpTime;
	}
	public void setSystemUpTime(long systemUpTime) {
		this.systemUpTime = systemUpTime;
	}
	public String getiPAddress() {
		return iPAddress;
	}
	public void setiPAddress(String iPAddress) {
		this.iPAddress = iPAddress;
	}
	

}

package com.howell.ksoap;
/**
 * @author 霍之昊 
 *
 * 类说明：互联网接入设备信息
 */
public class InternetDevice {
	private String id;
	private String name;
	private String manufacturer;
	private String model;
	private String firmware;
	private String serialNumber;
	private boolean isOnline;
	private String lastOnlineTime;
	private long systemUpTime;
	public InternetDevice(String id, String name, String manufacturer,
			String model, String firmware, String serialNumber,
			boolean isOnline, String lastOnlineTime, long systemUpTime) {
		super();
		this.id = id;
		this.name = name;
		this.manufacturer = manufacturer;
		this.model = model;
		this.firmware = firmware;
		this.serialNumber = serialNumber;
		this.isOnline = isOnline;
		this.lastOnlineTime = lastOnlineTime;
		this.systemUpTime = systemUpTime;
	}
	public InternetDevice() {
		super();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getFirmware() {
		return firmware;
	}
	public void setFirmware(String firmware) {
		this.firmware = firmware;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public boolean isOnline() {
		return isOnline;
	}
	public void setOnline(boolean isOnline) {
		this.isOnline = isOnline;
	}
	public String getLastOnlineTime() {
		return lastOnlineTime;
	}
	public void setLastOnlineTime(String lastOnlineTime) {
		this.lastOnlineTime = lastOnlineTime;
	}
	public long getSystemUpTime() {
		return systemUpTime;
	}
	public void setSystemUpTime(long systemUpTime) {
		this.systemUpTime = systemUpTime;
	}
	@Override
	public String toString() {
		return "InternetDevice [id=" + id + ", name=" + name
				+ ", manufacturer=" + manufacturer + ", model=" + model
				+ ", firmware=" + firmware + ", serialNumber=" + serialNumber
				+ ", isOnline=" + isOnline + ", lastOnlineTime="
				+ lastOnlineTime + ", systemUpTime=" + systemUpTime + "]";
	}
	
}

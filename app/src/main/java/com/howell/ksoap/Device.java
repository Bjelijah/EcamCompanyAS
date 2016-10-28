package com.howell.ksoap;

public class Device {
	private String id;
	private String name;
	private String manufacturer;
	private String model;
	private String firmware;
	private String serialNumber;
	private String pointOfSale;
	private String information;
	private String classification;
	private String parentDeviceId;
	private int busAddress;
	private boolean hasSubDevice;
	private DeviceStatus status;
	public Device(String id, String name, String manufacturer, String model,
			String firmware, String serialNumber, String pointOfSale,
			String information, String classification, String parentDeviceId,
			int busAddress, boolean hasSubDevice, DeviceStatus status) {
		super();
		this.id = id;
		this.name = name;
		this.manufacturer = manufacturer;
		this.model = model;
		this.firmware = firmware;
		this.serialNumber = serialNumber;
		this.pointOfSale = pointOfSale;
		this.information = information;
		this.classification = classification;
		this.parentDeviceId = parentDeviceId;
		this.busAddress = busAddress;
		this.hasSubDevice = hasSubDevice;
		this.status = status;
	}
	
	public Device(String name) {
		super();
		this.name = name;
	}

	public Device() {
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
	public String getPointOfSale() {
		return pointOfSale;
	}
	public void setPointOfSale(String pointOfSale) {
		this.pointOfSale = pointOfSale;
	}
	public String getInformation() {
		return information;
	}
	public void setInformation(String information) {
		this.information = information;
	}
	public String getClassification() {
		return classification;
	}
	public void setClassification(String classification) {
		this.classification = classification;
	}
	public String getParentDeviceId() {
		return parentDeviceId;
	}
	public void setParentDeviceId(String parentDeviceId) {
		this.parentDeviceId = parentDeviceId;
	}
	public int getBusAddress() {
		return busAddress;
	}
	public void setBusAddress(int busAddress) {
		this.busAddress = busAddress;
	}
	public boolean isHasSubDevice() {
		return hasSubDevice;
	}
	public void setHasSubDevice(boolean hasSubDevice) {
		this.hasSubDevice = hasSubDevice;
	}
	public DeviceStatus getStatus() {
		return status;
	}
	public void setStatus(DeviceStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Device [id=" + id + ", name=" + name + ", manufacturer="
				+ manufacturer + ", model=" + model + ", firmware=" + firmware
				+ ", serialNumber=" + serialNumber + ", pointOfSale="
				+ pointOfSale + ", information=" + information
				+ ", classification=" + classification + ", parentDeviceId="
				+ parentDeviceId + ", busAddress=" + busAddress
				+ ", hasSubDevice=" + hasSubDevice + ", status=" + status + "]";
	}
	
	

}

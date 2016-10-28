package com.howell.ksoap;
/**
 * @author 霍之昊 
 *
 * 类说明
 */
public class StorageMedium {
	private String id;
	private int storagePort;
	private String mediumType;
	private String manufacturer;
	private String model;
	private int capacity;
	private int freespace;
	private String storageType;
	public StorageMedium(String id, int storagePort, String mediumType,
			String manufacturer, String model, int capacity, int freespace,
			String storageType) {
		super();
		this.id = id;
		this.storagePort = storagePort;
		this.mediumType = mediumType;
		this.manufacturer = manufacturer;
		this.model = model;
		this.capacity = capacity;
		this.freespace = freespace;
		this.storageType = storageType;
	}
	public StorageMedium() {
		super();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getStoragePort() {
		return storagePort;
	}
	public void setStoragePort(int storagePort) {
		this.storagePort = storagePort;
	}
	public String getMediumType() {
		return mediumType;
	}
	public void setMediumType(String mediumType) {
		this.mediumType = mediumType;
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
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public int getFreespace() {
		return freespace;
	}
	public void setFreespace(int freespace) {
		this.freespace = freespace;
	}
	public String getStorageType() {
		return storageType;
	}
	public void setStorageType(String storageType) {
		this.storageType = storageType;
	}
	@Override
	public String toString() {
		return "StorageMedium [id=" + id + ", storagePort=" + storagePort
				+ ", mediumType=" + mediumType + ", manufacturer="
				+ manufacturer + ", model=" + model + ", capacity=" + capacity
				+ ", freespace=" + freespace + ", storageType=" + storageType
				+ "]";
	}
	
}

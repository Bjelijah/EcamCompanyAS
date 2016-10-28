package com.howell.ksoap;
/**
 * @author 霍之昊 
 *
 * 类说明
 */
public class NetworkInterface {
	private String id;
	private int interfacePort;
	private String iPVersion;
	private String addressingType;
	private String iPAddress;
	private String physcialAddress;
	private String cableType;
	private String speedDuplex;
	private String workMode;
	private String wireless;
	private int mTU;
	public NetworkInterface(String id, int interfacePort, String iPVersion,
			String addressingType, String iPAddress, String physcialAddress,
			String cableType, String speedDuplex, String workMode,
			String wireless, int mTU) {
		super();
		this.id = id;
		this.interfacePort = interfacePort;
		this.iPVersion = iPVersion;
		this.addressingType = addressingType;
		this.iPAddress = iPAddress;
		this.physcialAddress = physcialAddress;
		this.cableType = cableType;
		this.speedDuplex = speedDuplex;
		this.workMode = workMode;
		this.wireless = wireless;
		this.mTU = mTU;
	}
	public NetworkInterface() {
		super();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getInterfacePort() {
		return interfacePort;
	}
	public void setInterfacePort(int interfacePort) {
		this.interfacePort = interfacePort;
	}
	public String getiPVersion() {
		return iPVersion;
	}
	public void setiPVersion(String iPVersion) {
		this.iPVersion = iPVersion;
	}
	public String getAddressingType() {
		return addressingType;
	}
	public void setAddressingType(String addressingType) {
		this.addressingType = addressingType;
	}
	public String getiPAddress() {
		return iPAddress;
	}
	public void setiPAddress(String iPAddress) {
		this.iPAddress = iPAddress;
	}
	public String getPhyscialAddress() {
		return physcialAddress;
	}
	public void setPhyscialAddress(String physcialAddress) {
		this.physcialAddress = physcialAddress;
	}
	public String getCableType() {
		return cableType;
	}
	public void setCableType(String cableType) {
		this.cableType = cableType;
	}
	public String getSpeedDuplex() {
		return speedDuplex;
	}
	public void setSpeedDuplex(String speedDuplex) {
		this.speedDuplex = speedDuplex;
	}
	public String getWorkMode() {
		return workMode;
	}
	public void setWorkMode(String workMode) {
		this.workMode = workMode;
	}
	public String getWireless() {
		return wireless;
	}
	public void setWireless(String wireless) {
		this.wireless = wireless;
	}
	public int getmTU() {
		return mTU;
	}
	public void setmTU(int mTU) {
		this.mTU = mTU;
	}
	
	

}

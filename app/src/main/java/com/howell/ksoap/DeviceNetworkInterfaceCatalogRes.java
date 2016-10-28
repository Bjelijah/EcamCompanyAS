package com.howell.ksoap;

import java.util.ArrayList;

/**
 * @author 霍之昊 
 *
 * 类说明
 */
public class DeviceNetworkInterfaceCatalogRes {
	private String result;
	private Page page;
	private ArrayList<NetworkInterface> networkInterfaces;
	public DeviceNetworkInterfaceCatalogRes(String result, Page page,
			ArrayList<NetworkInterface> networkInterfaces) {
		super();
		this.result = result;
		this.page = page;
		this.networkInterfaces = networkInterfaces;
	}
	public DeviceNetworkInterfaceCatalogRes() {
		super();
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	public ArrayList<NetworkInterface> getNetworkInterfaces() {
		return networkInterfaces;
	}
	public void setNetworkInterfaces(ArrayList<NetworkInterface> networkInterfaces) {
		this.networkInterfaces = networkInterfaces;
	}
	
}

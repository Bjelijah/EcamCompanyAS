package com.howell.ksoap;

import java.util.ArrayList;

public class DeviceCatalogRes {
	private String result;
	private Page page;
	private ArrayList<Device> Devices;
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
	public ArrayList<Device> getDevices() {
		return Devices;
	}
	public void setDevices(ArrayList<Device> devices) {
		Devices = devices;
	}
	
	
	
}

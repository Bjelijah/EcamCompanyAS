package com.howell.ksoap;

import java.util.ArrayList;

/**
 * @author 霍之昊 
 *
 * 类说明
 */
public class InternetDeviceCatalogRes {
	private String result;
    private Page page;
    private ArrayList<InternetDevice> internetDevices;
	
	public InternetDeviceCatalogRes() {
		super();
	}

	public InternetDeviceCatalogRes(String result, Page page,
			ArrayList<InternetDevice> internetDevices) {
		super();
		this.result = result;
		this.page = page;
		this.internetDevices = internetDevices;
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

	public ArrayList<InternetDevice> getInternetDevices() {
		return internetDevices;
	}

	public void setInternetDevices(ArrayList<InternetDevice> internetDevices) {
		this.internetDevices = internetDevices;
	}

	@Override
	public String toString() {
		for(InternetDevice id : internetDevices){
			System.out.println("id:"+id.toString());
		}
		return "";
	}
    
    
}

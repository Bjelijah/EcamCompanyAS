package com.howell.ksoap;

import java.util.ArrayList;

/**
 * @author 霍之昊 
 *
 * 类说明
 */
public class DeviceIOInputChannelCatalogRes {
	private String result;
	private Page page;
	private ArrayList<IOInputChannel> iOInputChannels;
	public DeviceIOInputChannelCatalogRes(String result, Page page,
			ArrayList<IOInputChannel> iOInputChannels) {
		super();
		this.result = result;
		this.page = page;
		this.iOInputChannels = iOInputChannels;
	}
	public DeviceIOInputChannelCatalogRes() {
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
	public ArrayList<IOInputChannel> getiOInputChannels() {
		return iOInputChannels;
	}
	public void setiOInputChannels(ArrayList<IOInputChannel> iOInputChannels) {
		this.iOInputChannels = iOInputChannels;
	}
	
	
}	

package com.howell.ksoap;

import java.util.ArrayList;

/**
 * @author 霍之昊 
 *
 * 类说明:查询设备下的报警输出通道目录
 */
public class DeviceIOOutputChannelCatalogRes {
	private String result;
	private Page page;
	private ArrayList<IOOutputChannel> iOOutputChannels;
	public DeviceIOOutputChannelCatalogRes(String result, Page page,
			ArrayList<IOOutputChannel> iOOutputChannels) {
		super();
		this.result = result;
		this.page = page;
		this.iOOutputChannels = iOOutputChannels;
	}
	public DeviceIOOutputChannelCatalogRes() {
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
	public ArrayList<IOOutputChannel> getiOOutputChannels() {
		return iOOutputChannels;
	}
	public void setiOOutputChannels(ArrayList<IOOutputChannel> iOOutputChannels) {
		this.iOOutputChannels = iOOutputChannels;
	}
	
	

}

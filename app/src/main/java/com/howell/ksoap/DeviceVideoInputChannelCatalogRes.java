package com.howell.ksoap;

import java.util.ArrayList;

/**
 * @author 霍之昊 
 *
 * 类说明
 */
public class DeviceVideoInputChannelCatalogRes {
	private String result;
	private Page page;
	private ArrayList<VideoInputChannel> videoInputChannels;
	public DeviceVideoInputChannelCatalogRes(String result, Page page,
			ArrayList<VideoInputChannel> videoInputChannels) {
		super();
		this.result = result;
		this.page = page;
		this.videoInputChannels = videoInputChannels;
	}
	public DeviceVideoInputChannelCatalogRes() {
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
	public ArrayList<VideoInputChannel> getVideoInputChannels() {
		return videoInputChannels;
	}
	public void setVideoInputChannels(
			ArrayList<VideoInputChannel> videoInputChannels) {
		this.videoInputChannels = videoInputChannels;
	}
	
}

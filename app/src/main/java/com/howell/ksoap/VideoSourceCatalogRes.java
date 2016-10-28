package com.howell.ksoap;

import java.util.ArrayList;

public class VideoSourceCatalogRes {
	private String result;
	private Page page;
	private ArrayList<VideoSource> VideoSources;
	public VideoSourceCatalogRes(String result, Page page,
			ArrayList<VideoSource> videoSources) {
		super();
		this.result = result;
		this.page = page;
		VideoSources = videoSources;
	}
	public VideoSourceCatalogRes() {
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
	public ArrayList<VideoSource> getVideoSources() {
		return VideoSources;
	}
	public void setVideoSources(ArrayList<VideoSource> videoSources) {
		VideoSources = videoSources;
	}
	

}

package com.howell.ksoap;

import java.util.ArrayList;

/**
 * @author 霍之昊 
 *
 * 类说明:查询视频源分组下的视频源列表 response
 */
public class GroupVideoSourceCatalogRes {
	private String result;
	private Page page;
	private ArrayList<VideoSource> videoSources;
	public GroupVideoSourceCatalogRes(String result, Page page,
			ArrayList<VideoSource> videoSources) {
		super();
		this.result = result;
		this.page = page;
		this.videoSources = videoSources;
	}
	public GroupVideoSourceCatalogRes() {
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
		return videoSources;
	}
	public void setVideoSources(ArrayList<VideoSource> videoSources) {
		this.videoSources = videoSources;
	}

}

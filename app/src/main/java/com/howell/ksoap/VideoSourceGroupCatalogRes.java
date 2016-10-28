package com.howell.ksoap;

import java.util.ArrayList;

/**
 * @author 霍之昊 
 *
 * 类说明:查询视频源分组 response
 */
public class VideoSourceGroupCatalogRes {
	private String result;
    private Page page;
    private ArrayList<VideoSourceGroup> videoSourceGroups;
	public VideoSourceGroupCatalogRes(String result, Page page,
			ArrayList<VideoSourceGroup> videoSourceGroups) {
		super();
		this.result = result;
		this.page = page;
		this.videoSourceGroups = videoSourceGroups;
	}
	public VideoSourceGroupCatalogRes() {
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
	public ArrayList<VideoSourceGroup> getVideoSourceGroups() {
		return videoSourceGroups;
	}
	public void setVideoSourceGroups(ArrayList<VideoSourceGroup> videoSourceGroups) {
		this.videoSourceGroups = videoSourceGroups;
	}
    
    
}

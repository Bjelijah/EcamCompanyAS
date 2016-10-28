package com.howell.ksoap;

import java.util.ArrayList;

public class VideoRecordCatalogRes {
	private String result;
	private Page page;
	private ArrayList<VideoRecord> videoRecords;
	public VideoRecordCatalogRes(String result, Page page,
			ArrayList<VideoRecord> videoRecords) {
		super();
		this.result = result;
		this.page = page;
		this.videoRecords = videoRecords;
	}
	public VideoRecordCatalogRes() {
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
	public ArrayList<VideoRecord> getVideoRecords() {
		return videoRecords;
	}
	public void setVideoRecords(ArrayList<VideoRecord> videoRecords) {
		this.videoRecords = videoRecords;
	}

}

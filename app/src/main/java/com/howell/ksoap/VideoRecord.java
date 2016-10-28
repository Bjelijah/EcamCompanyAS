package com.howell.ksoap;

public class VideoRecord {
	private String beginTime;
	private String endTime;
	private int fileSize;
	private String fileName;
	private String description;
	public VideoRecord(String beginTime, String endTime, int fileSize,
			String fileName, String description) {
		super();
		this.beginTime = beginTime;
		this.endTime = endTime;
		this.fileSize = fileSize;
		this.fileName = fileName;
		this.description = description;
	}
	public VideoRecord() {
		super();
	}
	public String getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public int getFileSize() {
		return fileSize;
	}
	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}

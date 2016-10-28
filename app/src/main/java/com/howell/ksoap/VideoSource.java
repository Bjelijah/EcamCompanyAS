package com.howell.ksoap;

import java.io.Serializable;

public class VideoSource implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private int pseudoCode;
	private String videoInputChannelId;
	private String cameraType;
	private boolean pTZ;
	private boolean infrared;
	private boolean online;
	private String groupPath;
	
	private boolean favourite;
	
	public VideoSource() {
		super();
		this.favourite = false;
	}
	
	public VideoSource(String name,String id) {
		super();
		this.name = name;
		this.id = id;
		this.favourite = false;
	}
	
	public VideoSource(String id, String name, int pseudoCode,
			String videoInputChannelId, String cameraType, boolean pTZ,
			boolean infrared, boolean online, String groupPath) {
		super();
		this.id = id;
		this.name = name;
		this.pseudoCode = pseudoCode;
		this.videoInputChannelId = videoInputChannelId;
		this.cameraType = cameraType;
		this.pTZ = pTZ;
		this.infrared = infrared;
		this.online = online;
		this.groupPath = groupPath;
		this.favourite = false;
	}
	
	public boolean isFavourite() {
		return favourite;
	}

	public void setFavourite(boolean favourite) {
		this.favourite = favourite;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPseudoCode() {
		return pseudoCode;
	}
	public void setPseudoCode(int pseudoCode) {
		this.pseudoCode = pseudoCode;
	}
	public String getVideoInputChannelId() {
		return videoInputChannelId;
	}
	public void setVideoInputChannelId(String videoInputChannelId) {
		this.videoInputChannelId = videoInputChannelId;
	}
	public String getCameraType() {
		return cameraType;
	}
	public void setCameraType(String cameraType) {
		this.cameraType = cameraType;
	}
	public boolean ispTZ() {
		return pTZ;
	}
	public void setpTZ(boolean pTZ) {
		this.pTZ = pTZ;
	}
	public boolean isInfrared() {
		return infrared;
	}
	public void setInfrared(boolean infrared) {
		this.infrared = infrared;
	}
	public boolean isOnline() {
		return online;
	}
	public void setOnline(boolean online) {
		this.online = online;
	}
	public String getGroupPath() {
		return groupPath;
	}
	public void setGroupPath(String groupPath) {
		this.groupPath = groupPath;
	}
	@Override
	public String toString() {
		return "VideoSource [id=" + id + ", name=" + name + ", pseudoCode="
				+ pseudoCode + ", videoInputChannelId=" + videoInputChannelId
				+ ", cameraType=" + cameraType + ", pTZ=" + pTZ + ", infrared="
				+ infrared + ", online=" + online + ", groupPath=" + groupPath
				+ "]";
	}
	

}

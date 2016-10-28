package com.howell.ksoap;
/**
 * @author 霍之昊 
 *
 * 类说明
 */
public class VideoInputChannel {
	private String id;
	private String name;
	private boolean pTZ;
	private boolean infrared;
	private String cameraType;
	private boolean terminal;
	private boolean networked;
	private String videoInterfaceType;
	public VideoInputChannel(String id, String name, boolean pTZ,
			boolean infrared, String cameraType, boolean terminal,
			boolean networked, String videoInterfaceType) {
		super();
		this.id = id;
		this.name = name;
		this.pTZ = pTZ;
		this.infrared = infrared;
		this.cameraType = cameraType;
		this.terminal = terminal;
		this.networked = networked;
		this.videoInterfaceType = videoInterfaceType;
	}
	public VideoInputChannel() {
		super();
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
	public String getCameraType() {
		return cameraType;
	}
	public void setCameraType(String cameraType) {
		this.cameraType = cameraType;
	}
	public boolean isTerminal() {
		return terminal;
	}
	public void setTerminal(boolean terminal) {
		this.terminal = terminal;
	}
	public boolean isNetworked() {
		return networked;
	}
	public void setNetworked(boolean networked) {
		this.networked = networked;
	}
	public String getVideoInterfaceType() {
		return videoInterfaceType;
	}
	public void setVideoInterfaceType(String videoInterfaceType) {
		this.videoInterfaceType = videoInterfaceType;
	}
	
	

}

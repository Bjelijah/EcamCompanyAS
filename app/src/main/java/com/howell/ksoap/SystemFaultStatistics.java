package com.howell.ksoap;
/**
 * @author 霍之昊 
 *
 * 类说明:
 */
public class SystemFaultStatistics {
	private int faultNumber;	//故障数目
	private int offlineNumber;	//离线设备数目
	private int storageMediumAbnormalNumber;//出错的存储媒介数目
	private int videolossNumber;//视频丢失通道数目
	public SystemFaultStatistics(int faultNumber, int offlineNumber,
			int storageMediumAbnormalNumber, int videolossNumber) {
		super();
		this.faultNumber = faultNumber;
		this.offlineNumber = offlineNumber;
		this.storageMediumAbnormalNumber = storageMediumAbnormalNumber;
		this.videolossNumber = videolossNumber;
	}
	public SystemFaultStatistics() {
		super();
	}
	public int getFaultNumber() {
		return faultNumber;
	}
	public void setFaultNumber(int faultNumber) {
		this.faultNumber = faultNumber;
	}
	public int getOfflineNumber() {
		return offlineNumber;
	}
	public void setOfflineNumber(int offlineNumber) {
		this.offlineNumber = offlineNumber;
	}
	public int getStorageMediumAbnormalNumber() {
		return storageMediumAbnormalNumber;
	}
	public void setStorageMediumAbnormalNumber(int storageMediumAbnormalNumber) {
		this.storageMediumAbnormalNumber = storageMediumAbnormalNumber;
	}
	public int getVideolossNumber() {
		return videolossNumber;
	}
	public void setVideolossNumber(int videolossNumber) {
		this.videolossNumber = videolossNumber;
	}

	
}

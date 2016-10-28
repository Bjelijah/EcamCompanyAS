package com.howell.ksoap;
/**
 * @author 霍之昊 
 *
 * 类说明
 */
public class SystemWarningStatistics {
	private int warningNumber;					//警告数目
	private int cPUUsageNumber;					//CPU过高设备数目
	private int memoryUsageNumber;				//内存过高设备数目
	private int networkUsageNumber;				//网口使用率过高数目
	private int superHeatNumber;				//过热设备数目
	private int voltageInstabilityNumber;		//电压不稳数目
	private int videoHighLoadNumber;			//设备视频负载过高数目
	private int videoNetworkInstabilityNumber;	//视频网络状态不稳定通道数目
	private int teardownNumber;					//异常上下线设备数目	
	private int videoConnectionFailureNumber;	//系统视频连接失败率过高或数目
	public SystemWarningStatistics(int warningNumber, int cPUUsageNumber,
			int memoryUsageNumber, int networkUsageNumber, int superHeatNumber,
			int voltageInstabilityNumber, int videoHighLoadNumber,
			int videoNetworkInstabilityNumber, int teardownNumber,
			int videoConnectionFailureNumber) {
		super();
		this.warningNumber = warningNumber;
		this.cPUUsageNumber = cPUUsageNumber;
		this.memoryUsageNumber = memoryUsageNumber;
		this.networkUsageNumber = networkUsageNumber;
		this.superHeatNumber = superHeatNumber;
		this.voltageInstabilityNumber = voltageInstabilityNumber;
		this.videoHighLoadNumber = videoHighLoadNumber;
		this.videoNetworkInstabilityNumber = videoNetworkInstabilityNumber;
		this.teardownNumber = teardownNumber;
		this.videoConnectionFailureNumber = videoConnectionFailureNumber;
	}
	public SystemWarningStatistics() {
		super();
	}
	public int getWarningNumber() {
		return warningNumber;
	}
	public void setWarningNumber(int warningNumber) {
		this.warningNumber = warningNumber;
	}
	public int getcPUUsageNumber() {
		return cPUUsageNumber;
	}
	public void setcPUUsageNumber(int cPUUsageNumber) {
		this.cPUUsageNumber = cPUUsageNumber;
	}
	public int getMemoryUsageNumber() {
		return memoryUsageNumber;
	}
	public void setMemoryUsageNumber(int memoryUsageNumber) {
		this.memoryUsageNumber = memoryUsageNumber;
	}
	public int getNetworkUsageNumber() {
		return networkUsageNumber;
	}
	public void setNetworkUsageNumber(int networkUsageNumber) {
		this.networkUsageNumber = networkUsageNumber;
	}
	public int getSuperHeatNumber() {
		return superHeatNumber;
	}
	public void setSuperHeatNumber(int superHeatNumber) {
		this.superHeatNumber = superHeatNumber;
	}
	public int getVoltageInstabilityNumber() {
		return voltageInstabilityNumber;
	}
	public void setVoltageInstabilityNumber(int voltageInstabilityNumber) {
		this.voltageInstabilityNumber = voltageInstabilityNumber;
	}
	public int getVideoHighLoadNumber() {
		return videoHighLoadNumber;
	}
	public void setVideoHighLoadNumber(int videoHighLoadNumber) {
		this.videoHighLoadNumber = videoHighLoadNumber;
	}
	public int getVideoNetworkInstabilityNumber() {
		return videoNetworkInstabilityNumber;
	}
	public void setVideoNetworkInstabilityNumber(int videoNetworkInstabilityNumber) {
		this.videoNetworkInstabilityNumber = videoNetworkInstabilityNumber;
	}
	public int getTeardownNumber() {
		return teardownNumber;
	}
	public void setTeardownNumber(int teardownNumber) {
		this.teardownNumber = teardownNumber;
	}
	public int getVideoConnectionFailureNumber() {
		return videoConnectionFailureNumber;
	}
	public void setVideoConnectionFailureNumber(int videoConnectionFailureNumber) {
		this.videoConnectionFailureNumber = videoConnectionFailureNumber;
	}
	
	
}

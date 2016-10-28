package com.howell.ksoap;
/**
 * @author 霍之昊 
 *
 * 类说明:系统故障报告
 */
public class SystemFaultReport {
	private String id;				//报告唯一标识符
	private String creationTime;	//创建时间
	private String faultType;		//系统故障类型
	private String componentId;		//故障对象的唯一标识符，如：Offline则是设备唯一标识符，Videoloss 则是视频输入通道唯一标识符	
	private boolean recovered;		//是否已恢复
	private String recoveryTime;	//恢复时间
	private String description;		//描述信息
	public SystemFaultReport(String id, String creationTime, String faultType,
			String componentId, boolean recovered, String recoveryTime,
			String description) {
		super();
		this.id = id;
		this.creationTime = creationTime;
		this.faultType = faultType;
		this.componentId = componentId;
		this.recovered = recovered;
		this.recoveryTime = recoveryTime;
		this.description = description;
	}
	public SystemFaultReport() {
		super();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCreationTime() {
		return creationTime;
	}
	public void setCreationTime(String creationTime) {
		this.creationTime = creationTime;
	}
	public String getFaultType() {
		return faultType;
	}
	public void setFaultType(String faultType) {
		this.faultType = faultType;
	}
	public String getComponentId() {
		return componentId;
	}
	public void setComponentId(String componentId) {
		this.componentId = componentId;
	}
	public boolean isRecovered() {
		return recovered;
	}
	public void setRecovered(boolean recovered) {
		this.recovered = recovered;
	}
	public String getRecoveryTime() {
		return recoveryTime;
	}
	public void setRecoveryTime(String recoveryTime) {
		this.recoveryTime = recoveryTime;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "SystemFaultReport [id=" + id + ", creationTime=" + creationTime
				+ ", faultType=" + faultType + ", componentId=" + componentId
				+ ", recovered=" + recovered + ", recoveryTime=" + recoveryTime
				+ ", description=" + description + "]";
	}
	
}

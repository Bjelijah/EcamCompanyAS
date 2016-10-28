package com.howell.ksoap;
/**
 * @author 霍之昊 
 *
 * 类说明:系统警告报告
 */
public class SystemWarningReport {
	private String Id;				//报告唯一标识符
	private String CreationTime;	//创建时间
	private String WarningType;		//系统警告类型
	private String ComponentId;		//警告对象的唯一标识符
	private String Description;		//描述信息
	public SystemWarningReport(String id, String creationTime,
			String warningType, String componentId, String description) {
		super();
		Id = id;
		CreationTime = creationTime;
		WarningType = warningType;
		ComponentId = componentId;
		Description = description;
	}
	public SystemWarningReport() {
		super();
	}
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getCreationTime() {
		return CreationTime;
	}
	public void setCreationTime(String creationTime) {
		CreationTime = creationTime;
	}
	public String getWarningType() {
		return WarningType;
	}
	public void setWarningType(String warningType) {
		WarningType = warningType;
	}
	public String getComponentId() {
		return ComponentId;
	}
	public void setComponentId(String componentId) {
		ComponentId = componentId;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	
	

}	

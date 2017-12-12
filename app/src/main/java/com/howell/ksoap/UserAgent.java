package com.howell.ksoap;

public class UserAgent {
	private String uUID;
	private String model;
	private String name;
	private String agentType;
	private String agentOSType;
	private String manufactory;
	private String oSVersion;
	private String iMEI;
	
	public UserAgent() {
		super();
	}
	public UserAgent(String uUID, String model, String name, String agentType,
			String agentOSType, String manufactory, String oSVersion,
			String iMEI) {
		super();
		this.uUID = uUID;
		this.model = model;
		this.name = name;
		this.agentType = agentType;
		this.agentOSType = agentOSType;
		this.manufactory = manufactory;
		this.oSVersion = oSVersion;
		this.iMEI = iMEI;
	}
	public String getuUID() {
		return uUID;
	}
	public void setuUID(String uUID) {
		this.uUID = uUID;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAgentType() {
		return agentType;
	}
	public void setAgentType(String agentType) {
		this.agentType = agentType;
	}
	public String getAgentOSType() {
		return agentOSType;
	}
	public void setAgentOSType(String agentOSType) {
		this.agentOSType = agentOSType;
	}
	public String getManufactory() {
		return manufactory;
	}
	public void setManufactory(String manufactory) {
		this.manufactory = manufactory;
	}
	public String getoSVersion() {
		return oSVersion;
	}
	public void setoSVersion(String oSVersion) {
		this.oSVersion = oSVersion;
	}
	public String getiMEI() {
		return iMEI;
	}
	public void setiMEI(String iMEI) {
		this.iMEI = iMEI;
	}

	@Override
	public String toString() {
		return "UserAgent{" +
				"uUID='" + uUID + '\'' +
				", model='" + model + '\'' +
				", name='" + name + '\'' +
				", agentType='" + agentType + '\'' +
				", agentOSType='" + agentOSType + '\'' +
				", manufactory='" + manufactory + '\'' +
				", oSVersion='" + oSVersion + '\'' +
				", iMEI='" + iMEI + '\'' +
				'}';
	}
}

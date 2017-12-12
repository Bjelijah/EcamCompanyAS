package com.howell.ksoap;

public class UserLoginReq {
	
	private String username;
	private String userType;
	private String passwordType;
	private String password;
	private String internetDeviceId;
	private String applicationId;
	private String applicationVersion;
	private String networkOperator;
	private String networkType;
	private UserAgent userAgent;
	
	public UserLoginReq(String username, String userType, String passwordType,
			String password, String internetDeviceId, String applicationId,
			String applicationVersion, String networkOperator,
			String networkType, UserAgent userAgent) {
		super();
		this.username = username;
		this.userType = userType;
		this.passwordType = passwordType;
		this.password = password;
		this.internetDeviceId = internetDeviceId;
		this.applicationId = applicationId;
		this.applicationVersion = applicationVersion;
		this.networkOperator = networkOperator;
		this.networkType = networkType;
		this.userAgent = userAgent;
	}

	public UserLoginReq() {
		super();
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPasswordType() {
		return passwordType;
	}
	public void setPasswordType(String passwordType) {
		this.passwordType = passwordType;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getInternetDeviceId() {
		return internetDeviceId;
	}
	public void setInternetDeviceId(String internetDeviceId) {
		this.internetDeviceId = internetDeviceId;
	}
	public String getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}
	public String getApplicationVersion() {
		return applicationVersion;
	}
	public void setApplicationVersion(String applicationVersion) {
		this.applicationVersion = applicationVersion;
	}
	public String getNetworkOperator() {
		return networkOperator;
	}
	public void setNetworkOperator(String networkOperator) {
		this.networkOperator = networkOperator;
	}
	public String getNetworkType() {
		return networkType;
	}
	public void setNetworkType(String networkType) {
		this.networkType = networkType;
	}
	public UserAgent getUserAgent() {
		return userAgent;
	}
	public void setUserAgent(UserAgent userAgent) {
		this.userAgent = userAgent;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	@Override
	public String toString() {
		return "UserLoginReq{" +
				"username='" + username + '\'' +
				", userType='" + userType + '\'' +
				", passwordType='" + passwordType + '\'' +
				", password='" + password + '\'' +
				", internetDeviceId='" + internetDeviceId + '\'' +
				", applicationId='" + applicationId + '\'' +
				", applicationVersion='" + applicationVersion + '\'' +
				", networkOperator='" + networkOperator + '\'' +
				", networkType='" + networkType + '\'' +
				", userAgent=" + userAgent +
				'}';
	}
}

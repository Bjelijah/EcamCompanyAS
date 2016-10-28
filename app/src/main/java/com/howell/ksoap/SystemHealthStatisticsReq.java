package com.howell.ksoap;
/**
 * @author 霍之昊 
 *
 * 类说明:查询健康度信息
 */
public class SystemHealthStatisticsReq {
	private String session;
	private String internetDeviceId;
	public SystemHealthStatisticsReq(String session, String internetDeviceId) {
		super();
		this.session = session;
		this.internetDeviceId = internetDeviceId;
	}
	public SystemHealthStatisticsReq() {
		super();
	}
	public String getSession() {
		return session;
	}
	public void setSession(String session) {
		this.session = session;
	}
	public String getInternetDeviceId() {
		return internetDeviceId;
	}
	public void setInternetDeviceId(String internetDeviceId) {
		this.internetDeviceId = internetDeviceId;
	}
	
	
}

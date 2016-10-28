package com.howell.ksoap;
/**
 * @author 霍之昊 
 *
 * 类说明：查询健康度信息Response
 */
public class SystemHealthStatisticsRes {
	private String result;
    private SystemHealthStatistics systemHealthStatistics;
	public SystemHealthStatisticsRes(String result,
			SystemHealthStatistics systemHealthStatistics) {
		super();
		this.result = result;
		this.systemHealthStatistics = systemHealthStatistics;
	}
	public SystemHealthStatisticsRes() {
		super();
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public SystemHealthStatistics getSystemHealthStatistics() {
		return systemHealthStatistics;
	}
	public void setSystemHealthStatistics(
			SystemHealthStatistics systemHealthStatistics) {
		this.systemHealthStatistics = systemHealthStatistics;
	}
    

}

package com.howell.utils;

import com.howell.ksoap.SystemFaultStatistics;
import com.howell.ksoap.SystemWarningStatistics;

/**
 * @author 霍之昊 
 *
 * 类说明
 */
public class SystemReportStorage {
	/**  
	 * 单例对象实例  
	 */    
	private static SystemReportStorage instance = new SystemReportStorage();    
	 
	public static SystemReportStorage getInstance() {    
		return instance;    
	}
	
	/*Faults*/
	private SystemFaultStatistics systemFaultStatistics = new SystemFaultStatistics();
    
	/*Warnings*/
	private SystemWarningStatistics systemWarningStatistics = new SystemWarningStatistics();

	public SystemFaultStatistics getSystemFaultStatistics() {
		return systemFaultStatistics;
	}
	public void setSystemFaultStatistics(SystemFaultStatistics systemFaultStatistics) {
		this.systemFaultStatistics = systemFaultStatistics;
	}
	public SystemWarningStatistics getSystemWarningStatistics() {
		return systemWarningStatistics;
	}
	public void setSystemWarningStatistics(
			SystemWarningStatistics systemWarningStatistics) {
		this.systemWarningStatistics = systemWarningStatistics;
	}
	
}

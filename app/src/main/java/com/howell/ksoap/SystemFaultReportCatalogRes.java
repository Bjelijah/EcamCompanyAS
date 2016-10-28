package com.howell.ksoap;

import java.util.ArrayList;

/**
 * @author 霍之昊 
 *
 * 类说明:查询系统故障报告response
 */
public class SystemFaultReportCatalogRes {
	private String result;
	private int page;
	private ArrayList<SystemFaultReport> systemFaultReports;
	public SystemFaultReportCatalogRes(String result, int page,
			ArrayList<SystemFaultReport> systemFaultReports) {
		super();
		this.result = result;
		this.page = page;
		this.systemFaultReports = systemFaultReports;
	}
	public SystemFaultReportCatalogRes() {
		super();
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public ArrayList<SystemFaultReport> getSystemFaultReports() {
		return systemFaultReports;
	}
	public void setSystemFaultReports(
			ArrayList<SystemFaultReport> systemFaultReports) {
		this.systemFaultReports = systemFaultReports;
	}
	

}

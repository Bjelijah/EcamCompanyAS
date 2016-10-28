package com.howell.ksoap;

import java.util.ArrayList;

/**
 * @author 霍之昊 
 *
 * 类说明:查询系统警告报告response
 */
public class SystemWarningReportCatalogRes {
	private String result;
	private int page;
	private ArrayList<SystemWarningReport> systemWarningReports;
	public SystemWarningReportCatalogRes(String result, int page,
			ArrayList<SystemWarningReport> systemWarningReports) {
		super();
		this.result = result;
		this.page = page;
		this.systemWarningReports = systemWarningReports;
	}
	public SystemWarningReportCatalogRes() {
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
	public ArrayList<SystemWarningReport> getSystemWarningReports() {
		return systemWarningReports;
	}
	public void setSystemWarningReports(
			ArrayList<SystemWarningReport> systemWarningReports) {
		this.systemWarningReports = systemWarningReports;
	}

}

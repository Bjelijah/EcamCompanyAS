package com.howell.ksoap;

import java.util.ArrayList;

/**
 * @author 霍之昊 
 *
 * 类说明:存储媒介信息
 */
public class DeviceStorageMediumCatalogRes {
	private String result;
	private Page page;
	private ArrayList<StorageMedium> storageMediums;
	public DeviceStorageMediumCatalogRes(String result, Page page,
			ArrayList<StorageMedium> storageMediums) {
		super();
		this.result = result;
		this.page = page;
		this.storageMediums = storageMediums;
	}
	public DeviceStorageMediumCatalogRes() {
		super();
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	public ArrayList<StorageMedium> getStorageMediums() {
		return storageMediums;
	}
	public void setStorageMediums(ArrayList<StorageMedium> storageMediums) {
		this.storageMediums = storageMediums;
	}
	
}

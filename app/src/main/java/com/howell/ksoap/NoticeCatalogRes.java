package com.howell.ksoap;

import java.util.ArrayList;

/**
 * @author 霍之昊 
 *
 * 类说明
 */
public class NoticeCatalogRes {
	private String result;
	private Page page;
	private ArrayList<Notice> notices;
	public NoticeCatalogRes(String result, Page page, ArrayList<Notice> notices) {
		super();
		this.result = result;
		this.page = page;
		this.notices = notices;
	}
	public NoticeCatalogRes() {
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
	public ArrayList<Notice> getNotices() {
		return notices;
	}
	public void setNotices(ArrayList<Notice> notices) {
		this.notices = notices;
	}

	
}

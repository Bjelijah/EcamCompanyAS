package com.howell.ksoap;

import java.util.ArrayList;

/**
 * @author 霍之昊 
 *
 * 类说明
 */
public class EventSubscriptionCatalogRes {
	private String result;
	private Page page;
	private ArrayList<EventSubscription> eventSubscriptions;
	public EventSubscriptionCatalogRes(String result, Page page,
			ArrayList<EventSubscription> eventSubscriptions) {
		super();
		this.result = result;
		this.page = page;
		this.eventSubscriptions = eventSubscriptions;
	}
	public EventSubscriptionCatalogRes() {
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
	public ArrayList<EventSubscription> getEventSubscriptions() {
		return eventSubscriptions;
	}
	public void setEventSubscriptions(
			ArrayList<EventSubscription> eventSubscriptions) {
		this.eventSubscriptions = eventSubscriptions;
	}
	
}

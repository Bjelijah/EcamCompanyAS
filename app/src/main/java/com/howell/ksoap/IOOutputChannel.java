package com.howell.ksoap;
/**
 * @author 霍之昊 
 *
 * 类说明
 */
public class IOOutputChannel {
	private String id;
	private String name;
	private String triggeringType;
	public IOOutputChannel(String id, String name, String triggeringType) {
		super();
		this.id = id;
		this.name = name;
		this.triggeringType = triggeringType;
	}
	public IOOutputChannel() {
		super();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTriggeringType() {
		return triggeringType;
	}
	public void setTriggeringType(String triggeringType) {
		this.triggeringType = triggeringType;
	}
	
	

}

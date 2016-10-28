package com.howell.ksoap;
/**
 * @author 霍之昊 
 *
 * 类说明
 */
public class IOInputChannel {
	private String id;
	private String name;
	private String probeType;
	private String triggeringType;
	private String defenceZoneId;
	public IOInputChannel(String id, String name, String probeType,
			String triggeringType, String defenceZoneId) {
		super();
		this.id = id;
		this.name = name;
		this.probeType = probeType;
		this.triggeringType = triggeringType;
		this.defenceZoneId = defenceZoneId;
	}
	public IOInputChannel() {
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
	public String getProbeType() {
		return probeType;
	}
	public void setProbeType(String probeType) {
		this.probeType = probeType;
	}
	public String getTriggeringType() {
		return triggeringType;
	}
	public void setTriggeringType(String triggeringType) {
		this.triggeringType = triggeringType;
	}
	public String getDefenceZoneId() {
		return defenceZoneId;
	}
	public void setDefenceZoneId(String defenceZoneId) {
		this.defenceZoneId = defenceZoneId;
	}
	
	

}

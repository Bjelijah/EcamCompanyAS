package com.howell.ksoap;

import java.util.ArrayList;

/**
 * @author 霍之昊 
 *
 * 类说明
 */
public class Notice {
	private String id;
	private String message;
	private String classification;
	private String time;
	private String status;
	private String sender;
	private String componentId;
	private String componentName;
	private ArrayList<String> pictureID;
	public Notice(String id, String message, String classification,
			String time, String status, String sender, String componentId,
			String componentName) {
		super();
		this.id = id;
		this.message = message;
		this.classification = classification;
		this.time = time;
		this.status = status;
		this.sender = sender;
		this.componentId = componentId;
		this.componentName = componentName;
		pictureID = new ArrayList<String>();
	}
	public Notice() {
		super();
		pictureID = new ArrayList<String>();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getClassification() {
		return classification;
	}
	public void setClassification(String classification) {
		this.classification = classification;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getComponentId() {
		return componentId;
	}
	public void setComponentId(String componentId) {
		this.componentId = componentId;
	}
	public String getComponentName() {
		return componentName;
	}
	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}
	public ArrayList<String> getPictureID() {
		return pictureID;
	}
	public void setPictureID(ArrayList<String> pictureID) {
		this.pictureID = pictureID;
	}
	
}

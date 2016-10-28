package com.howell.ksoap;
/**
 * @author 霍之昊 
 *
 * 类说明
 */
public class VideoSourceGroup {
	private String id;
	private String name;
	private String comment;
	private String parentId;
	
	public VideoSourceGroup() {
		super();
	}
	
	public VideoSourceGroup(String id,String name) {
		super();
		this.id =id;
		this.name = name;
	}
	
	public VideoSourceGroup(String id, String name, String comment,
			String parentId) {
		super();
		this.id = id;
		this.name = name;
		this.comment = comment;
		this.parentId = parentId;
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
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	

}

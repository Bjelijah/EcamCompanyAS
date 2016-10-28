package com.howell.ksoap;
/**
 * @author 霍之昊 
 *
 * 类说明
 */
public class GetPictureReq {
	private String session;
	private String pictureID;
	public GetPictureReq(String session, String pictureID) {
		super();
		this.session = session;
		this.pictureID = pictureID;
	}
	public GetPictureReq() {
		super();
	}
	public String getSession() {
		return session;
	}
	public void setSession(String session) {
		this.session = session;
	}
	public String getPictureID() {
		return pictureID;
	}
	public void setPictureID(String pictureID) {
		this.pictureID = pictureID;
	}
	

}

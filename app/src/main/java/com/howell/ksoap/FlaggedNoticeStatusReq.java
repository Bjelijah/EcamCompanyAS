package com.howell.ksoap;
/**
 * @author 霍之昊 
 *
 * 类说明
 */
public class FlaggedNoticeStatusReq {
	private String session;
	private String status;
	private String noticeId;
	public FlaggedNoticeStatusReq(String session, String status,
			String noticeId) {
		super();
		this.session = session;
		this.status = status;
		this.noticeId = noticeId;
	}
	public FlaggedNoticeStatusReq() {
		super();
	}
	public String getSession() {
		return session;
	}
	public void setSession(String session) {
		this.session = session;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getNoticeId() {
		return noticeId;
	}
	public void setNoticeId(String noticeId) {
		this.noticeId = noticeId;
	}

}

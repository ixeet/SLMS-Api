/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scolere.lms.application.rest.vo.request;

/**
 * 
 * @author dell
 */
public class NotificationRequest {
	
	private int userId;
	private String deviceType;
	private String deviceToken;
	private String pushTitle;
	private String pushMessage;

	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getDeviceToken() {
		return deviceToken;
	}

	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
	}

	public String getPushTitle() {
		return pushTitle;
	}

	public void setPushTitle(String pushTitle) {
		this.pushTitle = pushTitle;
	}

	public String getPushMessage() {
		return pushMessage;
	}

	public void setPushMessage(String pushMessage) {
		this.pushMessage = pushMessage;
	}

	
	@Override
	public String toString() {
		return "NotificationRequest [userId=" + userId + ", deviceType="
				+ deviceType + ", deviceToken=" + deviceToken + ", pushTitle="
				+ pushTitle + ", pushMessage=" + pushMessage + "]";
	}

}

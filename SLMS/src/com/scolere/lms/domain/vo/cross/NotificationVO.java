package com.scolere.lms.domain.vo.cross;


public class NotificationVO {
	
	//user-device parameters
	private int userDeviceId;
	private int userId;
	private String deviceType;
	private String deviceToken;
	private String lastUpdatedBy;
	
	
	
	public NotificationVO() {
		super();
	}
	
	
	
	public NotificationVO(int userId, String deviceType, String deviceToken) {
		super();
		this.userId = userId;
		this.deviceType = deviceType;
		this.deviceToken = deviceToken;
	}



	public int getUserDeviceId() {
		return userDeviceId;
	}
	public void setUserDeviceId(int userDeviceId) {
		this.userDeviceId = userDeviceId;
	}
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
	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}
	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}
	
	@Override
	public String toString() {
		return "NotificationVO [userDeviceId=" + userDeviceId + ", userId="
				+ userId + ", deviceType=" + deviceType + ", deviceToken="
				+ deviceToken + ", lastUpdatedBy=" + lastUpdatedBy + "]";
	}
	
	

}

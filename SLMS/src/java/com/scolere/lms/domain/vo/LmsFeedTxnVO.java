package com.scolere.lms.domain.vo;

public class LmsFeedTxnVO {
	
	private int feedID;
	private int feedTypeID;
	private int resourceID;
	private String feedOn;
	private String lastUserIDCd;
	private String lastUpdtTm;
	public int getFeedID() {
		return feedID;
	}
	public void setFeedID(int feedID) {
		this.feedID = feedID;
	}
	public int getFeedTypeID() {
		return feedTypeID;
	}
	public void setFeedTypeID(int feedTypeID) {
		this.feedTypeID = feedTypeID;
	}
	public int getResourceID() {
		return resourceID;
	}
	public void setResourceID(int resourceID) {
		this.resourceID = resourceID;
	}
	public String getFeedOn() {
		return feedOn;
	}
	public void setFeedOn(String feedOn) {
		this.feedOn = feedOn;
	}
	public String getLastUserIDCd() {
		return lastUserIDCd;
	}
	public void setLastUserIDCd(String lastUserIDCd) {
		this.lastUserIDCd = lastUserIDCd;
	}
	public String getLastUpdtTm() {
		return lastUpdtTm;
	}
	public void setLastUpdtTm(String lastUpdtTm) {
		this.lastUpdtTm = lastUpdtTm;
	}
	
	

}

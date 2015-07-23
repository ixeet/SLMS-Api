package com.scolere.lms.domain.vo;

public class LmsFeedLikesVO {
	
	private int feedLikeID;
	private int feedID;
	private int parentCommentID;
	private String likeOn;
	private int associateID;
	private String likeBy;
	private String lastUserIDCd;
	private String lastUpdtTm;
	public int getFeedLikeID() {
		return feedLikeID;
	}
	public void setFeedLikeID(int feedLikeID) {
		this.feedLikeID = feedLikeID;
	}
	public int getFeedID() {
		return feedID;
	}
	public void setFeedID(int feedID) {
		this.feedID = feedID;
	}
	public int getParentCommentID() {
		return parentCommentID;
	}
	public void setParentCommentID(int parentCommentID) {
		this.parentCommentID = parentCommentID;
	}
	public String getLikeOn() {
		return likeOn;
	}
	public void setLikeOn(String likeOn) {
		this.likeOn = likeOn;
	}
	public int getAssociateID() {
		return associateID;
	}
	public void setAssociateID(int associateID) {
		this.associateID = associateID;
	}
	public String getLikeBy() {
		return likeBy;
	}
	public void setLikeBy(String likeBy) {
		this.likeBy = likeBy;
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

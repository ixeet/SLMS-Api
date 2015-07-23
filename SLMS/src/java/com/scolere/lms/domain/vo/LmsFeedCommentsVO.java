package com.scolere.lms.domain.vo;

public class LmsFeedCommentsVO {
	
	private int feedCommentID;
	private int feedID;
	private String commentTxt;
	private String parentCommentID;
	private int associateID;
	private String commentedBy;
	private String lastUserIDCd;
	private String lastUpdtTm;
	
	public int getFeedCommentID() {
		return feedCommentID;
	}
	public void setFeedCommentID(int feedCommentID) {
		this.feedCommentID = feedCommentID;
	}
	public int getFeedID() {
		return feedID;
	}
	public void setFeedID(int feedID) {
		this.feedID = feedID;
	}
	public String getCommentTxt() {
		return commentTxt;
	}
	public void setCommentTxt(String commentTxt) {
		this.commentTxt = commentTxt;
	}
	public String getParentCommentID() {
		return parentCommentID;
	}
	public void setParentCommentID(String parentCommentID) {
		this.parentCommentID = parentCommentID;
	}
	public int getAssociateID() {
		return associateID;
	}
	public void setAssociateID(int associateID) {
		this.associateID = associateID;
	}
	public String getCommentedBy() {
		return commentedBy;
	}
	public void setCommentedBy(String commentedBy) {
		this.commentedBy = commentedBy;
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

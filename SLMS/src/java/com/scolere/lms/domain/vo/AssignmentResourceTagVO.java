package com.scolere.lms.domain.vo;

public class AssignmentResourceTagVO {
	
	private int resourceTagID; 
	private int resourceTxnID; 
	private String tagName;
	private int TagWt;
	private String lastUserIDCd;
	private String lastUpdtTm;
	
	
	public int getResourceTagID() {
		return resourceTagID;
	}
	public void setResourceTagID(int resourceTagID) {
		this.resourceTagID = resourceTagID;
	}
	public int getResourceTxnID() {
		return resourceTxnID;
	}
	public void setResourceTxnID(int resourceTxnID) {
		this.resourceTxnID = resourceTxnID;
	}
	public String getTagName() {
		return tagName;
	}
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	public int getTagWt() {
		return TagWt;
	}
	public void setTagWt(int tagWt) {
		TagWt = tagWt;
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

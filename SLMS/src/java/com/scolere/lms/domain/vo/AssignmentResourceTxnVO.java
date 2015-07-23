package com.scolere.lms.domain.vo;

public class AssignmentResourceTxnVO {
	
	private int resourceTxnID;
	private int assignmentDtlID;
	private String studentID;
	private String uplodedResourcePath;
	private int resourceTypeID;
	private String lastUserIDCd;
	private String lastUpdtTm;
	
	
	public int getResourceTxnID() {
		return resourceTxnID;
	}
	public void setResourceTxnID(int resourceTxnID) {
		this.resourceTxnID = resourceTxnID;
	}
	public int getAssignmentDtlID() {
		return assignmentDtlID;
	}
	public void setAssignmentDtlID(int assignmentDtlID) {
		this.assignmentDtlID = assignmentDtlID;
	}
	public String getStudentID() {
		return studentID;
	}
	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}
	public String getUplodedResourcePath() {
		return uplodedResourcePath;
	}
	public void setUplodedResourcePath(String uplodedResourcePath) {
		this.uplodedResourcePath = uplodedResourcePath;
	}
	public int getResourceTypeID() {
		return resourceTypeID;
	}
	public void setResourceTypeID(int resourceTypeID) {
		this.resourceTypeID = resourceTypeID;
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

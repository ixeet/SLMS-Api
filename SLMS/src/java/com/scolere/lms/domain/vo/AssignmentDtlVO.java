package com.scolere.lms.domain.vo;

public class AssignmentDtlVO {

	private int assignmentDtlID;
	private int AssignmentID;
	private int AssignmentTypID;
	private int displayNo;
	private String lastUserIDCd;
	private String lastUpdtTm;
	
	public String getLastUpdtTm() {
		return lastUpdtTm;
	}
	public void setLastUpdtTm(String lastUpdtTm) {
		this.lastUpdtTm = lastUpdtTm;
	}
	public int getAssignmentDtlID() {
		return assignmentDtlID;
	}
	public void setAssignmentDtlID(int assignmentDtlID) {
		this.assignmentDtlID = assignmentDtlID;
	}
	public int getAssignmentID() {
		return AssignmentID;
	}
	public void setAssignmentID(int assignmentID) {
		AssignmentID = assignmentID;
	}
	public int getAssignmentTypID() {
		return AssignmentTypID;
	}
	public void setAssignmentTypID(int assignmentTypID) {
		AssignmentTypID = assignmentTypID;
	}
	public int getDisplayNo() {
		return displayNo;
	}
	public void setDisplayNo(int displayNo) {
		this.displayNo = displayNo;
	}
	public String getLastUserIDCd() {
		return lastUserIDCd;
	}
	public void setLastUserIDCd(String lastUserIDCd) {
		this.lastUserIDCd = lastUserIDCd;
	}
	
	
}

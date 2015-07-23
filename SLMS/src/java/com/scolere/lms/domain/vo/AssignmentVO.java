package com.scolere.lms.domain.vo;

public class AssignmentVO {
	
	private int assignmentID;    	
	private String assignmentName;
	private int assignmentTypID; 
	private String descTxt;          
	private int displayNo;
	private String enableFl; 
	private String lastUserIDCD;
	private String lastUpdtTm;
	
	public String getLastUpdtTm() {
		return lastUpdtTm;
	}
	public void setLastUpdtTm(String lastUpdtTm) {
		this.lastUpdtTm = lastUpdtTm;
	}
	public int getAssignmentID() {
		return assignmentID;
	}
	public void setAssignmentID(int assignmentID) {
		this.assignmentID = assignmentID;
	}
	public String getAssignmentName() {
		return assignmentName;
	}
	public void setAssignmentName(String assignmentName) {
		this.assignmentName = assignmentName;
	}
	public int getAssignmentTypID() {
		return assignmentTypID;
	}
	public void setAssignmentTypID(int assignmentTypID) {
		this.assignmentTypID = assignmentTypID;
	}
	public String getDescTxt() {
		return descTxt;
	}
	public void setDescTxt(String descTxt) {
		this.descTxt = descTxt;
	}
	public int getDisplayNo() {
		return displayNo;
	}
	public void setDisplayNo(int displayNo) {
		this.displayNo = displayNo;
	}
	
	public String getEnableFl() {
		return enableFl;
	}
	public void setEnableFl(String enableFl) {
		this.enableFl = enableFl;
	}
	public String getLastUserIDCD() {
		return lastUserIDCD;
	}
	public void setLastUserIDCD(String lastUserIDCD) {
		this.lastUserIDCD = lastUserIDCD;
	}
	
	

}

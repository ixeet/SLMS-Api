package com.scolere.lms.domain.vo;

public class AssignmentVdoTxnVO {
	
	private int vdoTxnID;
	private int assignmentDtlID;
	private String studentID;
	private String uplodedVdoPath;
	private String lastUserIDCd;
	private String lastUpdtTm;
	
	public int getVdoTxnID() {
		return vdoTxnID;
	}
	public void setVdoTxnID(int vdoTxnID) {
		this.vdoTxnID = vdoTxnID;
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
	public String getUplodedVdoPath() {
		return uplodedVdoPath;
	}
	public void setUplodedVdoPath(String uplodedVdoPath) {
		this.uplodedVdoPath = uplodedVdoPath;
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

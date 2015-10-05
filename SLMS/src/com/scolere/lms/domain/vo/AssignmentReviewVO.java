package com.scolere.lms.domain.vo;



public class AssignmentReviewVO {
	private int reviewTxnId;
	private int resourceTxnId;
	private int gradeParamId;
	private int gradeValueId;
	private String lastUpdatedBy;
	private String lastUpdatedDt;
	
	
	
	public int getReviewTxnId() {
		return reviewTxnId;
	}
	public void setReviewTxnId(int reviewTxnId) {
		this.reviewTxnId = reviewTxnId;
	}
	public int getResourceTxnId() {
		return resourceTxnId;
	}
	public void setResourceTxnId(int resourceTxnId) {
		this.resourceTxnId = resourceTxnId;
	}
	public int getGradeParamId() {
		return gradeParamId;
	}
	public void setGradeParamId(int gradeParamId) {
		this.gradeParamId = gradeParamId;
	}
	public int getGradeValueId() {
		return gradeValueId;
	}
	public void setGradeValueId(int gradeValueId) {
		this.gradeValueId = gradeValueId;
	}
	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}
	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}
	public String getLastUpdatedDt() {
		return lastUpdatedDt;
	}
	public void setLastUpdatedDt(String lastUpdatedDt) {
		this.lastUpdatedDt = lastUpdatedDt;
	}

}//End of class

package com.scolere.lms.domain.vo;

public class TeacherCourseSessionDtlsVO {
	
	private int courseSessionDtlsID;
	private int courseSessionID;
	private String teacherID;
	private int moduleID;
	private int contentID;
	private String startSessionTm;
	private String endSessionTm;
	private String isCompleted;
	private String lastUserIDCd;
	private String lastUpdtTm;
	public int getCourseSessionDtlsID() {
		return courseSessionDtlsID;
	}
	public void setCourseSessionDtlsID(int courseSessionDtlsID) {
		this.courseSessionDtlsID = courseSessionDtlsID;
	}
	public int getCourseSessionID() {
		return courseSessionID;
	}
	public void setCourseSessionID(int courseSessionID) {
		this.courseSessionID = courseSessionID;
	}
	public String getTeacherID() {
		return teacherID;
	}
	public void setTeacherID(String teacherID) {
		this.teacherID = teacherID;
	}
	public int getModuleID() {
		return moduleID;
	}
	public void setModuleID(int moduleID) {
		this.moduleID = moduleID;
	}
	public int getContentID() {
		return contentID;
	}
	public void setContentID(int contentID) {
		this.contentID = contentID;
	}
	public String getStartSessionTm() {
		return startSessionTm;
	}
	public void setStartSessionTm(String startSessionTm) {
		this.startSessionTm = startSessionTm;
	}
	public String getEndSessionTm() {
		return endSessionTm;
	}
	public void setEndSessionTm(String endSessionTm) {
		this.endSessionTm = endSessionTm;
	}
	public String getIsCompleted() {
		return isCompleted;
	}
	public void setIsCompleted(String isCompleted) {
		this.isCompleted = isCompleted;
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

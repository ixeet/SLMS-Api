package com.scolere.lms.application.rest.vo.response;

import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
*
* @author dell
*/

@JsonSerialize(include=JsonSerialize.Inclusion.NON_DEFAULT)
public class PercentageRespTo {
	private String courseComplete;
	private String courseProgress;
	private String courseNotStarted;
	private String assNotSubmit;
	private String assSubmitted;
	private String assReviewed;
	
	
	public String getCourseComplete() {
		return courseComplete;
	}
	public void setCourseComplete(String courseComplete) {
		this.courseComplete = courseComplete;
	}
	public String getCourseProgress() {
		return courseProgress;
	}
	public void setCourseProgress(String courseProgress) {
		this.courseProgress = courseProgress;
	}
	public String getCourseNotStarted() {
		return courseNotStarted;
	}
	public void setCourseNotStarted(String courseNotStarted) {
		this.courseNotStarted = courseNotStarted;
	}
	public String getAssNotSubmit() {
		return assNotSubmit;
	}
	public void setAssNotSubmit(String assNotSubmit) {
		this.assNotSubmit = assNotSubmit;
	}
	public String getAssSubmitted() {
		return assSubmitted;
	}
	public void setAssSubmitted(String assSubmitted) {
		this.assSubmitted = assSubmitted;
	}
	public String getAssReviewed() {
		return assReviewed;
	}
	public void setAssReviewed(String assReviewed) {
		this.assReviewed = assReviewed;
	}
	
	
	
	
	
}

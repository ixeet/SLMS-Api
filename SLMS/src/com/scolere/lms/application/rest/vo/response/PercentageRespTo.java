package com.scolere.lms.application.rest.vo.response;

import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
*
* @author dell
*/

@JsonSerialize(include=JsonSerialize.Inclusion.NON_DEFAULT)
public class PercentageRespTo {
	private int courseComplete;
	private int courseProgress;
	private int courseNotStarted;
	private int assNotSubmit;
	private int assSubmitted;
	private int assReviewed;
	
	
	public int getCourseComplete() {
		return courseComplete;
	}
	public void setCourseComplete(int courseComplete) {
		this.courseComplete = courseComplete;
	}
	public int getCourseProgress() {
		return courseProgress;
	}
	public void setCourseProgress(int courseProgress) {
		this.courseProgress = courseProgress;
	}
	public int getCourseNotStarted() {
		return courseNotStarted;
	}
	public void setCourseNotStarted(int courseNotStarted) {
		this.courseNotStarted = courseNotStarted;
	}
	public int getAssNotSubmit() {
		return assNotSubmit;
	}
	public void setAssNotSubmit(int assNotSubmit) {
		this.assNotSubmit = assNotSubmit;
	}
	public int getAssSubmitted() {
		return assSubmitted;
	}
	public void setAssSubmitted(int assSubmitted) {
		this.assSubmitted = assSubmitted;
	}
	public int getAssReviewed() {
		return assReviewed;
	}
	public void setAssReviewed(int assReviewed) {
		this.assReviewed = assReviewed;
	}
	
}

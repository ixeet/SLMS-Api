/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scolere.lms.domain.vo.cross;

/**
 * lms_feed_type & lms_feed_txn parameters
 * 
 * @author dell
 */
public class FeedVO {
    
    private String feedTemplate;
    private String tempParam;
    private int feedID;
    private int feedTypeID;
    private String feedRefName;
    private String viewStatus;
    
    private int schoolId;
    private String schoolName;

    private int classId;
    private String className;
    
    private int hrmId;
    private String hrmName;
    
    private int courseId;
    private String courseName;
    
    private int moduleId;
    private String moduleName;
    
    private int resourseId;
    private String resourseName;

    private int assignmentId;
    private String assignmentName;
    
    private int userId;
    private String userName;

    private String startActivityDate;
    private String activityOn;
    private String endActivityDate;

    private int likeCounts;
    private int shareCounts;
    private int commentCounts;
    private boolean isLiked;

    public int getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(int assignmentId) {
        this.assignmentId = assignmentId;
    }

    public String getAssignmentName() {
        return assignmentName;
    }

    public void setAssignmentName(String assignmentName) {
        this.assignmentName = assignmentName;
    }

    
    //setter-getters

    public String getViewStatus() {
		return viewStatus;
	}

	public void setViewStatus(String viewStatus) {
		this.viewStatus = viewStatus;
	}

	public int getFeedID() {
        return feedID;
    }

    public void setFeedID(int feedID) {
        this.feedID = feedID;
    }
    
    public int getLikeCounts() {
        return likeCounts;
    }

    public void setLikeCounts(int likeCounts) {
        this.likeCounts = likeCounts;
    }

    public int getShareCounts() {
        return shareCounts;
    }

    public void setShareCounts(int shareCounts) {
        this.shareCounts = shareCounts;
    }

    public int getCommentCounts() {
        return commentCounts;
    }

    public void setCommentCounts(int commentCounts) {
        this.commentCounts = commentCounts;
    }

    public boolean isIsLiked() {
        return isLiked;
    }

    public void setIsLiked(boolean isLiked) {
        this.isLiked = isLiked;
    }

    public String getFeedTemplate() {
        return feedTemplate;
    }

    public void setFeedTemplate(String feedTemplate) {
        this.feedTemplate = feedTemplate;
    }

    public String getTempParam() {
        return tempParam;
    }

    public void setTempParam(String tempParam) {
        this.tempParam = tempParam;
    }

    public int getFeedTypeID() {
        return feedTypeID;
    }

    public void setFeedTypeID(int feedTypeID) {
        this.feedTypeID = feedTypeID;
    }

    public String getFeedRefName() {
        return feedRefName;
    }

    public void setFeedRefName(String feedRefName) {
        this.feedRefName = feedRefName;
    }
    

    public int getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(int schoolId) {
        this.schoolId = schoolId;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getHrmId() {
        return hrmId;
    }

    public void setHrmId(int hrmId) {
        this.hrmId = hrmId;
    }

    public String getHrmName() {
        return hrmName;
    }

    public void setHrmName(String hrmName) {
        this.hrmName = hrmName;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getModuleId() {
        return moduleId;
    }

    public void setModuleId(int moduleId) {
        this.moduleId = moduleId;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public int getResourseId() {
        return resourseId;
    }

    public void setResourseId(int resourseId) {
        this.resourseId = resourseId;
    }

    public String getResourseName() {
        return resourseName;
    }

    public void setResourseName(String resourseName) {
        this.resourseName = resourseName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getStartActivityDate() {
        return startActivityDate;
    }

    public void setStartActivityDate(String startActivityDate) {
        this.startActivityDate = startActivityDate;
    }

    public String getActivityOn() {
        return activityOn;
    }

    public void setActivityOn(String activityOn) {
        this.activityOn = activityOn;
    }

    public String getEndActivityDate() {
        return endActivityDate;
    }

    public void setEndActivityDate(String endActivityDate) {
        this.endActivityDate = endActivityDate;
    }

    @Override
    public String toString() {
        return "FeedVO{" + "feedTemplate=" + feedTemplate + ", tempParam=" + tempParam + ", feedID=" + feedID + ", feedTypeID=" + feedTypeID + ", feedRefName=" + feedRefName + ", schoolId=" + schoolId + ", schoolName=" + schoolName + ", classId=" + classId + ", className=" + className + ", hrmId=" + hrmId + ", hrmName=" + hrmName + ", courseId=" + courseId + ", courseName=" + courseName + ", moduleId=" + moduleId + ", moduleName=" + moduleName + ", resourseId=" + resourseId + ", resourseName=" + resourseName + ", assignmentId=" + assignmentId + ", assignmentName=" + assignmentName + ", userId=" + userId + ", userName=" + userName + ", startActivityDate=" + startActivityDate + ", activityOn=" + activityOn + ", endActivityDate=" + endActivityDate + ", likeCounts=" + likeCounts + ", shareCounts=" + shareCounts + ", commentCounts=" + commentCounts + ", isLiked=" + isLiked + '}';
    }

    
    
    
}

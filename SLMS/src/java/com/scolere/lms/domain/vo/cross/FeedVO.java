/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scolere.lms.domain.vo.cross;

/**
 *
 * @author dell
 */
public class FeedVO {
    private String messageTemplate;
    
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
    
    private int userId;
    private String userName;

    private String startActivityDate;
    private String activityOn;
    private String endActivityDate;

    private int likeCounts;
    private int shareCounts;
    private int commentCounts;
    private boolean isLiked;
    
    
    
    //setter-getters

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
    
    
    
    public String getMessageTemplate() {
        return messageTemplate;
    }

    public void setMessageTemplate(String messageTemplate) {
        this.messageTemplate = messageTemplate;
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

    
    
    
}

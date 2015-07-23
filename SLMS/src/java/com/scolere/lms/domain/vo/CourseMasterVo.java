/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scolere.lms.domain.vo;

/**
 *
 * @author admin
 */
public class CourseMasterVo {
    private int courseId;
    private String courseName;
    private String courseAuthor;
    private String courseDuration;
    private String createdDt;
    private String descTxt;
    private String metadata;
    private String DeletedFl;
    private int displayNo;
    private String enableFl;
    private String createdBy;
    private String lastUserIdCd;
    private String lastUpdtTm;

    public CourseMasterVo() {
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

    public String getCourseAuthor() {
        return courseAuthor;
    }

    public void setCourseAuthor(String courseAuthor) {
        this.courseAuthor = courseAuthor;
    }

    public String getCourseDuration() {
        return courseDuration;
    }

    public void setCourseDuration(String courseDuration) {
        this.courseDuration = courseDuration;
    }

    public String getCreatedDt() {
        return createdDt;
    }

    public void setCreatedDt(String createdDt) {
        this.createdDt = createdDt;
    }

    public String getDescTxt() {
        return descTxt;
    }

    public void setDescTxt(String descTxt) {
        this.descTxt = descTxt;
    }

    public String getMetadata() {
        return metadata;
    }

    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    public String getDeletedFl() {
        return DeletedFl;
    }

    public void setDeletedFl(String DeletedFl) {
        this.DeletedFl = DeletedFl;
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

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getLastUserIdCd() {
        return lastUserIdCd;
    }

    public void setLastUserIdCd(String lastUserIdCd) {
        this.lastUserIdCd = lastUserIdCd;
    }

    public String getLastUpdtTm() {
        return lastUpdtTm;
    }

    public void setLastUpdtTm(String lastUpdtTm) {
        this.lastUpdtTm = lastUpdtTm;
    }
    
}

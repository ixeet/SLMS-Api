package com.scolere.lms.domain.vo;

public class TeacherCourseSessionVO
{
    private int courseSessionID;
    private int TeacherCourseID;
    private String startSessionTm;
    private String endSessionTm;
    private String statusTxt;
    private String lastUseridCd;
    private String lastUpdateTm;
    
    public int getCourseSessionID() {
        return courseSessionID;
    }

    public void setCourseSessionID(int courseSessionID) {
        this.courseSessionID = courseSessionID;
    }

    public int getTeacherCourseID() {
        return TeacherCourseID;
    }

    public void setTeacherCourseID(int TeacherCourseID) {
        this.TeacherCourseID = TeacherCourseID;
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

    public String getStatusTxt() {
        return statusTxt;
    }

    public void setStatusTxt(String statusTxt) {
        this.statusTxt = statusTxt;
    }

    public String getLastUseridCd() {
        return lastUseridCd;
    }

    public void setLastUseridCd(String lastUseridCd) {
        this.lastUseridCd = lastUseridCd;
    }

    public String getLastUpdateTm() {
        return lastUpdateTm;
    }

    public void setLastUpdateTm(String lastUpdateTm) {
        this.lastUpdateTm = lastUpdateTm;
    }
   
}

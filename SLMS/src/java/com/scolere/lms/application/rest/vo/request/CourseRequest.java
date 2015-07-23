/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scolere.lms.application.rest.vo.request;

/**
 *
 * @author dell
 */
public class CourseRequest {
    
    private String userName;
    private int userId;
    private String courseId;
    private String moduleId;
    
    private String searchText;
    

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    
    @Override
    public String toString() {
        return "CourseRequest{" + "userName=" + userName + ", userId=" + userId + ", courseId=" + courseId + ", moduleId=" + moduleId + ", searchText=" + searchText + '}';
    }
        
}

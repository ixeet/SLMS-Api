/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scolere.lms.application.rest.vo.request;

import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 *
 * @author dell
 */
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class UserRequest {

    private String userid;
    private String userFbId;
    private String userName;
    private String firstName;
    private String lastName;
    private String emailId;
    private String adminEmailId;
    private String schoolId;
    private String schoolName;
    private String address;
    private String classId;
    private String className;
    private String homeRoomId;
    private String homeRoomName;
    private String userPassword="";
    private String userNewPassword="";
    private String title;
    private String isFollowUpAllowed;
    //User access var
    private List<UserRequest> usersList;
    
    


	public String getIsFollowUpAllowed() {
		return isFollowUpAllowed;
	}

	public void setIsFollowUpAllowed(String isFollowUpAllowed) {
		this.isFollowUpAllowed = isFollowUpAllowed;
	}

	public List<UserRequest> getUsersList() {
		return usersList;
	}

	public void setUsersList(List<UserRequest> usersList) {
		this.usersList = usersList;
	}

	public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUserFbId() {
        return userFbId;
    }

    public void setUserFbId(String userFbId) {
        this.userFbId = userFbId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getHomeRoomId() {
        return homeRoomId;
    }

    public void setHomeRoomId(String homeRoomId) {
        this.homeRoomId = homeRoomId;
    }

    public String getHomeRoomName() {
        return homeRoomName;
    }

    public void setHomeRoomName(String homeRoomName) {
        this.homeRoomName = homeRoomName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserNewPassword() {
        return userNewPassword;
    }

    public void setUserNewPassword(String userNewPassword) {
        this.userNewPassword = userNewPassword;
    }

    public String getAdminEmailId() {
        return adminEmailId;
    }

    public void setAdminEmailId(String adminEmailId) {
        this.adminEmailId = adminEmailId;
    }

    @Override
    public String toString() {
        return "UserRequest{" + "userid=" + userid + ", userFbId=" + userFbId + ", userName=" + userName + ", firstName=" + firstName + ", lastName=" + lastName + ", emailId=" + emailId + ", adminEmailId=" + adminEmailId + ", schoolId=" + schoolId + ", schoolName=" + schoolName + ", address=" + address + ", classId=" + classId + ", className=" + className + ", homeRoomId=" + homeRoomId + ", homeRoomName=" + homeRoomName + ", userPassword=" + userPassword + ", userNewPassword=" + userNewPassword + ", title=" + title + '}';
    }

    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scolere.lms.application.rest.vo.common;

/**
 *
 * @author dell
 */
public class UserTO {
    private String userid;
    private String userFbId;
    private String userName;
    private String firstName;
    private String lastName;
    private String emailId;
    private String schoolId;
    private String schoolName;
    private String address;
    private String classId;
    private String className;
    private String homeRoomId;
    private String homeRoomName;
    
    
    

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

    @Override
    public String toString() {
        return "UserTO{" + "userid=" + userid + ", userFbId=" + userFbId + ", userName=" + userName + ", firstName=" + firstName + ", lastName=" + lastName + ", emailId=" + emailId + ", schoolId=" + schoolId + ", schoolName=" + schoolName + ", address=" + address + ", classId=" + classId + ", className=" + className + ", homeRoomId=" + homeRoomId + ", homeRoomName=" + homeRoomName + '}';
    }

    
    
    
}

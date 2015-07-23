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
public class TeacherDetailVo {
     private int teacherDetailId;
    private int userId;
    private String fName;
    private String lName;
    private String emailId;
    private String contactNo;
    private String birthDt;
    private String joiningDt;
    private String experience;
    private String contractExpDt;
    private String address;
    private String lastUserIdCd;
    private String lastUpdtTm;

    public TeacherDetailVo() {
    }

    public int getTeacherDetailId() {
        return teacherDetailId;
    }

    public void setTeacherDetailId(int teacherDetailId) {
        this.teacherDetailId = teacherDetailId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getBirthDt() {
        return birthDt;
    }

    public void setBirthDt(String birthDt) {
        this.birthDt = birthDt;
    }

    public String getJoiningDt() {
        return joiningDt;
    }

    public void setJoiningDt(String joiningDt) {
        this.joiningDt = joiningDt;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getContractExpDt() {
        return contractExpDt;
    }

    public void setContractExpDt(String contractExpDt) {
        this.contractExpDt = contractExpDt;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

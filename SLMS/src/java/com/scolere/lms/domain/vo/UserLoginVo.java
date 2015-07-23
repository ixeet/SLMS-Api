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
public class UserLoginVo {
    
    private int userId;
    private String userName;
    private String userPwd;
    private String userFbId;
    private int userTypeId;
    private String deletedFl;
    private String enableFl;
    private String lastUserIdCd;
    private String lastUpdtTm;

    public UserLoginVo () {
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

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getUserFbId() {
        return userFbId;
    }

    public void setUserFbId(String userFbId) {
        this.userFbId = userFbId;
    }

    public int getUserTypeId() {
        return userTypeId;
    }

    public void setUserTypeId(int userTypeId) {
        this.userTypeId = userTypeId;
    }

    public String getDeletedFl() {
        return deletedFl;
    }

    public void setDeletedFl(String deletedFl) {
        this.deletedFl = deletedFl;
    }

    public String getEnableFl() {
        return enableFl;
    }

    public void setEnableFl(String enableFl) {
        this.enableFl = enableFl;
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



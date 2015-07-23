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
public class UserClassMapVo {
    private int userId;
    private int schoolId;
    private int classId;
    private int homeRoomMasterId;

    public UserClassMapVo() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(int schoolId) {
        this.schoolId = schoolId;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public int getHomeRoomMasterId() {
        return homeRoomMasterId;
    }

    public void setHomeRoomMasterId(int homeRoomMasterId) {
        this.homeRoomMasterId = homeRoomMasterId;
    }
    
}

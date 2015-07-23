/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scolere.lms.application.rest.vo.response;

/**
 *
 * @author dell
 */
public class HomeRoomRespTO {
    
    private String homeRoomId;
    private String homeRoomName;  

    public HomeRoomRespTO() {
    }

    public HomeRoomRespTO(String homeRoomId, String homeRoomName) {
        this.homeRoomId = homeRoomId;
        this.homeRoomName = homeRoomName;
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
        return "HomeRoomRespTO{" + "homeRoomId=" + homeRoomId + ", homeRoomName=" + homeRoomName + '}';
    }
    
    
}

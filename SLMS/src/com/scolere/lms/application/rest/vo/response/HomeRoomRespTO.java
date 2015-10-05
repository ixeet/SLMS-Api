/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scolere.lms.application.rest.vo.response;

import java.util.List;

/**
 *
 * @author dell
 */
public class HomeRoomRespTO {
    
    private String homeRoomId;
    private String homeRoomName;  
    
    private List<CourseRespTO> courseList;

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

	public List<CourseRespTO> getCourseList() {
		return courseList;
	}

	public void setCourseList(List<CourseRespTO> courseList) {
		this.courseList = courseList;
	}
    
    
}

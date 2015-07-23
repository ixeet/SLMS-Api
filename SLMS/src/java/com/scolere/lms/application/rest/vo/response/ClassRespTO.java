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
public class ClassRespTO {
    
    private String classId;
    private String className;
    private List<HomeRoomRespTO> HomeRoomList;     

    
    public ClassRespTO() {
    }

    public ClassRespTO(String classId, String className) {
        this.classId = classId;
        this.className = className;
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

    public List<HomeRoomRespTO> getHomeRoomList() {
        return HomeRoomList;
    }

    public void setHomeRoomList(List<HomeRoomRespTO> HomeRoomList) {
        this.HomeRoomList = HomeRoomList;
    }

    @Override
    public String toString() {
        return "ClassRespTO{" + "classId=" + classId + ", className=" + className + ", Size of HomeRoomList=" + HomeRoomList.size() + '}';
    }
    
    
    
    
}//End of class

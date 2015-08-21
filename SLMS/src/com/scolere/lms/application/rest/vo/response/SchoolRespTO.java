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
public class SchoolRespTO {
    private String schoolId;
    private String schoolName;
    private List<ClassRespTO> classList; 

    public SchoolRespTO() {
    }

    public SchoolRespTO(String schoolId, String schoolName) {
        this.schoolId = schoolId;
        this.schoolName = schoolName;
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

    public List<ClassRespTO> getClassList() {
        return classList;
    }

    public void setClassList(List<ClassRespTO> classList) {
        this.classList = classList;
    }

    @Override
    public String toString() {
        return "SchoolRespTO{" + "schoolId=" + schoolId + ", schoolName=" + schoolName + ", size of classList=" + classList.size() + '}';
    }
    
    
    
    
}//End of class

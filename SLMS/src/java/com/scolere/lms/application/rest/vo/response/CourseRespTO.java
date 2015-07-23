/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scolere.lms.application.rest.vo.response;

import java.util.List;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 *
 * @author dell
 */
@JsonSerialize(include=JsonSerialize.Inclusion.NON_DEFAULT)
public class CourseRespTO extends CompletedStatusTO{
    private String courseId;
    private String courseName;
    private List<ModuleRespTO> moduleList;

    
    
    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public List<ModuleRespTO> getModuleList() {
        return moduleList;
    }

    public void setModuleList(List<ModuleRespTO> moduleList) {
        this.moduleList = moduleList;
    }
    
    
    
}

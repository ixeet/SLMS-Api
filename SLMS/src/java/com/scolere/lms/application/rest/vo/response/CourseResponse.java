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
public class CourseResponse extends CommonRespTO{
    private List<CourseRespTO> courseList;
    private List<ResourceRespTO> resourceList;
    private List<AssignmentRespTO> assignmentList;

    
    
    public List<CourseRespTO> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<CourseRespTO> courseList) {
        this.courseList = courseList;
    }

    public List<ResourceRespTO> getResourceList() {
        return resourceList;
    }

    public void setResourceList(List<ResourceRespTO> resourceList) {
        this.resourceList = resourceList;
    }

    public List<AssignmentRespTO> getAssignmentList() {
        return assignmentList;
    }

    public void setAssignmentList(List<AssignmentRespTO> assignmentList) {
        this.assignmentList = assignmentList;
    }


    
    
}//End of class

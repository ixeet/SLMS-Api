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
public class CommonResponse extends CommonRespTO{
    
    private List<SchoolRespTO> schoolList;
    private List<FeedRespTO> feedList;
    
    private UserResponse userDetail;
    private CourseRespTO courseDetail;
    private ModuleRespTO moduleDetail;
    private AssignmentRespTO assignmentDetail;
    private ResourceRespTO resourceDetail;
    
    
    //Getter-setters

    public UserResponse getUserDetail() {
        return userDetail;
    }

    public void setUserDetail(UserResponse userDetail) {
        this.userDetail = userDetail;
    }

    public CourseRespTO getCourseDetail() {
        return courseDetail;
    }

    public void setCourseDetail(CourseRespTO courseDetail) {
        this.courseDetail = courseDetail;
    }

    public ModuleRespTO getModuleDetail() {
        return moduleDetail;
    }

    public void setModuleDetail(ModuleRespTO moduleDetail) {
        this.moduleDetail = moduleDetail;
    }

    public AssignmentRespTO getAssignmentDetail() {
        return assignmentDetail;
    }

    public void setAssignmentDetail(AssignmentRespTO assignmentDetail) {
        this.assignmentDetail = assignmentDetail;
    }

    public ResourceRespTO getResourceDetail() {
        return resourceDetail;
    }

    public void setResourceDetail(ResourceRespTO resourceDetail) {
        this.resourceDetail = resourceDetail;
    }
    
    public List<FeedRespTO> getFeedList() {
        return feedList;
    }

    public void setFeedList(List<FeedRespTO> feedList) {
        this.feedList = feedList;
    }
    
    public List<SchoolRespTO> getSchoolList() {
        return schoolList;
    }

    public void setSchoolList(List<SchoolRespTO> schoolList) {
        this.schoolList = schoolList;
    }

    
}//End of class

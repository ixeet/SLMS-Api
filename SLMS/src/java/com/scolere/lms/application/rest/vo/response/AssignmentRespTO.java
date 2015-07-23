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
public class AssignmentRespTO {
    private int assignmentId;
    private String assignmentName;
    private String assignmentStatus;
    private String assignmentSubmittedDate;
    private String assignmentSubmittedBy;

    private List<ResourceRespTO> attachedResources;
    

    public int getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(int assignmentId) {
        this.assignmentId = assignmentId;
    }

    public String getAssignmentName() {
        return assignmentName;
    }

    public void setAssignmentName(String assignmentName) {
        this.assignmentName = assignmentName;
    }

    public String getAssignmentStatus() {
        return assignmentStatus;
    }

    public void setAssignmentStatus(String assignmentStatus) {
        this.assignmentStatus = assignmentStatus;
    }

    public String getAssignmentSubmittedDate() {
        return assignmentSubmittedDate;
    }

    public void setAssignmentSubmittedDate(String assignmentSubmittedDate) {
        this.assignmentSubmittedDate = assignmentSubmittedDate;
    }

    public String getAssignmentSubmittedBy() {
        return assignmentSubmittedBy;
    }

    public void setAssignmentSubmittedBy(String assignmentSubmittedBy) {
        this.assignmentSubmittedBy = assignmentSubmittedBy;
    }

    public List<ResourceRespTO> getAttachedResources() {
        return attachedResources;
    }

    public void setAttachedResources(List<ResourceRespTO> attachedResources) {
        this.attachedResources = attachedResources;
    }
    
    
    
}//End of class

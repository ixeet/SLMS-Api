/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scolere.lms.domain.vo.cross;

/**
 *
 * @author dell
 */
public class AssignmentVO {
    private int assignmentId;
    private String assignmentName;
    private String assignmentDesc;
    private String assignmentStatus;
    private String assignmentSubmittedDate;
    private String assignmentSubmittedBy;
    private String assignmentDueDate;
    
    //These parameter has been aded for Assignment uses- 
    private int courseId;
    private String courseName;
    private int moduleId;
    private String moduleName;
        
    
    
    public String getAssignmentDesc() {
		return assignmentDesc;
	}

	public void setAssignmentDesc(String assignmentDesc) {
		this.assignmentDesc = assignmentDesc;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public int getModuleId() {
		return moduleId;
	}

	public void setModuleId(int moduleId) {
		this.moduleId = moduleId;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getAssignmentDueDate() {
        return assignmentDueDate;
    }

    public void setAssignmentDueDate(String assignmentDueDate) {
        this.assignmentDueDate = assignmentDueDate;
    }
    
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
    
    
    
}

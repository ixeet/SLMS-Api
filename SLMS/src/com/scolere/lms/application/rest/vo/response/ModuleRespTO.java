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
public class ModuleRespTO extends CompletedStatusTO{
    private int moduleSessionId;
    
	private String courseId;
    private String moduleId;
    private String moduleName;
    private String moduleDesc;
    List<ResourceRespTO> resourceList;
    private List<AssignmentRespTO> assignmentList;
    
    //For modules assignments enable status
    private String assignmentEnableStatus;
    
    
    public ModuleRespTO(String moduleId, String moduleName) {
        this.moduleId = moduleId;
        this.moduleName = moduleName;
    }

    public ModuleRespTO() {
    }

    public String toString (){
    	
    	return "ModuleRespTO{"+" moduleId="+moduleId+", moduleName="+moduleName+'}';
    }
    
   
    
    public String getModuleDesc() {
		return moduleDesc;
	}

	public void setModuleDesc(String moduleDesc) {
		this.moduleDesc = moduleDesc;
	}

	public String getAssignmentEnableStatus() {
		return assignmentEnableStatus;
	}

	public void setAssignmentEnableStatus(String assignmentEnableStatus) {
		this.assignmentEnableStatus = assignmentEnableStatus;
	}

	public int getModuleSessionId() {
		return moduleSessionId;
	}

	public void setModuleSessionId(int moduleSessionId) {
		this.moduleSessionId = moduleSessionId;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public List<AssignmentRespTO> getAssignmentList() {
        return assignmentList;
    }

    public void setAssignmentList(List<AssignmentRespTO> assignmentList) {
        this.assignmentList = assignmentList;
    }

    
    public List<ResourceRespTO> getResourceList() {
        return resourceList;
    }

    public void setResourceList(List<ResourceRespTO> resourceList) {
        this.resourceList = resourceList;
    }
   
    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }
    
    
}//End of class

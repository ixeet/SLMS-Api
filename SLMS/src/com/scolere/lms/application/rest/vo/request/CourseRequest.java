/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scolere.lms.application.rest.vo.request;

import java.util.List;

import com.scolere.lms.application.rest.vo.response.KeyValTypeVO;

/**
 *
 * @author dell
 */
public class CourseRequest {

    private int moduleSessionId;

    private String userName;
    private int userId;
    private int commentId;
    private int schoolId=1;
    private int classId;
    private int hrmId;
    private int courseId;
    private int moduleId;
    private int resourceId;
    private String searchText;
    private String commentText;
    private int status;
    
    private List<KeyValTypeVO> ratingParameters;
    private int assignmentResourceTxnId;
    
    
    
    
    public List<KeyValTypeVO> getRatingParameters() {
		return ratingParameters;
	}

	public void setRatingParameters(List<KeyValTypeVO> ratingParameters) {
		this.ratingParameters = ratingParameters;
	}

	public int getAssignmentResourceTxnId() {
		return assignmentResourceTxnId;
	}

	public void setAssignmentResourceTxnId(int assignmentResourceTxnId) {
		this.assignmentResourceTxnId = assignmentResourceTxnId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(int schoolId) {
		this.schoolId = schoolId;
	}

	public int getClassId() {
		return classId;
	}

	public void setClassId(int classId) {
		this.classId = classId;
	}

	public int getHrmId() {
		return hrmId;
	}

	public void setHrmId(int hrmId) {
		this.hrmId = hrmId;
	}

	public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }
    
    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }
       
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getModuleId() {
        return moduleId;
    }

    public void setModuleId(int moduleId) {
        this.moduleId = moduleId;
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

	@Override
	public String toString() {
		return "CourseRequest [userName=" + userName + ", userId=" + userId
				+ ", commentId=" + commentId + ", schoolId=" + schoolId
				+ ", classId=" + classId + ", hrmId=" + hrmId + ", courseId="
				+ courseId + ", moduleId=" + moduleId + ", resourceId="
				+ resourceId + ", searchText=" + searchText + ", commentText="
				+ commentText + ", status=" + status + "]";
	}

	public int getModuleSessionId() {
		return moduleSessionId;
	}

	public void setModuleSessionId(int moduleSessionId) {
		this.moduleSessionId = moduleSessionId;
	}

        
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scolere.lms.application.rest.vo.request;

/**
 *
 * @author dell
 */
public class CommonRequest {
    private String userName;
    private int userId;
    private int commentId;
    private int resourceId;
    private int courseId;
    private int moduleId;
    private int feedId;
    private int schoolId;
    private int classId;
    private int hrmId;
    private String searchText;
    private String commentText;
    
    //Pagination parameter
    private int offset;
    private int noOfRecords;
    
    
    public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getNoOfRecords() {
		return noOfRecords;
	}

	public void setNoOfRecords(int noOfRecords) {
		this.noOfRecords = noOfRecords;
	}

	public int getFeedId() {
		return feedId;
	}

	public void setFeedId(int feedId) {
		this.feedId = feedId;
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

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
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

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    
	@Override
	public String toString() {
		return "CommonRequest [userName=" + userName + ", userId=" + userId
				+ ", commentId=" + commentId + ", resourceId=" + resourceId
				+ ", courseId=" + courseId + ", moduleId=" + moduleId
				+ ", feedId=" + feedId + ", searchText=" + searchText
				+ ", commentText=" + commentText + ", offset=" + offset
				+ ", noOfRecords=" + noOfRecords + "]";
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

	
    
}

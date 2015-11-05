package com.scolere.lms.application.rest.vo.response;

import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;


@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class SearchResponse extends CommonRespTO{
	
	private String category;
	private List<CourseRespTO> courseList;
	private List<UserResponse> usersList;
	private List<FeedRespTO> feedList;
    private List<AssignmentRespTO> assignmentList;
    
    private List<SearchResponse> searchList;
    //Total record counts
    private String totalCoursesCount;
    private String totalUsersCount;
    private String totalFeedsCount;
    private String totalAssignmentsCount;
    
    
    //Getters-Setters
    
    
    
	public String getCategory() {
		return category;
	}

	public String getTotalCoursesCount() {
		return totalCoursesCount;
	}

	public void setTotalCoursesCount(String totalCoursesCount) {
		this.totalCoursesCount = totalCoursesCount;
	}

	public String getTotalUsersCount() {
		return totalUsersCount;
	}

	public void setTotalUsersCount(String totalUsersCount) {
		this.totalUsersCount = totalUsersCount;
	}

	public String getTotalFeedsCount() {
		return totalFeedsCount;
	}

	public void setTotalFeedsCount(String totalFeedsCount) {
		this.totalFeedsCount = totalFeedsCount;
	}

	public String getTotalAssignmentsCount() {
		return totalAssignmentsCount;
	}

	public void setTotalAssignmentsCount(String totalAssignmentsCount) {
		this.totalAssignmentsCount = totalAssignmentsCount;
	}

	public List<SearchResponse> getSearchList() {
		return searchList;
	}
	public void setSearchList(List<SearchResponse> searchList) {
		this.searchList = searchList;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public List<CourseRespTO> getCourseList() {
		return courseList;
	}
	public void setCourseList(List<CourseRespTO> courseList) {
		this.courseList = courseList;
	}
	public List<UserResponse> getUsersList() {
		return usersList;
	}
	public void setUsersList(List<UserResponse> usersList) {
		this.usersList = usersList;
	}
	public List<FeedRespTO> getFeedList() {
		return feedList;
	}
	public void setFeedList(List<FeedRespTO> feedList) {
		this.feedList = feedList;
	}
	public List<AssignmentRespTO> getAssignmentList() {
		return assignmentList;
	}
	public void setAssignmentList(List<AssignmentRespTO> assignmentList) {
		this.assignmentList = assignmentList;
	}	
	
    
    
}

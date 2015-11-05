package com.scolere.lms.domain.vo.cross;


public class SearchVO {
	
	private String searchCategory;
	//User related variables
	private String userId;
	private String userName;
	private String profileImage;
	
	//Course related
    private String courseId;
    private String courseName;
    private String courseDesc;
    
    //Assignment related
    private int assignmentId;
    private String assignmentName;
    private String assignmentDesc;
    
    //Total record counts
    private int totalCoursesCount;
    private int totalUsersCount;
    private int totalFeedsCount;
    private int totalAssignmentsCount;    
    
    
    //getters-setters
    
    
    
	public String getSearchCategory() {
		return searchCategory;
	}
	public int getTotalCoursesCount() {
		return totalCoursesCount;
	}
	public void setTotalCoursesCount(int totalCoursesCount) {
		this.totalCoursesCount = totalCoursesCount;
	}
	public int getTotalUsersCount() {
		return totalUsersCount;
	}
	public void setTotalUsersCount(int totalUsersCount) {
		this.totalUsersCount = totalUsersCount;
	}
	public int getTotalFeedsCount() {
		return totalFeedsCount;
	}
	public void setTotalFeedsCount(int totalFeedsCount) {
		this.totalFeedsCount = totalFeedsCount;
	}
	public int getTotalAssignmentsCount() {
		return totalAssignmentsCount;
	}
	public void setTotalAssignmentsCount(int totalAssignmentsCount) {
		this.totalAssignmentsCount = totalAssignmentsCount;
	}
	public void setSearchCategory(String searchCategory) {
		this.searchCategory = searchCategory;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getProfileImage() {
		return profileImage;
	}
	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}
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
	public String getCourseDesc() {
		return courseDesc;
	}
	public void setCourseDesc(String courseDesc) {
		this.courseDesc = courseDesc;
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
	public String getAssignmentDesc() {
		return assignmentDesc;
	}
	public void setAssignmentDesc(String assignmentDesc) {
		this.assignmentDesc = assignmentDesc;
	}
    
    
    
}//End of class

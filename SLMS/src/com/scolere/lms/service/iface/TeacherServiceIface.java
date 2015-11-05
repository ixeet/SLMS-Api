package com.scolere.lms.service.iface;

import java.util.List;

import com.scolere.lms.domain.exception.LmsServiceException;


public interface TeacherServiceIface {

	int updateCourseStatus(int courseSessionId,int statusCode) throws LmsServiceException;
	int updateCourseModuleStatus(int moduleSessionId,int statusCode) throws LmsServiceException;
	int updateCourseResourceStatus(int resourseSessionId,int statusCode) throws LmsServiceException;
	List<Integer> getCoursePercentage(String userName, int schoolId, int classId,int hrmId)throws LmsServiceException;
	List<Integer> getAssignmentPercentage(String userName, int schoolId,int classId, int hrmId)throws LmsServiceException;
	int updateAssignmentStatus(int schoolId, int classId, int hrmId,int courseId, int moduleId, int statusCode, String userNm,
			String dueDate)throws LmsServiceException;
	
	
}

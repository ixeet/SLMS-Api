package com.scolere.lms.persistance.dao.iface;

import java.util.List;

import com.scolere.lms.application.rest.vo.response.PercentageRespTo;
import com.scolere.lms.domain.exception.LmsDaoException;



public interface TeacherDao {

	int updateCourseStatus(int courseSessionId) throws LmsDaoException;
	int updateCourseModuleStatus(int moduleSessionId,int statusCode) throws LmsDaoException;
	int updateCourseResourceStatus(int resourseSessionId,int statusCode) throws LmsDaoException;
	List<Integer> getCoursePercentage(String userName, int schoolId, int classId, int hrmId)throws LmsDaoException;
	List<Integer> getAssignmentPercentage(String userName, int schoolId,int classId, int hrmId)throws LmsDaoException;
	
}

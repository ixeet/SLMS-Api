package com.scolere.lms.persistance.dao.iface;

import com.scolere.lms.domain.exception.LmsDaoException;



public interface TeacherDao {

	int updateCourseStatus(int courseSessionId) throws LmsDaoException;
	int updateCourseModuleStatus(int moduleSessionId,int statusCode) throws LmsDaoException;
	int updateCourseResourceStatus(int resourseSessionId,int statusCode) throws LmsDaoException;
	
}

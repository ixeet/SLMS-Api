package com.scolere.lms.application.rest.bus.iface;

import com.scolere.lms.application.rest.exceptions.RestBusException;
import com.scolere.lms.application.rest.vo.response.TeacherResponse;


public interface TeacherBusIface {
	
	TeacherResponse updateCourseModuleStatus(int moduleSessionId,int statusCode) throws RestBusException;
	TeacherResponse updateCourseResourceStatus(int resourseSessionId,int statusCode) throws RestBusException;

	
}

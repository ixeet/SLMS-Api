package com.scolere.lms.service.impl;

import com.scolere.lms.domain.exception.LmsServiceException;
import com.scolere.lms.persistance.dao.iface.TeacherDao;
import com.scolere.lms.persistance.factory.LmsDaoFactory;
import com.scolere.lms.service.iface.TeacherServiceIface;


public class TeacherServiceImpl implements TeacherServiceIface {

	@Override
	public int updateCourseStatus(int courseSessionId)
			throws LmsServiceException {
        int updateCount = 0;
        
        try {
            TeacherDao dao = (TeacherDao) LmsDaoFactory.getDAO(TeacherDao.class);
            updateCount = dao.updateCourseStatus(courseSessionId);
        } catch (Exception ex) {
            System.out.println("LmsServiceException # updateCourseStatus = "+ex);
            throw new LmsServiceException(ex.getMessage());
        }
        
        return updateCount;
	}
	

	@Override
	public int updateCourseModuleStatus(int moduleSessionId,int statusCode)
			throws LmsServiceException {
        int updateCount = 0;
        
        try {
            TeacherDao dao = (TeacherDao) LmsDaoFactory.getDAO(TeacherDao.class);
            updateCount = dao.updateCourseModuleStatus(moduleSessionId,statusCode);
        } catch (Exception ex) {
            System.out.println("LmsServiceException # updateCourseModuleStatus = "+ex);
            throw new LmsServiceException(ex.getMessage());
        }
        
        return updateCount;
	}

	
	@Override
	public int updateCourseResourceStatus(int resourseSessionId,int statusCode)
			throws LmsServiceException {
        int updateCount = 0;
        
        try {
            TeacherDao dao = (TeacherDao) LmsDaoFactory.getDAO(TeacherDao.class);
            updateCount = dao.updateCourseResourceStatus(resourseSessionId,statusCode);
        } catch (Exception ex) {
            System.out.println("LmsServiceException # updateCourseResourceStatus = "+ex);
            throw new LmsServiceException(ex.getMessage());
        }
        
        return updateCount;
	}
	
	

}//End of class

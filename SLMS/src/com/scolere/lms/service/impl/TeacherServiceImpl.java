package com.scolere.lms.service.impl;

import java.util.List;


import com.scolere.lms.domain.exception.LmsServiceException;
import com.scolere.lms.persistance.dao.iface.TeacherDao;
import com.scolere.lms.persistance.factory.LmsDaoFactory;
import com.scolere.lms.service.iface.TeacherServiceIface;


public class TeacherServiceImpl implements TeacherServiceIface {

	@Override
	public int updateCourseStatus(int courseSessionId,int statusCode)
			throws LmsServiceException {
        int updateCount = 0;
        
        try {
            TeacherDao dao = (TeacherDao) LmsDaoFactory.getDAO(TeacherDao.class);
            updateCount = dao.updateCourseStatus(courseSessionId,statusCode);
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


	@Override
	public List<Integer> getCoursePercentage(String userName, int schoolId,
			int classId, int hrmId) throws LmsServiceException {
		List<Integer> list = null;
		 try {
	            TeacherDao dao = (TeacherDao) LmsDaoFactory.getDAO(TeacherDao.class);
	            list = (List<Integer>) dao.getCoursePercentage(userName, schoolId,classId, hrmId);
	        } catch (Exception ex) {
	            System.out.println("LmsServiceException # updateCourseResourceStatus = "+ex);
	            throw new LmsServiceException(ex.getMessage());
	        }
	        
	        return list;
	}


	@Override
	public List<Integer> getAssignmentPercentage(String userName, int schoolId,
			int classId, int hrmId) throws LmsServiceException {
		List<Integer> list = null;
		 try {
	            TeacherDao dao = (TeacherDao) LmsDaoFactory.getDAO(TeacherDao.class);
	            list = (List<Integer>) dao.getAssignmentPercentage(userName, schoolId,classId, hrmId);
	        } catch (Exception ex) {
	            System.out.println("LmsServiceException # updateCourseResourceStatus = "+ex);
	            throw new LmsServiceException(ex.getMessage());
	        }
	        
	        return list;
	}


	@Override
	public int updateAssignmentStatus(int schoolId, int classId, int hrmId,int courseId, int moduleId, int statusCode, String userNm,
			String dueDate)throws LmsServiceException {
        int updateCount = 0;
        
        try {
            TeacherDao dao = (TeacherDao) LmsDaoFactory.getDAO(TeacherDao.class);
            updateCount = dao.updateAssignmentStatus(schoolId, classId, hrmId, courseId,moduleId,statusCode,userNm,dueDate);
        } catch (Exception ex) {
            System.out.println("LmsServiceException # updateAssignmentStatus = "+ex);
            throw new LmsServiceException(ex.getMessage());
        }
        
        return updateCount;
	}
	
	

}//End of class

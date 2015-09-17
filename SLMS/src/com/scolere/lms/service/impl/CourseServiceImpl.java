/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scolere.lms.service.impl;

import java.util.List;

import com.scolere.lms.domain.exception.LmsServiceException;
import com.scolere.lms.domain.vo.TeacherCourseSessionDtlsVO;
import com.scolere.lms.domain.vo.TeacherCourseSessionVO;
import com.scolere.lms.domain.vo.TeacherCourseVO;
import com.scolere.lms.domain.vo.cross.AssignmentVO;
import com.scolere.lms.domain.vo.cross.CommentVO;
import com.scolere.lms.domain.vo.cross.CourseVO;
import com.scolere.lms.domain.vo.cross.ResourseVO;
import com.scolere.lms.persistance.dao.iface.ResourceTypeMstrDao;
import com.scolere.lms.persistance.dao.iface.TeacherCourseDao;
import com.scolere.lms.persistance.dao.iface.TeacherCourseSessionDao;
import com.scolere.lms.persistance.dao.iface.TeacherCourseSessionDtlsDao;
import com.scolere.lms.persistance.factory.LmsDaoFactory;
import com.scolere.lms.service.iface.CourseServiceIface;

/**
 *
 * @author dell
 */
public class CourseServiceImpl implements CourseServiceIface{


    @Override
    public boolean saveResourceComment(String commentBy, int resourceId, String commentTxt) throws LmsServiceException {
      boolean status = false;
        try {
            TeacherCourseSessionDao dao = (TeacherCourseSessionDao) LmsDaoFactory.getDAO(TeacherCourseSessionDao.class);
            status = dao.saveResourceComment(commentBy, resourceId, commentTxt);
        } catch (Exception ex) {
            System.out.println("LmsServiceException # saveResourceComment = "+ex);
            throw new LmsServiceException(ex.getMessage());
        }
        
        return status;
    }
    
    
    @Override
    public boolean saveCommentComment(String commentBy, int commentId, String commentTxt) throws LmsServiceException {
       boolean status = false;
        try {
            TeacherCourseSessionDao dao = (TeacherCourseSessionDao) LmsDaoFactory.getDAO(TeacherCourseSessionDao.class);
            status = dao.saveCommentComment(commentBy, commentId, commentTxt);
        } catch (Exception ex) {
            System.out.println("LmsServiceException # saveCommentComment = "+ex);
            throw new LmsServiceException(ex.getMessage());
        }
        
        return status;
    }
    
    
    @Override
    public boolean saveCommentLike(String commentBy, int commentId) throws LmsServiceException {
       boolean status = false;
        try {
            TeacherCourseSessionDao dao = (TeacherCourseSessionDao) LmsDaoFactory.getDAO(TeacherCourseSessionDao.class);
            status = dao.saveCommentLike(commentBy, commentId);
        } catch (Exception ex) {
            System.out.println("LmsServiceException # saveCommentLike = "+ex);
            throw new LmsServiceException(ex.getMessage());
        }
        
        return status;
    }
    

    @Override
    public boolean saveResourceLike(String commentBy, int resourceId) throws LmsServiceException {
       boolean status = false;
        try {
            TeacherCourseSessionDao dao = (TeacherCourseSessionDao) LmsDaoFactory.getDAO(TeacherCourseSessionDao.class);
            status = dao.saveResourceLike(commentBy, resourceId);
        } catch (Exception ex) {
            System.out.println("LmsServiceException # saveResourceLike = "+ex);
            throw new LmsServiceException(ex.getMessage());
        }
        
        return status;
    }

        
    @Override
    public List<ResourseVO> getStudentResources(int moduleId) throws LmsServiceException {
        List<ResourseVO> list =null;
        try {
            TeacherCourseSessionDao dao = (TeacherCourseSessionDao) LmsDaoFactory.getDAO(TeacherCourseSessionDao.class);
            list = dao.getStudentResources(moduleId);
        } catch (Exception ex) {
            System.out.println("LmsServiceException # getStudentResources = "+ex);
            throw new LmsServiceException(ex.getMessage());
        }
        
        return list;
    }
    
    @Override
    public List<ResourseVO> getStudentResources(int courseId, int moduleId) throws LmsServiceException {
        List<ResourseVO> list =null;
        try {
            TeacherCourseSessionDao dao = (TeacherCourseSessionDao) LmsDaoFactory.getDAO(TeacherCourseSessionDao.class);
            list = dao.getStudentResources(courseId, moduleId);
        } catch (Exception ex) {
            System.out.println("LmsServiceException # getStudentResources = "+ex);
            throw new LmsServiceException(ex.getMessage());
        }
        
        return list;
    }    
    
    
    @Override
    public List<ResourseVO> getStudentResources(int userId, int courseId, int moduleId, String searchText) throws LmsServiceException {
        List<ResourseVO> list =null;
        try {
            TeacherCourseSessionDao dao = (TeacherCourseSessionDao) LmsDaoFactory.getDAO(TeacherCourseSessionDao.class);
            list = dao.getStudentResources(userId, courseId, moduleId, searchText);
        } catch (Exception ex) {
            System.out.println("LmsServiceException # getStudentResources = "+ex);
            throw new LmsServiceException(ex.getMessage());
        }
        
        return list;
    }

    @Override
    public List<ResourseVO> getStudentResourcesWeb(int userId, String courseId, String moduleId, String searchText) throws LmsServiceException {
        List<ResourseVO> list =null;
        try {
            TeacherCourseSessionDao dao = (TeacherCourseSessionDao) LmsDaoFactory.getDAO(TeacherCourseSessionDao.class);
            list = dao.getStudentResourcesWeb(userId, courseId, moduleId, searchText);
        } catch (Exception ex) {
            System.out.println("LmsServiceException # getStudentResources = "+ex);
            throw new LmsServiceException(ex.getMessage());
        }
        
        return list;
    }
    
    @Override
    public List<ResourseVO> getTeacherModuleResources(int moduleSessionId) throws LmsServiceException {
        List<ResourseVO> list =null;
        try {
            TeacherCourseSessionDao dao = (TeacherCourseSessionDao) LmsDaoFactory.getDAO(TeacherCourseSessionDao.class);
            list = dao.getTeacherModuleResources(moduleSessionId);
        } catch (Exception ex) {
            System.out.println("LmsServiceException # getTeacherModuleResources = "+ex);
            throw new LmsServiceException(ex.getMessage());
        }
        
        return list;
    }
    
    @Override
    public List<CommentVO> getResourceChildComments(int userId,int commentId) throws LmsServiceException {
        
        List<CommentVO> list =null;
        try {
            TeacherCourseSessionDao dao = (TeacherCourseSessionDao) LmsDaoFactory.getDAO(TeacherCourseSessionDao.class);
            list = dao.getResourceChildCommentsList(userId,commentId);
        } catch (Exception ex) {
            System.out.println("LmsServiceException # getResourceComments = "+ex);
            throw new LmsServiceException(ex.getMessage());
        }
        
        return list;
    }
    
    @Override
    public List<CommentVO> getResourceChildComments(int commentId) throws LmsServiceException {
        
        List<CommentVO> list =null;
        try {
            TeacherCourseSessionDao dao = (TeacherCourseSessionDao) LmsDaoFactory.getDAO(TeacherCourseSessionDao.class);
            list = dao.getResourceChildCommentsList(commentId);
        } catch (Exception ex) {
            System.out.println("LmsServiceException # getResourceComments = "+ex);
            throw new LmsServiceException(ex.getMessage());
        }
        
        return list;
    }

    @Override
    public List<CommentVO> getResourceComments(int userId,int resourceId) throws LmsServiceException {
        
        List<CommentVO> list =null;
        try {
            TeacherCourseSessionDao dao = (TeacherCourseSessionDao) LmsDaoFactory.getDAO(TeacherCourseSessionDao.class);
            list = dao.getResourceComments(userId,resourceId);
        } catch (Exception ex) {
            System.out.println("LmsServiceException # getResourceComments = "+ex);
            throw new LmsServiceException(ex.getMessage());
        }
        
        return list;
    }
    
    @Override
    public List<CommentVO> getResourceComments(int resourceId) throws LmsServiceException {
        
        List<CommentVO> list =null;
        try {
            TeacherCourseSessionDao dao = (TeacherCourseSessionDao) LmsDaoFactory.getDAO(TeacherCourseSessionDao.class);
            list = dao.getResourceComments(resourceId);
        } catch (Exception ex) {
            System.out.println("LmsServiceException # getResourceComments = "+ex);
            throw new LmsServiceException(ex.getMessage());
        }
        
        return list;
    }

    
    @Override
    public List<ResourseVO> getRelatedResources(int resourceId) throws LmsServiceException {
        
        List<ResourseVO> list =null;
        try {
            TeacherCourseSessionDao dao = (TeacherCourseSessionDao) LmsDaoFactory.getDAO(TeacherCourseSessionDao.class);
            list = dao.getRelatedResources(resourceId);
        } catch (Exception ex) {
            System.out.println("LmsServiceException # getRelatedResources = "+ex);
            throw new LmsServiceException(ex.getMessage());
        }
        
        return list;
    }

    @Override
    public List<AssignmentVO> getStudentAssignments(int courseId,int moduleId,int userId) throws LmsServiceException {
        
        List<AssignmentVO> list =null;
        try {
            TeacherCourseSessionDao dao = (TeacherCourseSessionDao) LmsDaoFactory.getDAO(TeacherCourseSessionDao.class);
            list = dao.getStudentAssignments(courseId, moduleId, userId);
        } catch (Exception ex) {
            System.out.println("LmsServiceException # getStudentAssignments = "+ex);
            throw new LmsServiceException(ex.getMessage());
        }
        
        return list;
    }

    @Override
    public List<AssignmentVO> getStudentAssignmentsByModuleId(int moduleId) throws LmsServiceException {
        
        List<AssignmentVO> list =null;
        try {
            TeacherCourseSessionDao dao = (TeacherCourseSessionDao) LmsDaoFactory.getDAO(TeacherCourseSessionDao.class);
            list = dao.getStudentAssignmentsByModuleId(moduleId);
        } catch (Exception ex) {
            System.out.println("LmsServiceException # getStudentAssignmentsByModuleId = "+ex);
            throw new LmsServiceException(ex.getMessage());
        }
        
        return list;
    }
    

    @Override
    public List<AssignmentVO> getStudentAssignmentsByModuleId(String userId,int moduleId) throws LmsServiceException {
        
        List<AssignmentVO> list =null;
        try {
            TeacherCourseSessionDao dao = (TeacherCourseSessionDao) LmsDaoFactory.getDAO(TeacherCourseSessionDao.class);
            list = dao.getStudentAssignmentsByModuleId(userId,moduleId);
        } catch (Exception ex) {
            System.out.println("LmsServiceException # getStudentAssignmentsByModuleId = "+ex);
            throw new LmsServiceException(ex.getMessage());
        }
        
        return list;
    }  
    
    @Override
    public List<AssignmentVO> getStudentAssignments(int userId) throws LmsServiceException {
        
        List<AssignmentVO> list =null;
        try {
            TeacherCourseSessionDao dao = (TeacherCourseSessionDao) LmsDaoFactory.getDAO(TeacherCourseSessionDao.class);
            list = dao.getStudentAssignments(userId);
        } catch (Exception ex) {
            System.out.println("LmsServiceException # getStudentAssignments = "+ex);
            throw new LmsServiceException(ex.getMessage());
        }
        
        return list;
    }
    
    
    @Override
    public List<AssignmentVO> getStudentAssignments(int userId,String searchText) throws LmsServiceException {
        
        List<AssignmentVO> list =null;
        try {
            TeacherCourseSessionDao dao = (TeacherCourseSessionDao) LmsDaoFactory.getDAO(TeacherCourseSessionDao.class);
            list = dao.getStudentAssignments(userId,searchText);
        } catch (Exception ex) {
            System.out.println("LmsServiceException # getStudentAssignments(?,?) = "+ex);
            throw new LmsServiceException(ex.getMessage());
        }
        
        return list;
    }
    

    @Override
    public List<ResourseVO> getAssignmentsResources(int assignmentDtlId) throws LmsServiceException {
        
        List<ResourseVO> list =null;
        try {
            TeacherCourseSessionDao dao = (TeacherCourseSessionDao) LmsDaoFactory.getDAO(TeacherCourseSessionDao.class);
            list = dao.getAssignmentsResources(assignmentDtlId);
        } catch (Exception ex) {
            System.out.println("LmsServiceException # getAssignmentsResources(?) = "+ex);
            throw new LmsServiceException(ex.getMessage());
        }
        
        return list;
    }
    
    
    @Override
    public List<ResourseVO> getAssignmentsResources(int userId, int assignmentDtlId) throws LmsServiceException {
        
        List<ResourseVO> list =null;
        try {
            TeacherCourseSessionDao dao = (TeacherCourseSessionDao) LmsDaoFactory.getDAO(TeacherCourseSessionDao.class);
            list = dao.getAssignmentsResources(userId, assignmentDtlId);
        } catch (Exception ex) {
            System.out.println("LmsServiceException # getAssignmentsResources = "+ex);
            throw new LmsServiceException(ex.getMessage());
        }
        
        return list;
    }
    
    
    @Override
    public List<CourseVO> getStudentCourses(String userId, String searchText) throws LmsServiceException {
        
        List<CourseVO> list =null;
        try {
            TeacherCourseSessionDao dao = (TeacherCourseSessionDao) LmsDaoFactory.getDAO(TeacherCourseSessionDao.class);
            list = dao.getStudentCourses(userId, searchText);
        } catch (Exception ex) {
            System.out.println("LmsServiceException # getStudentCourses = "+ex);
            throw new LmsServiceException(ex.getMessage());
        }
        
    return list;

    }
    
    
    @Override
    public CourseVO getStudentCourseDetail(int courseId) throws LmsServiceException {
        CourseVO vo =null;

        try {
            TeacherCourseSessionDao dao = (TeacherCourseSessionDao) LmsDaoFactory.getDAO(TeacherCourseSessionDao.class);
            vo = dao.getStudentCourseDetail(courseId);
        } catch (Exception ex) {
            System.out.println("LmsServiceException # getStudentCourseDetail = "+ex);
            throw new LmsServiceException(ex.getMessage());
        }        
        
        return vo;
    }
    
    
    @Override
    public List<CourseVO> getStudentCourses(int userId, String searchText) throws LmsServiceException {
        
        List<CourseVO> list =null;
        try {
            TeacherCourseSessionDao dao = (TeacherCourseSessionDao) LmsDaoFactory.getDAO(TeacherCourseSessionDao.class);
            list = dao.getStudentCourses(userId, searchText);
        } catch (Exception ex) {
            System.out.println("LmsServiceException # getStudentCourses = "+ex);
            throw new LmsServiceException(ex.getMessage());
        }
        
    return list;

    }    

    @Override
    public List<CourseVO> getTeacherCourses(int userId,int schoolId,int classId,int hrmId,int courseId,int moduleId) throws LmsServiceException {
        
        List<CourseVO> list =null;
        try {
            TeacherCourseSessionDao dao = (TeacherCourseSessionDao) LmsDaoFactory.getDAO(TeacherCourseSessionDao.class);
            list = dao.getTeacherCourses(userId, schoolId, classId, hrmId, courseId,moduleId);
        } catch (Exception ex) {
            System.out.println("LmsServiceException # getTeacherCourses = "+ex);
            throw new LmsServiceException(ex.getMessage());
        }
        
    return list;

    }      
    
    @Override
    public List<CourseVO> getTeacherCourses(int userId,int schoolId,int classId,int hrmId,int courseId) throws LmsServiceException {
        
        List<CourseVO> list =null;
        try {
            TeacherCourseSessionDao dao = (TeacherCourseSessionDao) LmsDaoFactory.getDAO(TeacherCourseSessionDao.class);
            list = dao.getTeacherCourses(userId, schoolId, classId, hrmId, courseId);
        } catch (Exception ex) {
            System.out.println("LmsServiceException # getTeacherCourses = "+ex);
            throw new LmsServiceException(ex.getMessage());
        }
        
    return list;

    }     

    @Override
    public CourseVO getStudentModuleDetail(int moduleId) throws LmsServiceException {
        CourseVO vo = null;
        
        try {
            TeacherCourseSessionDao dao = (TeacherCourseSessionDao) LmsDaoFactory.getDAO(TeacherCourseSessionDao.class);
            vo = dao.getStudentModuleDetail(moduleId);
        } catch (Exception ex) {
            System.out.println("LmsServiceException # getStudentModuleDetail = "+ex);
            throw new LmsServiceException(ex.getMessage());
        }        
        
        return vo;
    }    
    
    @Override
    public List<CourseVO> getStudentCoursesModules(int courseSessionId) throws LmsServiceException {
        List<CourseVO> list =null;
        try {
            TeacherCourseSessionDao dao = (TeacherCourseSessionDao) LmsDaoFactory.getDAO(TeacherCourseSessionDao.class);
            list = dao.getStudentCoursesModules(courseSessionId);
        } catch (Exception ex) {
            System.out.println("LmsServiceException # getStudentCoursesModules = "+ex);
            throw new LmsServiceException(ex.getMessage());
        }
        
    return list;

    }       
    
    @Override
    public List<CourseVO> getTeacherCoursesModules(int courseSessionId) throws LmsServiceException {
        List<CourseVO> list =null;
        try {
            TeacherCourseSessionDao dao = (TeacherCourseSessionDao) LmsDaoFactory.getDAO(TeacherCourseSessionDao.class);
            list = dao.getTeacherCoursesModules(courseSessionId);
        } catch (Exception ex) {
            System.out.println("LmsServiceException # getTeacherCoursesModules = "+ex);
            throw new LmsServiceException(ex.getMessage());
        }
        
    return list;

    }        
    
    @Override
    public List<CourseVO> getTeacherCoursesModules(int courseSessionId,int moduleId) throws LmsServiceException {
        List<CourseVO> list =null;
        try {
            TeacherCourseSessionDao dao = (TeacherCourseSessionDao) LmsDaoFactory.getDAO(TeacherCourseSessionDao.class);
            list = dao.getTeacherCoursesModules(courseSessionId,moduleId);
        } catch (Exception ex) {
            System.out.println("LmsServiceException # getTeacherCoursesModules(?,?) = "+ex);
            throw new LmsServiceException(ex.getMessage());
        }
        
    return list;

    }            
    
    @Override
    public boolean updateTeacherCourseSession(TeacherCourseSessionVO vo) throws LmsServiceException {
        boolean status = false;
        
        try {
            TeacherCourseSessionDao dao = (TeacherCourseSessionDao) LmsDaoFactory.getDAO(TeacherCourseSessionDao.class);
            status = dao.updateTeacherCourseSession(vo);
        } catch (Exception ex) {
            System.out.println("LmsServiceException # updateTeacherCourseSession = "+ex);
            throw new LmsServiceException(ex.getMessage());
        }
        
        return status;
    }

    
    @Override
    public void saveTeacherCourseSession(TeacherCourseSessionVO vo) throws LmsServiceException {
        try {
            TeacherCourseSessionDao dao = (TeacherCourseSessionDao) LmsDaoFactory.getDAO(TeacherCourseSessionDao.class);
            dao.saveTeacherCourseSession(vo);
        } catch (Exception ex) {
            System.out.println("LmsServiceException # saveTeacherCourseSession = "+ex);
            throw new LmsServiceException(ex.getMessage());
        }
    }

    @Override
    public boolean deleteTeacherCourseSession(TeacherCourseSessionVO vo) throws LmsServiceException {
        boolean status = false;
        
        try {
            TeacherCourseSessionDao dao = (TeacherCourseSessionDao) LmsDaoFactory.getDAO(TeacherCourseSessionDao.class);
            status = dao.deleteTeacherCourseSession(vo);
        } catch (Exception ex) {
            System.out.println("LmsServiceException # deleteTeacherCourseSession = "+ex);
            throw new LmsServiceException(ex.getMessage());
        }
        
        return status;
    }

    @Override
    public TeacherCourseSessionVO getTeacherCourseSession(int id) throws LmsServiceException {

        TeacherCourseSessionVO vo =null;
        try {
            TeacherCourseSessionDao dao = (TeacherCourseSessionDao) LmsDaoFactory.getDAO(TeacherCourseSessionDao.class);
            vo = dao.getTeacherCourseSession(id);
        } catch (Exception ex) {
            System.out.println("LmsServiceException # deleteTeacherCourseSession = "+ex);
            throw new LmsServiceException(ex.getMessage());
        }
        
    return vo;
    }

    @Override
    public List<TeacherCourseSessionVO> getTeacherCourseSessionList() throws LmsServiceException {
        List<TeacherCourseSessionVO> list =null;
        try {
            TeacherCourseSessionDao dao = (TeacherCourseSessionDao) LmsDaoFactory.getDAO(TeacherCourseSessionDao.class);
            list = dao.getTeacherCourseSessionList();
        } catch (Exception ex) {
            System.out.println("LmsServiceException # getTeacherCourseSessionList = "+ex);
            throw new LmsServiceException(ex.getMessage());
        }
        
    return list;
    }

    @Override
    public boolean updateTeacherCourseSessionDtls(TeacherCourseSessionDtlsVO vo) throws LmsServiceException {
        boolean status = false;
        
        try {
            TeacherCourseSessionDtlsDao dao = (TeacherCourseSessionDtlsDao) LmsDaoFactory.getDAO(TeacherCourseSessionDtlsDao.class);
            status = dao.updateTeacherCourseSessionDtls(vo);
        } catch (Exception ex) {
            System.out.println("LmsServiceException # updateTeacherCourseSessionDtls = "+ex);
            throw new LmsServiceException(ex.getMessage());
        }
        
        return status;
    }
    
    
    @Override
    public void saveTeacherCourseSessionDtls(TeacherCourseSessionDtlsVO vo) throws LmsServiceException {
        
        try {
            TeacherCourseSessionDtlsDao dao = (TeacherCourseSessionDtlsDao) LmsDaoFactory.getDAO(TeacherCourseSessionDtlsDao.class);
            dao.saveTeacherCourseSessionDtls(vo);
        } catch (Exception ex) {
            System.out.println("LmsServiceException # saveTeacherCourseSessionDtls = "+ex);
            throw new LmsServiceException(ex.getMessage());
        }
        
    }

    @Override
    public boolean deleteTeacherCourseSessionDtls(TeacherCourseSessionDtlsVO vo) throws LmsServiceException {
        boolean status = false;
        
        try {
            TeacherCourseSessionDtlsDao dao = (TeacherCourseSessionDtlsDao) LmsDaoFactory.getDAO(TeacherCourseSessionDtlsDao.class);
            status = dao.deleteTeacherCourseSessionDtls(vo);
        } catch (Exception ex) {
            System.out.println("LmsServiceException # deleteTeacherCourseSessionDtls = "+ex);
            throw new LmsServiceException(ex.getMessage());
        }
        
        return status;
    }

    
    @Override
    public TeacherCourseSessionDtlsVO getTeacherCourseSessionDtls(int id) throws LmsServiceException {

        TeacherCourseSessionDtlsVO vo = null;
        
        try {
            TeacherCourseSessionDtlsDao dao = (TeacherCourseSessionDtlsDao) LmsDaoFactory.getDAO(TeacherCourseSessionDtlsDao.class);
            vo = dao.getTeacherCourseSessionDtls(id);
        } catch (Exception ex) {
            System.out.println("LmsServiceException # getTeacherCourseSessionDtls = "+ex);
            throw new LmsServiceException(ex.getMessage());
        }
        
        return vo;        
    
    }

    @Override
    public List<TeacherCourseSessionDtlsVO> getTeacherCourseSessionDtlsList() throws LmsServiceException {

        List<TeacherCourseSessionDtlsVO> list = null;
        
        try {
            TeacherCourseSessionDtlsDao dao = (TeacherCourseSessionDtlsDao) LmsDaoFactory.getDAO(TeacherCourseSessionDtlsDao.class);
            list = dao.getTeacherCourseSessionDtlsList();
        } catch (Exception ex) {
            System.out.println("LmsServiceException # getTeacherCourseSessionDtlsList = "+ex);
            throw new LmsServiceException(ex.getMessage());
        }
        
        return list;        
    
    }

    @Override
    public boolean updateTeacherCourseVO(TeacherCourseVO vo) throws LmsServiceException {
        boolean status = false;
        
        try {
            TeacherCourseDao dao = (TeacherCourseDao) LmsDaoFactory.getDAO(TeacherCourseDao.class);
            status = dao.updateTeacherCourseVO(vo);
        } catch (Exception ex) {
            System.out.println("LmsServiceException # updateTeacherCourseVO = "+ex);
            throw new LmsServiceException(ex.getMessage());
        }
        
        return status;
    }

    @Override
    public void saveTeacherCourseVO(TeacherCourseVO vo) throws LmsServiceException {

        try {
            TeacherCourseDao dao = (TeacherCourseDao) LmsDaoFactory.getDAO(TeacherCourseDao.class);
            dao.saveTeacherCourseVO(vo);
        } catch (Exception ex) {
            System.out.println("LmsServiceException # saveTeacherCourseVO = "+ex);
            throw new LmsServiceException(ex.getMessage());
        }        
    }

    @Override
    public boolean deleteTeacherCourseVO(TeacherCourseVO vo) throws LmsServiceException {
        boolean status = false;
        
        try {
            TeacherCourseDao dao = (TeacherCourseDao) LmsDaoFactory.getDAO(TeacherCourseDao.class);
            status = dao.deleteTeacherCourseVO(vo);
        } catch (Exception ex) {
            System.out.println("LmsServiceException # deleteTeacherCourseVO = "+ex);
            throw new LmsServiceException(ex.getMessage());
        }
        
        return status;
    }

    @Override
    public TeacherCourseVO getTeacherCourse(int id) throws LmsServiceException {
        
        TeacherCourseVO vo =null;
        try {
            TeacherCourseDao dao = (TeacherCourseDao) LmsDaoFactory.getDAO(TeacherCourseDao.class);
            vo = dao.getTeacherCourse(id);
        } catch (Exception ex) {
            System.out.println("LmsServiceException # deleteTeacherCourseVO = "+ex);
            throw new LmsServiceException(ex.getMessage());
        }
        
        return vo;
    }

    @Override
    public List<TeacherCourseVO> getTeacherCourseList() throws LmsServiceException {
        List<TeacherCourseVO> list = null;
        
        try {
            TeacherCourseDao dao = (TeacherCourseDao) LmsDaoFactory.getDAO(TeacherCourseDao.class);
            list = dao.getTeacherCourseList();
        } catch (Exception ex) {
            System.out.println("LmsServiceException # getTeacherCourseList = "+ex);
            throw new LmsServiceException(ex.getMessage());
        }
        
        return list;        
    
    }

    @Override
    public int uploadAssignment(int assignmentId,String resourceName,String resourceAuthor, String resourceDesc,String userName, String descTxt, String url, String thumbUrl, String authorImgUrl)
			throws LmsServiceException {
		
    		int status = 0;
        try {
        	 ResourceTypeMstrDao  dao = (ResourceTypeMstrDao) LmsDaoFactory.getDAO(ResourceTypeMstrDao.class);
             status = dao.uploadAssignment(assignmentId, resourceName, resourceAuthor, resourceDesc, userName, descTxt, url, thumbUrl, authorImgUrl);
        } catch (Exception ex) {
            System.out.println("Exception #  "+ex.getMessage());
            throw new LmsServiceException("Exception # "+ex.getMessage());
        }
        
       return status; 
	}


    
}//End of class

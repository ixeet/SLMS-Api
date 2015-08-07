/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scolere.lms.service.iface;


import com.scolere.lms.domain.exception.LmsServiceException;
import com.scolere.lms.domain.vo.TeacherCourseSessionDtlsVO;
import com.scolere.lms.domain.vo.TeacherCourseSessionVO;
import com.scolere.lms.domain.vo.TeacherCourseVO;
import com.scolere.lms.domain.vo.cross.AssignmentVO;
import com.scolere.lms.domain.vo.cross.CommentVO;
import com.scolere.lms.domain.vo.cross.CourseVO;
import com.scolere.lms.domain.vo.cross.ResourseVO;
import java.util.List;

/**
 *
 * @author dell
 */
public interface CourseServiceIface {

    // Comment & Likes
    boolean saveResourceComment(String commentBy,int resourceId,String commentTxt) throws LmsServiceException;
    boolean saveCommentComment(String commentBy,int commentId,String commentTxt) throws LmsServiceException;
    boolean saveCommentLike(String commentBy,int commentId) throws LmsServiceException;
    boolean saveResourceLike(String commentBy,int resourceId) throws LmsServiceException;
    
    // TeacherCourseSession
    List<CourseVO> getStudentCourses(int userId,String searchText) throws LmsServiceException;
    List<CourseVO> getStudentCourses(String userNm,String searchText) throws LmsServiceException;
    List<CourseVO> getStudentCoursesModules(int courseSessionId) throws LmsServiceException;
    //Get module details service -Resources & Comments
    List<ResourseVO> getStudentResources(int userId,int courseId,int moduleId,String searchText) throws LmsServiceException;
    List<ResourseVO> getStudentResourcesWeb(int userId,String courseId,String moduleId,String searchText) throws LmsServiceException;
    List<CommentVO> getResourceComments(int resourceId) throws LmsServiceException;
    List<ResourseVO> getRelatedResources(int resourceId) throws LmsServiceException;
    //Get assignments service -Resources & Comments
    List<AssignmentVO> getStudentAssignments(int courseId,int moduleId,int userId) throws LmsServiceException;
    List<AssignmentVO> getStudentAssignments(int userId) throws LmsServiceException;
    List<ResourseVO> getAssignmentsResources(int userId,int assignmentDtlId) throws LmsServiceException;
    
    boolean updateTeacherCourseSession(TeacherCourseSessionVO vo) throws LmsServiceException;

    void saveTeacherCourseSession(TeacherCourseSessionVO vo) throws LmsServiceException;

    boolean deleteTeacherCourseSession(TeacherCourseSessionVO vo) throws LmsServiceException;

    TeacherCourseSessionVO getTeacherCourseSession(int id) throws LmsServiceException;

    List<TeacherCourseSessionVO> getTeacherCourseSessionList() throws LmsServiceException;

    
    //TeacherCourseSessionDtls
    boolean updateTeacherCourseSessionDtls(TeacherCourseSessionDtlsVO vo) throws LmsServiceException;

    void saveTeacherCourseSessionDtls(TeacherCourseSessionDtlsVO vo) throws LmsServiceException;

    boolean deleteTeacherCourseSessionDtls(TeacherCourseSessionDtlsVO vo) throws LmsServiceException;

    TeacherCourseSessionDtlsVO getTeacherCourseSessionDtls(int id) throws LmsServiceException;

    List<TeacherCourseSessionDtlsVO> getTeacherCourseSessionDtlsList() throws LmsServiceException;

    
    //TeacherCourse
    boolean updateTeacherCourseVO(TeacherCourseVO vo) throws LmsServiceException;

    void saveTeacherCourseVO(TeacherCourseVO vo) throws LmsServiceException;

    boolean deleteTeacherCourseVO(TeacherCourseVO vo) throws LmsServiceException;

    TeacherCourseVO getTeacherCourse(int id) throws LmsServiceException;

    List<TeacherCourseVO> getTeacherCourseList() throws LmsServiceException;

    
    
    
    
}//End of class

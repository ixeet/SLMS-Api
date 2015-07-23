package com.scolere.lms.persistance.dao.iface;

import java.util.List;

import com.scolere.lms.domain.exception.LmsDaoException;
import com.scolere.lms.domain.vo.TeacherCourseSessionVO;

public interface TeacherCourseSessionDao
{
    	boolean updateTeacherCourseSession(TeacherCourseSessionVO  vo)throws LmsDaoException;
    /**
     * This method is used for save user login
     * @param vo 
     */

    void saveTeacherCourseSession(TeacherCourseSessionVO  vo)throws LmsDaoException;
    /**
     * This method  used for delete
     * @param vo
     * @return true/false
     */

    boolean deleteTeacherCourseSession(TeacherCourseSessionVO  vo)throws LmsDaoException;
    
    /**
     * This method used for get user login id.
     * @param id
     * @return teacherDtls
     */

    TeacherCourseSessionVO  getTeacherCourseSession(int id)throws LmsDaoException;

    /**
     * 
     * @return
     */
    List<TeacherCourseSessionVO > getTeacherCourseSessionList()throws LmsDaoException;

    
}

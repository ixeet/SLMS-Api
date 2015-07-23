package com.scolere.lms.persistance.dao.iface;

import java.util.List;

import com.scolere.lms.domain.exception.LmsDaoException;
import com.scolere.lms.domain.vo.TeacherCourseSessionDtlsVO;

public interface TeacherCourseSessionDtlsDao {
	
	boolean updateTeacherCourseSessionDtls(TeacherCourseSessionDtlsVO  vo)throws LmsDaoException;
    /**
     * This method is used for 
     * @param vo 
     */

    void saveTeacherCourseSessionDtls(TeacherCourseSessionDtlsVO  vo)throws LmsDaoException;
    /**
     * This method  used for delete
     * @param vo
     * @return true/false
     */

    boolean deleteTeacherCourseSessionDtls(TeacherCourseSessionDtlsVO  vo)throws LmsDaoException;
    
    /**
     * This method used for get .
     * @param id
     * @return teacherDtls
     */

    TeacherCourseSessionDtlsVO  getTeacherCourseSessionDtls(int id)throws LmsDaoException;

    /**
     * 
     * @return
     */
    List<TeacherCourseSessionDtlsVO > getTeacherCourseSessionDtlsList()throws LmsDaoException;

}

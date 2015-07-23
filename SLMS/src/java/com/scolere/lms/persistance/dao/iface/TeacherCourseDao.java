package com.scolere.lms.persistance.dao.iface;

import java.util.List;

import com.scolere.lms.domain.exception.LmsDaoException;
import com.scolere.lms.domain.vo.TeacherCourseVO;

public interface TeacherCourseDao
{
    	
	boolean updateTeacherCourseVO(TeacherCourseVO  vo)throws LmsDaoException;
    /**
     * This method is used for save user login
     * @param vo 
     */

    void saveTeacherCourseVO(TeacherCourseVO  vo)throws LmsDaoException;
    /**
     * This method  used for delete
     * @param vo
     * @return true/false
     */

    boolean deleteTeacherCourseVO(TeacherCourseVO  vo)throws LmsDaoException;
    
    /**
     * This method used for get user login id.
     * @param id
     * @return teacherDtls
     */

    TeacherCourseVO  getTeacherCourse(int id)throws LmsDaoException;

    /**
     * 
     * @return
     */
    List<TeacherCourseVO > getTeacherCourseList()throws LmsDaoException;
}

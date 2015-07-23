/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scolere.lms.persistance.dao.iface;

import com.scolere.lms.domain.exception.LmsDaoException;
import com.scolere.lms.domain.vo.UserCourseMapVo;
import java.util.List;

/**
 *
 * @author admin
 */
public interface UserCourseMapDao {
    boolean updateUserCourseMapDetail( UserCourseMapVo  vo) throws LmsDaoException;
    /**
     * This method is used for save  user course map
     * @param vo 
     */

    void saveUserCourseMapDetail( UserCourseMapVo  vo) throws LmsDaoException;
    /**
     * This method  used for delete user course map
     * @param vo
     * @return true/false
     */

    boolean deleteUserCourseMapDetail(UserCourseMapVo  vo) throws LmsDaoException;
    /**
     * This method used for get user user course.
     * @param id
     * @return UserCourseMapVo
     */

     UserCourseMapVo  getUserCourseMapDetail(int id) throws LmsDaoException;
    /**
     * This is used for list.
     * @return 
     */

    List< UserCourseMapVo > getUserCourseMapVoList() throws LmsDaoException;  
}

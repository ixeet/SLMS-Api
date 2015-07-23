/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scolere.lms.persistance.dao.iface;

import com.scolere.lms.domain.exception.LmsDaoException;
import com.scolere.lms.domain.vo.TeacherDetailVo;
import java.util.List;

/**
 *
 * @author admin
 */
public interface TeacherDetailDao {

    /**
     * This method is used for update
     *
     * @param vo
     * @return true/false
     */
    boolean updateTeacherDetail(TeacherDetailVo vo) throws LmsDaoException;

    /**
     * This method is used for save user login
     *
     * @param vo
     */
    void saveTeacherDetail(TeacherDetailVo vo) throws LmsDaoException;

    /**
     * This method used for delete
     *
     * @param vo
     * @return true/false
     */
    boolean deleteTeacherDetail(TeacherDetailVo vo) throws LmsDaoException;

    /**
     * This method used for get user login id.
     *
     * @param id
     * @return teacherDtls
     */
    TeacherDetailVo getTeacherDetail(int id) throws LmsDaoException;

    List<TeacherDetailVo> getTeacherDetailVoList() throws LmsDaoException;
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scolere.lms.persistance.dao.iface;

import com.scolere.lms.domain.exception.LmsDaoException;
import com.scolere.lms.domain.vo.StudentDetailVo;
import java.util.List;

/**
 *
 * @author admin
 */
public interface StudentDetailDao {
    
    boolean updateProfilePhoto(String photoPath,String userNm) throws LmsDaoException;
    
    boolean updateStudentDetail(StudentDetailVo  vo) throws LmsDaoException;
    /**
     * This method is used for save  detail
     * @param vo 
     */

    boolean saveStudentDetail(StudentDetailVo  vo) throws LmsDaoException;
    /**
     * This method  used for delete detail
     * @param vo
     * @return true/false
     */

    boolean deleteStudentDetail(StudentDetailVo  vo) throws LmsDaoException;
    /**
     * This method used for get user .
     * @param id
     * @return studentDtls
     */

     StudentDetailVo  getStudentDetail(int id) throws LmsDaoException;
    /**
     * This is used for list.
     * @return 
     */

    List<StudentDetailVo> getStudentDetailVoList() throws LmsDaoException;  
}

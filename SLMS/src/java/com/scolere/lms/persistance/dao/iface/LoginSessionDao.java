/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scolere.lms.persistance.dao.iface;

import com.scolere.lms.domain.exception.LmsDaoException;
import com.scolere.lms.domain.vo.LoginSessionVo;
import java.util.List;

/**
 *
 * @author admin
 */
public interface LoginSessionDao {
    
   boolean updateLoginSession(LoginSessionVo  vo) throws LmsDaoException;
    /**
     * This method is used for save  login session
     * @param vo 
     */

    boolean saveLoginSession(LoginSessionVo  vo) throws LmsDaoException;
    /**
     * This method  used for delete login session
     * @param vo
     * @return true/false
     */

    boolean deleteLoginSession(LoginSessionVo  vo) throws LmsDaoException;
    /**
     * This method used for get user login session.
     * @param id
     * @return teacherDtls
     */

     LoginSessionVo getLoginSession(int id) throws LmsDaoException;
    /**
     * This is used for list.
     * @return 
     */

    List< LoginSessionVo> getLoginSessionList() throws LmsDaoException;
}

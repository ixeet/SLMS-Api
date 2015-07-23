/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scolere.lms.persistance.dao.iface;

import com.scolere.lms.domain.exception.LmsDaoException;
import com.scolere.lms.domain.vo.UserTypeMasterVo;
import java.util.List;

/**
 *
 * @author admin
 */
public interface UserTypeMasterDao {

    /**
     * This method is used for update
     *
     * @param vo
     * @return true/false
     */
    boolean updateUserTypeMasterDetail(UserTypeMasterVo vo) throws LmsDaoException;

    /**
     * This method is used for save
     *
     * @param vo
     */
    void saveUserTypeMasterDetail(UserTypeMasterVo vo) throws LmsDaoException;

    /**
     * This method used for delete
     *
     * @param vo
     * @return true/false
     */
    boolean deleteUserTypeMasterDetail(UserTypeMasterVo vo) throws LmsDaoException;

    /**
     * This method used for get id.
     *
     * @param id
     * @return classDtls
     */
    UserTypeMasterVo getUserTypeMasterDetail(int id) throws LmsDaoException;

    List<UserTypeMasterVo> getUserTypeMasterVoList() throws LmsDaoException;
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scolere.lms.persistance.dao.iface;

import com.scolere.lms.domain.exception.LmsDaoException;
import java.util.List;
import com.scolere.lms.domain.vo.HomeRoomMasterVo;
import com.scolere.lms.domain.vo.ModuleMasterVo;

/**
 *
 * @author admin
 */
public interface ModuleMasterDao {

    /**
     * This method is used for update
     *
     * @param vo
     * @return true/false
     */
    boolean updateModuleMasterDetail(ModuleMasterVo vo) throws LmsDaoException;

    /**
     * This method is used for save
     *
     * @param vo
     */
    void saveModuleMasterDetail(ModuleMasterVo vo) throws LmsDaoException;

    /**
     * This method used for delete
     *
     * @param vo
     * @return true/false
     */
    boolean deleteModuleMasterDetail(ModuleMasterVo vo) throws LmsDaoException;

    /**
     * This method used for get id.
     *
     * @param id
     * @return userClassmap.
     */
    ModuleMasterVo getModuleMasterDetail(int id) throws LmsDaoException;

    List<ModuleMasterVo> getModuleMasterVoList() throws LmsDaoException;
}

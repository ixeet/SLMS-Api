package com.scolere.lms.persistance.dao.iface;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.scolere.lms.domain.exception.LmsDaoException;
import com.scolere.lms.domain.vo.ModuleResourceMapVo;
import java.util.List;

/**
 *
 * @author admin
 */
public interface ModuleResourcemapDao {

    /**
     * This method is used for update
     *
     * @param vo
     * @return true/false
     */
    boolean updateModuleResourcemapDetail(ModuleResourceMapVo vo) throws LmsDaoException;

    /**
     * This method is used for save
     *
     * @param vo
     */
    void saveModuleResourceMapDetail(ModuleResourceMapVo vo) throws LmsDaoException;

    /**
     * This method used for delete
     *
     * @param vo
     * @return true/false
     */
    boolean deleteModuleResourceMapDetail(ModuleResourceMapVo vo) throws LmsDaoException;

    /**
     * This method used for get id.
     *
     * @param id
     * @return classDtls
     */
    ModuleResourceMapVo getModuleResourceMapDetail(int id) throws LmsDaoException;

    List<ModuleResourceMapVo> getModuleResourceMapList() throws LmsDaoException;
}

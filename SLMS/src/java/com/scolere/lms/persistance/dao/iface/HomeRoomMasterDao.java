/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scolere.lms.persistance.dao.iface;

import com.scolere.lms.domain.exception.LmsDaoException;
import java.util.List;
import my.java.interfac.HomeRoomMasterVo;

/**
 *
 * @author admin
 */
public interface HomeRoomMasterDao {
    /**
     * This method is used for update
     * @param vo
     * @return true/false
     */
    boolean updateHomeRoomMasterDetail(HomeRoomMasterVo  vo) throws LmsDaoException;
    /**
     * This method is used for save 
     * @param vo 
     */

    void saveHomeRoomMasterDetail(HomeRoomMasterVo   vo) throws LmsDaoException;
    /**
     * This method  used for delete
     * @param vo
     * @return true/false
     */

    boolean deleteHomeRoomMasterDetail(HomeRoomMasterVo   vo) throws LmsDaoException;
    /**
     * This method used for get  id.
     * @param id
     * @return classDtls
     */

    HomeRoomMasterVo  getHomeRoomMasterDetail(int id) throws LmsDaoException;

    List<HomeRoomMasterVo > getHomeRoomMasterVoList() throws LmsDaoException;
}

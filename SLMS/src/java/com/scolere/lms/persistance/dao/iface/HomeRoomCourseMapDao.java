/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scolere.lms.persistance.dao.iface;

import com.scolere.lms.domain.exception.LmsDaoException;
import com.scolere.lms.domain.vo.HomeRoomCourseMapVo;
import java.util.List;

/**
 *
 * @author admin
 */
public interface HomeRoomCourseMapDao {
        /**
     * This method is used for update
     * @param vo
     * @return true/false
     */
    boolean updateHomeRoomCourseMapDetail(HomeRoomCourseMapVo  vo) throws LmsDaoException;
    /**
     * This method is used for save 
     * @param vo 
     */

    void saveHomeRoomCourseMapDetail(HomeRoomCourseMapVo   vo) throws LmsDaoException;
    /**
     * This method  used for delete
     * @param vo
     * @return true/false
     */

    boolean deleteHomeRoomCourseMapDetail(HomeRoomCourseMapVo   vo) throws LmsDaoException;
    /**
     * This method used for get  id.
     * @param id
     * @return classDtls
     */

    HomeRoomCourseMapVo  getHomeRoomCourseMapDetail(int id) throws LmsDaoException;

    List<HomeRoomCourseMapVo > getHomeRoomCourseMapList() throws LmsDaoException;
}

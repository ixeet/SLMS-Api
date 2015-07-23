/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scolere.lms.persistance.dao.iface;

import com.scolere.lms.domain.exception.LmsDaoException;
import com.scolere.lms.domain.vo.ClassHomeRoommapVo;
import java.util.List;

/**
 *
 * @author admin
 */
public interface ClassHomeRoomMapDao {
      /**
     * This method is used for update
     * @param vo
     * @return true/false
     */
    boolean updateClassHomeRoommapDetail(ClassHomeRoommapVo  vo) throws LmsDaoException;
    /**
     * This method is used for save 
     * @param vo 
     */

    void saveClassHomeRoommapDetail(ClassHomeRoommapVo   vo) throws LmsDaoException;
    /**
     * This method  used for delete
     * @param vo
     * @return true/false
     */

    boolean deleteClassHomeRoommapDetail(ClassHomeRoommapVo   vo) throws LmsDaoException;
    /**
     * This method used for get  id.
     * @param id
     * @return classDtls
     */

    ClassHomeRoommapVo  getClassHomeRoommapDetail(int id) throws LmsDaoException;

    List<ClassHomeRoommapVo > getClassHomeRoommapList() throws LmsDaoException;
}

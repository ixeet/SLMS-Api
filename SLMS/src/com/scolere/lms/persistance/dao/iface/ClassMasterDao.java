/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scolere.lms.persistance.dao.iface;

import com.scolere.lms.domain.vo.ClassMasterVo;
import java.util.List;

/**
 *
 * @author admin
 */
public interface ClassMasterDao {
    
     boolean updateClassDetail(ClassMasterVo  vo);
    /**
     * This method is used for save 
     * @param vo 
     */

    void saveClassDetail(ClassMasterVo   vo);
    /**
     * This method  used for delete
     * @param vo
     * @return true/false
     */

    boolean deleteClassDetail(ClassMasterVo   vo);
    /**
     * This method used for get  id.
     * @param id
     * @return classDtls
     */

    ClassMasterVo  getClassDetail(int id);

    List<ClassMasterVo > getClassMasterVoList(); 

    List<ClassMasterVo > getClassMasterVoList(int schoolId); 
}

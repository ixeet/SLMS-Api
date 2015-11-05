/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scolere.lms.persistance.dao.iface;

import com.scolere.lms.domain.vo.SchoolMasterVo;
import java.util.List;

/**
 *
 * @author admin
 */
public interface SchoolMasterDao {

    /**
     * This method use to update school master detail.
     * @param SchoolMasterVo
     * @return true/false
     */
    boolean updateSchoolMasterDetail(SchoolMasterVo vo);

    /**
     * This method use to save school master detail.
     * @param vo 
     */
    void saveSchoolMasterDetail(SchoolMasterVo vo);
    /**
     * This method use for delete school master detail.
     * @param vo
     * @return 
     */

    boolean deleteSchoolMasterDetail(SchoolMasterVo vo);
    /**
     * This method use for get school master detail.
     * @param id
     * @return SchoolMasterVo
     */

    SchoolMasterVo getSchoolMasterDetail(int id);
    /**
     * This method use for get list of school master detail.
     * 
     * @return 
     */

    List<SchoolMasterVo> getSchoolMasterVoList();
    
    List<SchoolMasterVo> getSchoolMasterVoList(int teacherId);

	List<SchoolMasterVo> getSchoolMasterVoList(int schoolId, int teacherId);


}

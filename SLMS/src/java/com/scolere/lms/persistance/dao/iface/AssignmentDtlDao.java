package com.scolere.lms.persistance.dao.iface;

import java.util.List;

import com.scolere.lms.domain.exception.LmsDaoException;
import com.scolere.lms.domain.vo.AssignmentDtlVO;

public interface AssignmentDtlDao {
	
	boolean updateAssignmentDtl(AssignmentDtlVO  vo)throws LmsDaoException;
    /**
     * This method is used for save user login
     * @param vo 
     */

    void saveAssignmentDtl(AssignmentDtlVO  vo)throws LmsDaoException;
    /**
     * This method  used for delete
     * @param vo
     * @return true/false
     */

    boolean deleteAssignmentDtl(AssignmentDtlVO  vo)throws LmsDaoException;
    
    /**
     * This method used for get user login id.
     * @param id
     * @return teacherDtls
     */

    AssignmentDtlVO  getAssignmentDtl(int id)throws LmsDaoException;

    /**
     * 
     * @return
     */
    List<AssignmentDtlVO > getAssignmentDtlList()throws LmsDaoException;

}

package com.scolere.lms.persistance.dao.iface;

import java.util.List;

import com.scolere.lms.domain.exception.LmsDaoException;
import com.scolere.lms.domain.vo.AssignmentResourceTagVO;

public interface AssignmentResourceTagDao {
	
	boolean updateAssignmentResourceTag(AssignmentResourceTagVO  vo)throws LmsDaoException;
    /**
     * This method is used for save user login
     * @param vo 
     */

    void saveAssignmentResourceTag(AssignmentResourceTagVO  vo)throws LmsDaoException;
    /**
     * This method  used for delete
     * @param vo
     * @return true/false
     */

    boolean deleteAssignmentResourceTag(AssignmentResourceTagVO  vo)throws LmsDaoException;
    
    /**
     * This method used for get user login id.
     * @param id
     * @return teacherDtls
     */

    AssignmentResourceTagVO  getAssignmentResourceTag(int id)throws LmsDaoException;

    /**
     * 
     * @return
     */
    List<AssignmentResourceTagVO > getAssignmentResourceTagList()throws LmsDaoException;

}

package com.scolere.lms.persistance.dao.iface;

import java.util.List;

import com.scolere.lms.domain.exception.LmsDaoException;
import com.scolere.lms.domain.vo.AssignmentResourceTxnVO;

public interface AssignmentResourceTxnDao {
	
	boolean updateAssignmentResourceTxn(AssignmentResourceTxnVO  vo)throws LmsDaoException;
    /**
     * This method is used for save user login
     * @param vo 
     */

    void saveAssignmentResourceTxn(AssignmentResourceTxnVO  vo)throws LmsDaoException;
    /**
     * This method  used for delete
     * @param vo
     * @return true/false
     */

    boolean deleteAssignmentResourceTxn(AssignmentResourceTxnVO  vo)throws LmsDaoException;
    
    /**
     * This method used for get user login id.
     * @param id
     * @return teacherDtls
     */

    AssignmentResourceTxnVO  getAssignmentResourceTxn(int id)throws LmsDaoException;

    /**
     * 
     * @return
     */
    List<AssignmentResourceTxnVO > getAssignmentResourceTxnList()throws LmsDaoException;

}

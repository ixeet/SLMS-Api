package com.scolere.lms.persistance.dao.iface;

import java.util.List;

import com.scolere.lms.domain.exception.LmsDaoException;
import com.scolere.lms.domain.vo.AssignmentVdoTxnVO;

public interface AssignmentVdoTxnDao {
	
	boolean updateAssignmentVdoTxn(AssignmentVdoTxnVO  vo) throws LmsDaoException;
    /**
     * This method is used for save user login
     * @param vo 
     */

    void saveAssignmentVdoTxn(AssignmentVdoTxnVO  vo) throws LmsDaoException;
    /**
     * This method  used for delete
     * @param vo
     * @return true/false
     */

    boolean deleteAssignmentVdoTxn(AssignmentVdoTxnVO  vo) throws LmsDaoException;
    
    /**
     * This method used for get user login id.
     * @param id
     * @return teacherDtls
     */

    AssignmentVdoTxnVO  getAssignmentVdoTxn(int id) throws LmsDaoException;

    /**
     * 
     * @return
     */
    List<AssignmentVdoTxnVO > getAssignmentVdoTxnList() throws LmsDaoException;

}

package com.scolere.lms.persistance.dao.iface;

import java.util.List;

import com.scolere.lms.domain.exception.LmsDaoException;
import com.scolere.lms.domain.vo.AssignmentTypMstrVO;

public interface AssignmentTypMstrDao {
	
	boolean updateAssignmentTypMstr(AssignmentTypMstrVO  vo)throws LmsDaoException;
    /**
     * This method is used for save AssignmentTypMstrDao
     * @param vo 
     */

    void saveAssignmentTypMstr(AssignmentTypMstrVO  vo)throws LmsDaoException;
    /**
     * This method  used for delete AssignmentTypMstrDao
     * @param vo
     * @return true/false
     */

    boolean deleteAssignmentTypMstr(AssignmentTypMstrVO  vo)throws LmsDaoException;
    
    /**
     * This method used for get AssignmentTypMstrDao
     * @param id
     * @return teacherDtls
     */

    AssignmentTypMstrVO  getAssignmentTypMstr(int id)throws LmsDaoException;

    /**
     * 
     * @return
     */
    List<AssignmentTypMstrVO > getAssignmentTypMstrList()throws LmsDaoException;

}

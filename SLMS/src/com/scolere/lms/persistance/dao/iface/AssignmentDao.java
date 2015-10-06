package com.scolere.lms.persistance.dao.iface;

import java.util.List;

import com.scolere.lms.domain.exception.LmsDaoException;
import com.scolere.lms.domain.vo.AssignmentVO;
import com.scolere.lms.domain.vo.ModuleMasterVo;


public interface AssignmentDao {
	
	boolean updateAssignment(AssignmentVO  vo)throws LmsDaoException;
    /**
     * This method is used for save user login
     * @param vo 
     */

    void saveAssignment(AssignmentVO  vo)throws LmsDaoException;
    /**
     * This method  used for delete
     * @param vo
     * @return true/false
     */

    boolean deleteAssignment(AssignmentVO  vo)throws LmsDaoException;
    
    /**
     * This method used for get user login id.
     * @param id
     * @return teacherDtls
     */

    AssignmentVO  getAssignment(int id)throws LmsDaoException;

    /**
     * 
     * @return
     */
    List<AssignmentVO > getAssignmentList()throws LmsDaoException;
	List<com.scolere.lms.domain.vo.AssignmentVO> getAssignmentList(int moduleMasterId, int homeRoomMstrId, int classId, int schoolId,int teacherId)  throws LmsDaoException;

}

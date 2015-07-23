package com.scolere.lms.persistance.dao.iface;

import java.util.List;

import com.scolere.lms.domain.exception.LmsDaoException;
import com.scolere.lms.domain.vo.LmsFeedCommentsVO;

public interface LmsFeedCommentsDao {
	
	boolean updateLmsFeedComments(LmsFeedCommentsVO  vo)throws LmsDaoException;
    /**
     * This method is used for 
     * @param vo 
     */

    void saveLmsFeedComments(LmsFeedCommentsVO  vo)throws LmsDaoException;
    /**
     * This method  used for delete
     * @param vo
     * @return true/false
     */

    boolean deleteLmsFeedComments(LmsFeedCommentsVO  vo)throws LmsDaoException;
    
    /**
     * This method used for get .
     * @param id
     * @return teacherDtls
     */

    LmsFeedCommentsVO  getLmsFeedComments(int id)throws LmsDaoException;

    /**
     * 
     * @return
     */
    List<LmsFeedCommentsVO > getLmsFeedCommentsVOList()throws LmsDaoException;


}

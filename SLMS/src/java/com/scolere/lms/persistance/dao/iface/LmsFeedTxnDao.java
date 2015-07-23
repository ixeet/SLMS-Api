package com.scolere.lms.persistance.dao.iface;

import java.util.List;

import com.scolere.lms.domain.exception.LmsDaoException;
import com.scolere.lms.domain.vo.LmsFeedTxnVO;

public interface LmsFeedTxnDao {
	
	boolean updateLmsFeedTxn(LmsFeedTxnVO  vo)throws LmsDaoException;
    /**
     * This method is used for 
     * @param vo 
     */

    void saveLmsFeedTxn(LmsFeedTxnVO  vo)throws LmsDaoException;
    /**
     * This method  used for delete
     * @param vo
     * @return true/false
     */

    boolean deleteLmsFeedTxn(LmsFeedTxnVO  vo)throws LmsDaoException;
    
    /**
     * This method used for get .
     * @param id
     * @return teacherDtls
     */

    LmsFeedTxnVO  getLmsFeedTxn(int id)throws LmsDaoException;

    /**
     * 
     * @return
     */
    List<LmsFeedTxnVO > getLmsFeedTxnList()throws LmsDaoException;


}

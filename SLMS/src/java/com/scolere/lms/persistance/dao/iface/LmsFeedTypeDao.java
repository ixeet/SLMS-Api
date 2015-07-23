package com.scolere.lms.persistance.dao.iface;

import java.util.List;

import com.scolere.lms.domain.exception.LmsDaoException;
import com.scolere.lms.domain.vo.LmsFeedTypeVO;

public interface LmsFeedTypeDao {
	
	boolean updateLmsFeedType(LmsFeedTypeVO  vo)throws LmsDaoException;
    /**
     * This method is used for 
     * @param vo 
     */

    void saveLmsFeedType(LmsFeedTypeVO  vo)throws LmsDaoException;
    /**
     * This method  used for delete
     * @param vo
     * @return true/false
     */

    boolean deleteLmsFeedType(LmsFeedTypeVO  vo)throws LmsDaoException;
    
    /**
     * This method used for get .
     * @param id
     * @return teacherDtls
     */

    LmsFeedTypeVO  getLmsFeedType(int id)throws LmsDaoException;

    /**
     * 
     * @return
     */
    List<LmsFeedTypeVO > getLmsFeedTypeList()throws LmsDaoException;


}

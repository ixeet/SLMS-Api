package com.scolere.lms.persistance.dao.iface;

import java.util.List;

import com.scolere.lms.domain.exception.LmsDaoException;
import com.scolere.lms.domain.vo.LmsFeedLikeVO;

public interface LmsFeedLikeDao {
	
	boolean updateLmsFeedLike(LmsFeedLikeVO  vo)throws LmsDaoException;
    /**
     * This method is used for 
     * @param vo 
     */

    void saveLmsFeedLike(LmsFeedLikeVO  vo)throws LmsDaoException;
    /**
     * This method  used for delete
     * @param vo
     * @return true/false
     */

    boolean deleteLmsFeedLike(LmsFeedLikeVO  vo)throws LmsDaoException;
    
    /**
     * This method used for get .
     * @param id
     * @return teacherDtls
     */

    LmsFeedLikeVO  getLmsFeedLike(int id)throws LmsDaoException;

    /**
     * 
     * @return
     */
    List<LmsFeedLikeVO > getLmsFeedLikeList()throws LmsDaoException;


}

package com.scolere.lms.persistance.dao.iface;

import java.util.List;

import com.scolere.lms.domain.exception.LmsDaoException;
import com.scolere.lms.domain.vo.LmsFeedLikesVO;

public interface LmsFeedLikesDao {
	
	boolean updateLmsFeedLikes(LmsFeedLikesVO  vo)throws LmsDaoException;
    /**
     * This method is used for 
     * @param vo 
     */

    void saveLmsFeedLikes(LmsFeedLikesVO  vo)throws LmsDaoException;
    /**
     * This method  used for delete
     * @param vo
     * @return true/false
     */

    boolean deleteLmsFeedLikes(LmsFeedLikesVO  vo)throws LmsDaoException;
    
    /**
     * This method used for get .
     * @param id
     * @return teacherDtls
     */

    LmsFeedLikesVO  getLmsFeedLikes(int id)throws LmsDaoException;

    /**
     * 
     * @return
     */
    List<LmsFeedLikesVO > getLmsFeedLikesList()throws LmsDaoException;

	
}

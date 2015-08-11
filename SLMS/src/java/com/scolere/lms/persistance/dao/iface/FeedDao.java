/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scolere.lms.persistance.dao.iface;

import com.scolere.lms.domain.exception.LmsDaoException;
import com.scolere.lms.domain.vo.cross.AssignmentVO;
import com.scolere.lms.domain.vo.cross.CommentVO;
import com.scolere.lms.domain.vo.cross.CourseVO;
import com.scolere.lms.domain.vo.cross.FeedVO;
import com.scolere.lms.domain.vo.cross.ResourseVO;
import com.scolere.lms.domain.vo.cross.UserVO;
import java.util.List;

/**
 *
 * @author dell
 */
public interface FeedDao {
    
    List<FeedVO> getFeedsList(int userId,String searchTxt) throws LmsDaoException;
    List<CommentVO> getFeedCommentsList(int feedId) throws LmsDaoException;
    ResourseVO getDefaultResourseDetail(int feedId) throws LmsDaoException;
    
    FeedVO getFeedDetail(int feedId) throws LmsDaoException;
    UserVO getUserDetail(String userName) throws LmsDaoException;
    UserVO getUserDetail(int userId) throws LmsDaoException;
    CourseVO getCourseDetail(int feedId) throws LmsDaoException;
    CourseVO getModuleDetail(int feedId) throws LmsDaoException;
    ResourseVO getResourseDetail(int feedId) throws LmsDaoException;
    AssignmentVO getAssignmentDetail(int feedId) throws LmsDaoException;
    
//    HashMap<Integer,LmsFeedTypeVO> getFeedTemplates() throws LmsDaoException;
//    List<LmsFeedTxnVO> getAllFeeds(int userId,String searchText) throws LmsDaoException; 
    String getResourceFeedText(int resourceId) throws LmsDaoException;
    String getCourseFeedText(int courseId) throws LmsDaoException;
    String getUserFeedText(int userId) throws LmsDaoException;
    String getAssignmentFeedText(int assignmentId) throws LmsDaoException;
    String getModuleFeedText(int moduleId) throws LmsDaoException;   
    
    boolean saveFeedComment(String commentBy,int feedId,String commentTxt) throws LmsDaoException;
    boolean saveCommentComment(String commentBy,int commentId,String commentTxt) throws LmsDaoException;
    boolean saveCommentLike(String commentBy,int commentId) throws LmsDaoException;
    boolean saveFeedLike(String commentBy,int feedId) throws LmsDaoException;    
    
}//End of class

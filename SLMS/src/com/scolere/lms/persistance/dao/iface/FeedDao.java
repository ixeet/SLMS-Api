/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scolere.lms.persistance.dao.iface;

import java.util.List;

import com.scolere.lms.domain.exception.LmsDaoException;
import com.scolere.lms.domain.vo.cross.AssignmentVO;
import com.scolere.lms.domain.vo.cross.CommentVO;
import com.scolere.lms.domain.vo.cross.CourseVO;
import com.scolere.lms.domain.vo.cross.FeedVO;
import com.scolere.lms.domain.vo.cross.ResourseVO;
import com.scolere.lms.domain.vo.cross.SearchVO;
import com.scolere.lms.domain.vo.cross.UserVO;

/**
 *
 * @author dell
 */
public interface FeedDao {
	
	List<SearchVO> getSearchList(int userId,String searchTxt,int offset,int noOfRecords) throws LmsDaoException;
	List<SearchVO> getSearchList(int userId,String searchTxt,int offset,int noOfRecords,String category) throws LmsDaoException;
	int getSearchRecordsCount(int userId,String searchTxt,String category) throws LmsDaoException;
	
	long updateNotificationStatus(int userId,int feedId,String status) throws LmsDaoException;
	FeedVO getFeedDetail(int userId,int feedId) throws LmsDaoException;
    List<FeedVO> getNotificationsList(int userId,String searchTxt,int offset,int noOfRecords) throws LmsDaoException;
    List<FeedVO> getFeedsList(int userId,String searchTxt,int offset,int noOfRecords) throws LmsDaoException;
    List<CommentVO> getFeedCommentsList(int feedId,int userId,int offset,int noOfRecords) throws LmsDaoException;
    List<CommentVO> getFeedChildCommentsList(int commentId,int userId,int offset,int noOfRecords) throws LmsDaoException;
    ResourseVO getDefaultResourseDetail(int feedId) throws LmsDaoException;
    
    FeedVO getFeedDetail(int feedId) throws LmsDaoException;
    UserVO getUserDetail(String userName) throws LmsDaoException;
    UserVO getUserDetail(int userId) throws LmsDaoException;
    CourseVO getCourseDetail(int feedId) throws LmsDaoException;
    CourseVO getModuleDetail(int feedId) throws LmsDaoException;
    ResourseVO getResourseDetail(int feedId) throws LmsDaoException;
    AssignmentVO getAssignmentDetail(int feedId) throws LmsDaoException;
    
    public long getTotalFeedsCount(int userId,String searchText) throws LmsDaoException;
    public long getTotalFeedsCount(int userId) throws LmsDaoException;
    public int getUnreadFeedsCount(int userId) throws LmsDaoException;
    public long getTotalCommentsCount(int feedId) throws LmsDaoException;
    public long getTotalCommentsCount(int feedId,int commentId) throws LmsDaoException;
    
//    HashMap<Integer,LmsFeedTypeVO> getFeedTemplates() throws LmsDaoException;
//    List<LmsFeedTxnVO> getAllFeeds(int userId,String searchText) throws LmsDaoException; 
    String getResourceFeedText(int resourceId) throws LmsDaoException;
    String getCourseFeedText(int courseId) throws LmsDaoException;
    String getUserFeedText(int userId) throws LmsDaoException;
    String getAssignmentFeedText(int assignmentId) throws LmsDaoException;
    String getModuleFeedText(int moduleId) throws LmsDaoException;   
	String getHomeRoomFeedText(int hrmId) throws LmsDaoException;    

    boolean saveFeedComment(String commentBy,int feedId,String commentTxt) throws LmsDaoException;
    boolean saveCommentComment(String commentBy,int commentId,String commentTxt) throws LmsDaoException;
    boolean saveCommentLike(String commentBy,int commentId) throws LmsDaoException;
    boolean saveFeedLike(String commentBy,int feedId) throws LmsDaoException;
    
}//End of class

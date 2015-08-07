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
    UserVO getUserDetail(int userId) throws LmsDaoException;
    CourseVO getCourseDetail(int courseId) throws LmsDaoException;
    CourseVO getModuleDetail(int moduleId) throws LmsDaoException;
    ResourseVO getResourseDetail(int resourseId) throws LmsDaoException;
    AssignmentVO getAssignmentDetail(int assignmentId) throws LmsDaoException;
    
}

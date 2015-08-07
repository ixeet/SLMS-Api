/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scolere.lms.persistance.dao.impl;

import com.scolere.lms.domain.exception.LmsDaoException;
import com.scolere.lms.domain.vo.cross.AssignmentVO;
import com.scolere.lms.domain.vo.cross.CommentVO;
import com.scolere.lms.domain.vo.cross.CourseVO;
import com.scolere.lms.domain.vo.cross.FeedVO;
import com.scolere.lms.domain.vo.cross.ResourseVO;
import com.scolere.lms.domain.vo.cross.UserVO;
import com.scolere.lms.persistance.dao.iface.FeedDao;
import com.scolere.lms.persistance.factory.LmsDaoAbstract;
import java.util.List;

/**
 *
 * @author dell
 */
public class FeedDaoImpl extends LmsDaoAbstract implements FeedDao{

    @Override
    public List<FeedVO> getFeedsList(int userId, String searchTxt) throws LmsDaoException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<CommentVO> getFeedCommentsList(int feedId) throws LmsDaoException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public UserVO getUserDetail(int userId) throws LmsDaoException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public CourseVO getCourseDetail(int courseId) throws LmsDaoException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public CourseVO getModuleDetail(int moduleId) throws LmsDaoException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ResourseVO getResourseDetail(int resourseId) throws LmsDaoException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public AssignmentVO getAssignmentDetail(int assignmentId) throws LmsDaoException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    
    
}//End of class

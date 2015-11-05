/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scolere.lms.service.iface;

import com.scolere.lms.domain.exception.LmsServiceException;
import com.scolere.lms.domain.vo.CommonKeyValueVO;
import com.scolere.lms.domain.vo.LoginSessionVo;
import com.scolere.lms.domain.vo.StudentDetailVo;
import com.scolere.lms.domain.vo.UserClassMapVo;
import com.scolere.lms.domain.vo.UserLoginVo;
import com.scolere.lms.domain.vo.cross.UserVO;
import java.util.List;

/**
 *
 * @author dell
 */
public interface LoginServiceIface {

	boolean isAdminEmailValid(int schoolId,String adminEmail) throws LmsServiceException;
	//Fee users start
	int updateFollowersStatus(int userId,List<UserVO> usersList) throws LmsServiceException;
    int getFeedAccessType(int userId) throws LmsServiceException;
    int setFeedAccessType(int userId,int accesTypeId) throws LmsServiceException;
    List<UserVO> getFeedUsers(int userId) throws LmsServiceException;
    List<CommonKeyValueVO> getAccessTypeMasterData() throws LmsServiceException;
	//Feed users end
    
    boolean defaultFeedsAccessType(int userId,int hrmId) throws LmsServiceException;

    boolean defaultUserAssignment(String userName,int schoolId,int classId,int hrmId) throws LmsServiceException;

    void saveUserClassMapDetail(UserClassMapVo   vo) throws LmsServiceException;
    
    UserVO  getUser(String userName,String userPwd) throws LmsServiceException; 

    UserVO  getUser(String userName,String userPwd,int userType) throws LmsServiceException; 
    
    UserVO  getUser(String facebookId) throws LmsServiceException; 
    
    UserVO  getUserOrgDetail(String userId) throws LmsServiceException; 

    /**
     *
     * @param vo
     * @return true/false
     */
    boolean updateUserFBId(String userName,String userFbId) throws LmsServiceException;
    
    boolean updateUserPwd(String userName,String userPwd,String userNewPwd) throws LmsServiceException;
    
    boolean updateUserLoginDetail(UserLoginVo vo) throws LmsServiceException;

    /**
     * This method is used for save user login
     *
     * @param vo
     */
    UserLoginVo saveUserLoginDetail(UserLoginVo vo) throws LmsServiceException;

    /**
     * This method used for delete
     *
     * @param vo
     * @return true/false
     */
    boolean deleteUserLoginDetail(UserLoginVo vo) throws LmsServiceException;

    /**
     * This method used for get user login id.
     *
     * @param id
     * @return teacherDtls
     */
    UserLoginVo getUserLoginDetail(int id) throws LmsServiceException;

    UserLoginVo getUserLoginDetail(String userName) throws LmsServiceException;

    UserLoginVo getUserLoginDetail(String userName,String userPwd) throws LmsServiceException;
    
    List<UserLoginVo> getUserLoginVoList() throws LmsServiceException;
    
    boolean updateStudentDetail(StudentDetailVo vo) throws LmsServiceException;
    
    boolean updateProfilePhoto(String photoName,String userNm) throws LmsServiceException; 
    /**
     * This method is used for save detail
     *
     * @param vo
     */
    boolean saveStudentDetail(StudentDetailVo vo) throws LmsServiceException;


    /**
     * This method used for delete detail
     *
     * @param vo
     * @return true/false
     */
    boolean deleteStudentDetail(StudentDetailVo vo) throws LmsServiceException;

    /**
     * This method used for get user .
     *
     * @param id
     * @return studentDtls
     */
    StudentDetailVo getStudentDetail(int id) throws LmsServiceException;

    
    /**
     * This is used for list.
     *
     * @return
     */
    List<StudentDetailVo> getStudentDetailVoList() throws LmsServiceException;

    boolean updateLoginSession(LoginSessionVo vo) throws LmsServiceException;

    /**
     * This method is used for save login session
     *
     * @param vo
     */
    boolean saveLoginSession(LoginSessionVo vo) throws LmsServiceException;

    /**
     * This method used for delete login session
     *
     * @param vo
     * @return true/false
     */
    boolean deleteLoginSession(LoginSessionVo vo) throws LmsServiceException;

    /**
     * This method used for get user login session.
     *
     * @param id
     * @return teacherDtls
     */
    LoginSessionVo getLoginSession(int id) throws LmsServiceException;

    /**
     * This is used for list.
     *
     * @return
     */
    List< LoginSessionVo> getLoginSessionList() throws LmsServiceException;
}

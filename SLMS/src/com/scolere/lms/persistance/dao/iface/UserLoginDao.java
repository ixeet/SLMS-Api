package com.scolere.lms.persistance.dao.iface;

import com.scolere.lms.domain.exception.LmsDaoException;
import com.scolere.lms.domain.vo.CommonKeyValueVO;
import com.scolere.lms.domain.vo.UserLoginVo;
import com.scolere.lms.domain.vo.cross.UserVO;
import java.util.List;

/**
 *
 * @author admin
 */
public interface UserLoginDao {
    
	//Fee users start
	int updateFollowersStatus(int userId,List<UserVO> usersList) throws LmsDaoException;
    int getFeedAccessType(int userId) throws LmsDaoException;
    int setFeedAccessType(int userId,int accesTypeId) throws LmsDaoException;
    List<UserVO> getFeedUsers(int userId) throws LmsDaoException;
    List<CommonKeyValueVO> getAccessTypeMasterData() throws LmsDaoException;
	//Feed users end

    boolean defaultFeedsAccessType(int userId,int hrmId) throws LmsDaoException;
	boolean defaultUserAssignment(String userName,int schoolId,int classId,int hrmId) throws LmsDaoException;
	
    /**
     * Get user complete details by user name & userPwd
     * @param userName
     * @return UserVO
     * @throws LmsDaoException 
     */
    UserVO  getUser(String userName,String userPwd) throws LmsDaoException;

    UserVO  getUser(String userName,String userPwd,int userType) throws LmsDaoException;

    /**
     * Get user complete details by user facebook id
     * @param userName
     * @return UserVO
     * @throws LmsDaoException 
     */
    UserVO  getUser(String facebookId) throws LmsDaoException;    
    
    /**
     * 
     * @param vo
     * @return true/false
     */
    boolean updateUserFBId(String userName,String fbId) throws LmsDaoException;    
        /**
     * 
     * @param vo
     * @return true/false
     */
    boolean updateUserPwd(String userName,String userPwd,String userNewPwd) throws LmsDaoException; 
    /**
     * 
     * @param vo
     * @return true/false
     */
    boolean updateUserLoginDetail(UserLoginVo  vo) throws LmsDaoException;
    /**
     * This method is used for save user login
     * @param vo 
     */

    UserLoginVo saveUserLoginDetail(UserLoginVo  vo) throws LmsDaoException;
    /**
     * This method  used for delete
     * @param vo
     * @return true/false
     */

    boolean deleteUserLoginDetail(UserLoginVo  vo) throws LmsDaoException;
    /**
     * This method used for get user login id.
     * @param id
     * @return teacherDtls
     */

    UserLoginVo  getUserLoginDetail(int id) throws LmsDaoException;

    UserLoginVo  getUserLoginDetail(String userName) throws LmsDaoException;

    UserLoginVo  getUserLoginDetail(String userName,String userPwd) throws LmsDaoException;

    List<UserLoginVo > getUserLoginVoList() throws LmsDaoException;
    
	UserVO getUserOrgDetail(String userId) throws LmsDaoException;
	
	boolean isAdminEmailValid(int schoolId, String adminEmail) throws LmsDaoException;
    
}

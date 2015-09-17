/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scolere.lms.persistance.dao.impl;

import com.scolere.lms.domain.exception.LmsDaoException;
import com.scolere.lms.domain.vo.UserLoginVo;
import com.scolere.lms.domain.vo.cross.UserVO;
import com.scolere.lms.persistance.dao.iface.UserLoginDao;
import com.scolere.lms.persistance.factory.LmsDaoAbstract;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class UserLoginDaoImpl extends LmsDaoAbstract implements UserLoginDao {

    /**
     * 
     * @param userName
     * @param userPwd
     * @return
     * @throws LmsDaoException 
     */
    public UserVO getUser(String userName, String userPwd) throws LmsDaoException {
        System.out.println("getUser(name,pwd) >>");
        
        UserVO user = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();

            //String sql = "SELECT ul.USER_ID,ul.USER_NM,ul.USER_FB_ID,sdtl.FNAME,sdtl.LNAME,sdtl.EMAIL_ID,sdtl.ADDRESS,ucm.SCHOOL_ID,ucm.CLASS_ID,ucm.HRM_ID FROM user_login ul inner join student_dtls sdtl on ul.user_id = sdtl.user_id inner join user_cls_map ucm on ucm.USER_ID = ul.USER_ID where ul.USER_NM=? and ul.USER_PWD=?";
            String sql = "SELECT ul.USER_ID,ul.USER_NM,ul.USER_FB_ID,sdtl.FNAME,sdtl.LNAME,sdtl.EMAIL_ID,sdtl.ADDRESS,sdtl.PROFILE_IMG," +
            		"ucm.SCHOOL_ID,ucm.CLASS_ID,ucm.HRM_ID,schol.SCHOOL_NAME,clas.CLASS_NAME,hrm.HRM_NAME,sdtl.TITLE " +
            		"FROM user_login ul inner join student_dtls sdtl on ul.user_id = sdtl.user_id " +
            		"inner join user_cls_map ucm on ucm.USER_ID = ul.USER_ID inner join school_mstr schol on schol.SCHOOL_ID=ucm.SCHOOL_ID" +
            		" inner join class_mstr clas on clas.CLASS_ID=ucm.CLASS_ID inner join homeroom_mstr hrm on hrm.HRM_ID=ucm.HRM_ID " +
            		"where ul.USER_NM=? and ul.USER_PWD=?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, userName);
            stmt.setString(2, userPwd);
            
            rs = stmt.executeQuery();
            if (rs.next()) {
            // USER_ID     USER_NM     USER_FB_ID     FNAME     LNAME     EMAIL_ID     ADDRESS     SCHOOL_ID     CLASS_ID     HRM_ID  schol.SCHOOL_NAME clas.CLASS_NAME hrm.HRM_NAME sdtl.TITLE  
                user = new UserVO();
                user.setUserId(rs.getInt(1));
                user.setUserName(rs.getString(2));
                user.setUserFbId(rs.getString(3));
                user.setFirstName(rs.getString(4));
                user.setLastName(rs.getString(5));
                user.setEmailId(rs.getString(6));
                user.setAddress(rs.getString(7));
                user.setProfileImage(rs.getString(8));
                user.setSchoolId(rs.getInt(9));
                user.setClassId(rs.getInt(10));
                user.setHomeRoomId(rs.getInt(11));
                
                user.setSchoolName(rs.getString(12));
                user.setClassName(rs.getString(13));
                user.setHomeRoomName(rs.getString(14));
                user.setTitle(rs.getString(15));
            }

        } catch (SQLException se) {
            System.out.println("getUser # " + se);
            throw new LmsDaoException(se.getMessage());
        } catch (Exception e) {
            System.out.println("getUser # " + e);
            throw new LmsDaoException(e.getMessage());
        } finally {
            closeResources(conn, stmt, rs);
        }
        
        return user;
    }

    
    public UserVO getUser(String userName, String userPwd,int userType) throws LmsDaoException {
        System.out.println("getUser(name,pwd,userType) >>");
        
        UserVO user = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();

            if(userType==3) //Student
            {
             System.out.println("Student login.");
            //String sql = "SELECT ul.USER_ID,ul.USER_NM,ul.USER_FB_ID,sdtl.FNAME,sdtl.LNAME,sdtl.EMAIL_ID,sdtl.ADDRESS,ucm.SCHOOL_ID,ucm.CLASS_ID,ucm.HRM_ID FROM user_login ul inner join student_dtls sdtl on ul.user_id = sdtl.user_id inner join user_cls_map ucm on ucm.USER_ID = ul.USER_ID where ul.USER_NM=? and ul.USER_PWD=?";
            String sql = "SELECT ul.USER_ID,ul.USER_NM,ul.USER_FB_ID,sdtl.FNAME,sdtl.LNAME,sdtl.EMAIL_ID,sdtl.ADDRESS,sdtl.PROFILE_IMG," +
            		"ucm.SCHOOL_ID,ucm.CLASS_ID,ucm.HRM_ID,schol.SCHOOL_NAME,clas.CLASS_NAME,hrm.HRM_NAME,sdtl.TITLE " +
            		"FROM user_login ul inner join student_dtls sdtl on ul.user_id = sdtl.user_id " +
            		"inner join user_cls_map ucm on ucm.USER_ID = ul.USER_ID inner join school_mstr schol on schol.SCHOOL_ID=ucm.SCHOOL_ID" +
            		" inner join class_mstr clas on clas.CLASS_ID=ucm.CLASS_ID inner join homeroom_mstr hrm on hrm.HRM_ID=ucm.HRM_ID " +
            		"where ul.USER_NM=? and ul.USER_PWD=?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, userName);
            stmt.setString(2, userPwd);
            
            rs = stmt.executeQuery();
            if (rs.next()) {
            // USER_ID     USER_NM     USER_FB_ID     FNAME     LNAME     EMAIL_ID     ADDRESS     SCHOOL_ID     CLASS_ID     HRM_ID  schol.SCHOOL_NAME clas.CLASS_NAME hrm.HRM_NAME sdtl.TITLE  
                user = new UserVO();
                user.setUserId(rs.getInt(1));
                user.setUserName(rs.getString(2));
                user.setUserFbId(rs.getString(3));
                user.setFirstName(rs.getString(4));
                user.setLastName(rs.getString(5));
                user.setEmailId(rs.getString(6));
                user.setAddress(rs.getString(7));
                user.setProfileImage(rs.getString(8));
                user.setSchoolId(rs.getInt(9));
                user.setClassId(rs.getInt(10));
                user.setHomeRoomId(rs.getInt(11));
                user.setSchoolName(rs.getString(12));
                user.setClassName(rs.getString(13));
                user.setHomeRoomName(rs.getString(14));
                user.setTitle(rs.getString(15));
            }
            }else if(userType==2) //Teacher
            {
            	
            	System.out.println("Teacher login.");
                String sql = "SELECT usr.USER_ID,usr.USER_NM,usr.USER_FB_ID,teachr.FNAME,teachr.LNAME,teachr.EMAIL_ID,teachr.ADDRESS FROM teacher_dtls teachr INNER JOIN user_login usr ON teachr.USER_ID=usr.USER_ID where usr.USER_NM=? AND usr.USER_PWD=?";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, userName);
                stmt.setString(2, userPwd);
                
                rs = stmt.executeQuery();
                if (rs.next()) {
                // USER_ID     USER_NM  USER_FB_ID     FNAME     LNAME     EMAIL_ID,ADDRESS    USER_TYPE_ID    
                    user = new UserVO();
                    user.setUserId(rs.getInt(1));
                    user.setUserName(rs.getString(2));
                    user.setUserFbId(rs.getString(3));
                    user.setFirstName(rs.getString(4));
                    user.setLastName(rs.getString(5));
                    user.setEmailId(rs.getString(6));
                    user.setAddress(rs.getString(7));
                    user.setProfileImage(rs.getString(7));
                   
                }
            	
            }else{
            	throw new LmsDaoException("Unknown user type.");
            }

        } catch (SQLException se) {
            System.out.println("getUser # " + se);
            throw new LmsDaoException(se.getMessage());
        } catch (Exception e) {
            System.out.println("getUser # " + e);
            throw new LmsDaoException(e.getMessage());
        } finally {
            closeResources(conn, stmt, rs);
        }
        
        return user;
    }


    
    public UserVO getUser(String facebookId) throws LmsDaoException {
        System.out.println("getUser(facebookId) >>");
        
        UserVO user = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();

            String sql = "SELECT ul.USER_ID,ul.USER_NM,ul.USER_FB_ID,sdtl.FNAME,sdtl.LNAME,sdtl.EMAIL_ID,sdtl.ADDRESS,sdtl.PROFILE_IMG," +
            		"ucm.SCHOOL_ID,ucm.CLASS_ID,ucm.HRM_ID,schol.SCHOOL_NAME,clas.CLASS_NAME,hrm.HRM_NAME,sdtl.TITLE " +
            		"FROM user_login ul inner join student_dtls sdtl on ul.user_id = sdtl.user_id " +
            		"inner join user_cls_map ucm on ucm.USER_ID = ul.USER_ID inner join school_mstr schol on schol.SCHOOL_ID=ucm.SCHOOL_ID" +
            		" inner join class_mstr clas on clas.CLASS_ID=ucm.CLASS_ID inner join homeroom_mstr hrm on hrm.HRM_ID=ucm.HRM_ID" +
            		" where ul.USER_FB_ID=?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, facebookId);
            
            rs = stmt.executeQuery();
            if (rs.next()) {
            // USER_ID     USER_NM     USER_FB_ID     FNAME     LNAME     EMAIL_ID     ADDRESS     SCHOOL_ID     CLASS_ID     HRM_ID    
                user = new UserVO();
                user.setUserId(rs.getInt(1));
                user.setUserName(rs.getString(2));
                user.setUserFbId(rs.getString(3));
                user.setFirstName(rs.getString(4));
                user.setLastName(rs.getString(5));
                user.setEmailId(rs.getString(6));
                user.setAddress(rs.getString(7));  
                user.setProfileImage(rs.getString(8));
                user.setSchoolId(rs.getInt(9));
                user.setClassId(rs.getInt(10));
                user.setHomeRoomId(rs.getInt(11));
                
                user.setSchoolName(rs.getString(12));
                user.setClassName(rs.getString(13));
                user.setHomeRoomName(rs.getString(14));
                user.setTitle(rs.getString(15));
                
            }

        } catch (SQLException se) {
            System.out.println("getUser # " + se);
            throw new LmsDaoException(se.getMessage());
        } catch (Exception e) {
            System.out.println("getUser # " + e);
            throw new LmsDaoException(e.getMessage());
        } finally {
            closeResources(conn, stmt, rs);
        }
        
        return user;
    }    
    
    public UserLoginVo getUserLoginDetail(int id) throws LmsDaoException {
        System.out.println("Inside getUserLoginDetail(?) >>");
        //Create object to return
        UserLoginVo userDtls =null;

        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = getConnection();

            String sql = "SELECT * FROM user_login where USER_ID=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
            	 userDtls = new UserLoginVo();
                //3. Set db data to object
                userDtls.setUserId(rs.getInt("USER_ID"));
                userDtls.setUserPwd(rs.getString("USER_PWD"));
                userDtls.setUserFbId(rs.getString("USER_FB_ID"));
                userDtls.setUserName(rs.getString("USER_NM"));
                userDtls.setUserTypeId(rs.getInt("USER_TYPE_ID"));
                userDtls.setDeletedFl(rs.getString("DELETED_FL"));
                userDtls.setEnableFl(rs.getString("ENABLE_FL"));
                userDtls.setLastUserIdCd(rs.getString("LAST_USERID_CD"));
                userDtls.setLastUpdtTm(rs.getString("LAST_UPDT_TM"));
            }

            System.out.println("get records into the table...");

        } catch (SQLException se) {
            System.out.println("getUserLoginDetail # " + se);
        } catch (Exception e) {
            System.out.println("getUserLoginDetail # " + e);
        } finally {
            closeResources(conn, stmt, null);
        }

        return userDtls;
    }

    
    public UserLoginVo getUserLoginDetail(String userName) throws LmsDaoException {
        System.out.println("Inside getUserLoginDetail(userName) >>");
        //Create object to return
        UserLoginVo userDtls = null;

        //1 . jdbc code start
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = getConnection();

            String sql = "SELECT * FROM user_login where USER_NM=?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, userName);
            
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                userDtls = new UserLoginVo();
                userDtls.setUserId(rs.getInt("USER_ID"));
                userDtls.setUserPwd(rs.getString("USER_PWD"));
                userDtls.setUserFbId(rs.getString("USER_FB_ID"));
                userDtls.setUserName(rs.getString("USER_NM"));
                userDtls.setUserTypeId(rs.getInt("USER_TYPE_ID"));
                userDtls.setDeletedFl(rs.getString("DELETED_FL"));
                userDtls.setEnableFl(rs.getString("ENABLE_FL"));
                userDtls.setLastUserIdCd(rs.getString("LAST_USERID_CD"));
                userDtls.setLastUpdtTm(rs.getString("LAST_UPDT_TM"));
            }


        } catch (SQLException se) {
            System.out.println("getUserLoginDetail # " + se);
        } catch (Exception e) {
            System.out.println("getUserLoginDetail # " + e);
        } finally {
            closeResources(conn, stmt, null);
        }

        return userDtls;
    }
    
    
    
    public UserLoginVo getUserLoginDetail(String userName,String userPwd) throws LmsDaoException {
        System.out.println("Inside getUserLoginDetail(userName,userPwd) >>");
        //Create object to return
        UserLoginVo userDtls = null;

        //1 . jdbc code start
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = getConnection();

            String sql = "SELECT * FROM user_login where USER_NM=? and USER_PWD=?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, userDtls.getUserName());
            stmt.setString(2, userDtls.getUserPwd());
            
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                userDtls = new UserLoginVo();
                
                userDtls.setUserId(rs.getInt("USER_ID"));
                userDtls.setUserPwd(rs.getString("USER_PWD"));
                userDtls.setUserFbId(rs.getString("USER_FB_ID"));
                userDtls.setUserName(rs.getString("USER_NM"));
                userDtls.setUserTypeId(rs.getInt("USER_TYPE_ID"));
                userDtls.setDeletedFl(rs.getString("DELETED_FL"));
                userDtls.setEnableFl(rs.getString("ENABLE_FL"));
                userDtls.setLastUserIdCd(rs.getString("LAST_USERID_CD"));
                userDtls.setLastUpdtTm(rs.getString("LAST_UPDT_TM"));
            }

        } catch (SQLException se) {
            System.out.println("getUserLoginDetail # " + se);
            throw new LmsDaoException(se.getMessage());
        } catch (Exception e) {
            System.out.println("getUserLoginDetail # " + e);
            throw new LmsDaoException(e.getMessage());
        } finally {
            closeResources(conn, stmt, null);
        }

        return userDtls;
    }
    
    
    public boolean updateUserLoginDetail(UserLoginVo vo) throws LmsDaoException {
        System.out.println("id =" + vo.getUserName());
        boolean status = true;

        //Database connection start
        Connection conn = null;
        PreparedStatement stmt = null;
        try {

            conn = getConnection();
            String sql = "UPDATE user_login set USER_NM=?, USER_PWD=?, DELETED_FL=?, ENABLE_FL=?, LAST_USERID_CD=?, LAST_UPDT_TM=utc_timestamp"
                    + "    WHERE USER_ID=?";
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, vo.getUserName());
            stmt.setString(2, vo.getUserPwd());
            stmt.setString(3, vo.getDeletedFl());
            stmt.setString(4, vo.getEnableFl());
            stmt.setString(5, vo.getLastUserIdCd());
            stmt.setInt(6, vo.getUserId());
            
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("getUserLoginDetail # " + e);
        } catch (Exception e) {
            System.out.println("getUserLoginDetail # " + e);
        } finally {
            closeResources(conn, stmt, null);
        }

        System.out.println("Successfully updated....");
        return status;

    }

    
    public boolean updateUserPwd(String userName,String userPwd,String userNewPwd) throws LmsDaoException {
       
        boolean status = true;

        //Database connection start
        Connection conn = null;
        PreparedStatement stmt = null;
        try {

            conn = getConnection();
            String sql = "UPDATE user_login SET USER_PWD=? WHERE USER_NM = ? and USER_PWD = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, userNewPwd);
            stmt.setString(2, userName);
            stmt.setString(3, userPwd);
            
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("updateUserPwd # " + e);
        } catch (Exception e) {
            System.out.println("updateUserPwd # " + e);
        } finally {
            closeResources(conn, stmt, null);
        }

        return status;

    }
    

    public boolean updateUserFBId(String userName,String fbId) throws LmsDaoException {
       
        boolean status = true;

        //Database connection start
        Connection conn = null;
        PreparedStatement stmt = null;
        try {

            conn = getConnection();
            String sql = "UPDATE user_login SET USER_FB_ID=? WHERE USER_NM=?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, fbId);
            stmt.setString(2, userName);
            
            int t=stmt.executeUpdate();
            if(t>0)
            {
            	//success
            }else{
            	//no update error
            }

        } catch (SQLException e) {
            System.out.println("updateUserLoginDetail # " + e);
        } catch (Exception e) {
            System.out.println("updateUserLoginDetail # " + e);
        } finally {
            closeResources(conn, stmt, null);
        }

        return status;

    }
    
    //save method

    public UserLoginVo saveUserLoginDetail(UserLoginVo vo) throws LmsDaoException {
        UserLoginVo userLoginVo = null;

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;
        try {

            conn = getConnection();
            String sql = "INSERT INTO user_login(USER_NM, USER_PWD, USER_TYPE_ID, DELETED_FL, ENABLE_FL, LAST_USERID_CD, LAST_UPDT_TM) VALUES(?, ?, ?, ?, ?, ?,  utc_timestamp)";
            stmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, vo.getUserName());
            stmt.setString(2, vo.getUserPwd());
            stmt.setInt(3, vo.getUserTypeId());
            stmt.setString(4, vo.getDeletedFl());
            stmt.setString(5, vo.getEnableFl());
            stmt.setString(6, vo.getLastUserIdCd());

            int t=stmt.executeUpdate();
            System.out.println("No of inserted row = "+t);
            resultSet = stmt.getGeneratedKeys();
            if (resultSet.next()) {
            int last_inserted_id = resultSet.getInt(1);
                System.out.println("Last inserted userId : "+last_inserted_id);
                userLoginVo = vo;
                userLoginVo.setUserId(last_inserted_id);
            }

        } catch (SQLException se) {
            System.out.println("saveUserLoginDetail # " + se);
            throw new LmsDaoException(se.getMessage());
        } catch (Exception e) {
            System.out.println("saveUserLoginDetail # " + e);
            throw new LmsDaoException(e.getMessage());
        } finally {
            closeResources(conn, stmt, resultSet);
        }

        return userLoginVo;
    }

    //delete method
    public boolean deleteUserLoginDetail(UserLoginVo vo) throws LmsDaoException {
        System.out.println("id =" + vo.getUserId());
        boolean status = true;

        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = getConnection();

            String sql = "DELETE FROM USER_LOGIN WHERE USER_ID = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, vo.getUserId());
            stmt.executeUpdate();

            System.out.println("Deleted records into the table...");

        } catch (SQLException se) {
            System.out.println("getUserLoginDetail # " + se);
        } catch (Exception e) {
            System.out.println("getUserLoginDetail # " + e);
        } finally {
            closeResources(conn, stmt, null);
        }

        return status;
    }

    @Override
    public List<UserLoginVo> getUserLoginVoList() throws LmsDaoException {
        List<UserLoginVo> distList = new ArrayList<UserLoginVo>();

        //1 . jdbc code start
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = getConnection();

            String sql = "SELECT * FROM user_login ";
            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            UserLoginVo userDtls = null;
            while (rs.next()) {

                //3. Set db data to object
                userDtls = new UserLoginVo();

                userDtls.setUserId(rs.getInt("USER_ID"));
                userDtls.setUserPwd(rs.getString("USER_PWD"));
                userDtls.setUserTypeId(rs.getInt("USER_TYPE_ID"));
                userDtls.setDeletedFl(rs.getString("DELETED_FL"));
                userDtls.setEnableFl(rs.getString("ENABLE_FL"));

                userDtls.setLastUserIdCd(rs.getString("LAST_USERID_CD"));
                userDtls.setLastUpdtTm(rs.getString("LAST_UPDT_TM"));
                //Add into list
                distList.add(userDtls);
            }

            System.out.println("get records into the table...");

        } catch (SQLException se) {
            System.out.println("getUserLoginDetail # " + se);
        } catch (Exception e) {
            System.out.println("getUserLoginDetail # " + e);
        } finally {
            closeResources(conn, stmt, null);
        }

        return distList;
    }


	@Override
	public boolean defaultUserAssignment(String userName, int schoolId,
			int classId, int hrmId) throws LmsDaoException {
		
		//SELECT mam.ASSIGNMENT_ID,'usernm',56,current_date,'1','usernm',current_timestamp,date(mod_sess.END_SESSION_TM) FROM teacher_courses course inner join teacher_course_sessions course_sess on course.TEACHER_COURSE_ID=course_sess.TEACHER_COURSE_ID inner join teacher_course_session_dtls mod_sess on course_sess.COURSE_SESSION_ID=mod_sess.COURSE_SESSION_ID inner join module_assignment_map mam on mam.MODULE_ID=mod_sess.MODULE_ID where course.SCHOOL_ID=1 and course.CLASS_ID=1 and course.HRM_ID=1
        String defaultAssignment="INSERT INTO assignment_resource_txn(ASSIGNMENT_ID, STUDENT_ID, UPLODED_RESOURCE_ID, UPLOADED_ON, IS_COMPLETED, LAST_USERID_CD, LAST_UPDT_TM, DUE_ON) SELECT mam.ASSIGNMENT_ID,'"+userName+"',null,null,'1','"+userName+"',current_timestamp,date(mod_sess.END_SESSION_TM) FROM teacher_courses course inner join teacher_course_sessions course_sess on course.TEACHER_COURSE_ID=course_sess.TEACHER_COURSE_ID inner join teacher_course_session_dtls mod_sess on course_sess.COURSE_SESSION_ID=mod_sess.COURSE_SESSION_ID inner join module_assignment_map mam on mam.MODULE_ID=mod_sess.MODULE_ID where course.SCHOOL_ID="+schoolId+" and course.CLASS_ID="+classId+" and course.HRM_ID="+hrmId+"";
        boolean assignmentStatus = deleteOrUpdateByQuery(defaultAssignment);
        System.out.println("assignmentStatus ? "+assignmentStatus);
        
        return assignmentStatus;
	}

    
}//end of class

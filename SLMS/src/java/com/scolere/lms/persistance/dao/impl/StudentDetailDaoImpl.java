/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scolere.lms.persistance.dao.impl;

import com.scolere.lms.domain.exception.LmsDaoException;
import com.scolere.lms.domain.vo.StudentDetailVo;
import com.scolere.lms.persistance.dao.iface.StudentDetailDao;
import com.scolere.lms.persistance.factory.LmsDaoAbstract;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class StudentDetailDaoImpl extends LmsDaoAbstract implements StudentDetailDao {

    public StudentDetailVo getStudentDetail(int id) throws LmsDaoException {
        System.out.println("Inside getStudentDetail(?) >>");
        //Create object to return
        StudentDetailVo userDtls = new StudentDetailVo();

        //1 . jdbc code start
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = getConnection();

            String sql = "SELECT * FROM student_dtls where STUDENT_DTLS_ID=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, userDtls.getStudentDetailId());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                //3. Set db data to object

                userDtls.setUserId(rs.getInt("STUDENT_DTLS_ID"));
                userDtls.setUserId(rs.getInt("USER_ID"));
                userDtls.setfName(rs.getString("FNAME"));
                userDtls.setlName(rs.getString("LNAME"));
                userDtls.setEmailId(rs.getString("EMAIL_id"));
                userDtls.setJoiningDt(rs.getString("JOINING_DT"));
                userDtls.setContactNo(rs.getString("CONTACT_NO"));
                userDtls.setBirthDt(rs.getString("BIRTH_DT"));
                userDtls.setProfile(rs.getString("PROFILE"));
                userDtls.setSocialProfile(rs.getString("SOCIAL_PROFILE"));
                userDtls.setAddress(rs.getString("ADDRESS"));
                userDtls.setAdminEmailId(rs.getString("ADMIN_EMAIL_ID"));

                userDtls.setLastUserIdCd(rs.getString("LAST_USERID_CD"));
                userDtls.setLastUpdtTm(rs.getString("LAST_UPDT_TM"));

            }

            System.out.println("get records into the table...");

        } catch (SQLException se) {
            System.out.println("getUserLoginDetail # " + se);
            throw new LmsDaoException(se.getMessage());
        } catch (Exception e) {
            System.out.println("getUserLoginDetail # " + e);
            throw new LmsDaoException(e.getMessage());
        } finally {
            closeResources(conn, stmt, null);
        }
        //1 . jdbc code endd

        //4 Return as required by method
        return userDtls;
    }

    
    public boolean updateStudentDetail(StudentDetailVo vo) throws LmsDaoException {
        System.out.println("updateStudentDetail id =" + vo.getStudentDetailId());
        boolean status = false;

        //Database connection start
        Connection conn = null;
        PreparedStatement stmt = null;
        try {

            conn = getConnection();
            String sql = "UPDATE student_dtls set TITLE=?,FNAME=?, LNAME=?, EMAIL_ID=?, LAST_USERID_CD=?, LAST_UPDT_TM=current_timestamp\n"
                    + " WHERE USER_ID=?";
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, vo.getTitle());
            stmt.setString(2, vo.getfName());
            stmt.setString(3, vo.getlName());
            stmt.setString(4, vo.getEmailId());
            stmt.setString(5, vo.getLastUserIdCd());

            stmt.setInt(6, vo.getUserId());
            
            stmt.executeUpdate();
            System.out.println("student profile updated..");
            status = true;
        } catch (SQLException e) {
            System.out.println("updateStudentDetail 1# " + e);
            throw new LmsDaoException(e.getMessage());
        } catch (Exception e) {
            System.out.println("updateStudentDetail 2# " + e);
            throw new LmsDaoException(e.getMessage());
        } finally {
            closeResources(conn, stmt, null);
        }

        return status;
    }

    
    @Override
    public boolean updateProfilePhoto(String photoPath) throws LmsDaoException {
        System.out.println("updateProfilePhoto id =" + photoPath);
        boolean status = false;

        //Database connection start
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = getConnection();
            String sql = "UPDATE student_dtls SET PROFILE_IMG=? WHERE USER_ID=(SELECT USER_ID FROM user_login where USER_NM = ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, photoPath);
            stmt.setString(2, photoPath.substring(0, photoPath.lastIndexOf(".")));
            
            stmt.executeUpdate();
            System.out.println("student profile updated..");
            status = true;
        } catch (SQLException e) {
            System.out.println("updateProfilePhoto 1# " + e);
            throw new LmsDaoException(e.getMessage());
        } catch (Exception e) {
            System.out.println("updateProfilePhoto 2# " + e);
            throw new LmsDaoException(e.getMessage());
        } finally {
            closeResources(conn, stmt, null);
        }

        return status;
    }
        
    

    public boolean saveStudentDetail(StudentDetailVo vo) throws LmsDaoException {
        boolean status = false;
        Connection conn = null;
        PreparedStatement stmt = null;
        try {

            conn = getConnection();
            String sql = "INSERT INTO student_dtls(USER_ID, FNAME, LNAME, EMAIL_ID, CONTACT_NO, BIRTH_DT, JOINING_DATE, PROFILE_IMG, SOCIAL_PROFILE, ADDRESS, LAST_USERID_CD,TITLE, LAST_UPDT_TM,ADMIN_EMAIL_ID) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?, current_timestamp,?)";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, vo.getUserId());
            stmt.setString(2, vo.getfName());
            stmt.setString(3, vo.getlName());
            stmt.setString(4, vo.getEmailId());
            stmt.setString(5, vo.getContactNo());
            stmt.setString(6, vo.getBirthDt());
            stmt.setString(7, vo.getJoiningDt());
            stmt.setString(8, vo.getProfile());
            stmt.setString(9, vo.getSocialProfile());
            stmt.setString(10, vo.getAddress());
            stmt.setString(11, vo.getLastUserIdCd());
            stmt.setString(12, vo.getTitle());
            stmt.setString(13, vo.getAdminEmailId());
            
            stmt.executeUpdate();
            status = true;

        } catch (SQLException se) {
            System.out.println("getUserLoginDetail # " + se);
            throw new LmsDaoException(se.getMessage());
        } catch (Exception e) {
            System.out.println("getUserLoginDetail # " + e);
            throw new LmsDaoException(e.getMessage());
        } finally {
            closeResources(conn, stmt, null);
        }

        System.out.println("Successfully saved....");
        return status;
    }

    //delete method
    public boolean deleteStudentDetail(StudentDetailVo vo) throws LmsDaoException {
        System.out.println("id =" + vo.getStudentDetailId());
        boolean status = true;

        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = getConnection();

            String sql = "DELETE FROM login_sessions WHERE SESSION_ID = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, vo.getStudentDetailId());
            stmt.executeUpdate();

            System.out.println("Deleted records into the table...");

        } catch (SQLException se) {
            System.out.println("getUserLoginDetail # " + se);
            throw new LmsDaoException(se.getMessage());
        } catch (Exception e) {
            System.out.println("getUserLoginDetail # " + e);
            throw new LmsDaoException(e.getMessage());
        } finally {
            closeResources(conn, stmt, null);
        }

        System.out.println("Successfully deleted....");
        return status;
    }

    @Override
    public List< StudentDetailVo> getStudentDetailVoList() throws LmsDaoException {
        List< StudentDetailVo> distList = new ArrayList<StudentDetailVo>();

        //1 . jdbc code start
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = getConnection();

            String sql = "SELECT * FROM student_dtls ";
            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {

                //3. Set db data to object
                StudentDetailVo userDtls = new StudentDetailVo();

                userDtls.setUserId(rs.getInt("STUDENT_DETAIL_ID"));
                userDtls.setUserId(rs.getInt("USER_id"));
                userDtls.setfName(rs.getString("FNAME"));
                userDtls.setlName(rs.getString("LNAME"));
                userDtls.setEmailId(rs.getString("EMAIL_id"));
                userDtls.setJoiningDt(rs.getString("JOINING_DT"));
                userDtls.setContactNo(rs.getString("CONTACT_NO"));
                userDtls.setBirthDt(rs.getString("BIRTH_DT"));
                userDtls.setProfile(rs.getString("PROFILE"));
                userDtls.setSocialProfile(rs.getString("SOCIAL_PROFILE"));
                userDtls.setAddress(rs.getString("ADDRESS"));

                userDtls.setLastUserIdCd(rs.getString("LAST_USERID_CD"));
                userDtls.setLastUpdtTm(rs.getString("LAST_UPDT_TM"));


                //Add into list
                distList.add(userDtls);
            }

            System.out.println("get records into the table...");

        } catch (SQLException se) {
            System.out.println("getUserLoginDetail # " + se);
            throw new LmsDaoException(se.getMessage());
        } catch (Exception e) {
            System.out.println("getUserLoginDetail # " + e);
            throw new LmsDaoException(e.getMessage());
        } finally {
            closeResources(conn, stmt, null);
        }

        return distList;

    }


}//End of class


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scolere.lms.persistance.dao.impl;

import com.scolere.lms.domain.exception.LmsDaoException;
import com.scolere.lms.domain.vo.UserClassMapVo;
import com.scolere.lms.persistance.dao.iface.UserClassMapDao;
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
public class UserClassMapDaoImpl extends LmsDaoAbstract implements UserClassMapDao {

    public UserClassMapVo getUserClassMapDetail(int id) throws LmsDaoException {
        System.out.println("Inside getUserClassDetail(?) >>");
        //Create object to return
        UserClassMapVo userDtls = new UserClassMapVo();

        //1 . jdbc code start
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = getConnection();

            String sql = "SELECT * FROM USER_COURSE_MAP where USER_ID=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, userDtls.getUserId());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                //3. Set db data to object

                userDtls.setUserId(rs.getInt("USER_ID"));
                userDtls.setSchoolId(rs.getInt("SCHOOL_ID"));
                userDtls.setClassId(rs.getInt("CLASS_ID"));
                userDtls.setHomeRoomMasterId(rs.getInt("HRM_ID"));
            }

            System.out.println("get records into the table...");

        } catch (SQLException se) {
            System.out.println("getUserClassMapDetail # " + se);
            se.printStackTrace();
        } catch (Exception e) {
            System.out.println("getUserClassMapDetail # " + e);
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, null);
        }
     //1 . jdbc code endd

        //4 Return as required by method
        return userDtls;
    }

    public boolean updateUserClassMapDetail(UserClassMapVo vo) throws LmsDaoException {
        System.out.println("id =" + vo.getUserId());
        boolean status = true;

        //Database connection start
        Connection conn = null;
        PreparedStatement stmt = null;
        try {

            conn = getConnection();
            String sql = "UPDATE user_cls_map set  SCHOOL_ID=?, CLASS_ID=?, HRM_ID=?\n" +
"    WHERE USER_ID=?";
            stmt = conn.prepareStatement(sql);
            
            stmt.setInt(1, vo.getSchoolId());
            stmt.setInt(2, vo.getClassId());
            stmt.setInt(3, vo.getHomeRoomMasterId());
            stmt.setInt(4, vo.getUserId());

            stmt.executeUpdate();
            System.out.println("updated records into the table...");

        } catch (SQLException e) {
            System.out.println("getUserClassMapDetail # " + e);
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("getUserClassMapDetail # " + e);
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, null);
        }

        System.out.println("Successfully updated....");
        return status;
        //End writting code to save into database   
    }
    //save method

    public void saveUserClassMapDetail(UserClassMapVo vo) throws LmsDaoException {
        //Database connection start
        Connection conn = null;
        PreparedStatement stmt = null;
        try {

            conn = getConnection();
            String sql = "INSERT INTO user_cls_map(USER_ID, SCHOOL_ID, CLASS_ID, HRM_ID) VALUES(?, ?,?,?)";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, vo.getUserId());
            stmt.setInt(2, vo.getSchoolId());
            stmt.setInt(3, vo.getClassId());
            stmt.setInt(4, vo.getHomeRoomMasterId());

            stmt.executeUpdate();
            System.out.println("Inserted records into the table...");

        } catch (SQLException se) {
            System.out.println("saveUserClassMapDetail # " + se);
            throw new LmsDaoException(se.getMessage());
        } catch (Exception e) {
            System.out.println("saveUserClassMapDetail # " + e);
            throw new LmsDaoException(e.getMessage());
        } finally {
            closeResources(conn, stmt, null);
        }

        System.out.println("Successfully saved....");
    }

    //delete method
    public boolean deleteUserClassMapDetail(UserClassMapVo vo) throws LmsDaoException {
        System.out.println("id =" + vo.getUserId());
        boolean status = true;

        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = getConnection();

            String sql = "DELETE FROM user_cls_map WHERE USER_ID = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, vo.getUserId());
            stmt.executeUpdate();

            System.out.println("Deleted records into the table...");

        } catch (SQLException se) {
            System.out.println("getUserClassMapDetail # " + se);
            se.printStackTrace();
        } catch (Exception e) {
            System.out.println("getUserClassMapDetail # " + e);
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, null);
        }

        System.out.println("Successfully deleted....");
        return status;
    }

    @Override
    public List< UserClassMapVo> getUserClassMapVoList() throws LmsDaoException {
        List<UserClassMapVo> distList = new ArrayList<UserClassMapVo>();

        //1 . jdbc code start
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = getConnection();

            String sql = "SELECT * FROM user_cls_map ";
            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {

                //3. Set db data to object
                UserClassMapVo userDtls = new UserClassMapVo();

                 userDtls.setUserId(rs.getInt("USER_ID"));
                userDtls.setSchoolId(rs.getInt("SCHOOL_ID"));
                userDtls.setClassId(rs.getInt("CLASS_ID"));
                userDtls.setHomeRoomMasterId(rs.getInt("HRM_ID"));
                //Add into list
                distList.add(userDtls);
            }

            System.out.println("get records into the table...");

        } catch (SQLException se) {
            System.out.println("getUserClassMapDetail # " + se);
            se.printStackTrace();
        } catch (Exception e) {
            System.out.println("getUserClassMapDetail # " + e);
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, null);
        }
     //1 . jdbc code endd

        //4 Return as required by method
        return distList;

    }


}

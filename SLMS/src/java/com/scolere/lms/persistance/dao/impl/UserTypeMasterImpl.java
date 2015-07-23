/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scolere.lms.persistance.dao.impl;

import com.scolere.lms.domain.exception.LmsDaoException;
import com.scolere.lms.domain.vo.UserTypeMasterVo;
import com.scolere.lms.persistance.dao.iface.UserTypeMasterDao;
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
public class UserTypeMasterImpl extends LmsDaoAbstract implements UserTypeMasterDao {

    public UserTypeMasterVo getUserTypeMasterDetail(int id) throws LmsDaoException {
        System.out.println("Inside getUserTypeMasterDetail(?) >>");
        //Create object to return
        UserTypeMasterVo userDtls = new UserTypeMasterVo();

        //1 . jdbc code start
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = getConnection();

            String sql = "SELECT * FROM USER_TYPE_MSTR where USER_TYPE_ID=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, userDtls.getUserTypeId());

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                //3. Set db data to object

                userDtls.setUserTypeId(rs.getInt("USER_TYPE_ID"));
                userDtls.setUserType(rs.getString("USER_TYPE"));

            }

            System.out.println("get records into the table...");

        } catch (SQLException se) {
            System.out.println("getUserTypeMasterDetail # " + se);
            se.printStackTrace();
        } catch (Exception e) {
            System.out.println("getUserTypeMasterDetail # " + e);
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, null);
        }
        //1 . jdbc code endd

        //4 Return as required by method
        return userDtls;
    }

    public boolean updateUserTypeMasterDetail(UserTypeMasterVo vo) throws LmsDaoException {
        System.out.println("id =" + vo.getUserTypeId());
        boolean status = true;

        //Database connection start
        Connection conn = null;
        PreparedStatement stmt = null;
        try {

            conn = getConnection();
            String sql = "UPDATE user_type_mstr set USER_TYPE=?\n"
                    + "    WHERE USER_TYPE_ID=?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, vo.getUserType());
            stmt.setInt(2, vo.getUserTypeId());

            stmt.executeUpdate();
            System.out.println("updated records into the table...");

        } catch (SQLException e) {
            System.out.println("getUserTypeMasterDetail # " + e);
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("getUserTypeMasterDetail # " + e);
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, null);
        }

        System.out.println("Successfully updated....");
        return status;
        //End writting code to save into database   
    }
    //save method

    public void saveUserTypeMasterDetail(UserTypeMasterVo vo) throws LmsDaoException {
        //Database connection start
        Connection conn = null;
        PreparedStatement stmt = null;
        try {

            conn = getConnection();
            String sql = "INSERT INTO user_type_mstr(USER_TYPE_ID, USER_TYPE)  VALUES(?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, vo.getUserTypeId());
            stmt.setString(2, vo.getUserType());

            //...
            stmt.executeUpdate();
            System.out.println("Inserted records into the table...");

        } catch (SQLException se) {
            System.out.println("getUserCourseMapDetail # " + se);
            se.printStackTrace();
        } catch (Exception e) {
            System.out.println("getUserCourseMapDetail # " + e);
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, null);
        }

        System.out.println("Successfully saved....");
    }

    //delete method
    public boolean deleteUserTypeMasterDetail(UserTypeMasterVo vo) throws LmsDaoException {
        System.out.println("id =" + vo.getUserTypeId());
        boolean status = true;

        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = getConnection();

            String sql = "DELETE FROM user_type_mstr WHERE USER_TYPE_ID = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, vo.getUserTypeId());
            stmt.executeUpdate();

            System.out.println("Deleted records into the table...");

        } catch (SQLException se) {
            System.out.println("getUserTypeMasterDetail # " + se);
            se.printStackTrace();
        } catch (Exception e) {
            System.out.println("getUserTypeMasterDetail # " + e);
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, null);
        }

        System.out.println("Successfully deleted....");
        return status;
    }

    @Override
    public List< UserTypeMasterVo> getUserTypeMasterVoList() throws LmsDaoException {
        List<UserTypeMasterVo> distList = new ArrayList<UserTypeMasterVo>();

        //1 . jdbc code start
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = getConnection();

            String sql = "SELECT * FROM user_type_mstr ";
            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {

                //3. Set db data to object
                UserTypeMasterVo userDtls = new UserTypeMasterVo();

                userDtls.setUserTypeId(rs.getInt("USER_TYPE_ID"));
                userDtls.setUserType(rs.getString("USER_TYPE"));

                //Add into list
                distList.add(userDtls);
            }

            System.out.println("get records into the table...");

        } catch (SQLException se) {
            System.out.println("getUserTypeMasterDetail # " + se);
            se.printStackTrace();
        } catch (Exception e) {
            System.out.println("getUserTypeMasterDetail # " + e);
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, null);
        }
        //1 . jdbc code endd

        //4 Return as required by method
        return distList;

    }
}

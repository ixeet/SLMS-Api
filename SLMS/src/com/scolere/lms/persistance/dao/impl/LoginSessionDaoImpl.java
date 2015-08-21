/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scolere.lms.persistance.dao.impl;

import com.scolere.lms.domain.exception.LmsDaoException;
import com.scolere.lms.domain.vo.LoginSessionVo;
import com.scolere.lms.persistance.dao.iface.LoginSessionDao;
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
public class LoginSessionDaoImpl extends LmsDaoAbstract implements LoginSessionDao {

    public LoginSessionVo getLoginSession(int id) throws LmsDaoException {
        System.out.println("Inside getUserLoginDetail(?) >>");
        //Create object to return
        LoginSessionVo userDtls = new LoginSessionVo();

        //1 . jdbc code start
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = getConnection();

            String sql = "SELECT * FROM login_sessions where LOGIN_SESSIONS_ID=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                //3. Set db data to object

                userDtls.setSessionId(rs.getString("LOGIN_SESSIONS_ID"));
                userDtls.setSessionId(rs.getString("SESSION_ID"));
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

        return userDtls;
    }

    public boolean updateLoginSession(LoginSessionVo vo) throws LmsDaoException {
        System.out.println("id =" + vo.getSessionId());
        boolean status = true;

        //Database connection start
        Connection conn = null;
        PreparedStatement stmt = null;
        try {

            conn = getConnection();
            String sql = "UPDATE login_sessions set SESSION_ID=?, LAST_USERID_CD=?, LAST_UPDT_TM=current_timestamp\n"
                    + "    WHERE LOGIN_SESSIONS_ID=?";
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, vo.getSessionId());

            stmt.setString(2, vo.getLastUserIdCd());
            stmt.setInt(3, vo.getLoginSession());
            stmt.executeUpdate();
            System.out.println("updated records into the table...");

        } catch (SQLException e) {
            System.out.println("getUserLoginDetail # " + e);
            throw new LmsDaoException(e.getMessage());
        } catch (Exception e) {
            System.out.println("getUserLoginDetail # " + e);
            throw new LmsDaoException(e.getMessage());
        } finally {
            closeResources(conn, stmt, null);
        }

        System.out.println("Successfully updated....");
        return status;
        //End writting code to save into database   
    }
    //save method

    public boolean saveLoginSession(LoginSessionVo vo) throws LmsDaoException {
        boolean status=false;
        
        Connection conn = null;
        PreparedStatement stmt = null;
        try {

            conn = getConnection();
            String sql = "INSERT INTO login_sessions(LOGIN_SESSIONS_ID, SESSION_ID, LAST_USERID_CD, LAST_UPDT_TM)  VALUES(?, ?, ?,  current_timestamp)";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, vo.getLoginSession());
            stmt.setString(2, vo.getSessionId());
            stmt.setString(3, vo.getLastUserIdCd());

            stmt.executeUpdate();
            status=true;
            System.out.println("Inserted records into the table...");

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
    public boolean deleteLoginSession(LoginSessionVo vo) throws LmsDaoException {
        System.out.println("id =" + vo.getSessionId());
        boolean status = true;

        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = getConnection();

            String sql = "DELETE FROM login_sessions WHERE SESSION_ID = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, vo.getSessionId());
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
    public List<LoginSessionVo> getLoginSessionList() throws LmsDaoException {
        List<LoginSessionVo> distList = new ArrayList<LoginSessionVo>();

        //1 . jdbc code start
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = getConnection();

            String sql = "SELECT * FROM login_sessions ";
            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {

                //3. Set db data to object
                LoginSessionVo userDtls = new  LoginSessionVo();

               userDtls.setSessionId(rs.getString("LOGIN_SESSIONS_ID"));
                userDtls.setSessionId(rs.getString("SESSION_ID"));
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



}//end of class

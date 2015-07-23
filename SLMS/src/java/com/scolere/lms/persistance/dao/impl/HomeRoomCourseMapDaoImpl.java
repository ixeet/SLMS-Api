/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scolere.lms.persistance.dao.impl;


import com.scolere.lms.domain.exception.LmsDaoException;
import com.scolere.lms.domain.vo.HomeRoomCourseMapVo;
import com.scolere.lms.persistance.dao.iface.HomeRoomCourseMapDao;
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
public class HomeRoomCourseMapDaoImpl extends LmsDaoAbstract implements HomeRoomCourseMapDao {

    public HomeRoomCourseMapVo getHomeRoomCourseMapDetail(int id) throws LmsDaoException {
        System.out.println("Inside getHomeRoomCourseMapDetail(?) >>");
        //Create object to return
        HomeRoomCourseMapVo userDtls = new HomeRoomCourseMapVo();

        //1 . jdbc code start
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = getConnection();

            String sql = "SELECT * FROM hrm_course_map where HRM_ID=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, userDtls.getCourseId());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                //3. Set db data to object

                userDtls.setCourseId(rs.getInt("COURSE_ID"));
                userDtls.setHomeroomId(rs.getInt("HRM_ID"));
            }

            System.out.println("get records into the table...");

        } catch (SQLException se) {
            System.out.println("getHomeRoomCourseMapDetail # " + se);
            se.printStackTrace();
        } catch (Exception e) {
            System.out.println("getHomeRoomCourseMapDetail # " + e);
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, null);
        }
     //1 . jdbc code endd

        //4 Return as required by method
        return userDtls;
    }

    public boolean updateHomeRoomCourseMapDetail(HomeRoomCourseMapVo vo) throws LmsDaoException {
        System.out.println("id =" + vo.getHomeroomId());
        boolean status = true;

        //Database connection start
        Connection conn = null;
        PreparedStatement stmt = null;
        try {

            conn = getConnection();
            String sql = "UPDATE hrm_course_map set COURSE_ID=?\n"
                    + "    WHERE HRM_ID=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, vo.getCourseId());
            stmt.setInt(2, vo.getHomeroomId());

            stmt.executeUpdate();
            System.out.println("updated records into the table...");

        } catch (SQLException e) {
            System.out.println("getHomeRoomCourseMapDetail # " + e);
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("getHomeRoomCourseMapDetail # " + e);
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, null);
        }

        System.out.println("Successfully updated....");
        return status;
        //End writting code to save into database   
    }
    //save method

    public void saveHomeRoomCourseMapDetail(HomeRoomCourseMapVo vo) throws LmsDaoException {
        //Database connection start
        Connection conn = null;
        PreparedStatement stmt = null;
        try {

            conn = getConnection();
            String sql = "INSERT INTO hrm_course_map(COURSE_ID, HRM_ID)  VALUES(?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, vo.getCourseId());
            stmt.setInt(2, vo.getHomeroomId());

            //...
            stmt.executeUpdate();
            System.out.println("Inserted records into the table...");

        } catch (SQLException se) {
            System.out.println("getHomeRoomCourseMapDetail # " + se);
            se.printStackTrace();
        } catch (Exception e) {
            System.out.println("getHomeRoomCourseMapDetail # " + e);
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, null);
        }

        System.out.println("Successfully saved....");
    }

    //delete method
    public boolean deleteHomeRoomCourseMapDetail(HomeRoomCourseMapVo vo) throws LmsDaoException {
        System.out.println("id =" + vo.getCourseId());
        boolean status = true;

        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = getConnection();

            String sql = "DELETE FROM user_course_map WHERE COURSE_ID = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, vo.getCourseId());
            stmt.executeUpdate();

            System.out.println("Deleted records into the table...");

        } catch (SQLException se) {
            System.out.println("getHomeRoomCourseMapDetail # " + se);
            se.printStackTrace();
        } catch (Exception e) {
            System.out.println("getHomeRoomCourseMapDetail # " + e);
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, null);
        }

        System.out.println("Successfully deleted....");
        return status;
    }

    public List< HomeRoomCourseMapVo> getHomeRoomCourseMapVoList() throws LmsDaoException {
        List<HomeRoomCourseMapVo> distList = new ArrayList<HomeRoomCourseMapVo>();

        //1 . jdbc code start
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = getConnection();

            String sql = "SELECT * FROM user_course_map ";
            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {

                //3. Set db data to object
                HomeRoomCourseMapVo userDtls = new HomeRoomCourseMapVo();

                userDtls.setCourseId(rs.getInt("COURSE_ID"));
                userDtls.setHomeroomId(rs.getInt("HRM_ID"));

                //Add into list
                distList.add(userDtls);
            }

            System.out.println("get records into the table...");

        } catch (SQLException se) {
            System.out.println("getUserCourseMapDetail # " + se);
            se.printStackTrace();
        } catch (Exception e) {
            System.out.println("getUserCourseMapDetail # " + e);
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, null);
        }
     //1 . jdbc code endd

        //4 Return as required by method
        return distList;

    }

    @Override
    public List<HomeRoomCourseMapVo> getHomeRoomCourseMapList() throws LmsDaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scolere.lms.persistance.dao.impl;

import com.scolere.lms.domain.exception.LmsDaoException;
import com.scolere.lms.domain.vo.CourseModuleMapVo;
import com.scolere.lms.persistance.dao.iface.CourseModuleDao;
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
public class CourseModuleMapDaoImpl extends LmsDaoAbstract implements CourseModuleDao {

    public CourseModuleMapVo getCourseModuleDetail(int id) throws LmsDaoException {
        System.out.println("Inside getCourseModuleMapDetail(?) >>");
        //Create object to return
        CourseModuleMapVo userDtls = new CourseModuleMapVo();

        //1 . jdbc code start
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = getConnection();

            String sql = "SELECT * FROM course_module_map where COURSE_ID=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, userDtls.getCourseId());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                //3. Set db data to object

                userDtls.setCourseId(rs.getInt("COURSE_ID"));
                userDtls.setModuleid(rs.getInt("MODULE_ID"));
            }

            System.out.println("get records into the table...");

        } catch (SQLException se) {
            System.out.println("getCourseModuleMapDetail # " + se);
            se.printStackTrace();
        } catch (Exception e) {
            System.out.println("getCourseModuleMapDetail # " + e);
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, null);
        }
        //1 . jdbc code endd

        //4 Return as required by method
        return userDtls;
    }

    public boolean updateCourseModuleMapDetail(CourseModuleMapVo vo) throws LmsDaoException {
        System.out.println("id =" + vo.getCourseId());
        boolean status = true;

        //Database connection start
        Connection conn = null;
        PreparedStatement stmt = null;
        try {

            conn = getConnection();
            String sql = "UPDATE user_course_map set COURSE_ID=?\n"
                    + "    WHERE USER_ID=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, vo.getCourseId());
            stmt.setInt(2, vo.getModuleid());

            stmt.executeUpdate();
            System.out.println("updated records into the table...");

        } catch (SQLException e) {
            System.out.println("getCourseModuleMapDetail # " + e);
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("getCourseModuleMapDetail # " + e);
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, null);
        }

        System.out.println("Successfully updated....");
        return status;
        //End writting code to save into database   
    }
    //save method

    public void saveCourseModuleDetail(CourseModuleMapVo vo) throws LmsDaoException {
        //Database connection start
        Connection conn = null;
        PreparedStatement stmt = null;
        try {

            conn = getConnection();
            String sql = "INSERT INTO course_module_map(COURSE_ID, MODULE_ID)  VALUES(?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, vo.getCourseId());
            stmt.setInt(2, vo.getModuleid());

            //...
            stmt.executeUpdate();
            System.out.println("Inserted records into the table...");

        } catch (SQLException se) {
            System.out.println("getCourseModuleMapDetail # " + se);
            se.printStackTrace();
        } catch (Exception e) {
            System.out.println("getCourseModuleMapDetail # " + e);
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, null);
        }

        System.out.println("Successfully saved....");
    }

    //delete method
    public boolean deleteCourseModuleMapDetail(CourseModuleMapVo vo) throws LmsDaoException {
        System.out.println("id =" + vo.getCourseId());
        boolean status = true;

        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = getConnection();

            String sql = "DELETE FROM course_module_map WHERE COURSE_ID = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, vo.getCourseId());
            stmt.executeUpdate();

            System.out.println("Deleted records into the table...");

        } catch (SQLException se) {
            System.out.println("getCourseModuleMapDetail # " + se);
            se.printStackTrace();
        } catch (Exception e) {
            System.out.println("getCourseModuleMapDetail # " + e);
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, null);
        }

        System.out.println("Successfully deleted....");
        return status;
    }

    public List< CourseModuleMapVo> getCourseModuleMapList() throws LmsDaoException {
        List<CourseModuleMapVo> distList = new ArrayList<CourseModuleMapVo>();

        //1 . jdbc code start
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = getConnection();

            String sql = "SELECT * FROM Course_module_map ";
            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {

                //3. Set db data to object
                CourseModuleMapVo userDtls = new CourseModuleMapVo();
                userDtls.setCourseId(rs.getInt("COURSE_ID"));
                userDtls.setModuleid(rs.getInt("MODULE_ID"));//Add into list

                distList.add(userDtls);
            }

            System.out.println("get records into the table...");

        } catch (SQLException se) {
            System.out.println("getCourseModuleMapDetail # " + se);
            se.printStackTrace();
        } catch (Exception e) {
            System.out.println("getCourseModuleMapDetail # " + e);
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, null);
        }
        //1 . jdbc code endd

        //4 Return as required by method
        return distList;

    }
}

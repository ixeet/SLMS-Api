/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scolere.lms.persistance.dao.impl;

import com.scolere.lms.domain.exception.LmsDaoException;
import com.scolere.lms.domain.vo.ClassHomeRoommapVo;
import com.scolere.lms.persistance.dao.iface.ClassHomeRoomMapDao;
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
public class ClassHomeRoomMapDaoImpl extends LmsDaoAbstract implements ClassHomeRoomMapDao{
    
    public ClassHomeRoommapVo getClassHomeRoommapDetail(int id) throws LmsDaoException {
        System.out.println("Inside getUserCourseMapDetail(?) >>");
        //Create object to return
        ClassHomeRoommapVo userDtls = new ClassHomeRoommapVo();

        //1 . jdbc code start
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = getConnection();

            String sql = "SELECT * FROM class_hrm_map where CLASS_ID=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, userDtls.getClassId());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                //3. Set db data to object

                userDtls.setClassId(rs.getInt("CLASS_ID"));
                userDtls.setHomeRoomId(rs.getInt("HOMEROOM_ID"));

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
        return userDtls;
    }

    public boolean updateClassHomeRoommapDetail(ClassHomeRoommapVo vo) throws LmsDaoException {
        System.out.println("id =" + vo.getClassId());
        boolean status = true;

        //Database connection start
        Connection conn = null;
        PreparedStatement stmt = null;
        try {

            conn = getConnection();
            String sql = "UPDATE class_hrm_map set CLASS_ID=?\n"
                    + "    WHERE HOMEROOM_ID=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, vo.getClassId());
            stmt.setInt(2, vo.getHomeRoomId());
    
            stmt.executeUpdate();
            System.out.println("updated records into the table...");

        } catch (SQLException e) {
            System.out.println("getUserCourseMapDetail # " + e);
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("getUserCourseMapDetail # " + e);
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, null);
        }

        System.out.println("Successfully updated....");
        return status;
        //End writting code to save into database   
    }
    //save method

    public void saveClassHomeRoommapDetail(ClassHomeRoommapVo vo)  throws LmsDaoException{
        //Database connection start
        Connection conn = null;
        PreparedStatement stmt = null;
        try {

            conn = getConnection();
            String sql = "INSERT INTO class_hrm_map(CLASS_ID, HRM_ID)  VALUES(?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, vo.getClassId());
            stmt.setInt(2, vo.getHomeRoomId());

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
    public boolean deleteClassHomeRoommapDetail(ClassHomeRoommapVo vo) throws LmsDaoException {
        System.out.println("id =" + vo.getClassId());
        boolean status = true;

        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = getConnection();

            String sql = "DELETE FROM class_hrm_map WHERE CLASS_ID = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, vo.getClassId());
            stmt.executeUpdate();

            System.out.println("Deleted records into the table...");

        } catch (SQLException se) {
            System.out.println("getUserCourseMapDetail # " + se);
            se.printStackTrace();
        } catch (Exception e) {
            System.out.println("getUserCourseMapDetail # " + e);
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, null);
        }

        System.out.println("Successfully deleted....");
        return status;
    }

    
    public List< ClassHomeRoommapVo> getClassHomeRoommapList()  throws LmsDaoException{
        List<ClassHomeRoommapVo> distList = new ArrayList<ClassHomeRoommapVo>();

        //1 . jdbc code start
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = getConnection();

            String sql = "SELECT * FROM class_hrm_map ";
            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {

                //3. Set db data to object
               ClassHomeRoommapVo userDtls = new ClassHomeRoommapVo();

                userDtls.setClassId(rs.getInt("CLASS_ID"));
                userDtls.setHomeRoomId(rs.getInt("HRM_ID"));

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


}

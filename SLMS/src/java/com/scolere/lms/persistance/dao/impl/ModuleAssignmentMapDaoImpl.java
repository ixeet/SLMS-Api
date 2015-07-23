/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scolere.lms.persistance.dao.impl;

import com.scolere.lms.domain.exception.LmsDaoException;
import com.scolere.lms.persistance.dao.iface.ModuleAssignmentMapDao;
import com.scolere.lms.persistance.factory.LmsDaoAbstract;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import my.java.interfac.ModuleAssignmentMapVo;

/**
 *
 * @author admin
 */
public class ModuleAssignmentMapDaoImpl extends LmsDaoAbstract implements ModuleAssignmentMapDao {

    public ModuleAssignmentMapVo getModuleAssignmentMapDetail(int id) throws LmsDaoException {
        System.out.println("Inside getModuleAssignmentMapDetail(?) >>");
        //Create object to return
        ModuleAssignmentMapVo userDtls = new ModuleAssignmentMapVo();

        //1 . jdbc code start
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = getConnection();

            String sql = "SELECT * FROM MODULE_ASSIGNMENT_MAP where MODULE_ID=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, userDtls.getModuleId());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                //3. Set db data to object

                userDtls.setModuleId(rs.getInt("MODULE_ID"));
                userDtls.setAssignmentId(rs.getInt("ASSIGNMENT_ID"));

            }

            System.out.println("get records into the table...");

        } catch (SQLException se) {
            System.out.println("getModuleAssignmentMapDetail # " + se);
            se.printStackTrace();
        } catch (Exception e) {
            System.out.println("getModuleAssignmentMapDetail # " + e);
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, null);
        }
        //1 . jdbc code endd

        //4 Return as required by method
        return userDtls;
    }

    public boolean updateModuleAssignmentMapDetail(ModuleAssignmentMapVo vo) throws LmsDaoException {
        System.out.println("id =" + vo.getModuleId());
        boolean status = true;

        //Database connection start
        Connection conn = null;
        PreparedStatement stmt = null;
        try {

            conn = getConnection();
            String sql = "UPDATE module_assignment_map set MODULE_ID=?\n"
                    + "    WHERE ASSIGNMENT_ID=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, vo.getModuleId());
            stmt.setInt(2, vo.getAssignmentId());

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

    public void saveModuleAssignmentMapDetail(ModuleAssignmentMapVo vo) throws LmsDaoException {
        //Database connection start
        Connection conn = null;
        PreparedStatement stmt = null;
        try {

            conn = getConnection();
            String sql = "INSERT INTO module_assignment_map (MODULE_ID, ASSIGNMENT_ID)  VALUES(?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, vo.getModuleId());
            stmt.setInt(2, vo.getAssignmentId());

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
    public boolean deleteModuleAssignmentMapDetail(ModuleAssignmentMapVo vo) throws LmsDaoException {
        System.out.println("id =" + vo.getModuleId());
        boolean status = true;

        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = getConnection();

            String sql = "DELETE FROM MODULE_ASSIGNMENT_MAP WHERE MODULE_ID = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, vo.getModuleId());
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

    public List< ModuleAssignmentMapVo> getModuleAssignmentMapVoList() throws LmsDaoException {
        List<ModuleAssignmentMapVo> distList = new ArrayList<ModuleAssignmentMapVo>();

        //1 . jdbc code start
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = getConnection();

            String sql = "SELECT * FROM MODULE_ASSIGNMENT_MAP ";
            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {

                //3. Set db data to object
                ModuleAssignmentMapVo userDtls = new ModuleAssignmentMapVo();

                userDtls.setModuleId(rs.getInt("MODULE_ID"));
                userDtls.setAssignmentId(rs.getInt("ASSIGNMENT_ID"));

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

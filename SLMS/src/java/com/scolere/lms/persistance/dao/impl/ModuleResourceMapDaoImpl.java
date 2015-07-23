/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scolere.lms.persistance.dao.impl;

import com.scolere.lms.domain.exception.LmsDaoException;
import com.scolere.lms.domain.vo.ModuleResourceMapVo;
import com.scolere.lms.persistance.dao.iface.ModuleResourcemapDao;
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
public class ModuleResourceMapDaoImpl extends LmsDaoAbstract implements ModuleResourcemapDao {

    public ModuleResourceMapVo getModuleResourceMapDetail(int id) throws LmsDaoException {
        System.out.println("Inside getUserCourseMapDetail(?) >>");
        //Create object to return
        ModuleResourceMapVo userDtls = new ModuleResourceMapVo();

        //1 . jdbc code start
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = getConnection();

            String sql = "SELECT * FROM MODULE_RESOURCE_MAP where MODULE_ID=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, userDtls.getModuleId());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                //3. Set db data to object

                userDtls.setModuleId(rs.getInt("MODULE_ID"));
                userDtls.setResourceId(rs.getInt("RESOURCE_ID"));

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

    public boolean updateModuleResourceMapDetail(ModuleResourceMapVo vo) throws LmsDaoException {
        System.out.println("id =" + vo.getModuleId());
        boolean status = true;

        //Database connection start
        Connection conn = null;
        PreparedStatement stmt = null;
        try {

            conn = getConnection();
            String sql = "UPDATE module_resource_map set MODULE_ID=?\n"
                    + "    WHERE RESOURCE_ID=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, vo.getModuleId());
            stmt.setInt(2, vo.getResourceId());

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

    public void saveModuleResourceMapDetail(ModuleResourceMapVo vo) throws LmsDaoException {
        //Database connection start
        Connection conn = null;
        PreparedStatement stmt = null;
        try {

            conn = getConnection();
            String sql = "INSERT INTO module_resource_map(MODULE_ID, RESOURCE_ID)  VALUES(?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, vo.getModuleId());
            stmt.setInt(2, vo.getResourceId());

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
    public boolean deleteModuleResourceMapDetail(ModuleResourceMapVo vo) throws LmsDaoException {
        System.out.println("id =" + vo.getModuleId());
        boolean status = true;

        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = getConnection();

            String sql = "DELETE FROM user_course_map WHERE USER_ID = ?";
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

    public List< ModuleResourceMapVo> getModuleResourceMapVoList() throws LmsDaoException {
        List<ModuleResourceMapVo> distList = new ArrayList<ModuleResourceMapVo>();

        //1 . jdbc code start
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = getConnection();

            String sql = "SELECT * FROM module_resource_map ";
            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {

                //3. Set db data to object
                ModuleResourceMapVo userDtls = new ModuleResourceMapVo();

                userDtls.setModuleId(rs.getInt("MODULE_ID"));
                userDtls.setResourceId(rs.getInt("RESOURCE_ID"));

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
    public boolean updateModuleResourcemapDetail(ModuleResourceMapVo vo) throws LmsDaoException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<ModuleResourceMapVo> getModuleResourceMapList() throws LmsDaoException {
        throw new UnsupportedOperationException("Not supported yet.");
    }


}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scolere.lms.persistance.dao.impl;

import com.scolere.lms.domain.exception.LmsDaoException;
import com.scolere.lms.domain.vo.SchoolClassMapVo;
import com.scolere.lms.persistance.dao.iface.SchoolClassMapDao;
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
public class SchoolClassMapDaoImpl extends LmsDaoAbstract implements SchoolClassMapDao {

    public SchoolClassMapVo getSchoolClassMapDetail(int id) throws LmsDaoException {
        System.out.println("Inside getSchoolClassMapDetail(?) >>");
        //Create object to return
        SchoolClassMapVo userDtls = new SchoolClassMapVo();

        //1 . jdbc code start
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = getConnection();

            String sql = "SELECT * FROM school_cls_map where SCHOOL_ID=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, userDtls.getSchoolId());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                //3. Set db data to object

                userDtls.setSchoolId(rs.getInt("SCHOOL_ID"));
                userDtls.setClassId(rs.getInt("CLASS_ID"));

            }

            System.out.println("get records into the table...");

        } catch (SQLException se) {
            System.out.println("getSchoolClassMapDetail # " + se);
            se.printStackTrace();
        } catch (Exception e) {
            System.out.println("getSchoolClassMapDetail # " + e);
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, null);
        }
        //1 . jdbc code endd

        //4 Return as required by method
        return userDtls;
    }

    public boolean updateSchoolClassMapDetail(SchoolClassMapVo vo) throws LmsDaoException {
        System.out.println("id =" + vo.getSchoolId());
        boolean status = true;

        //Database connection start
        Connection conn = null;
        PreparedStatement stmt = null;
        try {

            conn = getConnection();
            String sql = "UPDATE school_cls_map set CLASS_ID=?\n"
                    + "    WHERE SCHOOL_ID=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, vo.getClassId());
            stmt.setInt(2, vo.getSchoolId());

            stmt.executeUpdate();
            System.out.println("updated records into the table...");

        } catch (SQLException e) {
            System.out.println("getSchoolClassMapDetail # " + e);
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("getSchoolClassMapDetail # " + e);
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, null);
        }

        System.out.println("Successfully updated....");
        return status;
        //End writting code to save into database   
    }
    //save method

    public void saveSchoolClassMapDetail(SchoolClassMapVo vo) throws LmsDaoException {
        //Database connection start
        Connection conn = null;
        PreparedStatement stmt = null;
        try {

            conn = getConnection();
            String sql = "INSERT INTO school_cls_map(SCHOOL_ID, CLASS_ID)  VALUES(?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, vo.getSchoolId());
            stmt.setInt(2, vo.getClassId());

            //...
            stmt.executeUpdate();
            System.out.println("Inserted records into the table...");

        } catch (SQLException se) {
            System.out.println("getSchoolClassMapDetail # " + se);
            se.printStackTrace();
        } catch (Exception e) {
            System.out.println("getSchoolClassMapDetail # " + e);
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, null);
        }

        System.out.println("Successfully saved....");
    }

    //delete method
    public boolean deleteSchoolClassMapDetail(SchoolClassMapVo vo) throws LmsDaoException {
        System.out.println("id =" + vo.getSchoolId());
        boolean status = true;

        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = getConnection();

            String sql = "DELETE FROM school_cls_map WHERE SCHOOL_ID = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, vo.getSchoolId());
            stmt.executeUpdate();

            System.out.println("Deleted records into the table...");

        } catch (SQLException se) {
            System.out.println("getSchoolClassMapDetail # " + se);
            se.printStackTrace();
        } catch (Exception e) {
            System.out.println("getSchoolClassMapDetail # " + e);
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, null);
        }

        System.out.println("Successfully deleted....");
        return status;
    }

    public List< SchoolClassMapVo> getSchoolClassMapVoList() throws LmsDaoException {
        List<SchoolClassMapVo> distList = new ArrayList<SchoolClassMapVo>();

        //1 . jdbc code start
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = getConnection();

            String sql = "SELECT * FROM school_cls_map ";
            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {

                //3. Set db data to object
                SchoolClassMapVo userDtls = new SchoolClassMapVo();
                userDtls.setSchoolId(rs.getInt("SCHOOL_ID"));
                userDtls.setClassId(rs.getInt("CLASS_ID"));

                //Add into list
                distList.add(userDtls);
            }

            System.out.println("get records into the table...");

        } catch (SQLException se) {
            System.out.println("getSchoolClassMapDetail # " + se);
            se.printStackTrace();
        } catch (Exception e) {
            System.out.println("getSchoolClassMapDetail # " + e);
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, null);
        }
        //1 . jdbc code endd

        //4 Return as required by method
        return distList;

    }

    @Override
    public List<SchoolClassMapVo> getSchoolClassMapList() throws LmsDaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

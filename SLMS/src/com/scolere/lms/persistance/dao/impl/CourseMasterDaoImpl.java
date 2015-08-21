/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scolere.lms.persistance.dao.impl;

import com.scolere.lms.domain.exception.LmsDaoException;
import com.scolere.lms.domain.vo.CourseMasterVo;
import com.scolere.lms.persistance.dao.iface.CourseMasterDao;
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
public class CourseMasterDaoImpl extends LmsDaoAbstract implements CourseMasterDao {

    public CourseMasterVo getCourseMasterDetail(int id) throws LmsDaoException {
        System.out.println("Inside getCourseMasterDetail(?) >>");
        //Create object to return
        CourseMasterVo userDtls = new CourseMasterVo();

        //1 . jdbc code start
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = getConnection();

            String sql = "SELECT * FROM course_mstr where COURSE_ID=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, userDtls.getCourseId());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                //3. Set db data to object
                userDtls.setCourseId(rs.getInt("COURSE_ID"));
                userDtls.setCourseName(rs.getString("COURSE_NAME"));
                userDtls.setCourseAuthor(rs.getString("COURSE_AUTHOR"));
                userDtls.setCourseDuration(rs.getString("COURSE_DURATION"));
                userDtls.setCreatedDt(rs.getString("CREATED_DT"));
                userDtls.setDescTxt(rs.getString("DESC_TXT"));
                userDtls.setMetadata(rs.getString("METEDATA"));
                userDtls.setDeletedFl(rs.getString("DELETED_FL"));
                userDtls.setDisplayNo(rs.getInt("DISPLAY_NO"));
                userDtls.setEnableFl(rs.getString("ENABLE_FL"));
                userDtls.setCreatedBy(rs.getString("CREATED_BY"));

                userDtls.setLastUserIdCd(rs.getString("LAST_USERID_CD"));
                userDtls.setLastUpdtTm(rs.getString("LAST_UPDT"));

            }

            System.out.println("get records into the table...");

        } catch (SQLException se) {
            System.out.println("getClassDetail # " + se);
            throw new LmsDaoException(se.getMessage());
        } catch (Exception e) {
            System.out.println("getClassDetail # " + e);
            throw new LmsDaoException(e.getMessage());
        } finally {
            closeResources(conn, stmt, null);
        }
        //1 . jdbc code endd

        //4 Return as required by method
        return userDtls;
    }

    public boolean updateCourseMasterDetail(CourseMasterVo vo) throws LmsDaoException {
        System.out.println("id =" + vo.getCourseId());
        boolean status = true;

        //Database connection start
        Connection conn = null;
        PreparedStatement stmt = null;
        try {

            conn = getConnection();
            String sql = "UPDATE course_mstr set  COURSE_NAME=?, COURSE_AUTHOR=?, COURSE_DURATION=?, CREATED_DT=?, DESC_TXT=?, METADATA=?, DELETED_FL=?, DISPLAY_NO=?, ENABLE_FL=?, CREATED_BY=?, LAST_USERID_CD=?, LAST_UPDT=current_timestamp\n"
                    + "    WHERE COURSE_ID=?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, vo.getCourseName());
            stmt.setString(2, vo.getCourseAuthor());
            stmt.setString(3, vo.getCourseDuration());
            stmt.setString(4, vo.getCreatedDt());
            stmt.setString(5, vo.getDescTxt());
            stmt.setString(6, vo.getMetadata());
            stmt.setString(7, vo.getDeletedFl());
            stmt.setInt(8, vo.getDisplayNo());
            stmt.setString(9, vo.getEnableFl());
            stmt.setString(10, vo.getCreatedBy());
            stmt.setString(11, vo.getLastUserIdCd());
            stmt.setInt(12, vo.getCourseId());

            stmt.executeUpdate();
            System.out.println("updated records into the table...");

        } catch (SQLException e) {
            System.out.println("getCourseDetail # " + e);
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("getCourseDetail # " + e);
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, null);
        }

        System.out.println("Successfully updated....");
        return status;
        //End writting code to save into database   
    }
    //save method

    public void saveCourseMasterDetail(CourseMasterVo vo) throws LmsDaoException {
        //Database connection start
        Connection conn = null;
        PreparedStatement stmt = null;
        try {

            conn = getConnection();
            String sql = "INSERT INTO course_mstr(COURSE_ID, COURSE_NAME, COURSE_AUTHOR, COURSE_DURATION, CREATED_DT, DESC_TXT, METADATA, DELETED_FL, DISPLAY_NO, ENABLE_FL, CREATED_BY, LAST_USERID_CD, LAST_UPDT)    VALUES(?, ?, ?, ?, ?, ?, ?, ?, ? ,?, ?, ? ,current_timestamp)";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, vo.getCourseId());
            stmt.setString(2, vo.getCourseName());
            stmt.setString(3, vo.getCourseAuthor());
            stmt.setString(4, vo.getCourseDuration());
            stmt.setString(5, vo.getCreatedDt());
            stmt.setString(6, vo.getDescTxt());
            stmt.setString(7, vo.getMetadata());
            stmt.setString(8, vo.getDeletedFl());
            stmt.setInt(9, vo.getDisplayNo());
            stmt.setString(10, vo.getEnableFl());
            stmt.setString(11, vo.getCreatedBy());
            stmt.setString(12, vo.getLastUserIdCd());

            //...
            stmt.executeUpdate();
            System.out.println("Inserted records into the table...");

        } catch (SQLException se) {
            System.out.println("getCourseDetail # " + se);
            se.printStackTrace();
        } catch (Exception e) {
            System.out.println("getCourseDetail # " + e);
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, null);
        }

        System.out.println("Successfully saved....");
    }

    //delete method
    public boolean deleteCourseMasterDetail(CourseMasterVo vo) throws LmsDaoException {
        System.out.println("id =" + vo.getCourseId());
        boolean status = true;

        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = getConnection();

            String sql = "DELETE FROM course_mstr WHERE COURSE_ID = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, vo.getCourseId());
            stmt.executeUpdate();

            System.out.println("Deleted records into the table...");

        } catch (SQLException se) {
            System.out.println("getCourseDetail # " + se);
            se.printStackTrace();
        } catch (Exception e) {
            System.out.println("getCourseDetail # " + e);
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, null);
        }

        System.out.println("Successfully deleted....");
        return status;
    }

    @Override
    public List< CourseMasterVo> getCourseMasterVoList() throws LmsDaoException {
        List< CourseMasterVo> distList = new ArrayList<CourseMasterVo>();

        //1 . jdbc code start
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = getConnection();

            String sql = "SELECT * FROM course_mstr ";
            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {

                //3. Set db data to object
                CourseMasterVo userDtls = new CourseMasterVo();
                userDtls.setCourseId(rs.getInt("COURSE_ID"));
                userDtls.setCourseName(rs.getString("COURSE_NAME"));
                userDtls.setCourseAuthor(rs.getString("COURSE_AUTHOR"));
                userDtls.setCourseDuration(rs.getString("COURSE_DURATION"));
                userDtls.setCreatedDt(rs.getString("CREATED_DT"));
                userDtls.setDescTxt(rs.getString("DESC_TXT"));
                userDtls.setMetadata(rs.getString("METADATA"));
                userDtls.setDeletedFl(rs.getString("DELETED_FL"));
                userDtls.setDisplayNo(rs.getInt("DISPLAY_NO"));
                userDtls.setEnableFl(rs.getString("ENABLE_FL"));
                userDtls.setCreatedBy(rs.getString("CREATED_BY"));

                userDtls.setLastUserIdCd(rs.getString("LAST_USERID_CD"));
                userDtls.setLastUpdtTm(rs.getString("LAST_UPDT"));

                //Add into list
                distList.add(userDtls);
            }

            System.out.println("get records into the table...");

        } catch (SQLException se) {
            System.out.println("getCourseDetail # " + se);
            se.printStackTrace();
        } catch (Exception e) {
            System.out.println("getCourseDetail # " + e);
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, null);
        }
        //1 . jdbc code endd

        //4 Return as required by method
        return distList;

    }
}

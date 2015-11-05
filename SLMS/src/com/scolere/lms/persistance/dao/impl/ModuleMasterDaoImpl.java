/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scolere.lms.persistance.dao.impl;

import com.scolere.lms.domain.exception.LmsDaoException;
import com.scolere.lms.domain.vo.ModuleMasterVo;
import com.scolere.lms.persistance.dao.iface.ModuleMasterDao;
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
public class ModuleMasterDaoImpl extends LmsDaoAbstract implements ModuleMasterDao {

    public ModuleMasterVo getModuleMasterDetail(int id) throws LmsDaoException {
        System.out.println("Inside getModuleMasterDetail(?) >>");
        //Create object to return
        ModuleMasterVo userDtls = new ModuleMasterVo();

        //1 . jdbc code start
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = getConnection();

            String sql = "SELECT * FROM module_mstr where MODULE_ID=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, userDtls.getModuleMasterId());

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                //3. Set db data to object

                userDtls.setModuleMasterId(rs.getInt("MODULE_ID"));
                userDtls.setModuleMasterName(rs.getString("MODULE_NAME"));
                userDtls.setDescTxt(rs.getString("DESC_TXT"));

                userDtls.setMetadata(rs.getString("METADATA"));
                userDtls.setDeletedFl(rs.getString("DELETED_FL"));
                userDtls.setDisplayNo(rs.getInt("DISPLAY_NO"));
                userDtls.setEnableFl(rs.getString("ENABLE_FL"));
                userDtls.setCreatedBy(rs.getString("CREATED_BY"));

                userDtls.setLastuserIdCd(rs.getString("LAST_USERID_CD"));
                userDtls.setLastUpdtTm(rs.getString("LAST_UPDT_TM"));

            }

            System.out.println("get records into the table...");

        } catch (SQLException se) {
            System.out.println("getModuleMasterDetail # " + se);
            se.printStackTrace();
        } catch (Exception e) {
            System.out.println("getModuleMasterDetail # " + e);
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, null);
        }
        //1 . jdbc code endd

        //4 Return as required by method
        return userDtls;
    }

    public boolean updateModuleMasterDetail(ModuleMasterVo vo) throws LmsDaoException {
        System.out.println("id =" + vo.getModuleMasterId());
        boolean status = true;

        //Database connection start
        Connection conn = null;
        PreparedStatement stmt = null;
        try {

            conn = getConnection();
            String sql = "UPDATE module_mstr set MODULE_NAME=?, DESC_TXT=?, METADATA=?, DELETED_FL=?, DISPLAY_NO=?, ENABLE_FL=?, CREATED_BY=?, LAST_USERID_CD=?, LAST_UPDT_TM=utc_timestamp\n"
                    + "    WHERE MODULE_ID=?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, vo.getModuleMasterName());
            stmt.setString(2, vo.getDescTxt());
            stmt.setString(3, vo.getMetadata());
            stmt.setString(4, vo.getDeletedFl());
            stmt.setInt(5, vo.getDisplayNo());
            stmt.setString(6, vo.getEnableFl());
            stmt.setString(7, vo.getCreatedBy());
            stmt.setString(8, vo.getLastuserIdCd());
            stmt.setInt(9, vo.getModuleMasterId());

            stmt.executeUpdate();
            System.out.println("updated records into the table...");

        } catch (SQLException e) {
            System.out.println("getModuleMasterDetail # " + e);
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("getModuleMasterDetail # " + e);
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, null);
        }

        System.out.println("Successfully updated....");
        return status;
        //End writting code to save into database   
    }
    //save method

    public void saveModuleMasterDetail(ModuleMasterVo vo) throws LmsDaoException {
        //Database connection start
        Connection conn = null;
        PreparedStatement stmt = null;
        try {

            conn = getConnection();
            String sql = "INSERT INTO module_mstr(MODULE_ID, MODULE_NAME, DESC_TXT, METADATA, DELETED_FL, DISPLAY_NO, ENABLE_FL, CREATED_BY, LAST_USERID_CD, LAST_UPDT_TM)   VALUES(?, ?, ?, ?, ?, ?, ?, ?, ? , utc_timestamp)";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, vo.getModuleMasterId());
            stmt.setString(2, vo.getModuleMasterName());
            stmt.setString(3, vo.getDescTxt());
            stmt.setString(4, vo.getMetadata());
            stmt.setString(5, vo.getDeletedFl());
            stmt.setInt(6, vo.getDisplayNo());
            stmt.setString(7, vo.getEnableFl());
            stmt.setString(8, vo.getCreatedBy());
            stmt.setString(9, vo.getLastuserIdCd());
            //...
            stmt.executeUpdate();
            System.out.println("Inserted records into the table...");

        } catch (SQLException se) {
            System.out.println("getModuleMasterDetail # " + se);
            se.printStackTrace();
        } catch (Exception e) {
            System.out.println("getModuleMasterDetail # " + e);
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, null);
        }

        System.out.println("Successfully saved....");
    }

    //delete method
    public boolean deleteModuleMasterDetail(ModuleMasterVo vo) throws LmsDaoException {
        System.out.println("id =" + vo.getModuleMasterId());
        boolean status = true;

        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = getConnection();

            String sql = "DELETE FROM module_mstr WHERE MODULE_ID = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, vo.getModuleMasterId());
            stmt.executeUpdate();

            System.out.println("Deleted records into the table...");

        } catch (SQLException se) {
            System.out.println("getModuleMasterDetail # " + se);
            se.printStackTrace();
        } catch (Exception e) {
            System.out.println("getModuleMasterDetail # " + e);
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, null);
        }

        System.out.println("Successfully deleted....");
        return status;
    }

    @Override
    public List<ModuleMasterVo> getModuleMasterVoList() throws LmsDaoException {
        List< ModuleMasterVo> distList = new ArrayList<ModuleMasterVo>();

        //1 . jdbc code start
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = getConnection();

            String sql = "SELECT * FROM module_mstr ";
            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {

                //3. Set db data to object
                ModuleMasterVo userDtls = new ModuleMasterVo();
                userDtls.setModuleMasterId(rs.getInt("MODULE_ID"));
                userDtls.setModuleMasterName(rs.getString("MODULE_NAME"));
                userDtls.setDescTxt(rs.getString("DESC_TXT"));

                userDtls.setMetadata(rs.getString("METADATA"));
                userDtls.setDeletedFl(rs.getString("DELETED_FL"));
                userDtls.setDisplayNo(rs.getInt("DISPLAY_NO"));
                userDtls.setEnableFl(rs.getString("ENABLE_FL"));
                userDtls.setCreatedBy(rs.getString("CREATED_BY"));

                userDtls.setLastuserIdCd(rs.getString("LAST_USERID_CD"));
                userDtls.setLastUpdtTm(rs.getString("LAST_UPDT_TM"));

                //Add into list
                distList.add(userDtls);
            }

            System.out.println("get records into the table...");

        } catch (SQLException se) {
            System.out.println("getModuleMasterDetail # " + se);
            se.printStackTrace();
        } catch (Exception e) {
            System.out.println("getModuleMasterDetail # " + e);
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, null);
        }
        //1 . jdbc code endd

        //4 Return as required by method
        return distList;

    }

	@Override
	public List<ModuleMasterVo> getModuleList(int courseId, int homeRoomMstrId,
			int classId, int schoolId, int teacherId) throws LmsDaoException {
		 System.out.println("Inside getModuleList(?) >>");
	        //Create object to return
		 List< ModuleMasterVo> distList = new ArrayList<ModuleMasterVo>();

	        //1 . jdbc code start
	        Connection conn = null;
	        PreparedStatement stmt = null;
	        try {
	            conn = getConnection();

	            String sql = "SELECT mm.MODULE_ID, mm.MODULE_NAME,tcsd.IS_COMPLETED, tcsd.START_SESSION_TM, tcsd.END_SESSION_TM, " +
	            		"tcsd.LAST_UPDT_TM FROM module_mstr mm, teacher_course_sessions tcs, teacher_courses tc , student_dtls td ," +
	            		"teacher_course_session_dtls tcsd where mm.DELETED_FL='0' and tc.TEACHER_COURSE_ID = tcs.TEACHER_COURSE_ID and" +
	            		" tc.SCHOOL_ID = '"+schoolId+"' and tc.CLASS_ID = '"+classId+"' and tc.HRM_ID='"+homeRoomMstrId+"' and tc.TEACHER_ID=td.EMAIL_ID and td.USER_ID='"+teacherId+"'" +
	            				" and tc.COURSE_ID='"+courseId+"' and tcsd.COURSE_SESSION_ID=tcs.COURSE_SESSION_ID and mm.MODULE_ID=tcsd.MODULE_ID";
	           
	            
	            stmt = conn.prepareStatement(sql);
	          //  stmt.setInt(1, userDtls.getModuleMasterId());

	            ResultSet rs = stmt.executeQuery();
	            while (rs.next()) {
	                //3. Set db data to object
	            	 ModuleMasterVo userDtls = new ModuleMasterVo();
	                userDtls.setModuleMasterId(rs.getInt("MODULE_ID"));
	                userDtls.setModuleMasterName(rs.getString("MODULE_NAME"));
	                distList.add(userDtls);
	            }

	            System.out.println("get records into the table...");

	        } catch (SQLException se) {
	            System.out.println("getModuleMasterDetail # " + se);
	            se.printStackTrace();
	        } catch (Exception e) {
	            System.out.println("getModuleMasterDetail # " + e);
	            e.printStackTrace();
	        } finally {
	            closeResources(conn, stmt, null);
	        }
	        //1 . jdbc code endd

	        //4 Return as required by method
	        return distList;
	}

	 
}

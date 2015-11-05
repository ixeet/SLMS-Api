/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scolere.lms.persistance.dao.impl;

import com.scolere.lms.domain.vo.SchoolMasterVo;
import com.scolere.lms.persistance.dao.iface.SchoolMasterDao;
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
public class SchoolMasterDaoImpl extends LmsDaoAbstract implements SchoolMasterDao {

    public SchoolMasterVo getSchoolMasterDetail(int id) {
        System.out.println("Inside getSchoolMasterDetail(?) >>");
        //Create object to return
        SchoolMasterVo schoolMasterVo = new SchoolMasterVo();

        //1 . jdbc code start
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = getConnection();

            String sql = "SELECT * FROM school_mstr where DELETED_FL='0' and SCHOOL_ID=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, schoolMasterVo.getSchoolId());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                //3. Set db data to object
                schoolMasterVo.setSchoolId(rs.getInt("SCHOOL_ID"));
                schoolMasterVo.setSchoolName(rs.getString("SCHOOL_NAME"));
                schoolMasterVo.setSchoolAddress(rs.getString("SCHOOL_ADDRESS"));
                schoolMasterVo.setDescTxt(rs.getString("DESC_TXT"));
                schoolMasterVo.setWebsiteUrl(rs.getString("WEBSITE_URL"));
                schoolMasterVo.setSchoolLogo(rs.getString("SCHOOL_LOGO"));
                schoolMasterVo.setOtherImg(rs.getString("OTHER_IMG"));
                schoolMasterVo.setMetedata(rs.getString("OTHER_IMG"));
                schoolMasterVo.setDeleteFl(rs.getString("DELETED_FL"));
                schoolMasterVo.setDisplayNo(rs.getInt("DISPLAY_NO"));
                schoolMasterVo.setEnableFl(rs.getString("ENABLE_FL"));
                schoolMasterVo.setCreatedBy(rs.getString("CREATED_BY"));
                schoolMasterVo.setLastUserIdCd(rs.getString("LAST_USERID_CD"));
                schoolMasterVo.setLastUpdtTm(rs.getString("LAST_UPDT_TM"));

            }

            System.out.println("get records into the table...");

        } catch (SQLException se) {
            System.out.println("getSchoolMasterDetail # " + se);
            se.printStackTrace();
        } catch (Exception e) {
            System.out.println("getSchoolMasterDetail # " + e);
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, null);
        }
        //1 . jdbc code endd

        //4 Return as required by method
        return schoolMasterVo;
    }

    public boolean updateSchoolMasterDetail(SchoolMasterVo vo) {
        System.out.println("id =" + vo.getSchoolId());
        boolean status = true;

        //Database connection start
        Connection conn = null;
        PreparedStatement stmt = null;
        try {

            conn = getConnection();
            String sql = "UPDATE school_mstr set  SCHOOL_NAME=?, SCHOOL_ADDRESS=?, DESC_TXT=?, WEBSITE_URL=?, SCHOOL_LOGO=?, OTHER_IMG=?, METADATA=?, DELETED_FL=?, DISPLAY_NO=?, ENABLE_FL=?, CREATED_BY=?, LAST_USERID_CD=?, LAST_UPDT_TM=utc_timestamp\n"
                    + "    WHERE SCHOOL_ID=?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, vo.getSchoolName());

            stmt.setString(3, vo.getSchoolAddress());
            stmt.setString(4, vo.getDescTxt());
            stmt.setString(5, vo.getWebsiteUrl());
            stmt.setString(6, vo.getSchoolLogo());
            stmt.setString(7, vo.getOtherImg());
            stmt.setString(8, vo.getMetedata());
            stmt.setString(9, vo.getDeleteFl());
            stmt.setInt(10, vo.getDisplayNo());
            stmt.setString(11, vo.getEnableFl());
            stmt.setString(12, vo.getCreatedBy());
            stmt.setString(13, vo.getLastUserIdCd());
            stmt.setInt(2, vo.getSchoolId());

            stmt.executeUpdate();
            System.out.println("Inserted records into the table...");

        } catch (SQLException se) {
            System.out.println("getSchoolMasterDetail # " + se);
            se.printStackTrace();
        } catch (Exception e) {
            System.out.println("getSchoolMasterDetail # " + e);
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, null);
        }

        System.out.println("Successfully updated....");
        return status;
        //End writting code to save into database   
    }
    //save method

    public void saveSchoolMasterDetail(SchoolMasterVo vo) {
        //Database connection start
        Connection conn = null;
        PreparedStatement stmt = null;
        try {

            conn = getConnection();
            String sql = "INSERT INTO school_mstr(SCHOOL_ID, SCHOOL_NAME, SCHOOL_ADDRESS, DESC_TXT, WEBSITE_URL, SCHOOL_LOGO, OTHER_IMG, METADATA, DELETED_FL, DISPLAY_NO, ENABLE_FL, CREATED_BY, LAST_USERID_CD, LAST_UPDT_TM)VALUES(?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?, utc_timestamp)";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, vo.getSchoolId());
            stmt.setString(2, vo.getSchoolName());

            stmt.setString(3, vo.getSchoolAddress());
            stmt.setString(4, vo.getDescTxt());
            stmt.setString(5, vo.getWebsiteUrl());
            stmt.setString(6, vo.getSchoolLogo());
            stmt.setString(7, vo.getOtherImg());
            stmt.setString(8, vo.getMetedata());
            stmt.setString(9, vo.getDeleteFl());
            stmt.setInt(10, vo.getDisplayNo());
            stmt.setString(11, vo.getEnableFl());
            stmt.setString(12, vo.getCreatedBy());
            stmt.setString(13, vo.getLastUserIdCd());

            //...
            stmt.executeUpdate();
            System.out.println("Inserted records into the table...");

        } catch (SQLException se) {
            System.out.println("getSchoolMasterDetail # " + se);
            se.printStackTrace();
        } catch (Exception e) {
            System.out.println("getSchoolMasterDetail # " + e);
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, null);
        }

        System.out.println("Successfully saved....");
    }

    //delete method
    public boolean deleteSchoolMasterDetail(SchoolMasterVo vo) {
        System.out.println("id =" + vo.getSchoolId());
        boolean status = true;

        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = getConnection();

            String sql = "DELETE FROM school_mstr WHERE SCHOOL_ID = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, vo.getSchoolId());
            stmt.executeUpdate();

            System.out.println("Deleted records into the table...");

        } catch (SQLException se) {
            System.out.println("getSchoolMasterDetail # " + se);
            se.printStackTrace();
        } catch (Exception e) {
            System.out.println("getSchoolMasterDetail # " + e);
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, null);
        }

        System.out.println("Successfully saved....");
        return status;
    }

    public List<SchoolMasterVo> getSchoolMasterVoList() {
        List<SchoolMasterVo> distList = new ArrayList<SchoolMasterVo>();

        //1 . jdbc code start
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = getConnection();

            String sql = "SELECT * FROM school_mstr where DELETED_FL='0' order by DISPLAY_NO";
            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {

                //3. Set db data to object
                SchoolMasterVo schoolMasterVo = new SchoolMasterVo();
                schoolMasterVo.setSchoolId(rs.getInt("SCHOOL_ID"));
                schoolMasterVo.setSchoolName(rs.getString("SCHOOL_NAME"));
                schoolMasterVo.setSchoolAddress(rs.getString("SCHOOL_ADDRESS"));
                schoolMasterVo.setDescTxt(rs.getString("DESC_TXT"));
                schoolMasterVo.setWebsiteUrl(rs.getString("WEBSITE_URL"));
                schoolMasterVo.setSchoolLogo(rs.getString("SCHOOL_LOGO"));
                schoolMasterVo.setOtherImg(rs.getString("OTHER_IMG"));
                schoolMasterVo.setMetedata(rs.getString("OTHER_IMG"));
                schoolMasterVo.setDeleteFl(rs.getString("DELETED_FL"));
                schoolMasterVo.setDisplayNo(rs.getInt("DISPLAY_NO"));
                schoolMasterVo.setEnableFl(rs.getString("ENABLE_FL"));
                schoolMasterVo.setCreatedBy(rs.getString("CREATED_BY"));
                schoolMasterVo.setLastUserIdCd(rs.getString("LAST_USERID_CD"));
                schoolMasterVo.setLastUpdtTm(rs.getString("LAST_UPDT_TM"));

                //Add into list
                distList.add(schoolMasterVo);
            }

            System.out.println("get records into the table...");

        } catch (SQLException se) {
            System.out.println("getSchoolMasterDetail # " + se);
            se.printStackTrace();
        } catch (Exception e) {
            System.out.println("getSchoolMasterDetail # " + e);
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, null);
        }
        //1 . jdbc code endd

        //4 Return as required by method
        return distList;
    }
    

    public List<SchoolMasterVo> getSchoolMasterVoList(int teacherId) {
        List<SchoolMasterVo> distList = new ArrayList<SchoolMasterVo>();

        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = getConnection();

            String sql = "SELECT SCHOOL_ID,SCHOOL_NAME FROM school_mstr where DELETED_FL='0' and SCHOOL_ID in (SELECT distinct SCHOOL_ID FROM teacher_courses where TEACHER_ID=(SELECT USER_NM FROM user_login where USER_ID=?))";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, teacherId);
            
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                SchoolMasterVo schoolMasterVo = new SchoolMasterVo();
                schoolMasterVo.setSchoolId(rs.getInt(1));
                schoolMasterVo.setSchoolName(rs.getString(2));

                //Add into list
                distList.add(schoolMasterVo);
            }


        } catch (SQLException se) {
            System.out.println("getSchoolMasterVoList(?) # " + se);
            se.printStackTrace();
        } catch (Exception e) {
            System.out.println("getSchoolMasterVoList(?) # " + e);
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, null);
        }

        return distList;
    }

	@Override
	public List<SchoolMasterVo> getSchoolMasterVoList(int schoolId,
			int teacherId) {
		 List<SchoolMasterVo> distList = new ArrayList<SchoolMasterVo>();

	        Connection conn = null;
	        PreparedStatement stmt = null;
	        try {
	            conn = getConnection();

	            String sql = "SELECT SCHOOL_ID,SCHOOL_NAME FROM school_mstr where DELETED_FL='0' and SCHOOL_ID in (SELECT distinct SCHOOL_ID FROM teacher_courses tc where tc.TEACHER_ID=(SELECT USER_NM FROM user_login where USER_ID=?) ";
	            
	            if(schoolId>0){
	            	sql=sql+" AND tc.SCHOOL_ID='"+schoolId+"' )";
	            	
	            }
	            else{
	            	sql=sql+" )";
	            	
	            }
	            
	            stmt = conn.prepareStatement(sql);
	            stmt.setInt(1, teacherId);
	            
	            ResultSet rs = stmt.executeQuery();
	            while (rs.next()) {
	                SchoolMasterVo schoolMasterVo = new SchoolMasterVo();
	                schoolMasterVo.setSchoolId(rs.getInt(1));
	                schoolMasterVo.setSchoolName(rs.getString(2));

	                //Add into list
	                distList.add(schoolMasterVo);
	            }


	        } catch (SQLException se) {
	            System.out.println("getSchoolMasterVoList(?) # " + se);
	            se.printStackTrace();
	        } catch (Exception e) {
	            System.out.println("getSchoolMasterVoList(?) # " + e);
	            e.printStackTrace();
	        } finally {
	            closeResources(conn, stmt, null);
	        }

	        return distList;
	}
    
    
}//end of class

package com.scolere.lms.persistance.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.scolere.lms.domain.exception.LmsDaoException;
import com.scolere.lms.domain.vo.TeacherCourseSessionDtlsVO;
import com.scolere.lms.persistance.dao.iface.TeacherCourseSessionDtlsDao;
import com.scolere.lms.persistance.factory.LmsDaoAbstract;

public class TeacherCourseSessionDtlsDaoImpl extends LmsDaoAbstract implements TeacherCourseSessionDtlsDao {

	@Override
	public boolean updateTeacherCourseSessionDtls(TeacherCourseSessionDtlsVO vo)
			throws LmsDaoException {
		System.out.println("id =" + vo.getCourseSessionDtlsID());
		 //System.out.println("id =" +1234);
		 
		 boolean status = true;

	        //Database connection start
	        Connection conn = null;
	        PreparedStatement stmt = null;
	        try {

	            conn = getConnection();
	            String sql = "UPDATE teacher_course_session_dtls set COURSE_SESSION_ID=?, TEACHER_ID=?, MODULE_ID=?," +
	            		" CONTENT_ID=?, START_SESSION_TM=?,END_SESSION_TM=?,IS_COMPLETED=?, LAST_USERID_CD=?," +
	            		" LAST_UPDT_TM=current_timestamp\n"
	                    + "    WHERE COURSE_SESSION_DTLS_ID=?";
	           /* String sql = "UPDATE teacher_course_session_dtls set COURSE_SESSION_ID=?, TEACHER_ID=?, MODULE_ID=?," +
	            		" CONTENT_ID=?, START_SESSION_TM=?, END_SESSION_TM=?,IS_COMPLETED=?, LAST_USERID_CD=?\n"
	                    + "    WHERE COURSE_SESSION_DTLS_ID=?";*/
	            stmt = conn.prepareStatement(sql);
	            
	            stmt.setInt(1, vo.getCourseSessionID());
	            stmt.setString(2, vo.getTeacherID());
	            stmt.setInt(3, vo.getModuleID());
	            stmt.setInt(4, vo.getContentID());
	            stmt.setString(5, vo.getStartSessionTm());
	            stmt.setString(6, vo.getEndSessionTm());
	            stmt.setString(7, vo.getIsCompleted());
	            stmt.setString(8, vo.getLastUserIDCd());
	            stmt.setInt(9, vo.getCourseSessionDtlsID());
	           // stmt.setString(7, vo.getLastUpdtTm());
	            
	            stmt.executeUpdate();
	            System.out.println("updated records into the table...");

	        } catch (SQLException e) {
	            System.out.println("getCourseSession SQLException1# " + e);
	            throw new LmsDaoException(e.getMessage());
	        } catch (Exception e) {
	            System.out.println("getCourseSession Exception 2# " + e);
	            throw new LmsDaoException(e.getMessage());
	        } finally {
	            closeResources(conn, stmt, null);
	        }

	        System.out.println("Successfully updated....");
	        return status;
	}

	@Override
	public void saveTeacherCourseSessionDtls(TeacherCourseSessionDtlsVO vo)
			throws LmsDaoException {
		
		//Database connection start
        Connection conn = null;
        PreparedStatement stmt = null;
        try {

            conn = getConnection();
           /* String sql = "INSERT INTO teacher_course_session_dtls(COURSE_SESSION_DTLS_ID, COURSE_SESSION_ID," +
            		" TEACHER_ID, MODULE_ID, CONTENT_ID, START_SESSION_TM,END_SESSION_TM,IS_COMPLETED, " +
            		"LAST_USERID_CD)  VALUES(?, ?, ?, ?, ?, ?, ?,?,?)";*/
            String sql = "INSERT INTO teacher_course_session_dtls(COURSE_SESSION_DTLS_ID, COURSE_SESSION_ID," +
            		"TEACHER_ID, MODULE_ID, CONTENT_ID, START_SESSION_TM,END_SESSION_TM,IS_COMPLETED, " +
            		"LAST_USERID_CD, LAST_UPDT_TM)  VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, current_timestamp)";

            
            stmt = conn.prepareStatement(sql);
            
            stmt.setInt(1, vo.getCourseSessionDtlsID());
            stmt.setInt(2, vo.getCourseSessionID());
            stmt.setString(3, vo.getTeacherID());
            stmt.setInt(4, vo.getModuleID());
            stmt.setInt(5, vo.getContentID());
            stmt.setString(6, vo.getStartSessionTm());
            stmt.setString(7, vo.getEndSessionTm());
            stmt.setString(8, vo.getIsCompleted());
            stmt.setString(9, vo.getLastUserIDCd());
          
            //...
            stmt.executeUpdate();
            System.out.println("Inserted records into the table...");

        } catch (SQLException se) {
            System.out.println("TeacherCourseSessionDtls # " + se);
            throw new LmsDaoException(se.getMessage());
        } catch (Exception e) {
            System.out.println("TeacherCourseSessionDtls # " + e);
            throw new LmsDaoException(e.getMessage());
        } finally {
            closeResources(conn, stmt, null);
        }

        System.out.println("Successfully saved....");
	}

	@Override
	public boolean deleteTeacherCourseSessionDtls(TeacherCourseSessionDtlsVO vo)
			throws LmsDaoException {
		
		System.out.println("id =" + vo.getCourseSessionDtlsID());
        boolean status = true;

        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = getConnection();

            String sql = "DELETE FROM teacher_course_session_dtls WHERE COURSE_SESSION_DTLS_ID = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, vo.getCourseSessionDtlsID());
            stmt.executeUpdate();

            System.out.println("Deleted records into the COURSE_SESSION_DTLS table...");

        } catch (SQLException se) {
            System.out.println("COURSE_SESSION_DTLS # " + se);
            throw new LmsDaoException(se.getMessage());
        } catch (Exception e) {
            System.out.println("COURSE_SESSION_DTLS # " + e);
            throw new LmsDaoException(e.getMessage());
        } finally {
            closeResources(conn, stmt, null);
        }

        System.out.println("Successfully deleted....");
        return status;
	}

	@Override
	public TeacherCourseSessionDtlsVO getTeacherCourseSessionDtls(int id)
			throws LmsDaoException {
		
		System.out.println("Inside getTeacherCourseSessionDtls(?) >>");
        //Create object to return
		TeacherCourseSessionDtlsVO teacherCourseSessionDtlsVO = new TeacherCourseSessionDtlsVO();

        //1 . jdbc code start
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = getConnection();
            
            String sql = "SELECT * FROM teacher_course_session_dtls where COURSE_SESSION_DTLS_ID=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, teacherCourseSessionDtlsVO.getCourseSessionDtlsID());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                //3. Set db data to object
            	teacherCourseSessionDtlsVO.setCourseSessionDtlsID(rs.getInt("COURSE_SESSION_DTLS_ID"));
            	teacherCourseSessionDtlsVO.setCourseSessionID(rs.getInt("COURSE_SESSION_ID"));
            	teacherCourseSessionDtlsVO.setTeacherID(rs.getString("TEACHER_ID"));
            	teacherCourseSessionDtlsVO.setModuleID(rs.getInt("MODULE_ID"));
            	teacherCourseSessionDtlsVO.setContentID(rs.getInt("CONTENT_ID"));
            	teacherCourseSessionDtlsVO.setStartSessionTm(rs.getString("START_SESSION_TM"));
            	teacherCourseSessionDtlsVO.setEndSessionTm(rs.getString("END_SESSION_TM"));
            	teacherCourseSessionDtlsVO.setIsCompleted(rs.getString("IS_COMPLETED"));
            	teacherCourseSessionDtlsVO.setLastUserIDCd(rs.getString("LAST_USERID_CD"));
            	teacherCourseSessionDtlsVO.setLastUpdtTm(rs.getString("LAST_UPDT_TM"));       
            }

            System.out.println("get records into the table...");

        } catch (SQLException se) {
            System.out.println("TeacherCourseSessionDtls # " + se);
            throw new LmsDaoException(se.getMessage());
        } catch (Exception e) {
            System.out.println("TeacherCourseSessionDtls # " + e);
            throw new LmsDaoException(e.getMessage());
        } finally {
            closeResources(conn, stmt, null);
        }
     //1 . jdbc code endd

        //4 Return as required by method
        return teacherCourseSessionDtlsVO;
	}

	@Override
	public List<TeacherCourseSessionDtlsVO> getTeacherCourseSessionDtlsList()
			throws LmsDaoException {
		
		List< TeacherCourseSessionDtlsVO> distList = new ArrayList<TeacherCourseSessionDtlsVO>();

        //1 . jdbc code start
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = getConnection();

            String sql = "SELECT * FROM teacher_course_session_dtls ";
            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {

                //3. Set db data to object
            	TeacherCourseSessionDtlsVO teacherCourseSessionDtlsVO = new TeacherCourseSessionDtlsVO();
            	
            	teacherCourseSessionDtlsVO.setCourseSessionDtlsID(rs.getInt("COURSE_SESSION_DTLS_ID"));
            	teacherCourseSessionDtlsVO.setCourseSessionID(rs.getInt("COURSE_SESSION_ID"));
            	teacherCourseSessionDtlsVO.setTeacherID(rs.getString("TEACHER_ID"));
            	teacherCourseSessionDtlsVO.setModuleID(rs.getInt("MODULE_ID"));
            	teacherCourseSessionDtlsVO.setContentID(rs.getInt("CONTENT_ID"));
            	teacherCourseSessionDtlsVO.setStartSessionTm(rs.getString("START_SESSION_TM"));
            	teacherCourseSessionDtlsVO.setEndSessionTm(rs.getString("END_SESSION_TM"));
            	teacherCourseSessionDtlsVO.setIsCompleted(rs.getString("IS_COMPLETED"));
            	teacherCourseSessionDtlsVO.setLastUserIDCd(rs.getString("LAST_USERID_CD"));
            	teacherCourseSessionDtlsVO.setLastUpdtTm(rs.getString("LAST_UPDT_TM"));   

                //Add into list
                distList.add(teacherCourseSessionDtlsVO);
            }

            System.out.println("get records into the table...");

        } catch (SQLException se) {
            System.out.println("TeacherCourseSessionDtls # " + se);
            throw new LmsDaoException(se.getMessage());
        } catch (Exception e) {
            System.out.println("TeacherCourseSessionDtls # " + e);
            throw new LmsDaoException(e.getMessage());
        } finally {
            closeResources(conn, stmt, null);
        }
     //1 . jdbc code endd

        //4 Return as required by method
        return distList;
	}

}

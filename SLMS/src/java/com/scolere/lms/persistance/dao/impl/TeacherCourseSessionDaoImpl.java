package com.scolere.lms.persistance.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.scolere.lms.domain.exception.LmsDaoException;
import com.scolere.lms.domain.vo.TeacherCourseSessionVO;
import com.scolere.lms.persistance.dao.iface.TeacherCourseSessionDao;
import com.scolere.lms.persistance.factory.LmsDaoAbstract;

public class TeacherCourseSessionDaoImpl extends LmsDaoAbstract implements TeacherCourseSessionDao{

	@Override
	public boolean updateTeacherCourseSession(TeacherCourseSessionVO vo)
			throws LmsDaoException {
		 System.out.println("id =" + vo.getCourseSessionID());
		 //System.out.println("id =" +1234);
		 
		 boolean status = true;

	        //Database connection start
	        Connection conn = null;
	        PreparedStatement stmt = null;
	        try {
	        	
	            conn = getConnection();
	            String sql = "UPDATE teacher_course_sessions set TEACHER_COURSE_ID=?, START_SESSION_TM=?, END_SESSION_TM=?, STATUS_TXT=?, LAST_USERID_CD=?, LAST_UPDT_TM=current_timestamp\n"
	                    + "    WHERE COURSE_SESSION_ID=?";
	            /*String sql = "UPDATE teacher_course_sessions set TEACHER_COURSE_ID=?, START_SESSION_TM=?, END_SESSION_TM=?, STATUS_TXT=?, LAST_USERID_CD=?\n"
	                    + "    WHERE COURSE_SESSION_ID=?";*/
	            stmt = conn.prepareStatement(sql);

	            
	            stmt.setInt(1, vo.getTeacherCourseID());
	            stmt.setString(2, vo.getStartSessionTm());
	            stmt.setString(3, vo.getEndSessionTm());
	            stmt.setString(4, vo.getStatusTxt());
	            stmt.setString(5, vo.getLastUseridCd());
	            stmt.setInt(6, vo.getCourseSessionID());
	           // stmt.setString(7, vo.getLastUpdtTm());
	            
	            stmt.executeUpdate();
	            System.out.println("updated records into the table...");

	        } catch (SQLException e) {
	            System.out.println("TeacherCourseSessionVO SQLException1# " + e);
	            throw new LmsDaoException(e.getMessage());
	        } catch (Exception e) {
	            System.out.println("TeacherCourseSessionVO Exception 2# " + e);
	            throw new LmsDaoException(e.getMessage());
	        } finally {
	            closeResources(conn, stmt, null);
	        }

	        System.out.println("Successfully updated....");
	        return status;
		
	}

	@Override
	public void saveTeacherCourseSession(TeacherCourseSessionVO vo)
			throws LmsDaoException {
		//Database connection start
        Connection conn = null;
        PreparedStatement stmt = null;
        try {

            conn = getConnection();
           /* String sql = "INSERT INTO teacher_course_sessions(COURSE_SESSION_ID, TEACHER_COURSE_ID, START_SESSION_TM," +
            		" END_SESSION_TM, STATUS_TXT, LAST_USERID_CD)  VALUES(?, ?, ?, ?, ?, ?)";*/
            String sql = "INSERT INTO teacher_course_sessions(COURSE_SESSION_ID, TEACHER_COURSE_ID, START_SESSION_TM, END_SESSION_TM, " +
            		"STATUS_TXT, LAST_USERID_CD, LAST_UPDT_TM)  VALUES(?, ?, ?, ?, ?, ?, current_timestamp)";

           
            stmt = conn.prepareStatement(sql);
            
            stmt.setInt(1, vo.getCourseSessionID());
            stmt.setInt(2, vo.getTeacherCourseID());
            stmt.setString(3, vo.getStartSessionTm());
            stmt.setString(4, vo.getEndSessionTm());
            stmt.setString(5, vo.getStatusTxt());
            stmt.setString(6, vo.getLastUseridCd());
            
          /*  stmt.setString(8, vo.getLastUpdtTm());*/
          
            //...
            stmt.executeUpdate();
            System.out.println("Inserted records into the table...");

        } catch (SQLException se) {
            System.out.println("saveTeacherCourseSession # " + se);
            throw new LmsDaoException(se.getMessage());
        } catch (Exception e) {
            System.out.println("saveTeacherCourseSession # " + e);
            throw new LmsDaoException(e.getMessage());
        } finally {
            closeResources(conn, stmt, null);
        }

        System.out.println("Successfully saved....");
		
	}

	@Override
	public boolean deleteTeacherCourseSession(TeacherCourseSessionVO vo)
			throws LmsDaoException {
		 
		System.out.println("id =" + vo.getCourseSessionID());
        boolean status = true;

        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = getConnection();

            String sql = "DELETE FROM teacher_course_sessions WHERE COURSE_SESSION_ID = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, vo.getCourseSessionID());
            stmt.executeUpdate();

            System.out.println("Deleted records into the ASSIGNMENT table...");

        } catch (SQLException se) {
            System.out.println("TeacherCourseSessionVO # " + se);
            throw new LmsDaoException(se.getMessage());
        } catch (Exception e) {
            System.out.println("TeacherCourseSessionVO # " + e);
            throw new LmsDaoException(e.getMessage());
        } finally {
            closeResources(conn, stmt, null);
        }

        System.out.println("Successfully deleted....");
        return status;
	}

	@Override
	public TeacherCourseSessionVO getTeacherCourseSession(int id)
			throws LmsDaoException {
		 
		System.out.println("Inside getTeacherCourseSession(?) >>");
        //Create object to return
		TeacherCourseSessionVO teacherCourseSessionVO = new TeacherCourseSessionVO();

        //1 . jdbc code start
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = getConnection();

            String sql = "SELECT * FROM teacher_course_sessions where COURSE_SESSION_ID=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, teacherCourseSessionVO.getCourseSessionID());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                //3. Set db data to object
            	teacherCourseSessionVO.setCourseSessionID(rs.getInt("COURSE_SESSION_ID"));
            	teacherCourseSessionVO.setTeacherCourseID(rs.getInt("TEACHER_COURSE_ID"));
            	teacherCourseSessionVO.setStartSessionTm(rs.getString("START_SESSION_TM"));
            	teacherCourseSessionVO.setEndSessionTm(rs.getString("END_SESSION_TM"));
            	teacherCourseSessionVO.setStatusTxt(rs.getString("STATUS_TXT"));
            	teacherCourseSessionVO.setLastUseridCd(rs.getString("LAST_USERID_CD"));
            	teacherCourseSessionVO.setLastUpdateTm(rs.getString("LAST_UPDT_TM"));       
            }

            System.out.println("get records into the table...");

        } catch (SQLException se) {
            System.out.println("getTeacherCourseSession # " + se);
            throw new LmsDaoException(se.getMessage());
        } catch (Exception e) {
            System.out.println("getTeacherCourseSession # " + e);
            throw new LmsDaoException(e.getMessage());
        } finally {
            closeResources(conn, stmt, null);
        }
     //1 . jdbc code endd

        //4 Return as required by method
        return teacherCourseSessionVO;
	}

	@Override
	public List<TeacherCourseSessionVO> getTeacherCourseSessionList()
			throws LmsDaoException {
		 
		List< TeacherCourseSessionVO> distList = new ArrayList<TeacherCourseSessionVO>();

        //1 . jdbc code start
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = getConnection();

            String sql = "SELECT * FROM teacher_course_sessions ";
            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {

                //3. Set db data to object
            	TeacherCourseSessionVO teacherCourseSessionVO = new TeacherCourseSessionVO();

            	teacherCourseSessionVO.setCourseSessionID(rs.getInt("COURSE_SESSION_ID"));
            	teacherCourseSessionVO.setTeacherCourseID(rs.getInt("TEACHER_COURSE_ID"));
            	teacherCourseSessionVO.setStartSessionTm(rs.getString("START_SESSION_TM"));
            	teacherCourseSessionVO.setEndSessionTm(rs.getString("END_SESSION_TM"));
            	teacherCourseSessionVO.setStatusTxt(rs.getString("STATUS_TXT"));
            	teacherCourseSessionVO.setLastUseridCd(rs.getString("LAST_USERID_CD"));
            	teacherCourseSessionVO.setLastUpdateTm(rs.getString("LAST_UPDT_TM"));  


                //Add into list
                distList.add(teacherCourseSessionVO);
            }

            System.out.println("get records into the list...");

        } catch (SQLException se) {
            System.out.println("teacherCourseSessionVO # " + se);
            throw new LmsDaoException(se.getMessage());
        } catch (Exception e) {
            System.out.println("teacherCourseSessionVO # " + e);
            throw new LmsDaoException(e.getMessage());
        } finally {
            closeResources(conn, stmt, null);
        }
     //1 . jdbc code endd

        //4 Return as required by method
        return distList;
	}

	
	

}

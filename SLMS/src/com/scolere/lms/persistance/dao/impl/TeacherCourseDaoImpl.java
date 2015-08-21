package com.scolere.lms.persistance.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.scolere.lms.domain.exception.LmsDaoException;
import com.scolere.lms.domain.vo.TeacherCourseVO;
import com.scolere.lms.persistance.dao.iface.TeacherCourseDao;
import com.scolere.lms.persistance.factory.LmsDaoAbstract;

public class TeacherCourseDaoImpl extends LmsDaoAbstract implements TeacherCourseDao {

	@Override
	public boolean updateTeacherCourseVO(TeacherCourseVO vo)
			throws LmsDaoException {
		System.out.println("id =" + vo.getTeacherCourseID());
		 //System.out.println("id =" +1234);
		 
		 boolean status = true;

	        //Database connection start
	        Connection conn = null;
	        PreparedStatement stmt = null;
	        try {

	            conn = getConnection();
	            String sql = "UPDATE teacher_courses set COURSE_ID=?, TEACHER_ID=?, CLASS_ID=?, SCHOOL_ID=?, HRM_ID=?,  DISPLAY_NO=?,ENABLE_FL=?,CREATED_BY=?, LAST_USERID_CD=?, LAST_UPDT_TM=current_timestamp\n"
	                    + "    WHERE TEACHER_COURSE_ID=?";
	            /*String sql = "UPDATE teacher_courses set COURSE_ID=?, TEACHER_ID=?, CLASS_ID=?, SCHOOL_ID=?, HRM_ID=?," +
	            		" DISPLAY_NO=?,ENABLE_FL=?,CREATED_BY=?, LAST_USERID_CD=?\n"
	                    + "    WHERE TEACHER_COURSE_ID=?";*/
	            stmt = conn.prepareStatement(sql);
	          	            
	            stmt.setInt(1, vo.getCourseID());
	            stmt.setString(2, vo.getTeacherID());
	            stmt.setInt(3, vo.getClassID());
	            stmt.setInt(4, vo.getSchoolID());
	            stmt.setInt(5, vo.getHrmID());
	            stmt.setInt(6, vo.getDisplayNo());
	            stmt.setString(7, vo.getEnableFl());
	            stmt.setString(8, vo.getCreateBy());
	            stmt.setString(9, vo.getLastUseridCd());
	            stmt.setInt(10, vo.getTeacherCourseID());
	            
	           // stmt.setString(7, vo.getLastUpdtTm());
	            
	            stmt.executeUpdate();
	            System.out.println("updated records into the table...");

	        } catch (SQLException e) {
	            System.out.println("TeacherCourseVO SQLException1# " + e);
	            throw new LmsDaoException(e.getMessage());
	        } catch (Exception e) {
	            System.out.println("TeacherCourseVO Exception 2# " + e);
	            throw new LmsDaoException(e.getMessage());
	        } finally {
	            closeResources(conn, stmt, null);
	        }

	        System.out.println("Successfully updated....");
	        return status;
	}

	@Override
	public void saveTeacherCourseVO(TeacherCourseVO vo) throws LmsDaoException {
		//Database connection start
        Connection conn = null;
        PreparedStatement stmt = null;
        try {

            conn = getConnection();
            /*String sql = "INSERT INTO teacher_courses(TEACHER_COURSE_ID, COURSE_ID, TEACHER_ID, CLASS_ID," +
            		" SCHOOL_ID, HRM_ID, DISPLAY_NO, ENABLE_FL, CREATED_BY, LAST_USERID_CD)  VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";*/
            String sql = "INSERT INTO teacher_courses(TEACHER_COURSE_ID, COURSE_ID, TEACHER_ID, CLASS_ID, SCHOOL_ID, HRM_ID, DISPLAY_NO," +
            		" ENABLE_FL, CREATED_BY, LAST_USERID_CD, LAST_UPDT_TM)  VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, current_timestamp)";
          
            
            stmt = conn.prepareStatement(sql);
            
            stmt.setInt(1, vo.getTeacherCourseID());
            stmt.setInt(2, vo.getCourseID());
            stmt.setString(3, vo.getTeacherID());
            stmt.setInt(4, vo.getClassID());
            stmt.setInt(5, vo.getSchoolID());
            stmt.setInt(6, vo.getHrmID());
            stmt.setInt(7, vo.getDisplayNo());
            stmt.setString(8, vo.getEnableFl());
            stmt.setString(9, vo.getCreateBy());
            stmt.setString(10, vo.getLastUseridCd());
            
          /*  stmt.setString(8, vo.getLastUpdtTm());*/
          
            //...
            stmt.executeUpdate();
            System.out.println("Inserted records into the table...");

        } catch (SQLException se) {
            System.out.println("TeacherCourseVO # " + se);
            throw new LmsDaoException(se.getMessage());
        } catch (Exception e) {
            System.out.println("TeacherCourseVO # " + e);
            throw new LmsDaoException(e.getMessage());
        } finally {
            closeResources(conn, stmt, null);
        }

        System.out.println("Successfully saved....");
	}

	@Override
	public boolean deleteTeacherCourseVO(TeacherCourseVO vo)
			throws LmsDaoException {
		 
		System.out.println("id =" + vo.getTeacherCourseID());
        boolean status = true;

        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = getConnection();

            String sql = "DELETE FROM teacher_courses WHERE TEACHER_COURSE_ID = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, vo.getTeacherCourseID());
            stmt.executeUpdate();

            System.out.println("Deleted records into the TeacherCourse table...");

        } catch (SQLException se) {
            System.out.println("TeacherCourseVO # " + se);
            throw new LmsDaoException(se.getMessage());
        } catch (Exception e) {
            System.out.println("TeacherCourseVO # " + e);
            throw new LmsDaoException(e.getMessage());
        } finally {
            closeResources(conn, stmt, null);
        }

        System.out.println("Successfully deleted....");
        return status;
	}

	@Override
	public TeacherCourseVO getTeacherCourse(int id) throws LmsDaoException {
		 
		System.out.println("Inside getTeacherCourse(?) >>");
        //Create object to return
		TeacherCourseVO teacherCourseVO = new TeacherCourseVO();

        //1 . jdbc code start
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = getConnection();
           
            String sql = "SELECT * FROM teacher_courses where TEACHER_COURSE_ID=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, teacherCourseVO.getTeacherCourseID());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                //3. Set db data to object
            	teacherCourseVO.setTeacherCourseID(rs.getInt("TEACHER_COURSE_ID"));
            	teacherCourseVO.setCourseID(rs.getInt("COURSE_ID"));
            	teacherCourseVO.setTeacherID(rs.getString("TEACHER_ID"));
            	teacherCourseVO.setClassID(rs.getInt("CLASS_ID"));
            	teacherCourseVO.setSchoolID(rs.getInt("SCHOOL_ID"));
            	teacherCourseVO.setHrmID(rs.getInt("HRM_ID"));
            	teacherCourseVO.setDisplayNo(rs.getInt("DISPLAY_NO"));
            	teacherCourseVO.setEnableFl(rs.getString("ENABLE_FL"));
            	teacherCourseVO.setCreateBy(rs.getString("CREATED_BY"));
            	teacherCourseVO.setLastUseridCd(rs.getString("LAST_USERID_CD"));
            	teacherCourseVO.setLastUpdtTm(rs.getString("LAST_UPDT_TM"));       
            }

            System.out.println("get records into the table...");

        } catch (SQLException se) {
            System.out.println("teacherCourseVO # " + se);
            throw new LmsDaoException(se.getMessage());
        } catch (Exception e) {
            System.out.println("teacherCourseVO # " + e);
            throw new LmsDaoException(e.getMessage());
        } finally {
            closeResources(conn, stmt, null);
        }
     //1 . jdbc code endd

        //4 Return as required by method
        return teacherCourseVO;
	}

	@Override
	public List<TeacherCourseVO> getTeacherCourseList() throws LmsDaoException {
		 		
		 List< TeacherCourseVO> distList = new ArrayList<TeacherCourseVO>();

	        //1 . jdbc code start
	        Connection conn = null;
	        PreparedStatement stmt = null;
	        try {
	            conn = getConnection();

	            String sql = "SELECT * FROM teacher_courses ";
	            stmt = conn.prepareStatement(sql);
	            ResultSet rs = stmt.executeQuery();
	            while (rs.next()) {

	                //3. Set db data to object
	            	TeacherCourseVO teacherCourseVO = new TeacherCourseVO();

	            	teacherCourseVO.setTeacherCourseID(rs.getInt("TEACHER_COURSE_ID"));
	            	teacherCourseVO.setCourseID(rs.getInt("COURSE_ID"));
	            	teacherCourseVO.setTeacherID(rs.getString("TEACHER_ID"));
	            	teacherCourseVO.setClassID(rs.getInt("CLASS_ID"));
	            	teacherCourseVO.setSchoolID(rs.getInt("SCHOOL_ID"));
	            	teacherCourseVO.setHrmID(rs.getInt("HRM_ID"));
	            	teacherCourseVO.setDisplayNo(rs.getInt("DISPLAY_NO"));
	            	teacherCourseVO.setEnableFl(rs.getString("ENABLE_FL"));
	            	teacherCourseVO.setCreateBy(rs.getString("CREATED_BY"));
	            	teacherCourseVO.setLastUseridCd(rs.getString("LAST_USERID_CD"));
	            	teacherCourseVO.setLastUpdtTm(rs.getString("LAST_UPDT_TM"));  ;


	                //Add into list
	                distList.add(teacherCourseVO);
	            }

	            System.out.println("get records into the list...");

	        } catch (SQLException se) {
	            System.out.println("teacherCourseVO # " + se);
	            throw new LmsDaoException(se.getMessage());
	        } catch (Exception e) {
	            System.out.println("teacherCourseVO # " + e);
	            throw new LmsDaoException(e.getMessage());
	        } finally {
	            closeResources(conn, stmt, null);
	        }
	     //1 . jdbc code endd

	        //4 Return as required by method
	        return distList;
	}

}

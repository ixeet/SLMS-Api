package com.scolere.lms.persistance.dao.impl;

import com.scolere.lms.domain.exception.LmsDaoException;
import com.scolere.lms.domain.vo.TeacherCourseSessionVO;
import com.scolere.lms.domain.vo.cross.AssignmentVO;
import com.scolere.lms.domain.vo.cross.CommentVO;
import com.scolere.lms.domain.vo.cross.CourseVO;
import com.scolere.lms.domain.vo.cross.ResourseVO;
import com.scolere.lms.persistance.dao.iface.TeacherCourseSessionDao;
import com.scolere.lms.persistance.factory.LmsDaoAbstract;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeacherCourseSessionDaoImpl extends LmsDaoAbstract implements TeacherCourseSessionDao {

    @Override
    public boolean saveResourceComment(String commentBy, int resourceId, String commentTxt) throws LmsDaoException {
        
        boolean status=false;
        //Database connection start
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = getConnection();
            
            String sql = "INSERT INTO resource_comments(RESOURCE_ID, COMMENT_TXT, PARENT_COMMENT_ID, COMMENT_ON, ASSOCIATE_ID, COMMENTED_BY, LAST_USERID_CD, LAST_UPDT_TM)"
                    + " VALUES (?, ?, null, current_date, 0, ?, ?, utc_timestamp)";

            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, resourceId);
            stmt.setString(2, commentTxt);
//            stmt.setInt(3, 0);
            stmt.setString(3, commentBy);
            stmt.setString(4, commentBy);

            stmt.executeUpdate();
            System.out.println("Inserted records into the table...");
            status = true;
        } catch (SQLException se) {
            System.out.println("saveResourceComment # " + se);
            throw new LmsDaoException(se.getMessage());
        } catch (Exception e) {
            System.out.println("saveResourceComment # " + e);
            throw new LmsDaoException(e.getMessage());
        } finally {
            closeResources(conn, stmt, null);
        }

        return status;
    }
    
    @Override
    public boolean saveCommentComment(String commentBy, int commentId, String commentTxt) throws LmsDaoException {
        
        boolean status=false;
        //Database connection start
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = getConnection();
            
            String sql = "INSERT INTO resource_comments(RESOURCE_ID, COMMENT_TXT, PARENT_COMMENT_ID, COMMENT_ON, ASSOCIATE_ID, COMMENTED_BY, LAST_USERID_CD, LAST_UPDT_TM)"
                    + " VALUES (?, ?, ?, current_date, 0, ?, ?, utc_timestamp)";

            String resource_id = getQueryConcatedResult("SELECT RESOURCE_ID FROM resource_comments where RESOURCE_COMMENT_ID="+commentId);
            System.out.println("Resource id = "+resource_id);
            
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(resource_id));
            stmt.setString(2, commentTxt);
            stmt.setInt(3, commentId);
            stmt.setString(4, commentBy);
            stmt.setString(5, commentBy);

            stmt.executeUpdate();
            System.out.println("Inserted records into the table...");
            status = true;
        } catch (SQLException se) {
            System.out.println("saveCommentComment # " + se);
            throw new LmsDaoException(se.getMessage());
        } catch (Exception e) {
            System.out.println("saveCommentComment # " + e);
            throw new LmsDaoException(e.getMessage());
        } finally {
            closeResources(conn, stmt, null);
        }

        return status;
    }

    
    @Override
    public boolean saveResourceLike(String likeBy, int resourceId) throws LmsDaoException {
        boolean status=false;
        //Database connection start
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = getConnection();
            
            String sql = "INSERT INTO resource_likes(RESOURCE_ID, PARENT_COMMENT_ID, LIKE_ON, ASSOCIATE_ID, LIKE_BY, LAST_USERID_CD, LAST_UPDT_TM)"
                                           + "VALUES(?, null, current_date, 0, ?, ?, utc_timestamp)";

            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, resourceId);
            stmt.setString(2, likeBy);
            stmt.setString(3, likeBy);

            stmt.executeUpdate();
            System.out.println("Inserted records into the table...");
            status = true;
        } catch (SQLException se) {
            System.out.println("saveResourceLike # " + se);
            throw new LmsDaoException(se.getMessage());
        } catch (Exception e) {
            System.out.println("saveResourceLike # " + e);
            throw new LmsDaoException(e.getMessage());
        } finally {
            closeResources(conn, stmt, null);
        }

        return status;
    }

    
    
    @Override
    public boolean saveCommentLike(String likeBy, int commentId) throws LmsDaoException {
        
        boolean status=false;
        //Database connection start
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = getConnection();
            
            String sql = "INSERT INTO resource_likes(RESOURCE_ID, PARENT_COMMENT_ID, LIKE_ON, ASSOCIATE_ID, LIKE_BY, LAST_USERID_CD, LAST_UPDT_TM)"
                                            + "VALUES(?, ?, current_date, 0, ?, ?, utc_timestamp)";

            String resource_id = getQueryConcatedResult("SELECT RESOURCE_ID FROM resource_comments where RESOURCE_COMMENT_ID="+commentId);
            System.out.println("Resource id = "+resource_id);
            
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(resource_id));
            stmt.setInt(2, commentId);
            stmt.setString(3, likeBy);
            stmt.setString(4, likeBy);

            stmt.executeUpdate();
            System.out.println("Inserted records into the table...");
            status = true;
        } catch (SQLException se) {
            System.out.println("saveCommentLike # " + se);
            throw new LmsDaoException(se.getMessage());
        } catch (Exception e) {
            System.out.println("saveCommentLike # " + e);
            throw new LmsDaoException(e.getMessage());
        } finally {
            closeResources(conn, stmt, null);
        }

        return status;
    }

      
    
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
            String sql = "UPDATE teacher_course_sessions set TEACHER_COURSE_ID=?, START_SESSION_TM=?, END_SESSION_TM=?, STATUS_TXT=?, LAST_USERID_CD=?, LAST_UPDT_TM=utc_timestamp\n"
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
            String sql = "INSERT INTO teacher_course_sessions(COURSE_SESSION_ID, TEACHER_COURSE_ID, START_SESSION_TM, END_SESSION_TM, "
                    + "STATUS_TXT, LAST_USERID_CD, LAST_UPDT_TM)  VALUES(?, ?, ?, ?, ?, ?, utc_timestamp)";


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
        ResultSet rs = null;
        try {
            conn = getConnection();

            String sql = "SELECT * FROM teacher_course_sessions ";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
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

        return distList;
    }

    @Override
    public List<CourseVO> getStudentCourses(String userName, String searchText) throws LmsDaoException {
        List<CourseVO> list = new ArrayList<CourseVO>();

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();

            /**
             * Student Courses - > user_login + user_cls_map + clas_course_map 
             * SELECT cmstr.COURSE_ID,cmstr.COURSE_NAME,tc_sess.START_SESSION_TM,tc_sess.IS_COMPLETE,tc_sess.COURSE_SESSION_ID FROM user_cls_map ucmap INNER JOIN clas_course_map cc_map ON cc_map.CLASS_ID=ucmap.CLASS_ID INNER JOIN course_mstr cmstr ON cmstr.COURSE_ID=cc_map.COURSE_ID INNER JOIN teacher_courses tcourse ON tcourse.COURSE_ID= cc_map.COURSE_ID AND tcourse.CLASS_ID=ucmap.CLASS_ID INNER JOIN teacher_course_sessions tc_sess ON tc_sess.TEACHER_COURSE_ID=tcourse.TEACHER_COURSE_ID INNER JOIN user_login ulogin ON ulogin.USER_ID=ucmap.USER_ID where ulogin.USER_NM = ? AND cmstr.METADATA like ?
             */
            
            //String sql = "SELECT cmstr.COURSE_ID,cmstr.COURSE_NAME,tc_sess.START_SESSION_TM,tc_sess.IS_COMPLETE,tc_sess.COURSE_SESSION_ID FROM user_login ulogin INNER JOIN user_course_map ucmap ON ulogin.USER_ID=ucmap.USER_ID INNER JOIN course_mstr cmstr ON cmstr.COURSE_ID=ucmap.COURSE_ID INNER JOIN teacher_courses tcourse ON tcourse.COURSE_ID= ucmap.COURSE_ID INNER JOIN teacher_course_sessions tc_sess ON tc_sess.TEACHER_COURSE_ID=tcourse.TEACHER_COURSE_ID where ulogin.USER_NM = ? AND cmstr.METADATA like ?";
            String sql = "SELECT cmstr.COURSE_ID,cmstr.COURSE_NAME,tc_sess.START_SESSION_TM,tc_sess.IS_COMPLETE,tc_sess.COURSE_SESSION_ID FROM user_cls_map ucmap INNER JOIN clas_course_map cc_map ON cc_map.CLASS_ID=ucmap.CLASS_ID INNER JOIN course_mstr cmstr ON cmstr.COURSE_ID=cc_map.COURSE_ID INNER JOIN teacher_courses tcourse ON tcourse.COURSE_ID= cc_map.COURSE_ID AND tcourse.CLASS_ID=ucmap.CLASS_ID INNER JOIN teacher_course_sessions tc_sess ON tc_sess.TEACHER_COURSE_ID=tcourse.TEACHER_COURSE_ID INNER JOIN user_login ulogin ON ulogin.USER_ID=ucmap.USER_ID where ulogin.USER_NM = ? AND cmstr.METADATA like ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, userName);
            stmt.setString(2, "%"+searchText+"%");
                        
            rs = stmt.executeQuery();
            CourseVO vo = null;
            while (rs.next()) {
                vo = new CourseVO();
                vo.setCourseId(rs.getString(1));
                vo.setCourseName(rs.getString(2));
                vo.setStartedOn(rs.getString(3));
                vo.setCompletedStatus(rs.getString(4));
                vo.setCourseSessionId(rs.getInt(5));
                list.add(vo);
            }

        } catch (SQLException se) {
            System.out.println("getStudentCourses # " + se);
            throw new LmsDaoException(se.getMessage());
        } catch (Exception e) {
            System.out.println("getStudentCourses # " + e);
            throw new LmsDaoException(e.getMessage());
        } finally {
            closeResources(conn, stmt, rs);
        }

        return list;
    }

    

    @Override
    public CourseVO getStudentCourseDetail(int courseID) throws LmsDaoException {
        CourseVO vo =null;

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();

            /**
             * Student Courses - > user_cls_map + clas_course_map 
             * 
             * Query = SELECT cmstr.COURSE_ID,cmstr.COURSE_NAME,tc_sess.START_SESSION_TM,tc_sess.IS_COMPLETE,tc_sess.COURSE_SESSION_ID FROM user_cls_map ucmap INNER JOIN clas_course_map cc_map ON cc_map.CLASS_ID=ucmap.CLASS_ID INNER JOIN course_mstr cmstr ON cmstr.COURSE_ID=cc_map.COURSE_ID INNER JOIN teacher_courses tcourse ON tcourse.COURSE_ID= cc_map.COURSE_ID AND tcourse.CLASS_ID=ucmap.CLASS_ID INNER JOIN teacher_course_sessions tc_sess ON tc_sess.TEACHER_COURSE_ID=tcourse.TEACHER_COURSE_ID where cmstr.COURSE_ID = ?
             */
            
            String sql = "SELECT cmstr.COURSE_ID,cmstr.COURSE_NAME,tc_sess.START_SESSION_TM,tc_sess.IS_COMPLETE,tc_sess.COURSE_SESSION_ID FROM user_cls_map ucmap INNER JOIN clas_course_map cc_map ON cc_map.CLASS_ID=ucmap.CLASS_ID INNER JOIN course_mstr cmstr ON cmstr.COURSE_ID=cc_map.COURSE_ID INNER JOIN teacher_courses tcourse ON tcourse.COURSE_ID= cc_map.COURSE_ID AND tcourse.CLASS_ID=ucmap.CLASS_ID INNER JOIN teacher_course_sessions tc_sess ON tc_sess.TEACHER_COURSE_ID=tcourse.TEACHER_COURSE_ID where cmstr.COURSE_ID = ?";
            
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, courseID);
                        
            rs = stmt.executeQuery();
            while (rs.next()) {
                vo = new CourseVO();
                vo.setCourseId(rs.getString(1));
                vo.setCourseName(rs.getString(2));
                vo.setStartedOn(rs.getString(3));
                vo.setCompletedStatus(rs.getString(4));
                vo.setCourseSessionId(rs.getInt(5));
            }

        } catch (SQLException se) {
            System.out.println("getStudentCourseDetail # " + se);
            throw new LmsDaoException(se.getMessage());
        } catch (Exception e) {
            System.out.println("getStudentCourseDetail # " + e);
            throw new LmsDaoException(e.getMessage());
        } finally {
            closeResources(conn, stmt, rs);
        }

    return vo;        
    }    
    
    
    @Override
    public List<CourseVO> getTeacherCourses(int userId,int schoolId,int classId,int hrmId,int courseId,int moduleId) throws LmsDaoException {
        List<CourseVO> list = new ArrayList<CourseVO>();

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();

            StringBuffer sql = new StringBuffer("SELECT distinct cmstr.COURSE_ID,cmstr.COURSE_NAME,cmstr.COURSE_AUTHOR,cmstr.AUTHOR_IMG,tc_sess.START_SESSION_TM,tc_sess.END_SESSION_TM,tc_sess.IS_COMPLETE,tc_sess.COURSE_SESSION_ID,tcourse.SCHOOL_ID,smstr.SCHOOL_NAME,tcourse.CLASS_ID,clsmstr.CLASS_NAME,tcourse.HRM_ID,hmstr.HRM_NAME FROM teacher_courses tcourse INNER JOIN teacher_course_sessions tc_sess ON tc_sess.TEACHER_COURSE_ID=tcourse.TEACHER_COURSE_ID INNER JOIN teacher_course_session_dtls tm_sess ON tm_sess.COURSE_SESSION_ID=tc_sess.COURSE_SESSION_ID INNER JOIN teacher_dtls teacher ON teacher.EMAIL_ID=tcourse.TEACHER_ID LEFT JOIN course_mstr cmstr ON  tcourse.COURSE_ID= cmstr.COURSE_ID LEFT JOIN school_mstr smstr on smstr.SCHOOL_ID=tcourse.SCHOOL_ID LEFT JOIN class_mstr clsmstr on clsmstr.CLASS_ID=tcourse.CLASS_ID LEFT JOIN homeroom_mstr hmstr on hmstr.HRM_ID=tcourse.HRM_ID where teacher.USER_ID =").append(userId);
            if(courseId>0)
            	sql.append(" AND tcourse.COURSE_ID = ").append(courseId);
            if(schoolId>0)
            	sql.append(" AND tcourse.SCHOOL_ID = ").append(schoolId);
            if(classId>0)
            	sql.append(" AND tcourse.CLASS_ID = ").append(classId);
            if(hrmId>0)
            	sql.append(" AND tcourse.HRM_ID = ").append(hrmId);
            if(moduleId>0)
            	sql.append(" AND tm_sess.MODULE_ID = ").append(moduleId);
            
            
            System.out.println("Generated query : "+sql);
            stmt = conn.prepareStatement(sql.toString());
            rs = stmt.executeQuery();
            CourseVO vo = null;
            while (rs.next()) {
                vo = new CourseVO();
                vo.setCourseId(rs.getString(1));
                vo.setCourseName(rs.getString(2));
                vo.setAuthorName(rs.getString(3));
                vo.setAuthorImg(rs.getString(4));
                vo.setStartedOn(rs.getString(5));
                vo.setCompletedOn(rs.getString(6));
                vo.setCompletedStatus(rs.getString(7));
                vo.setCourseSessionId(rs.getInt(8));   //use to get modules list
                vo.setSchoolId(rs.getInt(9));
                vo.setSchoolName(rs.getString(10));
                vo.setClassId(rs.getInt(11));
                vo.setClasseName(rs.getString(12));
                vo.setHrmId(rs.getInt(13));
                vo.setHrmName(rs.getString(14));
                
                list.add(vo);
            }

        } catch (SQLException se) {
            System.out.println("getTeacherCourses # " + se);
            throw new LmsDaoException(se.getMessage());
        } catch (Exception e) {
            System.out.println("getTeacherCourses # " + e);
            throw new LmsDaoException(e.getMessage());
        } finally {
            closeResources(conn, stmt, rs);
        }

        return list;
    }
    
    
    
    @Override
    public List<CourseVO> getTeacherCourses(int userId,int schoolId,int classId,int hrmId,int courseId) throws LmsDaoException {
        List<CourseVO> list = new ArrayList<CourseVO>();

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();

            StringBuffer sql = new StringBuffer("SELECT cmstr.COURSE_ID,cmstr.COURSE_NAME,cmstr.COURSE_AUTHOR,cmstr.AUTHOR_IMG,tc_sess.START_SESSION_TM,tc_sess.END_SESSION_TM,tc_sess.IS_COMPLETE,tc_sess.COURSE_SESSION_ID,tcourse.SCHOOL_ID,smstr.SCHOOL_NAME,tcourse.CLASS_ID,clsmstr.CLASS_NAME,tcourse.HRM_ID,hmstr.HRM_NAME FROM teacher_courses tcourse INNER JOIN teacher_course_sessions tc_sess ON tc_sess.TEACHER_COURSE_ID=tcourse.TEACHER_COURSE_ID INNER JOIN teacher_dtls teacher ON teacher.EMAIL_ID=tcourse.TEACHER_ID LEFT JOIN course_mstr cmstr ON  tcourse.COURSE_ID= cmstr.COURSE_ID LEFT JOIN school_mstr smstr on smstr.SCHOOL_ID=tcourse.SCHOOL_ID LEFT JOIN class_mstr clsmstr on clsmstr.CLASS_ID=tcourse.CLASS_ID LEFT JOIN homeroom_mstr hmstr on hmstr.HRM_ID=tcourse.HRM_ID where teacher.USER_ID =").append(userId);
            if(courseId>0)
            	sql.append(" AND tcourse.COURSE_ID = ").append(courseId);
            if(schoolId>0)
            	sql.append(" AND tcourse.SCHOOL_ID = ").append(schoolId);
            if(classId>0)
            	sql.append(" AND tcourse.CLASS_ID = ").append(classId);
            if(hrmId>0)
            	sql.append(" AND tcourse.HRM_ID = ").append(hrmId);
            
            System.out.println("Generated query : "+sql);
            stmt = conn.prepareStatement(sql.toString());
            rs = stmt.executeQuery();
            CourseVO vo = null;
            while (rs.next()) {
                vo = new CourseVO();
                vo.setCourseId(rs.getString(1));
                vo.setCourseName(rs.getString(2));
                vo.setAuthorName(rs.getString(3));
                vo.setAuthorImg(rs.getString(4));
                vo.setStartedOn(rs.getString(5));
                vo.setCompletedOn(rs.getString(6));
                vo.setCompletedStatus(rs.getString(7));
                vo.setCourseSessionId(rs.getInt(8));   //use to get modules list
                vo.setSchoolId(rs.getInt(9));
                vo.setSchoolName(rs.getString(10));
                vo.setClassId(rs.getInt(11));
                vo.setClasseName(rs.getString(12));
                vo.setHrmId(rs.getInt(13));
                vo.setHrmName(rs.getString(14));
                
                list.add(vo);
            }

        } catch (SQLException se) {
            System.out.println("getTeacherCourses # " + se);
            throw new LmsDaoException(se.getMessage());
        } catch (Exception e) {
            System.out.println("getTeacherCourses # " + e);
            throw new LmsDaoException(e.getMessage());
        } finally {
            closeResources(conn, stmt, rs);
        }

        return list;
    }
    

    
    @Override
    public List<CourseVO> getStudentCourses(int userId, String searchText) throws LmsDaoException {
        List<CourseVO> list = new ArrayList<CourseVO>();

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();

            /**
             * Student Courses - > user_cls_map + clas_course_map 
             * 
             * Query = SELECT cmstr.COURSE_ID,cmstr.COURSE_NAME,tc_sess.START_SESSION_TM,tc_sess.IS_COMPLETE,tc_sess.COURSE_SESSION_ID FROM user_cls_map ucmap INNER JOIN clas_course_map cc_map ON cc_map.CLASS_ID=ucmap.CLASS_ID INNER JOIN course_mstr cmstr ON cmstr.COURSE_ID=cc_map.COURSE_ID INNER JOIN teacher_courses tcourse ON tcourse.COURSE_ID= cc_map.COURSE_ID AND tcourse.CLASS_ID=ucmap.CLASS_ID INNER JOIN teacher_course_sessions tc_sess ON tc_sess.TEACHER_COURSE_ID=tcourse.TEACHER_COURSE_ID where ucmap.USER_ID = ? AND cmstr.METADATA like ?
             */
            
            //String sql = "SELECT cmstr.COURSE_ID,cmstr.COURSE_NAME,tc_sess.START_SESSION_TM,tc_sess.IS_COMPLETE,tc_sess.COURSE_SESSION_ID FROM user_login ulogin INNER JOIN user_course_map ucmap ON ulogin.USER_ID=ucmap.USER_ID INNER JOIN course_mstr cmstr ON cmstr.COURSE_ID=ucmap.COURSE_ID INNER JOIN teacher_courses tcourse ON tcourse.COURSE_ID= ucmap.COURSE_ID INNER JOIN teacher_course_sessions tc_sess ON tc_sess.TEACHER_COURSE_ID=tcourse.TEACHER_COURSE_ID where ulogin.USER_ID = ? AND cmstr.METADATA like ?";
            String sql = "SELECT cmstr.COURSE_ID,cmstr.COURSE_NAME,cmstr.COURSE_AUTHOR,cmstr.AUTHOR_IMG,tc_sess.START_SESSION_TM,tc_sess.END_SESSION_TM,tc_sess.IS_COMPLETE,tc_sess.COURSE_SESSION_ID FROM user_cls_map ucmap INNER JOIN clas_course_map cc_map ON cc_map.CLASS_ID=ucmap.CLASS_ID INNER JOIN course_mstr cmstr ON cmstr.COURSE_ID=cc_map.COURSE_ID INNER JOIN teacher_courses tcourse ON tcourse.COURSE_ID= cc_map.COURSE_ID AND tcourse.CLASS_ID=ucmap.CLASS_ID INNER JOIN teacher_course_sessions tc_sess ON tc_sess.TEACHER_COURSE_ID=tcourse.TEACHER_COURSE_ID where ucmap.USER_ID = ? AND cmstr.METADATA like ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, userId);
            stmt.setString(2, "%"+searchText+"%");
                        
            rs = stmt.executeQuery();
            CourseVO vo = null;
            while (rs.next()) {
                vo = new CourseVO();
                vo.setCourseId(rs.getString(1));
                vo.setCourseName(rs.getString(2));
                vo.setAuthorName(rs.getString(3));
                vo.setAuthorImg(rs.getString(4));
                vo.setStartedOn(rs.getString(5));
                vo.setCompletedOn(rs.getString(6));
                vo.setCompletedStatus(rs.getString(7));
                vo.setCourseSessionId(rs.getInt(8));
                list.add(vo);
            }

        } catch (SQLException se) {
            System.out.println("getStudentCourses # " + se);
            throw new LmsDaoException(se.getMessage());
        } catch (Exception e) {
            System.out.println("getStudentCourses # " + e);
            throw new LmsDaoException(e.getMessage());
        } finally {
            closeResources(conn, stmt, rs);
        }

        return list;
    }
    


    
    @Override
    public CourseVO getStudentModuleDetail(int moduleId) throws LmsDaoException {
        CourseVO vo = null;

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();

            String sql = "SELECT modul.MODULE_ID,MODULE_NAME,tcs_dtl.START_SESSION_TM,tcs_dtl.IS_COMPLETED,(SELECT count(*) FROM teacher_module_session_dtls where IS_COMPLETED='1' and COURSE_SESSION_DTLS_ID=tcs_dtl.COURSE_SESSION_DTLS_ID)/(SELECT count(*) FROM teacher_module_session_dtls where COURSE_SESSION_DTLS_ID=tcs_dtl.COURSE_SESSION_DTLS_ID) as completedPercent,(SELECT COURSE_ID FROM course_module_map where MODULE_ID=modul.MODULE_ID limit 1) as course_id FROM module_mstr modul INNER JOIN teacher_course_session_dtls tcs_dtl ON modul.MODULE_ID=tcs_dtl.MODULE_ID INNER JOIN teacher_course_sessions tc_sess ON tc_sess.COURSE_SESSION_ID=tcs_dtl.COURSE_SESSION_ID WHERE tcs_dtl.MODULE_ID = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, moduleId);
            
            rs = stmt.executeQuery();
           
            if(rs.next()) {
                vo = new CourseVO();
                vo.setModuleId(rs.getString(1));
                vo.setModuleName(rs.getString(2));
                vo.setStartedOn(rs.getString(3));
                vo.setCompletedStatus(rs.getString(4));
                vo.setCompletedPercentStatus(String.valueOf(rs.getInt(5)*100));
                vo.setCourseId(rs.getString(6));
            }

        } catch (SQLException se) {
            System.out.println("getStudentModuleDetail # " + se);
            throw new LmsDaoException(se.getMessage());
        } catch (Exception e) {
            System.out.println("getStudentModuleDetail # " + e);
            throw new LmsDaoException(e.getMessage());
        } finally {
            closeResources(conn, stmt, rs);
        }

        return vo;
    }
    
    
    
    @Override
    public List<CourseVO> getStudentCoursesModules(int courseSessionId) throws LmsDaoException {
        List<CourseVO> list = new ArrayList<CourseVO>();

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();

            String sql = "SELECT modul.MODULE_ID,MODULE_NAME,tcs_dtl.START_SESSION_TM,tcs_dtl.END_SESSION_TM,tcs_dtl.IS_COMPLETED,(SELECT count(*) FROM teacher_module_session_dtls where IS_COMPLETED='1' and COURSE_SESSION_DTLS_ID=tcs_dtl.COURSE_SESSION_DTLS_ID)/(SELECT count(*) FROM teacher_module_session_dtls where COURSE_SESSION_DTLS_ID=tcs_dtl.COURSE_SESSION_DTLS_ID) as completedPercent,tcs_dtl.COURSE_SESSION_DTLS_ID FROM module_mstr modul INNER JOIN teacher_course_session_dtls tcs_dtl ON modul.MODULE_ID=tcs_dtl.MODULE_ID INNER JOIN teacher_course_sessions tc_sess ON tc_sess.COURSE_SESSION_ID=tcs_dtl.COURSE_SESSION_ID WHERE tc_sess.COURSE_SESSION_ID = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, courseSessionId);
            
            rs = stmt.executeQuery();
            CourseVO vo = null;
            while (rs.next()) {
                vo = new CourseVO();
                vo.setModuleId(rs.getString(1));
                vo.setModuleName(rs.getString(2));
                vo.setStartedOn(rs.getString(3));
                vo.setCompletedOn(rs.getString(4));
                vo.setCompletedStatus(rs.getString(5));
                vo.setCompletedPercentStatus(String.valueOf(rs.getInt(6)*100));
                vo.setModuleSessionId(rs.getInt(7)); //Teacher module session id
                
                list.add(vo);
            }

        } catch (SQLException se) {
            System.out.println("getStudentCoursesModules # " + se);
            throw new LmsDaoException(se.getMessage());
        } catch (Exception e) {
            System.out.println("getStudentCoursesModules # " + e);
            throw new LmsDaoException(e.getMessage());
        } finally {
            closeResources(conn, stmt, rs);
        }

        return list;
    }
    

    @Override
    public List<CourseVO> getTeacherCoursesModules(int courseSessionId) throws LmsDaoException {
        List<CourseVO> list = new ArrayList<CourseVO>();

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();

            String sql = "SELECT modul.MODULE_ID,MODULE_NAME,tcs_dtl.START_SESSION_TM,tcs_dtl.END_SESSION_TM,tcs_dtl.IS_COMPLETED,(SELECT count(*) FROM teacher_module_session_dtls where IS_COMPLETED='1' and COURSE_SESSION_DTLS_ID=tcs_dtl.COURSE_SESSION_DTLS_ID)/(SELECT count(*) FROM teacher_module_session_dtls where COURSE_SESSION_DTLS_ID=tcs_dtl.COURSE_SESSION_DTLS_ID) as completedPercent,tcs_dtl.COURSE_SESSION_DTLS_ID FROM module_mstr modul RIGHT JOIN teacher_course_session_dtls tcs_dtl ON modul.MODULE_ID=tcs_dtl.MODULE_ID INNER JOIN teacher_course_sessions tc_sess ON tc_sess.COURSE_SESSION_ID=tcs_dtl.COURSE_SESSION_ID WHERE tc_sess.COURSE_SESSION_ID = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, courseSessionId);
            
            rs = stmt.executeQuery();
            CourseVO vo = null;
            while (rs.next()) {
                vo = new CourseVO();
                vo.setModuleId(rs.getString(1));
                vo.setModuleName(rs.getString(2));
                vo.setStartedOn(rs.getString(3));
                vo.setCompletedOn(rs.getString(4));
                vo.setCompletedStatus(rs.getString(5));
                vo.setCompletedPercentStatus(String.valueOf(rs.getInt(6)*100));
                vo.setModuleSessionId(rs.getInt(7)); //teacher Module session id
                
                list.add(vo);
            }

        } catch (SQLException se) {
            System.out.println("getStudentCoursesModules # " + se);
            throw new LmsDaoException(se.getMessage());
        } catch (Exception e) {
            System.out.println("getStudentCoursesModules # " + e);
            throw new LmsDaoException(e.getMessage());
        } finally {
            closeResources(conn, stmt, rs);
        }

        return list;
    }


    @Override
    public List<CourseVO> getTeacherCoursesModules(int courseSessionId,int moduleId) throws LmsDaoException {
        List<CourseVO> list = new ArrayList<CourseVO>();

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();

            String sql = "SELECT modul.MODULE_ID,MODULE_NAME,tcs_dtl.START_SESSION_TM,tcs_dtl.END_SESSION_TM,tcs_dtl.IS_COMPLETED,(SELECT count(*) FROM teacher_module_session_dtls where IS_COMPLETED='1' and COURSE_SESSION_DTLS_ID=tcs_dtl.COURSE_SESSION_DTLS_ID)/(SELECT count(*) FROM teacher_module_session_dtls where COURSE_SESSION_DTLS_ID=tcs_dtl.COURSE_SESSION_DTLS_ID) as completedPercent,tcs_dtl.COURSE_SESSION_DTLS_ID FROM module_mstr modul RIGHT JOIN teacher_course_session_dtls tcs_dtl ON modul.MODULE_ID=tcs_dtl.MODULE_ID INNER JOIN teacher_course_sessions tc_sess ON tc_sess.COURSE_SESSION_ID=tcs_dtl.COURSE_SESSION_ID WHERE tc_sess.COURSE_SESSION_ID = ? AND modul.MODULE_ID=? ";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, courseSessionId);
            stmt.setInt(2, moduleId);
            
            rs = stmt.executeQuery();
            CourseVO vo = null;
            while (rs.next()) {
                vo = new CourseVO();
                vo.setModuleId(rs.getString(1));
                vo.setModuleName(rs.getString(2));
                vo.setStartedOn(rs.getString(3));
                vo.setCompletedOn(rs.getString(4));
                vo.setCompletedStatus(rs.getString(5));
                vo.setCompletedPercentStatus(String.valueOf(rs.getInt(6)*100));
                vo.setModuleSessionId(rs.getInt(7)); //teacher Module session id
                
                list.add(vo);
            }

        } catch (SQLException se) {
            System.out.println("getStudentCoursesModules(?,?) # " + se);
            throw new LmsDaoException(se.getMessage());
        } catch (Exception e) {
            System.out.println("getStudentCoursesModules(?,?) # " + e);
            throw new LmsDaoException(e.getMessage());
        } finally {
            closeResources(conn, stmt, rs);
        }

        return list;
    }


    
    @Override
    public List<ResourseVO> getStudentResources(int moduleId) throws LmsDaoException {
        List<ResourseVO> list = new ArrayList<ResourseVO>();

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs =null;
        try {
            conn = getConnection();
            
            /**
             * SELECT rc_mstr.RESOURSE_ID,rc_mstr.RESOURSE_NAME,rc_mstr.DESC_TXT,rc_mstr.RESOURCE_AUTHOR,tc_sess_dtl.START_SESSION_TM,tc_sess_dtl.END_SESSION_TM,(SELECT count(*) FROM resource_comments where RESOURCE_ID=rc_mstr.RESOURSE_ID),(SELECT count(*) FROM resource_likes where RESOURCE_ID=rc_mstr.RESOURSE_ID) FROM teacher_courses tc INNER JOIN teacher_course_sessions tc_sess ON tc.TEACHER_COURSE_ID=tc_sess.TEACHER_COURSE_ID INNER JOIN teacher_course_session_dtls tc_sess_dtl ON tc_sess_dtl.COURSE_SESSION_ID=tc_sess.COURSE_SESSION_ID INNER JOIN user_cls_map ucm ON tc.CLASS_ID = ucm.CLASS_ID AND ucm.SCHOOL_ID=tc.SCHOOL_ID INNER JOIN clas_course_map ccm on ccm.CLASS_ID=ucm.CLASS_ID AND tc.COURSE_ID=ccm.COURSE_ID INNER JOIN course_module_map cmm on cmm.COURSE_ID=ccm.COURSE_ID INNER JOIN module_resource_map mrm on mrm.MODULE_ID=cmm.MODULE_ID AND tc_sess_dtl.MODULE_ID=mrm.MODULE_ID INNER JOIN resourse_mstr rc_mstr on rc_mstr.RESOURSE_ID=mrm.RESOURCE_ID WHERE tc_sess_dtl.MODULE_ID=?
             */
            String sql = "SELECT rc_mstr.RESOURSE_ID,rc_mstr.RESOURSE_NAME,rc_mstr.DESC_TXT,rc_mstr.RESOURCE_AUTHOR,rc_mstr.THUMB_IMG,rc_mstr.RESOURCE_URL,rc_mstr.AUTHOR_IMG,tc_sess_dtl.START_SESSION_TM,tc_sess_dtl.END_SESSION_TM,(SELECT count(*) FROM resource_comments where RESOURCE_ID=rc_mstr.RESOURSE_ID and PARENT_COMMENT_ID is null) as commentCount,(SELECT count(*) FROM resource_likes where RESOURCE_ID=rc_mstr.RESOURSE_ID and PARENT_COMMENT_ID is null) as resourceCount FROM teacher_courses tc INNER JOIN teacher_course_sessions tc_sess ON tc.TEACHER_COURSE_ID=tc_sess.TEACHER_COURSE_ID INNER JOIN teacher_course_session_dtls tc_sess_dtl ON tc_sess_dtl.COURSE_SESSION_ID=tc_sess.COURSE_SESSION_ID INNER JOIN user_cls_map ucm ON tc.CLASS_ID = ucm.CLASS_ID AND ucm.SCHOOL_ID=tc.SCHOOL_ID INNER JOIN clas_course_map ccm on ccm.CLASS_ID=ucm.CLASS_ID AND tc.COURSE_ID=ccm.COURSE_ID INNER JOIN course_module_map cmm on cmm.COURSE_ID=ccm.COURSE_ID INNER JOIN module_resource_map mrm on mrm.MODULE_ID=cmm.MODULE_ID AND tc_sess_dtl.MODULE_ID=mrm.MODULE_ID INNER JOIN resourse_mstr rc_mstr on rc_mstr.RESOURSE_ID=mrm.RESOURCE_ID WHERE tc_sess_dtl.MODULE_ID=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1,moduleId);
            
            rs = stmt.executeQuery();
            ResourseVO vo = null;
            while (rs.next()) {
                vo = new ResourseVO();
                vo.setResourceId(rs.getInt(1));
                vo.setResourceName(rs.getString(2));
                vo.setResourceDesc(rs.getString(3));
                vo.setAuthorName(rs.getString(4));
                vo.setThumbUrl(rs.getString(5));
                vo.setResourceUrl(rs.getString(6));
                vo.setAuthorImg(rs.getString(7));
                
                vo.setStartedOn(rs.getString(8));
                vo.setCompletedOn(rs.getString(9));
                
                vo.setCommentCounts(rs.getInt(10));
                vo.setLikeCounts(rs.getInt(11));
                vo.setShareCounts(0);
                vo.setIsLiked(false); //Need to update further to make it dynamic
                
                list.add(vo);
            }

        } catch (SQLException se) {
            System.out.println("getStudentResources (?)# " + se);
            throw new LmsDaoException(se.getMessage());
        } catch (Exception e) {
            System.out.println("getStudentResources # (?)" + e);
            throw new LmsDaoException(e.getMessage());
        } finally {
            closeResources(conn, stmt, rs);
        }

        return list;
    }

    
    @Override
    public List<ResourseVO> getStudentResources(int userId, int courseId, int moduleId, String searchText) throws LmsDaoException {
        List<ResourseVO> list = new ArrayList<ResourseVO>();

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs =null;
        try {
            conn = getConnection();
            
            /**
             * SELECT rc_mstr.RESOURSE_ID,rc_mstr.RESOURSE_NAME,rc_mstr.DESC_TXT,rc_mstr.RESOURCE_AUTHOR,tc_sess_dtl.START_SESSION_TM,tc_sess_dtl.END_SESSION_TM,(SELECT count(*) FROM resource_comments where RESOURCE_ID=rc_mstr.RESOURSE_ID),(SELECT count(*) FROM resource_likes where RESOURCE_ID=rc_mstr.RESOURSE_ID) FROM teacher_courses tc INNER JOIN teacher_course_sessions tc_sess ON tc.TEACHER_COURSE_ID=tc_sess.TEACHER_COURSE_ID INNER JOIN teacher_course_session_dtls tc_sess_dtl ON tc_sess_dtl.COURSE_SESSION_ID=tc_sess.COURSE_SESSION_ID INNER JOIN user_cls_map ucm ON tc.CLASS_ID = ucm.CLASS_ID AND ucm.SCHOOL_ID=tc.SCHOOL_ID INNER JOIN clas_course_map ccm on ccm.CLASS_ID=ucm.CLASS_ID AND tc.COURSE_ID=ccm.COURSE_ID INNER JOIN course_module_map cmm on cmm.COURSE_ID=ccm.COURSE_ID INNER JOIN module_resource_map mrm on mrm.MODULE_ID=cmm.MODULE_ID AND tc_sess_dtl.MODULE_ID=mrm.MODULE_ID INNER JOIN resourse_mstr rc_mstr on rc_mstr.RESOURSE_ID=mrm.RESOURCE_ID WHERE ucm.USER_ID =? AND ccm.COURSE_ID = ? AND tc_sess_dtl.MODULE_ID=? AND rc_mstr.METADATA like ?
             */
            String sql = "SELECT rc_mstr.RESOURSE_ID,rc_mstr.RESOURSE_NAME,rc_mstr.DESC_TXT,rc_mstr.RESOURCE_AUTHOR,rc_mstr.THUMB_IMG,rc_mstr.RESOURCE_URL,rc_mstr.AUTHOR_IMG,tc_sess_dtl.START_SESSION_TM,tc_sess_dtl.END_SESSION_TM,(SELECT count(*) FROM resource_comments where RESOURCE_ID=rc_mstr.RESOURSE_ID and PARENT_COMMENT_ID is null) as commentCount,(SELECT count(*) FROM resource_likes where RESOURCE_ID=rc_mstr.RESOURSE_ID and PARENT_COMMENT_ID is null) as resourceCount,(SELECT count(*) FROM resource_likes where RESOURCE_ID=rc_mstr.RESOURSE_ID and PARENT_COMMENT_ID is null and LIKE_BY=(SELECT USER_NM FROM user_login where USER_ID=?)) AS isliked FROM teacher_courses tc INNER JOIN teacher_course_sessions tc_sess ON tc.TEACHER_COURSE_ID=tc_sess.TEACHER_COURSE_ID INNER JOIN teacher_course_session_dtls tc_sess_dtl ON tc_sess_dtl.COURSE_SESSION_ID=tc_sess.COURSE_SESSION_ID INNER JOIN teacher_module_session_dtls tc_mod_dtl ON tc_mod_dtl.COURSE_SESSION_DTLS_ID=tc_sess_dtl.COURSE_SESSION_DTLS_ID INNER JOIN user_cls_map ucm ON tc.CLASS_ID = ucm.CLASS_ID AND ucm.SCHOOL_ID=tc.SCHOOL_ID AND ucm.HRM_ID=tc.HRM_ID LEFT JOIN resourse_mstr rc_mstr on rc_mstr.RESOURSE_ID=tc_mod_dtl.CONTENT_ID WHERE ucm.USER_ID =? AND tc.COURSE_ID =? AND tc_sess_dtl.MODULE_ID=? AND rc_mstr.METADATA like ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1,userId);
            stmt.setInt(2,userId);
            stmt.setInt(3,courseId);
            stmt.setInt(4,moduleId);
            stmt.setString(5,"%"+searchText+"%");
            
            rs = stmt.executeQuery();
            ResourseVO vo = null;
            while (rs.next()) {
                vo = new ResourseVO();
                vo.setResourceId(rs.getInt(1));
                vo.setResourceName(rs.getString(2));
                vo.setResourceDesc(rs.getString(3));
                vo.setAuthorName(rs.getString(4));
                vo.setThumbUrl(rs.getString(5));
                vo.setResourceUrl(rs.getString(6));
                vo.setAuthorImg(rs.getString(7));
                
                vo.setStartedOn(rs.getString(8));
                vo.setCompletedOn(rs.getString(9));
                
                vo.setCommentCounts(rs.getInt(10));
                vo.setLikeCounts(rs.getInt(11));
                vo.setShareCounts(0);
                if((rs.getInt(12)) > 0)
                {
                	vo.setIsLiked(true); //Need to update further to make it dynamic
                }
                else
                {
                	vo.setIsLiked(false);
                }
                list.add(vo);
            }

        } catch (SQLException se) {
            System.out.println("getStudentResources # " + se);
            throw new LmsDaoException(se.getMessage());
        } catch (Exception e) {
            System.out.println("getStudentResources # " + e);
            throw new LmsDaoException(e.getMessage());
        } finally {
            closeResources(conn, stmt, rs);
        }

        return list;
    }
    
    
    @Override
    public List<ResourseVO> getStudentResources(int courseId, int moduleId) throws LmsDaoException {
        List<ResourseVO> list = new ArrayList<ResourseVO>();

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs =null;
        try {
            conn = getConnection();
            
            /**
             * SELECT rc_mstr.RESOURSE_ID,rc_mstr.RESOURSE_NAME,rc_mstr.DESC_TXT,rc_mstr.RESOURCE_AUTHOR,tc_sess_dtl.START_SESSION_TM,tc_sess_dtl.END_SESSION_TM,(SELECT count(*) FROM resource_comments where RESOURCE_ID=rc_mstr.RESOURSE_ID),(SELECT count(*) FROM resource_likes where RESOURCE_ID=rc_mstr.RESOURSE_ID) FROM teacher_courses tc INNER JOIN teacher_course_sessions tc_sess ON tc.TEACHER_COURSE_ID=tc_sess.TEACHER_COURSE_ID INNER JOIN teacher_course_session_dtls tc_sess_dtl ON tc_sess_dtl.COURSE_SESSION_ID=tc_sess.COURSE_SESSION_ID INNER JOIN user_cls_map ucm ON tc.CLASS_ID = ucm.CLASS_ID AND ucm.SCHOOL_ID=tc.SCHOOL_ID INNER JOIN clas_course_map ccm on ccm.CLASS_ID=ucm.CLASS_ID AND tc.COURSE_ID=ccm.COURSE_ID INNER JOIN course_module_map cmm on cmm.COURSE_ID=ccm.COURSE_ID INNER JOIN module_resource_map mrm on mrm.MODULE_ID=cmm.MODULE_ID AND tc_sess_dtl.MODULE_ID=mrm.MODULE_ID INNER JOIN resourse_mstr rc_mstr on rc_mstr.RESOURSE_ID=mrm.RESOURCE_ID WHERE ucm.USER_ID =? AND ccm.COURSE_ID = ? AND tc_sess_dtl.MODULE_ID=? AND rc_mstr.METADATA like ?
             */
            String sql = "SELECT rc_mstr.RESOURSE_ID,rc_mstr.RESOURSE_NAME,rc_mstr.DESC_TXT,rc_mstr.RESOURCE_AUTHOR,rc_mstr.THUMB_IMG,rc_mstr.RESOURCE_URL,rc_mstr.AUTHOR_IMG,tc_sess_dtl.START_SESSION_TM,tc_sess_dtl.END_SESSION_TM,(SELECT count(*) FROM resource_comments where RESOURCE_ID=rc_mstr.RESOURSE_ID),(SELECT count(*) FROM resource_likes where RESOURCE_ID=rc_mstr.RESOURSE_ID) FROM teacher_courses tc INNER JOIN teacher_course_sessions tc_sess ON tc.TEACHER_COURSE_ID=tc_sess.TEACHER_COURSE_ID INNER JOIN teacher_course_session_dtls tc_sess_dtl ON tc_sess_dtl.COURSE_SESSION_ID=tc_sess.COURSE_SESSION_ID INNER JOIN user_cls_map ucm ON tc.CLASS_ID = ucm.CLASS_ID AND ucm.SCHOOL_ID=tc.SCHOOL_ID INNER JOIN clas_course_map ccm on ccm.CLASS_ID=ucm.CLASS_ID AND tc.COURSE_ID=ccm.COURSE_ID INNER JOIN course_module_map cmm on cmm.COURSE_ID=ccm.COURSE_ID INNER JOIN module_resource_map mrm on mrm.MODULE_ID=cmm.MODULE_ID AND tc_sess_dtl.MODULE_ID=mrm.MODULE_ID INNER JOIN resourse_mstr rc_mstr on rc_mstr.RESOURSE_ID=mrm.RESOURCE_ID WHERE ccm.COURSE_ID = ? AND tc_sess_dtl.MODULE_ID=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1,courseId);
            stmt.setInt(2,moduleId);
            
            rs = stmt.executeQuery();
            ResourseVO vo = null;
            while (rs.next()) {
                vo = new ResourseVO();
                vo.setResourceId(rs.getInt(1));
                vo.setResourceName(rs.getString(2));
                vo.setResourceDesc(rs.getString(3));
                vo.setAuthorName(rs.getString(4));
                vo.setThumbUrl(rs.getString(5));
                vo.setResourceUrl(rs.getString(6));
                vo.setAuthorImg(rs.getString(7));
                
                vo.setStartedOn(rs.getString(8));
                vo.setCompletedOn(rs.getString(9));
                
                vo.setCommentCounts(rs.getInt(10));
                vo.setLikeCounts(rs.getInt(11));
                vo.setShareCounts(0);
                vo.setIsLiked(false); //Need to update further to make it dynamic
                
                list.add(vo);
            }

        } catch (SQLException se) {
            System.out.println("getStudentResources # " + se);
            throw new LmsDaoException(se.getMessage());
        } catch (Exception e) {
            System.out.println("getStudentResources # " + e);
            throw new LmsDaoException(e.getMessage());
        } finally {
            closeResources(conn, stmt, rs);
        }

        return list;
    }
    
    
    @Override
    public List<ResourseVO> getStudentResourcesWeb(int userId, String courseId, String moduleId, String searchText) throws LmsDaoException {
        List<ResourseVO> list = new ArrayList<ResourseVO>();

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs =null;
        try {
            conn = getConnection();
            
            /**
             * SELECT rc_mstr.RESOURSE_ID,rc_mstr.RESOURSE_NAME,rc_mstr.DESC_TXT,rc_mstr.RESOURCE_AUTHOR,rc_mstr.THUMB_IMG FROM user_cls_map ucm INNER JOIN school_cls_map scm on ucm.SCHOOL_ID=scm.SCHOOL_ID and ucm.CLASS_ID=scm.CLASS_ID INNER JOIN clas_course_map ccm on ccm.CLASS_ID=scm.CLASS_ID INNER JOIN course_module_map cmm on cmm.COURSE_ID=ccm.COURSE_ID INNER JOIN module_resource_map mrm on mrm.MODULE_ID=cmm.MODULE_ID INNER JOIN resourse_mstr rc_mstr on rc_mstr.RESOURSE_ID=mrm.RESOURCE_ID WHERE ucm.USER_ID = ? AND cmm.COURSE_ID = ? AND cmm.MODULE_ID=? AND rc_mstr.METADATA = ?
             */
           // String sql = "SELECT rc_mstr.RESOURSE_ID,rc_mstr.RESOURSE_NAME,rc_mstr.DESC_TXT,rc_mstr.RESOURCE_AUTHOR,rc_mstr.THUMB_IMG FROM teacher_courses tc INNER JOIN teacher_course_sessions tc_sess ON tc.TEACHER_COURSE_ID=tc_sess.TEACHER_COURSE_ID INNER JOIN teacher_course_session_dtls tc_sess_dtl ON tc_sess_dtl.COURSE_SESSION_ID=tc_sess.COURSE_SESSION_ID INNER JOIN user_course_map ucm ON ucm.COURSE_ID = tc.COURSE_ID INNER JOIN resourse_mstr rc_mstr on rc_mstr.RESOURSE_ID=tc_sess_dtl.CONTENT_ID WHERE ucm.USER_ID = ? AND ucm.COURSE_ID = ? AND tc_sess_dtl.MODULE_ID=? AND rc_mstr.METADATA like ?";
            String sql = "SELECT rc_mstr.RESOURSE_ID,rc_mstr.RESOURSE_NAME,rc_mstr.DESC_TXT,rc_mstr.RESOURCE_AUTHOR,rc_mstr.THUMB_IMG,rc_mstr.RESOURCE_URL,rc_mstr.AUTHOR_IMG FROM user_cls_map ucm INNER JOIN school_cls_map scm on ucm.SCHOOL_ID=scm.SCHOOL_ID and ucm.CLASS_ID=scm.CLASS_ID INNER JOIN clas_course_map ccm on ccm.CLASS_ID=scm.CLASS_ID INNER JOIN course_module_map cmm on cmm.COURSE_ID=ccm.COURSE_ID INNER JOIN module_resource_map mrm on mrm.MODULE_ID=cmm.MODULE_ID INNER JOIN resourse_mstr rc_mstr on rc_mstr.RESOURSE_ID=mrm.RESOURCE_ID WHERE ucm.USER_ID = ? AND cmm.COURSE_ID = ? AND cmm.MODULE_ID=? AND rc_mstr.METADATA like ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1,userId);
            stmt.setString(2,courseId);
            stmt.setString(3,moduleId);
            stmt.setString(4,"%"+searchText+"%");
            
            rs = stmt.executeQuery();
            ResourseVO vo = null;
            while (rs.next()) {
                vo = new ResourseVO();
                vo.setResourceId(rs.getInt(1));
                vo.setResourceName(rs.getString(2));
                vo.setResourceDesc(rs.getString(3));
                vo.setAuthorName(rs.getString(4));
                vo.setThumbUrl(rs.getString(5));
                vo.setResourceUrl(rs.getString(6));
                vo.setAuthorImg(rs.getString(7));
                
                list.add(vo);
            }

        } catch (SQLException se) {
            System.out.println("getStudentResourcesWeb # " + se);
            throw new LmsDaoException(se.getMessage());
        } catch (Exception e) {
            System.out.println("getStudentResourcesWeb # " + e);
            throw new LmsDaoException(e.getMessage());
        } finally {
            closeResources(conn, stmt, rs);
        }

        return list;
    }
    
    
    
    @Override
    public List<ResourseVO> getTeacherModuleResources(int moduleSessionId) throws LmsDaoException {
        List<ResourseVO> list = new ArrayList<ResourseVO>();

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs =null;
        try {
            conn = getConnection();

            String sql = "SELECT rc_mstr.RESOURSE_ID,rc_mstr.RESOURSE_NAME,rc_mstr.DESC_TXT,rc_mstr.RESOURCE_AUTHOR,rc_mstr.THUMB_IMG,rc_mstr.RESOURCE_URL,rc_mstr.AUTHOR_IMG,msession.START_SESSION_TM,msession.END_SESSION_TM,msession.IS_COMPLETED,msession.MODULE_SESSION_DTLS_ID FROM resourse_mstr rc_mstr RIGHT JOIN teacher_module_session_dtls msession on msession.CONTENT_ID=rc_mstr.RESOURSE_ID where msession.COURSE_SESSION_DTLS_ID = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1,moduleSessionId);
            
            rs = stmt.executeQuery();
            ResourseVO vo = null;
            while (rs.next()) {
                vo = new ResourseVO();
                vo.setResourceId(rs.getInt(1));
                vo.setResourceName(rs.getString(2));
                vo.setResourceDesc(rs.getString(3));
                vo.setAuthorName(rs.getString(4));
                vo.setThumbUrl(rs.getString(5));
                vo.setResourceUrl(rs.getString(6));
                vo.setAuthorImg(rs.getString(7));
                vo.setStartedOn(rs.getString(8));
                vo.setCompletedOn(rs.getString(9));
                vo.setCompletedStatus(rs.getString(10));
                vo.setResourceSessionId(rs.getInt(11));
                
                list.add(vo);
            }

        } catch (SQLException se) {
            System.out.println("getTeacherModuleResources # " + se);
            throw new LmsDaoException(se.getMessage());
        } catch (Exception e) {
            System.out.println("getTeacherModuleResources # " + e);
            throw new LmsDaoException(e.getMessage());
        } finally {
            closeResources(conn, stmt, rs);
        }

        return list;
    }

    
    @Override
    public List<CommentVO> getResourceChildCommentsList(int userId,int commentId) throws LmsDaoException {
    	 List<CommentVO> list = new ArrayList<CommentVO>();

         Connection conn = null;
         PreparedStatement stmt = null;
         ResultSet rs =null;
         try {
             conn = getConnection();

             /**
              *SELECT RESOURCE_COMMENT_ID,COMMENT_TXT,PARENT_COMMENT_ID,COMMENTED_BY,LAST_UPDT_TM FROM resource_comments where RESOURCE _ID=? 
              */
             String sql="SELECT temp.RESOURCE_COMMENT_ID,temp.COMMENT_TXT,temp.PARENT_COMMENT_ID,CONCAT(stdl.FNAME,' ',stdl.LNAME)AS USERNAME,stdl.USER_ID,stdl.PROFILE_IMG,temp.LAST_UPDT_TM,(SELECT count(*) FROM resource_comments where RESOURCE_ID=temp.RESOURCE_ID and PARENT_COMMENT_ID=temp.RESOURCE_COMMENT_ID) as commentCount,(SELECT count(*) FROM resource_likes where RESOURCE_ID=temp.RESOURCE_ID and PARENT_COMMENT_ID=temp.RESOURCE_COMMENT_ID) as likeCount,(SELECT COUNT(*) FROM resource_likes INNER JOIN user_login WHERE RESOURCE_ID=temp.RESOURCE_ID and PARENT_COMMENT_ID=temp.RESOURCE_COMMENT_ID AND LIKE_BY=(SELECT USER_NM FROM user_login where USER_ID=?)) AS isliked FROM resource_comments temp INNER JOIN student_dtls stdl  ON temp.COMMENTED_BY=stdl.EMAIL_ID where temp.PARENT_COMMENT_ID=? order by temp.LAST_UPDT_TM desc";             stmt = conn.prepareStatement(sql);
             stmt.setInt(1, userId);
             stmt.setInt(2, commentId);

             rs = stmt.executeQuery();
             CommentVO vo = null;
             while (rs.next()) {
                 vo = new CommentVO();
                 vo.setCommentId(rs.getInt(1));
                 vo.setCommentTxt(rs.getString(2));
                 vo.setParentCommentId(rs.getInt(3));
                 vo.setCommentBy(rs.getString(4));
                 vo.setCommentById(rs.getInt(5));
                 vo.setCommentByImage(rs.getString(6));
                 vo.setCommentDate(rs.getString(7));
                 
                 vo.setCommentCounts(rs.getInt(8));
                 vo.setLikeCounts(rs.getInt(9));
                 vo.setShareCounts(0);
                 if((rs.getInt(10))>0)
                 {
                	 vo.setIsLiked(true);
                 }
                 else
                 {
                 vo.setIsLiked(false); //Need to update later for dynamic update
                 }
                 list.add(vo);
             }

         } catch (SQLException se) {
             System.out.println("getResourceComments # " + se);
             throw new LmsDaoException(se.getMessage());
         } catch (Exception e) {
             System.out.println("getResourceComments # " + e);
             throw new LmsDaoException(e.getMessage());
         } finally {
             closeResources(conn, stmt, rs);
         }

         return list;
    }
    @Override
    public List<CommentVO> getResourceChildCommentsList(int commentId) throws LmsDaoException {
    	 List<CommentVO> list = new ArrayList<CommentVO>();

         Connection conn = null;
         PreparedStatement stmt = null;
         ResultSet rs =null;
         try {
             conn = getConnection();

             /**
              *SELECT RESOURCE_COMMENT_ID,COMMENT_TXT,PARENT_COMMENT_ID,COMMENTED_BY,LAST_UPDT_TM FROM resource_comments where RESOURCE _ID=? 
              */
             String sql="SELECT temp.RESOURCE_COMMENT_ID,temp.COMMENT_TXT,temp.PARENT_COMMENT_ID,CONCAT(stdl.FNAME,' ',stdl.LNAME)AS USERNAME,stdl.USER_ID,stdl.PROFILE_IMG,temp.LAST_UPDT_TM,(SELECT count(*) FROM resource_comments where PARENT_COMMENT_ID=temp.RESOURCE_COMMENT_ID) as commentCount,(SELECT count(*) FROM resource_likes where PARENT_COMMENT_ID=temp.RESOURCE_COMMENT_ID) as likeCount FROM resource_comments temp INNER JOIN student_dtls stdl  ON temp.COMMENTED_BY=stdl.EMAIL_ID where temp.PARENT_COMMENT_ID=? order by temp.LAST_UPDT_TM desc";
             stmt = conn.prepareStatement(sql);
             stmt.setInt(1, commentId);

             rs = stmt.executeQuery();
             CommentVO vo = null;
             while (rs.next()) {
                 vo = new CommentVO();
                 vo.setCommentId(rs.getInt(1));
                 vo.setCommentTxt(rs.getString(2));
                 vo.setParentCommentId(rs.getInt(3));
                 vo.setCommentBy(rs.getString(4));
                 vo.setCommentById(rs.getInt(5));
                 vo.setCommentByImage(rs.getString(6));
                 vo.setCommentDate(rs.getString(7));
                 
                 vo.setCommentCounts(rs.getInt(8));
                 vo.setLikeCounts(rs.getInt(9));
                 vo.setShareCounts(0);
                 vo.setIsLiked(false); //Need to update later for dynamic update
                 
                 list.add(vo);
             }

         } catch (SQLException se) {
             System.out.println("getResourceComments # " + se);
             throw new LmsDaoException(se.getMessage());
         } catch (Exception e) {
             System.out.println("getResourceComments # " + e);
             throw new LmsDaoException(e.getMessage());
         } finally {
             closeResources(conn, stmt, rs);
         }

         return list;
    }

    @Override
    public List<CommentVO> getResourceComments(int userId,int resourceId) throws LmsDaoException {
        List<CommentVO> list = new ArrayList<CommentVO>();

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs =null;
        try {
            conn = getConnection();

            /**
             *RESOURCE_COMMENT_ID     COMMENT_TXT     PARENT_COMMENT_ID     USERNAME      USER_ID     PROFILE_IMG             LAST_UPDT_TM          commentCount     likeCount     
             */
            String sql = "SELECT temp.RESOURCE_COMMENT_ID,temp.COMMENT_TXT,temp.PARENT_COMMENT_ID,CONCAT(stdl.FNAME,' ',stdl.LNAME)AS USERNAME,stdl.USER_ID,stdl.PROFILE_IMG,temp.LAST_UPDT_TM,(SELECT count(*) FROM resource_comments where RESOURCE_ID=temp.RESOURCE_ID and PARENT_COMMENT_ID =temp.RESOURCE_COMMENT_ID) as commentCount,(SELECT count(*) FROM resource_likes where RESOURCE_ID=temp.RESOURCE_ID and PARENT_COMMENT_ID =temp.RESOURCE_COMMENT_ID) as likeCount,(SELECT COUNT(*) FROM resource_likes INNER JOIN user_login WHERE RESOURCE_ID=temp.RESOURCE_ID and PARENT_COMMENT_ID=temp.RESOURCE_COMMENT_ID AND LIKE_BY=(SELECT USER_NM FROM user_login where USER_ID=?)) AS isliked  FROM resource_comments temp INNER JOIN student_dtls stdl  ON temp.COMMENTED_BY=stdl.EMAIL_ID where RESOURCE_ID=? and PARENT_COMMENT_ID is null order by temp.LAST_UPDT_TM desc";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, userId);
            stmt.setInt(2, resourceId);

            rs = stmt.executeQuery();
            CommentVO vo = null;
            while (rs.next()) {
                vo = new CommentVO();
                vo.setCommentId(rs.getInt(1));
                vo.setCommentTxt(rs.getString(2));
                vo.setParentCommentId(rs.getInt(3));
                vo.setCommentBy(rs.getString(4));
                vo.setCommentById(rs.getInt(5));
                vo.setCommentByImage(rs.getString(6));
                vo.setCommentDate(rs.getString(7));
                
                vo.setCommentCounts(rs.getInt(8));
                vo.setLikeCounts(rs.getInt(9));
                vo.setShareCounts(0);
                if((rs.getInt(10))>0)
                {
                vo.setIsLiked(true); //Need to update later for dynamic update
                }
                else
                {
                	vo.setIsLiked(false);
                }
                list.add(vo);
            }

        } catch (SQLException se) {
            System.out.println("getResourceComments # " + se);
            throw new LmsDaoException(se.getMessage());
        } catch (Exception e) {
            System.out.println("getResourceComments # " + e);
            throw new LmsDaoException(e.getMessage());
        } finally {
            closeResources(conn, stmt, rs);
        }

        return list;
    }

    @Override
    public List<CommentVO> getResourceComments(int resourceId) throws LmsDaoException {
        List<CommentVO> list = new ArrayList<CommentVO>();

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs =null;
        try {
            conn = getConnection();

            /**
             *RESOURCE_COMMENT_ID     COMMENT_TXT     PARENT_COMMENT_ID     USERNAME      USER_ID     PROFILE_IMG             LAST_UPDT_TM          commentCount     likeCount     
             */
            String sql = "SELECT temp.RESOURCE_COMMENT_ID,temp.COMMENT_TXT,temp.PARENT_COMMENT_ID,CONCAT(stdl.FNAME,' ',stdl.LNAME)AS USERNAME,stdl.USER_ID,stdl.PROFILE_IMG,temp.LAST_UPDT_TM,(SELECT count(*) FROM resource_comments where PARENT_COMMENT_ID=temp.RESOURCE_COMMENT_ID) as commentCount,(SELECT count(*) FROM resource_likes where PARENT_COMMENT_ID=temp.RESOURCE_COMMENT_ID) as likeCount FROM resource_comments temp INNER JOIN student_dtls stdl  ON temp.COMMENTED_BY=stdl.EMAIL_ID where RESOURCE_ID=? and PARENT_COMMENT_ID is null order by temp.LAST_UPDT_TM desc";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, resourceId);

            rs = stmt.executeQuery();
            CommentVO vo = null;
            while (rs.next()) {
                vo = new CommentVO();
                vo.setCommentId(rs.getInt(1));
                vo.setCommentTxt(rs.getString(2));
                vo.setParentCommentId(rs.getInt(3));
                vo.setCommentBy(rs.getString(4));
                vo.setCommentById(rs.getInt(5));
                vo.setCommentByImage(rs.getString(6));
                vo.setCommentDate(rs.getString(7));
                
                vo.setCommentCounts(rs.getInt(8));
                vo.setLikeCounts(rs.getInt(9));
                vo.setShareCounts(0);
                vo.setIsLiked(false); //Need to update later for dynamic update
                
                list.add(vo);
            }

        } catch (SQLException se) {
            System.out.println("getResourceComments # " + se);
            throw new LmsDaoException(se.getMessage());
        } catch (Exception e) {
            System.out.println("getResourceComments # " + e);
            throw new LmsDaoException(e.getMessage());
        } finally {
            closeResources(conn, stmt, rs);
        }

        return list;
    }

    
    @Override
    public List<ResourseVO> getRelatedResources(int resourceId) throws LmsDaoException {
        List<ResourseVO> list = new ArrayList<ResourseVO>();

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs =null;
        try {
            conn = getConnection();

            /**
             * SELECT RESOURSE_ID, RESOURSE_NAME, RESOURCE_AUTHOR, DESC_TXT, LAST_UPDT_TM FROM resourse_mstr where RESOURSE_ID=?
             * 
             */
            String sql = "SELECT RESOURSE_ID, RESOURSE_NAME, RESOURCE_AUTHOR, DESC_TXT, LAST_UPDT_TM,RESOURCE_URL, AUTHOR_IMG, THUMB_IMG, CREATED_BY FROM resourse_mstr where METADATA =(SELECT METADATA FROM resourse_mstr where RESOURSE_ID=?)";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, resourceId);
            
            rs = stmt.executeQuery();
            ResourseVO vo = null;
            while (rs.next()) {
                vo = new ResourseVO();
                vo.setResourceId(rs.getInt(1));
                vo.setResourceName(rs.getString(2));
                vo.setAuthorName(rs.getString(3));
                vo.setResourceDesc(rs.getString(4));
                vo.setUploadedDate(rs.getString(5));
                vo.setResourceUrl(rs.getString(6));
                vo.setAuthorImg(rs.getString(7));
                vo.setThumbUrl(rs.getString(8));
                vo.setUploadedBy(rs.getString(9));

                list.add(vo);
            }

        } catch (SQLException se) {
            System.out.println("getRelatedResources # " + se);
            throw new LmsDaoException(se.getMessage());
        } catch (Exception e) {
            System.out.println("getRelatedResources # " + e);
            throw new LmsDaoException(e.getMessage());
        } finally {
            closeResources(conn, stmt, rs);
        }

        return list;
    }
    
    @Override
    public List<AssignmentVO> getStudentAssignments(int courseId,int moduleId,int userId) throws LmsDaoException {
        List<AssignmentVO> list = new ArrayList<AssignmentVO>();

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs =null;
        try {
            conn = getConnection();

            /**
             * ASSIGNMENT_ID | ASSIGNMENT_NAME | ASSIGNMENT_STATUS | ASSIGNMENT_SUBMITTED_BY | ASSIGNMENT_SUBMITTED_DATE
             */
            
            String sql = "SELECT DISTINCT asgnmnt.ASSIGNMENT_ID,asgnmnt.ASSIGNMENT_NAME,txn.IS_COMPLETED,txn.STUDENT_ID,TIMESTAMP(txn.UPLOADED_ON),TIMESTAMP(txn.DUE_ON) FROM assignment_resource_txn txn INNER JOIN assignment asgnmnt ON txn.ASSIGNMENT_ID = asgnmnt.ASSIGNMENT_ID INNER JOIN module_assignment_map mod_assignment ON mod_assignment.ASSIGNMENT_ID=asgnmnt.ASSIGNMENT_ID INNER JOIN user_login ulogin ON ulogin.USER_NM=txn.STUDENT_ID where mod_assignment.MODULE_ID = ? and ulogin.USER_ID=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, moduleId);
            stmt.setInt(2, userId);
            
            rs = stmt.executeQuery();
            AssignmentVO vo = null;
            while (rs.next()) {
                vo = new AssignmentVO();
                vo.setAssignmentId(rs.getInt(1));
                vo.setAssignmentName(rs.getString(2));
                vo.setAssignmentStatus(rs.getString(3));
                vo.setAssignmentSubmittedBy(rs.getString(4));
                vo.setAssignmentSubmittedDate(rs.getString(5));

                list.add(vo);
            }

        } catch (SQLException se) {
            System.out.println("getStudentAssignments (?,?,?)# " + se);
            throw new LmsDaoException(se.getMessage());
        } catch (Exception e) {
            System.out.println("getStudentAssignments (?,?,?)# " + e);
            throw new LmsDaoException(e.getMessage());
        } finally {
            closeResources(conn, stmt, rs);
        }

        return list;
    }

    

    @Override
    public List<AssignmentVO> getStudentAssignments(int userId,String searchText) throws LmsDaoException {
        List<AssignmentVO> list = new ArrayList<AssignmentVO>();

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs =null;
        try {
            conn = getConnection();

            /**
             * ASSIGNMENT_ID | ASSIGNMENT_NAME | ASSIGNMENT_STATUS | ASSIGNMENT_SUBMITTED_BY | ASSIGNMENT_SUBMITTED_DATE |COURSE_ID | COURSE_NAME  |   MODULE_ID  |  MODULE_NAME    
             */
            
            //String sql = "SELECT DISTINCT asgnmnt.ASSIGNMENT_ID,asgnmnt.ASSIGNMENT_NAME,txn.IS_COMPLETED,txn.STUDENT_ID,TIMESTAMP(txn.UPLOADED_ON),cmstr.COURSE_ID,cmstr.COURSE_NAME,mmstr.MODULE_ID,mmstr.MODULE_NAME,asgnmnt.DESC_TXT,TIMESTAMP(txn.DUE_ON) FROM assignment_resource_txn txn INNER JOIN assignment asgnmnt ON txn.ASSIGNMENT_ID = asgnmnt.ASSIGNMENT_ID INNER JOIN module_assignment_map mam ON mam.ASSIGNMENT_ID = asgnmnt.ASSIGNMENT_ID INNER JOIN teacher_course_session_dtls tcs_dtl ON tcs_dtl.MODULE_ID = mam.MODULE_ID INNER JOIN course_module_map cmm ON cmm.MODULE_ID = tcs_dtl.MODULE_ID  INNER JOIN course_mstr cmstr ON cmstr.COURSE_ID = cmm.COURSE_ID INNEr JOIN module_mstr mmstr ON mmstr.MODULE_ID = tcs_dtl.MODULE_ID where txn.STUDENT_ID = (SELECT USER_NM FROM user_login where USER_ID=?) AND asgnmnt.DESC_TXT like ?";
            //updated on 26-08-2015
            String sql = "SELECT DISTINCT txn.ASSIGNMENT_ID,asignment.ASSIGNMENT_NAME,txn.IS_COMPLETED,txn.STUDENT_ID,TIMESTAMP(txn.UPLOADED_ON),cmm.COURSE_ID,cmstr.COURSE_NAME,cmm.MODULE_ID,mmstr.MODULE_NAME,asignment.DESC_TXT,TIMESTAMP(txn.DUE_ON) FROM assignment_resource_txn txn inner join module_assignment_map mam on mam.ASSIGNMENT_ID=txn.ASSIGNMENT_ID inner join course_module_map cmm on cmm.MODULE_ID=mam.MODULE_ID inner join teacher_courses tcourse on tcourse.COURSE_ID=cmm.COURSE_ID inner join user_cls_map ucm on ucm.SCHOOL_ID=tcourse.SCHOOL_ID and ucm.CLASS_ID=tcourse.CLASS_ID and ucm.HRM_ID=tcourse.HRM_ID inner join user_login ulogin on ulogin.USER_NM=txn.STUDENT_ID inner join course_mstr cmstr on cmstr.COURSE_ID=cmm.COURSE_ID inner join module_mstr mmstr on mmstr.MODULE_ID=cmm.MODULE_ID inner join assignment asignment on asignment.ASSIGNMENT_ID=txn.ASSIGNMENT_ID where ulogin.USER_ID=? AND asignment.DESC_TXT like ?";
            
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, userId);
            stmt.setString(2, "%"+searchText+"%");
            
            rs = stmt.executeQuery();
            AssignmentVO vo = null;
            while (rs.next()) {
                vo = new AssignmentVO();
                vo.setAssignmentId(rs.getInt(1));
                vo.setAssignmentName(rs.getString(2));
                vo.setAssignmentStatus(rs.getString(3));
                vo.setAssignmentSubmittedBy(rs.getString(4));
                vo.setAssignmentSubmittedDate(rs.getString(5));
                vo.setCourseId(rs.getInt(6));
                vo.setCourseName(rs.getString(7));
                vo.setModuleId(rs.getInt(8));
                vo.setModuleName(rs.getString(9));
                vo.setAssignmentDesc(rs.getString(10));
                vo.setAssignmentDueDate(rs.getString(11));
                
                list.add(vo);
            }

        } catch (SQLException se) {
            System.out.println("getStudentAssignments # " + se);
            throw new LmsDaoException(se.getMessage());
        } catch (Exception e) {
            System.out.println("getStudentAssignments # " + e);
            throw new LmsDaoException(e.getMessage());
        } finally {
            closeResources(conn, stmt, rs);
        }

        return list;
    }

    
    

    @Override
    public List<AssignmentVO> getStudentAssignments(int userId) throws LmsDaoException {
        List<AssignmentVO> list = new ArrayList<AssignmentVO>();

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs =null;
        try {
            conn = getConnection();

            /**
             * ASSIGNMENT_ID | ASSIGNMENT_NAME | ASSIGNMENT_STATUS | ASSIGNMENT_SUBMITTED_BY | ASSIGNMENT_SUBMITTED_DATE
             */
            
            String sql = "SELECT DISTINCT asgnmnt.ASSIGNMENT_ID,asgnmnt.ASSIGNMENT_NAME,txn.IS_COMPLETED,txn.STUDENT_ID,TIMESTAMP(txn.UPLOADED_ON),TIMESTAMP(txn.DUE_ON) FROM assignment_resource_txn txn INNER JOIN assignment asgnmnt ON txn.ASSIGNMENT_ID = asgnmnt.ASSIGNMENT_ID where txn.STUDENT_ID = (SELECT USER_NM FROM user_login where USER_ID=?)";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, userId);
            
            rs = stmt.executeQuery();
            AssignmentVO vo = null;
            while (rs.next()) {
                vo = new AssignmentVO();
                vo.setAssignmentId(rs.getInt(1));
                vo.setAssignmentName(rs.getString(2));
                vo.setAssignmentStatus(rs.getString(3));
                vo.setAssignmentSubmittedBy(rs.getString(4));
                vo.setAssignmentSubmittedDate(rs.getString(5));
                vo.setAssignmentDueDate(rs.getString(6));
                
                list.add(vo);
            }

        } catch (SQLException se) {
            System.out.println("getStudentAssignments # " + se);
            throw new LmsDaoException(se.getMessage());
        } catch (Exception e) {
            System.out.println("getStudentAssignments # " + e);
            throw new LmsDaoException(e.getMessage());
        } finally {
            closeResources(conn, stmt, rs);
        }

        return list;
    }

    

    @Override
    public List<AssignmentVO> getStudentAssignmentsByModuleId(int moduleId) throws LmsDaoException {
        List<AssignmentVO> list = new ArrayList<AssignmentVO>();

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs =null;
        try {
            conn = getConnection();

            /**
             * ASSIGNMENT_ID | ASSIGNMENT_NAME | ASSIGNMENT_STATUS | ASSIGNMENT_SUBMITTED_BY | ASSIGNMENT_SUBMITTED_DATE
             */
            
            String sql = "SELECT DISTINCT asgnmnt.ASSIGNMENT_ID,asgnmnt.ASSIGNMENT_NAME,txn.IS_COMPLETED,(SELECT concat(USER_ID,'-',FNAME,' ',LNAME) FROM student_dtls where EMAIL_ID=txn.STUDENT_ID) as student_nm,TIMESTAMP(txn.UPLOADED_ON),asgnmnt.DESC_TXT,TIMESTAMP(txn.DUE_ON) FROM assignment_resource_txn txn INNER JOIN assignment asgnmnt ON txn.ASSIGNMENT_ID = asgnmnt.ASSIGNMENT_ID INNER JOIN module_assignment_map mod_assignment ON mod_assignment.ASSIGNMENT_ID=asgnmnt.ASSIGNMENT_ID where mod_assignment.MODULE_ID = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, moduleId);
            
            rs = stmt.executeQuery();
            AssignmentVO vo = null;
            while (rs.next()) {
                vo = new AssignmentVO();
                vo.setAssignmentId(rs.getInt(1));
                vo.setAssignmentName(rs.getString(2));
                vo.setAssignmentStatus(rs.getString(3));
                vo.setAssignmentSubmittedBy(rs.getString(4));
                vo.setAssignmentSubmittedDate(rs.getString(5));
                vo.setAssignmentDesc(rs.getString(6));
                vo.setAssignmentDueDate(rs.getString(7));
                
                list.add(vo);
            }

        } catch (SQLException se) {
            System.out.println("getStudentAssignmentsByModuleId # " + se);
            throw new LmsDaoException(se.getMessage());
        } catch (Exception e) {
            System.out.println("getStudentAssignmentsByModuleId # " + e);
            throw new LmsDaoException(e.getMessage());
        } finally {
            closeResources(conn, stmt, rs);
        }

        return list;
    }


    @Override
    public List<AssignmentVO> getStudentAssignmentsByModuleId(String userName,int moduleId) throws LmsDaoException {
        List<AssignmentVO> list = new ArrayList<AssignmentVO>();

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs =null;
        try {
            conn = getConnection();

            /**
             * ASSIGNMENT_ID | ASSIGNMENT_NAME | ASSIGNMENT_STATUS | ASSIGNMENT_SUBMITTED_BY | ASSIGNMENT_SUBMITTED_DATE
             */
            
            String sql = "SELECT DISTINCT asgnmnt.ASSIGNMENT_ID,asgnmnt.ASSIGNMENT_NAME,txn.IS_COMPLETED,txn.STUDENT_ID,TIMESTAMP(txn.UPLOADED_ON),TIMESTAMP(txn.DUE_ON) FROM assignment_resource_txn txn INNER JOIN assignment asgnmnt ON txn.ASSIGNMENT_ID = asgnmnt.ASSIGNMENT_ID INNER JOIN module_assignment_map mod_assignment ON mod_assignment.ASSIGNMENT_ID=asgnmnt.ASSIGNMENT_ID where mod_assignment.MODULE_ID = ? and txn.LAST_USERID_CD=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, moduleId);
            stmt.setString(2, userName);
            
            rs = stmt.executeQuery();
            AssignmentVO vo = null;
            while (rs.next()) {
                vo = new AssignmentVO();
                vo.setAssignmentId(rs.getInt(1));
                vo.setAssignmentName(rs.getString(2));
                vo.setAssignmentStatus(rs.getString(3));
                vo.setAssignmentSubmittedBy(rs.getString(4));
                vo.setAssignmentSubmittedDate(rs.getString(5));
                vo.setAssignmentDueDate(rs.getString(6));
                
                list.add(vo);
            }

        } catch (SQLException se) {
            System.out.println("getStudentAssignmentsByModuleId # " + se);
            throw new LmsDaoException(se.getMessage());
        } catch (Exception e) {
            System.out.println("getStudentAssignmentsByModuleId # " + e);
            throw new LmsDaoException(e.getMessage());
        } finally {
            closeResources(conn, stmt, rs);
        }

        return list;
    }

    
    
    @Override
    public List<ResourseVO> getAssignmentsResources(int assignmentId) throws LmsDaoException {
        List<ResourseVO> list = new ArrayList<ResourseVO>();

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs =null;
        try {
            conn = getConnection();

            /**
             * RESOURSE_ID | RESOURSE_NAME | DESC_TXT   |     RESOURCE_AUTHOR | AUTHOR_IMG |  RESOURCE_URL | THUMB_IMG  |  UPLOADED_ON 
             * 
             * SELECT DISTINCT rsm.RESOURSE_ID,rsm.DESC_TXT,rsm.RESOURSE_NAME,TIMESTAMP(txn.UPLOADED_ON) FROM assignment_resource_txn txn INNER JOIN resourse_mstr rsm ON txn.UPLODED_RESOURCE_ID = rsm.RESOURSE_ID WHERE txn.STUDENT_ID=?
             */
            String sql = "SELECT DISTINCT rsm.RESOURSE_ID,rsm.RESOURSE_NAME,rsm.DESC_TXT,rsm.RESOURCE_AUTHOR,rsm.AUTHOR_IMG,rsm.RESOURCE_URL,rsm.THUMB_IMG,TIMESTAMP(txn.UPLOADED_ON),txn.STUDENT_ID FROM assignment_resource_txn txn INNER JOIN resourse_mstr rsm ON txn.UPLODED_RESOURCE_ID = rsm.RESOURSE_ID WHERE txn.ASSIGNMENT_ID=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, assignmentId);
            
            rs = stmt.executeQuery();
            ResourseVO vo = null;
            while (rs.next()) {
                vo = new ResourseVO();
                vo.setResourceId(rs.getInt(1));
                vo.setResourceName(rs.getString(2));
                vo.setResourceDesc(rs.getString(3));

                vo.setAuthorName(rs.getString(4));
                vo.setAuthorImg(rs.getString(5));
                vo.setResourceUrl(rs.getString(6));
                vo.setThumbUrl(rs.getString(7));
                vo.setUploadedDate(rs.getString(8));
                vo.setUploadedBy(rs.getString(9));
                
                list.add(vo);
            }

        } catch (SQLException se) {
            System.out.println("getAssignmentsResources # " + se);
            throw new LmsDaoException(se.getMessage());
        } catch (Exception e) {
            System.out.println("getAssignmentsResources # " + e);
            throw new LmsDaoException(e.getMessage());
        } finally {
            closeResources(conn, stmt, rs);
        }

        return list;
    }

    
    @Override
    public List<ResourseVO> getAssignmentsResources(int userId, int assignmentId) throws LmsDaoException {
        List<ResourseVO> list = new ArrayList<ResourseVO>();

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs =null;
        try {
            conn = getConnection();

            /**
             * RESOURCE_ID | RESOURCE_DESC | RESOURCE_URL | UPLOADED_DATE
             * 
             * SELECT DISTINCT rsm.RESOURSE_ID,rsm.DESC_TXT,rsm.RESOURSE_NAME,TIMESTAMP(txn.UPLOADED_ON) FROM assignment_resource_txn txn INNER JOIN resourse_mstr rsm ON txn.UPLODED_RESOURCE_ID = rsm.RESOURSE_ID WHERE txn.STUDENT_ID=?
             */
            String sql = "SELECT DISTINCT rsm.RESOURSE_ID,rsm.RESOURSE_NAME,rsm.RESOURCE_AUTHOR,rsm.DESC_TXT,rsm.RESOURCE_URL,rsm.AUTHOR_IMG, rsm.THUMB_IMG,TIMESTAMP(txn.UPLOADED_ON) FROM assignment_resource_txn txn INNER JOIN resourse_mstr rsm ON txn.UPLODED_RESOURCE_ID = rsm.RESOURSE_ID WHERE txn.STUDENT_ID=(SELECT USER_NM FROM user_login where USER_ID=?) and txn.ASSIGNMENT_ID=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, userId);
            stmt.setInt(2, assignmentId);
            
            rs = stmt.executeQuery();
            ResourseVO vo = null;
            while (rs.next()) {
                vo = new ResourseVO();
                vo.setResourceId(rs.getInt(1));
                vo.setResourceName(rs.getString(2));
                vo.setAuthorName(rs.getString(3));
                vo.setResourceDesc(rs.getString(4));
                vo.setResourceUrl(rs.getString(5));
                vo.setAuthorImg(rs.getString(6));
                vo.setThumbUrl(rs.getString(7));
                vo.setUploadedDate(rs.getString(8));

                list.add(vo);
            }

        } catch (SQLException se) {
            System.out.println("getAssignmentsResources # " + se);
            throw new LmsDaoException(se.getMessage());
        } catch (Exception e) {
            System.out.println("getAssignmentsResources # " + e);
            throw new LmsDaoException(e.getMessage());
        } finally {
            closeResources(conn, stmt, rs);
        }

        return list;
    }

    
}//end of class

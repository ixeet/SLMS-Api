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
                    + " VALUES (?, ?, null, current_date, 0, ?, ?, current_timestamp)";

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
                    + " VALUES (?, ?, ?, current_date, 0, ?, ?, current_timestamp)";

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
                                           + "VALUES(?, null, current_date, 0, ?, ?, current_timestamp)";

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
                                            + "VALUES(?, ?, current_date, 0, ?, ?, current_timestamp)";

            String resource_id = getQueryConcatedResult("SELECT RESOURCE_ID FROM resource_likes where RESOURCE_LIKE_ID="+commentId);
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
            String sql = "INSERT INTO teacher_course_sessions(COURSE_SESSION_ID, TEACHER_COURSE_ID, START_SESSION_TM, END_SESSION_TM, "
                    + "STATUS_TXT, LAST_USERID_CD, LAST_UPDT_TM)  VALUES(?, ?, ?, ?, ?, ?, current_timestamp)";


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
            String sql = "SELECT cmstr.COURSE_ID,cmstr.COURSE_NAME,tc_sess.START_SESSION_TM,tc_sess.IS_COMPLETE,tc_sess.COURSE_SESSION_ID FROM user_cls_map ucmap INNER JOIN clas_course_map cc_map ON cc_map.CLASS_ID=ucmap.CLASS_ID INNER JOIN course_mstr cmstr ON cmstr.COURSE_ID=cc_map.COURSE_ID INNER JOIN teacher_courses tcourse ON tcourse.COURSE_ID= cc_map.COURSE_ID AND tcourse.CLASS_ID=ucmap.CLASS_ID INNER JOIN teacher_course_sessions tc_sess ON tc_sess.TEACHER_COURSE_ID=tcourse.TEACHER_COURSE_ID where ucmap.USER_ID = ? AND cmstr.METADATA like ?";
            
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, userId);
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
    public List<CourseVO> getStudentCoursesModules(int courseSessionId) throws LmsDaoException {
        List<CourseVO> list = new ArrayList<CourseVO>();

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();

            String sql = "SELECT modul.MODULE_ID,MODULE_NAME,tcs_dtl.START_SESSION_TM,tcs_dtl.IS_COMPLETED,(SELECT count(*) FROM teacher_module_session_dtls where IS_COMPLETED='1' and COURSE_SESSION_DTLS_ID=tcs_dtl.COURSE_SESSION_DTLS_ID)/(SELECT count(*) FROM teacher_module_session_dtls where COURSE_SESSION_DTLS_ID=tcs_dtl.COURSE_SESSION_DTLS_ID) as completedPercent FROM module_mstr modul INNER JOIN teacher_course_session_dtls tcs_dtl ON modul.MODULE_ID=tcs_dtl.MODULE_ID INNER JOIN teacher_course_sessions tc_sess ON tc_sess.COURSE_SESSION_ID=tcs_dtl.COURSE_SESSION_ID WHERE tc_sess.COURSE_SESSION_ID = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, courseSessionId);
            
            rs = stmt.executeQuery();
            CourseVO vo = null;
            while (rs.next()) {
                vo = new CourseVO();
                vo.setModuleId(rs.getString(1));
                vo.setModuleName(rs.getString(2));
                vo.setStartedOn(rs.getString(3));
                vo.setCompletedStatus(rs.getString(4));
                vo.setCompletedPercentStatus(String.valueOf(rs.getDouble(5)*100));
                
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
            String sql = "SELECT rc_mstr.RESOURSE_ID,rc_mstr.RESOURSE_NAME,rc_mstr.DESC_TXT,rc_mstr.RESOURCE_AUTHOR,rc_mstr.THUMB_IMG,rc_mstr.RESOURCE_URL,rc_mstr.AUTHOR_IMG,tc_sess_dtl.START_SESSION_TM,tc_sess_dtl.END_SESSION_TM,(SELECT count(*) FROM resource_comments where RESOURCE_ID=rc_mstr.RESOURSE_ID),(SELECT count(*) FROM resource_likes where RESOURCE_ID=rc_mstr.RESOURSE_ID) FROM teacher_courses tc INNER JOIN teacher_course_sessions tc_sess ON tc.TEACHER_COURSE_ID=tc_sess.TEACHER_COURSE_ID INNER JOIN teacher_course_session_dtls tc_sess_dtl ON tc_sess_dtl.COURSE_SESSION_ID=tc_sess.COURSE_SESSION_ID INNER JOIN user_cls_map ucm ON tc.CLASS_ID = ucm.CLASS_ID AND ucm.SCHOOL_ID=tc.SCHOOL_ID INNER JOIN clas_course_map ccm on ccm.CLASS_ID=ucm.CLASS_ID AND tc.COURSE_ID=ccm.COURSE_ID INNER JOIN course_module_map cmm on cmm.COURSE_ID=ccm.COURSE_ID INNER JOIN module_resource_map mrm on mrm.MODULE_ID=cmm.MODULE_ID AND tc_sess_dtl.MODULE_ID=mrm.MODULE_ID INNER JOIN resourse_mstr rc_mstr on rc_mstr.RESOURSE_ID=mrm.RESOURCE_ID WHERE ucm.USER_ID =? AND ccm.COURSE_ID = ? AND tc_sess_dtl.MODULE_ID=? AND rc_mstr.METADATA like ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1,userId);
            stmt.setInt(2,courseId);
            stmt.setInt(3,moduleId);
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
    public List<CommentVO> getResourceComments(int resourceId) throws LmsDaoException {
        List<CommentVO> list = new ArrayList<CommentVO>();

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs =null;
        try {
            conn = getConnection();

            /**
             *SELECT RESOURCE_COMMENT_ID,COMMENT_TXT,PARENT_COMMENT_ID,COMMENTED_BY,LAST_UPDT_TM FROM resource_comments where RESOURCE _ID=? 
             */
            String sql = "SELECT RESOURCE_COMMENT_ID,COMMENT_TXT,PARENT_COMMENT_ID,COMMENTED_BY,LAST_UPDT_TM,(SELECT count(*) FROM resource_comments where PARENT_COMMENT_ID=temp.PARENT_COMMENT_ID),(SELECT count(*) FROM resource_likes where PARENT_COMMENT_ID=temp.PARENT_COMMENT_ID) FROM resource_comments temp where RESOURCE_ID=?";
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
                vo.setCommentDate(rs.getString(5));
                
                vo.setCommentCounts(rs.getInt(6));
                vo.setLikeCounts(rs.getInt(7));
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
            String sql = "SELECT RESOURSE_ID, RESOURSE_NAME, RESOURCE_AUTHOR, DESC_TXT, LAST_UPDT_TM FROM resourse_mstr where METADATA =(SELECT METADATA FROM resourse_mstr where RESOURSE_ID=?)";
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
            
            String sql = "SELECT DISTINCT asgnmnt.ASSIGNMENT_ID,asgnmnt.ASSIGNMENT_NAME,txn.IS_COMPLETED,txn.STUDENT_ID,txn.UPLOADED_ON FROM assignment_resource_txn txn INNER JOIN assignment asgnmnt ON txn.ASSIGNMENT_ID = asgnmnt.ASSIGNMENT_ID where txn.STUDENT_ID = (SELECT USER_NM FROM user_login where USER_ID=?)";
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
             * SELECT DISTINCT rsm.RESOURSE_ID,rsm.DESC_TXT,rsm.RESOURSE_NAME,txn.UPLOADED_ON FROM assignment_resource_txn txn INNER JOIN resourse_mstr rsm ON txn.UPLODED_RESOURCE_ID = rsm.RESOURSE_ID WHERE txn.STUDENT_ID=?
             */
            String sql = "SELECT DISTINCT rsm.RESOURSE_ID,rsm.DESC_TXT,rsm.RESOURSE_NAME,txn.UPLOADED_ON FROM assignment_resource_txn txn INNER JOIN "
                    + "resourse_mstr rsm ON txn.UPLODED_RESOURCE_ID = rsm.RESOURSE_ID WHERE txn.STUDENT_ID=(SELECT USER_NM FROM user_login where USER_ID=?) and txn.ASSIGNMENT_ID=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, userId);
            stmt.setInt(2, assignmentId);
            
            rs = stmt.executeQuery();
            ResourseVO vo = null;
            while (rs.next()) {
                vo = new ResourseVO();
                vo.setResourceId(rs.getInt(1));
                vo.setResourceDesc(rs.getString(2));
                vo.setResourceName(rs.getString(3));
                vo.setUploadedDate(rs.getString(4));

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

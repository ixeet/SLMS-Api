/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scolere.lms.persistance.dao.impl;

import com.scolere.lms.application.rest.constants.SLMSRestConstants;
import com.scolere.lms.domain.exception.LmsDaoException;
import com.scolere.lms.domain.vo.cross.AssignmentVO;
import com.scolere.lms.domain.vo.cross.CommentVO;
import com.scolere.lms.domain.vo.cross.CourseVO;
import com.scolere.lms.domain.vo.cross.FeedVO;
import com.scolere.lms.domain.vo.cross.ResourseVO;
import com.scolere.lms.domain.vo.cross.UserVO;
import com.scolere.lms.persistance.dao.iface.FeedDao;
import com.scolere.lms.persistance.factory.LmsDaoAbstract;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dell
 */
public class FeedDaoImpl extends LmsDaoAbstract implements FeedDao{

    @Override
    public List<FeedVO> getFeedsList(int userId, String searchTxt) throws LmsDaoException {

      List<FeedVO> list=new ArrayList<FeedVO>();

      Connection conn = null;
      PreparedStatement cstmt = null;
      ResultSet rs = null;

      try {
          conn = this.getConnection(dataSource);
          
          //SELECT lf_typ.FEED_TEMPLATE,lf_typ.TEMP_PARAM,lf_txn.*,(SELECT count(*) FROM lms_feed_comments where FEED_ID=lf_txn.FEED_ID) as feed_count,(SELECT count(*) FROM lms_feed_likes where FEED_ID=lf_txn.FEED_ID) as feed_likes,(SELECT count(*) FROM lms_feed_comments where COMMENTED_BY=ulogin.USER_NM)as COMMENTED_BY FROM lms_feed_txn lf_txn inner join lms_feed_type lf_typ on lf_typ.FEED_TYPE_ID=lf_txn.FEED_TYPE_ID inner join user_login ulogin on lf_txn.LAST_USERID_CD=ulogin.USER_NM inner join user_cls_map ucmap on ucmap.USER_ID=ulogin.USER_ID where ucmap.HRM_ID = (SELECT HRM_ID FROM user_cls_map where USER_ID=22)
          String query = "SELECT lf_typ.FEED_TEMPLATE,lf_typ.TEMP_PARAM,lf_txn.*,(SELECT count(*) FROM lms_feed_comments where FEED_ID=lf_txn.FEED_ID) as feed_count,(SELECT count(*) FROM lms_feed_likes where FEED_ID=lf_txn.FEED_ID) as feed_likes,(SELECT count(*) FROM lms_feed_comments where COMMENTED_BY=ulogin.USER_NM)as COMMENTED_BY FROM lms_feed_txn lf_txn inner join lms_feed_type lf_typ on lf_typ.FEED_TYPE_ID=lf_txn.FEED_TYPE_ID inner join user_login ulogin on lf_txn.LAST_USERID_CD=ulogin.USER_NM inner join user_cls_map ucmap on ucmap.USER_ID=ulogin.USER_ID where ucmap.HRM_ID = (SELECT HRM_ID FROM user_cls_map where USER_ID=?)";
          System.out.println("Query : " + query);

          cstmt = conn.prepareStatement(query);
          cstmt.setInt(1, userId);
          
          rs = cstmt.executeQuery();
          //FEED_TEMPLATE,TEMP_PARAM,FEED_ID,FEED_TYPE_ID,REFRENCE_NM,USER_ID,COURSE_ID,RESOURCE_ID,ASSIGNMENT_ID,MODULE_ID,HRM_ID,LAST_USERID_CD, LAST_UPDT_TM,,feed_count,feed_likes,COMMENTED_BY    
          FeedVO vo = null;
          while(rs.next())
          {
              vo = new FeedVO();
              vo.setFeedTemplate(rs.getString("FEED_TEMPLATE"));
              vo.setTempParam(rs.getString("TEMP_PARAM"));
              vo.setFeedID(rs.getInt("FEED_ID"));
              vo.setFeedTypeID(rs.getInt("FEED_TYPE_ID"));
              vo.setFeedRefName(rs.getString("REFRENCE_NM"));
              vo.setUserId(rs.getInt("USER_ID"));
              vo.setCourseId(rs.getInt("COURSE_ID"));
              vo.setResourseId(rs.getInt("RESOURCE_ID"));
              vo.setAssignmentId(rs.getInt("ASSIGNMENT_ID"));
              vo.setModuleId(rs.getInt("MODULE_ID"));
              vo.setHrmId(rs.getInt("HRM_ID"));
              vo.setUserName(rs.getString("LAST_USERID_CD"));
              vo.setCommentCounts(rs.getInt(14));
              vo.setLikeCounts(rs.getInt(15));
              
              int commentsByUser=rs.getInt(16);
              if(commentsByUser>0) {
                  vo.setIsLiked(true);
              }
              
              list.add(vo);
          }

          System.out.println("No of feeds returned : "+list.size());

      } catch (Exception e) {
          System.out.println("Error > getFeedsList - "+e.getMessage());
      } finally {
          closeResources(conn, cstmt, rs);
      }

      return list;        
  }

    
    @Override
    public List<CommentVO> getFeedCommentsList(int feedId) throws LmsDaoException {

      List<CommentVO> list=new ArrayList<CommentVO>();

      Connection conn = null;
      PreparedStatement cstmt = null;
      ResultSet rs = null;

      try {
          //SELECT DEALER_CD  FROM ACT.MQT_DATA_DLRSHIP_EMP_CUR where DISTRICT_CD='SEZ2D07'
          String query = "SELECT * FROM lms_feed_comments where FEED_ID=?";
          System.out.println("Query : " + query);

          conn = this.getConnection(dataSource);
          cstmt = conn.prepareStatement(query);
          cstmt.setInt(1, feedId);
          
          rs = cstmt.executeQuery();
          
          CommentVO vo = null;
          while(rs.next())
          {
             vo = new CommentVO();
             vo.setCommentBy(rs.getString("COMMENTED_BY"));
             vo.setCommentId(rs.getInt("FEED_COMMENT_ID"));
             vo.setParentCommentId(rs.getInt("PARENT_COMMENT_ID"));
             vo.setCommentTxt(rs.getString("COMMENT_TXT")); 
             vo.setCommentDate(rs.getString("LAST_UPDT_TM"));
             
             list.add(vo);
          }

          System.out.println("No of Feed Comments returned : "+list.size());

      } catch (Exception e) {
          System.out.println("Error > getFeedCommentsList - "+e.getMessage());
      } finally {
          closeResources(conn, cstmt, rs);
      }

      return list;        
  }


    @Override
    public String getResourceFeedText(int resourceId) throws LmsDaoException {
        String query="SELECT CONCAT(RESOURSE_NAME,'#',RESOURSE_ID) FROM resourse_mstr where RESOURSE_ID="+resourceId;
        return getQueryConcatedResult(query);
    }

    @Override
    public String getCourseFeedText(int courseId) throws LmsDaoException {
        String query="SELECT CONCAT(COURSE_NAME,'#',COURSE_ID) FROM course_mstr where COURSE_ID="+courseId;
        return getQueryConcatedResult(query);
    }

    @Override
    public String getUserFeedText(int userId) throws LmsDaoException {
        String query="SELECT CONCAT(temp.FNAME,' ',temp.LNAME,'#',temp.USER_ID) FROM user_login usr inner join (SELECT USER_ID, TITLE, FNAME, LNAME, EMAIL_ID, CONTACT_NO, BIRTH_DT, JOINING_DATE, PROFILE_IMG FROM student_dtls union SELECT USER_ID,'', FNAME, LNAME, EMAIL_ID, CONTACT_NO, BIRTH_DT, JOINING_DATE,'Teacher' FROM teacher_dtls) temp on temp.USER_ID = usr.USER_ID where usr.USER_ID="+userId;
        return getQueryConcatedResult(query);
    }

    @Override
    public String getAssignmentFeedText(int assignmentId) throws LmsDaoException {
        String query="SELECT CONCAT(ASSIGNMENT_NAME,'#',ASSIGNMENT_ID) FROM assignment where ASSIGNMENT_ID="+assignmentId;
        return getQueryConcatedResult(query);
    }

    @Override
    public String getModuleFeedText(int moduleId) throws LmsDaoException {
        String query="SELECT CONCAT(MODULE_NAME,'#',MODULE_ID) FROM module_mstr where MODULE_ID="+moduleId;
        return getQueryConcatedResult(query);
    }


    @Override
    public FeedVO getFeedDetail(int feedId) throws LmsDaoException {
         FeedVO vo = null;

         Connection conn = null;
         PreparedStatement cstmt = null;
         ResultSet rs = null;

         try {
             String query="SELECT * FROM lms_feed_txn where FEED_ID=?";
             System.out.println("Query : " + query);

             conn = this.getConnection(dataSource);
             cstmt = conn.prepareStatement(query);
             cstmt.setInt(1, feedId);
             rs = cstmt.executeQuery();
             if(rs.next())
             {
             //FEED_ID,FEED_TYPE_ID,REFRENCE_NM,USER_ID,COURSE_ID,RESOURCE_ID,ASSIGNMENT_ID,MODULE_ID,HRM_ID,LAST_USERID_CD    
             vo=new FeedVO();
             vo.setFeedID(rs.getInt(1));
             vo.setFeedTypeID(rs.getInt(2));
             vo.setFeedRefName(rs.getString(3));
             vo.setUserId(rs.getInt(4));
             vo.setCourseId(rs.getInt(5));
             vo.setResourseId(rs.getInt(6));
             vo.setAssignmentId(rs.getInt(7));
             vo.setModuleId(rs.getInt(8));
             vo.setHrmId(rs.getInt(9));
             vo.setUserName(rs.getString(10));
             }
             
         } catch (Exception e) {
             System.out.println("Error > getFeedDetail - "+e.getMessage());
         } finally {
             closeResources(conn, cstmt, rs);
         }

         return vo;        
     }
    
    
    @Override
    public UserVO getUserDetail(String userName) throws LmsDaoException {
         UserVO vo = null;

         Connection conn = null;
         PreparedStatement cstmt = null;
         ResultSet rs = null;

         try {
             // USER_ID,USER_NM,USER_FB_ID,TITLE,FNAME,LNAME,EMAIL_ID,CONTACT_NO,PROFILE_IMG,SCHOOL_ID,SCHOOL_NAME,CLASS_ID,CLASS_NAME,HRM_ID,HRM_NAME    
             String query="SELECT sdtl.USER_ID,ulogin.USER_NM,ulogin.USER_FB_ID,sdtl.TITLE,sdtl.FNAME,sdtl.LNAME,sdtl.EMAIL_ID,sdtl.ADMIN_EMAIL_ID,sdtl.PROFILE_IMG,smstr.SCHOOL_ID,smstr.SCHOOL_NAME,cmstr.CLASS_ID,cmstr.CLASS_NAME,hmstr.HRM_ID,hmstr.HRM_NAME FROM user_login ulogin inner join student_dtls sdtl on ulogin.USER_ID= sdtl.USER_ID inner join user_cls_map ucm on sdtl.USER_ID=ucm.USER_ID inner join class_mstr cmstr on cmstr.CLASS_ID=ucm.CLASS_ID inner join school_mstr smstr on smstr.SCHOOL_ID=ucm.SCHOOL_ID inner join homeroom_mstr hmstr on hmstr.HRM_ID=ucm.HRM_ID where ulogin.USER_NM=?";
             System.out.println("Query : " + query);

             conn = this.getConnection(dataSource);
             cstmt = conn.prepareStatement(query);
             cstmt.setString(1, userName);
             rs = cstmt.executeQuery();
             if(rs.next())
             {
             // USER_ID,USER_NM,USER_FB_ID,TITLE,FNAME,LNAME,EMAIL_ID,ADMIN_EMAIL_ID,PROFILE_IMG,SCHOOL_ID,SCHOOL_NAME,CLASS_ID,CLASS_NAME,HRM_ID,HRM_NAME    
             vo=new UserVO();
             vo.setUserId(rs.getInt(1));
             vo.setUserName(rs.getString(2));
             vo.setUserFbId(rs.getString(3));
             vo.setTitle(rs.getString(4));
             vo.setFirstName(rs.getString(5));
             vo.setLastName(rs.getString(6));
             vo.setEmailId(rs.getString(7));
             vo.setAdminEmailId(rs.getString(8));
             vo.setProfileImage(rs.getString(9));
             vo.setSchoolId(rs.getInt(10));
             vo.setSchoolName(rs.getString(11));
             vo.setClassId(rs.getInt(12));
             vo.setClassName(rs.getString(13));
             vo.setHomeRoomId(rs.getInt(14));
             vo.setHomeRoomName(rs.getString(15));
             }
             
         } catch (Exception e) {
             System.out.println("Error > getUserDetail(String) - "+e.getMessage());
         } finally {
             closeResources(conn, cstmt, rs);
         }

         return vo;        
     }
    
    
    @Override
    public UserVO getUserDetail(int userId) throws LmsDaoException {
         UserVO vo = null;

         Connection conn = null;
         PreparedStatement cstmt = null;
         ResultSet rs = null;

         try {
             // USER_ID,USER_NM,USER_FB_ID,TITLE,FNAME,LNAME,EMAIL_ID,CONTACT_NO,PROFILE_IMG,SCHOOL_ID,SCHOOL_NAME,CLASS_ID,CLASS_NAME,HRM_ID,HRM_NAME    
             String query="SELECT sdtl.USER_ID,ulogin.USER_NM,ulogin.USER_FB_ID,sdtl.TITLE,sdtl.FNAME,sdtl.LNAME,sdtl.EMAIL_ID,sdtl.ADMIN_EMAIL_ID,sdtl.PROFILE_IMG,smstr.SCHOOL_ID,smstr.SCHOOL_NAME,cmstr.CLASS_ID,cmstr.CLASS_NAME,hmstr.HRM_ID,hmstr.HRM_NAME FROM user_login ulogin inner join student_dtls sdtl on ulogin.USER_ID= sdtl.USER_ID inner join user_cls_map ucm on sdtl.USER_ID=ucm.USER_ID inner join class_mstr cmstr on cmstr.CLASS_ID=ucm.CLASS_ID inner join school_mstr smstr on smstr.SCHOOL_ID=ucm.SCHOOL_ID inner join homeroom_mstr hmstr on hmstr.HRM_ID=ucm.HRM_ID where sdtl.USER_ID=?";
             System.out.println("Query : " + query);

             conn = this.getConnection(dataSource);
             cstmt = conn.prepareStatement(query);
             cstmt.setInt(1, userId);
             rs = cstmt.executeQuery();
             if(rs.next())
             {
             // USER_ID,USER_NM,USER_FB_ID,TITLE,FNAME,LNAME,EMAIL_ID,ADMIN_EMAIL_ID,PROFILE_IMG,SCHOOL_ID,SCHOOL_NAME,CLASS_ID,CLASS_NAME,HRM_ID,HRM_NAME    
             vo=new UserVO();
             vo.setUserId(rs.getInt(1));
             vo.setUserName(rs.getString(2));
             vo.setUserFbId(rs.getString(3));
             vo.setTitle(rs.getString(4));
             vo.setFirstName(rs.getString(5));
             vo.setLastName(rs.getString(6));
             vo.setEmailId(rs.getString(7));
             vo.setAdminEmailId(rs.getString(8));
             vo.setProfileImage(rs.getString(9));
             vo.setSchoolId(rs.getInt(10));
             vo.setSchoolName(rs.getString(11));
             vo.setClassId(rs.getInt(12));
             vo.setClassName(rs.getString(13));
             vo.setHomeRoomId(rs.getInt(14));
             vo.setHomeRoomName(rs.getString(15));
             }
             
         } catch (Exception e) {
             System.out.println("Error > getUserDetail(int) - "+e.getMessage());
         } finally {
             closeResources(conn, cstmt, rs);
         }

         return vo;        
     }
    
    @Override
    public ResourseVO getDefaultResourseDetail(int feedId) throws LmsDaoException {
         ResourseVO vo = null;
         Connection conn = null;
         PreparedStatement cstmt = null;
         ResultSet rs = null;

         try {
             String query="";

             // Get Feed Details
             FeedVO feed = getFeedDetail(feedId);
             if(feed.getResourseId() > 0)
             {
                query="SELECT * FROM resourse_mstr where RESOURSE_ID ="+feed.getResourseId(); 
             }
             else if(feed.getModuleId() > 0)
             {
                 query="SELECT * FROM resourse_mstr where RESOURSE_ID = (SELECT RESOURCE_ID FROM module_resource_map where MODULE_ID="+feed.getModuleId()+")";
             }
             else if(feed.getAssignmentId() > 0)
             {
                 query="SELECT * FROM resourse_mstr where RESOURSE_ID = (SELECT UPLODED_RESOURCE_ID FROM assignment_resource_txn where ASSIGNMENT_ID="+feed.getAssignmentId()+")";
             }
             else if(feed.getCourseId() > 0)
             {
                 query="SELECT * FROM resourse_mstr where RESOURSE_ID = (SELECT distinct CONTENT_ID FROM teacher_courses tcourse inner join teacher_course_sessions tcs on tcs.TEACHER_COURSE_ID = tcourse.TEACHER_COURSE_ID inner join teacher_course_session_dtls tcs_dtls on tcs_dtls.COURSE_SESSION_ID=tcs.COURSE_SESSION_ID where COURSE_ID="+feed.getCourseId()+")";
             }
             
             //Create sql query
             System.out.println("Query : " + query);
             conn = this.getConnection(dataSource);
             cstmt = conn.prepareStatement(query);
             rs = cstmt.executeQuery();
             if(rs.next())
             {
                //RESOURSE_ID,RESOURSE_NAME,RESOURCE_AUTHOR,RESOURCE_DURATION,DESC_TXT,RESOURCE_TYP_ID,METADATA,DELETED_FL,DISPLAY_NO,ENABLE_FL,CREATED_BY,LAST_USERID_CD,RESOURCE_URL,AUTHOR_IMG,THUMB_IMG 
                vo = new ResourseVO();
                vo.setResourceId(rs.getInt("RESOURSE_ID"));
                vo.setResourceName(rs.getString("RESOURSE_NAME"));
                vo.setResourceDesc(rs.getString("DESC_TXT"));
                vo.setAuthorName(rs.getString("RESOURCE_AUTHOR"));
                vo.setThumbUrl(rs.getString("THUMB_IMG"));
                vo.setResourceUrl(rs.getString("RESOURCE_URL"));
                vo.setAuthorImg(rs.getString("AUTHOR_IMG"));
                 
             }
             
         } catch (Exception e) {
             System.out.println("Error > getDefaultResourseDetail - "+e.getMessage());
         } finally {
             closeResources(conn, cstmt, rs);
         }

         return vo;        
     }
    
    
    @Override
    public CourseVO getCourseDetail(int feedId) throws LmsDaoException {
        /**
         * 1.Get Feed Details
         * 2. Default resources : If (feed_ref = course_txn) Course Duration & start end details + first running video
         *                        else  first resource of master data.
         */
            
         CourseVO vo = null;
         Connection conn = null;
         PreparedStatement cstmt = null;
         ResultSet rs = null;

         try {
             //COURSE_ID,COURSE_NAME,COURSE_AUTHOR,AUTHOR_IMG,START_SESSION_TM,END_SESSION_TM,STATUS_TXT,COURSE_SESSION_ID
             String query_course="SELECT tcs.COURSE_ID,cmstr.COURSE_NAME,cmstr.COURSE_AUTHOR,cmstr.AUTHOR_IMG,tcsession.START_SESSION_TM,tcsession.END_SESSION_TM,tcsession.STATUS_TXT,tcsession.COURSE_SESSION_ID  FROM teacher_courses tcs inner join teacher_course_sessions tcsession on tcs.TEACHER_COURSE_ID=tcsession.TEACHER_COURSE_ID inner join course_mstr cmstr on tcs.COURSE_ID=cmstr.COURSE_ID where tcs.COURSE_ID=?";
             String query_modules="";
             String query_resources="";

             // Get Feed Details
             FeedVO feed = getFeedDetail(feedId);
             int courseId=feed.getCourseId();
             
             if(feed.getFeedRefName().equalsIgnoreCase(SLMSRestConstants.FEED_REF_COURSE_TXN))
             {
             
             }
             
//             
//             //Create sql query
//             System.out.println("Query : " + query);
//             conn = this.getConnection(dataSource);
//             cstmt = conn.prepareStatement(query);
//             rs = cstmt.executeQuery();
//             if(rs.next())
//             {
//
//                 
//             }
//             
             
             
         } catch (Exception e) {
             System.out.println("Error > getCourseDetail - "+e.getMessage());
         } finally {
             closeResources(conn, cstmt, rs);
         }

         return vo;        
     }

    
    
    @Override
    public AssignmentVO getAssignmentDetail(int feedId) throws LmsDaoException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
    @Override
    public CourseVO getModuleDetail(int feedId) throws LmsDaoException {
        /**
         * 1.Get Feed Details
         * 2. Default resources : If (feed_ref = course_txn) Module Duration & start end details + first running video
         *                        else  first resource of master data.
         */        
        throw new UnsupportedOperationException("Not supported yet.");
    }
    

    
    @Override
    public ResourseVO getResourseDetail(int feedId) throws LmsDaoException {
        return getDefaultResourseDetail(feedId);
    }

    
    @Override
    public boolean saveFeedComment(String commentBy, int feedId, String commentTxt) throws LmsDaoException {
        boolean status=false;
        //Database connection start
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = getConnection();
            
            String sql = "INSERT INTO lms_feed_comments(FEED_ID, COMMENT_TXT, PARENT_COMMENT_ID, COMMENT_ON, ASSOCIATE_ID, COMMENTED_BY, LAST_USERID_CD, LAST_UPDT_TM)"
                    + " VALUES (?, ?, null, current_date, 0, ?, ?, current_timestamp)";

            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, feedId);
            stmt.setString(2, commentTxt);
//            stmt.setInt(3, 0);
            stmt.setString(3, commentBy);
            stmt.setString(4, commentBy);

            stmt.executeUpdate();
            System.out.println("Inserted records into the table...");
            status = true;
        } catch (SQLException se) {
            System.out.println("saveFeedComment # " + se);
            throw new LmsDaoException(se.getMessage());
        } catch (Exception e) {
            System.out.println("saveFeedComment # " + e);
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
            
            String sql = "INSERT INTO lms_feed_comments(FEED_ID, COMMENT_TXT, PARENT_COMMENT_ID, COMMENT_ON, ASSOCIATE_ID, COMMENTED_BY, LAST_USERID_CD, LAST_UPDT_TM)"
                    + " VALUES (?, ?, ?, current_date, 0, ?, ?, current_timestamp)";

            String resource_id = getQueryConcatedResult("SELECT FEED_ID FROM lms_feed_comments where FEED_COMMENT_ID="+commentId);
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
    public boolean saveCommentLike(String likeBy, int commentId) throws LmsDaoException {
        boolean status=false;
        //Database connection start
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = getConnection();
            
            String sql = "INSERT INTO lms_feed_likes(FEED_ID, PARENT_COMMENT_ID, LIKE_ON, ASSOCIATE_ID, LIKE_BY, LAST_USERID_CD, LAST_UPDT_TM) VALUES (?, ?, current_date, 0, ?, ?, current_timestamp)";

            String resource_id = getQueryConcatedResult("SELECT FEED_ID FROM lms_feed_likes where FEED_LIKE_ID="+commentId);
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
    public boolean saveFeedLike(String likeBy, int feedId) throws LmsDaoException {
        boolean status=false;
        //Database connection start
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = getConnection();
            
            String sql = "INSERT INTO lms_feed_likes(FEED_ID, PARENT_COMMENT_ID, LIKE_ON, ASSOCIATE_ID, LIKE_BY, LAST_USERID_CD, LAST_UPDT_TM) VALUES (?, null, current_date, 0, ?, ?, current_timestamp)";

            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, feedId);
            stmt.setString(2, likeBy);
            stmt.setString(3, likeBy);

            stmt.executeUpdate();
            System.out.println("Inserted records into the table...");
            status = true;
        } catch (SQLException se) {
            System.out.println("saveFeedLike # " + se);
            throw new LmsDaoException(se.getMessage());
        } catch (Exception e) {
            System.out.println("saveFeedLike # " + e);
            throw new LmsDaoException(e.getMessage());
        } finally {
            closeResources(conn, stmt, null);
        }

        return status;
    }

   
    
}//End of class

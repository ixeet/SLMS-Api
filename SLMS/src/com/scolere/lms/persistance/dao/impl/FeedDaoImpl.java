/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scolere.lms.persistance.dao.impl;

import com.scolere.lms.application.rest.constants.SLMSRestConstants;
import com.scolere.lms.domain.exception.LmsDaoException;
import com.scolere.lms.domain.vo.UserClassMapVo;
import com.scolere.lms.domain.vo.cross.AssignmentVO;
import com.scolere.lms.domain.vo.cross.CommentVO;
import com.scolere.lms.domain.vo.cross.CourseVO;
import com.scolere.lms.domain.vo.cross.FeedVO;
import com.scolere.lms.domain.vo.cross.ResourseVO;
import com.scolere.lms.domain.vo.cross.SearchVO;
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

	private static String tempAllowedFeedUsers=null;
	
	@Override
	public List<SearchVO> getSearchList(int userId, String searchTxt,
			int offset, int noOfRecords) throws LmsDaoException {
		List<SearchVO> list = new ArrayList<SearchVO>();

		//Search categories - People,Course,Update,Assignment
		
		Connection conn = null;
		PreparedStatement cstmt = null;
		ResultSet resultSet = null;

		try {
			conn = this.getConnection(dataSource);
			
			String userTyp=getQueryConcatedResult("SELECT USER_TYPE_ID FROM user_login where USER_ID="+userId+"");
			
			String assignmentQuery="";
			if(userTyp.equalsIgnoreCase("3"))
			//	assignmentQuery="SELECT 'Assignment',asgnmnt.ASSIGNMENT_ID,asgnmnt.ASSIGNMENT_NAME,asgnmnt.DESC_TXT,sdtl.USER_ID,CONCAT(sdtl.FNAME,' ',sdtl.LNAME) FROM assignment_resource_txn txn inner join assignment asgnmnt on txn.ASSIGNMENT_ID=asgnmnt.ASSIGNMENT_ID and asgnmnt.DELETED_FL='0' inner join student_dtls sdtl on sdtl.EMAIL_ID=txn.STUDENT_ID where sdtl.USER_ID=? and asgnmnt.ASSIGNMENT_NAME like ? limit ?,?";
				assignmentQuery="SELECT 'Assignment',asgnmnt.ASSIGNMENT_ID,asgnmnt.ASSIGNMENT_NAME,asgnmnt.ASSIGNMENT_DESC_TXT,sdtl.USER_ID,CONCAT(sdtl.FNAME,' ',sdtl.LNAME) FROM assignment_resource_txn asgnmnt inner join student_dtls sdtl on sdtl.EMAIL_ID=asgnmnt.STUDENT_ID where sdtl.USER_ID=? and asgnmnt.ASSIGNMENT_NAME like ? limit ?,?"; //DB_UPDT
			else
				assignmentQuery="SELECT 'Assignment',asgnmnt.ASSIGNMENT_ID,asgnmnt.ASSIGNMENT_NAME,asgnmnt.DESC_TXT,sdtl.USER_ID,CONCAT(sdtl.FNAME,' ',sdtl.LNAME) FROM assignment_resource_txn txn inner join assignment asgnmnt on txn.ASSIGNMENT_ID=asgnmnt.ASSIGNMENT_ID and asgnmnt.DELETED_FL='0' inner join student_dtls sdtl on sdtl.EMAIL_ID=txn.STUDENT_ID where sdtl.USER_ID in (SELECT distinct sdtl.USER_ID FROM user_cls_map ucm inner join student_dtls sdtl on sdtl.USER_ID=ucm.USER_ID inner join teacher_courses tc on tc.SCHOOL_ID=ucm.SCHOOL_ID and tc.CLASS_ID=ucm.CLASS_ID and tc.HRM_ID=ucm.HRM_ID inner join student_dtls sdtl2 on tc.TEACHER_ID=sdtl2.EMAIL_ID where sdtl2.USER_ID=?) and asgnmnt.ASSIGNMENT_NAME like ? limit ?,?";
			
			//Updated @ 26-10-2015 for deleted_fl
			String query="(SELECT 'People',USER_ID,CONCAT(FNAME,' ',LNAME), PROFILE_IMG,'','' FROM student_dtls where CONCAT(FNAME,' ',LNAME) like ? limit ?,?) union (SELECT 'Course',COURSE_ID,COURSE_NAME,DESC_TXT,'','' FROM course_mstr where DELETED_FL='0' and COURSE_NAME like ? limit ?,?) union ("+assignmentQuery+")";
			System.out.println("Query : " + query);
			cstmt = conn.prepareStatement(query);
			cstmt.setString(1, "%"+searchTxt+"%");
			cstmt.setInt(2, offset);
			cstmt.setInt(3, noOfRecords);
			cstmt.setString(4, "%"+searchTxt+"%");
			cstmt.setInt(5, offset);
			cstmt.setInt(6, noOfRecords);
			cstmt.setInt(7, userId);
			cstmt.setString(8, "%"+searchTxt+"%");
			cstmt.setInt(9, offset);
			cstmt.setInt(10, noOfRecords);
			
			resultSet = cstmt.executeQuery();

			SearchVO vo=null;
			while (resultSet.next()) {
				vo = new SearchVO();
				vo.setSearchCategory(resultSet.getString(1));
				if(vo.getSearchCategory().equalsIgnoreCase(SLMSRestConstants.SEARCH_CAT_USER))
				{
					vo.setUserId(resultSet.getString(2));
					vo.setUserName(resultSet.getString(3));
					vo.setProfileImage(resultSet.getString(4));
					
				}else if(vo.getSearchCategory().equalsIgnoreCase(SLMSRestConstants.SEARCH_CAT_COURSE))
				{
					vo.setCourseId(resultSet.getString(2));
					vo.setCourseName(resultSet.getString(3));
					vo.setCourseDesc(resultSet.getString(4));
					
				}else{
					//Assignments
					vo.setAssignmentId(resultSet.getInt(2));
					vo.setAssignmentName(resultSet.getString(3));
					vo.setAssignmentDesc(resultSet.getString(4));			
					vo.setUserId(resultSet.getString(5));
					vo.setUserName(resultSet.getString(6));
				}
				
				list.add(vo);
			}

			System.out.println("No of Object returned : " + list.size());

		} catch (Exception e) {
			System.out.println("Error > getSearchList - " + e.getMessage());
		} finally {
			closeResources(conn, cstmt, resultSet);
		}

		return list;
	}


	@Override
	public int getSearchRecordsCount(int userId, String searchTxt,
			String category) throws LmsDaoException {
		int searchRecordsCount=0;

		//Search categories - People,Course,Update,Assignment
		
		Connection conn = null;
		PreparedStatement cstmt = null;
		ResultSet resultSet = null;

		try {
			conn = this.getConnection(dataSource);
			String query=null;
			if(category.equalsIgnoreCase(SLMSRestConstants.SEARCH_CAT_USER))
			{
				query="SELECT count(*) FROM student_dtls where CONCAT(FNAME,' ',LNAME) like ?";
			}else if(category.equalsIgnoreCase(SLMSRestConstants.SEARCH_CAT_COURSE))
			{
				query="SELECT count(*) FROM course_mstr where  DELETED_FL='0' and COURSE_NAME like ?";
			}else{
				
				String userTyp=getQueryConcatedResult("SELECT USER_TYPE_ID FROM user_login where USER_ID="+userId+"");

				if(userTyp.equalsIgnoreCase("3"))
					//query="SELECT 'Assignment',asgnmnt.ASSIGNMENT_ID,asgnmnt.ASSIGNMENT_NAME,asgnmnt.DESC_TXT,sdtl.USER_ID,CONCAT(sdtl.FNAME,' ',sdtl.LNAME) FROM assignment_resource_txn txn inner join assignment asgnmnt on txn.ASSIGNMENT_ID=asgnmnt.ASSIGNMENT_ID and asgnmnt.DELETED_FL='0' inner join student_dtls sdtl on sdtl.EMAIL_ID=txn.STUDENT_ID where sdtl.USER_ID=? and asgnmnt.ASSIGNMENT_NAME like ?";
					query="SELECT 'Assignment',asgnmnt.ASSIGNMENT_ID,asgnmnt.ASSIGNMENT_NAME,asgnmnt.DESC_TXT,sdtl.USER_ID,CONCAT(sdtl.FNAME,' ',sdtl.LNAME) FROM assignment_resource_txn asgnmnt inner join student_dtls sdtl on sdtl.EMAIL_ID=asgnmnt.STUDENT_ID where sdtl.USER_ID=? and asgnmnt.ASSIGNMENT_NAME like ?";
				else
					query="SELECT 'Assignment',asgnmnt.ASSIGNMENT_ID,asgnmnt.ASSIGNMENT_NAME,asgnmnt.DESC_TXT,sdtl.USER_ID,CONCAT(sdtl.FNAME,' ',sdtl.LNAME) FROM assignment_resource_txn txn inner join assignment asgnmnt on txn.ASSIGNMENT_ID=asgnmnt.ASSIGNMENT_ID and asgnmnt.DELETED_FL='0' inner join student_dtls sdtl on sdtl.EMAIL_ID=txn.STUDENT_ID where sdtl.USER_ID in (SELECT distinct sdtl.USER_ID FROM user_cls_map ucm inner join student_dtls sdtl on sdtl.USER_ID=ucm.USER_ID inner join teacher_courses tc on tc.SCHOOL_ID=ucm.SCHOOL_ID and tc.CLASS_ID=ucm.CLASS_ID and tc.HRM_ID=ucm.HRM_ID inner join student_dtls sdtl2 on tc.TEACHER_ID=sdtl2.EMAIL_ID where sdtl2.USER_ID=?) and asgnmnt.ASSIGNMENT_NAME like ?";

			}
			
			System.out.println("Query : " + query);
			cstmt = conn.prepareStatement(query);
			cstmt.setString(1, "%"+searchTxt+"%");
			if(category.equalsIgnoreCase(SLMSRestConstants.SEARCH_CAT_ASSIGNMENT))
			{
			cstmt.setInt(2,userId);	
			}
			
			resultSet = cstmt.executeQuery();

			if (resultSet.next()) {
				searchRecordsCount=resultSet.getInt(1);
			}


		} catch (Exception e) {
			System.out.println("Error > getSearchRecordsCount#category - " + e.getMessage());
		} finally {
			closeResources(conn, cstmt, resultSet);
		}

		return searchRecordsCount;
	}

	
	
	@Override
	public List<SearchVO> getSearchList(int userId, String searchTxt,
			int offset, int noOfRecords,String category) throws LmsDaoException {
		List<SearchVO> list = new ArrayList<SearchVO>();

		//Search categories - People,Course,Update,Assignment
		
		Connection conn = null;
		PreparedStatement cstmt = null;
		ResultSet resultSet = null;

		try {
			conn = this.getConnection(dataSource);
			String query=null;
			if(category.equalsIgnoreCase(SLMSRestConstants.SEARCH_CAT_USER))
			{
				query="SELECT 'People',USER_ID,CONCAT(FNAME,' ',LNAME), PROFILE_IMG FROM student_dtls where CONCAT(FNAME,' ',LNAME) like ? limit ?,?";
				cstmt = conn.prepareStatement(query);
				cstmt.setString(1, "%"+searchTxt+"%");
				cstmt.setInt(2, offset);
				cstmt.setInt(3, noOfRecords);
			}else if(category.equalsIgnoreCase(SLMSRestConstants.SEARCH_CAT_COURSE))
			{
				query="SELECT 'Course',COURSE_ID,COURSE_NAME,DESC_TXT FROM course_mstr where DELETED_FL='0' and COURSE_NAME like ? limit ?,?";
				cstmt = conn.prepareStatement(query);
				cstmt.setString(1, "%"+searchTxt+"%");
				cstmt.setInt(2, offset);
				cstmt.setInt(3, noOfRecords);
			}else{
				String userTyp=getQueryConcatedResult("SELECT USER_TYPE_ID FROM user_login where USER_ID="+userId+"");
				
				if(userTyp.equalsIgnoreCase("3"))
					//query="SELECT 'Assignment',asgnmnt.ASSIGNMENT_ID,asgnmnt.ASSIGNMENT_NAME,asgnmnt.DESC_TXT,sdtl.USER_ID,CONCAT(sdtl.FNAME,' ',sdtl.LNAME) FROM assignment_resource_txn txn inner join assignment asgnmnt on txn.ASSIGNMENT_ID=asgnmnt.ASSIGNMENT_ID and asgnmnt.DELETED_FL='0' inner join student_dtls sdtl on sdtl.EMAIL_ID=txn.STUDENT_ID where sdtl.USER_ID=? and asgnmnt.ASSIGNMENT_NAME like ? limit ?,?";
					query="SELECT 'Assignment',asgnmnt.ASSIGNMENT_ID,asgnmnt.ASSIGNMENT_NAME,asgnmnt.ASSIGNMENT_DESC_TXT,sdtl.USER_ID,CONCAT(sdtl.FNAME,' ',sdtl.LNAME) FROM assignment_resource_txn asgnmnt inner join student_dtls sdtl on sdtl.EMAIL_ID=asgnmnt.STUDENT_ID where sdtl.USER_ID=? and asgnmnt.ASSIGNMENT_NAME like ? limit ?,?";
				else
					query="SELECT 'Assignment',asgnmnt.ASSIGNMENT_ID,asgnmnt.ASSIGNMENT_NAME,asgnmnt.DESC_TXT,sdtl.USER_ID,CONCAT(sdtl.FNAME,' ',sdtl.LNAME) FROM assignment_resource_txn txn inner join assignment asgnmnt on txn.ASSIGNMENT_ID=asgnmnt.ASSIGNMENT_ID and asgnmnt.DELETED_FL='0' inner join student_dtls sdtl on sdtl.EMAIL_ID=txn.STUDENT_ID where sdtl.USER_ID in (SELECT distinct sdtl.USER_ID FROM user_cls_map ucm inner join student_dtls sdtl on sdtl.USER_ID=ucm.USER_ID inner join teacher_courses tc on tc.SCHOOL_ID=ucm.SCHOOL_ID and tc.CLASS_ID=ucm.CLASS_ID and tc.HRM_ID=ucm.HRM_ID inner join student_dtls sdtl2 on tc.TEACHER_ID=sdtl2.EMAIL_ID where sdtl2.USER_ID=?) and asgnmnt.ASSIGNMENT_NAME like ? limit ?,?";
				
				//query="SELECT 'Assignment',asgnmnt.ASSIGNMENT_ID,asgnmnt.ASSIGNMENT_NAME,asgnmnt.DESC_TXT,sdtl.USER_ID,CONCAT(sdtl.FNAME,' ',sdtl.LNAME) FROM assignment_resource_txn txn inner join assignment asgnmnt on txn.ASSIGNMENT_ID=asgnmnt.ASSIGNMENT_ID and asgnmnt.DELETED_FL='0' inner join student_dtls sdtl on sdtl.EMAIL_ID=txn.STUDENT_ID where sdtl.USER_ID=? and asgnmnt.ASSIGNMENT_NAME like ? limit ?,?";
				cstmt = conn.prepareStatement(query);
				cstmt.setInt(1, userId);
				cstmt.setString(2, "%"+searchTxt+"%");
				cstmt.setInt(3, offset);
				cstmt.setInt(4, noOfRecords);
			}
			System.out.println("Query : " + query);

			
			resultSet = cstmt.executeQuery();

			SearchVO vo=null;
			while (resultSet.next()) {
				vo = new SearchVO();
				vo.setSearchCategory(resultSet.getString(1));
				if(vo.getSearchCategory().equalsIgnoreCase(SLMSRestConstants.SEARCH_CAT_USER))
				{
					vo.setUserId(resultSet.getString(2));
					vo.setUserName(resultSet.getString(3));
					vo.setProfileImage(resultSet.getString(4));
					
				}else if(vo.getSearchCategory().equalsIgnoreCase(SLMSRestConstants.SEARCH_CAT_COURSE))
				{
					vo.setCourseId(resultSet.getString(2));
					vo.setCourseName(resultSet.getString(3));
					vo.setCourseDesc(resultSet.getString(4));
					
				}else{
					//Assignments
					vo.setAssignmentId(resultSet.getInt(2));
					vo.setAssignmentName(resultSet.getString(3));
					vo.setAssignmentDesc(resultSet.getString(4));	
					vo.setUserId(resultSet.getString(5));
					vo.setUserName(resultSet.getString(6));					
				}
				
				list.add(vo);
			}

			System.out.println("No of Object returned : " + list.size());

		} catch (Exception e) {
			System.out.println("Error > getSearchList#category - " + e.getMessage());
		} finally {
			closeResources(conn, cstmt, resultSet);
		}

		return list;
	}
	
	

	@Override
	public FeedVO getFeedDetail(int userId, int feedId) throws LmsDaoException {
			
		  FeedVO vo=null;

	      Connection conn = null;
	      PreparedStatement cstmt = null;
	      ResultSet rs = null;

	      try {
	          conn = this.getConnection(dataSource);
	          
	          String query = "SELECT lf_typ.FEED_TEMPLATE,lf_typ.TEMP_PARAM,lf_txn.*,(SELECT count(*) FROM lms_feed_comments where FEED_ID=lf_txn.FEED_ID and PARENT_COMMENT_ID is null) as feed_counts,(SELECT count(*) FROM lms_feed_likes where FEED_ID=lf_txn.FEED_ID and PARENT_COMMENT_ID is null) as feed_likes,(SELECT count(*) FROM lms_feed_likes where FEED_ID=lf_txn.FEED_ID and LIKE_BY = (SELECT USER_NM FROM user_login where USER_ID=?) and PARENT_COMMENT_ID is null)as LIKED_BY FROM lms_feed_txn lf_txn inner join lms_feed_type lf_typ on lf_typ.FEED_TYPE_ID=lf_txn.FEED_TYPE_ID where lf_txn.FEED_ID=?";
	          System.out.println("Query : " + query);

	          cstmt = conn.prepareStatement(query);
	          cstmt.setInt(1, userId);
	          cstmt.setInt(2, feedId);
	          
	          rs = cstmt.executeQuery();
	          //FEED_TEMPLATE,TEMP_PARAM,FEED_ID,FEED_TYPE_ID,REFRENCE_NM,USER_ID,COURSE_ID,RESOURCE_ID,ASSIGNMENT_ID,MODULE_ID,HRM_ID,LAST_USERID_CD, LAST_UPDT_TM,,feed_count,feed_likes,COMMENTED_BY    
	          if(rs.next())
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
	              vo.setActivityOn(rs.getString("LAST_UPDT_TM"));
	              vo.setCommentCounts(rs.getInt(14));
	              vo.setLikeCounts(rs.getInt(15));
	            //  vo.setCommentCounts(rs.getInt(16));

	              if(rs.getInt(16)>0) {
	                  vo.setIsLiked(true);
	              }
	              else
	              {
	            	  vo.setIsLiked(false);
	              }
	
	          }


	      } catch (Exception e) {
	          System.out.println("Error > getFeedDetail - "+e.getMessage());
	      } finally {
	          closeResources(conn, cstmt, rs);
	      }

	      return vo;        
	  }

	
    @Override
    public List<FeedVO> getNotificationsList(int userId, String searchTxt,int offset,int noOfRecords) throws LmsDaoException {

      List<FeedVO> list=new ArrayList<FeedVO>();

      Connection conn = null;
      PreparedStatement cstmt = null;
      ResultSet rs = null;

      try {
          List<Integer> usersListForSubmittedAssignmentOnly=getStudentTeachers(userId);
          //usersListForSubmittedAssignmentOnly.add(userId);
          
          String allowedFeedUsers=getFeedUsersStr(userId);

          System.out.println("People allowed for their posts : "+allowedFeedUsers);

          //String query = "SELECT lf_typ.FEED_TEMPLATE,lf_typ.TEMP_PARAM,lf_txn.*,(SELECT count(*) FROM notification_status where USER_ID=? and FEED_ID=lf_txn.FEED_ID and STATUS='1') as viewStatus FROM lms_feed_txn lf_txn inner join lms_feed_type lf_typ on lf_typ.FEED_TYPE_ID=lf_txn.FEED_TYPE_ID where concat(lf_typ.FEED_TEMPLATE,' ',lf_txn.LAST_USERID_CD) like ? and lf_txn.USER_ID in ("+allowedFeedUsers+") order by lf_txn.LAST_UPDT_TM desc LIMIT ?,?";
          //Introduce view
          String query = "SELECT lf_typ.FEED_TEMPLATE,lf_typ.TEMP_PARAM,lf_txn.*,(SELECT count(*) FROM notification_status where USER_ID=? and FEED_ID=lf_txn.FEED_ID and STATUS='1') as viewStatus FROM lms_feed_txn lf_txn inner join lms_feed_type lf_typ on lf_typ.FEED_TYPE_ID=lf_txn.FEED_TYPE_ID where lf_txn.HRM_ID in (SELECT HRM_ID FROM user_cls_map where USER_ID="+userId+" union SELECT HRM_ID FROM teacher_courses where TEACHER_ID=(SELECT USER_NM FROM user_login where USER_ID="+userId+")) AND lf_txn.FEED_ID in (SELECT FEED_ID FROM feed_search_view where search_content like ?) and lf_txn.USER_ID in ("+allowedFeedUsers+") order by lf_txn.LAST_UPDT_TM desc LIMIT ?,?";
          System.out.println("Query : " + query);

          conn = this.getConnection(dataSource);
          cstmt = conn.prepareStatement(query);
          cstmt.setInt(1, userId);
          cstmt.setString(2, "%"+searchTxt+"%");
          cstmt.setInt(3, offset);
          cstmt.setInt(4, noOfRecords);
          
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
              vo.setActivityOn(rs.getString("LAST_UPDT_TM"));
              
              if(rs.getInt(14)>0)
            	  vo.setViewStatus("1");
              else
            	  vo.setViewStatus("0");
              
              //Overriding feed display setting for submitted assignment
              if(vo.getFeedTypeID()==2)
              {
            	  if(usersListForSubmittedAssignmentOnly.contains(userId) || userId==vo.getUserId())
            		  list.add(vo);
              }
              else
              {
            	  list.add(vo);
              }
          }

          System.out.println("No of notifications returned : "+list.size());

      } catch (Exception e) {
          System.out.println("Error > getNotificationsList - "+e.getMessage());
          throw new LmsDaoException(e.getMessage());
      } finally {
          closeResources(conn, cstmt, rs);
      }

      return list;        
  }

	
    @Override
    public List<FeedVO> getFeedsList(int userId, String searchTxt,int offset,int noOfRecords) throws LmsDaoException {

      List<FeedVO> list=new ArrayList<FeedVO>();

      Connection conn = null;
      PreparedStatement cstmt = null;
      ResultSet rs = null;

      try {
          conn = this.getConnection(dataSource);
          
          List<Integer> usersListForSubmittedAssignmentOnly=getStudentTeachers(userId);
         // usersListForSubmittedAssignmentOnly.add(userId);
          
          String allowedFeedUsers=getFeedUsersStr(userId);
 
          System.out.println("People allowed for their posts : "+allowedFeedUsers);
          
          //SELECT lf_typ.FEED_TEMPLATE,lf_typ.TEMP_PARAM,lf_txn.*,(SELECT count(*) FROM lms_feed_comments where FEED_ID=lf_txn.FEED_ID and PARENT_COMMENT_ID is null) as feed_counts,(SELECT count(*) FROM lms_feed_likes where FEED_ID=lf_txn.FEED_ID and PARENT_COMMENT_ID is null) as feed_likes,(SELECT count(*) FROM lms_feed_likes where FEED_ID=lf_txn.FEED_ID and LIKE_BY = ulogin.USER_NM)as LIKED_BY FROM lms_feed_txn lf_txn inner join lms_feed_type lf_typ on lf_typ.FEED_TYPE_ID=lf_txn.FEED_TYPE_ID inner join user_login ulogin on lf_txn.LAST_USERID_CD=ulogin.USER_NM inner join user_cls_map ucmap on ucmap.USER_ID=ulogin.USER_ID where ucmap.HRM_ID = (SELECT HRM_ID FROM user_cls_map where USER_ID=?) and ucmap.CLASS_ID = (SELECT CLASS_ID FROM user_cls_map where USER_ID=?)
          String query = "SELECT lf_typ.FEED_TEMPLATE,lf_typ.TEMP_PARAM,lf_txn.*,(SELECT count(*) FROM lms_feed_comments where FEED_ID=lf_txn.FEED_ID and PARENT_COMMENT_ID is null) as feed_counts,(SELECT count(*) FROM lms_feed_likes where FEED_ID=lf_txn.FEED_ID and PARENT_COMMENT_ID is null) as feed_likes,(SELECT count(*) FROM lms_feed_likes where FEED_ID=lf_txn.FEED_ID and LIKE_BY = (SELECT USER_NM FROM user_login where USER_ID=?) and PARENT_COMMENT_ID is null)as LIKED_BY FROM lms_feed_txn lf_txn inner join lms_feed_type lf_typ on lf_typ.FEED_TYPE_ID=lf_txn.FEED_TYPE_ID where lf_txn.HRM_ID in (SELECT HRM_ID FROM user_cls_map where USER_ID="+userId+" union SELECT HRM_ID FROM teacher_courses where TEACHER_ID=(SELECT USER_NM FROM user_login where USER_ID="+userId+")) AND lf_txn.USER_ID in ("+allowedFeedUsers+") order by lf_txn.LAST_UPDT_TM desc LIMIT ?,?";
          System.out.println("Query : " + query);

          cstmt = conn.prepareStatement(query);
          cstmt.setInt(1, userId);
          cstmt.setInt(2, offset);
          cstmt.setInt(3, noOfRecords);
          
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
              vo.setActivityOn(rs.getString("LAST_UPDT_TM"));
              vo.setCommentCounts(rs.getInt(14));
              vo.setLikeCounts(rs.getInt(15));
            //  vo.setCommentCounts(rs.getInt(16));

              if(rs.getInt(16)>0) {
                  vo.setIsLiked(true);
              }
              else
              {
            	  vo.setIsLiked(false);
              }
              
              //Overriding feed display setting for submitted assignment
              if(vo.getFeedTypeID()==2)
              {
            	  if(usersListForSubmittedAssignmentOnly.contains(userId) || userId==vo.getUserId())
            		  list.add(vo);
              }
              else
              {
            	  list.add(vo);
              }
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
    public List<CommentVO> getFeedChildCommentsList(int commentId,int userId,int offset,int noOfRecords) throws LmsDaoException {

      List<CommentVO> list=new ArrayList<CommentVO>();

      Connection conn = null;
      PreparedStatement cstmt = null;
      ResultSet rs = null;

      try {
    	// USERNAME,PROFILE_IMG,USER_ID,FEED_COMMENT_ID,PARENT_COMMENT_ID,COMMENT_TXT,LAST_UPDT_TM,FEED_COMMENT_ID,commentCount,likeCount,userLike
    	  
          String query = "SELECT CONCAT(stdl.FNAME,' ',stdl.LNAME)AS USERNAME,stdl.PROFILE_IMG,stdl.USER_ID,lfc.FEED_COMMENT_ID,lfc.PARENT_COMMENT_ID,lfc.COMMENT_TXT,lfc.LAST_UPDT_TM,FEED_COMMENT_ID,(SELECT count(*) FROM lms_feed_comments where PARENT_COMMENT_ID=lfc.FEED_COMMENT_ID) as commentCount,(SELECT  count(*) FROM lms_feed_likes where PARENT_COMMENT_ID=lfc.FEED_COMMENT_ID) as likeCount, (SELECT  count(*) FROM lms_feed_likes where PARENT_COMMENT_ID=lfc.FEED_COMMENT_ID and LAST_USERID_CD=(SELECT USER_NM FROM user_login where USER_ID=?)) as userLike FROM lms_feed_comments lfc INNER JOIN student_dtls stdl ON lfc.COMMENTED_BY=stdl.EMAIL_ID where PARENT_COMMENT_ID =? order by lfc.LAST_UPDT_TM desc LIMIT ?,?";
          System.out.println("Query : " + query);

          conn = this.getConnection(dataSource);
          cstmt = conn.prepareStatement(query);
          cstmt.setInt(1, userId);
          cstmt.setInt(2, commentId);
          cstmt.setInt(3, offset);
          cstmt.setInt(4, noOfRecords);
          
          rs = cstmt.executeQuery();
          
          CommentVO vo = null;
          while(rs.next())
          {
        	     vo = new CommentVO();
        	     vo.setCommentBy(rs.getString(1));
        	     vo.setCommentByImage(rs.getString(2));
        	     vo.setCommentById(rs.getInt(3));
        	     vo.setCommentId(rs.getInt(4));
        	     vo.setParentCommentId(rs.getInt(5));
        	     vo.setCommentTxt(rs.getString(6)); 
        	     vo.setCommentDate(rs.getString(7));
        	     vo.setCommentCounts(rs.getInt(9));
        	     vo.setLikeCounts(rs.getInt(10));
        	     if(rs.getInt(11) > 0)
        	     {
        	    	 vo.setLiked(true);
        	     }else{
        	    	 vo.setLiked(false);
        	     }
        	     
             
             list.add(vo);
          }

          System.out.println("No of Feed child Comments returned : "+list.size());

      } catch (Exception e) {
          System.out.println("Error > getFeedChildCommentsList - "+e.getMessage());
      } finally {
          closeResources(conn, cstmt, rs);
      }

      return list;        
  }

    
	@Override
	public List<CommentVO> getFeedCommentsList(int feedId, int userId,int offset,int noOfRecords)
			throws LmsDaoException {

		List<CommentVO> list = new ArrayList<CommentVO>();

		Connection conn = null;
		PreparedStatement cstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			// USERNAME,PROFILE_IMG,USER_ID,FEED_COMMENT_ID,PARENT_COMMENT_ID,COMMENT_TXT,LAST_UPDT_TM,FEED_COMMENT_ID,commentCount,likeCount,userLike  
			//
			String query = "SELECT CONCAT(stdl.FNAME,' ',stdl.LNAME)AS USERNAME,stdl.PROFILE_IMG,stdl.USER_ID,lfc.FEED_COMMENT_ID,lfc.PARENT_COMMENT_ID,lfc.COMMENT_TXT,lfc.LAST_UPDT_TM,FEED_COMMENT_ID,(SELECT count(*) FROM lms_feed_comments where PARENT_COMMENT_ID=lfc.FEED_COMMENT_ID) as commentCount,(SELECT  count(*) FROM lms_feed_likes where PARENT_COMMENT_ID=lfc.FEED_COMMENT_ID) as likeCount, (SELECT  count(*) FROM lms_feed_likes where PARENT_COMMENT_ID=lfc.FEED_COMMENT_ID and LAST_USERID_CD=(SELECT USER_NM FROM user_login where USER_ID=?)) as userLike FROM lms_feed_comments lfc INNER JOIN student_dtls stdl ON lfc.COMMENTED_BY=stdl.EMAIL_ID where lfc.FEED_ID=? and PARENT_COMMENT_ID is null order by lfc.LAST_UPDT_TM desc LIMIT ?,?";

			System.out.println("Query : " + query);

			cstmt = conn.prepareStatement(query);
			cstmt.setInt(1, userId);
			cstmt.setInt(2, feedId);
	        cstmt.setInt(3, offset);
	        cstmt.setInt(4, noOfRecords);
	          
			rs = cstmt.executeQuery();

			CommentVO vo = null;
			while (rs.next()) {
				vo = new CommentVO();
				vo.setCommentBy(rs.getString(1));
				vo.setCommentByImage(rs.getString(2));
				vo.setCommentById(rs.getInt(3));
				vo.setCommentId(rs.getInt(4));
				vo.setParentCommentId(rs.getInt(5));
				vo.setCommentTxt(rs.getString(6));
				vo.setCommentDate(rs.getString(7));
				vo.setCommentCounts(rs.getInt(9));
				vo.setLikeCounts(rs.getInt(10));

				if (rs.getInt(11) > 0) {
					vo.setLiked(true);
				}

				list.add(vo);
			}

			System.out.println("No of Feed Comments returned : " + list.size());

		} catch (Exception e) {
			System.out.println("Error > getFeedCommentsList - "
					+ e.getMessage());
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
        String query="SELECT CONCAT(temp.FNAME,' ',temp.LNAME,'#',temp.USER_ID) FROM user_login usr inner join (SELECT USER_ID, TITLE, FNAME, LNAME, EMAIL_ID, CONTACT_NO, BIRTH_DT, JOINING_DATE, PROFILE_IMG FROM student_dtls union SELECT USER_ID,'', FNAME, LNAME, EMAIL_ID, CONTACT_NO, BIRTH_DT, JOINING_DATE,'Teacher' FROM student_dtls) temp on temp.USER_ID = usr.USER_ID where usr.USER_ID="+userId;
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
    public String getHomeRoomFeedText(int hrmId) throws LmsDaoException {
        String query="SELECT CONCAT(HRM_NAME,'#',HRM_ID) FROM homeroom_mstr where HRM_ID="+hrmId;
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
             //updated@26-10-2015 for deleted_fl
        	 String query="SELECT sdtl.USER_ID,ulogin.USER_NM,ulogin.USER_FB_ID,sdtl.TITLE,sdtl.FNAME,sdtl.LNAME,sdtl.EMAIL_ID,sdtl.ADMIN_EMAIL_ID,sdtl.PROFILE_IMG,smstr.SCHOOL_ID,smstr.SCHOOL_NAME,cmstr.CLASS_ID,cmstr.CLASS_NAME,hmstr.HRM_ID,hmstr.HRM_NAME FROM user_login ulogin inner join student_dtls sdtl on ulogin.USER_ID= sdtl.USER_ID inner join user_cls_map ucm on sdtl.USER_ID=ucm.USER_ID inner join class_mstr cmstr on cmstr.CLASS_ID=ucm.CLASS_ID and cmstr.DELETED_FL='0' inner join school_mstr smstr on smstr.SCHOOL_ID=ucm.SCHOOL_ID and smstr.DELETED_FL='0' inner join homeroom_mstr hmstr on hmstr.HRM_ID=ucm.HRM_ID and hmstr.DELETED_FL='0' where ulogin.USER_NM=?";
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
        	 //Updated@26-10-2015 for deleted_fl
             String query="SELECT sdtl.USER_ID,ulogin.USER_NM,ulogin.USER_FB_ID,sdtl.TITLE,sdtl.FNAME,sdtl.LNAME,sdtl.EMAIL_ID,sdtl.ADMIN_EMAIL_ID,sdtl.PROFILE_IMG,smstr.SCHOOL_ID,smstr.SCHOOL_NAME,cmstr.CLASS_ID,cmstr.CLASS_NAME,hmstr.HRM_ID,hmstr.HRM_NAME FROM user_login ulogin inner join student_dtls sdtl on ulogin.USER_ID= sdtl.USER_ID inner join user_cls_map ucm on sdtl.USER_ID=ucm.USER_ID inner join class_mstr cmstr on cmstr.CLASS_ID=ucm.CLASS_ID and cmstr.DELETED_FL='0' inner join school_mstr smstr on smstr.SCHOOL_ID=ucm.SCHOOL_ID and smstr.DELETED_FL='0' inner join homeroom_mstr hmstr on hmstr.HRM_ID=ucm.HRM_ID and hmstr.DELETED_FL='0' where ulogin.USER_ID=?";
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
                query="SELECT * FROM resourse_mstr where DELETED_FL='0' and RESOURSE_ID ="+feed.getResourseId(); 
             }
             else if(feed.getAssignmentId() > 0)
             {
                 query="SELECT * FROM resourse_mstr where DELETED_FL='0' and RESOURSE_ID = (SELECT txn.UPLODED_RESOURCE_ID FROM assignment_resource_txn txn inner join user_login login on txn.STUDENT_ID=login.USER_NM where login.USER_ID="+feed.getUserId()+" and ASSIGNMENT_ID="+feed.getAssignmentId()+")";
             }             
             else if(feed.getModuleId() > 0)
             {
                 query="SELECT * FROM resourse_mstr where DELETED_FL='0' and RESOURSE_ID = (SELECT RESOURCE_ID FROM module_resource_map where MODULE_ID="+feed.getModuleId()+" order by RESOURCE_ID desc limit 1)";
             }
             else if(feed.getCourseId() > 0)
             {
                 query="SELECT * FROM resourse_mstr where DELETED_FL='0' and RESOURSE_ID = (SELECT distinct mod_dtl.CONTENT_ID FROM teacher_courses tcourse inner join teacher_course_sessions tcs on tcs.TEACHER_COURSE_ID = tcourse.TEACHER_COURSE_ID inner join teacher_course_session_dtls tcs_dtls on tcs_dtls.COURSE_SESSION_ID=tcs.COURSE_SESSION_ID inner join teacher_module_session_dtls mod_dtl on tcs_dtls.COURSE_SESSION_DTLS_ID=mod_dtl.COURSE_SESSION_DTLS_ID where tcourse.COURSE_ID="+feed.getCourseId()+" limit 1)";
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
             throw new LmsDaoException("Error > getDefaultResourseDetail - "+e.getMessage());
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

         return vo;        
     }

    
    
    @Override
    public AssignmentVO getAssignmentDetail(int feedId) throws LmsDaoException {
    	AssignmentVO vo = null;
        Connection conn = null;
        PreparedStatement cstmt = null;
        ResultSet resultSet = null;

        try {
            // Get Feed Details
            FeedVO feed = getFeedDetail(feedId);
           
            if(feed != null)
            {
			String query="SELECT asgnmnt.ASSIGNMENT_ID,asgnmnt.ASSIGNMENT_NAME,asgnmnt.ASSIGNMENT_DESC_TXT,sdtl.USER_ID,CONCAT(sdtl.FNAME,' ',sdtl.LNAME),asgnmnt.IS_COMPLETED,TIMESTAMP(asgnmnt.DUE_ON) FROM assignment_resource_txn asgnmnt inner join student_dtls sdtl on sdtl.EMAIL_ID=asgnmnt.STUDENT_ID where sdtl.USER_ID=? and asgnmnt.ASSIGNMENT_ID = ?";
            System.out.println("Query : " + query);
            
            conn = this.getConnection(dataSource);
            cstmt = conn.prepareStatement(query);
			cstmt.setInt(1, feed.getUserId());
			cstmt.setInt(2, feed.getAssignmentId());
			
            resultSet = cstmt.executeQuery();
            if(resultSet.next())
            {
            	vo=new AssignmentVO();
				vo.setAssignmentId(resultSet.getInt(1));
				vo.setAssignmentName(resultSet.getString(2));
				vo.setAssignmentDesc(resultSet.getString(3));	
				vo.setAssignmentSubmittedById(resultSet.getInt(4));
				vo.setAssignmentSubmittedBy(resultSet.getString(5));	   
				vo.setAssignmentStatus(resultSet.getString(6));
				vo.setAssignmentDueDate(resultSet.getString(7));
            }
            
            }
        } catch (Exception e) {
            System.out.println("Error > getAssignmentDetail - "+e.getMessage());
        } finally {
            closeResources(conn, cstmt, resultSet);
        }

        return vo;        
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
                    + " VALUES (?, ?, null, current_date, 0, ?, ?, utc_timestamp)";

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
                    + " VALUES (?, ?, ?, current_date, 0, ?, ?, utc_timestamp)";

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
            
            String sql = "INSERT INTO lms_feed_likes(FEED_ID, PARENT_COMMENT_ID, LIKE_ON, ASSOCIATE_ID, LIKE_BY, LAST_USERID_CD, LAST_UPDT_TM) VALUES (?, ?, current_date, 0, ?, ?, utc_timestamp)";

            String resource_id = getQueryConcatedResult("SELECT FEED_ID FROM lms_feed_comments where FEED_COMMENT_ID="+commentId);
            System.out.println("Feed id = "+resource_id);

            int feedId= Integer.parseInt(resource_id);

            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, feedId);
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
            
            String sql = "INSERT INTO lms_feed_likes(FEED_ID, PARENT_COMMENT_ID, LIKE_ON, ASSOCIATE_ID, LIKE_BY, LAST_USERID_CD, LAST_UPDT_TM) VALUES (?, null, current_date, 0, ?, ?, utc_timestamp)";

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
    

	@Override
	public long getTotalFeedsCount(int userId,String searchText) throws LmsDaoException {
		
        String allowedFeedUsers=getFeedUsersStr(userId);

        System.out.println("People allowed for their posts : "+allowedFeedUsers);
        
       // String query = "SELECT count(*) FROM lms_feed_txn lf_txn inner join lms_feed_type lf_typ on lf_typ.FEED_TYPE_ID=lf_txn.FEED_TYPE_ID where concat(lf_typ.FEED_TEMPLATE,' ',lf_txn.LAST_USERID_CD) like '%"+searchText+"%' and lf_txn.USER_ID in ("+allowedFeedUsers+")";-
        String query = "SELECT count(*) FROM lms_feed_txn lf_txn inner join lms_feed_type lf_typ on lf_typ.FEED_TYPE_ID=lf_txn.FEED_TYPE_ID where lf_txn.HRM_ID in (SELECT HRM_ID FROM user_cls_map where USER_ID="+userId+" union SELECT HRM_ID FROM teacher_courses where TEACHER_ID=(SELECT USER_NM FROM user_login where USER_ID="+userId+")) AND lf_txn.FEED_ID in (SELECT FEED_ID FROM feed_search_view where search_content like '%"+searchText+"%') and lf_txn.USER_ID in ("+allowedFeedUsers+")";
        
        System.out.println("Query : " + query);

        long count=Long.parseLong(getQueryConcatedResult(query));
        
        return count;
	}
	
    
	@Override
	public long getTotalFeedsCount(int userId) throws LmsDaoException {
        String allowedFeedUsers=getFeedUsersStr(userId);

        System.out.println("People allowed for their posts : "+allowedFeedUsers);
        
        String query = "SELECT count(*) FROM lms_feed_txn lf_txn inner join lms_feed_type lf_typ on lf_typ.FEED_TYPE_ID=lf_txn.FEED_TYPE_ID where lf_txn.HRM_ID in (SELECT HRM_ID FROM user_cls_map where USER_ID="+userId+" union SELECT HRM_ID FROM teacher_courses where TEACHER_ID=(SELECT USER_NM FROM user_login where USER_ID="+userId+")) AND lf_txn.USER_ID in ("+allowedFeedUsers+")";
        System.out.println("Query : " + query);

        long count=Long.parseLong(getQueryConcatedResult(query));
        return count;
	}
	
	@Override
	public int getUnreadFeedsCount(int userId) throws LmsDaoException {
        String allowedFeedUsers=getFeedUsersStr(userId);

        System.out.println("People allowed for their posts : "+allowedFeedUsers);
        
        String query = "SELECT count(FEED_ID) FROM lms_feed_txn where USER_ID in("+allowedFeedUsers+") and LAST_UPDT_TM > (select if((SELECT LAST_UPDATED_TM FROM notification_status where USER_ID="+userId+" order by LAST_UPDATED_TM desc limit 1)!='',(SELECT LAST_UPDATED_TM FROM notification_status where USER_ID="+userId+" order by LAST_UPDATED_TM desc limit 1),'2000-01-01'))";
        System.out.println("Query : " + query);
        int count=Integer.parseInt(getQueryConcatedResult(query));
        return count;
	}	

	@Override
	public long getTotalCommentsCount(int feedId)
			throws LmsDaoException {
        String query="SELECT count(*) FROM lms_feed_comments where FEED_ID="+feedId+" and PARENT_COMMENT_ID is null";
        long count=Long.parseLong(getQueryConcatedResult(query));
        return count;
	}

	@Override
	public long getTotalCommentsCount(int feedId, int commentId)
			throws LmsDaoException {
        String query="SELECT count(*) FROM lms_feed_comments where FEED_ID="+feedId+" and PARENT_COMMENT_ID="+commentId;
        long count=Long.parseLong(getQueryConcatedResult(query));
        return count;
	}


	@Override
	public long updateNotificationStatus(int userId, int feedId, String status)
			throws LmsDaoException {
        long count=0;
        //Database connection start
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = getConnection();
            
            String sql = "INSERT INTO notification_status(USER_ID, FEED_ID, STATUS)VALUES(?, ?, ?)";

            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, userId);
            stmt.setInt(2, feedId);
            stmt.setString(3, status);

            count=stmt.executeUpdate();
            System.out.println("Inserted records into the table..."+count);
            
        } catch (SQLException se) {
            System.out.println("updateNotificationStatus # " + se);
            throw new LmsDaoException(se.getMessage());
        } catch (Exception e) {
            System.out.println("updateNotificationStatus # " + e);
            throw new LmsDaoException(e.getMessage());
        } finally {
            closeResources(conn, stmt, null);
        }

        return count;
	}

	
	private String getFeedUsersStr(int userId) throws LmsDaoException {
		String userStr="0";
		
		if(tempAllowedFeedUsers != null && tempAllowedFeedUsers.contains(String.valueOf(userId)))
		{
			userStr=tempAllowedFeedUsers;
			System.out.println("Return cached feed users.");
		}else{
			userStr=getFeedUsersStrOld(userId);			
		}
		
		return userStr;
	}	
	
	
	private String getFeedUsersStrOld(int userId) throws LmsDaoException {
		
		String userStr="0";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			
			//Get access_for_id : 1)school 2)district 3)class 4)home group + userId
			String accessTypFor=getQueryConcatedResult("SELECT concat(access_type_id,'~',access_for_id) FROM feed_user_access where user_id="+userId);
			if(accessTypFor != null && !accessTypFor.isEmpty())
			{
			String[] accessTypForArr=accessTypFor.split("~");	
			
			//Start getting userslist as per access type
			int accessType=Integer.parseInt(accessTypForArr[0]);
			UserClassMapVo userCls=getUserClassMapDetail(userId);
			
			StringBuffer userListQry=new StringBuffer("SELECT USER_ID FROM user_cls_map where SCHOOL_ID=").append(userCls.getSchoolId()); //Default school
			if(accessType==1) //School
			{
				//No Action
			}else if(accessType==2) //district
			{
				//Not yet implemented
			}else if(accessType==3) //class
			{
				userListQry.append(" AND CLASS_ID=").append(userCls.getClassId());
				
			}else if(accessType==4) //Home room
			{
				userListQry.append(" AND CLASS_ID=").append(userCls.getClassId());
				userListQry.append(" AND HRM_ID=").append(userCls.getHomeRoomMasterId());
			}

			//Appending query for homeroom teachers
			userListQry.append(" union ");
			userListQry.append("SELECT USER_ID FROM user_login where USER_NM=(SELECT TEACHER_ID FROM teacher_courses where SCHOOL_ID=").append(userCls.getSchoolId());
			userListQry.append(" and CLASS_ID=").append(userCls.getClassId()).append(" and HRM_ID=").append(userCls.getHomeRoomMasterId()).append("  limit 1)");
			
			conn = getConnection();
			String sql = "SELECT USER_ID,(SELECT count(*) FROM feed_restricted_users where userId=? and restricted_userId=USER_ID) as usr_count FROM student_dtls where USER_ID in ("+userListQry+")";
			System.out.println("Query created for feed users - "+sql);
			
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, userId);
			
			rs = stmt.executeQuery();
			
			StringBuffer temp=new StringBuffer();
			while (rs.next()) {
				int count=rs.getInt(2);
				if(count==0)
				{
					temp.append(",").append(rs.getString(1));//user.setIsFollowUpAllowed("0");
				}
			}
			userStr=temp.substring(1);
		  }
		} catch (SQLException se) {
			System.out.println("getFeedUsersStr # " + se);
		} catch (Exception e) {
			System.out.println("getFeedUsersStr # " + e);
		} finally {
			closeResources(conn, stmt, rs);
		}

		tempAllowedFeedUsers=userStr;
		
		return userStr;
	}
	
	
    private UserClassMapVo getUserClassMapDetail(int userId) throws LmsDaoException {
        System.out.println("Inside getUserClassDetail(?) >>");
        //Create object to return
        UserClassMapVo userDtls = new UserClassMapVo();

        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = getConnection();

            String sql = "SELECT USER_ID,SCHOOL_ID,CLASS_ID,HRM_ID FROM user_cls_map where USER_ID=? union SELECT ulogin.USER_ID,tcs.SCHOOL_ID,tcs.CLASS_ID,tcs.HRM_ID FROM teacher_courses tcs inner join user_login ulogin on tcs.TEACHER_ID=ulogin.USER_NM where ulogin.USER_ID=? limit 1";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, userId);
            stmt.setInt(2, userId);
            
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                userDtls.setUserId(rs.getInt(1));
                userDtls.setSchoolId(rs.getInt(2));
                userDtls.setClassId(rs.getInt(3));
                userDtls.setHomeRoomMasterId(rs.getInt(4));
            }

        } catch (SQLException se) {
            System.out.println("getUserClassMapDetail # " + se);
        } catch (Exception e) {
            System.out.println("getUserClassMapDetail # " + e);
        } finally {
            closeResources(conn, stmt, null);
        }
        
        return userDtls;
    }
	
    
    /**
     * This method returns teacher list of student to whom student can submit assignment.
     * 
     * @param userId
     * @return
     */
    private List<Integer> getStudentTeachers(int userId)
    {
    	List<Integer> teachers=new ArrayList<Integer>();
    	
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = getConnection();

            String sql = "SELECT distinct (SELECT USER_ID FROM user_login where USER_NM=tcs.TEACHER_ID)as USER_ID FROM teacher_courses tcs inner join user_cls_map ucm on ucm.SCHOOL_ID=tcs.SCHOOL_ID and ucm.CLASS_ID=tcs.CLASS_ID and ucm.HRM_ID=tcs.HRM_ID where ucm.USER_ID=? union SELECT USER_ID FROM user_login where USER_TYPE_ID=2 and USER_ID=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, userId);
            stmt.setInt(2, userId);
            
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
            	teachers.add(rs.getInt(1));
            }

        } catch (SQLException se) {
            System.out.println("getTeacherIdToAssignmentSubmitted # " + se);
        } catch (Exception e) {
            System.out.println("getTeacherIdToAssignmentSubmitted # " + e);
        } finally {
            closeResources(conn, stmt, null);
        }
        
    	return teachers;
    }
    
    
    
}//End of class

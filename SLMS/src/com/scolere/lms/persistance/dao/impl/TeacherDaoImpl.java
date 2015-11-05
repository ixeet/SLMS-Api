package com.scolere.lms.persistance.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.scolere.lms.domain.exception.LmsDaoException;
import com.scolere.lms.persistance.dao.iface.TeacherDao;
import com.scolere.lms.persistance.factory.LmsDaoAbstract;


public class TeacherDaoImpl extends LmsDaoAbstract implements TeacherDao{

	@Override
	public int updateCourseStatus(int courseSessionId,int statusCode) throws LmsDaoException {
		int updateCount=0;
		
		/*
		if(statusCode==1)
		{
			//Course completed
			String queryUpdateCourseStatus="update teacher_course_sessions set IS_COMPLETE='1',END_SESSION_TM=current_timestamp,LAST_UPDT_TM=current_timestamp where COURSE_SESSION_ID="+courseSessionId;
			updateCount = updateByQuery(queryUpdateCourseStatus);
			System.out.println("queryUpdateCourseStatus #1 status = "+updateCount);		
			
            //Create A feed | lms_feed_type = 4 |  $ has completed course $      <-   user,course 
            String feedQuery = "INSERT INTO lms_feed_txn(FEED_TYPE_ID, REFRENCE_NM, USER_ID, COURSE_ID, RESOURCE_ID, ASSIGNMENT_ID, MODULE_ID, HRM_ID, LAST_USERID_CD, LAST_UPDT_TM) SELECT  4,'Course Completed',(SELECT USER_ID FROM user_login where USER_NM=tc.TEACHER_ID) as user_id,tc.COURSE_ID,null,null,null,tc.HRM_ID,tc.TEACHER_ID,current_timestamp FROM teacher_courses tc inner join teacher_course_sessions crs on tc.TEACHER_COURSE_ID=crs.TEACHER_COURSE_ID where crs.COURSE_SESSION_ID="+courseSessionId;
            boolean feedStatus = deleteOrUpdateByQuery(feedQuery);
            System.out.println("Feed Creation ? "+feedStatus);					
		}
		else 
			*/
		
		if(statusCode==0)
		{
			//Course started
			String queryUpdateCourseStatus="update teacher_course_sessions set IS_COMPLETE='0',START_SESSION_TM=current_timestamp,LAST_UPDT_TM=current_timestamp where IS_COMPLETE='2' and COURSE_SESSION_ID="+courseSessionId;
			updateCount = updateByQuery(queryUpdateCourseStatus);
			System.out.println("queryUpdateCourseStatus #2 status = "+updateCount);		
			
			if(updateCount>0)
			{
            //Create A feed | lms_feed_type = 5 |  $ has started course $      <-   user,course 
            String feedQuery = "INSERT INTO lms_feed_txn(FEED_TYPE_ID, REFRENCE_NM, USER_ID, COURSE_ID, RESOURCE_ID, ASSIGNMENT_ID, MODULE_ID, HRM_ID, LAST_USERID_CD, LAST_UPDT_TM) SELECT  5,'Course Started',(SELECT USER_ID FROM user_login where USER_NM=tc.TEACHER_ID) as user_id,tc.COURSE_ID,null,null,null,tc.HRM_ID,tc.TEACHER_ID,current_timestamp FROM teacher_courses tc inner join teacher_course_sessions crs on tc.TEACHER_COURSE_ID=crs.TEACHER_COURSE_ID where crs.COURSE_SESSION_ID="+courseSessionId;
            boolean feedStatus = deleteOrUpdateByQuery(feedQuery);
            System.out.println("Feed Creation ? "+feedStatus);	
			}
		}else{
			updateCount=-1;
		}		
		
		
	    return updateCount;
	}
	
	

	/**
	 * Course/Module status code :
	 * (2) Module not started yet
	 * (1) Module completed
	 * (0) Module in progress or started
	 */
	@Override
	public int updateCourseModuleStatus(int moduleSessionId,int statusCode)
			throws LmsDaoException {
	     
		int updateCount=0;

		if(statusCode==1)
		{
		//update module status
		String queryUpdateModuleStatus="update teacher_course_session_dtls set IS_COMPLETED='1',END_SESSION_TM=current_timestamp,LAST_UPDT_TM=current_timestamp where IS_COMPLETED='0' and COURSE_SESSION_DTLS_ID="+moduleSessionId;
		updateCount = updateByQuery(queryUpdateModuleStatus);
		System.out.println("Module update status = "+updateCount);		
		
		if(updateCount>0)
		{
		//update all associated resources
			String queryUpdateModuleResources="update teacher_module_session_dtls set IS_COMPLETED='1',END_SESSION_TM=current_timestamp,LAST_UPDT_TM=current_timestamp where COURSE_SESSION_DTLS_ID="+moduleSessionId;
			int t1 = updateByQuery(queryUpdateModuleResources);
			System.out.println("queryUpdateModuleResources status = "+t1);			
		
		//update course status on the basis sibling modules
			String queryCountNonCompletedModules="SELECT count(*) FROM teacher_course_session_dtls where IS_COMPLETED != '1' and COURSE_SESSION_ID=(SELECT COURSE_SESSION_ID FROM teacher_course_session_dtls where COURSE_SESSION_DTLS_ID="+moduleSessionId+")";
			int nonCompletedModules=getCountQueryResult(queryCountNonCompletedModules);
			
				if(nonCompletedModules>0)
				{
					//Not all modules are completed => start course
					String queryUpdateCourseStatus="update teacher_course_sessions set IS_COMPLETE='0',START_SESSION_TM=current_timestamp where TEACHER_COURSE_ID=(SELECT COURSE_SESSION_ID FROM teacher_course_session_dtls where COURSE_SESSION_DTLS_ID="+moduleSessionId+")";
					int t2 = updateByQuery(queryUpdateCourseStatus);
					System.out.println("queryUpdateCourseStatus status = "+t2);	
					
					//New feed for starting the module  <= moduleSessionId
		            //Create A feed | lms_feed_type = 3 |  $ has started Module $ of course $  <- user,module,course
//		            String feedQuery = "INSERT INTO lms_feed_txn(FEED_TYPE_ID, REFRENCE_NM, USER_ID, COURSE_ID, RESOURCE_ID, ASSIGNMENT_ID, MODULE_ID, HRM_ID, LAST_USERID_CD, LAST_UPDT_TM) SELECT  3,'Module Started',(SELECT USER_ID FROM user_login where USER_NM=modl.TEACHER_ID) as user_id,tc.COURSE_ID,null,null,modl.MODULE_ID,tc.HRM_ID,modl.TEACHER_ID,current_timestamp FROM teacher_courses tc inner join teacher_course_sessions crs on tc.TEACHER_COURSE_ID=crs.TEACHER_COURSE_ID inner join teacher_course_session_dtls modl on crs.COURSE_SESSION_ID=modl.COURSE_SESSION_ID where modl.COURSE_SESSION_DTLS_ID="+moduleSessionId;
//		            boolean feedStatus = deleteOrUpdateByQuery(feedQuery);
//		            System.out.println("Feed Creation ? "+feedStatus);							
				}
				else
				{
					//All modules are completed
					String queryUpdateCourseStatus="update teacher_course_sessions set IS_COMPLETE='1',END_SESSION_TM=current_timestamp where TEACHER_COURSE_ID=(SELECT COURSE_SESSION_ID FROM teacher_course_session_dtls where COURSE_SESSION_DTLS_ID="+moduleSessionId+")";
					int t2 = updateByQuery(queryUpdateCourseStatus);
					System.out.println("queryUpdateCourseStatus status = "+t2);	
					
					if(t2>0)
					{
					//Feed for course completed
		            //Create A feed | lms_feed_type = 4 |  $ has completed course $  <- user,course
		            String feedQuery = "INSERT INTO lms_feed_txn(FEED_TYPE_ID, REFRENCE_NM, USER_ID, COURSE_ID, RESOURCE_ID, ASSIGNMENT_ID, MODULE_ID, HRM_ID, LAST_USERID_CD, LAST_UPDT_TM)SELECT  4,'Course Completed',(SELECT USER_ID FROM user_login where USER_NM=modl.TEACHER_ID) as user_id,tc.COURSE_ID,null,null,null,tc.HRM_ID,modl.TEACHER_ID,current_timestamp FROM teacher_courses tc inner join teacher_course_sessions crs on tc.TEACHER_COURSE_ID=crs.TEACHER_COURSE_ID inner join teacher_course_session_dtls modl on crs.COURSE_SESSION_ID=modl.COURSE_SESSION_ID where modl.COURSE_SESSION_DTLS_ID="+moduleSessionId;
		            boolean feedStatus = deleteOrUpdateByQuery(feedQuery);
		            System.out.println("Feed Creation ? "+feedStatus);
					}
				}
		}
		}else if(statusCode==0)
		{
			//Start module 
			//update module status
			String queryUpdateModule="update teacher_course_session_dtls set IS_COMPLETED='0',START_SESSION_TM=current_timestamp,LAST_UPDT_TM=current_timestamp where IS_COMPLETED='2' and COURSE_SESSION_DTLS_ID="+moduleSessionId;
			updateCount = updateByQuery(queryUpdateModule);
			System.out.println("queryUpdateModule status = "+updateCount);		
		
			//Start the associated course if it not started yet
			String queryUpdateCourse="update teacher_course_sessions set IS_COMPLETE='0',START_SESSION_TM=current_timestamp,LAST_UPDT_TM=current_timestamp where IS_COMPLETE='2' and COURSE_SESSION_ID=(SELECT COURSE_SESSION_ID FROM teacher_course_session_dtls where COURSE_SESSION_DTLS_ID="+moduleSessionId+")";
            boolean queryCourseStatus = deleteOrUpdateByQuery(queryUpdateCourse);
            System.out.println("queryUpdateCourse ? "+queryCourseStatus);
			
            if(updateCount>0)
            {
			//New feed for starting the module  <= resourceSessionId
            //Create A feed | lms_feed_type = 3 |  $ has started Module $ of course $  <- user,module,course
            String feedQuery = "INSERT INTO lms_feed_txn(FEED_TYPE_ID, REFRENCE_NM, USER_ID, COURSE_ID, RESOURCE_ID, ASSIGNMENT_ID, MODULE_ID, HRM_ID, LAST_USERID_CD, LAST_UPDT_TM)SELECT  3,'Module Started',(SELECT USER_ID FROM user_login where USER_NM=modl.TEACHER_ID) as user_id,tc.COURSE_ID,null,null,modl.MODULE_ID,tc.HRM_ID,modl.TEACHER_ID,current_timestamp FROM teacher_courses tc inner join teacher_course_sessions crs on tc.TEACHER_COURSE_ID=crs.TEACHER_COURSE_ID inner join teacher_course_session_dtls modl on crs.COURSE_SESSION_ID=modl.COURSE_SESSION_ID where modl.COURSE_SESSION_DTLS_ID="+moduleSessionId;
            boolean feedStatus = deleteOrUpdateByQuery(feedQuery);
            System.out.println("Feed Creation ? "+feedStatus);			
            }
		}
		else{
			//Need to start any resource first
			updateCount=-1;
		}
		
		
	    return updateCount;
	}
	

	/**
	 * Resource status code :
	 * (1) Resource completed
	 * (0) Resource incomplete
	 */	
	@Override
	public int updateCourseResourceStatus(int resourseSessionId,int statusCode)
			throws LmsDaoException {
	     
		int updateCount=0;

		if(statusCode==1)
		{
		//update resource status
		String queryUpdateResource="update teacher_module_session_dtls set IS_COMPLETED='1',END_SESSION_TM=current_timestamp,LAST_UPDT_TM=current_timestamp where MODULE_SESSION_DTLS_ID="+resourseSessionId;
		updateCount = updateByQuery(queryUpdateResource);
		System.out.println("queryUpdateResource status = "+updateCount);			
		
		if(updateCount>0)
		{
		
			String queryCountNonCompletedResources="SELECT count(*) FROM teacher_module_session_dtls where IS_COMPLETED!='1' and COURSE_SESSION_DTLS_ID=(SELECT COURSE_SESSION_DTLS_ID FROM teacher_module_session_dtls where MODULE_SESSION_DTLS_ID="+resourseSessionId+")";
			int countNonCompletedResources = getCountQueryResult(queryCountNonCompletedResources);
			
				if(countNonCompletedResources>0)
				{
					//Not all resources are completed ********
					
					//update module status on the basis of sibling resources
					String queryUpdateModule="update teacher_course_session_dtls set IS_COMPLETED='0',START_SESSION_TM=current_timestamp,LAST_UPDT_TM=current_timestamp where IS_COMPLETED='2' and COURSE_SESSION_DTLS_ID=(SELECT COURSE_SESSION_DTLS_ID FROM teacher_module_session_dtls where MODULE_SESSION_DTLS_ID="+resourseSessionId+")";
					int t1 = updateByQuery(queryUpdateModule);
					System.out.println("queryUpdateModule status = "+t1);		
					
					//update course status on the basis sibling modules
					String queryUpdateCourseStatus="update teacher_course_sessions set IS_COMPLETE='0',START_SESSION_TM=current_timestamp where TEACHER_COURSE_ID=(SELECT COURSE_SESSION_ID FROM teacher_course_session_dtls where COURSE_SESSION_DTLS_ID=(SELECT COURSE_SESSION_DTLS_ID FROM teacher_module_session_dtls where MODULE_SESSION_DTLS_ID="+resourseSessionId+"))";
					int t2 = updateByQuery(queryUpdateCourseStatus);
					System.out.println("queryUpdateCourseStatus status = "+t2);					
					
					if(t1>0)
					{
					//New feed for starting the module  <= resourceSessionId
		            //Create A feed | lms_feed_type = 3 |  $ has started Module $ of course $  <- user,module,course
		            String feedQuery = "INSERT INTO lms_feed_txn(FEED_TYPE_ID, REFRENCE_NM, USER_ID, COURSE_ID, RESOURCE_ID, ASSIGNMENT_ID, MODULE_ID, HRM_ID, LAST_USERID_CD, LAST_UPDT_TM)SELECT  3,'Module Started',(SELECT USER_ID FROM user_login where USER_NM=modl.TEACHER_ID) as user_id,tc.COURSE_ID,null,null,modl.MODULE_ID,tc.HRM_ID,modl.TEACHER_ID,current_timestamp FROM teacher_courses tc inner join teacher_course_sessions crs on tc.TEACHER_COURSE_ID=crs.TEACHER_COURSE_ID inner join teacher_course_session_dtls modl on crs.COURSE_SESSION_ID=modl.COURSE_SESSION_ID where modl.COURSE_SESSION_DTLS_ID=(SELECT COURSE_SESSION_DTLS_ID FROM teacher_module_session_dtls where MODULE_SESSION_DTLS_ID="+resourseSessionId+")";
		            boolean feedStatus = deleteOrUpdateByQuery(feedQuery);
		            System.out.println("Feed Creation ? "+feedStatus);		
					}
				}
				else
				{
					//All resources are completed *********
					//Complete module based on moduleSessionId
					String queryForModuleSessionId="SELECT COURSE_SESSION_DTLS_ID FROM teacher_module_session_dtls where MODULE_SESSION_DTLS_ID="+resourseSessionId;
					int moduleSessionId=getCountQueryResult(queryForModuleSessionId);
					//Update module status = 1
					updateCourseModuleStatus(moduleSessionId, 1);
				}
			
		}	
		}else{
			//Not applicable
			updateCount=-1;
		}
	    
	    return updateCount;
	}


	@Override
	public List<Integer> getCoursePercentage(String userName, int schoolId,
			int classId, int hrmId) throws LmsDaoException {
		List<Integer> list = new ArrayList<Integer>();

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();

            StringBuffer sql = new StringBuffer("SELECT tcs.IS_COMPLETE FROM teacher_courses  tc, teacher_course_sessions tcs WHERE tc.TEACHER_ID='"+userName+"' and tcs.TEACHER_COURSE_ID=tc.TEACHER_COURSE_ID");
            if(schoolId>0)
            	sql.append(" AND tc.SCHOOL_ID = ").append(schoolId);
            if(classId>0)
            	sql.append(" AND tc.CLASS_ID = ").append(classId);
            if(hrmId>0)
            	sql.append(" AND tc.HRM_ID = ").append(hrmId);
            
            System.out.println("Generated query : "+sql);
            
            stmt = conn.prepareStatement(sql.toString());
            rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(rs.getInt(1));
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
	public List<Integer> getAssignmentPercentage(String userName, int schoolId,
			int classId, int hrmId) throws LmsDaoException {
		List<Integer> list = new ArrayList<Integer>();

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();

            StringBuffer sql = new StringBuffer("SELECT distinct artxn.IS_COMPLETED, artxn.STUDENT_ID, artxn.ASSIGNMENT_ID,tc.SCHOOL_ID FROM teacher_course_session_dtls tcsd INNER JOIN module_assignment_map mam ON mam.MODULE_ID=tcsd.MODULE_ID INNER JOIN assignment_resource_txn artxn ON artxn.ASSIGNMENT_ID=mam.ASSIGNMENT_ID INNER JOIN teacher_courses tc ON tc.TEACHER_ID=tcsd.TEACHER_ID INNER JOIN teacher_course_sessions tcs on tcs.COURSE_SESSION_ID=tc.TEACHER_COURSE_ID and tcsd.COURSE_SESSION_ID = tcs.COURSE_SESSION_ID WHERE tcsd.TEACHER_ID ='"+userName+"'");
            if(schoolId>0)
            	sql.append(" AND tc.SCHOOL_ID = ").append(schoolId);
            if(classId>0)
            	sql.append(" AND tc.CLASS_ID = ").append(classId);
            if(hrmId>0)
            	sql.append(" AND tc.HRM_ID = ").append(hrmId);
            
            System.out.println("Generated query : "+sql);
            
            stmt = conn.prepareStatement(sql.toString());
            rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(rs.getInt(1));
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
	public int updateAssignmentStatus(int schoolId, int classId, int hrmId,int courseId, int moduleId, int statusCode, String userNm,
			String dueDate) throws LmsDaoException {
		int updateCount=0;

		if(statusCode==1)
		{
			//Activate assignments
			String queryUpdateAssignmentStatus="update assignment_resource_txn set ENABLE_FL='1',LAST_USERID_CD='"+userNm+"',DUE_ON='"+dueDate+"',LAST_UPDT_TM=current_timestamp where ENABLE_FL != '1' and ASSIGNMENT_ID in (SELECT mam.ASSIGNMENT_ID FROM teacher_course_session_dtls tcm inner join module_assignment_map mam on mam.MODULE_ID=tcm.MODULE_ID where tcm.TEACHER_ID='"+userNm+"' and tcm.MODULE_ID="+moduleId+") and STUDENT_ID in (SELECT USER_NM FROM user_cls_map ucm inner join user_login ulogin on ucm.USER_ID=ulogin.USER_ID where ucm.SCHOOL_ID="+schoolId+" and ucm.CLASS_ID="+classId+" and ucm.HRM_ID="+hrmId+")";
			updateCount = updateByQuery(queryUpdateAssignmentStatus);
			System.out.println("updateAssignmentStatus # status = "+updateCount);		
			
			if(updateCount>0)
			{
            //Create A feed | lms_feed_type = 6 |  Assignments for module $ of Course $ are open for submission.  <-   module,course 
            String feedQuery = "INSERT INTO lms_feed_txn(FEED_TYPE_ID, REFRENCE_NM, USER_ID, COURSE_ID, RESOURCE_ID, ASSIGNMENT_ID, MODULE_ID, HRM_ID, LAST_USERID_CD, LAST_UPDT_TM)VALUES(6,'open assignments',(SELECT USER_ID FROM user_login where USER_NM='"+userNm+"'),"+courseId+",null,null,"+moduleId+","+hrmId+",'"+userNm+"',current_timestamp)";
            boolean feedStatus = deleteOrUpdateByQuery(feedQuery);
            System.out.println("Feed Creation ? "+feedStatus);	
			}
		}else if(statusCode==0)
		{
			//Deactivate
			String queryUpdateAssignmentStatus="update assignment_resource_txn set ENABLE_FL='0',LAST_USERID_CD='"+userNm+"',DUE_ON='"+dueDate+"',LAST_UPDT_TM=current_timestamp where ASSIGNMENT_ID in (SELECT mam.ASSIGNMENT_ID FROM teacher_course_session_dtls tcm inner join module_assignment_map mam on mam.MODULE_ID=tcm.MODULE_ID where tcm.TEACHER_ID='"+userNm+"' and tcm.MODULE_ID="+moduleId+") and STUDENT_ID in (SELECT USER_NM FROM user_cls_map ucm inner join user_login ulogin on ucm.USER_ID=ulogin.USER_ID where ucm.SCHOOL_ID="+schoolId+" and ucm.CLASS_ID="+classId+" and ucm.HRM_ID="+hrmId+")";
			updateCount = updateByQuery(queryUpdateAssignmentStatus);
			System.out.println("updateAssignmentStatus # status = "+updateCount);		

		}else{
			updateCount=-1;
		}		
		
		
	    return updateCount;
	}
	

	
}//End of class


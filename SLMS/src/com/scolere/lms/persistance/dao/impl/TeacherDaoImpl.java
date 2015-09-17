package com.scolere.lms.persistance.dao.impl;

import com.scolere.lms.domain.exception.LmsDaoException;
import com.scolere.lms.persistance.dao.iface.TeacherDao;
import com.scolere.lms.persistance.factory.LmsDaoAbstract;


public class TeacherDaoImpl extends LmsDaoAbstract implements TeacherDao{

	
	@Override
	public int updateCourseStatus(int courseSessionId) throws LmsDaoException {
	     
		int updateCount=0;
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
		String queryUpdateModuleStatus="update teacher_course_session_dtls set IS_COMPLETED='1',LAST_UPDT_TM=current_timestamp where COURSE_SESSION_DTLS_ID="+moduleSessionId;
		updateCount = updateByQuery(queryUpdateModuleStatus);
		System.out.println("Module update status = "+updateCount);		
		
		if(updateCount>0)
		{
		//update all associated resources
			String queryUpdateModuleResources="update teacher_module_session_dtls set IS_COMPLETED='1',LAST_UPDT_TM=current_timestamp where COURSE_SESSION_DTLS_ID="+moduleSessionId;
			int t1 = updateByQuery(queryUpdateModuleResources);
			System.out.println("queryUpdateModuleResources status = "+t1);			
		
		//update course status on the basis sibling modules
			String queryCountNonCompletedModules="SELECT count(*) FROM teacher_course_session_dtls where IS_COMPLETED != '1' and COURSE_SESSION_ID=(SELECT COURSE_SESSION_ID FROM teacher_course_session_dtls where COURSE_SESSION_DTLS_ID="+moduleSessionId+")";
			int nonCompletedModules=getCountQueryResult(queryCountNonCompletedModules);
			
				if(nonCompletedModules>0)
				{
					//Not all modules are completed => start course
					String queryUpdateCourseStatus="update teacher_course_sessions set IS_COMPLETE='0' where TEACHER_COURSE_ID=(SELECT COURSE_SESSION_ID FROM teacher_course_session_dtls where COURSE_SESSION_DTLS_ID="+moduleSessionId+")";
					int t2 = updateByQuery(queryUpdateCourseStatus);
					System.out.println("queryUpdateCourseStatus status = "+t2);	
					
					//New feed for starting the module  <= moduleSessionId
		            //Create A feed | lms_feed_type = 3 |  $ has started Module $ of course $  <- user,module,course
		            String feedQuery = "INSERT INTO lms_feed_txn(FEED_TYPE_ID, REFRENCE_NM, USER_ID, COURSE_ID, RESOURCE_ID, ASSIGNMENT_ID, MODULE_ID, HRM_ID, LAST_USERID_CD, LAST_UPDT_TM) SELECT  3,'Module Started',(SELECT USER_ID FROM user_login where USER_NM=modl.TEACHER_ID) as user_id,tc.COURSE_ID,null,null,modl.MODULE_ID,tc.HRM_ID,modl.TEACHER_ID,current_timestamp FROM teacher_courses tc inner join teacher_course_sessions crs on tc.TEACHER_COURSE_ID=crs.TEACHER_COURSE_ID inner join teacher_course_session_dtls modl on crs.COURSE_SESSION_ID=modl.COURSE_SESSION_ID where modl.COURSE_SESSION_DTLS_ID="+moduleSessionId;
		            boolean feedStatus = deleteOrUpdateByQuery(feedQuery);
		            System.out.println("Feed Creation ? "+feedStatus);							
				}
				else
				{
					//All modules are completed
					String queryUpdateCourseStatus="update teacher_course_sessions set IS_COMPLETE='1' where TEACHER_COURSE_ID=(SELECT COURSE_SESSION_ID FROM teacher_course_session_dtls where COURSE_SESSION_DTLS_ID="+moduleSessionId+")";
					int t2 = updateByQuery(queryUpdateCourseStatus);
					System.out.println("queryUpdateCourseStatus status = "+t2);	
					
					//Feed for course completed
		            //Create A feed | lms_feed_type = 5 |  $ has completed Module $ of course $  <- user,module,course
		            String feedQuery = "INSERT INTO lms_feed_txn(FEED_TYPE_ID, REFRENCE_NM, USER_ID, COURSE_ID, RESOURCE_ID, ASSIGNMENT_ID, MODULE_ID, HRM_ID, LAST_USERID_CD, LAST_UPDT_TM)SELECT  5,'Course Completed',(SELECT USER_ID FROM user_login where USER_NM=modl.TEACHER_ID) as user_id,tc.COURSE_ID,null,null,modl.MODULE_ID,tc.HRM_ID,modl.TEACHER_ID,current_timestamp FROM teacher_courses tc inner join teacher_course_sessions crs on tc.TEACHER_COURSE_ID=crs.TEACHER_COURSE_ID inner join teacher_course_session_dtls modl on crs.COURSE_SESSION_ID=modl.COURSE_SESSION_ID where modl.COURSE_SESSION_DTLS_ID="+moduleSessionId;
		            boolean feedStatus = deleteOrUpdateByQuery(feedQuery);
		            System.out.println("Feed Creation ? "+feedStatus);
				}
		}
		
		}else{
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
		String queryUpdateResource="update teacher_module_session_dtls set IS_COMPLETED='1',LAST_UPDT_TM=current_timestamp where MODULE_SESSION_DTLS_ID="+resourseSessionId;
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
					String queryUpdateModule="update teacher_course_session_dtls set IS_COMPLETED='0',LAST_UPDT_TM=current_timestamp where COURSE_SESSION_DTLS_ID=(SELECT COURSE_SESSION_DTLS_ID FROM teacher_module_session_dtls where MODULE_SESSION_DTLS_ID="+resourseSessionId+")";
					int t1 = updateByQuery(queryUpdateModule);
					System.out.println("queryUpdateModule status = "+t1);		
					
					//update course status on the basis sibling modules
					String queryUpdateCourseStatus="update teacher_course_sessions set IS_COMPLETE='0' where TEACHER_COURSE_ID=(SELECT COURSE_SESSION_ID FROM teacher_course_session_dtls where COURSE_SESSION_DTLS_ID=(SELECT COURSE_SESSION_DTLS_ID FROM teacher_module_session_dtls where MODULE_SESSION_DTLS_ID="+resourseSessionId+"))";
					int t2 = updateByQuery(queryUpdateCourseStatus);
					System.out.println("queryUpdateCourseStatus status = "+t2);					
					
					//New feed for starting the module  <= resourceSessionId
		            //Create A feed | lms_feed_type = 3 |  $ has started Module $ of course $  <- user,module,course
		            String feedQuery = "INSERT INTO lms_feed_txn(FEED_TYPE_ID, REFRENCE_NM, USER_ID, COURSE_ID, RESOURCE_ID, ASSIGNMENT_ID, MODULE_ID, HRM_ID, LAST_USERID_CD, LAST_UPDT_TM)SELECT  3,'Module Started',(SELECT USER_ID FROM user_login where USER_NM=modl.TEACHER_ID) as user_id,tc.COURSE_ID,null,null,modl.MODULE_ID,tc.HRM_ID,modl.TEACHER_ID,current_timestamp FROM teacher_courses tc inner join teacher_course_sessions crs on tc.TEACHER_COURSE_ID=crs.TEACHER_COURSE_ID inner join teacher_course_session_dtls modl on crs.COURSE_SESSION_ID=modl.COURSE_SESSION_ID where modl.COURSE_SESSION_DTLS_ID=(SELECT COURSE_SESSION_DTLS_ID FROM teacher_module_session_dtls where MODULE_SESSION_DTLS_ID="+resourseSessionId+")";
		            boolean feedStatus = deleteOrUpdateByQuery(feedQuery);
		            System.out.println("Feed Creation ? "+feedStatus);						
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
	

	
}//End of class


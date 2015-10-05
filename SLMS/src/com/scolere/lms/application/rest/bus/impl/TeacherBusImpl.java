package com.scolere.lms.application.rest.bus.impl;

import java.util.List;

import com.scolere.lms.application.rest.bus.iface.TeacherBusIface;
import com.scolere.lms.application.rest.constants.SLMSRestConstants;
import com.scolere.lms.application.rest.exceptions.RestBusException;
import com.scolere.lms.application.rest.vo.request.CommonRequest;
import com.scolere.lms.application.rest.vo.response.CommonResponse;
import com.scolere.lms.application.rest.vo.response.PercentageRespTo;
import com.scolere.lms.application.rest.vo.response.TeacherResponse;
import com.scolere.lms.domain.exception.LmsServiceException;
import com.scolere.lms.service.iface.TeacherServiceIface;
import com.scolere.lms.service.impl.TeacherServiceImpl;



public class TeacherBusImpl implements TeacherBusIface{
	

	@Override
	public TeacherResponse updateCourseModuleStatus(int moduleSessionId,int statusCode)
			throws RestBusException {
		
			TeacherResponse resp = new TeacherResponse();
	       
	       try{
	                TeacherServiceIface service = new TeacherServiceImpl();
	                int updateCount = service.updateCourseModuleStatus(moduleSessionId,statusCode);
	           
	                if(updateCount>0)
	                {
	                //setting success into response
	                resp.setStatus(SLMSRestConstants.status_success);
	                resp.setStatusMessage(SLMSRestConstants.message_success); 
	                }else if(updateCount==0){
		            resp.setStatus(SLMSRestConstants.status_noUpdate);
		            resp.setStatusMessage(SLMSRestConstants.message_noUpdate); 
	                }else{
			        resp.setStatus(SLMSRestConstants.status_noUpdate);
			        resp.setStatusMessage(SLMSRestConstants.message_moduelStatus_zero); 	                	
	                }

	        } catch (LmsServiceException ex) {
	            System.out.println("LmsServiceException # updateCourseModuleStatus "+ex.getMessage());
	            resp.setStatus(SLMSRestConstants.status_failure);
	            resp.setStatusMessage(SLMSRestConstants.message_failure);
	            resp.setErrorMessage(ex.getMessage());
	        } catch (Exception ex) {
	            System.out.println("Exception # updateCourseModuleStatus "+ex.getMessage());
	            resp.setStatus(SLMSRestConstants.status_failure);
	            resp.setStatusMessage(SLMSRestConstants.message_failure);
	            resp.setErrorMessage(ex.getMessage());
	        }
	       
	       return resp;
	}

	

	@Override
	public TeacherResponse updateCourseResourceStatus(int resourseSessionId,int statusCode)
			throws RestBusException {
			
			TeacherResponse resp = new TeacherResponse();
	       
	       try{
	                TeacherServiceIface service = new TeacherServiceImpl();
	                int updateCount = service.updateCourseResourceStatus(resourseSessionId,statusCode);
	           
	                if(updateCount>0)
	                {
	                //setting success into response
	                resp.setStatus(SLMSRestConstants.status_success);
	                resp.setStatusMessage(SLMSRestConstants.message_success); 
	                }else if(updateCount==0){
		            resp.setStatus(SLMSRestConstants.status_noUpdate);
		            resp.setStatusMessage(SLMSRestConstants.message_noUpdate); 
	                }else{
			        resp.setStatus(SLMSRestConstants.status_noUpdate);
			        resp.setStatusMessage(SLMSRestConstants.message_invalidStatus); 	                	
	                }

	        } catch (LmsServiceException ex) {
	            System.out.println("LmsServiceException # updateCourseResourceStatus "+ex.getMessage());
	            resp.setStatus(SLMSRestConstants.status_failure);
	            resp.setStatusMessage(SLMSRestConstants.message_failure);
	            resp.setErrorMessage(ex.getMessage());
	        } catch (Exception ex) {
	            System.out.println("Exception # updateCourseResourceStatus "+ex.getMessage());
	            resp.setStatus(SLMSRestConstants.status_failure);
	            resp.setStatusMessage(SLMSRestConstants.message_failure);
	            resp.setErrorMessage(ex.getMessage());
	        }
	       
	       return resp;
	}



	@Override
	public TeacherResponse getPercentage(CommonRequest req)
			throws RestBusException {
		TeacherResponse resp = new TeacherResponse();
		PercentageRespTo percentage = new PercentageRespTo();
	       try{
	                TeacherServiceIface service = new TeacherServiceImpl();
	                List<Integer> courseList = service.getCoursePercentage(req.getUserName(),req.getSchoolId(),req.getClassId(),req.getHrmId());
	                List<Integer> assList = service.getAssignmentPercentage(req.getUserName(),req.getSchoolId(),req.getClassId(),req.getHrmId());
	                if(courseList !=null && courseList.size()>0)
	                {
	                 int completed=0;
	                 int inprogress=0;
	                 int notStarted=0;
	                 int totalCourse=courseList.size();
	                 for(int status : courseList){
	                	 if(status==0)
	                		 inprogress=inprogress+1;
	                	 else if(status==1)
	                		 completed=completed+1;
	                	 else if(status==2)
	                		 notStarted=notStarted+1;
	                 }
	                 percentage.setCourseComplete((completed*100/totalCourse));
	                 percentage.setCourseProgress((inprogress*100/totalCourse));
	                 percentage.setCourseNotStarted((notStarted*100/totalCourse));
		                
	                }else{
	                	percentage.setCourseNotStarted(100);
	                }
	                
	                if(assList !=null && assList.size()>0)
	                {
	                 int open=0;
	                 int submitted=0;
	                 int reviewed=0;
	                 int totalAss=assList.size();
	                 for(int status : assList){
	                	 if(status==1)
	                		 open=open+1;
	                	 else if(status==2)
	                		 submitted=submitted+1;
	                	 else if(status==3)
	                		 reviewed=reviewed+1;
	                 }
	                 
	                 percentage.setAssNotSubmit((open*100/totalAss));
	                 percentage.setAssSubmitted((submitted*100/totalAss));
	                 percentage.setAssReviewed((reviewed*100/totalAss));
	                }else{
	                	 percentage.setAssNotSubmit(100);
	                }
	                resp.setPercentage(percentage);
	                resp.setStatus(SLMSRestConstants.status_success);
	                resp.setStatusMessage(SLMSRestConstants.message_success); 
	        } catch (LmsServiceException ex) {
	            System.out.println("LmsServiceException # updateCourseModuleStatus "+ex.getMessage());
	            resp.setStatus(SLMSRestConstants.status_failure);
	            resp.setStatusMessage(SLMSRestConstants.message_failure);
	            resp.setErrorMessage(ex.getMessage());
	        } catch (Exception ex) {
	            System.out.println("Exception # updateCourseModuleStatus "+ex.getMessage());
	            resp.setStatus(SLMSRestConstants.status_failure);
	            resp.setStatusMessage(SLMSRestConstants.message_failure);
	            resp.setErrorMessage(ex.getMessage());
	        }
	       
	       return resp;
	}
	

	
}//End of class

package com.scolere.lms.application.rest.bus.impl;

import com.scolere.lms.application.rest.bus.iface.TeacherBusIface;
import com.scolere.lms.application.rest.constants.SLMSRestConstants;
import com.scolere.lms.application.rest.exceptions.RestBusException;
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
	

	
}//End of class

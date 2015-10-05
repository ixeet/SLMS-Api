package com.scolere.lms.application.rest.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.scolere.lms.application.rest.bus.iface.TeacherBusIface;
import com.scolere.lms.application.rest.bus.impl.TeacherBusImpl;
import com.scolere.lms.application.rest.constants.SLMSRestConstants;
import com.scolere.lms.application.rest.exceptions.RestBusException;
import com.scolere.lms.application.rest.vo.request.CommonRequest;
import com.scolere.lms.application.rest.vo.response.TeacherResponse;


@Path("/teacher")
public class TeacherController {

	TeacherBusIface restService = new TeacherBusImpl();
	
    /**
     * Default controller method
     *
     * @return
     */
    @GET
    public String defaultx() {
        String message = "Welcome to SLMS teacher webservices....";
        return message;
    }
    	
    
    
    @GET
    @Path("/updateResourseStatus/id/{resourceSessionId}/statusCode/{statusCode}")
    @Produces(MediaType.APPLICATION_JSON)
    public TeacherResponse updateResourseStatus(@PathParam("resourceSessionId") int resourceSessionId,@PathParam("statusCode") int statusCode) {
        System.out.println("Start updateResourseStatus for id >> "+resourceSessionId+" Status code = "+statusCode);
        TeacherResponse resp = null;

        try {
        	
        	if(resourceSessionId <= 0 )
        	{
        		resp = new TeacherResponse();
        		resp.setStatus(SLMSRestConstants.status_fieldRequired);
        		resp.setStatusMessage(SLMSRestConstants.message_fieldRequired);
        		resp.setErrorMessage(SLMSRestConstants.message_idRequired); 
        	}
        	else
        	{
            resp = restService.updateCourseResourceStatus(resourceSessionId,statusCode);
        	}        	
        	
        } catch (RestBusException ex) {
            System.out.println("Exception # updateResourseStatus - " + ex);
        }

        System.out.println("<< End updateResourseStatus # " + resp);
        
        return resp;
    }
    	
	
    @GET
    @Path("/updateModuleStatus/id/{moduleSessionId}/statusCode/{statusCode}")
    @Produces(MediaType.APPLICATION_JSON)
    public TeacherResponse completeModule(@PathParam("moduleSessionId") int moduleSessionId,@PathParam("statusCode") int statusCode) {
        System.out.println("Start completeModule for id >> "+moduleSessionId+" Status code = "+statusCode);
        TeacherResponse resp = null;

        try {
        	
        	if(moduleSessionId <= 0 )
        	{
        		resp = new TeacherResponse();
        		resp.setStatus(SLMSRestConstants.status_fieldRequired);
        		resp.setStatusMessage(SLMSRestConstants.message_fieldRequired);
        		resp.setErrorMessage(SLMSRestConstants.message_idRequired); 
        	}
        	else
        	{
            resp = restService.updateCourseModuleStatus(moduleSessionId,statusCode);
            		
        	}        	
        	
        } catch (RestBusException ex) {
            System.out.println("Exception # completeModule - " + ex);
        }

        System.out.println("<< End completeModule # " + resp);
        
        return resp;
    }    
    
    
    
    /**
     * This method returns percent of course and assignment. 
     * If request does not contain commentId it will return commentsList else sub-comments list.
     *  
     * @param CommonRequest
     * @return CommonResponse
     */
    
    @POST
    @Path("/getPercentage")
    @Consumes(MediaType.APPLICATION_JSON)    
    @Produces(MediaType.APPLICATION_JSON)     
    public TeacherResponse getFeedComments(CommonRequest req) {
        System.out.println(">> getFeedComments "+req);
        TeacherResponse resp = new TeacherResponse();    
        
    try {
    	
	    	if(req.getUserName() =="" || req.getUserName() ==null)
	    	{
	    		resp.setStatus(SLMSRestConstants.status_fieldRequired);
	    		resp.setStatusMessage(SLMSRestConstants.message_fieldRequired);
	    		resp.setErrorMessage(SLMSRestConstants.message_noOfRecordsRequired);    //noOfRecords validation
	    	}
	    	else
	    	{
	          resp = restService.getPercentage(req);
	    	}
    	
        }catch (Exception ex){
            System.out.println("CourseController#getFeedComments " +ex);
        }
        System.out.println("<< getFeedComments "+resp);
         
        return resp;
    }     
	
}//End of class

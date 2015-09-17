/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scolere.lms.application.rest.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.scolere.lms.application.rest.bus.iface.NotificationBusIface;
import com.scolere.lms.application.rest.bus.impl.NotificationBusImpl;
import com.scolere.lms.application.rest.constants.SLMSRestConstants;
import com.scolere.lms.application.rest.vo.request.NotificationRequest;
import com.scolere.lms.application.rest.vo.response.NotificationResponse;
import com.scolere.lms.common.utils.PropertyManager;

/**
 *
 * @author dell
 */
@Path("/notification")
public class NotificationController {

	NotificationBusIface restService = new NotificationBusImpl();

    /**
     * Default controller method
     * @return
     */
    @GET
    public String defaultx() {
        String message = "Welcome to SLMS notification webservices...."+PropertyManager.getProperty("TEST.MESSAGE.WELCOME");
        
        return message;
    }
    

    
    /**
     * Register device to server database.
     * @param req [userId | deviceType | deviceToken]
     * @return status
     */
    @POST
    @Path("/registerDevice")
    @Consumes(MediaType.APPLICATION_JSON)    
    @Produces(MediaType.APPLICATION_JSON)     
    public NotificationResponse registerDevice(NotificationRequest req) {
        System.out.println(">> registerDevice "+req);
        NotificationResponse resp = new NotificationResponse();    
        
    try {
    	if(req.getUserId() <= 0)
    	{
    		resp.setStatus(SLMSRestConstants.status_fieldRequired);
    		resp.setStatusMessage(SLMSRestConstants.message_fieldRequired);
    		resp.setErrorMessage(SLMSRestConstants.message_userIdRequired);  
    	}else if(req.getDeviceToken()==null ||(req.getDeviceToken()!=null && req.getDeviceToken().trim().isEmpty())){
       		resp.setStatus(SLMSRestConstants.status_fieldRequired);
    		resp.setStatusMessage(SLMSRestConstants.message_fieldRequired);
    		resp.setErrorMessage(SLMSRestConstants.message_deviceTokenRequired);    //device token validation
    		
    	}
    	else
    	{
          resp = restService.registerDevice(req); 
    	}
    	
        }catch (Exception ex){
            System.out.println("registerDevice " +ex);
        }
        System.out.println("<< registerDevice "+resp);
         
        return resp;
    }    


    /**
     * Send push notification to all registered devices.
     * @param req [pushTitle | pushMessage]
     * @return
     */
    @POST
    @Path("/notify")
    @Consumes(MediaType.APPLICATION_JSON)    
    @Produces(MediaType.APPLICATION_JSON)     
    public NotificationResponse pushNotification(NotificationRequest req) {
        System.out.println(">> pushNotification "+req);
        NotificationResponse resp = new NotificationResponse();    
        
    try {
    	
    	if(req.getPushMessage()==null ||(req.getPushMessage()!=null && req.getPushMessage().trim().isEmpty())){
       		resp.setStatus(SLMSRestConstants.status_fieldRequired);
    		resp.setStatusMessage(SLMSRestConstants.message_fieldRequired);
    		resp.setErrorMessage(SLMSRestConstants.message_pushMessageRequired);   
    	}else if(req.getPushTitle()==null ||(req.getPushTitle()!=null && req.getPushTitle().trim().isEmpty())){
       		resp.setStatus(SLMSRestConstants.status_fieldRequired);
    		resp.setStatusMessage(SLMSRestConstants.message_fieldRequired);
    		resp.setErrorMessage(SLMSRestConstants.message_pushTitleRequired);   
    	}
    	else
    	{
          resp = restService.pushNotification(req);
    	}
    	
        }catch (Exception ex){
            System.out.println("pushNotification " +ex);
        }
        System.out.println("<< pushNotification "+resp);
         
        return resp;
    }    

       
    
}//End of class

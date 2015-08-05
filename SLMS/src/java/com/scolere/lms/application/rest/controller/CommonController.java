/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scolere.lms.application.rest.controller;

import com.scolere.lms.application.rest.bus.iface.CommonBusIface;
import com.scolere.lms.application.rest.bus.impl.CommonBusImpl;
import com.scolere.lms.application.rest.exceptions.RestBusException;
import com.scolere.lms.application.rest.vo.request.CommonRequest;
import com.scolere.lms.application.rest.vo.response.CommonResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author dell
 */
@Path("/common")
public class CommonController {

    CommonBusIface restService = new CommonBusImpl();

    /**
     * Default controller method
     * @return
     */
    @GET
    public String defaultx() {
        String message = "Welcome to SLMS common webservices....";
        return message;
    }
    
    
    @POST
    @Path("/getFeeds")
    @Consumes(MediaType.APPLICATION_JSON)    
    @Produces(MediaType.APPLICATION_JSON)     
    public CommonResponse getFeeds(CommonRequest req) {
        System.out.println(">> getFeeds "+req);
        CommonResponse resp = new CommonResponse();    
        
        try {
           
        } catch (Exception ex) {
            System.out.println("CourseController#getFeeds " +ex);
        }
         System.out.println("<< getCourses "+resp);
        return resp;
    }    
        
    

    @GET
    @Path("/getMasterData")
    @Produces(MediaType.APPLICATION_JSON)
    public CommonResponse getMasterData() {
        System.out.println("Start getMasterData >> ");
        CommonResponse commonResponse = null;

        try {
            commonResponse = restService.getSchoolMasterData();
        } catch (RestBusException ex) {
            System.out.println("Exception # getMasterData - " + ex);
        }

        System.out.println("<< End getMasterData # " + commonResponse);
        return commonResponse;
    }
    
    
    
}//End of class

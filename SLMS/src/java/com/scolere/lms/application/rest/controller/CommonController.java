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
import javax.ws.rs.PathParam;
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
          resp = restService.getFeedsList(req); 
        }catch (Exception ex){
            System.out.println("CourseController#getFeeds " +ex);
        }
        System.out.println("<< getFeeds "+resp);
         
        return resp;
    }    

    
    
    @GET
    @Path("/getCourse/id/{courseId}/userId/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public CommonResponse getCourseDetail(@PathParam("courseId") int courseId,@PathParam("userId") int userId) {
        System.out.println("Start getCourseDetail for id >> "+courseId);
        CommonResponse commonResponse = null;

        try {
            commonResponse = restService.getCourseDetail(courseId);
        } catch (RestBusException ex) {
            System.out.println("Exception # getCourseDetail - " + ex);
        }

        System.out.println("<< End getCourseDetail # " + commonResponse);
        return commonResponse;
    }
    
    
    
    
    @GET
    @Path("/getUser/id/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public CommonResponse getUserDetail(@PathParam("userId") int userId) {
        System.out.println("Start getUserDetail for id >> "+userId);
        CommonResponse commonResponse = null;

        try {
            commonResponse = restService.getUserDetail(userId);
        } catch (RestBusException ex) {
            System.out.println("Exception # getCourseDetail - " + ex);
        }

        System.out.println("<< End getUserDetail # " + commonResponse);
        return commonResponse;
    }
    

    @GET
    @Path("/getModule/id/{moduleId}")
    @Produces(MediaType.APPLICATION_JSON)
    public CommonResponse getModuleDetail(@PathParam("moduleId") int moduleId) {
        System.out.println("Start getModuleDetail for id >> "+moduleId);
        CommonResponse commonResponse = null;

        try {
            commonResponse = restService.getModuleDetail(moduleId);
        } catch (RestBusException ex) {
            System.out.println("Exception # getCourseDetail - " + ex);
        }

        System.out.println("<< End getModuleDetail # " + commonResponse);
        return commonResponse;
    }
    

    @GET
    @Path("/getResource/id/{resourceId}")
    @Produces(MediaType.APPLICATION_JSON)
    public CommonResponse getResourceDetail(@PathParam("resourceId") int resourceId) {
        System.out.println("Start getResourceDetail for id >> "+resourceId);
        CommonResponse commonResponse = null;

        try {
            commonResponse = restService.getResourseDetail(resourceId); 
        } catch (RestBusException ex) {
            System.out.println("Exception # getResourceDetail - " + ex);
        }

        System.out.println("<< End getResourceDetail # " + commonResponse);
        return commonResponse;
    }

    
    @GET
    @Path("/getAssignment/id/{assignmentId}/userId/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public CommonResponse getAssignmentDetail(@PathParam("assignmentId") int assignmentId,@PathParam("userId") int userId) {
        System.out.println("Start getAssignmentDetail for id >> "+assignmentId);
        CommonResponse commonResponse = null;

        try {
            commonResponse = restService.getAssignmentDetail(assignmentId);
        } catch (RestBusException ex) {
            System.out.println("Exception # getAssignmentDetail - " + ex);
        }

        System.out.println("<< End getAssignmentDetail # " + commonResponse);
        return commonResponse;
    }    
    
    
    
    /**
     * Master data service - returns school-class-home room mapping details.
     * @return 
     */
    
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

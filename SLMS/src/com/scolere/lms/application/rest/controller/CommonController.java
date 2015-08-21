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
    @Path("/commentOnComment")
    @Consumes(MediaType.APPLICATION_JSON)    
    @Produces(MediaType.APPLICATION_JSON)     
    public CommonResponse CommentOnComment(CommonRequest req) {
        System.out.println(">> CommentOnComment "+req);
        CommonResponse resp = new CommonResponse();    
        
        try {
            //commentId | commentText | userId
            
            resp = restService.commentOnComment(req);
        } catch (RestBusException ex) {
            System.out.println("CourseController#CommentOnComment " +ex);
        }
         System.out.println("<< CommentOnComment "+resp);
        return resp;
    }         
    
    
    @POST
    @Path("/commentOnFeed")
    @Consumes(MediaType.APPLICATION_JSON)    
    @Produces(MediaType.APPLICATION_JSON)     
    public CommonResponse commentOnFeed(CommonRequest req) {
        System.out.println(">> commentOnFeed "+req);
        CommonResponse resp = new CommonResponse();    
        
        try {
            //feedId | commentText | user
            
            resp = restService.commentOnFeed(req);
        } catch (RestBusException ex) {
            System.out.println("CourseController#commentOnFeed " +ex);
        }
         System.out.println("<< commentOnFeed "+resp);
        return resp;
    }  
    
    
    @GET
    @Path("/likeOnComment/userName/{userName}/commentId/{commentId}")
    @Produces(MediaType.APPLICATION_JSON)
    public CommonResponse likeOnComment(@PathParam("userName") String userName,@PathParam("commentId") int commentId) {
        System.out.println("Start likeOnComment >> userName="+userName+" | commentId="+commentId);
        CommonResponse resp = null;
        
        try {
            resp = restService.likeOnComment(userName, commentId);
        } catch (RestBusException ex) {
            System.out.println("Exception # likeOnComment - "+ex);
        }
        
        System.out.println("<< End likeOnComment # "+resp); 
        
        return resp;
    }    
    
    @GET
    @Path("/likeOnFeed/userName/{userName}/feedId/{feedId}")
    @Produces(MediaType.APPLICATION_JSON)
    public CommonResponse likeOnFeed(@PathParam("userName") String userName,@PathParam("feedId") int feedId) {
        System.out.println("Start likeOnResource >> userName="+userName+" | feedId="+feedId);
        CommonResponse resp = null;
        
        try {
            resp = restService.likeOnFeed(userName, feedId);
        } catch (RestBusException ex) {
            System.out.println("Exception # likeOnFeed - "+ex);
        }
        
        System.out.println("<< End likeOnFeed # "+resp); 
        
        return resp;
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
    @Path("/getCourse/feedId/{feedId}") //Not IN USE
    @Produces(MediaType.APPLICATION_JSON)
    public CommonResponse getCourseDetail(@PathParam("feedId") int feedId) {
        System.out.println("Start getCourseDetail for feedId >> "+feedId);
        CommonResponse commonResponse = null;

        try {
            commonResponse = restService.getCourseDetail(feedId);
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
            System.out.println("Exception # getUserDetail - " + ex);
        }

        System.out.println("<< End getUserDetail # " + commonResponse);
        return commonResponse;
    }
    

    @GET
    @Path("/getModule/feedId/{feedId}") //NOT IN USE
    @Produces(MediaType.APPLICATION_JSON)
    public CommonResponse getModuleDetail(@PathParam("feedId") int feedId) {
        System.out.println("Start getModuleDetail for feedId >> "+feedId);
        CommonResponse commonResponse = null;

        try {
            commonResponse = restService.getModuleDetail(feedId);
        } catch (RestBusException ex) {
            System.out.println("Exception # getModuleDetail - " + ex);
        }

        System.out.println("<< End getModuleDetail # " + commonResponse);
        return commonResponse;
    }
    

    @GET
    @Path("/getResource/feedId/{feedId}")
    @Produces(MediaType.APPLICATION_JSON)
    public CommonResponse getResourceDetail(@PathParam("feedId") int feedId) {
        System.out.println("Start getResourceDetail for id >> "+feedId);
        CommonResponse commonResponse = null;

        try {
            commonResponse = restService.getResourseDetail(feedId); 
        } catch (RestBusException ex) {
            System.out.println("Exception # getResourceDetail - " + ex);
        }

        System.out.println("<< End getResourceDetail # " + commonResponse);
        return commonResponse;
    }

    
    @GET
    @Path("/getAssignment/feedId/{feedId}")  //NOT YET IMPLEMENTED
    @Produces(MediaType.APPLICATION_JSON)
    public CommonResponse getAssignmentDetail(@PathParam("feedId") int feedId) {
        System.out.println("Start getAssignmentDetail for feedId >> "+feedId);
        CommonResponse commonResponse = null;

        try {
            commonResponse = restService.getAssignmentDetail(feedId);
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

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scolere.lms.application.rest.controller;

import com.scolere.lms.application.rest.bus.iface.CommonBusIface;
import com.scolere.lms.application.rest.bus.impl.CommonBusImpl;
import com.scolere.lms.application.rest.constants.SLMSRestConstants;
import com.scolere.lms.application.rest.exceptions.RestBusException;
import com.scolere.lms.application.rest.vo.request.CommonRequest;
import com.scolere.lms.application.rest.vo.response.CommonResponse;
import com.scolere.lms.application.rest.vo.response.CourseResponse;
import com.scolere.lms.common.utils.PropertyManager;

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
        String message = "Welcome to SLMS common webservices...."+PropertyManager.getProperty("TEST.MESSAGE.WELCOME");
        
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
        	if(req.getUserName() == null || (req.getUserName() != null && req.getUserName().trim().isEmpty()))
        	{
        		resp.setStatus(SLMSRestConstants.status_fieldRequired);
        		resp.setStatusMessage(SLMSRestConstants.message_fieldRequired);
        		resp.setErrorMessage(SLMSRestConstants.message_userNameRequired);    //user name validation
        	} 
        	else if(req.getCommentId() == 0 )
        	{
        		resp.setStatus(SLMSRestConstants.status_fieldRequired);
        		resp.setStatusMessage(SLMSRestConstants.message_fieldRequired);
        		resp.setErrorMessage(SLMSRestConstants.message_commentIdRequired);    //Comment Id validation
        	}
        	else
        	{
            resp = restService.commentOnComment(req);
        	}
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
        	if(req.getUserName() == null || (req.getUserName() != null && req.getUserName().trim().isEmpty()))
        	{
        		resp.setStatus(SLMSRestConstants.status_fieldRequired);
        		resp.setStatusMessage(SLMSRestConstants.message_fieldRequired);
        		resp.setErrorMessage(SLMSRestConstants.message_userNameRequired);    //user name validation
        	}   
        	else if(req.getFeedId() == 0 )
        	{
        		resp.setStatus(SLMSRestConstants.status_fieldRequired);
        		resp.setStatusMessage(SLMSRestConstants.message_fieldRequired);
        		resp.setErrorMessage(SLMSRestConstants.message_feedIdRequired);    //Resource Id validation
        	}
        	else
        	{
            resp = restService.commentOnFeed(req);
        	}
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
        	resp=new CommonResponse();
        	if(userName == null || (userName != null && userName.trim().isEmpty()))
        	{
        		resp.setStatus(SLMSRestConstants.status_fieldRequired);
        		resp.setStatusMessage(SLMSRestConstants.message_fieldRequired);
        		resp.setErrorMessage(SLMSRestConstants.message_userNameRequired);    //user name validation
        	} 
        	else if(commentId <= 0)
        	{
        		resp.setStatus(SLMSRestConstants.status_fieldRequired);
        		resp.setStatusMessage(SLMSRestConstants.message_fieldRequired);
        		resp.setErrorMessage(SLMSRestConstants.message_commentIdRequired);    //Comment Id validation
        	}
        	else
        	{
                resp = restService.likeOnComment(userName, commentId);
        	}
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
        	resp = new CommonResponse();
        	if(userName == null || (userName != null && userName.trim().isEmpty()))
        	{
        		resp.setStatus(SLMSRestConstants.status_fieldRequired);
        		resp.setStatusMessage(SLMSRestConstants.message_fieldRequired);
        		resp.setErrorMessage(SLMSRestConstants.message_userNameRequired);    //user name validation
        	}   
        	else if(feedId == 0)
        	{
        		resp.setStatus(SLMSRestConstants.status_fieldRequired);
        		resp.setStatusMessage(SLMSRestConstants.message_fieldRequired);
        		resp.setErrorMessage(SLMSRestConstants.message_feedIdRequired);    //Resource Id validation
        	}
        	else
        	{
            resp = restService.likeOnFeed(userName, feedId);
        	}
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
    	
    	if(req.getNoOfRecords() <= 0)
    	{
    		resp.setStatus(SLMSRestConstants.status_fieldRequired);
    		resp.setStatusMessage(SLMSRestConstants.message_fieldRequired);
    		resp.setErrorMessage(SLMSRestConstants.message_noOfRecordsRequired);    //noOfRecords validation
    	}
    	else
    	{
          resp = restService.getFeedsList(req); 
    	}
    	
        }catch (Exception ex){
            System.out.println("CourseController#getFeeds " +ex);
        }
        System.out.println("<< getFeeds "+resp);
         
        return resp;
    }    

    
    /***
     * Get Notifications list
     * @param req [userId | searchText | offset | noOfRecords]
     * @return
     */
    @POST
    @Path("/getNotifications")
    @Consumes(MediaType.APPLICATION_JSON)    
    @Produces(MediaType.APPLICATION_JSON)     
    public CommonResponse getNotifications(CommonRequest req) {
        System.out.println(">> getNotifications "+req);
        CommonResponse resp = new CommonResponse();    
        
    try {
    	
    	if(req.getNoOfRecords() <= 0)
    	{
    		resp.setStatus(SLMSRestConstants.status_fieldRequired);
    		resp.setStatusMessage(SLMSRestConstants.message_fieldRequired);
    		resp.setErrorMessage(SLMSRestConstants.message_noOfRecordsRequired);    //noOfRecords validation
    	}
    	else
    	{
          resp = restService.getNotificationsList(req);
    	}
    	
        }catch (Exception ex){
            System.out.println("CourseController#getNotifications " +ex);
        }
        System.out.println("<< getNotifications "+resp);
         
        return resp;
    }    
    
    
    @GET
    @Path("/updateNotificationStatus/userId/{userId}/feedId/{feedId}/status/{status}")
    @Produces(MediaType.APPLICATION_JSON)     
    public CommonResponse updateNotificationStatus(@PathParam("userId") int userId,@PathParam("feedId") int feedId,@PathParam("status") String status) {
        System.out.println(">> updateNotificationStatus feedId = "+feedId);
        CommonResponse resp = new CommonResponse();    
        
    try {

          resp = restService.updateNotificationStatus(userId, feedId,status);
    	
        }catch (Exception ex){
            System.out.println("CourseController#updateNotificationStatus " +ex);
        }
        System.out.println("<< updateNotificationStatus "+resp);
         
        return resp;
    }    
    
    
    @GET
    @Path("/getFeedDetail/userId/{userId}/feedId/{feedId}")
    @Produces(MediaType.APPLICATION_JSON)     
    public CommonResponse getFeedDetail(@PathParam("userId") int userId,@PathParam("feedId") int feedId) {
        System.out.println(">> getFeedDetail feedId = "+feedId);
        CommonResponse resp = new CommonResponse();    
        
    try {
    	
          resp = restService.getFeedDetail(userId, feedId);
    	
        }catch (Exception ex){
            System.out.println("CourseController#getFeedDetail " +ex);
        }
        System.out.println("<< getFeedDetail "+resp);
         
        return resp;
    }    

    
    
    /**
     * This method returns list of comments based on request parameter feedId & commentId. 
     * If request does not contain commentId it will return commentsList else sub-comments list.
     *  
     * @param CommonRequest
     * @return CommonResponse
     */
    
    @POST
    @Path("/getFeedComments")
    @Consumes(MediaType.APPLICATION_JSON)    
    @Produces(MediaType.APPLICATION_JSON)     
    public CommonResponse getFeedComments(CommonRequest req) {
        System.out.println(">> getFeedComments "+req);
        CommonResponse resp = new CommonResponse();    
        
    try {
    	
	    	if(req.getNoOfRecords() <= 0)
	    	{
	    		resp.setStatus(SLMSRestConstants.status_fieldRequired);
	    		resp.setStatusMessage(SLMSRestConstants.message_fieldRequired);
	    		resp.setErrorMessage(SLMSRestConstants.message_noOfRecordsRequired);    //noOfRecords validation
	    	}
	    	else
	    	{
	          resp = restService.getFeedComments(req);
	    	}
    	
        }catch (Exception ex){
            System.out.println("CourseController#getFeedComments " +ex);
        }
        System.out.println("<< getFeedComments "+resp);
         
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
    

    @GET
    @Path("/getMasterData/teacherId/{teacherId}")
    @Produces(MediaType.APPLICATION_JSON)
    public CommonResponse getMasterData(@PathParam("teacherId") int teacherId) {
        System.out.println("Start getMasterData/teacherId >> "+teacherId);
        CommonResponse commonResponse = null;

        try {
        	
            commonResponse = restService.getSchoolMasterData();
            
        } catch (RestBusException ex) {
            System.out.println("Exception # getMasterData/teacherId - " + ex);
        }

        System.out.println("<< End getMasterData/teacherId # " + commonResponse);
        return commonResponse;
    }
    
    
    
}//End of class

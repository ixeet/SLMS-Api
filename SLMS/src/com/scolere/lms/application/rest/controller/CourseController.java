/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scolere.lms.application.rest.controller;

import java.io.InputStream;
import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.scolere.lms.application.rest.bus.iface.CourseBusIface;
import com.scolere.lms.application.rest.bus.impl.CourseBusImpl;
import com.scolere.lms.application.rest.constants.SLMSRestConstants;
import com.scolere.lms.application.rest.exceptions.RestBusException;
import com.scolere.lms.application.rest.vo.request.CourseRequest;
import com.scolere.lms.application.rest.vo.response.CourseResponse;
import com.scolere.lms.common.utils.AppUtils;
import com.scolere.lms.common.utils.PropertyManager;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;


/**
 *
 * @author dell
 */
@Path("/course")
public class CourseController {

    CourseBusIface restService = new CourseBusImpl();
    /**
     * Default controller method 
     *
     * @return
     */
    @GET
    public String defaultx() {
        String message = "Welcome to SLMS course webservices....";
        return message;
    }
    
   
    @GET
    @Path("/getCourse/feedId/{feedId}")
    @Produces(MediaType.APPLICATION_JSON)
    public CourseResponse getCourseDetail(@PathParam("feedId") int feedId) {
        System.out.println("Start getCourseDetail for feedId >> "+feedId);
        CourseResponse resp = null;

        try {
            resp = restService.getCourseDetailsByFeedId(feedId);
        } catch (RestBusException ex) {
            System.out.println("Exception # getCourseDetail - " + ex);
        }

        System.out.println("<< End getCourseDetail # " + resp);
        return resp;
    }    
    
    
    @POST
    @Path("/getCourses")
    @Consumes(MediaType.APPLICATION_JSON)    
    @Produces(MediaType.APPLICATION_JSON)     
    public CourseResponse getUserCourses(CourseRequest course) {
        System.out.println(">> getCourses "+course);
        CourseResponse resp = new CourseResponse();    
        
        try {
            resp = restService.getUserCourses(course);
        } catch (RestBusException ex) {
            System.out.println("CourseController#getUserCourses " +ex);
        }
         System.out.println("<< getCourses "+resp);
        return resp;
    }    
    
    
        
    @POST
    @Path("/getCourses/web")
    @Consumes(MediaType.APPLICATION_JSON)    
    @Produces(MediaType.APPLICATION_JSON)     
    public CourseResponse getUserCoursesWeb(CourseRequest course) {
        System.out.println(">> getCourses/web "+course);
        CourseResponse resp = new CourseResponse();    
        
        try {
            resp = restService.getUserCoursesWeb(course);
        } catch (RestBusException ex) {
            System.out.println("CourseController#getUserCourses " +ex);
        }
         System.out.println("<< getCourses/web "+resp);
        return resp;
    }  
    
    
    
    @POST
    @Path("/getCourses/teacher")
    @Consumes(MediaType.APPLICATION_JSON)    
    @Produces(MediaType.APPLICATION_JSON)     
    public CourseResponse getUserCoursesTeacher(CourseRequest course) {
        System.out.println(">> getCourses/teacher "+course);
        CourseResponse resp = new CourseResponse();    
        
        try {
            resp = restService.getUserCoursesTeacher(course);
        } catch (RestBusException ex) {
            System.out.println("CourseController#getUserCoursesTeacher " +ex);
        }
         System.out.println("<< getCourses/teacher "+resp);
        return resp;
    }  
        
    
    
    @POST
    @Path("/getAssignments/teacher")
    @Consumes(MediaType.APPLICATION_JSON)    
    @Produces(MediaType.APPLICATION_JSON)     
    public CourseResponse getTeacherAssignments(CourseRequest course) {
        System.out.println(">> getAssignments/teacher "+course);
        CourseResponse resp = new CourseResponse();    
        
        try {
            resp = restService.getTeacherAssignments(course);
        } catch (RestBusException ex) {
            System.out.println("CourseController#getTeacherAssignments " +ex);
        }
         System.out.println("<< getAssignments/teacher "+resp);
        return resp;
    }  
           
    
    
    @GET
    @Path("/getModule/feedId/{feedId}")
    @Produces(MediaType.APPLICATION_JSON)
    public CourseResponse getModuleDetail(@PathParam("feedId") int feedId) {
        System.out.println("Start getModuleDetail for feedId >> "+feedId);
        CourseResponse resp = null;

        try {
            resp = restService.getModuleDetailsByFeedId(feedId);
        } catch (RestBusException ex) {
            System.out.println("Exception # getModule - " + ex);
        }

        System.out.println("<< End getModule # " + resp);
        return resp;
    }  
    
    
    
    @POST
    @Path("/getModuleDetail")
    @Consumes(MediaType.APPLICATION_JSON)    
    @Produces(MediaType.APPLICATION_JSON)     
    public CourseResponse getModuleDetail(CourseRequest course) {
        System.out.println(">> getModuleDetail "+course);
        CourseResponse resp = new CourseResponse();    
        
        try {
            resp = restService.getModuleResources(course);
        } catch (RestBusException ex) {
            System.out.println("CourseController#getModuleDetail " +ex);
        }
         System.out.println("<< getModuleDetail "+resp);
        return resp;
    }     
    
    
    @POST
    @Path("/commentOnComment")
    @Consumes(MediaType.APPLICATION_JSON)    
    @Produces(MediaType.APPLICATION_JSON)     
    public CourseResponse CommentOnComment(CourseRequest course) {
        System.out.println(">> CommentOnComment "+course);
        CourseResponse resp = null;    
        
        try {
        	resp = new CourseResponse();
            //commentId | commentText | userName
        	if(course.getUserName() == null || (course.getUserName() != null && course.getUserName().trim().isEmpty()))
        	{
        		resp.setStatus(SLMSRestConstants.status_fieldRequired);
        		resp.setStatusMessage(SLMSRestConstants.message_fieldRequired);
        		resp.setErrorMessage(SLMSRestConstants.message_userNameRequired);    //user name validation
        	} 
        	else if(course.getCommentId() == 0 )
        	{
        		resp.setStatus(SLMSRestConstants.status_fieldRequired);
        		resp.setStatusMessage(SLMSRestConstants.message_fieldRequired);
        		resp.setErrorMessage(SLMSRestConstants.message_commentIdRequired);    //Comment Id validation
        	}
        	else
        	{
            resp = restService.commentOnComment(course);
        	}
        } catch (RestBusException ex) {
            System.out.println("CourseController#CommentOnComment " +ex);
        }
         System.out.println("<< CommentOnComment "+resp);
        return resp;
    }         
    
    
    @POST
    @Path("/commentOnResourse")
    @Consumes(MediaType.APPLICATION_JSON)    
    @Produces(MediaType.APPLICATION_JSON)     
    public CourseResponse CommentOnResourse(CourseRequest course) {
        System.out.println(">> CommentOnResourse "+course);
        CourseResponse resp = null;    
        
        try {
        	resp = new CourseResponse();
            //resourceId | commentText | userName
        	if(course.getUserName() == null || (course.getUserName() != null && course.getUserName().trim().isEmpty()))
        	{
        		resp.setStatus(SLMSRestConstants.status_fieldRequired);
        		resp.setStatusMessage(SLMSRestConstants.message_fieldRequired);
        		resp.setErrorMessage(SLMSRestConstants.message_userNameRequired);    //user name validation
        	}   
        	else if(course.getResourceId() == 0 )
        	{
        		resp.setStatus(SLMSRestConstants.status_fieldRequired);
        		resp.setStatusMessage(SLMSRestConstants.message_fieldRequired);
        		resp.setErrorMessage(SLMSRestConstants.message_resourceIdRequired);    //Resource Id validation
        	}
        	else
        	{
            resp = restService.commentOnResource(course);
        	}
        } catch (RestBusException ex) {
            System.out.println("CourseController#CommentOnResourse " +ex);
        }
         System.out.println("<< CommentOnResourse "+resp);
        return resp;
    }  
    
    
    @GET
    @Path("/likeOnComment/userName/{userName}/commentId/{commentId}")
    @Produces(MediaType.APPLICATION_JSON)
    public CourseResponse likeOnComment(@PathParam("userName") String userName,@PathParam("commentId") int commentId) {
        System.out.println("Start likeOnComment >> userName="+userName+" | commentId="+commentId);
        CourseResponse resp = null;
        
        try {
        	resp=new CourseResponse();
        	if(userName == null || (userName != null && userName.trim().isEmpty()))
        	{
        		resp.setStatus(SLMSRestConstants.status_fieldRequired);
        		resp.setStatusMessage(SLMSRestConstants.message_fieldRequired);
        		resp.setErrorMessage(SLMSRestConstants.message_userNameRequired);    //user name validation
        	} 
        	else if(commentId == 0)
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
    @Path("/likeOnResource/userName/{userName}/resourceId/{resourceId}")
    @Produces(MediaType.APPLICATION_JSON)
    public CourseResponse likeOnResource(@PathParam("userName") String userName,@PathParam("resourceId") int resourceId) {
        System.out.println("Start likeOnResource >> userName="+userName+" | resourceId="+resourceId);
        CourseResponse resp = null;
        
        try {
        	resp = new CourseResponse();
        	if(userName == null || (userName != null && userName.trim().isEmpty()))
        	{
        		resp.setStatus(SLMSRestConstants.status_fieldRequired);
        		resp.setStatusMessage(SLMSRestConstants.message_fieldRequired);
        		resp.setErrorMessage(SLMSRestConstants.message_userNameRequired);    //user name validation
        	}   
        	else if(resourceId == 0)
        	{
        		resp.setStatus(SLMSRestConstants.status_fieldRequired);
        		resp.setStatusMessage(SLMSRestConstants.message_fieldRequired);
        		resp.setErrorMessage(SLMSRestConstants.message_resourceIdRequired);    //Resource Id validation
        	}
        	else
        	{
            resp = restService.likeOnResource(userName, resourceId);
        	}
        } catch (RestBusException ex) {
            System.out.println("Exception # likeOnResource - "+ex);
        }
        
        System.out.println("<< End likeOnResource # "+resp); 
        
        return resp;
    }    
   
    /***ASSIGNMENT SERVICES***/    
    
    @POST
    @Path("/getAssignments")
    @Consumes(MediaType.APPLICATION_JSON)    
    @Produces(MediaType.APPLICATION_JSON)  
    public CourseResponse getAssignments(CourseRequest course) {
        System.out.println("Start getAssignments >> "+course);
        CourseResponse resp = null;
        
        try {
        	//userId | searchText
            resp = restService.getAssignments(course);
        } catch (RestBusException ex) {
            System.out.println("Exception # getAssignments - "+ex);
        }
        System.out.println("<< End getAssignments # "+resp); 
        
        return resp;
    }   
    
  
    
    @POST
    @Path("/uploadResourceDetail")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)          
    public CourseResponse uploadImageDetail(
            @FormDataParam("assignmentId") int assignmentId,
            @FormDataParam("resourceName") String resourceName,
            @FormDataParam("resourceAuthor") String resourceAuthor,
            @FormDataParam("fileName") InputStream uploadedInputStream,
            @FormDataParam("fileName") FormDataContentDisposition fileDetail,
            @FormDataParam("userName") String userName,
            @FormDataParam("descTxt") String descTxt,
            @FormDataParam("uploadedUrl") String uploadedUrl
    		) {

    	 CourseResponse resp = new CourseResponse();
         
         try {
        	 
        	 if(assignmentId <= 0)
         	{
         		resp.setStatus(SLMSRestConstants.status_fieldRequired);
         		resp.setStatusMessage(SLMSRestConstants.message_fieldRequired);
         		resp.setErrorMessage(SLMSRestConstants.message_assignmentIdRequired);    //Assignment Id validation
         	}  
        	 else if(resourceName == null || (resourceName != null && resourceName.trim().isEmpty()))
          	{
          		resp.setStatus(SLMSRestConstants.status_fieldRequired);
          		resp.setStatusMessage(SLMSRestConstants.message_fieldRequired);
          		resp.setErrorMessage(SLMSRestConstants.message_resourceNameRequired);    //Resource name  validation
          	} 
        	 else if(resourceAuthor == null || (resourceAuthor != null && resourceAuthor.trim().isEmpty()))
           	{
           		resp.setStatus(SLMSRestConstants.status_fieldRequired);
           		resp.setStatusMessage(SLMSRestConstants.message_fieldRequired);
           		resp.setErrorMessage(SLMSRestConstants.message_resourceAuthorRequired);    //Resource Author validation
           	}
        	 else if(userName == null || (userName != null && userName.trim().isEmpty()))
            	{
            		resp.setStatus(SLMSRestConstants.status_fieldRequired);
            		resp.setStatusMessage(SLMSRestConstants.message_fieldRequired);
            		resp.setErrorMessage(SLMSRestConstants.message_userNameRequired);    //user name validation
            	}
         	  else if(descTxt == null || (descTxt != null && descTxt.trim().isEmpty()))
            	{
            		resp.setStatus(SLMSRestConstants.status_fieldRequired);
            		resp.setStatusMessage(SLMSRestConstants.message_fieldRequired);
            		resp.setErrorMessage(SLMSRestConstants.message_desc_textRequired);    //Description validation
            	}
	        	else if(fileDetail.getFileName().equals("") && (uploadedUrl==null ||(uploadedUrl != null && uploadedUrl.trim().isEmpty())))
	         	{
	         		resp.setStatus(SLMSRestConstants.status_fieldRequired);
	         		resp.setStatusMessage(SLMSRestConstants.message_fieldRequired);
	         		resp.setErrorMessage(SLMSRestConstants.message_fileDetailRequired);  //File Details or Empty Url validation
	         	}
        	 
        	 else {
        	 String thumbImgUrl="common.png";
        	 String authorImgUrl="default-author.png";
        	 String resourceUrl;
        	 
        	//Case : File Uploading
        	 if(!fileDetail.getFileName().equals(""))
        	 {
        		 System.out.println("New file submitting ... ");
        		 //Generating file name
        		 Date currentDate = new Date();
        		 String fileExt = fileDetail.getFileName().substring(fileDetail.getFileName().lastIndexOf("."));
        		 String tempFileName = userName+"_"+currentDate.getTime()+fileExt;
        		 System.out.println("Temp file name = "+tempFileName);
        		 
        		 //Writing file
        		 String uploadedFileLocation = PropertyManager.getProperty(SLMSRestConstants.location_assignments)+tempFileName;
        		 System.out.println("Temp file location = "+uploadedFileLocation);
        		 AppUtils.writeToFile(uploadedInputStream, uploadedFileLocation);
        		 System.out.println("Assignment resource successfully uploaded..");
        		 
        		 //Updating into database
        		 resourceUrl = PropertyManager.getProperty(SLMSRestConstants.baseUrl_resources)+tempFileName;
     		
        		 int updateStatus = restService.uploadAssignment(assignmentId, resourceName, resourceAuthor, descTxt, userName, descTxt, resourceUrl, thumbImgUrl, authorImgUrl);
            	 System.out.println("File Upload status ? "+updateStatus);  
            	 
            	 //Condition if uploaded url is also adding along with file upload
            	 /*
            	 if(uploadedUrl != null && !uploadedUrl.isEmpty())
            	 {
             		System.out.println("URL adding ... ");
            		resourceUrl = uploadedUrl;
            		int updateStatus2 = restService.uploadAssignment(assignmentId, resourceName, resourceAuthor, descTxt, userName, descTxt, resourceUrl, thumbImgUrl, authorImgUrl);
            		System.out.println("URL adding status ? "+updateStatus2);            		 
           		 
            	 }	 
            	 */
        	 }
        	        		 
        	 //Case : File Not Uploading
        	 if(fileDetail.getFileName().equals(""))
        	 {
        		System.out.println("URL submitting ... ");
        		resourceUrl = uploadedUrl;
        		
        		int updateStatus = restService.uploadAssignment(assignmentId, resourceName, resourceAuthor, descTxt, userName, descTxt, resourceUrl, thumbImgUrl, authorImgUrl);
        		
        		System.out.println("URL submitted status ? "+updateStatus);
        	 }

         //Common status---
         resp.setStatus(SLMSRestConstants.status_success);
         resp.setStatusMessage(SLMSRestConstants.message_success);  
       }
       }catch(Exception e){
    	   System.out.println("Exception while uploading assignment.."+e);
           resp.setStatus(SLMSRestConstants.status_failure);
           resp.setStatusMessage(SLMSRestConstants.message_failure);  
           resp.setErrorMessage(e.getMessage());
       }
         

    return resp;
    }
    
   
    
}//End of class

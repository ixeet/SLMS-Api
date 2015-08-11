/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scolere.lms.application.rest.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import com.scolere.lms.application.rest.bus.iface.CourseBusIface;
import com.scolere.lms.application.rest.bus.impl.CourseBusImpl;
import com.scolere.lms.application.rest.constants.SLMSRestConstants;
import com.scolere.lms.application.rest.exceptions.RestBusException;
import com.scolere.lms.application.rest.vo.request.CourseRequest;
import com.scolere.lms.application.rest.vo.response.CourseResponse;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

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
        System.out.println(">> getCourses "+course);
        CourseResponse resp = new CourseResponse();    
        
        try {
            resp = restService.getUserCoursesWeb(course);
        } catch (RestBusException ex) {
            System.out.println("CourseController#getUserCourses " +ex);
        }
         System.out.println("<< getCourses "+resp);
        return resp;
    }  
    
    
    
    @GET
    @Path("/getModule/feedId/{feedId}")
    @Produces(MediaType.APPLICATION_JSON)
    public CourseResponse getModuleDetail(@PathParam("feedId") int feedId) {
        System.out.println("Start getModuleDetail for feedId >> "+feedId);
        CourseResponse resp = null;

        try {
            resp = restService.getCourseDetailsByFeedId(feedId);
        } catch (RestBusException ex) {
            System.out.println("Exception # getModuleDetail - " + ex);
        }

        System.out.println("<< End getModuleDetail # " + resp);
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
        CourseResponse resp = new CourseResponse();    
        
        try {
            //commentId | commentText | userId
            
            resp = restService.commentOnComment(course);
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
        CourseResponse resp = new CourseResponse();    
        
        try {
            //resourseId | commentText | user
            
            resp = restService.commentOnResource(course);
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
            resp = restService.likeOnComment(userName, commentId);
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
            resp = restService.likeOnResource(userName, resourceId);
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
            @FormDataParam("resourceName") String resourceName,
            @FormDataParam("resourceUrl") InputStream uploadedInputStream,
            @FormDataParam("resourceUrl") FormDataContentDisposition fileDetail,
            @FormDataParam("resourceAuthor") String resourceAuthor,
            @FormDataParam("resourceImage") String resourceImage,
            @FormDataParam("lastUserIdCd") String lastUserIdCd,
            @FormDataParam("descTxt") String descTxt,
            @FormDataParam("upLoadUrl") String upLoadUrl
    		) {

    	 CourseResponse resp = new CourseResponse();
         
         try {
         //Update PROFILE_IMG to database
        	 String resourceprofileImgName="";
        	 String resoUrl;
        	 if(fileDetail.getFileName().equals("")){
        		 resourceprofileImgName = resourceImage;
        		 resoUrl=fileDetail.getFileName();
        	 }
        	 else{
        		 resourceprofileImgName = resourceImage+SLMSRestConstants.image_extension;
        		 resoUrl=fileDetail.getFileName();
        	 }
        	 
         int updateStatus =  restService.saveResourceProfile(resourceprofileImgName,resourceAuthor,resourceImage,lastUserIdCd,descTxt,resourceName,upLoadUrl,resoUrl);
         System.out.println("Image name saved into db ? "+updateStatus);
         
         if(!fileDetail.getFileName().equals("")){
         String uploadedFileLocation = SLMSRestConstants.location_userprofile+updateStatus+resourceprofileImgName;
         System.out.println("Uploading file @: "+uploadedFileLocation);
         writeToFile(uploadedInputStream, uploadedFileLocation);
         }
         System.out.println("Profile image successfully uploaded..");
         
         
         resp.setStatus(SLMSRestConstants.status_success);
         resp.setStatusMessage(SLMSRestConstants.message_success);  
         
         
        }
        catch (Exception e) {
			e.printStackTrace();
		}
        /*return resp;*/
        return resp;
    }
    
    private void writeToFile(InputStream uploadedInputStream,
            String uploadedFileLocation) {

        try {
            OutputStream out = new FileOutputStream(new File(uploadedFileLocation));
            int read = 0;
            byte[] bytes = new byte[1024];

            out = new FileOutputStream(new File(uploadedFileLocation));
            while ((read = uploadedInputStream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            out.flush();
            out.close();
        } catch (Exception e) {
            System.out.println("File upload exception #2 "+e.getMessage());
        }

    }
    
    
}//End of class

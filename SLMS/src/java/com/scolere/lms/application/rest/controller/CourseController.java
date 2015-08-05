/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scolere.lms.application.rest.controller;

import com.scolere.lms.application.rest.bus.iface.CourseBusIface;
import com.scolere.lms.application.rest.bus.impl.CourseBusImpl;
import com.scolere.lms.application.rest.exceptions.RestBusException;
import com.scolere.lms.application.rest.vo.request.CourseRequest;
import com.scolere.lms.application.rest.vo.response.CourseResponse;
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
   
        
    
}//End of class

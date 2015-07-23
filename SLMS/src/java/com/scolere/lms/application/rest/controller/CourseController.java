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
    
    
}//End of class

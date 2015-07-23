/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scolere.lms.application.rest.bus.iface;

import com.scolere.lms.application.rest.exceptions.RestBusException;
import com.scolere.lms.application.rest.vo.request.CourseRequest;
import com.scolere.lms.application.rest.vo.response.CourseResponse;

/**
 *
 * @author dell
 */
public interface CourseBusIface {
    
    /**
     * This method returns list of all the courses associated with an user.
     * @param req
     * @return CourseResponse (Array of Courses {CoursesId,,CoursesName,PercentageOfCources,ArrayofModules(ModuleId,ModuleName,Status,PercentOfModule)}Status,StatusMessage)
     * @throws RestBusException 
     */
    CourseResponse getUserCourses(CourseRequest req) throws RestBusException;
    
    /**
     * Returns all the resources associated with a module.
     * @param req
     * @return CourseResponse
     * @throws RestBusException 
     */
    CourseResponse getModuleResources(CourseRequest req) throws RestBusException;
    
    
}

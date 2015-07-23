/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scolere.lms.application.rest.bus.impl;

import com.scolere.lms.application.rest.bus.iface.CourseBusIface;
import com.scolere.lms.application.rest.exceptions.RestBusException;
import com.scolere.lms.application.rest.vo.request.CourseRequest;
import com.scolere.lms.application.rest.vo.response.AssignmentRespTO;
import com.scolere.lms.application.rest.vo.response.CommentRespTO;
import com.scolere.lms.application.rest.vo.response.CourseRespTO;
import com.scolere.lms.application.rest.vo.response.CourseResponse;
import com.scolere.lms.application.rest.vo.response.ModuleRespTO;
import com.scolere.lms.application.rest.vo.response.ResourceRespTO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dell
 */
public class CourseBusImpl implements CourseBusIface{

    /**
     * 
     * @param req (UserId,TextSearch)
     * @return (Array of Courses :
     * {CoursesId,CoursesName,PercentageOfCources,
     * ArrayofModules(
     * ModuleId,ModuleName,Status,PercentOfModule
     * )
     * }Status,StatusMessage
     * )
     * @throws RestBusException 
     */
    @Override
    public CourseResponse getUserCourses(CourseRequest req) throws RestBusException {
        CourseResponse resp = new CourseResponse();
        
        ArrayList<CourseRespTO> courses = new ArrayList<CourseRespTO>();
        
        CourseRespTO course = new CourseRespTO();
        course.setCourseId("01");
        course.setCourseName("Physics");
        course.setCompletedPercentStatus("100");
        course.setStartedOn("2015-07-01");
        List<ModuleRespTO> moduleList = new ArrayList<ModuleRespTO>();
              ModuleRespTO mod1=new ModuleRespTO("02", "Velocity");
              mod1.setCompletedPercentStatus("100");
              mod1.setStartedOn("2015-07-05");
              moduleList.add(mod1);
              ModuleRespTO mod2=new ModuleRespTO("03", "Mass");
              mod2.setCompletedPercentStatus("100");
              mod2.setStartedOn("2015-07-05");
              moduleList.add(mod2);
              ModuleRespTO mod3=new ModuleRespTO("04", "Acceleration");
              mod3.setCompletedPercentStatus("100");
              mod3.setStartedOn("2015-07-05");
              moduleList.add(mod3);              
        course.setModuleList(moduleList);
        courses.add(course);
        
        CourseRespTO course2 = new CourseRespTO();
        course2.setCourseId("06");
        course2.setCourseName("Mathematics");
        course2.setCompletedPercentStatus("25");
        course2.setStartedOn("2015-07-05");
        List<ModuleRespTO> moduleList2 = new ArrayList<ModuleRespTO>();
              mod1=new ModuleRespTO("01", "Algebra");
              mod1.setCompletedPercentStatus("50");
              mod1.setStartedOn("2015-07-05");
              moduleList2.add(mod1);

              mod2=new ModuleRespTO("02", "Trigonometry");
              mod2.setCompletedPercentStatus("75");
              mod2.setStartedOn("2015-07-05");
              moduleList2.add(mod2);

              mod3=new ModuleRespTO("03", "Statistics");
              mod3.setCompletedPercentStatus("40");
              mod3.setStartedOn("2015-07-05");
              moduleList2.add(mod3);              
              
              ModuleRespTO mod4=new ModuleRespTO("04", "Geometry");
              mod4.setCompletedPercentStatus("100");
              mod4.setStartedOn("2015-07-05");
              moduleList2.add(mod4);  
              
        course2.setModuleList(moduleList2);
        courses.add(course2);
        
        
        resp.setCourseList(courses);
        resp.setStatus(1001);
        resp.setStatusMessage("success");
        
        return resp;
    }

    /**
     * Returns module resources.
     * @param req (userId/courseId/moduleId/searchText)
     * @return 
     * @throws RestBusException 
     */
    @Override
    public CourseResponse getModuleResources(CourseRequest req) throws RestBusException {
        CourseResponse resp = new CourseResponse();
        
    List<ResourceRespTO> resourceList = new ArrayList<ResourceRespTO>();
    
    ResourceRespTO vdo = new ResourceRespTO();
    vdo.setResourceId("1");
    vdo.setResourceDesc("Resource 1");
    vdo.setResourceUrl("testresource1.com");
    vdo.setAuthorName("Irish Allen");
    vdo.setStartedOn("2015-01-01");
    vdo.setCompletedOn("2015-01-21");
    vdo.setLikeCounts(21);
    vdo.setShareCounts(45);
    vdo.setCommentCounts(500);
    
    
   // related videos    
    List<ResourceRespTO> relatedVideoList = new ArrayList<ResourceRespTO>();
    
    ResourceRespTO vdo1 = new ResourceRespTO();
    vdo1.setResourceId("1");
    vdo1.setResourceDesc("Test resource1");
    vdo1.setResourceUrl("testresource1.com");
    vdo1.setUploadedDate("2000-01-01");
    relatedVideoList.add(vdo1);

    ResourceRespTO vdo2 = new ResourceRespTO();
    vdo2.setResourceId("1");
    vdo2.setResourceDesc("Test resource1");
    vdo2.setResourceUrl("testresource1.com");
    vdo2.setUploadedDate("2000-01-01");
    relatedVideoList.add(vdo2);
    
    ResourceRespTO vdo3 = new ResourceRespTO();
    vdo3.setResourceId("1");
    vdo3.setResourceDesc("Test resource1");
    vdo3.setResourceUrl("testresource1.com");
    vdo3.setUploadedDate("2000-01-01");
    relatedVideoList.add(vdo3);    
    
    
    List<CommentRespTO> commentList = new ArrayList<CommentRespTO>();
    
    CommentRespTO crt1 = new CommentRespTO();
    crt1.setCommentBy("Dixit");
    crt1.setCommentCounts(11);
    crt1.setCommentDate("2015-01-05");
    crt1.setCommentTxt("Swimming is a good excercise for the health.");
    crt1.setLikeCounts(23);
    crt1.setShareCounts(45);
    crt1.setCommentId(10);
    crt1.setParentCommentId(0);
    commentList.add(crt1);
    
    CommentRespTO crt2 = new CommentRespTO();
    crt2.setCommentBy("Mayank");
    crt2.setCommentCounts(11);
    crt2.setCommentDate("2015-01-12");
    crt2.setCommentTxt("hdfgsjf kashdajkhd hasdkahsd 8qwyeq8e ajsdhajshd aidsIUD SJDFHSFD");
    crt2.setLikeCounts(23);
    crt2.setShareCounts(45);
    crt2.setCommentId(11);
    crt2.setParentCommentId(0);
    commentList.add(crt2);
    
    vdo.setCommentList(commentList);
    vdo.setRelatedVideoList(relatedVideoList);
    resourceList.add(vdo);

    
    ResourceRespTO vdo2x = vdo;
    vdo2x.setAuthorName("Hunny singh choutala");
    vdo2x.setResourceDesc("Resource 2");
    resourceList.add(vdo2x);

    resp.setResourceList(resourceList);

    //Assignments -------------------- > 
    List<AssignmentRespTO> assignmentList = new ArrayList<AssignmentRespTO>();
    
    AssignmentRespTO assignmentRespTO = new AssignmentRespTO();
    assignmentRespTO.setAssignmentId(1);
    assignmentRespTO.setAssignmentName("Assignment-1");
    assignmentRespTO.setAssignmentStatus("Completed");
    assignmentRespTO.setAssignmentSubmittedBy("Mayankd");
    assignmentRespTO.setAssignmentSubmittedDate("2015-01-07");
    
    List<ResourceRespTO> attachedResources = new ArrayList<ResourceRespTO>();
    attachedResources.add(vdo);
    attachedResources.add(vdo1);
    attachedResources.add(vdo2);
    
    assignmentRespTO.setAttachedResources(attachedResources);
    assignmentList.add(assignmentRespTO);
    
    AssignmentRespTO assignmentRespTO2=assignmentRespTO;
    assignmentRespTO2.setAssignmentName("Assignment-2");
    assignmentRespTO2.setAssignmentId(2);
    
    assignmentRespTO2.setAttachedResources(attachedResources);
    assignmentList.add(assignmentRespTO2);
    resp.setAssignmentList(assignmentList);
    
    
    resp.setStatus(1001);
    resp.setStatusMessage("success");
    
    return resp;
    }
    
    
}//end of class

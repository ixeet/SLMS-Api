/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scolere.lms.application.rest.bus.impl;

import com.scolere.lms.application.rest.bus.iface.CourseBusIface;
import com.scolere.lms.application.rest.constants.SLMSRestConstants;
import com.scolere.lms.application.rest.exceptions.RestBusException;
import com.scolere.lms.application.rest.vo.request.CourseRequest;
import com.scolere.lms.application.rest.vo.response.AssignmentRespTO;
import com.scolere.lms.application.rest.vo.response.CommentRespTO;
import com.scolere.lms.application.rest.vo.response.CourseRespTO;
import com.scolere.lms.application.rest.vo.response.CourseResponse;
import com.scolere.lms.application.rest.vo.response.ModuleRespTO;
import com.scolere.lms.application.rest.vo.response.ResourceRespTO;
import com.scolere.lms.domain.exception.LmsServiceException;
import com.scolere.lms.domain.vo.cross.AssignmentVO;
import com.scolere.lms.domain.vo.cross.CommentVO;
import com.scolere.lms.domain.vo.cross.CourseVO;
import com.scolere.lms.domain.vo.cross.ResourseVO;
import com.scolere.lms.service.iface.CourseServiceIface;
import com.scolere.lms.service.impl.CourseServiceImpl;
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
            CourseServiceIface service = new CourseServiceImpl();
    
            try {

            //Courses list
             List<CourseVO> courseListFromDB = service.getStudentCourses(req.getUserId(), req.getSearchText());
            // List<CourseVO> courseListFromDB = service.getStudentCourses(req.getUserName(), req.getSearchText());
             ArrayList<CourseRespTO> courses = new ArrayList<CourseRespTO>(courseListFromDB.size());
             
             CourseRespTO courseResp = null;
             ModuleRespTO modResp = null;
             for(CourseVO vo : courseListFromDB)
             {
             courseResp = new CourseRespTO();
             courseResp.setCompletedOn(vo.getCompletedOn());
             courseResp.setCompletedStatus(vo.getCompletedStatus());
             courseResp.setCourseId(vo.getCourseId());
             courseResp.setCourseName(vo.getCourseName());
             courseResp.setAuthorImg(vo.getAuthorImg());
             courseResp.setAuthorName(vo.getAuthorName());
             courseResp.setStartedOn(vo.getStartedOn());
             
             //Modules list
             
                double temp=0;
                List<CourseVO> moduleListFromDB = service.getStudentCoursesModules(vo.getCourseSessionId());
                List<ModuleRespTO> moduleList = new ArrayList<ModuleRespTO>(moduleListFromDB.size());
                for(CourseVO mod:moduleListFromDB)
                {
                modResp = new ModuleRespTO();
                modResp.setModuleId(mod.getModuleId());
                modResp.setModuleName(mod.getModuleName());
                modResp.setStartedOn(mod.getStartedOn());
                modResp.setCompletedStatus(mod.getCompletedStatus());
                modResp.setCompletedPercentStatus(mod.getCompletedPercentStatus());
                
                if(mod.getCompletedStatus().equalsIgnoreCase("y") || mod.getCompletedStatus().equals("1"))
                {
                temp=temp+1;
                }
                
                moduleList.add(modResp);
                }
             
             double completedCoursePercent=0;
             if(moduleListFromDB.size()>0){
             completedCoursePercent = temp/(double)moduleList.size();
             }
             
             courseResp.setCompletedPercentStatus(String.valueOf(completedCoursePercent*100));   

             courseResp.setModuleList(moduleList);
             courses.add(courseResp);
             }

             resp.setCourseList(courses);

            resp.setStatus(SLMSRestConstants.status_success);
            resp.setStatusMessage(SLMSRestConstants.message_success); 
        }catch(Exception e){
            System.out.println("Exception # getUserCourses "+e.getMessage());
            resp.setStatus(SLMSRestConstants.status_failure);
            resp.setStatusMessage(SLMSRestConstants.message_failure);
            resp.setErrorMessage(e.getMessage());            
        }
            
        return resp;
    }

    @Override
    public CourseResponse getUserCoursesWeb(CourseRequest req) throws RestBusException {
            CourseResponse resp = new CourseResponse();
            CourseServiceIface service = new CourseServiceImpl();
    
            try {

            //Courses list
             List<CourseVO> courseListFromDB = service.getStudentCourses(req.getUserId(), req.getSearchText());
            // List<CourseVO> courseListFromDB = service.getStudentCourses(req.getUserName(), req.getSearchText());
             ArrayList<CourseRespTO> courses = new ArrayList<CourseRespTO>(courseListFromDB.size());
             
             CourseRespTO courseResp = null;
             ModuleRespTO modResp = null;
             for(CourseVO vo : courseListFromDB)
             {
             courseResp = new CourseRespTO();
             courseResp.setCompletedOn(vo.getCompletedOn());
             courseResp.setCompletedStatus(vo.getCompletedStatus());
             courseResp.setCourseId(vo.getCourseId());
             courseResp.setCourseName(vo.getCourseName());
             courseResp.setAuthorImg(vo.getAuthorImg());
             courseResp.setAuthorName(vo.getAuthorName());
             courseResp.setStartedOn(vo.getStartedOn());
             
             //Modules list
             
                double temp=0;
                List<CourseVO> moduleListFromDB = service.getStudentCoursesModules(vo.getCourseSessionId());
                List<ModuleRespTO> moduleList = new ArrayList<ModuleRespTO>(moduleListFromDB.size());
                for(CourseVO mod:moduleListFromDB)
                {
                modResp = new ModuleRespTO();
                modResp.setModuleId(mod.getModuleId());
                modResp.setModuleName(mod.getModuleName());
                modResp.setStartedOn(mod.getStartedOn());
                modResp.setCompletedStatus(mod.getCompletedStatus());
                modResp.setCompletedPercentStatus(mod.getCompletedPercentStatus());
                
                if(mod.getCompletedStatus().equalsIgnoreCase("y") || mod.getCompletedStatus().equals("1"))
                {
                temp=temp+1;
                }
                
            //Resources list >>>>>>>                
                        List<ResourseVO> resourceListFromDB = service.getStudentResourcesWeb(req.getUserId(),vo.getCourseId(), mod.getModuleId(), req.getSearchText());
                        List<ResourceRespTO> resourceList = new ArrayList<ResourceRespTO>(resourceListFromDB.size());

                        ResourceRespTO resTo = null;
                        for(ResourseVO vo2:resourceListFromDB)
                        {
                            resTo = new ResourceRespTO();
                            resTo.setAuthorName(vo2.getAuthorName());
                            resTo.setAuthorImg(vo2.getAuthorImg());
                            resTo.setResourceId(""+vo2.getResourceId());
                            resTo.setResourceName(vo2.getResourceName());
                            resTo.setResourceDesc(vo2.getResourceDesc());
                            resTo.setResourceUrl(vo2.getResourceUrl());
                            resTo.setThumbImg(vo2.getThumbUrl());
                            resTo.setStartedOn(vo2.getStartedOn());
                            resTo.setCompletedOn(vo2.getCompletedOn());
                            resTo.setLikeCounts(vo2.getLikeCounts());
                            resTo.setCommentCounts(vo2.getCommentCounts());
                            resTo.setShareCounts(vo2.getShareCounts());

                            resourceList.add(resTo);
                        }

                        modResp.setResourceList(resourceList);

             //End resources list               
                
                moduleList.add(modResp);
                }
             
             double completedCoursePercent=0;
             if(moduleListFromDB.size()>0){
             completedCoursePercent = temp/(double)moduleList.size();
             }
             
             courseResp.setCompletedPercentStatus(String.valueOf(completedCoursePercent*100));   

             courseResp.setModuleList(moduleList);
             courses.add(courseResp);
             }

             resp.setCourseList(courses);

            resp.setStatus(SLMSRestConstants.status_success);
            resp.setStatusMessage(SLMSRestConstants.message_success); 
        }catch(Exception e){
            System.out.println("Exception # getUserCourses "+e.getMessage());
            resp.setStatus(SLMSRestConstants.status_failure);
            resp.setStatusMessage(SLMSRestConstants.message_failure);
            resp.setErrorMessage(e.getMessage());            
        }
            
        return resp;
    }
    
    
    public CourseResponse getUserCourses_x(CourseRequest req) throws RestBusException {
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
            CourseServiceIface service = new CourseServiceImpl();    
        
        try{
            //>>>>>>>>>>resources start
            List<ResourseVO> resourceListFromDB = service.getStudentResources(req.getUserId(), req.getCourseId(), req.getModuleId(), req.getSearchText());
            List<ResourceRespTO> resourceList = new ArrayList<ResourceRespTO>(resourceListFromDB.size());
            
            ResourceRespTO resTo = null;
            for(ResourseVO vo:resourceListFromDB)
            {
                resTo = new ResourceRespTO();
                resTo.setResourceId(""+vo.getResourceId());
                resTo.setResourceName(vo.getResourceName());
                resTo.setResourceDesc(vo.getResourceDesc());
                resTo.setResourceUrl(vo.getResourceUrl());
                resTo.setThumbImg(vo.getThumbUrl());
                resTo.setAuthorName(vo.getAuthorName());
                resTo.setAuthorImg(vo.getAuthorImg());
                
                resTo.setStartedOn(vo.getStartedOn());
                resTo.setCompletedOn(vo.getCompletedOn());
                resTo.setLikeCounts(vo.getLikeCounts());
                resTo.setCommentCounts(vo.getCommentCounts());
                resTo.setShareCounts(vo.getShareCounts());
                resTo.setIsLiked(vo.isIsLiked());
                
                //Resource Comments
                List<CommentVO> commentListDB = service.getResourceComments(vo.getResourceId());
                List<CommentRespTO> commentList = new ArrayList<CommentRespTO>(commentListDB.size());
                
                CommentRespTO resto2 = null;
                for(CommentVO vo2 : commentListDB)
                {
                resto2 = new CommentRespTO();    
                resto2.setCommentBy(vo2.getCommentBy());
                resto2.setCommentByImage(vo2.getCommentByImage());
                resto2.setCommentCounts(vo2.getCommentCounts());
                resto2.setCommentDate(vo2.getCommentDate());
                resto2.setCommentId(vo2.getCommentId());
                resto2.setCommentTxt(vo2.getCommentTxt());
                resto2.setLikeCounts(vo2.getLikeCounts());
                resto2.setParentCommentId(vo2.getParentCommentId());
                resto2.setShareCounts(vo2.getShareCounts());
                resto2.setIsLiked(vo2.isIsLiked());
                        
                commentList.add(resto2);        
                }
                resTo.setCommentList(commentList);
                            
                //Resources related video
                List<ResourseVO> relatedResFromDB = service.getRelatedResources(vo.getResourceId());
                List<ResourceRespTO> relatedResList = new ArrayList<ResourceRespTO>(relatedResFromDB.size());
                 
                ResourceRespTO temp=null;
                for(ResourseVO vo3 : relatedResFromDB)
                {
                 temp = new ResourceRespTO();
                 temp.setResourceId(""+vo3.getResourceId());
                 temp.setResourceName(vo3.getResourceName());
                 temp.setResourceDesc(vo3.getResourceDesc());
                 temp.setResourceUrl(vo3.getResourceUrl());
                 temp.setThumbImg(vo3.getThumbUrl());
                 temp.setUploadedDate(vo3.getUploadedDate());
                 
                 relatedResList.add(temp);
                }
                
                resTo.setRelatedVideoList(relatedResList);
                
                resourceList.add(resTo);
            }
             
            resp.setResourceList(resourceList);
             //>>>>>>>>>>resources end
            
            //Assignment start >>
            List<AssignmentVO> assgnmentsFromDb = service.getStudentAssignments(req.getUserId());
            List<AssignmentRespTO> assignmentList = new ArrayList<AssignmentRespTO>(assgnmentsFromDb.size());
            AssignmentRespTO assignmentVo = null;
            for(AssignmentVO vo4:assgnmentsFromDb)
            {
            assignmentVo = new AssignmentRespTO();
            assignmentVo.setAssignmentId(vo4.getAssignmentId());
            assignmentVo.setAssignmentName(vo4.getAssignmentName());
            assignmentVo.setAssignmentStatus(vo4.getAssignmentStatus());
            assignmentVo.setAssignmentSubmittedBy(vo4.getAssignmentSubmittedBy());
            assignmentVo.setAssignmentSubmittedDate(vo4.getAssignmentSubmittedDate());
            //Assignment Resources
            
            List<ResourseVO> attachedResourcesFromDB=service.getAssignmentsResources(req.getUserId(), vo4.getAssignmentId());
            List<ResourceRespTO> attachedResources = new ArrayList<ResourceRespTO>(attachedResourcesFromDB.size());
            ResourceRespTO resourceRespTO = null;
            for(ResourseVO vo5 : attachedResourcesFromDB)
            {
            resourceRespTO = new ResourceRespTO();
            resourceRespTO.setResourceId(String.valueOf(vo5.getResourceId()));
            resourceRespTO.setResourceName(vo5.getResourceName());
            resourceRespTO.setResourceDesc(vo5.getResourceDesc());
            resourceRespTO.setResourceUrl(vo5.getResourceUrl());
            resourceRespTO.setThumbImg(vo5.getThumbUrl());
            resourceRespTO.setUploadedDate(vo5.getUploadedDate());
            
            attachedResources.add(resourceRespTO);
            }
            
            assignmentVo.setAttachedResources(attachedResources);
            assignmentList.add(assignmentVo);
            }
            
            resp.setAssignmentList(assignmentList);
            //Status response section
            resp.setStatus(SLMSRestConstants.status_success);
            resp.setStatusMessage(SLMSRestConstants.message_success); 
        }catch(Exception e){
            System.out.println("Exception # getModuleResources "+e.getMessage());
            resp.setStatus(SLMSRestConstants.status_failure);
            resp.setStatusMessage(SLMSRestConstants.message_failure);
            resp.setErrorMessage(e.getMessage());            
        }
    
    return resp;
    }

    
    
    
    /**
     * Returns module resources.
     * @param req (userId/courseId/moduleId/searchText)
     * @return 
     * @throws RestBusException 
     */
    
    public CourseResponse getModuleResources_x(CourseRequest req) throws RestBusException {
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

    
    
    @Override
    public CourseResponse commentOnComment(CourseRequest req) throws RestBusException {
       CourseResponse resp = new CourseResponse();
       
       try{
                CourseServiceIface service = new CourseServiceImpl();
                service.saveCommentComment(req.getUserName(), req.getCommentId(), req.getCommentText());
           
                //setting success into response
                resp.setStatus(SLMSRestConstants.status_success);
                resp.setStatusMessage(SLMSRestConstants.message_success);                   

        } catch (LmsServiceException ex) {
            System.out.println("LmsServiceException # commentOnComment "+ex.getMessage());
            resp.setStatus(SLMSRestConstants.status_failure);
            resp.setStatusMessage(SLMSRestConstants.message_failure);
            resp.setErrorMessage(ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Exception # commentOnComment "+ex.getMessage());
            resp.setStatus(SLMSRestConstants.status_failure);
            resp.setStatusMessage(SLMSRestConstants.message_failure);
            resp.setErrorMessage(ex.getMessage());
        }
       
       return resp;
    }

    @Override
    public CourseResponse commentOnResource(CourseRequest req) throws RestBusException {
       CourseResponse resp = new CourseResponse();
       
       try{
                CourseServiceIface service = new CourseServiceImpl();
                service.saveResourceComment(req.getUserName(), req.getResourceId(), req.getCommentText());
           
                //setting success into response
                resp.setStatus(SLMSRestConstants.status_success);
                resp.setStatusMessage(SLMSRestConstants.message_success);                   

        } catch (LmsServiceException ex) {
            System.out.println("LmsServiceException # commentOnResource "+ex.getMessage());
            resp.setStatus(SLMSRestConstants.status_failure);
            resp.setStatusMessage(SLMSRestConstants.message_failure);
            resp.setErrorMessage(ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Exception # commentOnResource "+ex.getMessage());
            resp.setStatus(SLMSRestConstants.status_failure);
            resp.setStatusMessage(SLMSRestConstants.message_failure);
            resp.setErrorMessage(ex.getMessage());
        }
       
       return resp;
    }
    

    @Override
    public CourseResponse likeOnComment(String userName, int commentId) throws RestBusException {
       CourseResponse resp = new CourseResponse();
       
       try{
                CourseServiceIface service = new CourseServiceImpl();
                service.saveCommentLike(userName, commentId);
           
                //setting success into response
                resp.setStatus(SLMSRestConstants.status_success);
                resp.setStatusMessage(SLMSRestConstants.message_success);                   

        } catch (LmsServiceException ex) {
            System.out.println("LmsServiceException # likeOnComment "+ex.getMessage());
            resp.setStatus(SLMSRestConstants.status_failure);
            resp.setStatusMessage(SLMSRestConstants.message_failure);
            resp.setErrorMessage(ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Exception # likeOnComment "+ex.getMessage());
            resp.setStatus(SLMSRestConstants.status_failure);
            resp.setStatusMessage(SLMSRestConstants.message_failure);
            resp.setErrorMessage(ex.getMessage());
        }
       
       return resp;
    }

    
    @Override
    public CourseResponse likeOnResource(String userName, int resourceId) throws RestBusException {
       CourseResponse resp = new CourseResponse();
       
       try{
                CourseServiceIface service = new CourseServiceImpl();
                service.saveResourceLike(userName, resourceId);
           
                //setting success into response
                resp.setStatus(SLMSRestConstants.status_success);
                resp.setStatusMessage(SLMSRestConstants.message_success);                   

        } catch (LmsServiceException ex) {
            System.out.println("LmsServiceException # likeOnResource "+ex.getMessage());
            resp.setStatus(SLMSRestConstants.status_failure);
            resp.setStatusMessage(SLMSRestConstants.message_failure);
            resp.setErrorMessage(ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Exception # likeOnResource "+ex.getMessage());
            resp.setStatus(SLMSRestConstants.status_failure);
            resp.setStatusMessage(SLMSRestConstants.message_failure);
            resp.setErrorMessage(ex.getMessage());
        }
       
       return resp;
    }
    
    
}//end of class

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scolere.lms.application.rest.bus.impl;

import com.scolere.lms.application.rest.bus.iface.CommonBusIface;
import com.scolere.lms.application.rest.constants.SLMSRestConstants;
import com.scolere.lms.application.rest.exceptions.RestBusException;
import com.scolere.lms.application.rest.vo.request.CommonRequest;
import com.scolere.lms.application.rest.vo.response.ClassRespTO;
import com.scolere.lms.application.rest.vo.response.CommentRespTO;
import com.scolere.lms.application.rest.vo.response.CommonResponse;
import com.scolere.lms.application.rest.vo.response.CourseRespTO;
import com.scolere.lms.application.rest.vo.response.FeedRespTO;
import com.scolere.lms.application.rest.vo.response.HomeRoomRespTO;
import com.scolere.lms.application.rest.vo.response.SchoolRespTO;
import com.scolere.lms.domain.vo.ClassMasterVo;
import com.scolere.lms.domain.vo.SchoolMasterVo;
import com.scolere.lms.service.iface.CommonServiceIface;
import com.scolere.lms.service.impl.CommonServiceImpl;
import java.util.ArrayList;
import java.util.List;
import my.java.interfac.HomeRoomMasterVo;

/**
 *
 * @author dell
 */
public class CommonBusImpl implements CommonBusIface{
    

    @Override
    public CommonResponse getFeedsList(CommonRequest req) throws RestBusException {
        
        CommonResponse resp = new CommonResponse();
        CommonServiceIface service = new CommonServiceImpl();
        
        try{
            
         List<FeedRespTO> feedList = new ArrayList<FeedRespTO>();
         FeedRespTO feed = new FeedRespTO();
         feed.setFeedId(1); 
         feed.setFeedText("You liked <x RK Physics vol-1#resourceId=1 /x> of <x Force#moduleId=1 /x>");
         feed.setCommentCounts(5);
         feed.setIsLiked(false);
         feed.setLikeCounts(4);
         feed.setShareCounts(8);

         List<CommentRespTO> feedCommentsList = new ArrayList<CommentRespTO>();
         CommentRespTO rto = new CommentRespTO();
         rto.setCommentBy("Mahendra Singh");
         rto.setCommentByImage("default-user.png");
         rto.setCommentCounts(11);
         rto.setCommentTxt("Swimming is good excercise for health.");
         rto.setCommentDate("2015-05-05");
         rto.setCommentId(1);
         feedCommentsList.add(rto);
         
         rto = new CommentRespTO();
         rto.setCommentBy("D Mayank");
         rto.setCommentByImage("default-user.png");
         rto.setCommentCounts(11);
         rto.setCommentTxt("Swimming is good excercise for health.");
         rto.setCommentDate("2015-05-05");
         rto.setCommentId(2);         
         feedCommentsList.add(rto);
         
         feed.setFeedCommentsList(feedCommentsList);
         feedList.add(feed);
            
         feed = new FeedRespTO();
         feed.setFeedId(2); 
         feed.setFeedText("You liked <x RK Physics vol-2#resourceId=2 /x> of <x Velocity#moduleId=2 /x>");
         feed.setFeedCommentsList(feedCommentsList);
         feedList.add(feed);
         
         feed = new FeedRespTO();
         feed.setFeedId(3); 
         feed.setFeedText("You liked <x RK Physics vol-3#resourceId=3 /x> of <x Acceleration#moduleId=3 /x>");
         feed.setFeedCommentsList(feedCommentsList);
         feedList.add(feed);         
         
         
         resp.setFeedList(feedList);
        //--------------common---    
        resp.setStatus(SLMSRestConstants.status_success);
        resp.setStatusMessage(SLMSRestConstants.message_success); 
        }catch(Exception e){
            System.out.println("Exception # getFeedsList "+e.getMessage());
            resp.setStatus(SLMSRestConstants.status_failure);
            resp.setStatusMessage(SLMSRestConstants.message_failure);
            resp.setErrorMessage(e.getMessage());            
        }
        
        return resp;
  }

    
    @Override
    public CommonResponse getCourseDetail(int courseId) throws RestBusException {
        CommonResponse resp = new CommonResponse();
        CommonServiceIface service = new CommonServiceImpl();
        
        try{
        CourseRespTO course = new CourseRespTO();
        course.setCourseId("1");
        course.setCourseName("Test Course");
            
            
        resp.setStatus(SLMSRestConstants.status_success);
        resp.setStatusMessage(SLMSRestConstants.message_success); 
        }catch(Exception e){
            System.out.println("Exception # getCourseDetail "+e.getMessage());
            resp.setStatus(SLMSRestConstants.status_failure);
            resp.setStatusMessage(SLMSRestConstants.message_failure);
            resp.setErrorMessage(e.getMessage());            
        }
        
        return resp;
  }

    
    @Override
    public CommonResponse getModuleDetail(int moduleId) throws RestBusException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    
    @Override
    public CommonResponse getResourseDetail(int resourseId) throws RestBusException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    
    @Override
    public CommonResponse getUserDetail(int userId) throws RestBusException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    

    @Override
    public CommonResponse getAssignmentDetail(int assignmentId) throws RestBusException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
       
    
    
    /*
     * Get School,Class & homeroom master table data and display in given hirarchy.
     * 
     */
    
    @Override
    public CommonResponse getSchoolMasterData() throws RestBusException {
        CommonResponse resp = new CommonResponse();
        CommonServiceIface service = new CommonServiceImpl();
        
        try{
        
        List<SchoolMasterVo> schoolListFromDB = service.getSchoolMasterVoList();
        List<SchoolRespTO> schoolList = new ArrayList<SchoolRespTO>(schoolListFromDB.size());

        SchoolRespTO schoolRespTO = null;
        for(SchoolMasterVo vo : schoolListFromDB)
        {
        schoolRespTO = new SchoolRespTO(String.valueOf(vo.getSchoolId()), vo.getSchoolName());
        //Class master data
        List<ClassMasterVo> clsMstrListDB = service.getClassVoList(vo.getSchoolId());
        List<ClassRespTO> classList = new ArrayList<ClassRespTO>(clsMstrListDB.size());
        ClassRespTO classRespTO = null;
        for(ClassMasterVo clsvo : clsMstrListDB)
        {
        classRespTO = new ClassRespTO(String.valueOf(clsvo.getClassId()), clsvo.getClassName());
        //Homeroom data
        List<HomeRoomMasterVo> hrmListDB = service.getHomeRoomMasterVoList(clsvo.getClassId());
        List<HomeRoomRespTO> homeRoomList = new ArrayList<HomeRoomRespTO>(hrmListDB.size());
        HomeRoomRespTO homeRoomRespTO = null;
        for(HomeRoomMasterVo hrmvo  : hrmListDB)
        {
        homeRoomRespTO = new HomeRoomRespTO(String.valueOf(hrmvo.getHomeRoomMstrId()), hrmvo.getHomeRoomMstrName());
        homeRoomList.add(homeRoomRespTO);
        }//hrm loop
        
        classRespTO.setHomeRoomList(homeRoomList);
        classList.add(classRespTO);
        }//cls loop
        
        schoolRespTO.setClassList(classList);
        schoolList.add(schoolRespTO);
        } //school loop
        resp.setSchoolList(schoolList);

        resp.setStatus(SLMSRestConstants.status_success);
        resp.setStatusMessage(SLMSRestConstants.message_success); 
        }catch(Exception e){
            System.out.println("Exception # getSchoolMasterData "+e.getMessage());
            resp.setStatus(SLMSRestConstants.status_failure);
            resp.setStatusMessage(SLMSRestConstants.message_failure);
            resp.setErrorMessage(e.getMessage());            
        }
        
        return resp;
  }

       
    
}//End of class

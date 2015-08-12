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
import com.scolere.lms.application.rest.vo.response.KeyValTypeVO;
import com.scolere.lms.application.rest.vo.response.ResourceRespTO;
import com.scolere.lms.application.rest.vo.response.SchoolRespTO;
import com.scolere.lms.application.rest.vo.response.UserResponse;
import com.scolere.lms.domain.exception.LmsServiceException;
import com.scolere.lms.domain.vo.ClassMasterVo;
import com.scolere.lms.domain.vo.SchoolMasterVo;
import com.scolere.lms.domain.vo.cross.CommentVO;
import com.scolere.lms.domain.vo.cross.FeedVO;
import com.scolere.lms.domain.vo.cross.ResourseVO;
import com.scolere.lms.domain.vo.cross.UserVO;
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
         List<FeedVO> feedListFromDb = service.getFeedsList(req.getUserId(), req.getSearchText());   
         List<FeedRespTO> feedList = new ArrayList<FeedRespTO>(feedListFromDb.size());
         FeedRespTO feed = null;
         
         for(FeedVO vo:feedListFromDb)
         {
         feed = new FeedRespTO();
         //set feedID
         feed.setFeedId(vo.getFeedID());
         
         //start - Create feedText & set to resp
         //String temp = vo.getFeedTemplate(); //{?}
         feed.setFeedText(vo.getFeedTemplate());

         {
             String[] params = vo.getTempParam().split(SLMSRestConstants.FEED_TMPLT_PARAM_SEPARATOR); //user,assignment,module,resource,course
             List<KeyValTypeVO> kvtoList = new ArrayList<KeyValTypeVO>(params.length);

             for(String param : params)
             {
                String[] tempArray = null;
                        
                if(param.equalsIgnoreCase(SLMSRestConstants.FEED_TMPLT_PARAM_COURSE))
                {
                    tempArray = service.getCourseFeedText(vo.getCourseId()).split("#");
                }
                else if(param.equalsIgnoreCase(SLMSRestConstants.FEED_TMPLT_PARAM_RESOURSE))
                {
                    tempArray =  service.getResourceFeedText(vo.getResourseId()).split("#");
                }
                else if(param.equalsIgnoreCase(SLMSRestConstants.FEED_TMPLT_PARAM_USER))
                {
                    tempArray =   service.getUserFeedText(vo.getUserId()).split("#");
                }              
                else if(param.equalsIgnoreCase(SLMSRestConstants.FEED_TMPLT_PARAM_ASSIGNMENT))
                {
                    tempArray =   service.getAssignmentFeedText(vo.getAssignmentId()).split("#");
                }              
                else if(param.equalsIgnoreCase(SLMSRestConstants.FEED_TMPLT_PARAM_MODULE))
                {
                    tempArray =   service.getModuleFeedText(vo.getModuleId()).split("#");
                }              
                
               // temp = temp.replaceFirst(SLMSRestConstants.FEED_TMPLT_PLACEHOLDER, feedTextNm);
                if(tempArray!=null)
                {
                KeyValTypeVO kvto = new KeyValTypeVO(tempArray[1],tempArray[0],param);
                kvtoList.add(kvto);
                }
             }
             feed.setFeedTextArray(kvtoList);
         }
         
         //end - Create feedText & set to resp
         feed.setCommentCounts(vo.getCommentCounts());
         feed.setLikeCounts(vo.getLikeCounts());
         feed.setIsLiked(vo.isIsLiked());
         
         //Start - Set Comment List
         List<CommentVO> commentListDB = service.getFeedCommentsList(vo.getFeedID());
         List<CommentRespTO> feedCommentsList = new ArrayList<CommentRespTO>(commentListDB.size());
         CommentRespTO crto=null;
         for(CommentVO cvo : commentListDB)
         {
         crto = new CommentRespTO();
         crto.setCommentBy(cvo.getCommentBy());
         crto.setCommentDate(cvo.getCommentDate());
         crto.setCommentId(cvo.getCommentId());
         crto.setCommentTxt(cvo.getCommentTxt());
         crto.setParentCommentId(cvo.getParentCommentId());
         
         feedCommentsList.add(crto);
         }
         feed.setFeedCommentsList(feedCommentsList);
         //End - Set Comment List
         
         //start - Set user details 
         UserVO userFromDB=service.getUserDetail(vo.getUserName());
         if(userFromDB != null)
         {
         UserResponse user = new UserResponse();
         user.setUserId(String.valueOf(userFromDB.getUserId()));
         user.setUserName(userFromDB.getUserName());
         user.setUserFbId(userFromDB.getUserFbId());
         user.setFirstName(userFromDB.getFirstName());
         user.setLastName(userFromDB.getLastName());
         user.setTitle(userFromDB.getTitle());
         user.setEmailId(userFromDB.getEmailId());
         user.setAdminEmailId(user.getAdminEmailId());
         user.setProfileImage(userFromDB.getProfileImage());

         feed.setUser(user);
         }
         //end - Set user details 

         //set default resource
         ResourseVO defaultRes = service.getDefaultResourseDetail(feed.getFeedId());
         if(defaultRes != null)
         {
         ResourceRespTO rsto = new ResourceRespTO();
         rsto.setResourceId(String.valueOf(defaultRes.getResourceId()));
         rsto.setResourceName(defaultRes.getResourceName());
         rsto.setResourceDesc(defaultRes.getResourceDesc());
         rsto.setResourceUrl(defaultRes.getResourceUrl());
         rsto.setThumbImg(defaultRes.getThumbUrl());
         rsto.setAuthorName(defaultRes.getAuthorName());
         rsto.setAuthorImg(defaultRes.getAuthorImg());
         
         feed.setResource(rsto);
         }
         
         feedList.add(feed);
         }
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
    public CommonResponse getCourseDetail(int feedId) throws RestBusException {
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
    public CommonResponse getModuleDetail(int feedId) throws RestBusException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    
    @Override
    public CommonResponse getResourseDetail(int feedId) throws RestBusException {

        CommonResponse resp = new CommonResponse();
        CommonServiceIface service = new CommonServiceImpl();
        
        ResourceRespTO rsto = null;
        
        try{
         ResourseVO defaultRes = service.getResourseDetail(feedId);
         if(defaultRes != null)
         {
         rsto = new ResourceRespTO();
         rsto.setResourceId(String.valueOf(defaultRes.getResourceId()));
         rsto.setResourceName(defaultRes.getResourceName());
         rsto.setResourceDesc(defaultRes.getResourceDesc());
         rsto.setResourceUrl(defaultRes.getResourceUrl());
         rsto.setThumbImg(defaultRes.getThumbUrl());
         rsto.setAuthorName(defaultRes.getAuthorName());
         rsto.setAuthorImg(defaultRes.getAuthorImg());
         }
        resp.setResourceDetail(rsto);
        
        resp.setStatus(SLMSRestConstants.status_success);
        resp.setStatusMessage(SLMSRestConstants.message_success); 
        }catch(Exception e){
            System.out.println("Exception # getResourseDetail "+e.getMessage());
            resp.setStatus(SLMSRestConstants.status_failure);
            resp.setStatusMessage(SLMSRestConstants.message_failure);
            resp.setErrorMessage(e.getMessage());            
        }
        
        return resp;
  }

    
    @Override
    public CommonResponse getUserDetail(int userId) throws RestBusException {
        CommonResponse resp = new CommonResponse();
        CommonServiceIface service = new CommonServiceImpl();
        
        try{
            
         UserResponse user = null;
         UserVO userFromDB = service.getUserDetail(userId);
         
        if(userFromDB != null)
        {
         user = new UserResponse();
         user.setUserId(String.valueOf(userFromDB.getUserId()));
         user.setUserName(userFromDB.getUserName());
         user.setUserFbId(userFromDB.getUserFbId());
         user.setFirstName(userFromDB.getFirstName());
         user.setLastName(userFromDB.getLastName());
         user.setTitle(userFromDB.getTitle());
         user.setEmailId(userFromDB.getEmailId());
         user.setAdminEmailId(user.getAdminEmailId());
         user.setProfileImage(userFromDB.getProfileImage());
        }
        resp.setUserDetail(user);
        
        resp.setStatus(SLMSRestConstants.status_success);
        resp.setStatusMessage(SLMSRestConstants.message_success); 
        }catch(Exception e){
            System.out.println("Exception # getUserDetail "+e.getMessage());
            resp.setStatus(SLMSRestConstants.status_failure);
            resp.setStatusMessage(SLMSRestConstants.message_failure);
            resp.setErrorMessage(e.getMessage());            
        }
        
        return resp;
  }
    

    @Override
    public CommonResponse getAssignmentDetail(int feedId) throws RestBusException {
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

    @Override
    public CommonResponse commentOnFeed(CommonRequest req) throws RestBusException {
       CommonResponse resp = new CommonResponse();
       
       try{
                CommonServiceIface service = new CommonServiceImpl();
                service.saveFeedComment(req.getUserName(), req.getCommentId(), req.getCommentText());
           
                //setting success into response
                resp.setStatus(SLMSRestConstants.status_success);
                resp.setStatusMessage(SLMSRestConstants.message_success);                   

        } catch (LmsServiceException ex) {
            System.out.println("LmsServiceException # commentOnFeed "+ex.getMessage());
            resp.setStatus(SLMSRestConstants.status_failure);
            resp.setStatusMessage(SLMSRestConstants.message_failure);
            resp.setErrorMessage(ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Exception # commentOnFeed "+ex.getMessage());
            resp.setStatus(SLMSRestConstants.status_failure);
            resp.setStatusMessage(SLMSRestConstants.message_failure);
            resp.setErrorMessage(ex.getMessage());
        }
       
       return resp;
    }

    
    @Override
    public CommonResponse likeOnFeed(String userName, int feedId) throws RestBusException {
       CommonResponse resp = new CommonResponse();
       
       try{
                CommonServiceIface service = new CommonServiceImpl();
                service.saveFeedLike(userName, feedId);
           
                //setting success into response
                resp.setStatus(SLMSRestConstants.status_success);
                resp.setStatusMessage(SLMSRestConstants.message_success);                   

        } catch (LmsServiceException ex) {
            System.out.println("LmsServiceException # likeOnFeed "+ex.getMessage());
            resp.setStatus(SLMSRestConstants.status_failure);
            resp.setStatusMessage(SLMSRestConstants.message_failure);
            resp.setErrorMessage(ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Exception # likeOnFeed "+ex.getMessage());
            resp.setStatus(SLMSRestConstants.status_failure);
            resp.setStatusMessage(SLMSRestConstants.message_failure);
            resp.setErrorMessage(ex.getMessage());
        }
       
       return resp;
    }

    
    @Override
    public CommonResponse likeOnComment(String userName, int commentId) throws RestBusException {
       CommonResponse resp = new CommonResponse();
       
       try{
                CommonServiceIface service = new CommonServiceImpl();
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
    public CommonResponse commentOnComment(CommonRequest req) throws RestBusException {
       CommonResponse resp = new CommonResponse();
       
       try{
                CommonServiceIface service = new CommonServiceImpl();
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

       
    
}//End of class

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scolere.lms.application.rest.bus.impl;

import java.util.ArrayList;
import java.util.List;

import com.scolere.lms.application.rest.bus.iface.CommonBusIface;
import com.scolere.lms.application.rest.constants.SLMSRestConstants;
import com.scolere.lms.application.rest.exceptions.RestBusException;
import com.scolere.lms.application.rest.vo.request.CommonRequest;
import com.scolere.lms.application.rest.vo.response.AssignmentRespTO;
import com.scolere.lms.application.rest.vo.response.ClassRespTO;
import com.scolere.lms.application.rest.vo.response.CommentRespTO;
import com.scolere.lms.application.rest.vo.response.CommonResponse;
import com.scolere.lms.application.rest.vo.response.CourseRespTO;
import com.scolere.lms.application.rest.vo.response.FeedRespTO;
import com.scolere.lms.application.rest.vo.response.HomeRoomRespTO;
import com.scolere.lms.application.rest.vo.response.KeyValTypeVO;
import com.scolere.lms.application.rest.vo.response.ModuleRespTO;
import com.scolere.lms.application.rest.vo.response.ResourceRespTO;
import com.scolere.lms.application.rest.vo.response.SchoolRespTO;
import com.scolere.lms.application.rest.vo.response.UserResponse;
import com.scolere.lms.common.utils.PropertyManager;
import com.scolere.lms.domain.exception.LmsServiceException;
import com.scolere.lms.domain.vo.AssignmentVO;
import com.scolere.lms.domain.vo.ClassMasterVo;
import com.scolere.lms.domain.vo.CourseMasterVo;
import com.scolere.lms.domain.vo.HomeRoomMasterVo;
import com.scolere.lms.domain.vo.ModuleMasterVo;
import com.scolere.lms.domain.vo.SchoolMasterVo;
import com.scolere.lms.domain.vo.UserLoginVo;
import com.scolere.lms.domain.vo.cross.CommentVO;
import com.scolere.lms.domain.vo.cross.FeedVO;
import com.scolere.lms.domain.vo.cross.ResourseVO;
import com.scolere.lms.domain.vo.cross.UserVO;
import com.scolere.lms.service.iface.CommonServiceIface;
import com.scolere.lms.service.iface.LoginServiceIface;
import com.scolere.lms.service.impl.CommonServiceImpl;
import com.scolere.lms.service.impl.LoginServiceImpl;


/**
 *
 * @author dell
 */
public class CommonBusImpl implements CommonBusIface{
    
	
	@Override
	public CommonResponse getFeedDetail(int userId, int feedId)
			throws RestBusException {
        
        CommonResponse resp = new CommonResponse();
        CommonServiceIface service = new CommonServiceImpl();
        
        try{
        
         FeedVO vo = service.getFeedDetail(userId, feedId);
         if(vo!=null)
         {
         FeedRespTO feed=new FeedRespTO();
        
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
                else if(param.equalsIgnoreCase(SLMSRestConstants.FEED_TMPLT_PARAM_HOMEROOM))
                {
                    tempArray =  service.getHomeRoomFeedText(vo.getHrmId()).split("#");
                    System.out.println("homeroom");
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
         feed.setFeedOn(vo.getActivityOn());
         
         //Start - Set Comment List
         List<CommentVO> commentListDB = service.getFeedCommentsList(vo.getFeedID(),userId,SLMSRestConstants.pagination_offset,SLMSRestConstants.pagination_records);   
         List<CommentRespTO> feedCommentsList = new ArrayList<CommentRespTO>(commentListDB.size());
         CommentRespTO crto,childCrto=null;
         for(CommentVO cvo : commentListDB)
         {
             crto = new CommentRespTO();
             crto.setCommentBy(cvo.getCommentBy());
             crto.setCommentById(cvo.getCommentById());
             crto.setCommentByImage(PropertyManager.getProperty(SLMSRestConstants.baseUrl_userprofile)+cvo.getCommentByImage());
             crto.setCommentDate(cvo.getCommentDate());
             crto.setCommentId(cvo.getCommentId());
             crto.setCommentTxt(cvo.getCommentTxt());
             crto.setParentCommentId(cvo.getParentCommentId());
             crto.setCommentCounts(cvo.getCommentCounts());
             crto.setLikeCounts(cvo.getLikeCounts());
             crto.setIsLiked(cvo.isIsLiked());
             
         //Setting Child comments
         List<CommentVO> childCommentListDB = service.getFeedChildCommentsList(cvo.getCommentId(),userId,SLMSRestConstants.pagination_offset,SLMSRestConstants.pagination_records);   
         List<CommentRespTO> childCommentsList = new ArrayList<CommentRespTO>(childCommentListDB.size());
	         for(CommentVO cvo2 : childCommentListDB)
	         {
	        	 childCrto = new CommentRespTO();
	        	 childCrto.setCommentBy(cvo2.getCommentBy());
	        	 childCrto.setCommentById(cvo2.getCommentById());
	        	 childCrto.setCommentByImage(PropertyManager.getProperty(SLMSRestConstants.baseUrl_userprofile)+cvo2.getCommentByImage());
	        	 childCrto.setCommentDate(cvo2.getCommentDate());
	        	 childCrto.setCommentId(cvo2.getCommentId());
	        	 childCrto.setCommentTxt(cvo2.getCommentTxt());
	        	 childCrto.setParentCommentId(cvo2.getParentCommentId());
	        	 childCrto.setCommentCounts(cvo2.getCommentCounts());
	        	 childCrto.setLikeCounts(cvo2.getLikeCounts());
	        	 childCrto.setIsLiked(cvo2.isIsLiked());
	        	 
	        	 childCommentsList.add(childCrto);
	         }
         
	         crto.setSubComments(childCommentsList);
	         
         feedCommentsList.add(crto);
         }
         feed.setFeedCommentsList(feedCommentsList);
         //End - Set Comment List
         
         //start - Set user details 
         UserResponse user=this.getUserDetail(vo.getUserId()).getUserDetail();
         feed.setUser(user);
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
         
         resp.setFeedDetail(feed);
         
         }else{
        	 resp.setErrorMessage(SLMSRestConstants.message_recordnotfound);
         }
         
        //--------------common---    
        resp.setStatus(SLMSRestConstants.status_success);
        resp.setStatusMessage(SLMSRestConstants.message_success); 
        }catch(Exception e){
            System.out.println("Exception # getFeedDetail "+e.getMessage());
            resp.setStatus(SLMSRestConstants.status_failure);
            resp.setStatusMessage(SLMSRestConstants.message_failure);
            resp.setErrorMessage(e.getMessage());            
        }
        
        return resp;
	}
	

	
    @Override
    public CommonResponse getFeedsList(CommonRequest req) throws RestBusException {
        
        CommonResponse resp = new CommonResponse();
        CommonServiceIface service = new CommonServiceImpl();
        
        try{
        
         //Total feed records for pagination
         resp.setTotalRecords(service.getTotalFeedsCount(req.getUserId()));
         
         //Setting feed list	
         List<FeedVO> feedListFromDb = service.getFeedsList(req.getUserId(), req.getSearchText(),req.getOffset(),req.getNoOfRecords());   
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
                else if(param.equalsIgnoreCase(SLMSRestConstants.FEED_TMPLT_PARAM_HOMEROOM))
                {
                    tempArray =  service.getHomeRoomFeedText(vo.getHrmId()).split("#");
                    System.out.println("homeroom");
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
         feed.setFeedOn(vo.getActivityOn());
         
         //Start - Set Comment List
         List<CommentVO> commentListDB = service.getFeedCommentsList(vo.getFeedID(),req.getUserId(),SLMSRestConstants.pagination_offset,SLMSRestConstants.pagination_records);   
         List<CommentRespTO> feedCommentsList = new ArrayList<CommentRespTO>(commentListDB.size());
         CommentRespTO crto,childCrto=null;
         for(CommentVO cvo : commentListDB)
         {
             crto = new CommentRespTO();
             crto.setCommentBy(cvo.getCommentBy());
             crto.setCommentById(cvo.getCommentById());
             crto.setCommentByImage(PropertyManager.getProperty(SLMSRestConstants.baseUrl_userprofile)+cvo.getCommentByImage());
             crto.setCommentDate(cvo.getCommentDate());
             crto.setCommentId(cvo.getCommentId());
             crto.setCommentTxt(cvo.getCommentTxt());
             crto.setParentCommentId(cvo.getParentCommentId());
             crto.setCommentCounts(cvo.getCommentCounts());
             crto.setLikeCounts(cvo.getLikeCounts());
             crto.setIsLiked(cvo.isIsLiked());
             
         //Setting Child comments
         List<CommentVO> childCommentListDB = service.getFeedChildCommentsList(cvo.getCommentId(),req.getUserId(),SLMSRestConstants.pagination_offset,SLMSRestConstants.pagination_records);   
         List<CommentRespTO> childCommentsList = new ArrayList<CommentRespTO>(childCommentListDB.size());
	         for(CommentVO cvo2 : childCommentListDB)
	         {
	        	 childCrto = new CommentRespTO();
	        	 childCrto.setCommentBy(cvo2.getCommentBy());
	        	 childCrto.setCommentById(cvo2.getCommentById());
	        	 childCrto.setCommentByImage(PropertyManager.getProperty(SLMSRestConstants.baseUrl_userprofile)+cvo2.getCommentByImage());
	        	 childCrto.setCommentDate(cvo2.getCommentDate());
	        	 childCrto.setCommentId(cvo2.getCommentId());
	        	 childCrto.setCommentTxt(cvo2.getCommentTxt());
	        	 childCrto.setParentCommentId(cvo2.getParentCommentId());
	        	 childCrto.setCommentCounts(cvo2.getCommentCounts());
	        	 childCrto.setLikeCounts(cvo2.getLikeCounts());
	        	 childCrto.setIsLiked(cvo2.isIsLiked());
	        	 
	        	 childCommentsList.add(childCrto);
	         }
         
	         crto.setSubComments(childCommentsList);
	         
         feedCommentsList.add(crto);
         }
         feed.setFeedCommentsList(feedCommentsList);
         //End - Set Comment List
         
         //start - Set user details 
         UserResponse user=this.getUserDetail(vo.getUserId()).getUserDetail();
         feed.setUser(user);
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
    public CommonResponse getNotificationsList(CommonRequest req) throws RestBusException {
        
        CommonResponse resp = new CommonResponse();
        CommonServiceIface service = new CommonServiceImpl();
        
        try{
        
         //Total feed records for pagination
         resp.setTotalRecords(service.getTotalFeedsCount(req.getUserId()));
         
         //Setting feed list	
         List<FeedVO> feedListFromDb = service.getNotificationsList(req.getUserId(), req.getSearchText(),req.getOffset(),req.getNoOfRecords());   
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
                else if(param.equalsIgnoreCase(SLMSRestConstants.FEED_TMPLT_PARAM_HOMEROOM))
                {
                    tempArray =  service.getHomeRoomFeedText(vo.getHrmId()).split("#");
                    System.out.println("homeroom");
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
         
         feed.setFeedOn(vo.getActivityOn());
         
         //start - Set user details 
         UserResponse user=this.getUserDetail(vo.getUserId()).getUserDetail();
         feed.setUser(user);
         //end - Set user details         
         feed.setViewStatus(vo.getViewStatus());
         
         feedList.add(feed);
         }
         
         resp.setFeedList(feedList);
        //--------------common---    
        resp.setStatus(SLMSRestConstants.status_success);
        resp.setStatusMessage(SLMSRestConstants.message_success); 
        }catch(Exception e){
            System.out.println("Exception # getNotificationsList "+e.getMessage());
            resp.setStatus(SLMSRestConstants.status_failure);
            resp.setStatusMessage(SLMSRestConstants.message_failure);
            resp.setErrorMessage(e.getMessage());            
        }
        
        return resp;
  }
    

    
    @Override
    public CommonResponse getFeedComments(CommonRequest req) throws RestBusException {
        
        CommonResponse resp = new CommonResponse();
        CommonServiceIface service = new CommonServiceImpl();
        
        try{
        	
        //Setting comment list	
         List<CommentRespTO> feedCommentsList = new ArrayList<CommentRespTO>(1);
        	
         if(req.getFeedId()>0 && req.getCommentId()==0)	
         {
         //Setting total comment counts
         resp.setTotalRecords(service.getTotalCommentsCount(req.getFeedId()));	
        	 
         //Start - Set Comment List
         List<CommentVO> commentListDB = service.getFeedCommentsList(req.getFeedId(),req.getUserId(),req.getOffset(),req.getNoOfRecords());   
         feedCommentsList = new ArrayList<CommentRespTO>(commentListDB.size());
         CommentRespTO crto,childCrto=null;
         for(CommentVO cvo : commentListDB)
         {
             crto = new CommentRespTO();
             crto.setCommentBy(cvo.getCommentBy());
             crto.setCommentById(cvo.getCommentById());
             crto.setCommentByImage(PropertyManager.getProperty(SLMSRestConstants.baseUrl_userprofile)+cvo.getCommentByImage());
             crto.setCommentDate(cvo.getCommentDate());
             crto.setCommentId(cvo.getCommentId());
             crto.setCommentTxt(cvo.getCommentTxt());
             crto.setParentCommentId(cvo.getParentCommentId());
             crto.setCommentCounts(cvo.getCommentCounts());
             crto.setLikeCounts(cvo.getLikeCounts());
             crto.setIsLiked(cvo.isIsLiked());
             
         //Setting Child comments
         List<CommentVO> childCommentListDB = service.getFeedChildCommentsList(cvo.getCommentId(),req.getUserId(),SLMSRestConstants.pagination_offset,SLMSRestConstants.pagination_records);   
         List<CommentRespTO> childCommentsList = new ArrayList<CommentRespTO>(childCommentListDB.size());
	         for(CommentVO cvo2 : childCommentListDB)
	         {
	        	 childCrto = new CommentRespTO();
	        	 childCrto.setCommentBy(cvo2.getCommentBy());
	        	 childCrto.setCommentById(cvo2.getCommentById());
	        	 childCrto.setCommentByImage(PropertyManager.getProperty(SLMSRestConstants.baseUrl_userprofile)+cvo2.getCommentByImage());
	        	 childCrto.setCommentDate(cvo2.getCommentDate());
	        	 childCrto.setCommentId(cvo2.getCommentId());
	        	 childCrto.setCommentTxt(cvo2.getCommentTxt());
	        	 childCrto.setParentCommentId(cvo2.getParentCommentId());
	        	 childCrto.setCommentCounts(cvo2.getCommentCounts());
	        	 childCrto.setLikeCounts(cvo2.getLikeCounts());
	        	 childCrto.setIsLiked(cvo2.isIsLiked());
	        	 
	        	 childCommentsList.add(childCrto);
	         }
         
	         crto.setSubComments(childCommentsList);
	         
         feedCommentsList.add(crto);
         }
         
         }else{
        	 //Comment list
        	 
        	 if(req.getCommentId()>0)
        	 {
   	        //Setting total comment counts
             resp.setTotalRecords(service.getTotalCommentsCount(req.getFeedId(), req.getCommentId()));	
        		 
             List<CommentVO> childCommentListDB = service.getFeedChildCommentsList(req.getCommentId(),req.getUserId(),req.getOffset(),req.getNoOfRecords());      
             feedCommentsList = new ArrayList<CommentRespTO>(childCommentListDB.size());
             	
             CommentRespTO childCrto = null;
    	         for(CommentVO cvo2 : childCommentListDB)
    	         {
    	        	 childCrto = new CommentRespTO();
    	        	 childCrto.setCommentBy(cvo2.getCommentBy());
    	        	 childCrto.setCommentById(cvo2.getCommentById());
    	        	 childCrto.setCommentByImage(PropertyManager.getProperty(SLMSRestConstants.baseUrl_userprofile)+cvo2.getCommentByImage());
    	        	 childCrto.setCommentDate(cvo2.getCommentDate());
    	        	 childCrto.setCommentId(cvo2.getCommentId());
    	        	 childCrto.setCommentTxt(cvo2.getCommentTxt());
    	        	 childCrto.setParentCommentId(cvo2.getParentCommentId());
    	        	 childCrto.setCommentCounts(cvo2.getCommentCounts());
    	        	 childCrto.setLikeCounts(cvo2.getLikeCounts());
    	        	 childCrto.setIsLiked(cvo2.isIsLiked());
    	        	 
    	        	 feedCommentsList.add(childCrto);
    	         }
        	 }
         }
         
         resp.setCommentsList(feedCommentsList);
         
         //--------------common---    
        resp.setStatus(SLMSRestConstants.status_success);
        resp.setStatusMessage(SLMSRestConstants.message_success); 
        }catch(Exception e){
            System.out.println("Exception # getFeedComments "+e.getMessage());
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
        CommonResponse finalResp = new CommonResponse();
        UserResponse resp = null;
        
        try {
            //verify if user name exists
            LoginServiceIface loginService = new LoginServiceImpl();
            UserLoginVo loginVo = loginService.getUserLoginDetail(userId);
            
            if(loginVo != null)
            {
            //verify credential (userName,Password)
                //UserVO userFromDb = loginService.getUser(req.getUserName(), req.getUserPassword());
                UserVO userFromDb = loginService.getUser(loginVo.getUserName(), loginVo.getUserPwd(),loginVo.getUserTypeId());
               if(userFromDb !=null)
               {
               //Authenticated true
            	   resp = new UserResponse();
                   resp.setUserId(String.valueOf(loginVo.getUserId()));
                   resp.setUserName(loginVo.getUserName());
                   resp.setUserType(String.valueOf(loginVo.getUserTypeId()));
            	   resp.setAddress(userFromDb.getAddress());
            	   
            	   if(loginVo.getUserTypeId() == 3)
                   resp.setProfileImage(PropertyManager.getProperty(SLMSRestConstants.baseUrl_userprofile)+userFromDb.getProfileImage());
            	   else
                   resp.setProfileImage(userFromDb.getProfileImage());
            		   
                   resp.setClassId(String.valueOf(userFromDb.getClassId()));
                   resp.setClassName(userFromDb.getClassName());
                   resp.setEmailId(userFromDb.getEmailId());
                   resp.setFirstName(userFromDb.getFirstName());
                   resp.setHomeRoomId(String.valueOf(userFromDb.getHomeRoomId()));
                   resp.setHomeRoomName(userFromDb.getHomeRoomName());
                   resp.setLastName(userFromDb.getLastName());
                   resp.setSchoolId(String.valueOf(userFromDb.getSchoolId()));
                   resp.setSchoolName(userFromDb.getSchoolName());
                   resp.setTitle(userFromDb.getTitle());
                   resp.setUserFbId(userFromDb.getUserFbId());
                   resp.setAdminEmailId(userFromDb.getAdminEmailId());
                   
                   finalResp.setUserDetail(resp);   
                   finalResp.setStatus(SLMSRestConstants.status_success);
                   finalResp.setStatusMessage(SLMSRestConstants.message_success);  
                                   
               }else{
               //Authentication failed
            	   finalResp.setStatus(SLMSRestConstants.status_wrongcredential);
            	   finalResp.setStatusMessage(SLMSRestConstants.message_wrongcredential);                   
               }

            }else{
            //userName not exist
            System.out.println("userId not exist.");
            finalResp.setStatus(SLMSRestConstants.status_userNotExist);
            finalResp.setStatusMessage(SLMSRestConstants.message_userNotExist);                
            }


        } catch (LmsServiceException ex) {
            System.out.println("LmsServiceException # login 22 "+ex.getMessage());
            finalResp.setStatus(SLMSRestConstants.status_failure);
            finalResp.setStatusMessage(SLMSRestConstants.message_failure);
            finalResp.setErrorMessage(ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Exception # login 22"+ex.getMessage());
            finalResp.setStatus(SLMSRestConstants.status_failure);
            finalResp.setStatusMessage(SLMSRestConstants.message_failure);
            finalResp.setErrorMessage(ex.getMessage());
        }
        

        return finalResp;
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
    public CommonResponse getSchoolMasterData(int teacherId) throws RestBusException {
        CommonResponse resp = new CommonResponse();
        CommonServiceIface service = new CommonServiceImpl();
        
        try{
        
        List<SchoolMasterVo> schoolListFromDB = service.getSchoolMasterVoList(teacherId);
        List<SchoolRespTO> schoolList = new ArrayList<SchoolRespTO>(schoolListFromDB.size());

        SchoolRespTO schoolRespTO = null;
        for(SchoolMasterVo vo : schoolListFromDB)
        {
	        schoolRespTO = new SchoolRespTO(String.valueOf(vo.getSchoolId()), vo.getSchoolName());
	        //Class master data
	        List<ClassMasterVo> clsMstrListDB = service.getClassVoList(vo.getSchoolId(),teacherId);
	        List<ClassRespTO> classList = new ArrayList<ClassRespTO>(clsMstrListDB.size());
	        ClassRespTO classRespTO = null;
	        for(ClassMasterVo clsvo : clsMstrListDB)
	        {
	        classRespTO = new ClassRespTO(String.valueOf(clsvo.getClassId()), clsvo.getClassName());
	        //Homeroom data
	         List<HomeRoomMasterVo> hrmListDB = service.getHomeRoomMasterVoList(clsvo.getClassId(),vo.getSchoolId(),teacherId);
	        List<HomeRoomRespTO> homeRoomList = new ArrayList<HomeRoomRespTO>(hrmListDB.size());
	        HomeRoomRespTO homeRoomRespTO = null;
	        for(HomeRoomMasterVo hrmvo  : hrmListDB)
	        {
	        homeRoomRespTO = new HomeRoomRespTO(String.valueOf(hrmvo.getHomeRoomMstrId()), hrmvo.getHomeRoomMstrName());
	        
	        List<CourseMasterVo> courseMstrListDB = service.getCourseVoList(hrmvo.getHomeRoomMstrId(),clsvo.getClassId(),vo.getSchoolId(),teacherId);
	        List<CourseRespTO> courseList = new ArrayList<CourseRespTO>(courseMstrListDB.size());
	        CourseRespTO courseRespTO = null;
	        for(CourseMasterVo courvo  : courseMstrListDB)
	        {
	        	courseRespTO= new CourseRespTO(String.valueOf(courvo.getCourseId()), courvo.getCourseName());
	        	List<ModuleMasterVo> moduleMstrListDB = service.getModuleVoList(courvo.getCourseId(),hrmvo.getHomeRoomMstrId(),clsvo.getClassId(),vo.getSchoolId(),teacherId);
	  	        List<ModuleRespTO> moduleList = new ArrayList<ModuleRespTO>(moduleMstrListDB.size());
	  	        ModuleRespTO moduleRespTO = null;
	  	        for(ModuleMasterVo modvo : moduleMstrListDB){
	  	        	moduleRespTO = new ModuleRespTO(String.valueOf(modvo.getModuleMasterId()) , modvo.getModuleMasterName());
	  	        	moduleList.add(moduleRespTO);
	  	        }
	        	
	  	        courseRespTO.setModuleList(moduleList);
	        	courseList.add(courseRespTO);
	        }
	        homeRoomRespTO.setCourseList(courseList);
	        homeRoomList.add(homeRoomRespTO);
	        
	       } 
	         
	        //hrm loop
	       
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
                service.saveFeedComment(req.getUserName(), req.getFeedId(), req.getCommentText());
           
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


	@Override
	public CommonResponse updateNotificationStatus(int userId, int feedId,
			String status) throws RestBusException {
	       CommonResponse resp = new CommonResponse();
	       
	       try{
	                CommonServiceIface service = new CommonServiceImpl();
	                long temp=service.updateNotificationStatus(userId, feedId, status);
	           
	                if(temp>0)
	                {
	                //setting success into response
	                resp.setStatus(SLMSRestConstants.status_success);
	                resp.setStatusMessage(SLMSRestConstants.message_success);                   
	                }else{
		                resp.setStatus(SLMSRestConstants.status_noUpdate);
		                resp.setStatusMessage(SLMSRestConstants.message_noUpdate);  
	                }
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

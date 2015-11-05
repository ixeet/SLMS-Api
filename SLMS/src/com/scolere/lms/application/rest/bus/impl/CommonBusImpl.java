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
import com.scolere.lms.application.rest.vo.response.SearchResponse;
import com.scolere.lms.application.rest.vo.response.UserResponse;
import com.scolere.lms.common.utils.PropertyManager;
import com.scolere.lms.domain.exception.LmsServiceException;
import com.scolere.lms.domain.vo.ClassMasterVo;
import com.scolere.lms.domain.vo.CourseMasterVo;
import com.scolere.lms.domain.vo.HomeRoomMasterVo;
import com.scolere.lms.domain.vo.ModuleMasterVo;
import com.scolere.lms.domain.vo.SchoolMasterVo;
import com.scolere.lms.domain.vo.UserLoginVo;
import com.scolere.lms.domain.vo.cross.AssignmentVO;
import com.scolere.lms.domain.vo.cross.CommentVO;
import com.scolere.lms.domain.vo.cross.FeedVO;
import com.scolere.lms.domain.vo.cross.ResourseVO;
import com.scolere.lms.domain.vo.cross.SearchVO;
import com.scolere.lms.domain.vo.cross.UserVO;
import com.scolere.lms.service.iface.CommonServiceIface;
import com.scolere.lms.service.iface.CourseServiceIface;
import com.scolere.lms.service.iface.LoginServiceIface;
import com.scolere.lms.service.impl.CommonServiceImpl;
import com.scolere.lms.service.impl.CourseServiceImpl;
import com.scolere.lms.service.impl.LoginServiceImpl;


/**
 *
 * @author dell
 */
public class CommonBusImpl implements CommonBusIface{
    

    /**
     * Global search (People/Course/Assignment/Updates)    
     * @param req [userId | searchText | offset | noOfRecords]
     * @return
     */
	@Override
	public SearchResponse search(CommonRequest req) throws RestBusException {
		
			SearchResponse resp = new SearchResponse();
	        CommonServiceIface service = new CommonServiceImpl();
	        
	        try{
	        
	        List<SearchResponse> searchList = new ArrayList<SearchResponse>(5);
	        	
	        //Search for People/Course/Assignment	
	        List<SearchVO> searchListFromDb = service.getSearchList(req.getUserId(), req.getSearchText(),req.getOffset(),req.getNoOfRecords());   
	    	 List<CourseRespTO> courseList = new ArrayList<CourseRespTO>(req.getNoOfRecords());
	    	 List<UserResponse> usersList = new ArrayList<UserResponse>(req.getNoOfRecords());
	    	 List<FeedRespTO> feedList = null;
	         List<AssignmentRespTO> assignmentList = new ArrayList<AssignmentRespTO>(req.getNoOfRecords());
	        	
	         CourseRespTO course=null;
	         UserResponse user=null;
	         AssignmentRespTO assignment=null;
	         for(SearchVO vo : searchListFromDb)
	         {
	        	 if(vo.getSearchCategory().equalsIgnoreCase(SLMSRestConstants.SEARCH_CAT_USER))
	        	 {
	        		 user = new UserResponse();
	        		 user.setUserId(String.valueOf(vo.getUserId()));
	        		 user.setUserName(vo.getUserName());
	        		 user.setProfileImage(PropertyManager.getProperty(SLMSRestConstants.baseUrl_userprofile)+vo.getProfileImage());
	        		 
	        		 usersList.add(user);
	        	 }
	        	 else if(vo.getSearchCategory().equalsIgnoreCase(SLMSRestConstants.SEARCH_CAT_COURSE))
	        	 {
	        		 course = new CourseRespTO();
	        		 course.setCourseId(vo.getCourseId());
	        		 course.setCourseName(vo.getCourseName());
	        		 course.setCourseDesc(vo.getCourseDesc());
	        		 
	        		 courseList.add(course);
	        	 }
	        	 else
	        	 {
	        		 assignment = new AssignmentRespTO();
	        		 assignment.setAssignmentId(vo.getAssignmentId()); 
	        		 assignment.setAssignmentName(vo.getAssignmentName());
	        		 assignment.setAssignmentDesc(vo.getAssignmentDesc());
	        		 assignment.setAssignmentSubmittedById(vo.getUserId());
	        		 assignment.setAssignmentSubmittedBy(vo.getUserName());
	        		 
	        		 assignmentList.add(assignment);
	        	 }
	        	 
	         }
	        	
	         //Search for feeds
	         CommonResponse feedResp=getNotificationsList(req);
	         feedList=feedResp.getFeedList();
	         
	         //Setting & Formatting response
	         SearchResponse userSearchResp=new SearchResponse();
	         userSearchResp.setCategory(SLMSRestConstants.SEARCH_CAT_USER);
	         userSearchResp.setUsersList(usersList);
	         if(usersList.size()<req.getNoOfRecords())
	         {
	        	 userSearchResp.setTotalUsersCount(""+usersList.size());
	         }else{
	        	 userSearchResp.setTotalUsersCount(""+service.getSearchRecordsCount(req.getUserId(), req.getSearchText(), SLMSRestConstants.SEARCH_CAT_USER));
	         }	         
	         searchList.add(userSearchResp);
	         
	         SearchResponse courseSearchResp=new SearchResponse();
	         courseSearchResp.setCategory(SLMSRestConstants.SEARCH_CAT_COURSE);
	         courseSearchResp.setCourseList(courseList);
	         if(courseList.size()<req.getNoOfRecords())
	         {
	        	 courseSearchResp.setTotalCoursesCount(""+courseList.size());
	         }else{
	        	 courseSearchResp.setTotalCoursesCount(""+service.getSearchRecordsCount(req.getUserId(), req.getSearchText(), SLMSRestConstants.SEARCH_CAT_COURSE));
	         }
	         searchList.add(courseSearchResp);	        	
	         
	         SearchResponse assignmentSearchResp=new SearchResponse();
	         assignmentSearchResp.setCategory(SLMSRestConstants.SEARCH_CAT_ASSIGNMENT);
	         assignmentSearchResp.setAssignmentList(assignmentList);
	         if(assignmentList.size()<req.getNoOfRecords())
	         {
	        	 assignmentSearchResp.setTotalAssignmentsCount(""+assignmentList.size());
	         }else{
	        	 assignmentSearchResp.setTotalAssignmentsCount(""+service.getSearchRecordsCount(req.getUserId(), req.getSearchText(), SLMSRestConstants.SEARCH_CAT_ASSIGNMENT));
	         }
	         searchList.add(assignmentSearchResp);	
	         
	         SearchResponse feedSearchResp=new SearchResponse();
	         feedSearchResp.setCategory(SLMSRestConstants.SEARCH_CAT_FEED);
	         feedSearchResp.setFeedList(feedList);
	         feedSearchResp.setTotalFeedsCount(""+feedResp.getTotalRecords());
	         searchList.add(feedSearchResp);	
	         
	         resp.setSearchList(searchList);
	        //--------------common---    
	        resp.setStatus(SLMSRestConstants.status_success);
	        resp.setStatusMessage(SLMSRestConstants.message_success); 
	        }catch(Exception e){
	            System.out.println("Exception # search "+e.getMessage());
	            resp.setStatus(SLMSRestConstants.status_failure);
	            resp.setStatusMessage(SLMSRestConstants.message_failure);
	            resp.setErrorMessage(e.getMessage());            
	        }
	        
	        return resp;
	  }


	
    /**
     * Search by category    
     * @param req [userId | searchText | offset | noOfRecords]
     * @return
     */
	@Override
	public SearchResponse search(CommonRequest req, String category)
			throws RestBusException {
		SearchResponse resp = new SearchResponse();
        CommonServiceIface service = new CommonServiceImpl();
        
        try{
        
        List<SearchResponse> searchList = new ArrayList<SearchResponse>(2);
        	
        if(category.equalsIgnoreCase(SLMSRestConstants.SEARCH_CAT_FEED))
        {
        List<FeedRespTO> feedList = null;
        //Search for feeds
        CommonResponse feedResp=getNotificationsList(req);
        feedList=feedResp.getFeedList();
        SearchResponse feedSearchResp=new SearchResponse();
        feedSearchResp.setCategory(SLMSRestConstants.SEARCH_CAT_FEED);
        feedSearchResp.setFeedList(feedList);
        feedSearchResp.setTotalFeedsCount(""+feedResp.getTotalRecords());
        searchList.add(feedSearchResp);	
        }else{
        //Search for People/Course/Assignment	
         List<SearchVO> searchListFromDb = service.getSearchList(req.getUserId(), req.getSearchText(),req.getOffset(),req.getNoOfRecords(),category);   
    	 List<CourseRespTO> courseList = new ArrayList<CourseRespTO>(req.getNoOfRecords());
    	 List<UserResponse> usersList = new ArrayList<UserResponse>(req.getNoOfRecords());
         List<AssignmentRespTO> assignmentList = new ArrayList<AssignmentRespTO>(req.getNoOfRecords());
        	
         CourseRespTO course=null;
         UserResponse user=null;
         AssignmentRespTO assignment=null;
         for(SearchVO vo : searchListFromDb)
         {
        	 if(vo.getSearchCategory().equalsIgnoreCase(SLMSRestConstants.SEARCH_CAT_USER))
        	 {
        		 user = new UserResponse();
        		 user.setUserId(String.valueOf(vo.getUserId()));
        		 user.setUserName(vo.getUserName());
        		 user.setProfileImage(PropertyManager.getProperty(SLMSRestConstants.baseUrl_userprofile)+vo.getProfileImage());
        		 
        		 usersList.add(user);
        	 }
        	 else if(vo.getSearchCategory().equalsIgnoreCase(SLMSRestConstants.SEARCH_CAT_COURSE))
        	 {
        		 course = new CourseRespTO();
        		 course.setCourseId(vo.getCourseId());
        		 course.setCourseName(vo.getCourseName());
        		 course.setCourseDesc(vo.getCourseDesc());
        		 
        		 courseList.add(course);
        	 }
        	 else
        	 {
        		 
        		 assignment = new AssignmentRespTO();
        		 assignment.setAssignmentId(vo.getAssignmentId()); 
        		 assignment.setAssignmentName(vo.getAssignmentName());
        		 assignment.setAssignmentDesc(vo.getAssignmentDesc());
        		 assignment.setAssignmentSubmittedById(vo.getUserId());
        		 assignment.setAssignmentSubmittedBy(vo.getUserName());
        		         		 
        		 assignmentList.add(assignment);
        	 }
        	 
         }
        	
         //Setting & Formatting response
         if(category.equalsIgnoreCase(SLMSRestConstants.SEARCH_CAT_USER))
         {
         SearchResponse userSearchResp=new SearchResponse();
         userSearchResp.setCategory(SLMSRestConstants.SEARCH_CAT_USER);
         userSearchResp.setUsersList(usersList);
         if(usersList.size()<req.getNoOfRecords())
         {
        	 userSearchResp.setTotalUsersCount(""+usersList.size());
         }else{
        	 userSearchResp.setTotalUsersCount(""+service.getSearchRecordsCount(req.getUserId(), req.getSearchText(), SLMSRestConstants.SEARCH_CAT_USER));
         }         
         searchList.add(userSearchResp);
         }else if(category.equalsIgnoreCase(SLMSRestConstants.SEARCH_CAT_COURSE))
         {
         SearchResponse courseSearchResp=new SearchResponse();
         courseSearchResp.setCategory(SLMSRestConstants.SEARCH_CAT_COURSE);
         courseSearchResp.setCourseList(courseList);
         if(courseList.size()<req.getNoOfRecords())
         {
        	 courseSearchResp.setTotalCoursesCount(""+courseList.size());
         }else{
        	 courseSearchResp.setTotalCoursesCount(""+service.getSearchRecordsCount(req.getUserId(), req.getSearchText(), SLMSRestConstants.SEARCH_CAT_COURSE));
         }         
         searchList.add(courseSearchResp);	        	
         }else if(category.equalsIgnoreCase(SLMSRestConstants.SEARCH_CAT_ASSIGNMENT))
         {
         SearchResponse assignmentSearchResp=new SearchResponse();
         assignmentSearchResp.setCategory(SLMSRestConstants.SEARCH_CAT_ASSIGNMENT);
         assignmentSearchResp.setAssignmentList(assignmentList);
         if(assignmentList.size()<req.getNoOfRecords())
         {
        	 assignmentSearchResp.setTotalAssignmentsCount(""+assignmentList.size());
         }else{
        	 assignmentSearchResp.setTotalAssignmentsCount(""+service.getSearchRecordsCount(req.getUserId(), req.getSearchText(), SLMSRestConstants.SEARCH_CAT_ASSIGNMENT));
         }         
         searchList.add(assignmentSearchResp);	
         }else{
        	System.out.println("INVALID SEARCH CATEGORY."); 
         }
        }
         resp.setSearchList(searchList);
        //--------------common---    
        resp.setStatus(SLMSRestConstants.status_success);
        resp.setStatusMessage(SLMSRestConstants.message_success); 
        }catch(Exception e){
            System.out.println("Exception # search "+e.getMessage());
            resp.setStatus(SLMSRestConstants.status_failure);
            resp.setStatusMessage(SLMSRestConstants.message_failure);
            resp.setErrorMessage(e.getMessage());            
        }
        
        return resp;
  }

     	
	
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
         resp.setTotalRecords(service.getTotalFeedsCount(req.getUserId(),req.getSearchText()));
         //set total unread notifications
         resp.setUnreadNotificationCount(service.getUnreadFeedCount(req.getUserId()));
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
            	   
            	   //if(loginVo.getUserTypeId() == 3)
            		
            	   if(!userFromDb.getProfileImage().startsWith("http:"))
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
        CommonResponse resp = new CommonResponse();
        CourseServiceIface service = new CourseServiceImpl();
         CommonServiceIface commonService = new CommonServiceImpl();

        try {
		     FeedVO feed = commonService.getFeedDetail(feedId);
		     AssignmentVO asignment = null;
            //Assignment start
		     if(feed!= null && feed.getUserId()>0 && feed.getAssignmentId()>0)
        	 asignment = service.getAssignmentDetail(feed.getUserId(), feed.getAssignmentId());
		     
            if(asignment != null)
            {
        	   AssignmentRespTO assign = new AssignmentRespTO();
            	assign.setAssignmentId(asignment.getAssignmentId());
            	assign.setAssignmentName(asignment.getAssignmentName());
            	assign.setAssignmentDesc(asignment.getAssignmentDesc());
            	assign.setAssignmentSubmittedDate(asignment.getAssignmentSubmittedDate());
            	assign.setAssignmentStatus(asignment.getAssignmentStatus());
            	assign.setAssignmentDueDate(asignment.getAssignmentDueDate());
            	assign.setCourseId(asignment.getCourseId());
            	assign.setCourseName(asignment.getCourseName());
            	assign.setModuleId(asignment.getModuleId());
            	assign.setModuleName(asignment.getModuleName());
            	assign.setAssignmentResourceTxnId(asignment.getAssignmentResourceTxnId());
            	
            	//Start - Assignment Resources
                List<ResourseVO> attachedResourcesFromDB=service.getAssignmentsResources(feed.getUserId(), assign.getAssignmentId());
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
                resourceRespTO.setAuthorName(vo5.getAuthorName());
                resourceRespTO.setAuthorImg(vo5.getAuthorImg());
                
                attachedResources.add(resourceRespTO);
                }
                
                assign.setAttachedResources(attachedResources);
            	//End - Assignment Resources

                //Assignment end
                resp.setAssignmentDetail(assign);   
                resp.setStatus(SLMSRestConstants.status_success);
                resp.setStatusMessage(SLMSRestConstants.message_success); 
             }else{
                 resp.setStatus(SLMSRestConstants.status_success);
                 resp.setStatusMessage(SLMSRestConstants.message_recordnotfound);                 	 
             }

    }catch(Exception e){
        System.out.println("Exception # getAssignmentDetail "+e.getMessage());
        resp.setStatus(SLMSRestConstants.status_failure);
        resp.setStatusMessage(SLMSRestConstants.message_failure);
        resp.setErrorMessage(e.getMessage());            
    }
        
    return resp;
    }
    
       
    public CommonResponse getAssignmentDetail_x(int feedId) throws RestBusException {
        CommonResponse resp = new CommonResponse();
        CommonServiceIface service = new CommonServiceImpl();
        
        try{
        	
        	AssignmentVO vo = service.getAssignmentDetail(feedId);
        	if(vo != null)
        	{
        		AssignmentRespTO assignmentDetail = new AssignmentRespTO();
        		assignmentDetail.setAssignmentId(vo.getAssignmentId());
        		assignmentDetail.setAssignmentName(vo.getAssignmentName());
        		assignmentDetail.setAssignmentDesc(vo.getAssignmentDesc());
        		assignmentDetail.setAssignmentSubmittedById(""+vo.getAssignmentSubmittedById());
        		assignmentDetail.setAssignmentSubmittedBy(vo.getAssignmentSubmittedBy());
        		assignmentDetail.setAssignmentStatus(vo.getAssignmentStatus());
        		assignmentDetail.setAssignmentDueDate(vo.getAssignmentDueDate());

           		//Assignment uploaded resource
        		if(vo.getAssignmentStatus() != null && !vo.getAssignmentStatus().equalsIgnoreCase("1")) //Assignment not uploaded/reviwed
        		{
             		List<ResourceRespTO> attachedResources = new ArrayList<ResourceRespTO>();
          	         ResourseVO defaultRes = service.getResourseDetail(feedId);
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
        	         attachedResources.add(rsto);
        	         }       
        	        assignmentDetail.setAttachedResources(attachedResources);
        		}
        		//Assignment upload resource
        		
        		resp.setAssignmentDetail(assignmentDetail);
		        resp.setStatus(SLMSRestConstants.status_success);
		        resp.setStatusMessage(SLMSRestConstants.message_success); 
        	}else{
                resp.setStatus(SLMSRestConstants.status_success);
                resp.setStatusMessage(SLMSRestConstants.message_recordnotfound); 
        	}
        
        }catch(Exception e){
            System.out.println("Exception # getAssignmentDetail "+e.getMessage());
            resp.setStatus(SLMSRestConstants.status_failure);
            resp.setStatusMessage(SLMSRestConstants.message_failure);
            resp.setErrorMessage(e.getMessage());            
        }
        
        return resp;
        
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

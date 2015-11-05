/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scolere.lms.application.rest.bus.impl;

import java.util.ArrayList;
import java.util.List;

import com.scolere.lms.application.rest.bus.iface.UserBusIface;
import com.scolere.lms.application.rest.constants.SLMSRestConstants;
import com.scolere.lms.application.rest.exceptions.RestBusException;
import com.scolere.lms.application.rest.vo.request.UserRequest;
import com.scolere.lms.application.rest.vo.response.UserResponse;
import com.scolere.lms.common.mail.Emailer;
import com.scolere.lms.common.utils.PropertyManager;
import com.scolere.lms.domain.exception.LmsServiceException;
import com.scolere.lms.domain.vo.StudentDetailVo;
import com.scolere.lms.domain.vo.UserClassMapVo;
import com.scolere.lms.domain.vo.UserLoginVo;
import com.scolere.lms.domain.vo.cross.UserVO;
import com.scolere.lms.service.iface.LoginServiceIface;
import com.scolere.lms.service.impl.LoginServiceImpl;

/**
 *
 * @author dell
 */
public class UserBusImpl implements UserBusIface{

    /**
     * Student registration > 
     * 1)Insert into user_login with enable flag 0 
     * 2)Student_dtls table 
     * 3)Email to admin
     * 4)Enable by admin after approval by means of web console.
     * 
     * @param UserRequest
     * @return UserResponse(userId | userName | Status | StatusMessage)
     * @throws RestBusException 
     */
    @Override
    public UserResponse registration(UserRequest req) throws RestBusException {

        UserResponse resp = new UserResponse();
        LoginServiceIface loginService = new LoginServiceImpl();

        try {
        	//Is admin email valid?
        	if(loginService.isAdminEmailValid(Integer.parseInt(req.getSchoolId()), req.getAdminEmailId()))
        	{
        	 System.out.println("Valid admin email > Go ahead with saving user registration data..");
            //1)Insert into user_login with enable flag 0 
            UserLoginVo loginVo = loginService.getUserLoginDetail(req.getUserName());
            
            if(loginVo == null)
            {
                //Save to user_login
                loginVo = new UserLoginVo();
                loginVo.setUserName(req.getUserName());
                loginVo.setUserFbId(null);
                loginVo.setDeletedFl("0");
                loginVo.setEnableFl("0");
                loginVo.setUserTypeId(3);//Student
                loginVo.setUserPwd(req.getUserPassword());
                
                loginVo = loginService.saveUserLoginDetail(loginVo); //saved
                
                if(loginVo != null)
                {
                //Save student details into Student_dtls table 
                StudentDetailVo studentVo=new StudentDetailVo();
                studentVo.setUserId(loginVo.getUserId());
                studentVo.setAddress(req.getAddress());
                studentVo.setBirthDt(null);
                studentVo.setContactNo("");
                studentVo.setEmailId(req.getEmailId());
                studentVo.setAdminEmailId(req.getAdminEmailId());
                studentVo.setJoiningDt(null);
                studentVo.setLastUserIdCd(req.getUserName());
                studentVo.setProfile(SLMSRestConstants.default_userprofile);
                studentVo.setSocialProfile(null);
                studentVo.setfName(req.getFirstName());
                studentVo.setlName(req.getLastName());
                studentVo.setTitle(req.getTitle());
                
                loginService.saveStudentDetail(studentVo);//saved Student_dtls
                
                //Save into user_cls_map
                int classId=Integer.parseInt(req.getClassId());
                int schoolId=Integer.parseInt(req.getSchoolId());
                int hrmId=Integer.parseInt(req.getHomeRoomId());
                
                UserClassMapVo userClassMapVo = new UserClassMapVo();
                userClassMapVo.setClassId(classId);
                userClassMapVo.setSchoolId(schoolId);
                userClassMapVo.setUserId(loginVo.getUserId());
                userClassMapVo.setHomeRoomMasterId(hrmId);
                System.out.println("Saving into user_cls_map ....");
                loginService.saveUserClassMapDetail(userClassMapVo);
                
                //Default assignments
                loginService.defaultUserAssignment(req.getUserName(), schoolId, classId, hrmId);
                //Default updates access type of user
                loginService.defaultFeedsAccessType(loginVo.getUserId(), hrmId); //Default access type is home group
                
                //Email to admin
                Emailer emailer = new Emailer();
                req.setUserid(String.valueOf(loginVo.getUserId()));
                emailer.mailto(SLMSRestConstants.email_from, req.getAdminEmailId(), "Registration approval", getUserRegistrationEmailContent(req));
                
                //setting success into response
                resp.setUserId(String.valueOf(loginVo.getUserId()));
                resp.setUserName(req.getUserName());
                resp.setStatus(SLMSRestConstants.status_success);
                resp.setStatusMessage(SLMSRestConstants.message_success);                
                }else{  
                //failed while saving user details    
                resp.setStatus(SLMSRestConstants.status_failure);
                resp.setStatusMessage(SLMSRestConstants.message_failure);
                }
                
            }else{
            //userName allready exists
            System.out.println("userName exist.");
            resp.setStatus(SLMSRestConstants.status_userExist);
            resp.setStatusMessage(SLMSRestConstants.message_userExist);                
            }
         }else{
        	 //Admin email is not valid
             System.out.println("invalid admin email.");
             resp.setStatus(SLMSRestConstants.status_invalid_adminemail);
             resp.setStatusMessage(SLMSRestConstants.message_invalid_adminemail);          	 
         }
        } catch (LmsServiceException ex) {
            System.out.println("LmsServiceException # registration "+ex.getMessage());
            resp.setStatus(SLMSRestConstants.status_failure);
            resp.setStatusMessage(SLMSRestConstants.message_failure);
            resp.setErrorMessage(ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Exception # registration "+ex.getMessage());
            resp.setStatus(SLMSRestConstants.status_failure);
            resp.setStatusMessage(SLMSRestConstants.message_failure);
            resp.setErrorMessage(ex.getMessage());
        }
        
       return resp;        
    }

    
    @Override
    public UserResponse updateProfile(UserRequest req) throws RestBusException {

        UserResponse resp = new UserResponse();
        
        try {
            LoginServiceIface loginService = new LoginServiceImpl();

            if(req.getUserid() != null && !req.getUserid().isEmpty())
            {
                //update student details into Student_dtls table 
                StudentDetailVo studentVo=new StudentDetailVo();
                studentVo.setUserId(Integer.parseInt(req.getUserid()));
                
                studentVo.setEmailId(req.getEmailId());
                studentVo.setLastUserIdCd(req.getUserName());
                studentVo.setfName(req.getFirstName());
                studentVo.setlName(req.getLastName());
                studentVo.setTitle(req.getTitle());
                studentVo.setProfile(req.getUserPassword());
                
                loginService.updateStudentDetail(studentVo);

                //setting success into response
                resp.setStatus(SLMSRestConstants.status_success);
                resp.setStatusMessage(SLMSRestConstants.message_success);  
            }else{
                resp.setStatus(SLMSRestConstants.status_badrequest);
                resp.setStatusMessage(SLMSRestConstants.message_badrequest);              
            }

        } catch (LmsServiceException ex) {
            System.out.println("LmsServiceException # updateProfile "+ex.getMessage());
            resp.setStatus(SLMSRestConstants.status_failure);
            resp.setStatusMessage(SLMSRestConstants.message_failure);
            resp.setErrorMessage(ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Exception # updateProfile "+ex.getMessage());
            resp.setStatus(SLMSRestConstants.status_failure);
            resp.setStatusMessage(SLMSRestConstants.message_failure);
            resp.setErrorMessage(ex.getMessage());
        }
        
       return resp;        
    }

    @Override
    public boolean updateProfilePhoto(String photoPath,String userName) throws RestBusException {

        boolean status = false;
        
        try {
            LoginServiceIface loginService = new LoginServiceImpl();

            if(photoPath != null && !photoPath.isEmpty())
            {
                status = loginService.updateProfilePhoto(photoPath,userName);
                
            }

        } catch (Exception ex) {
            System.out.println("Exception # updateProfilePhoto "+ex.getMessage());
            throw new RestBusException("Exception # updateProfilePhoto "+ex.getMessage());
        }
        
       return status;        
    }

    
    
    /**
     * Login > 
     * 1) verify if user name exists
     * 2) verify if credential is correct
     * 3) in case of success > insert into login sessions
     * @param UserRequest(userName | userPassword)
     * @return UserResponse(Status | StatusMessage | UserDetails..)
     * @throws RestBusException 
     */
    @Override
    public UserResponse login(UserRequest req) throws RestBusException {
        UserResponse resp = new UserResponse();
        
        try {
            //verify if user name exists
            LoginServiceIface loginService = new LoginServiceImpl();
            UserLoginVo loginVo = loginService.getUserLoginDetail(req.getUserName());
            
            if(loginVo != null)
            {
            	//access to teacher & student only
            if(loginVo.getUserTypeId()==2 || loginVo.getUserTypeId()==3){
            		System.out.println("Accessible to teacher or students only.");
            	
            	//verify credential (userName,Password)
               UserVO userFromDb = loginService.getUser(req.getUserName(), req.getUserPassword(),loginVo.getUserTypeId());
               if(userFromDb !=null)
               {
               //Authenticated true
               	//Verify if approved by admin or not
               	if(loginVo.getEnableFl().equalsIgnoreCase(SLMSRestConstants.FLAG_DISABLE))
               	{
                       resp.setStatus(SLMSRestConstants.status_user_need_approval);
                       resp.setStatusMessage(SLMSRestConstants.message_user_need_approval);                		
               	}
               	else
               	{
                   resp.setUserId(String.valueOf(loginVo.getUserId()));
                   resp.setUserName(loginVo.getUserName());
                   resp.setUserType(String.valueOf(loginVo.getUserTypeId()));
            	   
            	   resp.setAddress(userFromDb.getAddress());
                   resp.setProfileImage(PropertyManager.getProperty(SLMSRestConstants.baseUrl_userprofile)+userFromDb.getProfileImage());
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
                   
                //setting success into response
                resp.setStatus(SLMSRestConstants.status_success);
                resp.setStatusMessage(SLMSRestConstants.message_success);     
               	}
               }else{
               //Authentication failed
                resp.setStatus(SLMSRestConstants.status_wrongcredential);
                resp.setStatusMessage(SLMSRestConstants.message_wrongcredential);                   
               }
            }//access check
            else
            {
            	//Not access 
            	System.out.println("System not accessible.");
                resp.setStatus(SLMSRestConstants.status_not_access);
                resp.setStatusMessage(SLMSRestConstants.message_not_access);   
            }

            }else{
            //userName not exist
            System.out.println("userName not exist.");
            resp.setStatus(SLMSRestConstants.status_userNotExist);
            resp.setStatusMessage(SLMSRestConstants.message_userNotExist);                
            }

        } catch (LmsServiceException ex) {
            System.out.println("LmsServiceException # login "+ex.getMessage());
            resp.setStatus(SLMSRestConstants.status_failure);
            resp.setStatusMessage(SLMSRestConstants.message_failure);
            resp.setErrorMessage(ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Exception # login "+ex.getMessage());
            resp.setStatus(SLMSRestConstants.status_failure);
            resp.setStatusMessage(SLMSRestConstants.message_failure);
            resp.setErrorMessage(ex.getMessage());
        }
        
       return resp;        
    }

    
    /**
     * update USER_FB_ID into user_login.
     * 
     * @param userId
     * @param fbId
     * @return UserResponse (Status | StatusMessage)
     * @throws RestBusException 
     */
    @Override
    public UserResponse setFBId(String userName, String fbId) throws RestBusException {
        UserResponse resp = new UserResponse();
        
        try {
                LoginServiceIface loginService = new LoginServiceImpl();
                loginService.updateUserFBId(userName, fbId);
                
                //setting success into response
                resp.setStatus(SLMSRestConstants.status_success);
                resp.setStatusMessage(SLMSRestConstants.message_success);                   

        } catch (LmsServiceException ex) {
            System.out.println("LmsServiceException # setFBId "+ex.getMessage());
            resp.setStatus(SLMSRestConstants.status_failure);
            resp.setStatusMessage(SLMSRestConstants.message_failure);
            resp.setErrorMessage(ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Exception # setFBId "+ex.getMessage());
            resp.setStatus(SLMSRestConstants.status_failure);
            resp.setStatusMessage(SLMSRestConstants.message_failure);
            resp.setErrorMessage(ex.getMessage());
        }
       
        return resp;
    }
    
    /**
     * Return user details by facebook id
     * @param fbId
     * @return UserResponse (Status | StatusMessage | UserDetails..)
     * @throws RestBusException 
     */
    @Override
    public UserResponse getByFBId(String fbId) throws RestBusException {
        UserResponse resp = new UserResponse();
     
        try {
            //verify if user name exists
            LoginServiceIface loginService = new LoginServiceImpl();

            UserVO userFromDb = loginService.getUser(fbId);
               if(userFromDb !=null)
               {
                   resp.setUserId(String.valueOf(userFromDb.getUserId()));
                   resp.setUserName(userFromDb.getUserName());
                   resp.setAddress(userFromDb.getAddress());
                   resp.setProfileImage(PropertyManager.getProperty(SLMSRestConstants.baseUrl_userprofile)+userFromDb.getProfileImage());
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
                   
                //setting success into response
                resp.setStatus(SLMSRestConstants.status_success);
                resp.setStatusMessage(SLMSRestConstants.message_success);                   
               }else{
               //Authentication failed
                resp.setStatus(SLMSRestConstants.status_userNotExist);
                resp.setStatusMessage(SLMSRestConstants.message_userNotExist);                   
               }

        } catch (LmsServiceException ex) {
            System.out.println("LmsServiceException # getByFBId "+ex.getMessage());
            resp.setStatus(SLMSRestConstants.status_failure);
            resp.setStatusMessage(SLMSRestConstants.message_failure);
            resp.setErrorMessage(ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Exception # getByFBId "+ex.getMessage());
            resp.setStatus(SLMSRestConstants.status_failure);
            resp.setStatusMessage(SLMSRestConstants.message_failure);
            resp.setErrorMessage(ex.getMessage());
        }
        
        return resp;    
    }

    /**
     * Email user password on user's email.
     * @param userId
     * @return UserResponse (Status | Status message)
     * @throws RestBusException 
     */
    @Override
    public UserResponse forgetPwd(String userName) throws RestBusException {
        UserResponse resp = new UserResponse();
        
        try {
            //verify if user name exists
            LoginServiceIface loginService = new LoginServiceImpl();

            UserLoginVo userFromDb = loginService.getUserLoginDetail(userName);
               if(userFromDb !=null)
               {
                   //send password to the user email id
                   String pwd=userFromDb.getUserPwd();
                   String userEmailId=userName;
                   
                   //Email details
                   String subject="LMS forgot password notification.";
                   String message = "Dear user, <br/> Greetings! <br/> Your password is "+pwd;
                   
                   Emailer emailer = new Emailer();
                   emailer.mailto(SLMSRestConstants.email_from, userEmailId, subject, message);

                    
                //setting success into response
                resp.setStatus(SLMSRestConstants.status_success);
                resp.setStatusMessage(SLMSRestConstants.message_success);                   
               }else{
                resp.setStatus(SLMSRestConstants.status_userNotExist);
                resp.setStatusMessage(SLMSRestConstants.message_userNotExist);                 
               }

        } catch (LmsServiceException ex) {
            System.out.println("LmsServiceException # forgetPwd "+ex.getMessage());
            resp.setStatus(SLMSRestConstants.status_failure);
            resp.setStatusMessage(SLMSRestConstants.message_failure);
            resp.setErrorMessage(ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Exception # forgetPwd "+ex.getMessage());
            resp.setStatus(SLMSRestConstants.status_failure);
            resp.setStatusMessage(SLMSRestConstants.message_failure);
            resp.setErrorMessage(ex.getMessage());
        }

        return resp;
    }

    /**
     * Change user password.
     * @param UserRequest (userName | userPassword | userNewPassword)
     * @return UserResponse (Status | Status message)
     * @throws RestBusException 
     */
    @Override
    public UserResponse changePwd(UserRequest req) throws RestBusException {
        UserResponse resp = new UserResponse();
        try {
            //verify if user name exists
            LoginServiceIface loginService = new LoginServiceImpl();

               UserVO userFromDb = loginService.getUser(req.getUserName(), req.getUserPassword());
               if(userFromDb !=null)
               {
               //Authenticated true
                 //update password
                 loginService.updateUserPwd(req.getUserName(), req.getUserPassword(), req.getUserNewPassword());  
                   
                //setting success into response
                resp.setStatus(SLMSRestConstants.status_success);
                resp.setStatusMessage(SLMSRestConstants.message_success);                   
               }else{
               //Authentication failed
                resp.setStatus(SLMSRestConstants.status_wrongcredential);
                resp.setStatusMessage(SLMSRestConstants.message_wrongcredential);                   
               }


        } catch (LmsServiceException ex) {
            System.out.println("LmsServiceException # registration "+ex.getMessage());
            resp.setStatus(SLMSRestConstants.status_failure);
            resp.setStatusMessage(SLMSRestConstants.message_failure);
            resp.setErrorMessage(ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Exception # registration "+ex.getMessage());
            resp.setStatus(SLMSRestConstants.status_failure);
            resp.setStatusMessage(SLMSRestConstants.message_failure);
            resp.setErrorMessage(ex.getMessage());
        }
        
        return resp;
    }


	@Override
	public UserResponse getFeedAccessType(int userId) throws RestBusException {
        UserResponse resp = new UserResponse();
        
        try {
            
            LoginServiceIface loginService = new LoginServiceImpl();
            //Setting userStatus
            resp.setUserId(String.valueOf(userId));
            resp.setUserAccessTypeId(loginService.getFeedAccessType(userId));
            
            /*
            //Setting master data
            List<CommonKeyValueVO> mstrListFromDb=loginService.getAccessTypeMasterData();
            List<KeyValTypeVO> listMstrData=new ArrayList<KeyValTypeVO>(mstrListFromDb.size());
            KeyValTypeVO vo=null;
            for(CommonKeyValueVO temp:mstrListFromDb)        
            {
            	vo = new KeyValTypeVO(temp.getItemCode(), temp.getItemName(), "");
            	listMstrData.add(vo);
            }
            resp.setAccessTypeList(listMstrData);
            */
            
            //setting success into response
            resp.setStatus(SLMSRestConstants.status_success);
            resp.setStatusMessage(SLMSRestConstants.message_success);                   

        } catch (LmsServiceException ex) {
            System.out.println("LmsServiceException # getFeedAccessType "+ex.getMessage());
            resp.setStatus(SLMSRestConstants.status_failure);
            resp.setStatusMessage(SLMSRestConstants.message_failure);
            resp.setErrorMessage(ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Exception # getFeedAccessType "+ex.getMessage());
            resp.setStatus(SLMSRestConstants.status_failure);
            resp.setStatusMessage(SLMSRestConstants.message_failure);
            resp.setErrorMessage(ex.getMessage());
        }

        return resp;
    }


	@Override
	public UserResponse setFeedAccessType(int userId, int accesTypeId)
			throws RestBusException {
        UserResponse resp = new UserResponse();
        
        try {
            
            LoginServiceIface loginService = new LoginServiceImpl();
            //Setting userStatus
            loginService.setFeedAccessType(userId, accesTypeId);
            
            //setting success into response
            resp.setStatus(SLMSRestConstants.status_success);
            resp.setStatusMessage(SLMSRestConstants.message_success);                   

        } catch (LmsServiceException ex) {
            System.out.println("LmsServiceException # setFeedAccessType "+ex.getMessage());
            resp.setStatus(SLMSRestConstants.status_failure);
            resp.setStatusMessage(SLMSRestConstants.message_failure);
            resp.setErrorMessage(ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Exception # setFeedAccessType "+ex.getMessage());
            resp.setStatus(SLMSRestConstants.status_failure);
            resp.setStatusMessage(SLMSRestConstants.message_failure);
            resp.setErrorMessage(ex.getMessage());
        }

        return resp;
    }

	@Override
	public UserResponse getFeedUsers(int userId) throws RestBusException {
        UserResponse resp = new UserResponse();
        
        try {
            
            LoginServiceIface loginService = new LoginServiceImpl();
            //Setting userStatus
            List<UserVO> userListFromDb=loginService.getFeedUsers(userId);
            List<UserResponse> usersList = new ArrayList<UserResponse>(userListFromDb.size());
            UserResponse temp=null;
            for(UserVO vo:userListFromDb)
            {
            	temp = new UserResponse();
            	temp.setUserId(String.valueOf(vo.getUserId()));
            	temp.setUserName(vo.getUserName());
            	temp.setProfileImage(PropertyManager.getProperty(SLMSRestConstants.baseUrl_userprofile)+vo.getProfileImage());            	
            	temp.setIsFollowUpAllowed(vo.getIsFollowUpAllowed());
            	usersList.add(temp);
            }
            
            resp.setUsersList(usersList);
            
            //setting success into response
            resp.setStatus(SLMSRestConstants.status_success);
            resp.setStatusMessage(SLMSRestConstants.message_success);                   

        } catch (LmsServiceException ex) {
            System.out.println("LmsServiceException # getFeedUsers "+ex.getMessage());
            resp.setStatus(SLMSRestConstants.status_failure);
            resp.setStatusMessage(SLMSRestConstants.message_failure);
            resp.setErrorMessage(ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Exception # getFeedUsers "+ex.getMessage());
            resp.setStatus(SLMSRestConstants.status_failure);
            resp.setStatusMessage(SLMSRestConstants.message_failure);
            resp.setErrorMessage(ex.getMessage());
        }

        return resp;
    }

	@Override
	public UserResponse updateFollowersStatus(UserRequest req)
			throws RestBusException {
	       UserResponse resp = new UserResponse();
	        
	        try {
	            
	            LoginServiceIface loginService = new LoginServiceImpl();
	            //Setting userStatus
	            
	            List<UserVO> usersList = new ArrayList<UserVO>();
	            UserVO vo=null;
	            for(UserRequest usr:req.getUsersList())
	            {

	            	vo=new UserVO();
	            	vo.setUserId(Integer.parseInt(usr.getUserid()));
	            	vo.setIsFollowUpAllowed(usr.getIsFollowUpAllowed());
	            	usersList.add(vo);

	            }
	            
	            loginService.updateFollowersStatus(Integer.parseInt(req.getUserid()), usersList);
	            
	            //setting success into response
	            resp.setStatus(SLMSRestConstants.status_success);
	            resp.setStatusMessage(SLMSRestConstants.message_success);                   

	        } catch (LmsServiceException ex) {
	            System.out.println("LmsServiceException # updateFollowersStatus "+ex.getMessage());
	            resp.setStatus(SLMSRestConstants.status_failure);
	            resp.setStatusMessage(SLMSRestConstants.message_failure);
	            resp.setErrorMessage(ex.getMessage());
	        } catch (Exception ex) {
	            System.out.println("Exception # updateFollowersStatus "+ex.getMessage());
	            resp.setStatus(SLMSRestConstants.status_failure);
	            resp.setStatusMessage(SLMSRestConstants.message_failure);
	            resp.setErrorMessage(ex.getMessage());
	        }

	        return resp;
	    }


	
	private String getUserRegistrationEmailContent(UserRequest vo)
	{
		String message="";
		LoginServiceIface loginService = new LoginServiceImpl();
		try{
		UserVO user=loginService.getUserOrgDetail(vo.getUserid());
		
		StringBuffer temp=new StringBuffer("<font face=\"calibri\">");
		temp.append("Hello ").append(user.getSchoolName()).append(" Admin user,").append("<br/><br/>");
		temp.append("<b>").append(vo.getFirstName()).append(" ").append(vo.getLastName()).append("</b> has requested to access Growth Cafe.").append("<br/><br/>");
		temp.append("<b>Registration Details:</b><br/>");
		temp.append("Student's Name : ").append(vo.getTitle()).append(" ").append(vo.getFirstName()).append(" ").append(vo.getLastName()).append("<br/>");
		temp.append("Email id : ").append(vo.getEmailId()).append("<br/>");
		temp.append("Department : ").append(user.getClassName()).append("<br/>");
		temp.append("Group : ").append(user.getHomeRoomName());
		temp.append("<br/><br/>You can activate this user from admin section under user management.<br/><br/>");
		temp.append("Thanks<br/>Growth Cafe");
		temp.append("</font>");
		message=temp.toString();
		}catch(Exception e){
			System.out.println("Exception # getUserRegistrationEmailContent - "+e.getMessage());
		}
		
		return message;
	}


	
    
}//End of class

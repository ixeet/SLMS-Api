/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scolere.lms.application.rest.bus.impl;

import com.scolere.lms.application.rest.bus.iface.UserBusIface;
import com.scolere.lms.application.rest.constants.SLMSRestConstants;
import com.scolere.lms.application.rest.exceptions.RestBusException;
import com.scolere.lms.application.rest.vo.request.UserRequest;
import com.scolere.lms.application.rest.vo.response.UserResponse;
import com.scolere.lms.common.mail.Emailer;
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
        
        try {
            //1)Insert into user_login with enable flag 0 
            LoginServiceIface loginService = new LoginServiceImpl();
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
                studentVo.setProfile(null);
                studentVo.setSocialProfile(null);
                studentVo.setfName(req.getFirstName());
                studentVo.setlName(req.getLastName());
                studentVo.setTitle(req.getTitle());
                
                loginService.saveStudentDetail(studentVo);//saved Student_dtls
                
                //Save into user_cls_map
                UserClassMapVo userClassMapVo = new UserClassMapVo();
                userClassMapVo.setClassId(Integer.parseInt(req.getClassId()));
                userClassMapVo.setSchoolId(Integer.parseInt(req.getSchoolId()));
                userClassMapVo.setUserId(loginVo.getUserId());
                userClassMapVo.setHomeRoomMasterId(Integer.parseInt(req.getHomeRoomId()));
                System.out.println("Saving into user_cls_map ....");
                loginService.saveUserClassMapDetail(userClassMapVo);
                
                //Email to admin
                Emailer emailer = new Emailer();
                emailer.mailto(SLMSRestConstants.email_from, req.getAdminEmailId(), "Registration approval", studentVo.toString());
                
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
    public boolean updateProfilePhoto(String photoPath) throws RestBusException {

        boolean status = false;
        
        try {
            LoginServiceIface loginService = new LoginServiceImpl();

            if(photoPath != null && !photoPath.isEmpty())
            {
                status = loginService.updateProfilePhoto(photoPath);
                
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
            //verify credential (userName,Password)
               UserVO userFromDb = loginService.getUser(req.getUserName(), req.getUserPassword());
               if(userFromDb !=null)
               {
               //Authenticated true
                 //Setting user details into resp
                   
                   resp.setUserId(String.valueOf(loginVo.getUserId()));
                   resp.setUserName(loginVo.getUserName());
                   resp.setAddress(userFromDb.getAddress());
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
                resp.setStatus(SLMSRestConstants.status_wrongcredential);
                resp.setStatusMessage(SLMSRestConstants.message_wrongcredential);                   
               }

            }else{
            //userName not exist
            System.out.println("userName not exist.");
            resp.setStatus(SLMSRestConstants.status_userNotExist);
            resp.setStatusMessage(SLMSRestConstants.message_userNotExist);                
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

    
    /**
     * update USER_FB_ID into user_login.
     * 
     * @param userId
     * @param fbId
     * @return UserResponse (Status | StatusMessage)
     * @throws RestBusException 
     */
    @Override
    public UserResponse setFBId(String userId, String fbId) throws RestBusException {
        UserResponse resp = new UserResponse();
        
        try {
                LoginServiceIface loginService = new LoginServiceImpl();
                loginService.updateUserFBId(userId, fbId);
                
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
    
    
}//End of class

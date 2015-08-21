/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scolere.lms.application.rest.controller;

import com.scolere.lms.application.rest.bus.iface.UserBusIface;
import com.scolere.lms.application.rest.bus.impl.UserBusImpl;
import com.scolere.lms.application.rest.constants.SLMSRestConstants;
import com.scolere.lms.application.rest.exceptions.RestBusException;
import com.scolere.lms.application.rest.vo.request.UserRequest;
import com.scolere.lms.application.rest.vo.response.UserResponse;
import com.scolere.lms.common.utils.AppUtils;
import com.scolere.lms.common.utils.StringEncrypter;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author dell
 */
@Path("/user")
public class UserController {

    UserBusIface restService = new UserBusImpl();
    /**
     * Default controller method
     * @return 
     */
    @GET
    public String defaultx() {
        String message = "Welcome to SLMS user webservices....";
        return message;
    }
    

    /**
     * User registration service.
     * 
     * @param UserRequest
     * @param HttpServletRequest
     * @return UserResponse
     */
    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)    
    @Produces(MediaType.APPLICATION_JSON)      
    public UserResponse registration(UserRequest req,@Context HttpServletRequest request) {
        System.out.println("Start registration >>"+req);
        UserResponse userResponse = null;
        
        try {
            userResponse = restService.registration(req);
        } catch (RestBusException ex) {
            System.out.println("Exception # registration - "+ex);
        }
        
        System.out.println("<< End registration "+userResponse); 
        
        return userResponse;
    }

    /**
     * Update Profile service.
     * 
     * @param UserRequest
     * @param HttpServletRequest
     * @return UserResponse
     */
    @POST
    @Path("/updateProfile")
    @Consumes(MediaType.APPLICATION_JSON)    
    @Produces(MediaType.APPLICATION_JSON)      
    public UserResponse updateProfile(UserRequest req,@Context HttpServletRequest request) {
        System.out.println("Start updateProfile >>"+req);
        UserResponse userResponse = null;
        
        try {
            userResponse = restService.updateProfile(req);
        } catch (RestBusException ex) {
            System.out.println("Exception # updateProfile - "+ex);
        }
        
        System.out.println("<< End updateProfile "+userResponse); 
        
        return userResponse;
    }
    
    
    /**
     * Upload profile image
     * @param vin
     * @param customerId
     * @param uploadedInputStream
     * @param fileDetail
     * @return 
     */
    @POST
    @Path("/uploadProfileImage")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)          
    public UserResponse uploadProfileImage(
            @FormDataParam("userName") String userName,
            @FormDataParam("profilePhoto") InputStream uploadedInputStream,
            @FormDataParam("profilePhoto") FormDataContentDisposition fileDetail) {

        System.out.println("Start uploadProfileImage >>"+userName);
        UserResponse resp = new UserResponse();
        
        try {
        //Update PROFILE_IMG to database
        String profileImgName = userName+SLMSRestConstants.image_extension;
        boolean updateStatus =  restService.updateProfilePhoto(profileImgName);
        System.out.println("Image name saved into db ? "+updateStatus);
        
        String uploadedFileLocation = SLMSRestConstants.location_userprofile+profileImgName;
        System.out.println("Uploading file @: "+uploadedFileLocation);
        AppUtils.writeToFile(uploadedInputStream, uploadedFileLocation);
        System.out.println("Profile image successfully uploaded..");
        
        resp.setUploadLocation(uploadedFileLocation);
        
        resp.setStatus(SLMSRestConstants.status_success);
        resp.setStatusMessage(SLMSRestConstants.message_success);          
        } catch (Exception ex) {
            System.out.println("Exception # uploadProfileImage - "+ex);
            resp.setStatus(SLMSRestConstants.status_failure);
            resp.setStatusMessage(SLMSRestConstants.message_failure);
            resp.setErrorMessage(ex.getMessage());            
        }
        System.out.println("<< End uploadProfileImage "+resp); 
        
        return resp;        
    }

    
    /**
     * 
     * User login service.
     * 
     * @param UserRequest
     * @param HttpServletRequest
     * @return UserResponse
     * 
     */
    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)    
    @Produces(MediaType.APPLICATION_JSON)      
    public UserResponse login(UserRequest req,@Context HttpServletRequest request) {
        System.out.println("Start login >>"+req);
        UserResponse userResponse = null;
        
        try {
            userResponse = restService.login(req);
        } catch (RestBusException ex) {
            System.out.println("Exception # login - "+ex);
        }
        
        System.out.println("<< End login # "+userResponse); 
        
        return userResponse;
    }    
    
    /**
     * Update facebook id into database. 
     * @param User Id
     * @param Facebook Id
     * @return UserResponse
     */
    @GET
    @Path("/setFBId/userName/{userName}/userFbId/{fbId}")
    @Produces(MediaType.APPLICATION_JSON)
    public UserResponse setFBId(@PathParam("userName") String userId,@PathParam("fbId") String fbId) {
        System.out.println("Start setFBId >> userId="+userId+" | fbid="+fbId);
        UserResponse userResponse = null;
        
        try {
            userResponse = restService.setFBId(userId, fbId);
        } catch (RestBusException ex) {
            System.out.println("Exception # setFBId - "+ex);
        }
        
        System.out.println("<< End setFBId # "+userResponse); 
        
        return userResponse;
    }    
    
    
    /**
     * Get user details by Facebook id
     * @param fbId
     * @return 
     */
    @GET
    @Path("/getByFBId/userFbId/{fbId}")
    @Produces(MediaType.APPLICATION_JSON)
    public UserResponse getByFBId(@PathParam("fbId") String fbId) {
        System.out.println("Start getByFBId >> fbid = "+fbId);
        UserResponse userResponse = null;
        
        try {
            userResponse = restService.getByFBId(fbId);
        } catch (RestBusException ex) {
            System.out.println("Exception # getByFBId - "+ex);
        }
        
        System.out.println("<< End getByFBId # "+userResponse); 
        
        return userResponse;
    }    
        
    /**
     * Email user password to registered email id.
     * @param userId
     * @return UserResponse
     */
    @GET
    @Path("/forgetPwd/userId/{userId}")
    @Produces(MediaType.APPLICATION_JSON)      
    public UserResponse forgetPwd(@PathParam("userId") String userId) {
        System.out.println("Start forgetPwd >> userId = "+userId);
        UserResponse userResponse = null;
        
        try {
            userResponse = restService.forgetPwd(userId);
        } catch (RestBusException ex) {
            System.out.println("Exception # forgetPwd - "+ex);
        }
        
        System.out.println("<< End forgetPwd # "+userResponse); 
        return userResponse;
    }
    
        
    /**
     * Change user password.
     * @param UserRequest
     * @param HttpServletRequest
     * @return UserResponse
     */
    @POST
    @Path("/changePwd")
    @Consumes(MediaType.APPLICATION_JSON)    
    @Produces(MediaType.APPLICATION_JSON)      
    public UserResponse changePwd(UserRequest req,@Context HttpServletRequest request) {
        System.out.println("Start changePwd >> "+req);
        UserResponse userResponse = null;
        
        try {
            userResponse = restService.changePwd(req);
        } catch (RestBusException ex) {
            System.out.println("Exception # changePwd - "+ex);
        }
        
        System.out.println("<< End changePwd # "+userResponse); 
        
        return userResponse;
    }      
    
    
    @GET
    @Path("/test")
    @Produces(MediaType.APPLICATION_JSON)     
    public UserRequest testUserReqVo()
    {
    
        UserRequest userDetail = new UserRequest();
        //UserTO userDetail=new UserTO();
        userDetail.setUserName("mayankd@ixeet.com");
        String pwd=new StringEncrypter().encrypt("ixeet@123");
        
        System.out.println("pwd = "+new StringEncrypter().decrypt(pwd));
        //userDetail.setUserPassword(pwd);
        
        userDetail.setFirstName("Mayank");
        userDetail.setLastName("Dixit");
        userDetail.setEmailId("mayankd@ixeet.com");
        userDetail.setSchoolId("s01");
        userDetail.setSchoolName("Sample School");
        userDetail.setHomeRoomId("hrm01");
        userDetail.setHomeRoomName("Sample HRM");
        userDetail.setClassId("cls01");
        userDetail.setClassName("Sample Class");
        userDetail.setAddress("Testtttt Address");
        userDetail.setAdminEmailId("admin@ixeet.com");
        userDetail.setTitle("Mr.");
        
        return userDetail;
    }
    
    
}//End of class

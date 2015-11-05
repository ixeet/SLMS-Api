/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scolere.lms.application.rest.constants;

/**
 * 
 * @author dell
 */
public class SLMSRestConstants {
    
    public static final String FLAG_DISABLE="0";
    public static final String FLAG_ENABLE="1";

    //Response Status & Message
    public static final int status_failure=1000;
    public static final String message_failure="failure";
    public static final int status_success=1001;
    public static final String message_success="success";
    public static final int status_noUpdate=1002;
    public static final String message_noUpdate="No record updated.";
    public static final int status_userExist=1006;
    public static final String message_userExist="Email id already exists, Please try with another email or retrieve password";
    public static final int status_userNotExist=1007;
    public static final String message_userNotExist="User does not exist.";
    public static final int status_wrongcredential=1008;
    public static final String message_wrongcredential="Userid or Password incorrect";
    public static final int status_badrequest=1009;
    public static final String message_badrequest="Bad request";
    public static final int status_fieldRequired=1010;
    public static final String message_fieldRequired="Incompatible input.";
    public static final String message_recordnotfound="No record found.";

    public static final int status_user_need_approval=1011;
    public static final String message_user_need_approval="Your registration request is in approval process.You will get email once approval completed.";
    public static final int status_invalid_adminemail=1012;
    public static final String message_invalid_adminemail="Organization contact email is not correct, please try with a correct email id or contact your Organization admin.";
    public static final int status_not_access=1013;
    public static final String message_not_access="Access denied.";
    
    //Error messages
    public static final String message_pushTitleRequired="Push title is required.";
    public static final String message_pushMessageRequired="Push message is required.";
    public static final String message_deviceTokenRequired="Device Token is required.";
    public static final String message_deviceRegistered="Device is already registered on server."; 
    public static final String message_userNameRequired="User Name is required.";
    public static final String message_firstNameRequired="First Name is required.";
    public static final String message_lastNameRequired="Last Name is required.";
    public static final String message_emailIdRequired="Email Id is required.";
    public static final String message_adminEmailIdRequired="Admin Email Id is required.";
    public static final String message_schoolIdRequired="School Id is required.";
    public static final String message_classIdRequired="Class Id is required.";
    public static final String message_homeRoomIdRequired="HomeRoom Id is required.";
    public static final String message_userPasswordRequired="User Password is required.";
    public static final String message_addressRequired="Address is required.";
    public static final String message_titleRequired="Title is required.";
    
    public static final String message_userNewPasswordRequired="User New Password Id is required.";
    public static final String message_fileDetailRequired="Rsource file or Url or both are required.";
    
    public static final String message_resourceIdRequired="Resource Id is required.";
    public static final String message_idRequired="Id is required.";
    public static final String message_commentIdRequired="Comment Id is required.";
    public static final String message_feedIdRequired="Feed Id is required.";
    public static final String message_noOfRecordsRequired="noOfRecords should be > 0.";

    public static final String message_assignmentIdRequired="Assignment Id is required.";
    public static final String message_resourceNameRequired="Resource name is required.";
    public static final String message_resourceAuthorRequired="Resource Author is required.";
    public static final String message_desc_textRequired="Description is required.";

    public static final String message_userIdRequired="User Id is required.";
    public static final String message_facebookIdRequired="Facebook Id is required.";
    public static final String message_moduelStatus_zero="Please start any associated resource,this module will started itself.";
    public static final String message_courseStatus_all="Please complete all the modules first,this course will complted itself.";
    public static final String message_invalidStatus="Invalid status";
    

    //Feed Template & Placeholders
    public static final String FEED_TMPLT_PLACEHOLDER="$";
    public static final String FEED_REF_COURSE_TXN="COURSE_TXN";
    public static final String FEED_REF_ASGNMT_TXN="ASSIGNMENT_TXN";
    //user,assignment,module,resource,course
    public static final String FEED_TMPLT_PARAM_SEPARATOR=",";
    public static final String FEED_TMPLT_PARAM_COURSE="course";
    public static final String FEED_TMPLT_PARAM_ASSIGNMENT="assignment";
    public static final String FEED_TMPLT_PARAM_USER="user";
    public static final String FEED_TMPLT_PARAM_RESOURSE="resource";
    public static final String FEED_TMPLT_PARAM_MODULE="module";
    public static final String FEED_TMPLT_PARAM_HOMEROOM="homeroom";
    
    public static final String email_from="system@ixeet.com";
    
    //Pagination
    public static final int pagination_offset=0;
    public static final int pagination_records=10;
    
    //Uploaded files locations
    public static final String image_extension=".png";
    public static final String default_userprofile="default-profile.jpg";

    //Device types
    public static final String DEVICE_TYPE_IPHONE="iPhone";
    public static final String DEVICE_TYPE_ANDROID="Android";
    
    //Search Constants
    public static final String SEARCH_CAT_USER="People";
    public static final String SEARCH_CAT_COURSE="Course";
    public static final String SEARCH_CAT_FEED="Update";
    public static final String SEARCH_CAT_ASSIGNMENT="Assignment";
    
    
    
///////////////////////////////////////////////////    
////////////////Property file items////////////////
//////////////////////////////////////////////////    
    
//    public static final String location_userprofile="C:\\slms\\static\\resources\\profile\\";
//    public static final String location_assignments="C:\\slms\\static\\resources\\video\\";
    public static final String location_userprofile="DIR.SLMS.RESOURCE.PROFILE";
    public static final String location_assignments="DIR.SLMS.RESOURCE.CONTENT";
    
    //Uploaded location base URLs
//    public static final String baseUrl_userprofile="http://191.239.57.54:8080/resources/profile/";
//    public static final String baseUrl_resources="http://191.239.57.54:8080/resources/video/";
    public static final String baseUrl_userprofile="URL.SLMS.RESOURCE.PROFILE";
    public static final String baseUrl_resources="URL.SLMS.RESOURCE.CONTENT";

    
    
    
}

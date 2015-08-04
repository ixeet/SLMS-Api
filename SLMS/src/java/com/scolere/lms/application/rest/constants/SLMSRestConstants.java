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
    public static final int status_failure=1000;
    public static final String message_failure="failure";
    public static final int status_success=1001;
    public static final String message_success="success";
    public static final int status_userExist=1006;
    public static final String message_userExist="User name not available";
    public static final int status_userNotExist=1007;
    public static final String message_userNotExist="User does not exist.";
    public static final int status_wrongcredential=1008;
    public static final String message_wrongcredential="User name or password is not correct.";
    public static final int status_badrequest=1009;
    public static final String message_badrequest="Bad request";

    public static final String email_from="mymazda@interrait.com";
    
    //Uploaded files locations
    public static final String image_extension=".png";
    public static final String location_userprofile="C:\\SLMS\\user-profile\\";
    
    
    
}

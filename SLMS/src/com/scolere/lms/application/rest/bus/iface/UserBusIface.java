/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scolere.lms.application.rest.bus.iface;

import com.scolere.lms.application.rest.exceptions.RestBusException;
import com.scolere.lms.application.rest.vo.request.UserRequest;
import com.scolere.lms.application.rest.vo.response.UserResponse;

/**
 *
 * @author dell
 */
public interface UserBusIface {
    
    UserResponse registration(UserRequest req) throws RestBusException;
    UserResponse updateProfile(UserRequest req) throws RestBusException;
    boolean updateProfilePhoto(String photoName,String userName) throws RestBusException;
    UserResponse login(UserRequest req) throws RestBusException;
    UserResponse setFBId(String userId,String fbId) throws RestBusException;
    UserResponse getByFBId(String fbId) throws RestBusException;
    UserResponse forgetPwd(String userId) throws RestBusException;
    UserResponse changePwd(UserRequest req) throws RestBusException;
    //UserResponse loginApproval(int userId) throws RestBusException;
    
    //Feed users 
    UserResponse getFeedAccessType(int userId) throws RestBusException;
    UserResponse setFeedAccessType(int userId,int accesTypeId) throws RestBusException;
    UserResponse getFeedUsers(int userId) throws RestBusException;
    UserResponse updateFollowersStatus(UserRequest req) throws RestBusException;
    

}

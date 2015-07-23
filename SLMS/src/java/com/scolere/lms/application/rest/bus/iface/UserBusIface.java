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
    UserResponse login(UserRequest req) throws RestBusException;
    UserResponse setFBId(String userId,String fbId) throws RestBusException;
    UserResponse getByFBId(String fbId) throws RestBusException;
    UserResponse forgetPwd(String userId) throws RestBusException;
    UserResponse changePwd(UserRequest req) throws RestBusException;
    
    
}

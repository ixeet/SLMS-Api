/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scolere.lms.application.rest.bus.iface;

import com.scolere.lms.application.rest.exceptions.RestBusException;
import com.scolere.lms.application.rest.vo.request.CommonRequest;
import com.scolere.lms.application.rest.vo.response.CommonResponse;

/**
 *
 * @author dell
 */
public interface CommonBusIface {
    CommonResponse getSchoolMasterData() throws RestBusException;
    CommonResponse getFeedsList(CommonRequest req) throws RestBusException;
    
    CommonResponse getCourseDetail(int courseId) throws RestBusException;
    CommonResponse getModuleDetail(int moduleId) throws RestBusException;
    CommonResponse getResourseDetail(int resourseId) throws RestBusException;
    CommonResponse getUserDetail(int userId) throws RestBusException;
    CommonResponse getAssignmentDetail(int assignmentId) throws RestBusException;
    
}

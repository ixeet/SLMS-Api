/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scolere.lms.application.rest.bus.iface;

import com.scolere.lms.application.rest.exceptions.RestBusException;
import com.scolere.lms.application.rest.vo.response.CommonResponse;

/**
 *
 * @author dell
 */
public interface CommonBusIface {
    CommonResponse getSchoolMasterData() throws RestBusException;
    
}

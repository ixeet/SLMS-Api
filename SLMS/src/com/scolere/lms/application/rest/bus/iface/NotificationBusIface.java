/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scolere.lms.application.rest.bus.iface;

import com.scolere.lms.application.rest.exceptions.RestBusException;
import com.scolere.lms.application.rest.vo.request.NotificationRequest;
import com.scolere.lms.application.rest.vo.response.NotificationResponse;

/**
 *
 * @author dell
 */
public interface NotificationBusIface {
	
    NotificationResponse registerDevice(NotificationRequest req) throws RestBusException;
    NotificationResponse pushNotification(NotificationRequest req) throws RestBusException;

}

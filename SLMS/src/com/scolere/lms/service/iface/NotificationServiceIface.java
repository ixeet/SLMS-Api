/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scolere.lms.service.iface;

import java.util.List;

import com.scolere.lms.domain.exception.LmsServiceException;
import com.scolere.lms.domain.vo.cross.NotificationVO;


/**
 *
 * @author dell
 */
public interface NotificationServiceIface {
        
	int saveUserDevice(NotificationVO vo)throws LmsServiceException;
	boolean isDeviceRegistered(NotificationVO vo)throws LmsServiceException;
	NotificationVO getUserDevice(int userId)throws LmsServiceException;
	List<NotificationVO> getUserDevicesList(String deviceType)throws LmsServiceException;
	
}

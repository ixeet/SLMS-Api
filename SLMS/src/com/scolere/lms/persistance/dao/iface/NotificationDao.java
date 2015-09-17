/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scolere.lms.persistance.dao.iface;

import java.util.List;

import com.scolere.lms.domain.exception.LmsDaoException;
import com.scolere.lms.domain.vo.cross.NotificationVO;


/**
 *
 * @author dell
 */
public interface NotificationDao {
	
	int saveUserDevice(NotificationVO vo)throws LmsDaoException;
	boolean isDeviceRegistered(NotificationVO vo)throws LmsDaoException;
	NotificationVO getUserDevice(int userId)throws LmsDaoException;
	List<NotificationVO> getUserDevicesList(String deviceType)throws LmsDaoException;
	
    
}//End of class

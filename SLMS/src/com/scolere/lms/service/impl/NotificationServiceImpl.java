/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scolere.lms.service.impl;

import java.util.List;

import com.scolere.lms.domain.exception.LmsServiceException;
import com.scolere.lms.domain.vo.cross.NotificationVO;
import com.scolere.lms.persistance.dao.iface.FeedDao;
import com.scolere.lms.persistance.dao.iface.NotificationDao;
import com.scolere.lms.persistance.factory.LmsDaoFactory;
import com.scolere.lms.service.iface.NotificationServiceIface;


/**
 *
 * @author dell
 */
public class NotificationServiceImpl implements NotificationServiceIface{

	@Override
	public int saveUserDevice(NotificationVO vo) throws LmsServiceException {
		int count=0;
        try {
            
        	NotificationDao dao = (NotificationDao) LmsDaoFactory.getDAO(NotificationDao.class);
            count = dao.saveUserDevice(vo);
                    
        } catch (Exception ex) {
            System.out.println("LmsServiceException # saveUserDevice = "+ex);
            throw new LmsServiceException(ex.getMessage());
        }        
        
        return count;
	}

	
	@Override
	public boolean isDeviceRegistered(NotificationVO vo)
			throws LmsServiceException {
		boolean status=false;
        try {
            
        	NotificationDao dao = (NotificationDao) LmsDaoFactory.getDAO(NotificationDao.class);
        	status = dao.isDeviceRegistered(vo);
                    
        } catch (Exception ex) {
            System.out.println("LmsServiceException # isDeviceRegistered = "+ex);
            throw new LmsServiceException(ex.getMessage());
        }        
        
        return status;
	}
	

	@Override
	public NotificationVO getUserDevice(int userId) throws LmsServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NotificationVO> getUserDevicesList(String deviceType)
			throws LmsServiceException {
		// TODO Auto-generated method stub
		return null;
	}
    
}//End of class

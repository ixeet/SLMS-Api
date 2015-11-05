package com.scolere.lms.application.rest.bus.impl;

import java.util.List;

import com.scolere.lms.application.rest.bus.iface.NotificationBusIface;
import com.scolere.lms.application.rest.constants.SLMSRestConstants;
import com.scolere.lms.application.rest.exceptions.RestBusException;
import com.scolere.lms.application.rest.vo.request.NotificationRequest;
import com.scolere.lms.application.rest.vo.response.NotificationResponse;
import com.scolere.lms.domain.exception.LmsServiceException;
import com.scolere.lms.domain.vo.cross.NotificationVO;
import com.scolere.lms.service.iface.NotificationServiceIface;
import com.scolere.lms.service.impl.NotificationServiceImpl;


public class NotificationBusImpl implements NotificationBusIface {

	@Override
	public NotificationResponse registerDevice(NotificationRequest req)
			throws RestBusException {
		NotificationResponse resp = new NotificationResponse();
	       
	       try{
	    	   NotificationServiceIface service = new NotificationServiceImpl();
	    	   NotificationVO vo = new NotificationVO(req.getUserId(), req.getDeviceType(), req.getDeviceToken());
	    	   		if(service.isDeviceRegistered(vo)==false)
	    	   		{
	    	   			service.saveUserDevice(vo);
		                resp.setStatus(SLMSRestConstants.status_success);
		                resp.setStatusMessage(SLMSRestConstants.message_success);
	    	   		}else{
		                resp.setStatus(SLMSRestConstants.status_success);
		                resp.setStatusMessage(SLMSRestConstants.message_deviceRegistered);
	    	   		}

	        } catch (LmsServiceException ex) {
	            System.out.println("LmsServiceException # registerDevice "+ex.getMessage());
	            resp.setStatus(SLMSRestConstants.status_failure);
	            resp.setStatusMessage(SLMSRestConstants.message_failure);
	            resp.setErrorMessage(ex.getMessage());
	        } catch (Exception ex) {
	            System.out.println("Exception # registerDevice "+ex.getMessage());
	            resp.setStatus(SLMSRestConstants.status_failure);
	            resp.setStatusMessage(SLMSRestConstants.message_failure);
	            resp.setErrorMessage(ex.getMessage());
	        }
	       
	       return resp;
	}

	
	@Override
	public NotificationResponse pushNotification(NotificationRequest req)
			throws RestBusException {
		NotificationResponse resp = new NotificationResponse();
	       
	       try{
	    	   NotificationServiceIface service = new NotificationServiceImpl();
	    	   //......
	    	   List<NotificationVO> deviceList = service.getUserDevicesList(req.getDeviceType());
	                //Write push notification code here
	           
	    	   
	    	   
	    	   
	                //setting success into response
	                resp.setStatus(SLMSRestConstants.status_success);
	                resp.setStatusMessage(SLMSRestConstants.message_success);                   

	        } catch (LmsServiceException ex) {
	            System.out.println("LmsServiceException # pushNotification "+ex.getMessage());
	            resp.setStatus(SLMSRestConstants.status_failure);
	            resp.setStatusMessage(SLMSRestConstants.message_failure);
	            resp.setErrorMessage(ex.getMessage());
	        } catch (Exception ex) {
	            System.out.println("Exception # pushNotification "+ex.getMessage());
	            resp.setStatus(SLMSRestConstants.status_failure);
	            resp.setStatusMessage(SLMSRestConstants.message_failure);
	            resp.setErrorMessage(ex.getMessage());
	        }
	       
	       return resp;
	}
	

	
	

}//End of class

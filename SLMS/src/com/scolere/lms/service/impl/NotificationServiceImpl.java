/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scolere.lms.service.impl;

import java.util.ArrayList;
import java.util.List;

import javapns.Push;
import javapns.notification.PushNotificationPayload;
import javapns.notification.PushedNotification;
import javapns.notification.ResponsePacket;


import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Sender;
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
	
    
	
	
	/**
	 * This method is used to push notifications to iPhone apps.
	 * New libraries used - JavaPNS_2.2.jar,bcprov-jdk15-146.jar 
	 * @param message
	 * @param list
	 */
	 private void pushToIphone(String message,ArrayList<String> list)
	{
		System.out.println(">>>>>>>Start notifyIphone");

		try {

	        PushNotificationPayload payload = PushNotificationPayload.complex();

	        payload.addAlert(message);
	        payload.addBadge(0);
	        payload.addSound("default");
	        payload.addCustomDictionary("id", "1");

	        System.out.println("payload="+payload.toString());

	        List<PushedNotification> NOTIFICATIONS = Push.payload(payload, "C:\\push-ssl\\GrowthCafe_Push_key.p12", "1234", false, "bcec5690b5effd5e02c0d051c12d7672697a9fbfaac2441c3b380932a5204831");
	    //    List<PushedNotification> NOTIFICATIONS = Push.payload(payload, appConstants.MMG_PUSHNOTIFICATION_IPHONE_KEYSTORESSLP12_PATH, appConstants.MMG_PUSHNOTIFICATION_IPHONE_PASSWORD, false, list);

	        for (PushedNotification NOTIFICATION : NOTIFICATIONS) {
	            if (NOTIFICATION.isSuccessful()) {
	                    /* APPLE ACCEPTED THE NOTIFICATION AND SHOULD DELIVER IT */  
	            	System.out.println("PUSH NOTIFICATION SENT SUCCESSFULLY TO: " +
	                                                    NOTIFICATION.getDevice().getToken());
	                    /* STILL NEED TO QUERY THE FEEDBACK SERVICE REGULARLY */  
	            } 
	            else {
	                    String INVALIDTOKEN = NOTIFICATION.getDevice().getToken();
	                    /* ADD CODE HERE TO REMOVE INVALIDTOKEN FROM YOUR DATABASE */  

	                    /* FIND OUT MORE ABOUT WHAT THE PROBLEM WAS */  
	                    Exception THEPROBLEM = NOTIFICATION.getException();
	                    THEPROBLEM.printStackTrace();

	                    /* IF THE PROBLEM WAS AN ERROR-RESPONSE PACKET RETURNED BY APPLE, GET IT */  
	                    ResponsePacket THEERRORRESPONSE = NOTIFICATION.getResponse();
	                    if (THEERRORRESPONSE != null) {
	                    	System.out.println(THEERRORRESPONSE.getMessage());
	                    }
	            }
	      }


	    } catch (Exception e) {
	    	System.out.println("Exception created while push notifications to iphone : "+e.getMessage());
	    }

		
		System.out.println(">>>>>>>End notifyIphone");
	}
	

	/**
	 * This method is used to push notifications to Android phones.
	 * New libraries used - gcm-server.jar ,json_simple-1.1.jar
	 * @param messageToSend
	 * @param devicesList
	 */
	private void notifyAndroid(String messageToSend,ArrayList<String> devicesList)
	{
		System.out.println(">>>>>>>Start notifyAndroid");

		try {

			Sender sender = new Sender("AIzaSyCro057JwMpPKx5Os4SSL9iUkIpGmvuPfo");
//			Sender sender = new Sender(appConstants.MMG_PUSHNOTIFICATION_ANDROID_KEY);

			// Use this line to send message without payload data
			// Message message = new Message.Builder().build();

			// use this line to send message with payload data
			Message message = new Message.Builder()
					.collapseKey("1") 
					.timeToLive(120)  //Time in seconds to keep message queued if device offline.
					.delayWhileIdle(true) // Wait for device to become active before sending.
					.addData("message",messageToSend)
					.build();

			// Use this code to send to a single device
			// Result result = sender
			// .send(message,
			// "APA91bGiRaramjyohc2lKjAgFGpzBwtEmI8tJC30O89C2b3IjP1CuMeU1h9LMjKhmWuZwcXZjy1eqC4cE0tWBNt61Kx_SuMF6awzIt8WNq_4AfwflaVPHQ0wYHG_UX3snjp_U-5kJkmysdRlN6T8xChB1n3DtIq98w",
			// 1);

			// Use this for multicast messages
			MulticastResult result = sender.send(message, devicesList, 1);
			//sender.send(message, devicesList, 1);

				System.out.println("Push send result = "+result.toString());
			if (result.getResults() != null) {
				int canonicalRegId = result.getCanonicalIds();
				if (canonicalRegId != 0) {
				}
			} else {
				int error = result.getFailure();
				System.out.println("error - "+error);
			}
		} catch (Exception e) {
			System.out.println("Exception created while push notifications to android : "+e.getMessage());
		}
		
		System.out.println(">>>>>>>End notifyAndroid");
		
	}
	
	
	
	
    public static void main(String[] args) {
    	 

        String[] devices = {"069c2f614fa64cec443b8a5a86248c89fe501791ab7bfc73ce0eae6896bb54be", 
                            "e5d4ac11104d2259ca97dfa2b39bba816bdf2c442d24e376d862f97f1bb27a60"}; 


        try {
         
                 List<PushedNotification> notifications = Push.alert("Hello Test Hello Hello...!","C:\\push-ssl\\GrowthCafe_Push_key.p12", "1234", false, devices); 
 
                 for (PushedNotification notification : notifications) {
                         if (notification.isSuccessful()) {
                                 /* Apple accepted the notification and should deliver it */  
                                 System.out.println("Push notification sent successfully to: " +
                                                                 notification.getDevice().getToken());
                                 /* Still need to query the Feedback Service regularly */  
                         } else {
                                 String invalidToken = notification.getDevice().getToken();
                                 /* Add code here to remove invalidToken from your database */  
 
                                 /* Find out more about what the problem was */  
                                 Exception theProblem = notification.getException();
                                 theProblem.printStackTrace();
 
                                 /* If the problem was an error-response packet returned by Apple, get it */  
                                 ResponsePacket theErrorResponse = notification.getResponse();
                                 if (theErrorResponse != null) {
                                         System.out.println(theErrorResponse.getMessage());
                                 }
                         }
                 }
                 
         } catch (Exception e) {
                 /* A critical problem occurred while trying to use your keystore */  
               System.out.println("Exception >>>"+e.getMessage());
                 
         }
 }	
	
	
}//End of class

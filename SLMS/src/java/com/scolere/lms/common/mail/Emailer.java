/*
 * Created on Jan 4, 2006
 * By: 
 */
package com.scolere.lms.common.mail;


import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;


public class Emailer
{
    
    public void mailto(String from, String to, String subject, String message)
            throws MessagingException {

            System.out.println("Sending message with from = "+from+" to= "+to+" subject= "+subject+" message= "+message);
            sendHtmlMail(from,to,subject,message);
            
    }   
    
    
   private void sendHtmlMail(String fromEmail,String toEmail,String subject,String emailMeggage)
   {
 
      String host="opal.interrait.com";//change accordingly
      String to=toEmail;
      final String user=fromEmail;
      //final String password="-----";//change accordingly

      Properties properties = System.getProperties();
      properties.setProperty("mail.smtp.host", host);
    //  properties.put("mail.smtp.auth", "true");

//      Session session = Session.getDefaultInstance(properties,
//	new javax.mail.Authenticator() {
//	 protected PasswordAuthentication getPasswordAuthentication() {
//	  return new PasswordAuthentication(user,password);
//	 }
//      });
      
      Session session = Session.getDefaultInstance(properties);
      
      try{
         MimeMessage message = new MimeMessage(session);
         message.setFrom(new InternetAddress(user));
         message.addRecipient(Message.RecipientType.TO,
                                  new InternetAddress(to));

        message.setSubject(subject);
        message.setContent(emailMeggage,"text/html" );
  
       Transport.send(message);
         System.out.println("message sent....");
      }catch (MessagingException ex) {
          System.out.println("Exception while sending email - "+ex);
          ex.printStackTrace();
      
      }
   }//end of method sendHtmlMail

    
   
  public static void main(String [] args)
   {

      String host="opal.interrait.com";//change accordingly
      String to="mymazda@interrait.com";//change accordingly
      final String user="mymazda@interrait.com";//change accordingly
      //final String password="-----";//change accordingly

      Properties properties = System.getProperties();
      properties.setProperty("mail.smtp.host", host);
    //  properties.put("mail.smtp.auth", "true");

//      Session session = Session.getDefaultInstance(properties,
//	new javax.mail.Authenticator() {
//	 protected PasswordAuthentication getPasswordAuthentication() {
//	  return new PasswordAuthentication(user,password);
//	 }
//      });
      
      Session session = Session.getDefaultInstance(properties);
      
      try{
         MimeMessage message = new MimeMessage(session);
         message.setFrom(new InternetAddress(user));
         message.addRecipient(Message.RecipientType.TO,
                                  new InternetAddress(to));

        message.setSubject("HTML Message");
        message.setContent("<h1>sending html mail check</h1>","text/html" );
  
       Transport.send(message);
         System.out.println("message sent....");
      }catch (MessagingException ex) {ex.printStackTrace();}
   }
   
 
}//end of class

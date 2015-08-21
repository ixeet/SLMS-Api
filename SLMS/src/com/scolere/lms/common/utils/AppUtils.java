/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scolere.lms.common.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author dell
 */
public class AppUtils {

	

	/**
	 * Use to write file on file system at specified location.
	 * @param uploadedInputStream
	 * @param uploadedFileLocation
	 */
    public static void writeToFile(InputStream uploadedInputStream,
            String uploadedFileLocation) {

        try {
            OutputStream out = new FileOutputStream(new File(uploadedFileLocation));
            int read = 0;
            byte[] bytes = new byte[1024];

            out = new FileOutputStream(new File(uploadedFileLocation));
            while ((read = uploadedInputStream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            out.flush();
            out.close();
        } catch (Exception e) {
            System.out.println("File upload exception #2 "+e.getMessage());
        }

    }
    
    
	public static boolean isDateEqual(String dateStr1, String dateStr2) {
		boolean isEqual = false;

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            int temp = sdf.parse(dateStr1).compareTo(sdf.parse(dateStr2));
            if (temp >= 0) {
                isEqual = true;
            }

        } catch (Exception ex) {
            //##("Exception > " + ex);
        }

		//##("is equal ? " + isEqual);

		return isEqual;
	}	
	
	
	
	public static String getCurrDateForDB()
	{
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date today = Calendar.getInstance().getTime();        
		String reportDate = df.format(today);
		//##("Assessment Date: " + reportDate); 		
		
		return reportDate;
	}    

        public static String getFirstDayOfMonth()
	{
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                Calendar cal=Calendar.getInstance();
                cal.set(Calendar.DAY_OF_MONTH,1);
		Date today = cal.getTime();        
		String reportDate = df.format(today);
		//##("First Day of month: " + reportDate); 		
		
		return reportDate;
	}    
    
        public static String getLastDayOfMonth()
	{
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                 Date today = new Date();  
                    Calendar calendar = Calendar.getInstance();  
                    calendar.setTime(today);  

                    calendar.add(Calendar.MONTH, 1);  
                    calendar.set(Calendar.DAY_OF_MONTH, 1);  
                    calendar.add(Calendar.DATE, -1);  

                    Date lastDayOfMonth = calendar.getTime();                 
		String reportDate = df.format(lastDayOfMonth);
		//##("Last Day of month: " + reportDate); 		
		
		return reportDate;
	}    

        public static String getNextSeventhDay()
	{
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                 Date today = new Date();  
                    Calendar calendar = Calendar.getInstance();  
                    calendar.setTime(today);  

                    calendar.add(Calendar.DATE, 7);  

                    Date lastDayOfMonth = calendar.getTime();                 
		String reportDate = df.format(lastDayOfMonth);
		//##("getNextSeventhDay : " + reportDate); 		
		
		return reportDate;
	}    

        
        public static String getSixtyDayBefore()
	{
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                 Date today = new Date();  
                    Calendar calendar = Calendar.getInstance();  
                    calendar.setTime(today);  

                    calendar.add(Calendar.DATE, -60);  

                    Date lastDayOfMonth = calendar.getTime();                 
		String reportDate = df.format(lastDayOfMonth);
		//##("getNextSeventhDay : " + reportDate); 		
		
		return reportDate;
	}    
        
        
        public static String getDateTwoYrsBefore()
	{
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                 Date today = new Date();  
                    Calendar calendar = Calendar.getInstance();  
                    calendar.setTime(today);  

                    calendar.add(Calendar.MONTH, -24);  

                    Date lastDayOfMonth = calendar.getTime();                 
		String reportDate = df.format(lastDayOfMonth);
		//##("getDateTwoYrsBefore : " + reportDate); 		
		
		return reportDate;
	} 
        
    public static void main(String[] arg){
    
       //##(getSixtyDayBefore());
    }
    
    
}

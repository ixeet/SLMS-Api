package com.scolere.lms.common.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class PropertyManager {
	
	private String appPropertFile="slms.properties";
	private static Properties properties=null;
			
	
	public PropertyManager() {
		
		try{
			if(properties==null){
				String filePath = System.getProperty("SLMS_HOME") + System.getProperty("file.separator")+appPropertFile;
				System.out.println("Property file location ? "+filePath);
				properties=new Properties();
				properties.load(new FileInputStream(filePath));
				System.out.println(properties.getProperty("TEST.MESSAGE.WELCOME"));			
			}else{
				System.out.println("Properties all ready loaded.");
				System.out.println(properties.getProperty("TEST.MESSAGE.WELCOME"));	
			}
		}catch(Exception e){
			System.out.println("Property loading error--"+e.getMessage());
			System.exit(0);
		}
		
	}

	public static String getProperty(String propertyName)
	{
		return properties.getProperty(propertyName);
	}

	

	public static Properties getCurrentProperty(String filename){
			Properties prop = new Properties();
			try {
			java.net.URL url = Thread.currentThread().getContextClassLoader().getResource(filename);
				prop.load(url.openStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
			return prop;
		}

}

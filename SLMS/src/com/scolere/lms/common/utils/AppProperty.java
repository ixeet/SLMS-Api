package com.scolere.lms.common.utils;

import java.io.IOException;
import java.util.Properties;



public class AppProperty {
	
public static Properties getProperty(String filename){
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

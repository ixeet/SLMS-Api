/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scolere.lms.common.utils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Web application lifecycle listener. 
 *
 * @author dell
 */
@WebListener()
public class ApplContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Context initialized...........");
        PropertyManager propertyManager= new PropertyManager();
        System.out.println("Application property loaded..."+propertyManager.getProperty("TEST.MESSAGE.WELCOME"));
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Context destroyed...........");
    }
    
    
}

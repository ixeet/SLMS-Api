/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scolere.lms.application.rest.controller;

import com.scolere.lms.application.rest.bus.iface.CommonBusIface;
import com.scolere.lms.application.rest.bus.impl.CommonBusImpl;
import com.scolere.lms.application.rest.exceptions.RestBusException;
import com.scolere.lms.application.rest.vo.response.CommonResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author dell
 */
@Path("/common")
public class CommonController {

    CommonBusIface restService = new CommonBusImpl();

    /**
     * Default controller method
     *
     * @return
     */
    @GET
    public String defaultx() {
        String message = "Welcome to SLMS common webservices....";
        return message;
    }

    @GET
    @Path("/getMasterData")
    @Produces(MediaType.APPLICATION_JSON)
    public CommonResponse getMasterData() {
        System.out.println("Start getMasterData >> ");
        CommonResponse commonResponse = null;

        try {
            commonResponse = restService.getSchoolMasterData();
        } catch (RestBusException ex) {
            System.out.println("Exception # getMasterData - " + ex);
        }

        System.out.println("<< End getMasterData # " + commonResponse);
        return commonResponse;
    }
    
    
    
}//End of class

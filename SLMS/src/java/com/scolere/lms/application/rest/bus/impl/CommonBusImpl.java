/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scolere.lms.application.rest.bus.impl;

import com.scolere.lms.application.rest.bus.iface.CommonBusIface;
import com.scolere.lms.application.rest.exceptions.RestBusException;
import com.scolere.lms.application.rest.vo.response.ClassRespTO;
import com.scolere.lms.application.rest.vo.response.CommonResponse;
import com.scolere.lms.application.rest.vo.response.HomeRoomRespTO;
import com.scolere.lms.application.rest.vo.response.SchoolRespTO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dell
 */
public class CommonBusImpl implements CommonBusIface{

    @Override
    public CommonResponse getSchoolMasterData() throws RestBusException {
        CommonResponse resp = new CommonResponse();
    
        List<SchoolRespTO> schoolList = new ArrayList<SchoolRespTO>();
        SchoolRespTO school = new SchoolRespTO("1", "Wilkinson boys school");
        SchoolRespTO school2 = new SchoolRespTO("2", "Lovely public school");
        
        List<ClassRespTO> classList = new ArrayList<ClassRespTO>(); 
        ClassRespTO claz = new ClassRespTO("01", "Class 01");
        
        List<HomeRoomRespTO> HomeRoomList = new ArrayList<HomeRoomRespTO>();
        HomeRoomRespTO hrm1 = new HomeRoomRespTO("01", "HRM1");
        HomeRoomList.add(hrm1);
        HomeRoomRespTO hrm2 = new HomeRoomRespTO("02", "HRM2");
        HomeRoomList.add(hrm2);
        claz.setHomeRoomList(HomeRoomList);
        classList.add(claz);
        
        school.setClassList(classList);
        school2.setClassList(classList);

        schoolList.add(school);
        schoolList.add(school2);
        
        resp.setSchoolList(schoolList);
        resp.setStatus(1001);
        resp.setStatusMessage("Success");
        
        return resp;
    }
    
    
    
}//End of class

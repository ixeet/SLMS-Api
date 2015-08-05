/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scolere.lms.application.rest.bus.impl;

import com.scolere.lms.application.rest.bus.iface.CommonBusIface;
import com.scolere.lms.application.rest.constants.SLMSRestConstants;
import com.scolere.lms.application.rest.exceptions.RestBusException;
import com.scolere.lms.application.rest.vo.response.ClassRespTO;
import com.scolere.lms.application.rest.vo.response.CommonResponse;
import com.scolere.lms.application.rest.vo.response.HomeRoomRespTO;
import com.scolere.lms.application.rest.vo.response.SchoolRespTO;
import com.scolere.lms.domain.vo.ClassMasterVo;
import com.scolere.lms.domain.vo.SchoolMasterVo;
import com.scolere.lms.service.iface.CommonServiceIface;
import com.scolere.lms.service.impl.CommonServiceImpl;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import my.java.interfac.HomeRoomMasterVo;

/**
 *
 * @author dell
 */
public class CommonBusImpl implements CommonBusIface{

    /*
     * Get School,Class & homeroom master table data and display in given hirarchy.
     * 
     */
    
    @Override
    public CommonResponse getSchoolMasterData() throws RestBusException {
        CommonResponse resp = new CommonResponse();
        CommonServiceIface service = new CommonServiceImpl();
        
        try{
        
        List<SchoolMasterVo> schoolListFromDB = service.getSchoolMasterVoList();
        List<SchoolRespTO> schoolList = new ArrayList<SchoolRespTO>(schoolListFromDB.size());

        SchoolRespTO schoolRespTO = null;
        for(SchoolMasterVo vo : schoolListFromDB)
        {
        schoolRespTO = new SchoolRespTO(String.valueOf(vo.getSchoolId()), vo.getSchoolName());
        //Class master data
        List<ClassMasterVo> clsMstrListDB = service.getClassVoList(vo.getSchoolId());
        List<ClassRespTO> classList = new ArrayList<ClassRespTO>(clsMstrListDB.size());
        ClassRespTO classRespTO = null;
        for(ClassMasterVo clsvo : clsMstrListDB)
        {
        classRespTO = new ClassRespTO(String.valueOf(clsvo.getClassId()), clsvo.getClassName());
        //Homeroom data
        List<HomeRoomMasterVo> hrmListDB = service.getHomeRoomMasterVoList(clsvo.getClassId());
        List<HomeRoomRespTO> homeRoomList = new ArrayList<HomeRoomRespTO>(hrmListDB.size());
        HomeRoomRespTO homeRoomRespTO = null;
        for(HomeRoomMasterVo hrmvo  : hrmListDB)
        {
        homeRoomRespTO = new HomeRoomRespTO(String.valueOf(hrmvo.getHomeRoomMstrId()), hrmvo.getHomeRoomMstrName());
        homeRoomList.add(homeRoomRespTO);
        }//hrm loop
        
        classRespTO.setHomeRoomList(homeRoomList);
        classList.add(classRespTO);
        }//cls loop
        
        schoolRespTO.setClassList(classList);
        schoolList.add(schoolRespTO);
        } //school loop
        resp.setSchoolList(schoolList);

        resp.setStatus(SLMSRestConstants.status_success);
        resp.setStatusMessage(SLMSRestConstants.message_success); 
        }catch(Exception e){
            System.out.println("Exception # getSchoolMasterData "+e.getMessage());
            resp.setStatus(SLMSRestConstants.status_failure);
            resp.setStatusMessage(SLMSRestConstants.message_failure);
            resp.setErrorMessage(e.getMessage());            
        }
        
        return resp;
  }
    
    
    
}//End of class

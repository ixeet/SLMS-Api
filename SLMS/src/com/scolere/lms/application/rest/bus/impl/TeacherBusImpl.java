package com.scolere.lms.application.rest.bus.impl;

import java.text.DecimalFormat;
import java.util.List;

import com.scolere.lms.application.rest.bus.iface.TeacherBusIface;
import com.scolere.lms.application.rest.constants.SLMSRestConstants;
import com.scolere.lms.application.rest.exceptions.RestBusException;
import com.scolere.lms.application.rest.vo.request.CommonRequest;
import com.scolere.lms.application.rest.vo.response.PercentageRespTo;
import com.scolere.lms.application.rest.vo.response.TeacherResponse;
import com.scolere.lms.domain.exception.LmsServiceException;
import com.scolere.lms.service.iface.TeacherServiceIface;
import com.scolere.lms.service.impl.TeacherServiceImpl;



public class TeacherBusImpl implements TeacherBusIface{
	
	@Override
	public TeacherResponse updateAssignmentStatus(int schoolId,int classId,int hrmId,int courseId,int moduleId,int statusCode,String userNm,String dueDate) throws RestBusException{
		
		TeacherResponse resp = new TeacherResponse();
       
       try{
                TeacherServiceIface service = new TeacherServiceImpl();
                int updateCount = service.updateAssignmentStatus(schoolId, classId, hrmId, courseId,moduleId,statusCode,userNm,dueDate);
           
                if(updateCount>0)
                {
                //setting success into response
                resp.setStatus(SLMSRestConstants.status_success);
                resp.setStatusMessage(SLMSRestConstants.message_success); 
                }else if(updateCount==0){
	            resp.setStatus(SLMSRestConstants.status_noUpdate);
	            resp.setStatusMessage(SLMSRestConstants.message_noUpdate); 
                }else{
		        resp.setStatus(SLMSRestConstants.status_noUpdate);
		        resp.setStatusMessage(SLMSRestConstants.message_moduelStatus_zero); 	                	
                }

        } catch (LmsServiceException ex) {
            System.out.println("LmsServiceException # updateAssignmentStatus "+ex.getMessage());
            resp.setStatus(SLMSRestConstants.status_failure);
            resp.setStatusMessage(SLMSRestConstants.message_failure);
            resp.setErrorMessage(ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Exception # updateAssignmentStatus "+ex.getMessage());
            resp.setStatus(SLMSRestConstants.status_failure);
            resp.setStatusMessage(SLMSRestConstants.message_failure);
            resp.setErrorMessage(ex.getMessage());
        }
       
       return resp;
}
	
	
	@Override
	public TeacherResponse updateCourseStatus(int courseSessionId,
			int statusCode) throws RestBusException {
		TeacherResponse resp = new TeacherResponse();

		try {
			TeacherServiceIface service = new TeacherServiceImpl();
			int updateCount = service.updateCourseStatus(courseSessionId,
					statusCode);

			if (updateCount > 0) {
				// setting success into response
				resp.setStatus(SLMSRestConstants.status_success);
				resp.setStatusMessage(SLMSRestConstants.message_success);
			} else if (updateCount == 0) {
				resp.setStatus(SLMSRestConstants.status_noUpdate);
				resp.setStatusMessage(SLMSRestConstants.message_noUpdate);
			} else {
				resp.setStatus(SLMSRestConstants.status_noUpdate);
				resp.setStatusMessage(SLMSRestConstants.message_courseStatus_all);
			}

		} catch (LmsServiceException ex) {
			System.out.println("LmsServiceException # updateCourseStatus "
					+ ex.getMessage());
			resp.setStatus(SLMSRestConstants.status_failure);
			resp.setStatusMessage(SLMSRestConstants.message_failure);
			resp.setErrorMessage(ex.getMessage());
		} catch (Exception ex) {
			System.out.println("Exception # updateCourseStatus "
					+ ex.getMessage());
			resp.setStatus(SLMSRestConstants.status_failure);
			resp.setStatusMessage(SLMSRestConstants.message_failure);
			resp.setErrorMessage(ex.getMessage());
		}

		return resp;
	}
	

	@Override
	public TeacherResponse updateCourseModuleStatus(int moduleSessionId,int statusCode)
			throws RestBusException {
		
			TeacherResponse resp = new TeacherResponse();
	       
	       try{
	                TeacherServiceIface service = new TeacherServiceImpl();
	                int updateCount = service.updateCourseModuleStatus(moduleSessionId,statusCode);
	           
	                if(updateCount>0)
	                {
	                //setting success into response
	                resp.setStatus(SLMSRestConstants.status_success);
	                resp.setStatusMessage(SLMSRestConstants.message_success); 
	                }else if(updateCount==0){
		            resp.setStatus(SLMSRestConstants.status_noUpdate);
		            resp.setStatusMessage(SLMSRestConstants.message_noUpdate); 
	                }else{
			        resp.setStatus(SLMSRestConstants.status_noUpdate);
			        resp.setStatusMessage(SLMSRestConstants.message_moduelStatus_zero); 	                	
	                }

	        } catch (LmsServiceException ex) {
	            System.out.println("LmsServiceException # updateCourseModuleStatus "+ex.getMessage());
	            resp.setStatus(SLMSRestConstants.status_failure);
	            resp.setStatusMessage(SLMSRestConstants.message_failure);
	            resp.setErrorMessage(ex.getMessage());
	        } catch (Exception ex) {
	            System.out.println("Exception # updateCourseModuleStatus "+ex.getMessage());
	            resp.setStatus(SLMSRestConstants.status_failure);
	            resp.setStatusMessage(SLMSRestConstants.message_failure);
	            resp.setErrorMessage(ex.getMessage());
	        }
	       
	       return resp;
	}

	

	@Override
	public TeacherResponse updateCourseResourceStatus(int resourseSessionId,int statusCode)
			throws RestBusException {
			
			TeacherResponse resp = new TeacherResponse();
	       
	       try{
	                TeacherServiceIface service = new TeacherServiceImpl();
	                int updateCount = service.updateCourseResourceStatus(resourseSessionId,statusCode);
	           
	                if(updateCount>0)
	                {
	                //setting success into response
	                resp.setStatus(SLMSRestConstants.status_success);
	                resp.setStatusMessage(SLMSRestConstants.message_success); 
	                }else if(updateCount==0){
		            resp.setStatus(SLMSRestConstants.status_noUpdate);
		            resp.setStatusMessage(SLMSRestConstants.message_noUpdate); 
	                }else{
			        resp.setStatus(SLMSRestConstants.status_noUpdate);
			        resp.setStatusMessage(SLMSRestConstants.message_invalidStatus); 	                	
	                }

	        } catch (LmsServiceException ex) {
	            System.out.println("LmsServiceException # updateCourseResourceStatus "+ex.getMessage());
	            resp.setStatus(SLMSRestConstants.status_failure);
	            resp.setStatusMessage(SLMSRestConstants.message_failure);
	            resp.setErrorMessage(ex.getMessage());
	        } catch (Exception ex) {
	            System.out.println("Exception # updateCourseResourceStatus "+ex.getMessage());
	            resp.setStatus(SLMSRestConstants.status_failure);
	            resp.setStatusMessage(SLMSRestConstants.message_failure);
	            resp.setErrorMessage(ex.getMessage());
	        }
	       
	       return resp;
	}


	@Override
	public TeacherResponse getPercentage(CommonRequest req)
			throws RestBusException {
		TeacherResponse resp = new TeacherResponse();
		PercentageRespTo percentage = new PercentageRespTo();
		 DecimalFormat df = new DecimalFormat("#.##");
	       try{
	                TeacherServiceIface service = new TeacherServiceImpl();
	                List<Integer> courseList = service.getCoursePercentage(req.getUserName(),req.getSchoolId(),req.getClassId(),req.getHrmId());
	                
	                
	                
	                List<Integer> assList = service.getAssignmentPercentage(req.getUserName(),req.getSchoolId(),req.getClassId(),req.getHrmId());
	                if(courseList !=null && courseList.size()>0)
	                {
	                	double completed=0;
	                	double inprogress=0;
	                	double notStarted=0;
	                	double totalCourse=courseList.size();
	                 for(int status : courseList){
	                	 if(status==0)
	                		 inprogress=inprogress+1;
	                	 else if(status==1)
	                		 completed=completed+1;
	                	 else if(status==2)
	                		 notStarted=notStarted+1;
	                 }
	                 percentage.setCourseComplete(df.format((completed*100/totalCourse)));
	                 percentage.setCourseProgress(df.format((inprogress*100/totalCourse)));
	                 percentage.setCourseNotStarted(df.format((notStarted*100/totalCourse)));
		                
	                }else{
	                	percentage.setCourseNotStarted("-1");
	                }
	                
	                if(assList !=null && assList.size()>0)
	                {
	                 double open=0;
	                 double submitted=0;
	                 double reviewed=0;
	                 double totalAss=assList.size();
	                 for(int status : assList){
	                	 if(status==1)
	                		 open=open+1;
	                	 else if(status==2)
	                		 submitted=submitted+1;
	                	 else if(status==3)
	                		 reviewed=reviewed+1;
	                 }
	                 
	                 percentage.setAssNotSubmit(df.format((open*100/totalAss)));
	                 percentage.setAssSubmitted(df.format((submitted*100/totalAss)));
	                 percentage.setAssReviewed(df.format((reviewed*100/totalAss)));
	                }else{
	                	 percentage.setAssNotSubmit("100");
	                }
	                resp.setPercentage(percentage);
	                resp.setStatus(SLMSRestConstants.status_success);
	                resp.setStatusMessage(SLMSRestConstants.message_success); 
	        } catch (LmsServiceException ex) {
	            System.out.println("LmsServiceException # updateCourseModuleStatus "+ex.getMessage());
	            resp.setStatus(SLMSRestConstants.status_failure);
	            resp.setStatusMessage(SLMSRestConstants.message_failure);
	            resp.setErrorMessage(ex.getMessage());
	        } catch (Exception ex) {
	            System.out.println("Exception # updateCourseModuleStatus "+ex.getMessage());
	            resp.setStatus(SLMSRestConstants.status_failure);
	            resp.setStatusMessage(SLMSRestConstants.message_failure);
	            resp.setErrorMessage(ex.getMessage());
	        }
	       
	       return resp;
	}


	
}//End of class

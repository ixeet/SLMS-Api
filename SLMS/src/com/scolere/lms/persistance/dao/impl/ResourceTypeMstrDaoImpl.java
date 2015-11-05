/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scolere.lms.persistance.dao.impl;

import com.scolere.lms.domain.exception.LmsDaoException;
import com.scolere.lms.domain.vo.ResourceTypeMstrVo;

import com.scolere.lms.persistance.dao.iface.ResourceTypeMstrDao;
import com.scolere.lms.persistance.factory.LmsDaoAbstract;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class ResourceTypeMstrDaoImpl extends LmsDaoAbstract implements ResourceTypeMstrDao{
    
    public ResourceTypeMstrVo getResourceTypeMstrDetail(int id) throws LmsDaoException {
        System.out.println("Inside getResourceTypeMstrDetail(?) >>");
        //Create object to return
        ResourceTypeMstrVo userDtls = new ResourceTypeMstrVo();

        //1 . jdbc code start
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = getConnection();

            String sql = "SELECT * FROM resource_typ_mstr where RESOURCE_TYP_ID=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, userDtls.getResourceTypeId());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                //3. Set db data to object

                userDtls.setResourceTypeId(rs.getInt("RESOURCE_TYPE_ID"));
                userDtls.setResourceTypename(rs.getString("RESOURCE_TYPE_NAME"));

            }

            System.out.println("get records into the table...");

        } catch (SQLException se) {
            System.out.println("getResourceTypeMstrDetail # " + se);
            se.printStackTrace();
        } catch (Exception e) {
            System.out.println("getResourceTypeMstrDetail # " + e);
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, null);
        }
     //1 . jdbc code endd

        //4 Return as required by method
        return userDtls;
    }

    public boolean updateResourceTypeMstrDetail(ResourceTypeMstrVo vo) throws LmsDaoException {
        System.out.println("id =" + vo.getResourceTypeId());
        boolean status = true;

        //Database connection start
        Connection conn = null;
        PreparedStatement stmt = null;
        try {

            conn = getConnection();
            String sql = "UPDATE resource_typ_mstr set RESOURCE_TYP_NAME=?\n"
                    + "    WHERE RESOURCE_TYPE_ID=?";
            stmt = conn.prepareStatement(sql);
           
            stmt.setString(1, vo.getResourceTypename());
             stmt.setInt(2, vo.getResourceTypeId());
            stmt.executeUpdate();
            System.out.println("updated records into the table...");

        } catch (SQLException e) {
            System.out.println("getResourceTypeMstrDetail # " + e);
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("getResourceTypeMstrDetail # " + e);
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, null);
        }

        System.out.println("Successfully updated....");
        return status;
        //End writting code to save into database   
    }
    //save method

    public void saveResourceTypeMstrDetail(ResourceTypeMstrVo vo)  throws LmsDaoException{
        //Database connection start
        Connection conn = null;
        PreparedStatement stmt = null;
        try {

            conn = getConnection();
            String sql = "INSERT INTO resource_typ_mstr (RESOURSE_TYP_ID, RESOURCE_TYP_NAME)  VALUES(?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, vo.getResourceTypeId());
            stmt.setString(2, vo.getResourceTypename());

            //...
            stmt.executeUpdate();
            System.out.println("Inserted records into the table...");

        } catch (SQLException se) {
            System.out.println("getResourceTypeMstrDetail # " + se);
            se.printStackTrace();
        } catch (Exception e) {
            System.out.println("getResourceTypeMstrDetail # " + e);
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, null);
        }

        System.out.println("Successfully saved....");
    }

    //delete method
    public boolean deleteResourceTypeMstrDetail(ResourceTypeMstrVo vo) throws LmsDaoException {
        System.out.println("id =" + vo.getResourceTypeId());
        boolean status = true;

        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = getConnection();

            String sql = "DELETE FROM resource_typ_mstr WHERE RESOURCE_TYP_ID = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, vo.getResourceTypeId());
            stmt.executeUpdate();

            System.out.println("Deleted records into the table...");

        } catch (SQLException se) {
            System.out.println("getResourceTypeMstrDetail # " + se);
            se.printStackTrace();
        } catch (Exception e) {
            System.out.println("getResourceTypeMstrDetail # " + e);
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, null);
        }

        System.out.println("Successfully deleted....");
        return status;
    }

    
    public List< ResourceTypeMstrVo> getResourceTypeMstrVoList()  throws LmsDaoException{
        List<ResourceTypeMstrVo> distList = new ArrayList<ResourceTypeMstrVo>();

        //1 . jdbc code start
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = getConnection();

            String sql = "SELECT * FROM resource_typ_mstr ";
            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {

                //3. Set db data to object
                ResourceTypeMstrVo userDtls = new ResourceTypeMstrVo();

                userDtls.setResourceTypeId(rs.getInt("RESOURCE_TYP_ID"));
                userDtls.setResourceTypename(rs.getString("RESOURCE_TYP_NAME"));

                //Add into list
                distList.add(userDtls);
            }

            System.out.println("get records into the table...");

        } catch (SQLException se) {
            System.out.println("getResourceTypeMstrDetail # " + se);
            se.printStackTrace();
        } catch (Exception e) {
            System.out.println("getResourceTypeMstrDetail # " + e);
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, null);
        }

        return distList;

    }

	 
	@Override
	public int uploadAssignment(int assignmentId,String resourceName,String resourceAuthor, String resourceDesc,String userName, String descTxt, String url, String thumbUrl, String authorImgUrl) throws LmsDaoException {
			
		int status=0;
		
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;
        try {

            conn = getConnection();
            String sql = "INSERT INTO resourse_mstr(RESOURSE_ID, RESOURSE_NAME, RESOURCE_AUTHOR, RESOURCE_DURATION, DESC_TXT, RESOURCE_TYP_ID, METADATA, DELETED_FL, DISPLAY_NO, ENABLE_FL, CREATED_BY, LAST_USERID_CD, LAST_UPDT_TM, RESOURCE_URL, AUTHOR_IMG, THUMB_IMG)" +
            		"VALUES(?, ?, ?, 0, ?, 1, ?, '0', 0, '1', ?, ?,utc_timestamp, ?, ?, ?)";
            
            String res_id=getQueryConcatedResult("SELECT MAX(RESOURSE_ID)+1 FROM resourse_mstr");
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(res_id));
            stmt.setString(2,resourceName);
            stmt.setString(3, resourceAuthor);
            stmt.setString(4, resourceDesc);
            stmt.setString(5, resourceDesc);//Metadata
            stmt.setString(6, userName);
            stmt.setString(7, userName);//Last updated user
            stmt.setString(8, url);
            stmt.setString(9, authorImgUrl);
            stmt.setString(10, thumbUrl);

            int t=stmt.executeUpdate();
            System.out.println("No of inserted resources = "+t);
            if (res_id!=null && t>0) {
                System.out.println("Last inserted userId : "+res_id);
                //Add module-resource mapping
                String modResMapQuery="INSERT INTO module_resource_map(MODULE_ID, RESOURCE_ID)VALUES((SELECT MODULE_ID FROM module_assignment_map where ASSIGNMENT_ID="+assignmentId+"), "+res_id+")";
                boolean modResMapStatus = deleteOrUpdateByQuery(modResMapQuery);
                System.out.println("Create module-resource mapping ? "+modResMapStatus);     
                
                //Update assignment status
//                String updateQuery = "UPDATE assignment_resource_txn SET UPLODED_RESOURCE_ID="+res_id+", UPLOADED_ON=current_date, IS_COMPLETED='2', LAST_USERID_CD='"+userName+"', LAST_UPDT_TM=utc_timestamp WHERE ASSIGNMENT_ID = "+assignmentId+" AND STUDENT_ID='"+userName+"'";
                //DB_UPDT
                String updateQuery = "UPDATE assignment_resource_txn SET UPLODED_RESOURCE_ID="+res_id+", UPLOADED_ON=current_date, IS_COMPLETED='2', LAST_USERID_CD='"+userName+"', LAST_UPDT_TM=utc_timestamp , RESOURSE_NAME='"+resourceName+"', RESOURCE_AUTHOR='"+resourceAuthor+"', RESOURCE_DURATION=0, DESC_TXT='"+resourceDesc+"', RESOURCE_TYP_ID=1, METADATA='"+resourceDesc+"', RESOURCE_URL='"+url+"', AUTHOR_IMG='"+authorImgUrl+"', THUMB_IMG='"+thumbUrl+"', DELETED_FL='0' WHERE ASSIGNMENT_ID = "+assignmentId+" AND STUDENT_ID='"+userName+"'";
                boolean updateStatus = deleteOrUpdateByQuery(updateQuery);
                System.out.println("Uploaded assignment status updated ? "+updateStatus);               
                
                //Create A feed | lms_feed_type = 2 | $ submitted an assignment $ for the module $ <- user,assignment,module
                String feedQuery = "INSERT INTO lms_feed_txn(FEED_TYPE_ID, REFRENCE_NM, USER_ID, COURSE_ID, RESOURCE_ID, ASSIGNMENT_ID, MODULE_ID, HRM_ID, LAST_USERID_CD, LAST_UPDT_TM) SELECT 2, 'assignment_upload', ucm.USER_ID,null, null,"+assignmentId+", (SELECT MODULE_ID FROM module_assignment_map where ASSIGNMENT_ID="+assignmentId+") as moduleId, ucm.HRM_ID,ulogin.USER_NM,current_timestamp FROM user_login ulogin inner join user_cls_map ucm on ucm.USER_ID=ulogin.USER_ID where ulogin.USER_NM='"+userName+"'";
                boolean feedStatus = deleteOrUpdateByQuery(feedQuery);
                System.out.println("Feed Creation ? "+feedStatus);                 

            }
            
            status=t;
        } catch (SQLException se) {
            System.out.println("uploadAssignment  # " + se);
            throw new LmsDaoException(se.getMessage());
        } catch (Exception e) {
            System.out.println("uploadAssignment  # " + e);
            throw new LmsDaoException(e.getMessage());
        } finally {
            closeResources(conn, stmt, resultSet);
        }
	        
	  return status;
	}

 

	 
}//End of class

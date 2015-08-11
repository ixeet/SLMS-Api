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
     //1 . jdbc code endd

        //4 Return as required by method
        return distList;

    }

	 
	@Override
	public int saveResourceProfile(String resourceprofileImgName,
			String resourceAuthor, String resourceImage,
			String lastUserIdCd, String descTxt ,String resourceName,String upLoadUrl, String resoUrl) throws LmsDaoException {
		Connection conn = null;
		   Statement stmt = null;
		   int status=0;
	        try {
	            conn = getConnection();
	            stmt = conn.createStatement();
	            String sql="";
	            
	            if(!resoUrl.equals("") && !upLoadUrl.equals("")){
	            	 sql = "INSERT INTO resourse_mstr(RESOURSE_ID, RESOURSE_NAME, RESOURCE_AUTHOR, RESOURCE_DURATION, DESC_TXT, RESOURCE_TYP_ID, METADATA, DELETED_FL, " +
	 	            		"DISPLAY_NO, ENABLE_FL, CREATED_BY, LAST_USERID_CD,  RESOURCE_URL, AUTHOR_IMG, THUMB_IMG) VALUES " +
	 	            		"((SELECT MAX( RESOURSE_ID ) FROM resourse_mstr r) +1, '"+resourceName+"', '"+resourceAuthor+"', 1, '"+descTxt+"',1, '', 0, 0, " +
	 	            				"'', '', '"+lastUserIdCd+"', '"+upLoadUrl+"', '', '')";
	            	 stmt.executeUpdate(sql);
	            	
		            	 sql = "INSERT INTO resourse_mstr(RESOURSE_ID, RESOURSE_NAME, RESOURCE_AUTHOR, RESOURCE_DURATION, DESC_TXT, RESOURCE_TYP_ID, METADATA, DELETED_FL, " +
		 	            		"DISPLAY_NO, ENABLE_FL, CREATED_BY, LAST_USERID_CD,  RESOURCE_URL, AUTHOR_IMG, THUMB_IMG) VALUES " +
		 	            		"((SELECT MAX( RESOURSE_ID ) FROM resourse_mstr r) +1, '"+resourceName+"', '"+resourceAuthor+"', 1, '"+descTxt+"',1, '', 0, 0, " +
		 	            				"'', '', '"+lastUserIdCd+"', '', '"+resourceprofileImgName+"', '')";
		            	 stmt.executeUpdate(sql);
	            }
	            
	            else if(resoUrl!="" && upLoadUrl.equals("")){
	            	 sql = "INSERT INTO resourse_mstr(RESOURSE_ID, RESOURSE_NAME, RESOURCE_AUTHOR, RESOURCE_DURATION, DESC_TXT, RESOURCE_TYP_ID, METADATA, DELETED_FL, " +
	 	            		"DISPLAY_NO, ENABLE_FL, CREATED_BY, LAST_USERID_CD,  RESOURCE_URL, AUTHOR_IMG, THUMB_IMG) VALUES " +
	 	            		"((SELECT MAX( RESOURSE_ID ) FROM resourse_mstr r) +1, '"+resourceName+"', '"+resourceAuthor+"', 1, '"+descTxt+"',1, '', 0, 0, " +
	 	            				"'', '', '"+lastUserIdCd+"', '', '"+resourceprofileImgName+"', '')";
	            	 stmt.executeUpdate(sql);
	            }
	            
	            else if(resoUrl.equals("") && upLoadUrl!= ""){
	            	 sql = "INSERT INTO resourse_mstr(RESOURSE_ID, RESOURSE_NAME, RESOURCE_AUTHOR, RESOURCE_DURATION, DESC_TXT, RESOURCE_TYP_ID, METADATA, DELETED_FL, " +
	 	            		"DISPLAY_NO, ENABLE_FL, CREATED_BY, LAST_USERID_CD,  RESOURCE_URL, AUTHOR_IMG, THUMB_IMG) VALUES " +
	 	            		"((SELECT MAX( RESOURSE_ID ) FROM resourse_mstr r) +1, '"+resourceName+"', '"+resourceAuthor+"', 1, '"+descTxt+"',1, '', 0, 0, " +
	 	            				"'', '', '"+lastUserIdCd+"', '"+upLoadUrl+"', '"+resourceprofileImgName+"', '')";
	            	 stmt.executeUpdate(sql);
	            }
	            
	           
	            
	            sql="SELECT MAX(RESOURSE_ID) FROM resourse_mstr";
	            
	            ResultSet re=stmt.executeQuery(sql);
	            re.last();
	             status  = re.getInt("MAX(RESOURSE_ID)");
	            
	            
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
	        
	        return status;
	}

 

	 
}

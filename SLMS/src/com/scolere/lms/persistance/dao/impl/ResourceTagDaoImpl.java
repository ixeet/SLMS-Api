/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scolere.lms.persistance.dao.impl;

import com.scolere.lms.domain.exception.LmsDaoException;
import com.scolere.lms.domain.vo.ResourceTagVo;
import com.scolere.lms.persistance.dao.iface.ResourceTagDao;
import com.scolere.lms.persistance.factory.LmsDaoAbstract;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class ResourceTagDaoImpl extends LmsDaoAbstract implements ResourceTagDao {

    public ResourceTagVo getResourceTagDetail(int id) throws LmsDaoException {
        System.out.println("Inside getResourceTagDetail(?) >>");
        //Create object to return
        ResourceTagVo userDtls = new ResourceTagVo();

        //1 . jdbc code start
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = getConnection();

            String sql = "SELECT * FROM RESOURSE_TAG where RESOURCE_TAG_ID=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, userDtls.getTagId());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                //3. Set db data to object

                userDtls.setTagId(rs.getInt("RESOURCE_TAG_ID"));
                userDtls.setResourceId(rs.getInt("RESOURCE_id"));
                userDtls.setTagNm(rs.getString("TAG_NM"));
                userDtls.setTagWtAdmin(rs.getInt("TAG_WT_USER"));
                userDtls.setTagWtUser(rs.getInt("TAG_WT_USER"));

                userDtls.setLastUserIdCd(rs.getString("LAST_USERID_CD"));
                userDtls.setLastupdtTm(rs.getString("LAST_UPDT_TM"));

            }

            System.out.println("get records into the table...");

        } catch (SQLException se) {
            System.out.println("getResourceTagDetail # " + se);
            se.printStackTrace();
        } catch (Exception e) {
            System.out.println("getResourceTagDetail # " + e);
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, null);
        }
        //1 . jdbc code endd

        //4 Return as required by method
        return userDtls;
    }

    public boolean updateResourceTagDetail(ResourceTagVo vo) throws LmsDaoException {
        System.out.println("id =" + vo.getResourceId());
        boolean status = true;

        //Database connection start
        Connection conn = null;
        PreparedStatement stmt = null;
        try {

            conn = getConnection();
            String sql = "UPDATE resourse_tag set  RESOURCE_ID=?,  TAG_NM=?, TAG_WT_ADMIN=?, TAG_WT_USER=?, LAST_USERID_CD=?, LAST_UPDT_TM=utc_timestamp\n"
                    + "    WHERE RESOURCE_TAG_ID=?";
            stmt = conn.prepareStatement(sql);

            stmt.setInt(1, vo.getResourceId());
            stmt.setString(2, vo.getTagNm());
            stmt.setInt(3, vo.getTagWtAdmin());
            stmt.setInt(4, vo.getTagWtUser());

            stmt.setString(5, vo.getLastUserIdCd());
            stmt.setInt(6, vo.getTagId());
            stmt.executeUpdate();
            System.out.println("updated records into the table...");

        } catch (SQLException e) {
            System.out.println("getResourceTagDetail # " + e);
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("getResourceTagDetail # " + e);
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, null);
        }

        System.out.println("Successfully updated....");
        return status;
        //End writting code to save into database   
    }
    //save method

    public void saveResourceTagDetail(ResourceTagVo vo) throws LmsDaoException {
        //Database connection start
        Connection conn = null;
        PreparedStatement stmt = null;
        try {

            conn = getConnection();
            String sql = "INSERT INTO resourse_tag(RESOURCE_TAG_ID, RESOURCE_ID, TAG_NM, TAG_WT_ADMIN, TAG_WT_USER, LAST_USERID_CD, LAST_UPDT_TM)  VALUES(?, ?, ?, ?, ?, ?,  utc_timestamp)";
            stmt = conn.prepareStatement(sql);

            stmt.setInt(1, vo.getTagId());
            stmt.setInt(2, vo.getResourceId());
            stmt.setString(3, vo.getTagNm());
            stmt.setInt(4, vo.getTagWtAdmin());
            stmt.setInt(5, vo.getTagWtUser());

            stmt.setString(6, vo.getLastUserIdCd());
            //...
            stmt.executeUpdate();
            System.out.println("Inserted records into the table...");

        } catch (SQLException se) {
            System.out.println("getResourceTagDetail # " + se);
            se.printStackTrace();
        } catch (Exception e) {
            System.out.println("getResourceTagDetail # " + e);
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, null);
        }

        System.out.println("Successfully saved....");
    }

    //delete method
    public boolean deleteResourceTagDetail(ResourceTagVo vo) throws LmsDaoException {
        System.out.println("id =" + vo.getResourceId());
        boolean status = true;

        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = getConnection();

            String sql = "DELETE FROM resourse_tag  WHERE RESOURCE_TAG_ID = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, vo.getResourceId());
            stmt.executeUpdate();

            System.out.println("Deleted records into the table...");

        } catch (SQLException se) {
            System.out.println("getResourceTagDetail # " + se);
            se.printStackTrace();
        } catch (Exception e) {
            System.out.println("getResourceTagDetail # " + e);
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, null);
        }

        System.out.println("Successfully deleted....");
        return status;
    }

    public List<ResourceTagVo> getResourceTagVoList() {

        List< ResourceTagVo> distList = new ArrayList<ResourceTagVo>();

        //1 . jdbc code start
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = getConnection();

            String sql = "SELECT * FROM resourse_tag ";
            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {

                //3. Set db data to object
                ResourceTagVo userDtls = new ResourceTagVo();

                userDtls.setTagId(rs.getInt("RESOURCE_TAG_ID"));
                userDtls.setResourceId(rs.getInt("RESOURCE_id"));
                userDtls.setTagNm(rs.getString("TAG_NM"));
                userDtls.setTagWtAdmin(rs.getInt("TAG_WT_USER"));
                userDtls.setTagWtUser(rs.getInt("TAG_WT_USER"));

                userDtls.setLastUserIdCd(rs.getString("LAST_USERID_CD"));
                userDtls.setLastupdtTm(rs.getString("LAST_UPDT_TM"));

                //Add into list
                distList.add(userDtls);
            }

            System.out.println("get records into the table...");

        } catch (SQLException se) {
            System.out.println("getUserLoginDetail # " + se);
            se.printStackTrace();
        } catch (Exception e) {
            System.out.println("getUserLoginDetail # " + e);
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, null);
        }
        //1 . jdbc code endd

        //4 Return as required by method
        return distList;

    }

    @Override
    public List<ResourceTagVo> getResourceTagList() throws LmsDaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

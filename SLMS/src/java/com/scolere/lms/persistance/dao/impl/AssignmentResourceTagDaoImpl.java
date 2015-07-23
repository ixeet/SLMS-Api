package com.scolere.lms.persistance.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.scolere.lms.domain.exception.LmsDaoException;
import com.scolere.lms.domain.vo.AssignmentResourceTagVO;
import com.scolere.lms.persistance.dao.iface.AssignmentResourceTagDao;
import com.scolere.lms.persistance.factory.LmsDaoAbstract;

public class AssignmentResourceTagDaoImpl extends LmsDaoAbstract implements AssignmentResourceTagDao{

	@Override
	public boolean updateAssignmentResourceTag(AssignmentResourceTagVO vo) throws LmsDaoException {
		System.out.println("id =" + vo.getResourceTagID());
        boolean status = true;

        //Database connection start
        Connection conn = null;
        PreparedStatement stmt = null;
        try {

            conn = getConnection();
            String sql = "UPDATE assignment_resource_tag set RESOURCE_TXN_ID=?, TAG_NAME=?, TAG_WT=?, LAST_USERID_CD=?, LAST_UPDT_TM=current_timestamp\n"
                    + "    WHERE RESOURCE_TAG_ID=?";
            stmt = conn.prepareStatement(sql);

            stmt.setInt(1, vo.getResourceTxnID());
            stmt.setString(2, vo.getTagName());
            stmt.setInt(3, vo.getTagWt());
            stmt.setString(4, vo.getLastUserIDCd());
            stmt.setInt(5, vo.getResourceTagID());

            stmt.executeUpdate();
            System.out.println("updated records into the table...");

        } catch (SQLException e) {
            System.out.println("getResourceTagID 1# " + e);
            throw new LmsDaoException(e.getMessage());
        } catch (Exception e) {
            System.out.println("getResourceTagID 2# " + e);
            throw new LmsDaoException(e.getMessage());
        } finally {
            closeResources(conn, stmt, null);
        }

        System.out.println("Successfully updated....");
        return status;
	}

	@Override
	public void saveAssignmentResourceTag(AssignmentResourceTagVO vo) throws LmsDaoException {
		//Database connection start
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
        	
            conn = getConnection();
            String sql = "INSERT INTO assignment_resource_tag(RESOURCE_TAG_ID, RESOURCE_TXN_ID, TAG_NAME, TAG_WT, " +
            		"LAST_USERID_CD, LAST_UPDT_TM)  VALUES(?, ?, ?, ?, ?, current_timestamp)";
           
            stmt = conn.prepareStatement(sql);
            
            stmt.setInt(1, vo.getResourceTagID());
            stmt.setInt(2, vo.getResourceTxnID());
            stmt.setString(3, vo.getTagName());
            stmt.setInt(4, vo.getTagWt());
            stmt.setString(5, vo.getLastUserIDCd());
           

            
            //...
            stmt.executeUpdate();
            System.out.println("Inserted records into the table...");

        } catch (SQLException se) {
            System.out.println("AssignmentResourceTag # " + se);
            throw new LmsDaoException(se.getMessage());
        } catch (Exception e) {
            System.out.println("AssignmentResourceTag # " + e);
            throw new LmsDaoException(e.getMessage());
        } finally {
            closeResources(conn, stmt, null);
        }

        System.out.println("Successfully saved....");
	}

	@Override
	public boolean deleteAssignmentResourceTag(AssignmentResourceTagVO vo) throws LmsDaoException {
		System.out.println("id =" + vo.getResourceTagID());
        boolean status = true;
        
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = getConnection();

            String sql = "DELETE FROM assignment_resource_tag WHERE RESOURCE_TAG_ID = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, vo.getResourceTagID());
            stmt.executeUpdate();

            System.out.println("Deleted records into the table...");

        } catch (SQLException se) {
            System.out.println("assignment_resource_tag # " + se);
            throw new LmsDaoException(se.getMessage());
        } catch (Exception e) {
            System.out.println("assignment_resource_tag # " + e);
            throw new LmsDaoException(e.getMessage());
        } finally {
            closeResources(conn, stmt, null);
        }

        System.out.println("Successfully deleted....");
        return status;
	}

	@Override
	public AssignmentResourceTagVO getAssignmentResourceTag(int id) throws LmsDaoException {
		
		System.out.println("Inside getAssignmentResourceTag(?) >>");
        //Create object to return
		AssignmentResourceTagVO assignmentResourceTagVO = new AssignmentResourceTagVO();

        //1 . jdbc code start
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = getConnection();

            String sql = "SELECT * FROM assignment_resource_tag where STUDENT_DTLS_ID=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, assignmentResourceTagVO.getResourceTagID());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                //3. Set db data to object

            	assignmentResourceTagVO.setResourceTagID(rs.getInt("RESOURCE_TAG_ID"));
            	assignmentResourceTagVO.setResourceTxnID(rs.getInt("RESOURCE_TXN_ID"));
            	assignmentResourceTagVO.setTagName(rs.getString("TAG_NAME"));
            	assignmentResourceTagVO.setTagWt(rs.getInt("TAG_WT"));
            	assignmentResourceTagVO.setLastUserIDCd(rs.getString("LAST_USERID_CD"));
            	assignmentResourceTagVO.setLastUpdtTm(rs.getString("LAST_UPDT_TM"));

            }

            System.out.println("get records into the table...");

        } catch (SQLException se) {
            System.out.println("getAssignmentResourceTag # " + se);
            throw new LmsDaoException(se.getMessage());
        } catch (Exception e) {
            System.out.println("getAssignmentResourceTag # " + e);
            throw new LmsDaoException(e.getMessage());
        } finally {
            closeResources(conn, stmt, null);
        }
     //1 . jdbc code endd

        //4 Return as required by method
        return assignmentResourceTagVO;
	}

	@Override
	public List<AssignmentResourceTagVO> getAssignmentResourceTagList() throws LmsDaoException {
		
		 List< AssignmentResourceTagVO> distList = new ArrayList<AssignmentResourceTagVO>();

	        //1 . jdbc code start
	        Connection conn = null;
	        PreparedStatement stmt = null;
	        try {
	            conn = getConnection();

	            String sql = "SELECT * FROM assignment_resource_tag ";
	            stmt = conn.prepareStatement(sql);
	            ResultSet rs = stmt.executeQuery();
	            while (rs.next()) {
	            	//3. Set db data to object
	            	AssignmentResourceTagVO assignmentResourceTagVO = new AssignmentResourceTagVO();

	            	assignmentResourceTagVO.setResourceTagID(rs.getInt("RESOURCE_TAG_ID"));
	            	assignmentResourceTagVO.setResourceTxnID(rs.getInt("RESOURCE_TXN_ID"));
	            	assignmentResourceTagVO.setTagName(rs.getString("TAG_NAME"));
	            	assignmentResourceTagVO.setTagWt(rs.getInt("TAG_WT"));
	            	assignmentResourceTagVO.setLastUserIDCd(rs.getString("LAST_USERID_CD"));
	            	assignmentResourceTagVO.setLastUpdtTm(rs.getString("LAST_UPDT_TM"));


	                //Add into list
	                distList.add(assignmentResourceTagVO);
	            }

	            System.out.println("get records into the list...");

	        } catch (SQLException se) {
	            System.out.println("assignmentResourceTag # " + se);
	            throw new LmsDaoException(se.getMessage());
	        } catch (Exception e) {
	            System.out.println("assignmentResourceTag # " + e);
	            throw new LmsDaoException(e.getMessage());
	        } finally {
	            closeResources(conn, stmt, null);
	        }
	     //1 . jdbc code endd

	        //4 Return as required by method
	        return distList;
	}

}

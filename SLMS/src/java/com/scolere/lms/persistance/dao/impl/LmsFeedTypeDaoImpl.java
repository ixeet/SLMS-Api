package com.scolere.lms.persistance.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.scolere.lms.domain.exception.LmsDaoException;
import com.scolere.lms.domain.vo.LmsFeedTypeVO;
import com.scolere.lms.persistance.dao.iface.LmsFeedTypeDao;
import com.scolere.lms.persistance.factory.LmsDaoAbstract;

public class LmsFeedTypeDaoImpl extends LmsDaoAbstract implements LmsFeedTypeDao{

	@Override
	public boolean updateLmsFeedType(LmsFeedTypeVO vo) throws LmsDaoException {
		System.out.println("id =" + vo.getFeedTypeID());
		 //System.out.println("id =" +1234);
		 
		 boolean status = true;

	        //Database connection start
	        Connection conn = null;
	        PreparedStatement stmt = null;
	        try {
	        	
	            conn = getConnection();
	            String sql = "UPDATE lms_feed_type set FEED_TYPE_NM=?, DISPLAY_NO=?, ENABLE_FL=?, LAST_USERID_CD=?, LAST_UPDT_TM=current_timestamp\n"
	                    + "    WHERE FEED_TYPE_ID=?";
	           /* String sql = "UPDATE lms_feed_type set FEED_TYPE_NM=?, DISPLAY_NO=?, ENABLE_FL=?, LAST_USERID_CD=?\n"
	                    + "    WHERE FEED_TYPE_ID=?";*/
	            stmt = conn.prepareStatement(sql);

	            
	            stmt.setString(1, vo.getFeedTypeNm());
	            stmt.setInt(2, vo.getDisplayNo());
	            stmt.setString(3, vo.getEnableFl());
	            stmt.setString(4, vo.getLastUserIDCd());
	            stmt.setInt(5, vo.getFeedTypeID());
	           // stmt.setString(7, vo.getLastUpdtTm());
	            
	            stmt.executeUpdate();
	            System.out.println("updated records into the table...");

	        } catch (SQLException e) {
	            System.out.println("getFeedTypeNm SQLException1# " + e);
	            throw new LmsDaoException(e.getMessage());
	        } catch (Exception e) {
	            System.out.println("getFeedTypeNm Exception 2# " + e);
	            throw new LmsDaoException(e.getMessage());
	        } finally {
	            closeResources(conn, stmt, null);
	        }

	        System.out.println("Successfully updated....");
	        return status;
	}

	@Override
	public void saveLmsFeedType(LmsFeedTypeVO vo) throws LmsDaoException {
		//Database connection start
        Connection conn = null;
        PreparedStatement stmt = null;
        try {

            conn = getConnection();
            //String sql = "INSERT INTO lms_feed_type(FEED_TYPE_ID, FEED_TYPE_NM, DISPLAY_NO, ENABLE_FL, LAST_USERID_CD)  VALUES(?, ?, ?, ?, ?)";
            String sql = "INSERT INTO lms_feed_type(FEED_TYPE_ID, FEED_TYPE_NM, DISPLAY_NO, ENABLE_FL, LAST_USERID_CD, LAST_UPDT_TM)  VALUES(?, ?, ?, ?, ?, current_timestamp)";
                        
            stmt = conn.prepareStatement(sql);
            
            stmt.setInt(1, vo.getFeedTypeID());
            stmt.setString(2, vo.getFeedTypeNm());
            stmt.setInt(3, vo.getDisplayNo());
            stmt.setString(4, vo.getEnableFl());
            stmt.setString(5, vo.getLastUserIDCd());
          /*  stmt.setString(8, vo.getLastUpdtTm());*/
          
            //...
            stmt.executeUpdate();
            System.out.println("Inserted records into the table...");

        } catch (SQLException se) {
            System.out.println("getFeedTypeID # " + se);
            throw new LmsDaoException(se.getMessage());
        } catch (Exception e) {
            System.out.println("getFeedTypeID # " + e);
            throw new LmsDaoException(e.getMessage());
        } finally {
            closeResources(conn, stmt, null);
        }

        System.out.println("Successfully saved....");
	}

	@Override
	public boolean deleteLmsFeedType(LmsFeedTypeVO vo) throws LmsDaoException {
		System.out.println("id =" + vo.getFeedTypeID());
        boolean status = true;

        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = getConnection();
            
            String sql = "DELETE FROM lms_feed_type WHERE FEED_TYPE_ID = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, vo.getFeedTypeID());
            stmt.executeUpdate();

            System.out.println("Deleted records into the FEED_TYPE table...");

        } catch (SQLException se) {
            System.out.println("FEED_TYPE # " + se);
            throw new LmsDaoException(se.getMessage());
        } catch (Exception e) {
            System.out.println("FEED_TYPE # " + e);
            throw new LmsDaoException(e.getMessage());
        } finally {
            closeResources(conn, stmt, null);
        }

        System.out.println("Successfully deleted....");
        return status;
	}

	@Override
	public LmsFeedTypeVO getLmsFeedType(int id) throws LmsDaoException {
		
		System.out.println("Inside getLmsFeedType(?) >>");
        //Create object to return
		LmsFeedTypeVO lmsFeedTypeVO = new LmsFeedTypeVO();

        //1 . jdbc code start
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = getConnection();

            String sql = "SELECT * FROM lms_feed_type where FEED_TYPE_ID=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, lmsFeedTypeVO.getFeedTypeID());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                //3. Set db data to object
            	lmsFeedTypeVO.setFeedTypeID(rs.getInt("FEED_TYPE_ID"));
            	lmsFeedTypeVO.setFeedTypeNm(rs.getString("FEED_TYPE_NM"));
            	lmsFeedTypeVO.setDisplayNo(rs.getInt("DISPLAY_NO"));
            	lmsFeedTypeVO.setEnableFl(rs.getString("ENABLE_FL"));
            	lmsFeedTypeVO.setLastUserIDCd(rs.getString("LAST_USERID_CD"));
            	lmsFeedTypeVO.setLastUpdtTm(rs.getString("LAST_UPDT_TM"));       
            }

            System.out.println("get records into the table...");

        } catch (SQLException se) {
            System.out.println("getLmsFeedType # " + se);
            throw new LmsDaoException(se.getMessage());
        } catch (Exception e) {
            System.out.println("getLmsFeedType # " + e);
            throw new LmsDaoException(e.getMessage());
        } finally {
            closeResources(conn, stmt, null);
        }
     //1 . jdbc code endd

        //4 Return as required by method
        return lmsFeedTypeVO;
	}

	@Override
	public List<LmsFeedTypeVO> getLmsFeedTypeList() throws LmsDaoException {
		 List< LmsFeedTypeVO> distList = new ArrayList<LmsFeedTypeVO>();

	        //1 . jdbc code start
	        Connection conn = null;
	        PreparedStatement stmt = null;
	        try {
	            conn = getConnection();
	            
	            String sql = "SELECT * FROM lms_feed_type ";
	            stmt = conn.prepareStatement(sql);
	            ResultSet rs = stmt.executeQuery();
	            while (rs.next()) {

	                //3. Set db data to object
	            	LmsFeedTypeVO lmsFeedTypeVO = new LmsFeedTypeVO();

	            	lmsFeedTypeVO.setFeedTypeID(rs.getInt("FEED_TYPE_ID"));
	            	lmsFeedTypeVO.setFeedTypeNm(rs.getString("FEED_TYPE_NM"));
	            	lmsFeedTypeVO.setDisplayNo(rs.getInt("DISPLAY_NO"));
	            	lmsFeedTypeVO.setEnableFl(rs.getString("ENABLE_FL"));
	            	lmsFeedTypeVO.setLastUserIDCd(rs.getString("LAST_USERID_CD"));
	            	lmsFeedTypeVO.setLastUpdtTm(rs.getString("LAST_UPDT_TM"));


	                //Add into list
	                distList.add(lmsFeedTypeVO);
	            }

	            System.out.println("get records into the list...");

	        } catch (SQLException se) {
	            System.out.println("lmsFeedType # " + se);
	            throw new LmsDaoException(se.getMessage());
	        } catch (Exception e) {
	            System.out.println("lmsFeedType # " + e);
	            throw new LmsDaoException(e.getMessage());
	        } finally {
	            closeResources(conn, stmt, null);
	        }
	     //1 . jdbc code endd

	        //4 Return as required by method
	        return distList;
	}

}

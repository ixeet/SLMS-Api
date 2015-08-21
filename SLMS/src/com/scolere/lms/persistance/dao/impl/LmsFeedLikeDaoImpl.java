package com.scolere.lms.persistance.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.scolere.lms.domain.exception.LmsDaoException;
import com.scolere.lms.domain.vo.LmsFeedLikeVO;
import com.scolere.lms.persistance.dao.iface.LmsFeedLikeDao;
import com.scolere.lms.persistance.factory.LmsDaoAbstract;

public class LmsFeedLikeDaoImpl extends LmsDaoAbstract implements LmsFeedLikeDao {

	@Override
	public boolean updateLmsFeedLike(LmsFeedLikeVO vo) throws LmsDaoException {
		System.out.println("id =" + vo.getFeedLikeID());
		 //System.out.println("id =" +1234);
		
		 boolean status = true;

	        //Database connection start
	        Connection conn = null;
	        PreparedStatement stmt = null;
	        try {

	            conn = getConnection();
	            String sql = "UPDATE lms_feed_like set FEED_ID=?, PARENT_COMMENT_ID=?, LIKE_ON=?, ASSOCIATE_ID=?, " +
	            		"LIKE_BY=?, LAST_USERID_CD=?, LAST_UPDT_TM=current_timestamp\n"
	                    + "    WHERE FEED_LIKE_ID=?";
	            /*String sql = "UPDATE lms_feed_like set FEED_ID=?, PARENT_COMMENT_ID=?, LIKE_ON=?, ASSOCIATE_ID=?, LIKE_BY=?, LAST_USERID_CD=?\n"
	                    + "    WHERE FEED_LIKE_ID=?";*/
	            stmt = conn.prepareStatement(sql);
	           	           
	            stmt.setInt(1, vo.getFeedID());
	            stmt.setInt(2, vo.getParentCommentID());
	            stmt.setString(3, vo.getLikeOn());
	            stmt.setInt(4, vo.getAssociateID());
	            stmt.setString(5, vo.getLikeBy());
	            stmt.setString(6, vo.getLastUserIDCd());
	            stmt.setInt(7, vo.getFeedLikeID());
	           // stmt.setString(7, vo.getLastUpdtTm());
	            
	            stmt.executeUpdate();
	            System.out.println("updated records into the table...");

	        } catch (SQLException e) {
	            System.out.println("LmsFeedLike SQLException1# " + e);
	            throw new LmsDaoException(e.getMessage());
	        } catch (Exception e) {
	            System.out.println("LmsFeedLike Exception 2# " + e);
	            throw new LmsDaoException(e.getMessage());
	        } finally {
	            closeResources(conn, stmt, null);
	        }

	        System.out.println("Successfully updated....");
	        return status;
	}

	@Override
	public void saveLmsFeedLike(LmsFeedLikeVO vo) throws LmsDaoException {
		//Database connection start
        Connection conn = null;
        PreparedStatement stmt = null;
        try {

            conn = getConnection();
            /*String sql = "INSERT INTO lms_feed_like(FEED_LIKE_ID, FEED_ID, PARENT_COMMENT_ID, LIKE_ON, ASSOCIATE_ID, " +
            		"LIKE_BY, LAST_USERID_CD)  VALUES(?, ?, ?, ?, ?, ?, ?)";*/
            String sql = "INSERT INTO lms_feed_like(FEED_LIKE_ID, FEED_ID, PARENT_COMMENT_ID, LIKE_ON, ASSOCIATE_ID, " +
            		"LIKE_BY, LAST_USERID_CD, LAST_UPDT_TM)  VALUES(?, ?, ?, ?, ?, ?, ?, current_timestamp)";
           
            
            stmt = conn.prepareStatement(sql);
            
            stmt.setInt(1, vo.getFeedLikeID());
            stmt.setInt(2, vo.getFeedID());
            stmt.setInt(3, vo.getParentCommentID());
            stmt.setString(4, vo.getLikeOn());
            stmt.setInt(5, vo.getAssociateID());
            stmt.setString(6, vo.getLikeBy());
            stmt.setString(7, vo.getLastUserIDCd());
          
          
            //...
            stmt.executeUpdate();
            System.out.println("Inserted records into the table...");

        } catch (SQLException se) {
            System.out.println("lms_feed_like # " + se);
            throw new LmsDaoException(se.getMessage());
        } catch (Exception e) {
            System.out.println("lms_feed_like # " + e);
            throw new LmsDaoException(e.getMessage());
        } finally {
            closeResources(conn, stmt, null);
        }

        System.out.println("Successfully saved....");
		
	}

	@Override
	public boolean deleteLmsFeedLike(LmsFeedLikeVO vo) throws LmsDaoException {
		System.out.println("id =" + vo.getFeedLikeID());
        boolean status = true;

        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = getConnection();
            
            String sql = "DELETE FROM lms_feed_like WHERE FEED_LIKE_ID = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, vo.getFeedLikeID());
            stmt.executeUpdate();

            System.out.println("Deleted records into the FEED_LIKE_ID table...");

        } catch (SQLException se) {
            System.out.println("LmsFeedLike # " + se);
            throw new LmsDaoException(se.getMessage());
        } catch (Exception e) {
            System.out.println("LmsFeedLike # " + e);
            throw new LmsDaoException(e.getMessage());
        } finally {
            closeResources(conn, stmt, null);
        }

        System.out.println("Successfully deleted....");
        return status;
	}

	@Override
	public LmsFeedLikeVO getLmsFeedLike(int id) throws LmsDaoException {
		
		System.out.println("Inside getLmsFeedLikeVO(?) >>");
        //Create object to return
		LmsFeedLikeVO lmsFeedLikeVO = new LmsFeedLikeVO();

        //1 . jdbc code start
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = getConnection();
            
            String sql = "SELECT * FROM lms_feed_like where FEED_LIKE_ID=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, lmsFeedLikeVO.getFeedLikeID());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                //3. Set db data to object
            	lmsFeedLikeVO.setFeedLikeID(rs.getInt("FEED_LIKE_ID"));
            	lmsFeedLikeVO.setFeedID(rs.getInt("FEED_ID"));
            	lmsFeedLikeVO.setParentCommentID(rs.getInt("PARENT_COMMENT_ID"));
            	lmsFeedLikeVO.setLikeOn(rs.getString("LIKE_ON"));
            	lmsFeedLikeVO.setAssociateID(rs.getInt("ASSOCIATE_ID"));
            	lmsFeedLikeVO.setLikeBy(rs.getString("LIKE_BY"));
            	lmsFeedLikeVO.setLastUserIDCd(rs.getString("LAST_USERID_CD"));
            	lmsFeedLikeVO.setLastUpdtTm(rs.getString("LAST_UPDT_TM"));       
            }

            System.out.println("get records into the table...");

        } catch (SQLException se) {
            System.out.println("lmsFeedLikeVO # " + se);
            throw new LmsDaoException(se.getMessage());
        } catch (Exception e) {
            System.out.println("lmsFeedLikeVO # " + e);
            throw new LmsDaoException(e.getMessage());
        } finally {
            closeResources(conn, stmt, null);
        }
     //1 . jdbc code endd

        //4 Return as required by method
        return lmsFeedLikeVO;
	}

	@Override
	public List<LmsFeedLikeVO> getLmsFeedLikeList() throws LmsDaoException {
		
		List< LmsFeedLikeVO> distList = new ArrayList<LmsFeedLikeVO>();

        //1 . jdbc code start
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = getConnection();

            String sql = "SELECT * FROM lms_feed_like ";
            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {

                //3. Set db data to object
            	LmsFeedLikeVO lmsFeedLikeVO = new LmsFeedLikeVO();
            	
            	lmsFeedLikeVO.setFeedLikeID(rs.getInt("FEED_LIKE_ID"));
            	lmsFeedLikeVO.setFeedID(rs.getInt("FEED_ID"));
            	lmsFeedLikeVO.setParentCommentID(rs.getInt("PARENT_COMMENT_ID"));
            	lmsFeedLikeVO.setLikeOn(rs.getString("LIKE_ON"));
            	lmsFeedLikeVO.setAssociateID(rs.getInt("ASSOCIATE_ID"));
            	lmsFeedLikeVO.setLikeBy(rs.getString("LIKE_BY"));
            	lmsFeedLikeVO.setLastUserIDCd(rs.getString("LAST_USERID_CD"));
            	lmsFeedLikeVO.setLastUpdtTm(rs.getString("LAST_UPDT_TM"));


                //Add into list
                distList.add(lmsFeedLikeVO);
            }

            System.out.println("get records into the list...");

        } catch (SQLException se) {
            System.out.println("lms_feed_like # " + se);
            throw new LmsDaoException(se.getMessage());
        } catch (Exception e) {
            System.out.println("lms_feed_like # " + e);
            throw new LmsDaoException(e.getMessage());
        } finally {
            closeResources(conn, stmt, null);
        }
     //1 . jdbc code endd

        //4 Return as required by method
        return distList;
	}

	

}

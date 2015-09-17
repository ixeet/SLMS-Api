package com.scolere.lms.persistance.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.scolere.lms.domain.exception.LmsDaoException;
import com.scolere.lms.domain.vo.LmsFeedLikesVO;
import com.scolere.lms.persistance.dao.iface.LmsFeedLikesDao;
import com.scolere.lms.persistance.factory.LmsDaoAbstract;

public class LmsFeedLikesDaoImpl extends LmsDaoAbstract implements LmsFeedLikesDao{

	@Override
	public boolean updateLmsFeedLikes(LmsFeedLikesVO vo) throws LmsDaoException {
		System.out.println("id =" + vo.getFeedLikeID());
		 //System.out.println("id =" +1234);
		 
		 boolean status = true;

	        //Database connection start
	        Connection conn = null;
	        PreparedStatement stmt = null;
	        try {
	        	
	            conn = getConnection();
	            String sql = "UPDATE lms_feed_likes set FEED_ID=?, PARENT_COMMENT_ID=?, LIKE_ON=?, ASSOCIATE_ID=?, " +
	            		"LIKE_BY=?, LAST_USERID_CD=?, LAST_UPDT_TM=utc_timestamp\n"
	                    + "    WHERE FEED_LIKE_ID=?";
	           /* String sql = "UPDATE lms_feed_likes set FEED_ID=?, PARENT_COMMENT_ID=?, LIKE_ON=?, ASSOCIATE_ID=?," +
	            		" LIKE_BY=?, LAST_USERID_CD=?\n"
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
	            System.out.println("getFeedID SQLException1# " + e);
	            throw new LmsDaoException(e.getMessage());
	        } catch (Exception e) {
	            System.out.println("getFeedID Exception 2# " + e);
	            throw new LmsDaoException(e.getMessage());
	        } finally {
	            closeResources(conn, stmt, null);
	        }

	        System.out.println("Successfully updated....");
	        return status;
	}

	@Override
	public void saveLmsFeedLikes(LmsFeedLikesVO vo) throws LmsDaoException {
		//Database connection start
        Connection conn = null;
        PreparedStatement stmt = null;
        try {

            conn = getConnection();
            String sql = "INSERT INTO lms_feed_likes(FEED_LIKE_ID, FEED_ID, PARENT_COMMENT_ID, LIKE_ON," +
            		" ASSOCIATE_ID, LIKE_BY, LAST_USERID_CD, LAST_UPDT_TM)  VALUES(?, ?, ?, ?, ?, ?, ?, utc_timestamp)";
                        
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
            System.out.println("getFeedLike # " + se);
            throw new LmsDaoException(se.getMessage());
        } catch (Exception e) {
            System.out.println("getFeedLike # " + e);
            throw new LmsDaoException(e.getMessage());
        } finally {
            closeResources(conn, stmt, null);
        }

        System.out.println("Successfully saved....");
	}

	@Override
	public boolean deleteLmsFeedLikes(LmsFeedLikesVO vo) throws LmsDaoException {
		System.out.println("id =" + vo.getFeedLikeID());
        boolean status = true;

        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = getConnection();
          
            String sql = "DELETE FROM lms_feed_likes WHERE FEED_LIKE_ID = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, vo.getFeedLikeID());
            stmt.executeUpdate();

            System.out.println("Deleted records into the FEED_LIKE table...");

        } catch (SQLException se) {
            System.out.println("FEED_LIKE # " + se);
            throw new LmsDaoException(se.getMessage());
        } catch (Exception e) {
            System.out.println("FEED_LIKE # " + e);
            throw new LmsDaoException(e.getMessage());
        } finally {
            closeResources(conn, stmt, null);
        }

        System.out.println("Successfully deleted....");
        return status;
	}

	@Override
	public LmsFeedLikesVO getLmsFeedLikes(int id) throws LmsDaoException {
		
		System.out.println("Inside getLmsFeedLikes(?) >>");
        //Create object to return
		LmsFeedLikesVO lmsFeedLikesVO = new LmsFeedLikesVO();

        //1 . jdbc code start
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = getConnection();

            String sql = "SELECT * FROM lms_feed_likes where FEED_LIKE_ID=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, lmsFeedLikesVO.getFeedLikeID());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                //3. Set db data to object
            	lmsFeedLikesVO.setFeedLikeID(rs.getInt("FEED_LIKE_ID"));
            	lmsFeedLikesVO.setFeedID(rs.getInt("FEED_ID"));
            	lmsFeedLikesVO.setParentCommentID(rs.getInt("PARENT_COMMENT_ID"));
            	lmsFeedLikesVO.setLikeOn(rs.getString("LIKE_ON"));
            	lmsFeedLikesVO.setAssociateID(rs.getInt("ASSOCIATE_ID"));
            	lmsFeedLikesVO.setLikeBy(rs.getString("LIKE_BY"));
            	lmsFeedLikesVO.setLastUserIDCd(rs.getString("LAST_USERID_CD"));
            	lmsFeedLikesVO.setLastUpdtTm(rs.getString("LAST_UPDT_TM"));       
            }

            System.out.println("get records into the table...");

        } catch (SQLException se) {
            System.out.println("getLmsFeedLikes # " + se);
            throw new LmsDaoException(se.getMessage());
        } catch (Exception e) {
            System.out.println("getLmsFeedLikes # " + e);
            throw new LmsDaoException(e.getMessage());
        } finally {
            closeResources(conn, stmt, null);
        }
     //1 . jdbc code endd

        //4 Return as required by method
        return lmsFeedLikesVO;
		
	}

	@Override
	public List<LmsFeedLikesVO> getLmsFeedLikesList() throws LmsDaoException {
		 List< LmsFeedLikesVO> distList = new ArrayList<LmsFeedLikesVO>();

	        //1 . jdbc code start
	        Connection conn = null;
	        PreparedStatement stmt = null;
	        try {
	            conn = getConnection();

	            String sql = "SELECT * FROM lms_feed_likes ";
	            stmt = conn.prepareStatement(sql);
	            ResultSet rs = stmt.executeQuery();
	            while (rs.next()) {
	            	
	                //3. Set db data to object
	            	LmsFeedLikesVO lmsFeedLikesVO = new LmsFeedLikesVO();

	            	lmsFeedLikesVO.setFeedLikeID(rs.getInt("FEED_LIKE_ID"));
	            	lmsFeedLikesVO.setFeedID(rs.getInt("FEED_ID"));
	            	lmsFeedLikesVO.setParentCommentID(rs.getInt("PARENT_COMMENT_ID"));
	            	lmsFeedLikesVO.setLikeOn(rs.getString("LIKE_ON"));
	            	lmsFeedLikesVO.setAssociateID(rs.getInt("ASSOCIATE_ID"));
	            	lmsFeedLikesVO.setLikeBy(rs.getString("LIKE_BY"));
	            	lmsFeedLikesVO.setLastUserIDCd(rs.getString("LAST_USERID_CD"));
	            	lmsFeedLikesVO.setLastUpdtTm(rs.getString("LAST_UPDT_TM"));


	                //Add into list
	                distList.add(lmsFeedLikesVO);
	            }

	            System.out.println("get records into the List...");

	        } catch (SQLException se) {
	            System.out.println("assignment # " + se);
	            throw new LmsDaoException(se.getMessage());
	        } catch (Exception e) {
	            System.out.println("assignment # " + e);
	            throw new LmsDaoException(e.getMessage());
	        } finally {
	            closeResources(conn, stmt, null);
	        }
	     //1 . jdbc code endd

	        //4 Return as required by method
	        return distList;
	}

}

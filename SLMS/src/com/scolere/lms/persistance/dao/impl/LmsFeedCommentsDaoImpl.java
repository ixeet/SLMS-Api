package com.scolere.lms.persistance.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.scolere.lms.domain.exception.LmsDaoException;
import com.scolere.lms.domain.vo.LmsFeedCommentsVO;
import com.scolere.lms.persistance.dao.iface.LmsFeedCommentsDao;
import com.scolere.lms.persistance.factory.LmsDaoAbstract;

public class LmsFeedCommentsDaoImpl extends LmsDaoAbstract implements LmsFeedCommentsDao{

	@Override
	public boolean updateLmsFeedComments(LmsFeedCommentsVO vo)
			throws LmsDaoException {
		System.out.println("id =" + vo.getFeedCommentID());
		 //System.out.println("id =" +1234);
		 
		 boolean status = true;

	        //Database connection start
	        Connection conn = null;
	        PreparedStatement stmt = null;
	        try {
	        	
	            conn = getConnection();
	            String sql = "UPDATE assignment set FEED_ID=?, COMMENT_TXT=?, PARENT_COMMENT_ID=?, ASSOCIATE_ID=?," +
	            		"COMMENTED_BY=?, LAST_USERID_CD=?, LAST_UPDT_TM=current_timestamp\n"
	                    + "    WHERE FEED_COMMENT_ID=?";
	           /* String sql = "UPDATE lms_feed_comments set FEED_ID=?, COMMENT_TXT=?, PARENT_COMMENT_ID=?," +
	            		" ASSOCIATE_ID=?, COMMENTED_BY=?, LAST_USERID_CD=?\n"
	                    + "    WHERE FEED_COMMENT_ID=?";*/
	            stmt = conn.prepareStatement(sql);

	            
	            stmt.setInt(1, vo.getFeedID());
	            stmt.setString(2, vo.getCommentTxt());
	            stmt.setString(3, vo.getParentCommentID());
	            stmt.setInt(4, vo.getAssociateID());
	            stmt.setString(5, vo.getCommentedBy());
	            stmt.setString(6, vo.getLastUserIDCd());
	            stmt.setInt(7, vo.getFeedCommentID());
	           // stmt.setString(7, vo.getLastUpdtTm());
	            
	            stmt.executeUpdate();
	            System.out.println("updated records into the table...");

	        } catch (SQLException e) {
	            System.out.println("LmsFeedComments SQLException1# " + e);
	            throw new LmsDaoException(e.getMessage());
	        } catch (Exception e) {
	            System.out.println("LmsFeedComments Exception 2# " + e);
	            throw new LmsDaoException(e.getMessage());
	        } finally {
	            closeResources(conn, stmt, null);
	        }

	        System.out.println("Successfully updated....");
	        return status;
	}

	@Override
	public void saveLmsFeedComments(LmsFeedCommentsVO vo) throws LmsDaoException {
		
		//Database connection start
        Connection conn = null;
        PreparedStatement stmt = null;
        try {

            conn = getConnection();
            /*String sql = "INSERT INTO lms_feed_comments(FEED_COMMENT_ID, FEED_ID, COMMENT_TXT, PARENT_COMMENT_ID, " +
            		"ASSOCIATE_ID, COMMENTED_BY, LAST_USERID_CD)  VALUES(?, ?, ?, ?, ?, ?, ?)";*/
            String sql = "INSERT INTO lms_feed_comments(FEED_COMMENT_ID, FEED_ID, COMMENT_TXT, PARENT_COMMENT_ID, " +
            		"ASSOCIATE_ID, COMMENTED_BY, LAST_USERID_CD, LAST_UPDT_TM)  VALUES(?, ?, ?, ?, ?, ?, ?, current_timestamp)";

            
            stmt = conn.prepareStatement(sql);
            
            stmt.setInt(1, vo.getFeedCommentID());
            stmt.setInt(2, vo.getFeedID());
            stmt.setString(3, vo.getCommentTxt());
            stmt.setString(4, vo.getParentCommentID());
            stmt.setInt(5, vo.getAssociateID());
            stmt.setString(6, vo.getCommentedBy());
            stmt.setString(7, vo.getLastUserIDCd());
          /*  stmt.setString(8, vo.getLastUpdtTm());*/
          
            //...
            stmt.executeUpdate();
            System.out.println("Inserted records into the table...");

        } catch (SQLException se) {
            System.out.println("LmsFeedComments # " + se);
            throw new LmsDaoException(se.getMessage());
        } catch (Exception e) {
            System.out.println("LmsFeedComments # " + e);
            throw new LmsDaoException(e.getMessage());
        } finally {
            closeResources(conn, stmt, null);
        }

        System.out.println("Successfully saved....");
		
	}

	@Override
	public boolean deleteLmsFeedComments(LmsFeedCommentsVO vo)
			throws LmsDaoException {
		
		System.out.println("id =" + vo.getFeedCommentID());
        boolean status = true;

        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = getConnection();

            String sql = "DELETE FROM lms_feed_comments WHERE FEED_COMMENT_ID = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, vo.getFeedCommentID());
            stmt.executeUpdate();

            System.out.println("Deleted records into the lms_feed_comments table...");

        } catch (SQLException se) {
            System.out.println("LmsFeedComments # " + se);
            throw new LmsDaoException(se.getMessage());
        } catch (Exception e) {
            System.out.println("LmsFeedComments # " + e);
            throw new LmsDaoException(e.getMessage());
        } finally {
            closeResources(conn, stmt, null);
        }

        System.out.println("Successfully deleted....");
        return status;
	}
	

	@Override
	public LmsFeedCommentsVO getLmsFeedComments(int id) throws LmsDaoException {
		System.out.println("Inside getLmsFeedComments(?) >>");
        //Create object to return
		LmsFeedCommentsVO lmsFeedCommentsVO = new LmsFeedCommentsVO();
		
        //1 . jdbc code start
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = getConnection();

            String sql = "SELECT * FROM lms_feed_comments where FEED_COMMENT_ID=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, lmsFeedCommentsVO.getFeedCommentID());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                //3. Set db data to object
            	lmsFeedCommentsVO.setFeedCommentID(rs.getInt("FEED_COMMENT_ID"));
            	lmsFeedCommentsVO.setFeedID(rs.getInt("FEED_ID"));
            	lmsFeedCommentsVO.setCommentTxt(rs.getString("COMMENT_TXT"));
            	lmsFeedCommentsVO.setParentCommentID(rs.getString("PARENT_COMMENT_ID"));
            	lmsFeedCommentsVO.setAssociateID(rs.getInt("ASSOCIATE_ID"));
            	lmsFeedCommentsVO.setCommentedBy(rs.getString("COMMENTED_BY"));
            	lmsFeedCommentsVO.setLastUserIDCd(rs.getString("LAST_USERID_CD"));
            	lmsFeedCommentsVO.setLastUpdtTm(rs.getString("LAST_UPDT_TM"));       
            }

            System.out.println("get records into the table...");

        } catch (SQLException se) {
            System.out.println("getLmsFeedComments # " + se);
            throw new LmsDaoException(se.getMessage());
        } catch (Exception e) {
            System.out.println("getLmsFeedComments # " + e);
            throw new LmsDaoException(e.getMessage());
        } finally {
            closeResources(conn, stmt, null);
        }
     //1 . jdbc code endd

        //4 Return as required by method
        return lmsFeedCommentsVO;
	}
	
	@Override
	public List<LmsFeedCommentsVO> getLmsFeedCommentsVOList()
			throws LmsDaoException {
		
		List< LmsFeedCommentsVO> distList = new ArrayList<LmsFeedCommentsVO>();

        //1 . jdbc code start
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = getConnection();

            String sql = "SELECT * FROM lms_feed_comments ";
            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
            	
                //3. Set db data to object
            	LmsFeedCommentsVO lmsFeedCommentsVO = new LmsFeedCommentsVO();

            	lmsFeedCommentsVO.setFeedCommentID(rs.getInt("FEED_COMMENT_ID"));
            	lmsFeedCommentsVO.setFeedID(rs.getInt("FEED_ID"));
            	lmsFeedCommentsVO.setCommentTxt(rs.getString("COMMENT_TXT"));
            	lmsFeedCommentsVO.setParentCommentID(rs.getString("PARENT_COMMENT_ID"));
            	lmsFeedCommentsVO.setAssociateID(rs.getInt("ASSOCIATE_ID"));
            	lmsFeedCommentsVO.setCommentedBy(rs.getString("COMMENTED_BY"));
            	lmsFeedCommentsVO.setLastUserIDCd(rs.getString("LAST_USERID_CD"));
            	lmsFeedCommentsVO.setLastUpdtTm(rs.getString("LAST_UPDT_TM"));


                //Add into list
                distList.add(lmsFeedCommentsVO);
            }

            System.out.println("get records into the LIST...");

        } catch (SQLException se) {
            System.out.println("lmsFeedComments # " + se);
            throw new LmsDaoException(se.getMessage());
        } catch (Exception e) {
            System.out.println("lmsFeedComments # " + e);
            throw new LmsDaoException(e.getMessage());
        } finally {
            closeResources(conn, stmt, null);
        }
     //1 . jdbc code endd

        //4 Return as required by method
        return distList;
	}
	
	
	
	
	

}

package com.scolere.lms.persistance.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.scolere.lms.domain.exception.LmsDaoException;
import com.scolere.lms.domain.vo.AssignmentDtlVO;
import com.scolere.lms.persistance.dao.iface.AssignmentDtlDao;
import com.scolere.lms.persistance.factory.LmsDaoAbstract;

public class AssignmentDtlDaoImpl extends LmsDaoAbstract implements AssignmentDtlDao {

    @Override
    public boolean updateAssignmentDtl(AssignmentDtlVO vo) throws LmsDaoException {

        System.out.println("id =" + vo.getAssignmentDtlID());
        boolean status = true;

        //Database connection start
        Connection conn = null;
        PreparedStatement stmt = null;
        try {

            conn = getConnection();
            String sql = "UPDATE assignment_dtl set  ASSIGNMENT_ID=?, ASSIGNMENT_TYP_ID=?, DISPLAY_NO=?, LAST_USERID_CD=?, LAST_UPDT_TM=current_timestamp\n"
                    + "    WHERE ASSIGNMENT_DTL_ID=?";
            /* String sql = "UPDATE assignment_dtl set  ASSIGNMENT_ID=?, ASSIGNMENT_TYP_ID=?, DISPLAY_NO=?, LAST_USERID_CD=?\n"
             + "    WHERE ASSIGNMENT_DTL_ID=?";*/
            stmt = conn.prepareStatement(sql);

            stmt.setInt(1, vo.getAssignmentID());
            stmt.setInt(2, vo.getAssignmentTypID());
            stmt.setInt(3, vo.getDisplayNo());
            stmt.setString(4, vo.getLastUserIDCd());
            stmt.setInt(5, vo.getAssignmentDtlID());
            //stmt.setString(5, vo.getLastUpdtTm());
            stmt.executeUpdate();
            System.out.println("updated records into the table...");

        } catch (SQLException e) {
            System.out.println("getAssignmentDtlID 1# " + e);
            throw new LmsDaoException(e.getMessage());
        } catch (Exception e) {
            System.out.println("getAssignmentDtlID 2# " + e);
            throw new LmsDaoException(e.getMessage());
        } finally {
            closeResources(conn, stmt, null);
        }

        System.out.println("Successfully updated....");
        return status;
    }

    @Override
    public void saveAssignmentDtl(AssignmentDtlVO vo) throws LmsDaoException {

        //Database connection start
        Connection conn = null;
        PreparedStatement stmt = null;
        try {

            conn = getConnection();
            String sql = "INSERT INTO assignment_dtl(ASSIGNMENT_DTL_ID, ASSIGNMENT_ID, ASSIGNMENT_TYP_ID, "
                    + "DISPLAY_NO, LAST_USERID_CD, LAST_UPDT_TM)  VALUES(?, ?, ?, ?, ?, current_timestamp)";
            /*String sql = "INSERT INTO assignment_dtl(ASSIGNMENT_DTL_ID, ASSIGNMENT_ID, ASSIGNMENT_TYP_ID," +
             " DISPLAY_NO, LAST_USERID_CD)  VALUES(?, ?, ?, ?, ?)";*/

            stmt = conn.prepareStatement(sql);

            stmt.setInt(1, vo.getAssignmentDtlID());
            stmt.setInt(2, vo.getAssignmentID());
            stmt.setInt(3, vo.getAssignmentTypID());
            stmt.setInt(4, vo.getDisplayNo());
            stmt.setString(5, vo.getLastUserIDCd());
            //stmt.setString(6, vo.getLastUpdtTm());

            //...
            stmt.executeUpdate();
            System.out.println("Inserted records into the table...");

        } catch (SQLException se) {
            System.out.println("AssignmentDtlVO # " + se);
            throw new LmsDaoException(se.getMessage());
        } catch (Exception e) {
            System.out.println("AssignmentDtlVO # " + e);
            throw new LmsDaoException(e.getMessage());
        } finally {
            closeResources(conn, stmt, null);
        }

        System.out.println("Successfully saved....");
    }

    @Override
    public boolean deleteAssignmentDtl(AssignmentDtlVO vo) throws LmsDaoException {

        System.out.println("id =" + vo.getAssignmentDtlID());
        boolean status = true;

        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = getConnection();

            String sql = "DELETE FROM assignment_dtl WHERE ASSIGNMENT_DTL_ID = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, vo.getAssignmentDtlID());
            stmt.executeUpdate();

            System.out.println("Deleted records into the table...");

        } catch (SQLException se) {
            System.out.println("ASSIGNMENT_DTL # " + se);
            throw new LmsDaoException(se.getMessage());
        } catch (Exception e) {
            System.out.println("ASSIGNMENT_DTL # " + e);
            throw new LmsDaoException(e.getMessage());
        } finally {
            closeResources(conn, stmt, null);
        }

        System.out.println("Successfully deleted....");
        return status;
    }

    @Override
    public AssignmentDtlVO getAssignmentDtl(int id) throws LmsDaoException {

        System.out.println("Inside AssignmentDtl(?) >>");
        //Create object to return
        AssignmentDtlVO assignmentDtlVO = new AssignmentDtlVO();

        //1 . jdbc code start
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = getConnection();

            String sql = "SELECT * FROM assignment_dtl where ASSIGNMENT_DTL_ID=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, assignmentDtlVO.getAssignmentDtlID());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                //3. Set db data to object

                assignmentDtlVO.setAssignmentDtlID(rs.getInt("ASSIGNMENT_DTL_ID"));
                assignmentDtlVO.setAssignmentID(rs.getInt("ASSIGNMENT_ID"));
                assignmentDtlVO.setAssignmentTypID(rs.getInt("ASSIGNMENT_TYP_ID"));
                assignmentDtlVO.setDisplayNo(rs.getInt("DISPLAY_NO"));
                assignmentDtlVO.setLastUserIDCd(rs.getString("LAST_USERID_CD"));
                assignmentDtlVO.setLastUpdtTm(rs.getString("LAST_UPDT_TM"));

            }

            System.out.println("get records into the table...");

        } catch (SQLException se) {
            System.out.println("AssignmentDtl # " + se);
            throw new LmsDaoException(se.getMessage());
        } catch (Exception e) {
            System.out.println("AssignmentDtl # " + e);
            throw new LmsDaoException(e.getMessage());
        } finally {
            closeResources(conn, stmt, null);
        }
        //1 . jdbc code endd

        //4 Return as required by method
        return assignmentDtlVO;
    }

    @Override
    public List<AssignmentDtlVO> getAssignmentDtlList() throws LmsDaoException {
        List< AssignmentDtlVO> distList = new ArrayList<AssignmentDtlVO>();

        //1 . jdbc code start
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = getConnection();

            String sql = "SELECT * FROM assignment_dtl ";
            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {

                AssignmentDtlVO assignmentDtlVO = new AssignmentDtlVO();

                assignmentDtlVO.setAssignmentDtlID(rs.getInt("ASSIGNMENT_DTL_ID"));
                assignmentDtlVO.setAssignmentID(rs.getInt("ASSIGNMENT_ID"));
                assignmentDtlVO.setAssignmentTypID(rs.getInt("ASSIGNMENT_TYP_ID"));
                assignmentDtlVO.setDisplayNo(rs.getInt("DISPLAY_NO"));
                assignmentDtlVO.setLastUserIDCd(rs.getString("LAST_USERID_CD"));
                assignmentDtlVO.setLastUpdtTm(rs.getString("LAST_UPDT_TM"));

                //Add into list
                distList.add(assignmentDtlVO);
            }

            System.out.println("get records into the list...");

        } catch (SQLException se) {
            System.out.println("assignmentDtl # " + se);
            throw new LmsDaoException(se.getMessage());
        } catch (Exception e) {
            System.out.println("assignmentDtl # " + e);
            throw new LmsDaoException(e.getMessage());
        } finally {
            closeResources(conn, stmt, null);
        }
        //1 . jdbc code endd
        //4 Return as required by method
        return distList;
    }
}

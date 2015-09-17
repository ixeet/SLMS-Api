/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scolere.lms.persistance.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.scolere.lms.domain.exception.LmsDaoException;
import com.scolere.lms.domain.vo.cross.NotificationVO;
import com.scolere.lms.persistance.dao.iface.NotificationDao;
import com.scolere.lms.persistance.factory.LmsDaoAbstract;



/**
 *
 * @author dell
 */
public class NotificationDaoImpl extends LmsDaoAbstract implements NotificationDao{

	@Override
	public int saveUserDevice(NotificationVO vo) throws LmsDaoException {
        int count=0;
        //Database connection start
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = getConnection();
            
            String sql = "INSERT INTO user_devices(USER_ID, DEVICE_TYPE, DEVICE_TOKEN)VALUES(?, ?,?)";

            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, vo.getUserId());
            stmt.setString(2, vo.getDeviceType());
            stmt.setString(3, vo.getDeviceToken());

            count=stmt.executeUpdate();
            System.out.println("Inserted records into the table..."+count);
            
        } catch (SQLException se) {
            System.out.println("saveUserDevice # " + se);
            throw new LmsDaoException(se.getMessage());
        } catch (Exception e) {
            System.out.println("saveUserDevice # " + e);
            throw new LmsDaoException(e.getMessage());
        } finally {
            closeResources(conn, stmt, null);
        }

        return count;
	}

	
	@Override
	public boolean isDeviceRegistered(NotificationVO vo) throws LmsDaoException {
		boolean status = false;
		
		String query="SELECT count(*) FROM user_devices where USER_ID="+vo.getUserId()+" and DEVICE_TYPE='"+vo.getDeviceType()+"' and DEVICE_TOKEN='"+vo.getDeviceToken()+"'";
		int count = getCountQueryResult(query);
		if(count>0)
			status=true;
		
		return status;
	}

	
	@Override
	public NotificationVO getUserDevice(int userId) throws LmsDaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NotificationVO> getUserDevicesList(String deviceType)
			throws LmsDaoException {
		// TODO Auto-generated method stub
		return null;
	}
    
}//End of class

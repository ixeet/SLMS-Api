/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scolere.lms.persistance.factory;

import com.scolere.lms.domain.constants.LMSConstants;
import com.scolere.lms.domain.exception.LmsDaoException;
import com.scolere.lms.domain.vo.CommonKeyValueVO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dell
 */
public abstract class LmsDaoAbstract extends JDBCDAOAbstract{
    
 public static String dataSource = LMSConstants.APP_DATASOURCE;
 
 /**
  * This method use to close JDBC resources.
  * @param Connection
  * @param Statement
  * @param ResultSet 
  */    
 public void closeResources(Connection conn,Statement stmt,ResultSet res)
  {
 	try{
 	if(res!=null) {
                 res.close();
             }
 	if(stmt!=null) {
                 stmt.close();
             }
 	if(conn!=null) {
                 conn.close();
             }
 	}catch(Exception e){
             System.out.println(e.getMessage());
         }

  }

 public void closeResources(Connection conn)
  {
 	try{
             
 	if(conn!=null) {
                 conn.close();
             }
 	}catch(Exception e){
             System.out.println(e.getMessage());
         }

  }    

 
 public boolean deleteOrUpdateByQuery(String query) throws LmsDaoException {
     System.out.println("Inside deleteOrUpdateByQuery>>>>");

     boolean status=false;
     
    Connection conn = null;
    PreparedStatement cstmt = null;
    ResultSet resultSet = null;

    try {
        System.out.println("Query : " + query);
        conn = this.getConnection(dataSource);
        
        cstmt = conn.prepareStatement(query);
        int t=cstmt.executeUpdate();
        System.out.println("No of affected row = "+t);
        status = true;
    } catch (Exception e) {
        System.out.println("Error deleteOrUpdateByQuery - "+e.getMessage());
    } finally {
        closeResources(conn, cstmt, resultSet);
    }

    return status;  
}

 
 public List<String> getStrList(String query) throws LmsDaoException {

     List<String> list=new ArrayList<String>();

      Connection conn = null;
      PreparedStatement cstmt = null;
      ResultSet resultSet = null;

      try {
          //SELECT DEALER_CD  FROM ACT.MQT_DATA_DLRSHIP_EMP_CUR where DISTRICT_CD='SEZ2D07'
          System.out.println("Query : " + query);

          conn = this.getConnection(dataSource);
          cstmt = conn.prepareStatement(query);
          resultSet = cstmt.executeQuery();
          
          while(resultSet.next())
          {
              list.add(resultSet.getString(1));
          }

          System.out.println("No of Object returned : "+list.size());

      } catch (Exception e) {
          list = new ArrayList<String>();
          System.out.println("Error > getStrList - "+e.getMessage());
      } finally {
          closeResources(conn, cstmt, resultSet);
      }

      return list;        
  }


     public List<CommonKeyValueVO> getKeyValuePairList(String query) throws LmsDaoException {

        List<CommonKeyValueVO> list=new ArrayList<CommonKeyValueVO>();

         Connection conn = null;
         PreparedStatement cstmt = null;
         ResultSet resultSet = null;

         try {
             //SELECT DEALER_CD,DEALER_NM  FROM ACT.MQT_DATA_DLRSHIP_EMP_CUR where DISTRICT_CD='SEZ2D07'
             System.out.println("Query : " + query);

             conn = this.getConnection(dataSource);
             cstmt = conn.prepareStatement(query);
             resultSet = cstmt.executeQuery();
             
             CommonKeyValueVO commonKeyValueVO=null;
             while(resultSet.next())
             {
                 commonKeyValueVO=new CommonKeyValueVO(resultSet.getString(1), resultSet.getString(2));
                 list.add(commonKeyValueVO);
             }

             System.out.println("No of Object returned : "+list.size());

         } catch (Exception e) {
             list = new ArrayList<CommonKeyValueVO>();
             System.out.println("Error > getKeyValuePairList - "+e.getMessage());
         } finally {
             closeResources(conn, cstmt, resultSet);
         }

         return list;        
     }

     
     public String getLastUpdatedOn(String tableName) throws LmsDaoException {
     
         String lastUpdatedOn = null;

         Connection conn = null;
         PreparedStatement cstmt = null;
         ResultSet resultSet = null;

         try {
             String query="SELECT MAX(DATE(LAST_UPDT_TM)) FROM ACT."+tableName;
             System.out.println("Query : " + query);

             conn = this.getConnection(dataSource);
             cstmt = conn.prepareStatement(query);
             resultSet = cstmt.executeQuery();
             if(resultSet.next())
             {
             lastUpdatedOn=resultSet.getString(1);
             }
             

         } catch (Exception e) {
             System.out.println("Error > getLastUpdatedOn - "+e.getMessage());
         } finally {
             closeResources(conn, cstmt, resultSet);
         }

         return lastUpdatedOn;        
     }

     
     public String getQueryConcatedResult(String query) throws LmsDaoException {
     
         String result = "?";

         Connection conn = null;
         PreparedStatement cstmt = null;
         ResultSet resultSet = null;

         try {
            // String query="SELECT MAX(DATE(LAST_UPDT_TM)) FROM ACT."+tableName;
             System.out.println("Query : " + query);

             conn = this.getConnection(dataSource);
             cstmt = conn.prepareStatement(query);
             resultSet = cstmt.executeQuery();
             if(resultSet.next())
             {
            	 result=resultSet.getString(1);
             }
             

         } catch (Exception e) {
             System.out.println("Error > getQueryConcatedResult - "+e.getMessage());
         } finally {
             closeResources(conn, cstmt, resultSet);
         }

         return result;        
     }
     


     public <T> T getById(String query,Class outputClass) throws LmsDaoException {
     
         T vo = null;

         Connection conn = null;
         PreparedStatement cstmt = null;
         ResultSet resultSet = null;

         try {
             System.out.println("Query : " + query);

             conn = this.getConnection(dataSource);
             cstmt = conn.prepareStatement(query);
             resultSet = cstmt.executeQuery();

             //Map result set to object
             vo = new ResultSetMapper<T>().mapRersultSetToObject(resultSet, outputClass);
             System.out.println("Object returned : "+vo);

         } catch (Exception e) {
             System.out.println("Error getById - "+e.getMessage());
         } finally {
             closeResources(conn, cstmt, resultSet);
         }

         return vo;        
     }

    
     public <T> List<T> getAll(String query,Class outputClass) throws LmsDaoException {

        List<T> list=null;

         Connection conn = null;
         PreparedStatement cstmt = null;
         ResultSet resultSet = null;

         try {
              System.out.println("Query : " + query);

             conn = this.getConnection(dataSource);
             cstmt = conn.prepareStatement(query);
             resultSet = cstmt.executeQuery();

             //Map result set to list
             list = new ResultSetMapper<T>().mapRersultSetToObjectList(resultSet, outputClass);

             System.out.println("No of Object returned : "+list.size());

         } catch (Exception e) {
             list = new ArrayList<T>();
             //throw new LmsDaoException("Error calling getById > " + e.getMessage());
            // System.out.println("Error getAll - "+e.getMessage());
         } finally {
             closeResources(conn, cstmt, resultSet);
         }

         return list;        
     
     }


 }

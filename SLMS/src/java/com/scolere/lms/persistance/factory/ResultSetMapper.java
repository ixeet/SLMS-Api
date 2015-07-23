package com.scolere.lms.persistance.factory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import org.apache.commons.beanutils.BeanUtils;


public class ResultSetMapper<T> {

    public T getById(Connection conn,String query,Class outputClass) throws SQLException {
    
        T vo = null;

        PreparedStatement cstmt = null;
        ResultSet resultSet = null;

        try {
            System.out.println("Query : " + query);

            cstmt = conn.prepareStatement(query);
            resultSet = cstmt.executeQuery();

            //Map result set to object
            vo = this.mapRersultSetToObject(resultSet, outputClass);
            System.out.println(outputClass.getSimpleName()+" returned : "+vo);

        } catch (Exception e) {
            throw new SQLException("Error calling getById > " + e.getMessage());
        } finally {
            closeResources(conn, cstmt, resultSet);
        }

        return vo;        
    }

   
    public List<T> getAll(Connection conn,String query,Class outputClass) throws SQLException {

       List<T>  list=null;

        PreparedStatement cstmt = null;
        ResultSet resultSet = null;

        try {
             System.out.println("Query : " + query);

            cstmt = conn.prepareStatement(query);
            resultSet = cstmt.executeQuery();

            //Map result set to list
            list = this.mapRersultSetToObjectList(resultSet, outputClass);

            System.out.println("No of "+outputClass.getSimpleName()+" returned : "+list.size());

        } catch (Exception e) {
            list = new ArrayList<T>();
            throw new SQLException("Error calling getById > " + e.getMessage());
        } finally {
            closeResources(conn, cstmt, resultSet);
        }

        return list;        
    
    }

        
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
        


        
    //-----------Do not update below code----------------    

	public List<T> mapRersultSetToObjectList(ResultSet rs, Class outputClass) {
		List<T> outputList = null;
		try {
			// make sure resultset is not null
			if (rs != null) {
				// check if outputClass has 'Entity' annotation
				if (outputClass.isAnnotationPresent(Entity.class)) {
					// get the resultset metadata
					ResultSetMetaData rsmd = rs.getMetaData();
					// get all the attributes of outputClass
					Field[] fields = outputClass.getDeclaredFields();
					while (rs.next()) {
						T bean = (T) outputClass.newInstance();
						for (int _iterator = 0; _iterator < rsmd
								.getColumnCount(); _iterator++) {
							// getting the SQL column name
							String columnName = rsmd
									.getColumnName(_iterator + 1);
							// reading the value of the SQL column
							Object columnValue = rs.getObject(_iterator + 1);
							// iterating over outputClass attributes to check if any attribute has 'Column' annotation with matching 'name' value
							for (Field field : fields) {
								if (field.isAnnotationPresent(Column.class)) {
									Column column = field
											.getAnnotation(Column.class);
									if (column.name().equalsIgnoreCase(
											columnName)
											&& columnValue != null) {
										BeanUtils.setProperty(bean, field
												.getName(), columnValue);
										break;
									}
								}
							}
						}
						if (outputList == null) {
							outputList = new ArrayList<T>();
						}
						outputList.add(bean);
					}

				} else {
					// throw some error
				}
			} else {
				return null;
			}
		} catch (IllegalAccessException e) {
		} catch (SQLException e) {
		} catch (InstantiationException e) {
		} catch (InvocationTargetException e) {
		}
		return outputList;
	}

	public T mapRersultSetToObject(ResultSet rs, Class outputClass) {
		T obj = null;
		try {
			// make sure resultset is not null
			if (rs != null) {
				// check if outputClass has 'Entity' annotation
				if (outputClass.isAnnotationPresent(Entity.class)) {
					// get the resultset metadata
					ResultSetMetaData rsmd = rs.getMetaData();
					// get all the attributes of outputClass
					Field[] fields = outputClass.getDeclaredFields();
					if (rs.next()) {
						T bean = (T) outputClass.newInstance();
						for (int _iterator = 0; _iterator < rsmd
								.getColumnCount(); _iterator++) {
							// getting the SQL column name
							String columnName = rsmd
									.getColumnName(_iterator + 1);
							// reading the value of the SQL column
							Object columnValue = rs.getObject(_iterator + 1);
							// iterating over outputClass attributes to check if any attribute has 'Column' annotation with matching 'name' value
							for (Field field : fields) {
								if (field.isAnnotationPresent(Column.class)) {
									Column column = field
											.getAnnotation(Column.class);
									if (column.name().equalsIgnoreCase(
											columnName)
											&& columnValue != null) {
										BeanUtils.setProperty(bean, field
												.getName(), columnValue);
										break;
									}
								}
							}
						}
						obj=bean;
					}

				} else {
					// throw some error
				}
			} else {
				return null;
			}
		} catch (IllegalAccessException e) {
		} catch (SQLException e) {
		} catch (InstantiationException e) {
		} catch (InvocationTargetException e) {
		}
		return obj;
	}
}

